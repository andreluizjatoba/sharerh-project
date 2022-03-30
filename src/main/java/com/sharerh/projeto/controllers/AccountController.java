package com.sharerh.projeto.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sharerh.projeto.dto.AccountDTO;
import com.sharerh.projeto.dto.AccountUpdateDTO;
import com.sharerh.projeto.entities.Account;
import com.sharerh.projeto.entities.AccountResponseMessage;
import com.sharerh.projeto.services.AccountService;
import com.sharerh.projeto.services.exceptions.AccountConflictException;

@RestController
@RequestMapping("v1/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/pages")
	public ResponseEntity<Page<AccountDTO>> getAll(Pageable pageable) {
		Page<AccountDTO> list = accountService.getAll(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDTO>> findAll() {
		List<AccountDTO> lis = accountService.findAll();
		return ResponseEntity.ok().body(lis);
	}
	
	@GetMapping("/{id}")
	public AccountDTO findById(@PathVariable Long id) {
		return accountService.findById(id);
	}
	
	@PostMapping
    public ResponseEntity<AccountDTO> create(@Valid @RequestBody AccountDTO dto) {
    	if (dto.getId() != null)
    		throw new AccountConflictException("Do not enter the ID field - automatically generated");
    	else {
    		Account entity = accountService.create(dto);
    		AccountDTO accountDTO = new AccountDTO(entity);
    		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(accountDTO.getId()).toUri();
    		return ResponseEntity.created(location).body(accountDTO);
    	}
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<AccountUpdateDTO> update(@Valid @RequestBody AccountUpdateDTO dto, @PathVariable Long id) {
		if (dto.getId() != null)
    		throw new AccountConflictException("Do not enter the ID field - automatically generated");
		else {
			Account entity = accountService.update(dto, id);
			AccountUpdateDTO accountUpdateDTO = new AccountUpdateDTO(entity);
			//account = accountService.update(account, id);
			//return ResponseEntity.noContent().build();
			return ResponseEntity.ok().body(accountUpdateDTO);
		}
	}
    
	@DeleteMapping("/{id}")
	public ResponseEntity<AccountResponseMessage> delete(@PathVariable Long id) {
		accountService.delete(id);
		AccountResponseMessage response = new AccountResponseMessage();
		response.setMessage("Account id deleted: "+ id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
