package com.abhijith.Job.Service;

import com.abhijith.Job.Clients.CompanyClient;
import com.abhijith.Job.Clients.ReviewClient;
import com.abhijith.Job.DTO.JobCompanyReviewDTO;
import com.abhijith.Job.Exception.JobException;
import com.abhijith.Job.External.Company;
import com.abhijith.Job.External.Review;
import com.abhijith.Job.Mapper.JobMapper;
import com.abhijith.Job.Model.Job;
import com.abhijith.Job.Repository.JobRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService{

    private final JobRepositoryDAO jobRepositoryDAO;

    @Autowired
    RestTemplate restTemplate; //load balanced rest template

    CompanyClient companyClient;
    ReviewClient reviewClient;
    public JobServiceImpl(JobRepositoryDAO jobRepositoryDAO,CompanyClient companyClient, ReviewClient reviewClient){
        this.jobRepositoryDAO = jobRepositoryDAO;
        this.companyClient= companyClient;
        this.reviewClient= reviewClient;
    }
    @Override
    public List<JobCompanyReviewDTO> findAllJobs() {
//      -----   Rest templates  --------
        List<Job> jobs= jobRepositoryDAO.findAll();
        return jobs.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public JobCompanyReviewDTO mapToDTO(Job job){
//            RestTemplate restTemplate = new RestTemplate();
//        Company company = restTemplate.getForObject("http://COMPANY-MS:8081/api/companies/"+ job.getCompanyId(), Company.class);
        Company company = companyClient.getCompany(job.getCompanyId());

//        ResponseEntity<List<Review>> reviewRespose = restTemplate.exchange("http://Review-MS:8083/api/reviews?companyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {
//                });
//        List<Review> reviews = reviewRespose.getBody();

            List<Review> reviews= reviewClient.getReviews(job.getCompanyId());

            JobCompanyReviewDTO jobCompanyReviewDTO = JobMapper.jobCompanyDTOMapper(job,company,reviews);
            return jobCompanyReviewDTO;
    }

    @Override
    public Job addJob(Job job) {
        return jobRepositoryDAO.save(job);
    }

    @Override
    public JobCompanyReviewDTO findJobById(Long id) throws JobException {

        Job job =  jobRepositoryDAO.findById(id).orElseThrow(()-> new JobException("Job Not Found"));
        if(job == null)
            return null;
        return mapToDTO(job);
    }

    @Override
    public boolean deleteJob(Long id) {
        if(jobRepositoryDAO.existsById(id)){
            jobRepositoryDAO.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job job) throws JobException {
        Job job1= jobRepositoryDAO.findById(id).orElseThrow(()-> new JobException("Job Not Found"));
        job1.setCompanyId(job.getCompanyId());
        job1.setDescription(job.getDescription());
        job1.setLocation(job.getLocation());

        job1.setMaxSalary(job.getMaxSalary());
        job1.setMinSalary(job.getMinSalary());
        job1.setTitle(job.getTitle());
        try {
            jobRepositoryDAO.save(job1);
            return true;
        }catch (Exception exception){
            return false;
        }
    }
}
