package com.abhijith.Review.Service;


import com.abhijith.Review.Exception.CompanyException;
import com.abhijith.Review.Exception.ReviewException;
import com.abhijith.Review.Model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReviews();
    boolean addReview(Long companyId,Review review);

    Review findReviewById( Long reviewId) throws ReviewException, CompanyException;

    boolean deleteReview(Long reviewId) throws ReviewException, CompanyException;

    boolean updateReview(Long reviewId, Review review) throws ReviewException, CompanyException;

    List<Review> findReviewsByCompanyId(Long companyId);
}
