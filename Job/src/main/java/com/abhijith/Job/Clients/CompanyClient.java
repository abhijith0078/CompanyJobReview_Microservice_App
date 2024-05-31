package com.abhijith.Job.Clients;

import com.abhijith.Job.External.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-MS")
public interface CompanyClient {

    @GetMapping("/api/companies/{id}")
    Company getCompany(@PathVariable("id") Long id);
}
