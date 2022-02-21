package com.vobi.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vobi.bank.domain.UserType;

public interface UserTypeRepositpry extends JpaRepository<UserType, Integer> {

}
