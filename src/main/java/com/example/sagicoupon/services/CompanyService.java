package com.example.sagicoupon.services;

import com.example.sagicoupon.dto.CompanyDto;
import com.example.sagicoupon.model.Company;
import java.util.List;

public interface CompanyService {

    Company addCompany (Company company);

    List<Company> getAllCompanies ();

    Company findCompanyById (long id);

    Company updateCompany (Company company);

    void deleteCompanyById (long id);

    CompanyDto getCompanyDtoById (long id);
}
