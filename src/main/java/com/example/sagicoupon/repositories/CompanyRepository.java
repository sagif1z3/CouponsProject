package com.example.sagicoupon.repositories;

import com.example.sagicoupon.dto.CompanyDto;
import com.example.sagicoupon.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyDto, Long> {

    Optional<CompanyDto> findFirstByName(String name);

    Optional<CompanyDto> findFirstByPhoneNumber(String phoneNumber);

    Optional<CompanyDto> findFirstByAddress(String address);

}
