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

/**
 * Classe Services definie as regras de negócio e persiste os dados chamando a interface Repository
 * 
 * @author andrejatoba
 *
 */

@Service
public class AccountService {
	
	@Autowired	
	private AccountRepository accountRepository;
	
	// Método para listar todas as contas utilizando "Page"
	@Transactional(readOnly = true)
	public Page<AccountDTO> getAll(Pageable pageable) {
		Page<Account> list = accountRepository.findAll(pageable);
		Page<AccountDTO> listDTO = list.map(obj -> new AccountDTO(obj));
		return listDTO;
	}
	
	// Método para listar todas as contas utilizando "List"
	@Transactional(readOnly = true)
	public List<AccountDTO> findAll() {
		List<Account> list = accountRepository.findAll();
		List<AccountDTO> listDTO = list.stream().map(obj -> new AccountDTO(obj)).collect(Collectors.toList());
		return listDTO;
	}
	
	// Método para buscar uma conta por ID
	@Transactional(readOnly = true)	
	public AccountDTO findById(Long id) {
		Account entity = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found "+ id));
		AccountDTO dto = new AccountDTO(entity);
		return dto;		
	}
	
	// Método para criar uma nova conta
	// Foi definido que os campos numberAccount e registerId deven ser únicos no banco de dados
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
	
	// Método para atualizar um conta existente
	@Transactional
	public Account update(AccountUpdateDTO dto, Long id) {
		try {
			
			Optional<Account> accountDTO = accountRepository.findById(id);
			Account account = accountRepository.findById(id).get();
			
			// Verifica se o campo nameOwner foi informado
			// Caso não seja informado mantém o atual
			if  ( (dto.getNameOwner() == null)  || (dto.getNameOwner().isEmpty()) || dto.getNameOwner().isBlank() )
				accountDTO.get().setNameOwner(account.getNameOwner());
			else
				accountDTO.get().setNameOwner(dto.getNameOwner());
			
			// Verifica se o campo agencyCode foi informado
			// Caso não seja informado mantém o atual						
			if ( (dto.getAgencyCode() == null) || (dto.getAgencyCode().isEmpty()) )
				accountDTO.get().setAgencyCode(account.getAgencyCode());
			else
				accountDTO.get().setAgencyCode(dto.getAgencyCode());
				
								
			// Verifica se o campo numberAccount foi informado
			// Caso não seja informado mantém o atual
			// Caso seja informado um novo valor verifica se mesmo não existe no banco de dados
			// Seguindo a regra de negócio este campo deve ser único.
			if ( (dto.getNumberAccount() == null) || (dto.getNumberAccount().isEmpty()) || (dto.getNumberAccount().equals(account.getNumberAccount())) )
				accountDTO.get().setNumberAccount(account.getNumberAccount());
			else if (accountRepository.findTop1ByNumberAccount(dto.getNumberAccount()) != null)
				throw new AccountConflictException("numberAccount - field already exists in the database");
			else		
				accountDTO.get().setNumberAccount(dto.getNumberAccount());
				
			
			// Verifica se o campo digitVerification foi informado
			// Caso não seja informado mantém o atual	
			if ( (dto.getDigitVerification() == null) || (dto.getDigitVerification().isEmpty()) )
				accountDTO.get().setDigitVerification(account.getDigitVerification());
			else
				accountDTO.get().setDigitVerification(dto.getDigitVerification());
			
			
			// Verifica se o campo registerId foi informado
			// Caso não seja informado mantém o atual
			// Caso seja informado um novo valor verifica se mesmo não existe no banco de dados
			// Seguindo a regra de negócio este campo deve ser único.									
			if ( (dto.getRegisterId() == null) || (dto.getRegisterId().isEmpty()) || (dto.getRegisterId().equals(account.getRegisterId())) )
				accountDTO.get().setRegisterId(account.getRegisterId());
			else if (accountRepository.findTop1ByRegisterId(dto.getRegisterId()) != null)
				throw new AccountConflictException("registerId - field already exists in the database");
			else		
				accountDTO.get().setRegisterId(dto.getRegisterId());
			
			// Salva as attualizações no banco de dados			
			accountRepository.save(accountDTO.get());
			return accountDTO.get();
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Id not found: "+ id);
		}catch(NoSuchElementException e) {
			throw new EntityNotFoundException("Id not found: "+ id);
		}
	}
	
	// Método para deletar uma conta
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
