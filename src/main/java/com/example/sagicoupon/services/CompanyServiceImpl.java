package com.example.sagicoupon.services;

import com.example.sagicoupon.dto.CompanyDto;
import com.example.sagicoupon.model.Company;
import com.example.sagicoupon.converters.CompanyToCompanyDtoConverter;
import com.example.sagicoupon.converters.CompanyDtoToCompanyConverter;
import com.example.sagicoupon.repositories.CompanyRepository;
import com.example.sagicoupon.validators.CompanyValidators;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private CompanyValidators companyValidators;
    private CompanyToCompanyDtoConverter companyToCompanyDtoConverter;
    private CompanyDtoToCompanyConverter companyDtoToCompanyConverter;

    @Override
    public Company addCompany(Company company) {
        return Optional.ofNullable(company)
                .filter(companyValidators::addCompanyValidations)
                .map(companyToCompanyDtoConverter::convertSave)
                .map(companyRepository::save)
                .map(companyDtoToCompanyConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot save company because company was null"));
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(companyDtoToCompanyConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(companyDtoToCompanyConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot find company by id"));
    }

    @Override
    public Company updateCompany(Company company) {
        return Optional.ofNullable(company)
                .filter(companyValidators::updateCompanyValidation)
                .map(companyToCompanyDtoConverter::convertUpdate)
                .map(companyRepository::save)
                .map(companyDtoToCompanyConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot update company"));
    }

    @Override
    public void deleteCompanyById(Long id) {
        Optional.ofNullable(findCompanyById(id))
                .map(companyToCompanyDtoConverter::convert)
                .ifPresent(companyRepository::delete);
    }

    @Override
    public CompanyDto getCompanyDtoById(Long id) {
        return companyRepository.findById(id).get();
    }
}
