package com.abhijith.Company.Repository;


import com.abhijith.Company.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepositoryDAO extends JpaRepository<Company,Long> {
}
