package com.example.sagicoupon.controllers;

import com.example.sagicoupon.services.CompanyService;
import com.example.sagicoupon.model.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

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
