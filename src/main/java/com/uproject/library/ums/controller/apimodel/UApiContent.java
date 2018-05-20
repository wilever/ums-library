package com.uproject.library.ums.controller.apimodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model of content with system fields.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 * 
 */
@ApiModel(value="Content structure")
public class UApiContent {
	
	/** Unique identifier for data. */
	@ApiModelProperty(
			value="Unique identifier for data",
			example="0",
			required=true)
	@Id
	@Column(name="any_id")
	private final Integer identifier;
	
	/** The active ind. It can be 'Y' or 'N'.*/
	@ApiModelProperty(
			value="A Y or N flag indicating "
					+ "whether this row of data is currently "
					+ "either active/valid (Y) or inactive/invalid (N)",
			example="Y")
	@Column(name="active_ind")
	private final String activeInd;

	/** The effective date. */
	@ApiModelProperty(
			value="The date that the data in "
			+ "this row first came into effect",
			example="2001-01-01")
	@Temporal(TemporalType.DATE)
	@Column(name="effective_date")
	private final Date effectiveDate;

	/** The expiry date. */
	@ApiModelProperty(
			value="The date that the data in " +
			"this row was no longer active or in effect",
			example="null")
	@Temporal(TemporalType.DATE)
	@Column(name="expiry_date")
	private final Date expiryDate;

	/** The ppdm guid. */
	@ApiModelProperty(
			value="This value may be used to provide "
					+ "a global unique identifier for "
					+ "this row of data",
			example="0")
	@Column(name="ppdm_guid")
	private final String ppdmGuid;

	/** The remark. */
	@ApiModelProperty(
			value="Narrative remarks about this row of data.",
			example="This is a remark")
	@Column(name="remark")
	private final String remark;

	/** The row changed by. */
	@ApiModelProperty(
			value="Application login id of the user"
					+ " who last changed the row",
			example="UPDATED_USER")
	@Column(name="row_changed_by")
	private final String rowChangedBy;

	/** The row changed date. */
	@ApiModelProperty(
			value="System date of the last time "
					+ "the row was changed",
			example="2001-01-01")
	@Temporal(TemporalType.DATE)
	@Column(name="row_changed_date")
	private final Date rowChangedDate;

	/** The row created by. */
	@ApiModelProperty(
			value="System user who "
				+ "created this row of data",
			example="CREATED_USER")
	@Column(name="row_created_by")
	private final String rowCreatedBy;

	/** The row created date. */
	@ApiModelProperty(
			value="Date that the row was created on",
			example="2000-01-01")
	@Temporal(TemporalType.DATE)
	@Column(name="row_created_date")
	private final Date rowCreatedDate;

	/** The row effective date. */
	@ApiModelProperty(
			value=	"A system assigned date " +
					"that specified when a row of data is effective " +
					"from a systems perspective. This may be the " +
					"date that a row of data was made available to " +
					"end users, for example",
			example="2001-01-01")
	@Temporal(TemporalType.DATE)
	@Column(name="row_effective_date")
	private final Date rowEffectiveDate;

	/** The row expiry date. */
	@ApiModelProperty(
			value=	"A system assigned date that " +
					"specified when a row of data is no longer " +
					"effective from a systems perspective. This " +
					"may be the date that a row of data was no " +
					"longer available to end users, for example",
			example="null")
	@Temporal(TemporalType.DATE)
	@Column(name="row_expiry_date")
	private final Date rowExpiryDate;

	/** The row quality. */
	@ApiModelProperty(
			value=	"A set of values indicating " +
					"the quality of data in this row, usually with " +
					"reference to the method or procedures used to " +
					"load the data, although other types of quality " +
					"reference are permitted",
			example="REAL")
	@Column(name="row_quality")
	private final String rowQuality;

	/** The source. */
	@ApiModelProperty(
			value=	"The individual, company, state, or " +
					"government agency designated as the source " +
					"of information for this row",
			example="PPDM_SAMPLE")
	@Column(name="source")
	private final String source;

	/**
	 * Instantiates a new KA content.
	 *
	 * @param identifier the identifier
	 * @param activeInd the active ind
	 * @param effectiveDate the effective date
	 * @param expiryDate the expiry date
	 * @param ppdmGuid the ppdm guid
	 * @param remark the remark
	 * @param rowChangedBy the row changed by
	 * @param rowChangedDate the row changed date
	 * @param rowCreatedBy the row created by
	 * @param rowCreatedDate the row created date
	 * @param rowEffectiveDate the row effective date
	 * @param rowExpiryDate the row expiry date
	 * @param rowQuality the row quality
	 * @param source the source
	 */
	public UApiContent(Integer identifier, String activeInd, Date effectiveDate, Date expiryDate, String ppdmGuid,
			String remark, String rowChangedBy, Date rowChangedDate, String rowCreatedBy, Date rowCreatedDate,
			Date rowEffectiveDate, Date rowExpiryDate, String rowQuality, String source) {
		this.identifier = identifier;
		this.activeInd = activeInd;
		this.effectiveDate = effectiveDate;
		this.expiryDate = expiryDate;
		this.ppdmGuid = ppdmGuid;
		this.remark = remark;
		this.rowChangedBy = rowChangedBy;
		this.rowChangedDate = rowChangedDate;
		this.rowCreatedBy = rowCreatedBy;
		this.rowCreatedDate = rowCreatedDate;
		this.rowEffectiveDate = rowEffectiveDate;
		this.rowExpiryDate = rowExpiryDate;
		this.rowQuality = rowQuality;
		this.source = source;
	}

	/**
	 * Gets the identifier.
	 *
	 * @return the identifier
	 */
	public Integer getIdentifier() {
		return identifier;
	}

	/**
	 * Gets the active ind.
	 *
	 * @return the active ind
	 */
	public String getActiveInd() {
		return activeInd;
	}

	/**
	 * Gets the effective date.
	 *
	 * @return the effective date
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * Gets the expiry date.
	 *
	 * @return the expiry date
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Gets the ppdm guid.
	 *
	 * @return the ppdm guid
	 */
	public String getPpdmGuid() {
		return ppdmGuid;
	}

	/**
	 * Gets the remark.
	 *
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Gets the row changed by.
	 *
	 * @return the row changed by
	 */
	public String getRowChangedBy() {
		return rowChangedBy;
	}

	/**
	 * Gets the row changed date.
	 *
	 * @return the row changed date
	 */
	public Date getRowChangedDate() {
		return rowChangedDate;
	}

	/**
	 * Gets the row created by.
	 *
	 * @return the row created by
	 */
	public String getRowCreatedBy() {
		return rowCreatedBy;
	}

	/**
	 * Gets the row created date.
	 *
	 * @return the row created date
	 */
	public Date getRowCreatedDate() {
		return rowCreatedDate;
	}

	/**
	 * Gets the row effective date.
	 *
	 * @return the row effective date
	 */
	public Date getRowEffectiveDate() {
		return rowEffectiveDate;
	}

	/**
	 * Gets the row expiry date.
	 *
	 * @return the row expiry date
	 */
	public Date getRowExpiryDate() {
		return rowExpiryDate;
	}

	/**
	 * Gets the row quality.
	 *
	 * @return the row quality
	 */
	public String getRowQuality() {
		return rowQuality;
	}

	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public String getSource() {
		return source;
	}	
}
