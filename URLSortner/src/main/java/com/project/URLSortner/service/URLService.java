package com.project.URLSortner.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.URLSortner.entity.ShortURL;
import com.project.URLSortner.exceptions.URLNotFoundException;
import com.project.URLSortner.repository.ShortURLRepo;

@Service
public class URLService {
	@Autowired
	private ShortURLRepo repo;
	
	public String urlSortner(String url) {
		String shortCode = UUID.randomUUID().toString().substring(0,6);
		System.out.println("Short generated code : "+shortCode);
		ShortURL e = new ShortURL();
		e.setShortCode(shortCode);
		e.setOriginalUrl(url);
		System.out.println(e.toString());
		repo.save(e);
		return shortCode;
	}
	
	public String getOriginalUrl(String shortCode) throws Exception {
	    String shortUrl = repo.findByShortCode(shortCode); 
	    if(shortUrl == null) {
	    	throw new Exception("URL is not valid");
	    }
	    return shortUrl;
	}

}
