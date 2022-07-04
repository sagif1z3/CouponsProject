package com.example.sagicoupon.repositories;

import com.example.sagicoupon.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDto, Long> {
}
