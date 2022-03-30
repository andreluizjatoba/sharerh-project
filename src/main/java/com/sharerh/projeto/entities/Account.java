package com.sharerh.projeto.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ACCOUNT")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME_OWNER", nullable = false, length = 50)
	private String nameOwner;
	
	@Column(name = "AGENCY_CODE", nullable = false, length = 4)
	private String agencyCode;
	
	@Column(name = "NUMBER_ACCOUNT", nullable = false, length = 8, unique = true)
	private String numberAccount;
	
	@Column(name = "DIGIT_VERIFICATION", nullable = false, length = 3)
	private String digitVerification;
	
	@Column(name = "REGISTER_ID", nullable = false, length = 20, unique = true)
	private String registerId;
	
	//Contructors
	public Account() {
		
	}
	
	public Account(String nameOwner, String agencyCode, String numberAccount, String digitVerification, String registerId) {
		this.nameOwner = nameOwner;
		this.agencyCode = agencyCode;
		this.numberAccount = numberAccount;
		this.digitVerification = digitVerification;
		this.registerId = registerId;
	}

	//Getters and Setters
	public Long getId() {
		return id;
	}
	
	public String getNameOwner() {
		return nameOwner;
	}
	public void setNameOwner(String nameOwner) {
		this.nameOwner = nameOwner;
	}

	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getNumberAccount() {
		return numberAccount;
	}
	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}

	public String getDigitVerification() {
		return digitVerification;
	}
	public void setDigitVerification(String digitVerification) {
		this.digitVerification = digitVerification;
	}

	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Account other = (Account) obj;
		return Objects.equals(id, other.id);
	}

}
