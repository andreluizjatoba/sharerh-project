package com.sharerh.projeto.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sharerh.projeto.entities.Account;

/**
 * Classe DTO específica para o método de update, recebe e retorna os dados para o Controle Rest
 * 
 * @author andrejatoba
 *
 */

public class AccountUpdateDTO {
	
	private Long id;
	
	@Size(max = 50, message = "{size.nameOwner}")
	private String nameOwner;
	
	@Size(max = 4, message = "{size.agencyCode}") @Pattern(regexp = "[0-9]*", message = "{field.is.numeric}")
	private String agencyCode;
	
	@Size(max = 8, message = "{size.numberAccount}") @Pattern(regexp = "[0-9]*", message = "{field.is.numeric}")
	private String numberAccount;
	
	@Size(max = 3, message = "{size.digitVerification}") @Pattern(regexp = "[0-9]*", message = "{field.is.numeric}")
	private String digitVerification;
	
	@Size(max = 20, message = "{size.registerId}")
	private String registerId;
	
	// Constructors
	public AccountUpdateDTO() {
		
	}
	
	public AccountUpdateDTO(
			Long id,
			
			@NotBlank(message = "{field.not.blank}") @NotEmpty(message = "{field.not.empty}")
			@Size(max = 50, message = "{size.nameOwner}")
			String nameOwner,
			
			@NotBlank(message = "{field.not.blank}") @NotEmpty(message = "{field.not.empty}")
			@Size(max = 4, message = "{size.agencyCode}") @Pattern(regexp = "[0-9]*", message = "{field.is.numeric}")
			String agencyCode,
			
			@NotBlank(message = "{field.not.blank}") @NotEmpty(message = "{field.not.empty}")
			@Size(max = 8, message = "{size.numberAccount}") @Pattern(regexp = "[0-9]*", message = "{field.is.numeric}")
			String numberAccount,
			
			@NotBlank(message = "{field.not.blank}") @NotEmpty(message = "{field.not.empty}")
			@Size(max = 3, message = "{size.digitVerification}") @Pattern(regexp = "[0-9]*", message = "{field.is.numeric}")
			String digitVerification,
			
			@NotBlank(message = "{field.not.blank}") @NotEmpty(message = "{field.not.empty}")
			@Size(max = 20, message = "{size.registerId}") @Pattern(regexp = "[0-9]*", message = "{field.is.numeric}")
			String registerId) {
		
		this.id = id;
		this.nameOwner = nameOwner;
		this.agencyCode = agencyCode;
		this.numberAccount = numberAccount;
		this.digitVerification = digitVerification;
		this.registerId = registerId;
	}

	public AccountUpdateDTO(Account account) {
		id = account.getId();
		nameOwner = account.getNameOwner();
		agencyCode = account.getAgencyCode();
		numberAccount = account.getNumberAccount();
		digitVerification = account.getDigitVerification();
		registerId = account.getRegisterId();
	}
	
	// Método para converter os dados para a Classe Entity Account
	public Account transformIntoEntity() {
		return new Account(nameOwner, agencyCode, numberAccount, digitVerification, registerId);
	}
	
	//Getters and Setters
	// id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	// nameOwner
	public String getNameOwner() {
		return nameOwner;
	}
	public void setNameOwner(String nameOwner) {
		this.nameOwner = nameOwner;
	}

	// agencyCode
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	// numberAccount
	public String getNumberAccount() {
		return numberAccount;
	}
	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}

	// digitalVerification
	public String getDigitVerification() {
		return digitVerification;
	}
	public void setDigitVerification(String digitVerification) {
		this.digitVerification = digitVerification;
	}

	// registerId
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

}
