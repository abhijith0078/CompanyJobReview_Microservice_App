package com.abhijith.Job.Controller;

import com.abhijith.Job.DTO.JobCompanyReviewDTO;
import com.abhijith.Job.Exception.JobException;
import com.abhijith.Job.Model.Job;
import com.abhijith.Job.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;
    @GetMapping("")
    public ResponseEntity<List<JobCompanyReviewDTO>> getJobs(){
        return ResponseEntity.ok(jobService.findAllJobs());
    }
    @PostMapping("")
    public ResponseEntity<String> addJobs(@RequestBody Job job){
        jobService.addJob(job);
        return ResponseEntity.ok("Job Added");
    }
    @GetMapping("/{jobId}")
    public ResponseEntity<JobCompanyReviewDTO> getJob(@PathVariable("jobId") Long id) throws JobException {
        JobCompanyReviewDTO jobCompanyReviewDTO = jobService.findJobById(id);
        if(jobCompanyReviewDTO == null){
            throw new JobException("job Is null");
        }
        return new ResponseEntity<>(jobCompanyReviewDTO,HttpStatus.OK);
    }
    @DeleteMapping("/{deleteId}")
    public ResponseEntity<String> deleteJob(@PathVariable("deleteId") Long id) throws JobException {
        boolean status= jobService.deleteJob(id);
        if(!status) {
            throw  new JobException("Job Deletion didn't happen");
        }
        return ResponseEntity.ok("Company deleted");

    }
    @PutMapping("/{jobId}")
    public  ResponseEntity<String> updateJob(@PathVariable("jobId") Long id, @RequestBody Job job) throws  JobException {
        boolean status= jobService.updateJob(id, job);
        if(status) {
            return ResponseEntity.ok("Job updated");
        }
        return ResponseEntity.ok("Job Not updated");
    }
}
