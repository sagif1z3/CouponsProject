package com.example.sagicoupon.validators;

import com.example.sagicoupon.dto.CompanyDto;
import com.example.sagicoupon.enums.ErrorType;
import com.example.sagicoupon.exceptions.ServerException;
import com.example.sagicoupon.model.Company;
import com.example.sagicoupon.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CompanyValidators {

    private CompanyRepository companyRepository;

    public boolean addCompanyValidations(Company company) {

        companyRepository.findFirstByName(company.getName())
                .ifPresent(companyDto -> {
                    throw new ServerException(ErrorType.COMPANY_ALREADY_EXIST);
                });

        Optional.of(company)
                .map(Company::getName)
                .filter(Regex::textValidation)
                .orElseThrow(() -> new ServerException(ErrorType.COMPANY_NAME_IS_INVALID));

        Optional.of(company)
                .map(Company::getPhoneNumber)
                .filter(Regex::phoneValidation)
                .orElseThrow(() -> new ServerException(ErrorType.COMPANY_PHONE_NUMBER_IS_INVALID));

        Optional.of(company)
                .map(Company::getAddress)
                .filter(Regex::addressValidation)
                .orElseThrow(() -> new ServerException(ErrorType.COMPANY_ADDRESS_IS_INVALID));

        return true;
    }

    public boolean updateCompanyValidation(Company company) {
        Optional<CompanyDto> find = companyRepository.findById(company.getId());
        find.ifPresentOrElse(
                (CompanyDto) -> System.out.println(
                        "Found company: " + company),
                () -> {
                    throw new ServerException(ErrorType.COMPANY_DOES_NOT_EXIST, String.valueOf(company.getId()));
                });

        Optional.of(company)
                .map(Company::getName)
                .filter(Regex::textValidation)
                .orElseThrow(() -> new ServerException(ErrorType.COMPANY_NAME_IS_INVALID));

        Optional.of(company)
                .map(Company::getPhoneNumber)
                .filter(Regex::phoneValidation)
                .orElseThrow(() -> new ServerException(ErrorType.COMPANY_PHONE_NUMBER_IS_INVALID));

        Optional.of(company)
                .map(Company::getAddress)
                .filter(Regex::addressValidation)
                .orElseThrow(() -> new ServerException(ErrorType.COMPANY_ADDRESS_IS_INVALID));

        return true;
    }
}
