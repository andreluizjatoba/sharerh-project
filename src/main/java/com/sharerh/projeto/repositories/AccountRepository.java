package com.sharerh.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharerh.projeto.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	public Account findTop1ByNumberAccount(String numberAccount);
	public Account findTop1ByRegisterId(String registerId);

}
