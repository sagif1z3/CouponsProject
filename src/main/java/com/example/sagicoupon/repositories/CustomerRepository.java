package com.example.sagicoupon.repositories;

import com.example.sagicoupon.dto.CustomerDto;
import com.example.sagicoupon.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDto, Long> {

    @Query(value = "SELECT * FROM users u INNER JOIN customers c WHERE u.first_name = :firstName",nativeQuery = true)
    Optional<CustomerDto> findByFirstName(String firstName);

    @Query(value = "SELECT * FROM users u INNER JOIN customers c WHERE u.last_name = :lastName",nativeQuery = true)
    Optional<CustomerDto> findByLastName(String lastName);

    @Query(value = "SELECT * FROM users u INNER JOIN customers c WHERE u.username = :username",nativeQuery = true)
    Optional<CustomerDto> findByUsername(String username);

    @Query(value = "SELECT * FROM users u INNER JOIN customers c WHERE u.password = :password",nativeQuery = true)
    Optional<CustomerDto> findByPassword(String password);

    @Query(value = "SELECT * FROM users u INNER JOIN customers c WHERE u.user_type = :userType",nativeQuery = true)
    Optional<CustomerDto> findByUserType(UserType userType);

    Optional<CustomerDto> findFirstByAddress (String address);

    Optional<CustomerDto> findFirstByBirthDate (Date birthDate);

}
