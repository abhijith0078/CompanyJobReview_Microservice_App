package com.abhijith.Company.Service;

import com.abhijith.Company.Exception.CompanyException;
import com.abhijith.Company.Model.Company;

import java.util.List;


public interface CompanyService {
    List<Company> findAllCompanies();

    Company addCompany(Company company);

    Company findComapnyById(Long id) throws CompanyException;

    boolean deleteCompany(Long id);

    boolean updateCompany(Long id, Company company) throws CompanyException;
}
