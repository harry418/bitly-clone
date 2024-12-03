package com.project.URLSortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.URLSortner.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByUserName(String userName);
}
