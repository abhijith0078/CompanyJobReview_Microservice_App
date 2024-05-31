package com.abhijith.Job.Clients;

import com.abhijith.Job.External.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEW-MS")
public interface ReviewClient {
    @GetMapping("/api/reviews")
    List<Review> getReviews(@RequestParam("companyId") Long companyId);
}
