package com.user.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    List<User> findByStartDateGreaterThanEqualAndLastDateLessThanEqual(LocalDate startDate, LocalDate endDate);

    @Query("SELECT u FROM User u WHERE " +
            "(:firstName IS NULL OR LOWER(u.firstName) = LOWER(:firstName)) AND " +
            "(:lastName IS NULL OR LOWER(u.lastName) = LOWER(:lastName)) AND " +
            "(:city IS NULL OR LOWER(u.city) = LOWER(:city))")
     List<User> findByFirstNameAndLastName(
         @Param("firstName") String firstName,
         @Param("lastName") String lastName,
         @Param("city") String city
     );
 }