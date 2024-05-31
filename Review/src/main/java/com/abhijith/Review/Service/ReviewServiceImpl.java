package com.abhijith.Review.Service;


import com.abhijith.Review.Exception.CompanyException;
import com.abhijith.Review.Model.Review;
import com.abhijith.Review.Repository.ReviewRepositoryDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepositoryDAO reviewRepositoryDAO;

    public ReviewServiceImpl(ReviewRepositoryDAO reviewRepositoryDAO){
        this.reviewRepositoryDAO = reviewRepositoryDAO;
    }
    @Override
    public List<Review> findAllReviews() {
        return reviewRepositoryDAO.findAll();
    }

    @Override
    public boolean addReview(Long companyId,Review review) {
        if(companyId != null || review != null){
            review.setCompanyId(companyId);
            reviewRepositoryDAO.save(review);
            return true;
        }
        return false;


    }

    @Override
    public Review findReviewById(Long reviewId)  {
        return reviewRepositoryDAO.findById(reviewId).orElse(null);
    }

    @Override
    public boolean deleteReview( Long reviewId) throws CompanyException {
        Review review = reviewRepositoryDAO.findById(reviewId).orElse(null);
        if (review != null){
            reviewRepositoryDAO.delete(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReview(Long reviewId, Review review) throws CompanyException {
        Review review1= reviewRepositoryDAO.findById(reviewId).orElse(null);
        if(review1 != null){
            review1.setTitle(review.getTitle());
            review1.setMessage(review.getMessage());
            review1.setCompanyId(review.getCompanyId());
            review1.setRating(review.getRating());
            reviewRepositoryDAO.save(review1);
            return true;
        }

        return  false;


    }

    @Override
    public List<Review> findReviewsByCompanyId(Long companyId) {

        return reviewRepositoryDAO.findByCompanyId(companyId);
    }
}
