package com.vobi.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vobi.bank.domain.Users;
import com.vobi.bank.dto.UsersDTO;
import com.vobi.bank.mapper.UsersMapper;
import com.vobi.bank.service.UsersService;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

	@Autowired
	UsersService usersService;
	
	
	@Autowired
	UsersMapper usersMapper;
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) throws Exception{		
		usersService.deleteById(id);
	}
	
	@PutMapping 
	public UsersDTO update(@Valid @RequestBody UsersDTO usersDTO)throws Exception{
		Users users=usersMapper.usersDTOtoUsers(usersDTO);
		users=usersService.update(users);
		usersDTO=usersMapper.usersToUsersDTO(users);
		
		return usersDTO;
	}
	
	
	@PostMapping
	public UsersDTO save(@RequestBody @Valid UsersDTO usersDTO)throws Exception{
		Users users=usersMapper.usersDTOtoUsers(usersDTO);
		users=usersService.save(users);
		usersDTO=usersMapper.usersToUsersDTO(users);
		
		return usersDTO;
	}
	
	@GetMapping("/{id}")
	public UsersDTO findById(@PathVariable String id) throws Exception{
		

		Users users=null;
		UsersDTO usersDTO=null;
		if(usersService.findById(id).isPresent()==true)
			users=usersService.findById(id).get();
		
		usersDTO=usersMapper.usersToUsersDTO(users);
		
		return usersDTO;
		
	}
	
	@GetMapping
	public List<UsersDTO> findAll()throws Exception{
		
		List<Users> allUsers=usersService.findAll();
		List<UsersDTO> usersDTOs=usersMapper.usersListToUsersDTOList(allUsers);
		
		return usersDTOs;
	}
	
	
	
	}
