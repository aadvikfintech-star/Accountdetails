package com.cooperative.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@NamedQueries({  
	@NamedQuery(name="fetchRoleList",query="select r from Role r order by roleId"),
	@NamedQuery(name="fetchActiveRoleList",query="select r from Role r where isActive = 'Y' order by roleId"),
})

@Entity
@Table(name = "tbl_role")
public class Role  {
	@Id
	@SequenceGenerator(name = "tbl_role_seq", sequenceName = "tbl_role_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_role_seq")
	@Column(name = "role_id") //db column name
	private Long roleId; //java varibale name
	
	@Column(name = "role_name", nullable = false)
	private String roleName;
	
	@Column(name = "is_active") //db column name
	private Character isActive; //java varibale name
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Character getIsActive() {
		return isActive;
	}

	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}

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

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
