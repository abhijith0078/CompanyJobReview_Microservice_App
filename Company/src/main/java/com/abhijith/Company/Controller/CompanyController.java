package com.abhijith.Company.Controller;

import com.abhijith.Company.Exception.CompanyException;
import com.abhijith.Company.Model.Company;
import com.abhijith.Company.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("")
    public ResponseEntity<List<Company>> getCompanies(){
        return ResponseEntity.ok(companyService.findAllCompanies());
    }
    @PostMapping("")
    public ResponseEntity<String> addCompany(@RequestBody Company company){
         companyService.addCompany(company);
        return new ResponseEntity<>("Company Added",HttpStatus.OK);
    }
    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getJob(@PathVariable("companyId") Long id) throws CompanyException {
        return new ResponseEntity<>(companyService.findComapnyById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{companyId}")
    public  ResponseEntity<String> deleteCompany(@PathVariable("companyId") Long id) throws CompanyException {
        boolean status= companyService.deleteCompany(id);
        if(!status) {
            throw  new CompanyException("Company Deletion didn't happen");
        }
        return ResponseEntity.ok("Company deleted");

    }
    @PutMapping("/{companyId}")
    public  ResponseEntity<String> updateCompany(@PathVariable("companyId") Long id, @RequestBody Company company) throws  CompanyException {
        if(companyService.updateCompany(id, company)) {
            return ResponseEntity.ok("Company updated");
        }
        return ResponseEntity.ok("Company not updated");
    }
}
