package com.chillhub.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chillhub.app.config.JwtTokenUtil;
import com.chillhub.app.dao.UserDao;
import com.chillhub.app.entities.User;
import com.chillhub.app.models.JwtRequest;
import com.chillhub.app.models.JwtResponse;
import com.chillhub.app.security.UserPrincipalDetailsService;

@RestController
@CrossOrigin(origins = "*")
public class JwtAuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserPrincipalDetailsService userDetailsService;
	
	@Autowired
	private UserDao dao;
	
	@PostMapping("authentication")
	public ResponseEntity<?> createAthenticationToken(@RequestBody JwtRequest authRequest) throws Exception {
		authenticate(authRequest.getUsername(), authRequest.getPassword());
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authRequest.getUsername());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		final User user = dao.findByUsername(authRequest.getUsername());
		
		return ResponseEntity.ok(new JwtResponse(token, user));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}

