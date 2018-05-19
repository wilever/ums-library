package com.uproject.library.ums.domain.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.uproject.library.ums.domain.repository.TestRepository;

/**
 * TestEntity for be use by {@link TestRepository}.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
@Entity
@Table(name="any_entity")
public class TestEntity {

	/** The id. */
	@Id
	@Column(name="id")
	private Integer id;
	
	/** The field. */
	@Column(name="field")
	private String field;
	
	/** The description field. */
	@Column(name="description_field")
	private String descriptionField;
	
	/** The date. */
	@Column(name="date")
	private Date date;
	
	/**
	 * Instantiates a new test entity.
	 */
	public TestEntity() {
	}

	/**
	 * Gets the id.
	 *
	 * @return The id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id The new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the field.
	 *
	 * @return The field
	 */
	public String getField() {
		return field;
	}

	/**
	 * Sets the field.
	 *
	 * @param field The new field
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * Gets the description field.
	 *
	 * @return The description field
	 */
	public String getDescriptionField() {
		return descriptionField;
	}

	/**
	 * Sets the description field.
	 *
	 * @param descriptionField The new description field
	 */
	public void setDescriptionField(String descriptionField) {
		this.descriptionField = descriptionField;
	}

	/**
	 * Gets the date.
	 *
	 * @return The date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date The new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
