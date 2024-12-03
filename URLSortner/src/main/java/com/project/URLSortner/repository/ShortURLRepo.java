package com.project.URLSortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.URLSortner.entity.ShortURL;

public interface ShortURLRepo extends JpaRepository<ShortURL, Long> {
	@Query("SELECT u.originalUrl FROM ShortURL u WHERE u.shortCode = :shortCode")
    String findByShortCode(@Param("shortCode") String shortCode);
}
