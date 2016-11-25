package edu.mum.farmer.app.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.farmer.app.domain.Credentials;





	@Repository
	public interface CredentialsRepository extends  CrudRepository<Credentials, String> 
	{
  	}

