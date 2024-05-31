package com.abhijith.Job.Mapper;

import com.abhijith.Job.DTO.JobCompanyReviewDTO;
import com.abhijith.Job.External.Company;
import com.abhijith.Job.External.Review;
import com.abhijith.Job.Model.Job;

import java.util.List;

public class JobMapper {
    public static JobCompanyReviewDTO jobCompanyDTOMapper(Job job, Company company, List<Review> review){
        JobCompanyReviewDTO jobCompanyReviewDTO = new JobCompanyReviewDTO();
        jobCompanyReviewDTO.setCompany(company);
        jobCompanyReviewDTO.setReview(review);
        jobCompanyReviewDTO.setId(job.getId());
        jobCompanyReviewDTO.setDescription(job.getDescription());
        jobCompanyReviewDTO.setLocation(job.getLocation());
        jobCompanyReviewDTO.setMaxSalary(job.getMaxSalary());
        jobCompanyReviewDTO.setMinSalary(job.getMinSalary());
        jobCompanyReviewDTO.setTitle(job.getTitle());
        return jobCompanyReviewDTO;
    }
}
