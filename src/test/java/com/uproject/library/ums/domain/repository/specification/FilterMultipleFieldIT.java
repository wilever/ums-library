package com.uproject.library.ums.domain.repository.specification;

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

import com.uproject.library.ums.domain.repository.TestRepository;
import com.uproject.library.ums.domain.repository.entity.TestEntity;
import com.uproject.library.ums.domain.repository.specification.USpecification;
import com.uproject.library.ums.domain.repository.specification.USpecificationOperator;

/**
 * Test filter by multiple fields.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class FilterMultipleFieldIT {
	
	/** The repository. */
	@Autowired
	private TestRepository repository;
	
	/** The any entity. */
	private TestEntity anyEntity = new TestEntity();
	
	/** The another entity. */
	private TestEntity anotherEntity = new TestEntity();
	
	/** The same field. */
	private TestEntity sameField = new TestEntity();
	
	/** The filter. */
	private String filter;
	
	/** The search. */
	private String search;
	
	/** The actual list. */
	private List<TestEntity> actualList = new ArrayList<>();
	
	/**
	 * Sets the entity.
	 *
	 * @param anyEntity the any entity
	 * @param id the id
	 * @param field the field
	 * @param descriptionField the description field
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
	 * @return the all init data
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
		filter="field"+USpecificationOperator.NEGATION.operator+anyEntity.getField();
		actualList = repository.findAll(USpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(1);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(anotherEntity);
	}
	
	/**
	 * Two fields.
	 */
	@Test
	public void TwoFields() {
		filter="field"+USpecificationOperator.EQUALITY.operator+anyEntity.getField()+USpecificationOperator.SEPARATOR
				+"descriptionField"+USpecificationOperator.NEGATION.operator+anyEntity.getDescriptionField();
		actualList = repository.findAll(USpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(1);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(sameField);
	}
	
	/**
	 * Three fields.
	 */
	@Test
	public void ThreeFields() {
		filter="field"+USpecificationOperator.EQUALITY.operator+anyEntity.getField()+USpecificationOperator.SEPARATOR
				+"descriptionField"+USpecificationOperator.EQUALITY.operator+sameField.getDescriptionField()+USpecificationOperator.SEPARATOR
				+"id"+USpecificationOperator.GREATER_THAN.operator+anyEntity.getId();
		actualList = repository.findAll(USpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(1);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(sameField);
	}
	
	/**
	 * One field fail.
	 */
	@Test(expected=InvalidDataAccessApiUsageException.class)
	public void OneFieldFail() {
		filter="field"+USpecificationOperator.EQUALITY.operator+anyEntity.getField()+USpecificationOperator.SEPARATOR
				+"descriptionField"+USpecificationOperator.EQUALITY.operator+sameField.getDescriptionField()+USpecificationOperator.SEPARATOR
				+"id"+USpecificationOperator.GREATER_THAN.operator+"abc";
		repository.findAll(USpecification.getSpecifications(search, filter));
	}
}
