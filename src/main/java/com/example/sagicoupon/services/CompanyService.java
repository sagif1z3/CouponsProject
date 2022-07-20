package com.example.sagicoupon.services;

import com.example.sagicoupon.dto.CompanyDto;
import com.example.sagicoupon.model.Company;
import java.util.List;

public interface CompanyService {

    Company addCompany (Company company);

    List<Company> getAllCompanies ();

    Company findCompanyById (Long id);

    Company updateCompany (Company company);

    void deleteCompanyById (Long id);

    CompanyDto getCompanyDtoById (Long id);
}
