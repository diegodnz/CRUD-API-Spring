package com.alisson.springcloudpostgre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alisson.springcloudpostgre.model.*;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {}
