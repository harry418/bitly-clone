package com.project.URLSortner.controllers;

import java.net.URI;
import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.URLSortner.service.URLService;

@RestController
@RequestMapping("/api")
public class URLShortnerContoller {

	@Autowired
	private URLService urlService;

	@PostMapping("/url/shorten")
	public String shortenURL(@RequestParam("url") String url) {
		return "Shorten URl :" + urlService.urlSortner(url);
	}

	@GetMapping("/{shortCode}")
	public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortCode) throws Exception {
		String originalUrl = urlService.getOriginalUrl(shortCode);
		
		if (originalUrl == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if not found
		}
		// Redirect to the original URL
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(URI.create(originalUrl));
		
		return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
	}

	
}
