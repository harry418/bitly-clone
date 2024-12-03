package com.project.URLSortner.controllers;

import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.URLSortner.entity.User;
import com.project.URLSortner.repository.UserRepo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepo userRepo;
	
	@Value("${jwt.secret}")
	private String secret;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest,HttpServletRequest request) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			// Generate SecretKey from the string
//			Key key = Keys.hmacShaKeyFor(secret.getBytes());
//
//
//			String token = Jwts.builder().setSubject(loginRequest.getUserName()).signWith(key, SignatureAlgorithm.HS512)
//					.compact();
			HttpSession session = request.getSession(true);
	        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

			return ResponseEntity.ok("Login successful!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user,HttpServletRequest request) {
		User existingUser = userRepo.findByUserName(user.getUserName());
		//String token;
		if (existingUser != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already existed");
		} else {
			user.setPassword(encoder.encode(user.getPassword()));
			userRepo.save(user);
			try {
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				// Generate SecretKey from the string
//				Key key = Keys.hmacShaKeyFor(secret.getBytes());
//
//				token = Jwts.builder().setSubject(user.getUserName()).signWith(key, SignatureAlgorithm.HS512).compact();
				
				HttpSession session = request.getSession(true); // Create a new session if it doesn't exist
		        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
		        
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Registration successful, but authentication failed.");
			}
			return ResponseEntity.ok("User Registration Sucessfull");
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logOut() {
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok("Logout successful!");
	}

	public static class LoginRequest {
		private String userName;
		private String password;

		public String getUserName() {
			return userName;
		}

		public void setUsername(String username) {
			this.userName = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
