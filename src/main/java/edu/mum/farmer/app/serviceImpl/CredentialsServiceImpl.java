package edu.mum.farmer.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.farmer.app.domain.Credentials;
import edu.mum.farmer.app.domain.Member;
import edu.mum.farmer.app.repository.CredentialsRepository;
import edu.mum.farmer.app.service.CredentialsService;

@Service
@Transactional
public class CredentialsServiceImpl implements CredentialsService {

	@Autowired
	private CredentialsRepository credentialsRepository;

	public void save(Credentials credentials) {

		/*
		 * PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); String
		 * encodedPassword = passwordEncoder.encode(credentials.getPassword());
		 * credentials.setPassword(encodedPassword);
		 */

		credentialsRepository.save(credentials);
	}

	public List<Credentials> findAll() {
		return (List<Credentials>) credentialsRepository.findAll();
	}

	@Override
	public void deleteCredentials(String username) {
		// TODO Auto-generated method stub
		credentialsRepository.delete(username);
		// credentialsRepository.findAll();

	}

	@Override
	public Credentials updateCredentials(Member member, String password) {
		Credentials credentials = member.getCredentials();
		credentials.setPassword(password);
		Credentials newCredentials = credentialsRepository.save(credentials);
		return newCredentials;
	}

}
