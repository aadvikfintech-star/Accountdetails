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
		@NamedQuery(name = "fetchWalletHistoryByWallet", query = "select w from WalletHistory w where w.walletId.walletId=:walletId and w.isActive ='Y'"),
		@NamedQuery(name = "fetchWalletHistoryByCooprative", query = "select w from WalletHistory w where w.walletId.copId.copId =:copId"),})
@Entity
@Table(name = "tbl_wallet_history")
public class WalletHistory {
	@Id
	@SequenceGenerator(name = "wallet_history_seq", sequenceName = "wallet_history_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_history_seq")
	@Column(name = "id")
	private Long id;

	@Column(name = "amount", nullable = false)
	private Double amount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "wallet_id", referencedColumnName = "wallet_id")
	private Wallet walletId;

	@Column(name = "payment_mode")
	private String paymentMode;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Wallet getWalletId() {
		return walletId;
	}

	public void setWalletId(Wallet walletId) {
		this.walletId = walletId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
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
