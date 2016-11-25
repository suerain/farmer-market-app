package edu.mum.farmer.app.service;

import java.util.List;

import edu.mum.farmer.app.domain.Credentials;
import edu.mum.farmer.app.domain.Member;



 
public interface CredentialsService {

	public void save(Credentials credentials);
	public List<Credentials> findAll();
	public void deleteCredentials(String username);
	public Credentials updateCredentials(Member member,String  password);
 }
