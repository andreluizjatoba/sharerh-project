package com.sharerh.projeto.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharerh.projeto.dto.AccountDTO;
import com.sharerh.projeto.dto.AccountUpdateDTO;
import com.sharerh.projeto.entities.Account;
import com.sharerh.projeto.repositories.AccountRepository;
import com.sharerh.projeto.services.exceptions.AccountConflictException;
import com.sharerh.projeto.services.exceptions.IntegrityViolatedException;

@Service
public class AccountService {
	
	@Autowired	
	private AccountRepository accountRepository;
	
	@Transactional(readOnly = true)
	public Page<AccountDTO> getAll(Pageable pageable) {
		Page<Account> list = accountRepository.findAll(pageable);
		Page<AccountDTO> listDTO = list.map(obj -> new AccountDTO(obj));
		return listDTO;
	}
		
	@Transactional(readOnly = true)
	public List<AccountDTO> findAll() {
		List<Account> list = accountRepository.findAll();
		List<AccountDTO> listDTO = list.stream().map(obj -> new AccountDTO(obj)).collect(Collectors.toList());
		return listDTO;
	}
	
	
	@Transactional(readOnly = true)	
	public AccountDTO findById(Long id) {
		Account entity = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found "+ id));
		AccountDTO dto = new AccountDTO(entity);
		return dto;		
	}
	
	@Transactional
	public Account create(AccountDTO dto) {
		if (accountRepository.findTop1ByNumberAccount(dto.getNumberAccount()) != null)
			throw new AccountConflictException("numberAccount - field already exists in the database");
		else if (accountRepository.findTop1ByRegisterId(dto.getRegisterId()) != null)
			throw new AccountConflictException("registerId - field already exists in the database");
		else {
			return accountRepository.save(dto.transformIntoEntity());
			
		}
		
	}
	
	@Transactional
	public Account update(AccountUpdateDTO dto, Long id) {
		try {
			
			Optional<Account> accountDTO = accountRepository.findById(id);
			
			Account account = accountRepository.findById(id).get();
			
			if  ( (dto.getNameOwner() == null)  || (dto.getNameOwner().isEmpty()) || dto.getNameOwner().isBlank() )
				accountDTO.get().setNameOwner(account.getNameOwner());
			else
				accountDTO.get().setNameOwner(dto.getNameOwner());
				
						
			if ( (dto.getAgencyCode() == null) || (dto.getAgencyCode().isEmpty()) )
				accountDTO.get().setAgencyCode(account.getAgencyCode());
			else
				accountDTO.get().setAgencyCode(dto.getAgencyCode());
				
								
			
			if ( (dto.getNumberAccount() == null) || (dto.getNumberAccount().isEmpty()) || (dto.getNumberAccount().equals(account.getNumberAccount())) )
				accountDTO.get().setNumberAccount(account.getNumberAccount());
			else if (accountRepository.findTop1ByNumberAccount(dto.getNumberAccount()) != null)
				throw new AccountConflictException("numberAccount - field already exists in the database");
			else		
				accountDTO.get().setNumberAccount(dto.getNumberAccount());
				
			
			
			if ( (dto.getDigitVerification() == null) || (dto.getDigitVerification().isEmpty()) )
				accountDTO.get().setDigitVerification(account.getDigitVerification());
			else
				accountDTO.get().setDigitVerification(dto.getDigitVerification());
				
									
			if ( (dto.getRegisterId() == null) || (dto.getRegisterId().isEmpty()) || (dto.getRegisterId().equals(account.getRegisterId())) )
				accountDTO.get().setRegisterId(account.getRegisterId());
			else if (accountRepository.findTop1ByRegisterId(dto.getRegisterId()) != null)
				throw new AccountConflictException("registerId - field already exists in the database");
			else		
				accountDTO.get().setRegisterId(dto.getRegisterId());
			
			
			accountRepository.save(accountDTO.get());
			return accountDTO.get();
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Id not found: "+ id);
		}catch(NoSuchElementException e) {
			throw new EntityNotFoundException("Id not found: "+ id);
		}
	}
	
	@Transactional	
	public void delete(Long id) {
		try {
			accountRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Id not found: "+ id);
		}catch(DataIntegrityViolationException e) {
			throw new IntegrityViolatedException("Integrity violated!");
		}
	}
	
}
