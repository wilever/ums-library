package ve.com.karanta.kps.library.kms.domain.repository.specification;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import ve.com.karanta.kps.library.kms.domain.repository.TestRepository;
import ve.com.karanta.kps.library.kms.domain.repository.entity.TestEntity;
import ve.com.karanta.kps.library.kms.domain.repository.specification.KSpecification;

/**
 * Test search by multiple fields.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SearchMultipleFieldIT {
	
	/** The repository. */
	@Autowired
	private TestRepository repository;
	
	/** The any entity. */
	private TestEntity anyEntity = new TestEntity();
	
	/** The another entity. */
	private TestEntity anotherEntity = new TestEntity();
	
	/** The same field. */
	private TestEntity sameField = new TestEntity();
	
	/** The search. */
	private String search;
	
	/** The filter. */
	private String filter;
	
	/** The actual list. */
	private List<TestEntity> actualList = new ArrayList<>();
	
	/**
	 * Sets the entity.
	 *
	 * @param anyEntity The any entity
	 * @param id The id
	 * @param field The field
	 * @param descriptionField The description field
	 */
	public void setEntity(
			TestEntity anyEntity, 
			Integer id,
			String field,
			String descriptionField) {
		anyEntity.setId(id);
		anyEntity.setField(field);
		anyEntity.setDescriptionField(descriptionField);
		repository.save(anyEntity);
	}
	
	/**
	 * Inits the data.
	 */
	@Before
	public void initData() {
		setEntity(anyEntity, 0, "anyField", "anyDescriptionField");
		setEntity(anotherEntity, 1, "anotherField", "anotherDescriptionField");
		setEntity(sameField, 2, anyEntity.getField(), anotherEntity.getField());
		actualList.clear();
		}
	
	/**
	 * Gets the all init data.
	 *
	 * @return The all init data
	 */
	@Test
	public void getAll_InitData() {
		actualList = repository.findAll();
		assertThat(actualList).hasSize(3);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(anyEntity);
		assertThat(actualList.get(1)).isEqualToComparingFieldByFieldRecursively(anotherEntity);
		assertThat(actualList.get(2)).isEqualToComparingFieldByFieldRecursively(sameField);
	}
	
	/**
	 * One field.
	 */
	@Test
	public void OneField() {
		search="field"+KSpecificationOperator.NEGATION.operator+anyEntity.getField();
		actualList = repository.findAll(KSpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(1);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(anotherEntity);
	}
	
	/**
	 * Two fields.
	 */
	@Test
	public void TwoFields() {
		search="field"+KSpecificationOperator.EQUALITY.operator+"any*"+KSpecificationOperator.SEPARATOR
				+"id"+KSpecificationOperator.GREATER_THAN.operator+anotherEntity.getId();
		actualList = repository.findAll(KSpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(2);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(anyEntity);
		assertThat(actualList.get(1)).isEqualToComparingFieldByFieldRecursively(sameField);	
	}
	
	/**
	 * Three fields.
	 */
	@Test
	public void ThreeFields() {
		search="field"+KSpecificationOperator.EQUALITY.operator+"any*"+KSpecificationOperator.SEPARATOR
				+"descriptionField"+KSpecificationOperator.NEGATION.operator+anyEntity.getDescriptionField()+KSpecificationOperator.SEPARATOR
				+"id"+KSpecificationOperator.GREATER_THAN.operator+anotherEntity.getId();
		actualList = repository.findAll(KSpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(3);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(anyEntity);
		assertThat(actualList.get(1)).isEqualToComparingFieldByFieldRecursively(anotherEntity);
		assertThat(actualList.get(2)).isEqualToComparingFieldByFieldRecursively(sameField);
	}
	
	/**
	 * One field fail.
	 */
	@Test(expected=InvalidDataAccessApiUsageException.class)
	public void OneFieldFail() {
		search="field"+KSpecificationOperator.EQUALITY.operator+"any*"+KSpecificationOperator.SEPARATOR
				+"descriptionField"+KSpecificationOperator.NEGATION.operator+anyEntity.getDescriptionField()+KSpecificationOperator.SEPARATOR
				+"id"+KSpecificationOperator.GREATER_THAN.operator+"abc";
		repository.findAll(KSpecification.getSpecifications(search, filter));
	}
}
