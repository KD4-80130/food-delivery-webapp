package com.swaad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swaad.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String username);
}
