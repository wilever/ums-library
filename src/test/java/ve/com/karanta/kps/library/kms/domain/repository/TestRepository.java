package ve.com.karanta.kps.library.kms.domain.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ve.com.karanta.kps.library.kms.domain.repository.entity.TestEntity;

/**
 * TestRepository for perform test for this project.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
@Repository
public interface TestRepository extends JpaRepository<TestEntity, Serializable>, JpaSpecificationExecutor<TestEntity>{

}
