package com.uproject.library.ums.domain.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.uproject.library.ums.domain.repository.entity.TestEntity;

/**
 * TestRepository for perform test for this project.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
@Repository
public interface TestRepository extends JpaRepository<TestEntity, Serializable>, JpaSpecificationExecutor<TestEntity>{

}
