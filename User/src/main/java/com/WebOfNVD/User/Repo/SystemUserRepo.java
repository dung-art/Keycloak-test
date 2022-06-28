package com.WebOfNVD.User.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.WebOfNVD.User.Entity.SystemUser;

@Transactional
@Repository
public interface SystemUserRepo extends JpaRepository<SystemUser, String>, JpaSpecificationExecutor<SystemUser> {
	@Query(value = "Select s from SystemUsers s Where s.isDelete = false")
	List<SystemUser> findAllNoDelete();

	@Query(value = "Select s from SystemUsers s Where s.isDelete = true")
	List<SystemUser> findAllDeleted();

	@Query(value = "Select s from SystemUsers s Where s.identifyNumber = :identifyNumber and s.isDelete = false")
	Optional<SystemUser> findByIdentifyNumber(@Param("identifyNumber") String identifyNumber);
}
