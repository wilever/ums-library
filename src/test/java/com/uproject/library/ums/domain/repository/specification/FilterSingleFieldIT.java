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
 * Test filter by single field.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class FilterSingleFieldIT {
	
	/** The repository. */
	@Autowired
	private TestRepository repository;
	
	/** The any entity. */
	private TestEntity anyEntity = new TestEntity();
	
	/** The same entity. */
	private TestEntity sameEntity = new TestEntity();
	
	/** The another entity. */
	private TestEntity anotherEntity = new TestEntity();
	
	/** The another entity unactive. */
	private TestEntity anotherEntityUnactive = new TestEntity();
	
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
		setEntity(sameEntity, 1, anyEntity.getField(), anyEntity.getDescriptionField());
		setEntity(anotherEntity, 2, "anotherField", "anotherDescriptionField");
		setEntity(anotherEntityUnactive, 3, anotherEntity.getField(), anotherEntity.getDescriptionField());
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
		assertThat(actualList).hasSize(4);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(anyEntity);
		assertThat(actualList.get(1)).isEqualToComparingFieldByFieldRecursively(sameEntity);
		assertThat(actualList.get(2)).isEqualToComparingFieldByFieldRecursively(anotherEntity);
		assertThat(actualList.get(3)).isEqualToComparingFieldByFieldRecursively(anotherEntityUnactive);
	}
	
	/**
	 * Filter by unknown field.
	 */
	@Test(expected=InvalidDataAccessApiUsageException.class)//(expected=IllegalArgumentException.class)
	public void FilterByUnknownField() {
		filter="unknownField"+USpecificationOperator.EQUALITY.operator+anyEntity.getField();
		repository.findAll(USpecification.getSpecifications(search, filter));
	}
	
	/**
	 * Filter by null or find all.
	 */
	@Test
	public void filterByNullOrFindAll() {
		filter=null;
		List<TestEntity> nullList = repository.findAll(USpecification
				.getSpecifications(search, filter));
		List<TestEntity> allList = repository.findAll();
		assertThat(nullList).isEqualTo(allList);
	}
	
	/**
	 * Filter by equality.
	 */
	@Test
	public void filterByEquality() {
		filter="field"+USpecificationOperator.EQUALITY.operator+anyEntity.getField();
		actualList = repository.findAll(USpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(2);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(anyEntity);
		assertThat(actualList.get(1)).isEqualToComparingFieldByFieldRecursively(sameEntity);
	}
	
	/**
	 * Filter by negation.
	 */
	@Test
	public void filterByNegation() {
		filter="field"+USpecificationOperator.NEGATION.operator+anyEntity.getField();
		actualList = repository.findAll(USpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(2);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(anotherEntity);
		assertThat(actualList.get(1)).isEqualToComparingFieldByFieldRecursively(anotherEntityUnactive);
	}
	
	/**
	 * Filter by less than.
	 */
	@Test
	public void filterByLessThan() {
		filter="id"+USpecificationOperator.LESS_THAN.operator+sameEntity.getId().toString();
		actualList = repository.findAll(USpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(1);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(anyEntity);
	}
	
	/**
	 * Filter by greater than.
	 */
	@Test
	public void filterByGreaterThan() {
		filter="id"+USpecificationOperator.GREATER_THAN.operator+anyEntity.getId().toString();
		actualList = repository.findAll(USpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(3);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(sameEntity);
		assertThat(actualList.get(1)).isEqualToComparingFieldByFieldRecursively(anotherEntity);
		assertThat(actualList.get(2)).isEqualToComparingFieldByFieldRecursively(anotherEntityUnactive);
	}
	
	/**
	 * Filter by start with.
	 */
	@Test
	public void filterByStartWith() {
		filter="descriptionField"+USpecificationOperator.EQUALITY.operator+"any*";
		actualList = repository.findAll(USpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(2);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(anyEntity);
		assertThat(actualList.get(1)).isEqualToComparingFieldByFieldRecursively(sameEntity);
	}
	
	/**
	 * Filter by end with.
	 */
	@Test
	public void filterByEndWith() {
		filter="descriptionField"+USpecificationOperator.EQUALITY.operator+"*Field";
		actualList = repository.findAll(USpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(4);
	}
	
	/**
	 * Filter by contain.
	 */
	@Test
	public void filterByContain() {
		filter="descriptionField"+USpecificationOperator.EQUALITY.operator+"*nyDe*";
		actualList = repository.findAll(USpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(2);
		assertThat(actualList.get(0)).isEqualToComparingFieldByFieldRecursively(anyEntity);
		assertThat(actualList.get(1)).isEqualToComparingFieldByFieldRecursively(sameEntity);
	}
	
	/**
	 * No field.
	 */
	@Test(expected=InvalidDataAccessApiUsageException.class)
	public void NoField() {
		filter="fielddddd"+USpecificationOperator.EQUALITY.operator+"field";
		actualList = repository.findAll(USpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(4);
	}
	
	/**
	 * Value not compatible.
	 */
	@Test
	public void ValueNotCompatible() {
		filter="fielddddd"+USpecificationOperator.EQUALITY.operator+"????";
		actualList = repository.findAll(USpecification.getSpecifications(search, filter));
		assertThat(actualList).hasSize(4);
	}
}
