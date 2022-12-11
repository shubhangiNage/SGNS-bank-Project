package com.cjc.homeloanapplication.app.repositoryEnq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.homeloanapplication.app.model.EnquiryDetails;

@Repository
public interface EnqRepository extends JpaRepository<EnquiryDetails, Integer> {

}
