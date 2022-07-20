package com.example.sagicoupon.repositories;

import com.example.sagicoupon.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Long> {

    Optional<UserDto> findFirstByFirstName (String firstName);

    Optional<UserDto> findFirstByLastName (String lastName);

    Optional<UserDto> findFirstByUsername (String username);

    Optional<UserDto> findFirstByPassword (String password);
}
