package com.abhijith.Job.Repository;

import com.abhijith.Job.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepositoryDAO extends JpaRepository<Job,Long> {
}
