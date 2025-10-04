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
	@NamedQuery(name = "fetchAllWalletByCooprative", query = "select c from Wallet c where c.isActive ='Y' order by walletId"), 
	@NamedQuery(name = "fetchWalletByCooprative", query = "select c from Wallet c where c.copId.copId =:copId"), 
	})
@Entity
@Table(name = "tbl_wallet")
public class Wallet {
	@Id
	@SequenceGenerator(name = "tbl_wallet_seq", sequenceName = "tbl_wallet_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_wallet_seq")
	@Column(name = "wallet_id")
	private Long walletId;

	@Column(name = "amount", nullable = false)
	private Double amount;

	@Column(name = "balanced_amount", nullable = false)
	private Double balancedAmount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cop_id", referencedColumnName = "cop_id")
	private Cooperative copId;

	@Column(name = "is_active")
	private Character isActive;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getBalancedAmount() {
		return balancedAmount;
	}

	public void setBalancedAmount(Double balancedAmount) {
		this.balancedAmount = balancedAmount;
	}

	public Cooperative getCopId() {
		return copId;
	}

	public void setCopId(Cooperative copId) {
		this.copId = copId;
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
