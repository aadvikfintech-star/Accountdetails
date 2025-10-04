package com.cooperative.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({ 
	@NamedQuery(name = "fetchDocumentsByCooprative", query = "select c from Document c where c.copId =:copId"),
})
@Entity
@Table(name = "tbl_document")
public class Document {
	@Id
	@SequenceGenerator(name = "tbl_document_seq", sequenceName = "tbl_document_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_document_seq")
	@Column(name = "doc_id")
	private Long docId;

	@Column(name = "doc_number", nullable = false)
	private String docNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "doc_type_id", referencedColumnName = "id")
	private SystemConfiguration docTypeId;

	@Column(name = "api_response")
	private String apiResponse;

	@Column(name = "api_status")
	private String apiStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cop_id", referencedColumnName = "cop_id")
	private Cooperative copId;

//	@Column(name = "is_active")
//	private Character isActive;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_date")
	private Date createdDate;

//	@Column(name = "updated_by")
//	private Long updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public SystemConfiguration getDocTypeId() {
		return docTypeId;
	}

	public void setDocTypeId(SystemConfiguration docTypeId) {
		this.docTypeId = docTypeId;
	}

	public String getApiResponse() {
		return apiResponse;
	}

	public void setApiResponse(String apiResponse) {
		this.apiResponse = apiResponse;
	}

	public Cooperative getCopId() {
		return copId;
	}

	public void setCopId(Cooperative copId) {
		this.copId = copId;
	}

//	public Character getIsActive() {
//		return isActive;
//	}
//
//	public void setIsActive(Character isActive) {
//		this.isActive = isActive;
//	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

//	public Long getUpdatedBy() {
//		return updatedBy;
//	}
//
//	public void setUpdatedBy(Long updatedBy) {
//		this.updatedBy = updatedBy;
//	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getApiStatus() {
		return apiStatus;
	}

	public void setApiStatus(String apiStatus) {
		this.apiStatus = apiStatus;
	}

}
