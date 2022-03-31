package com.sharerh.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharerh.projeto.entities.Account;

/**
 * Interface para executar as operações de banco de dados (abstração)
 * 
 * @author andrejatoba
 *
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	// Métodos para buscar as informações de numberAccount e registerID
	public Account findTop1ByNumberAccount(String numberAccount);
	public Account findTop1ByRegisterId(String registerId);

}
