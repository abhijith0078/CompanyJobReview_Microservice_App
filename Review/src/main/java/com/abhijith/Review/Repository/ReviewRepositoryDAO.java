package com.abhijith.Review.Repository;

import com.abhijith.Review.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepositoryDAO extends JpaRepository<Review,Long> {
    List<Review> findByCompanyId(Long companyId);
}
