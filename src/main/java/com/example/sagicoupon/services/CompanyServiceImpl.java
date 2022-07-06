package com.example.sagicoupon.services;

import com.example.sagicoupon.model.Company;
import com.example.sagicoupon.converters.CompanyToCompanyDtoConverter;
import com.example.sagicoupon.converters.CompanyDtoToCompanyConverter;
import com.example.sagicoupon.repositories.CompanyRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private CompanyToCompanyDtoConverter companyToCompanyDtoConverter;
    private CompanyDtoToCompanyConverter companyDtoToCompanyConverter;

    @Autowired
    public CompanyServiceImpl(CompanyRepository CompanyRepository,
                              @Lazy CompanyToCompanyDtoConverter companyToCompanyDtoConverter,
                              @Lazy CompanyDtoToCompanyConverter companyDtoToCompanyConverter) {
        this.companyRepository = CompanyRepository;
        this.companyToCompanyDtoConverter = companyToCompanyDtoConverter;
        this.companyDtoToCompanyConverter = companyDtoToCompanyConverter;
    }

    public Company addCompany(Company company) {
        return Optional.ofNullable(companyToCompanyDtoConverter.convert(company))
                .map(companyRepository::save)
                .map(companyDtoToCompanyConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot save company"));
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(companyDtoToCompanyConverter::convert)
                .collect(Collectors.toList());
    }

    public Company findCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(companyDtoToCompanyConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot find company by id"));
    }

    public Company updateCompany(Company company) {
        Company existingCompany = null;
        try {
            existingCompany = findCompanyById(company.getId());
        } catch (RuntimeException e) {
            throw new RuntimeException("Could not update company because company not found ");
        }

        return Optional.ofNullable(companyToCompanyDtoConverter.convert(company))
                .map(companyRepository::save)
                .map(companyDtoToCompanyConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot update company"));
    }

    public void deleteCompanyById(Long id) {
        Optional.ofNullable(companyToCompanyDtoConverter.convert(findCompanyById(id)))
                .ifPresent(companyRepository::delete);
    }
}
