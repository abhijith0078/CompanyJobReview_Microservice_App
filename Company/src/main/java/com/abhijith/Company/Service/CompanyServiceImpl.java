package com.abhijith.Company.Service;

import com.abhijith.Company.Exception.CompanyException;
import com.abhijith.Company.Model.Company;
import com.abhijith.Company.Repository.CompanyRepositoryDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl  implements  CompanyService{

    private final CompanyRepositoryDAO CompanyRepositoryDAO;
    public CompanyServiceImpl(CompanyRepositoryDAO CompanyRepositoryDAO){
        this.CompanyRepositoryDAO = CompanyRepositoryDAO;
    }
    @Override
    public List<Company> findAllCompanies() {
        return CompanyRepositoryDAO.findAll();
    }

    @Override
    public Company addCompany(Company company) {
        return CompanyRepositoryDAO.save(company);
    }

    @Override
    public Company findComapnyById(Long id) throws CompanyException {
        return CompanyRepositoryDAO.findById(id).orElseThrow(()->new CompanyException("Company Not Found"));
    }

    @Override
    public boolean deleteCompany(Long id) {
        if(CompanyRepositoryDAO.existsById(id)){
            CompanyRepositoryDAO.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateCompany(Long id, Company company) throws CompanyException {
        Company company1 = findComapnyById(id);
        company1.setName(company.getName());
        company1.setDescription(company.getDescription());
        try {
            CompanyRepositoryDAO.save(company1);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

}
