package com.example.sagicoupon.controllers;

import com.example.sagicoupon.services.CompanyService;
import com.example.sagicoupon.model.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/addnew")
    public Company addNewCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @GetMapping("/{id}/showbyid")
    public Company showCompanyById(@PathVariable Long id) {
        return companyService.findCompanyById(id);
    }

    @GetMapping("/showall")
    public List<Company> showAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PutMapping("/update")
    public Company updateCompany(@RequestBody Company company) {
        return companyService.updateCompany(company);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteCompanyById(@PathVariable Long id) {
        companyService.deleteCompanyById(id);
    }
}
