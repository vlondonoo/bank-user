package com.vobi.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vobi.bank.domain.UserType;
import com.vobi.bank.domain.Users;
import com.vobi.bank.repository.UserTypeRepositpry;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class UsersServiceIT {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	UserTypeRepositpry userTypeRepository;
	
	@Test
	@Order(1)
	void debeValidarLasDependencias() {
		assertNotNull(usersService);
		assertNotNull(userTypeRepository);		
	}
	
	@Test
	@Order(2)
	void debeCrearUnUSer() throws Exception{
		
		Integer idUserType=1;
		
		Users users=null;
		UserType userType=userTypeRepository.findById(idUserType).get();
		
		users=new Users();
		users.setUserEmail("yo@name.com");
		users.setUserType(userType);
		users.setName("yosoy");
		users.setEnable("Y");
		users.setToken("vvvvlllloooo");
			
		
		users=usersService.save(users);
		
		assertNotNull(users,"El usuario es nulo y no se pudo grabar");
		
		
	}
	
	@Test
	@Order(3)
	void debeModificarUnUSer() throws Exception {		
		
		String idUsers="yo@name.com";		
		Users users=null;
		
		users=usersService.findById(idUsers).get();		
		
		users.setName("vlondonoo");			
		
		users=usersService.update(users);
		
		assertNotNull(users,"El usuario es nulo y no se pudo modificar");				
		
	}

	@Test
	@Order(4)
	void debeBorrarUnUSer() throws Exception {		
		
		String idUsers="yo@name.com";		
		Users users=null;
		Optional<Users> usersOptional=null;
		
		users=usersService.findById(idUsers).get();		
		
		usersService.delete(users);
		usersOptional=usersService.findById(idUsers);
		
		assertFalse(usersOptional.isPresent(),"No pudo borrar el user");			
		
	}
	
	
	@Test
	@Order(5)
	void debeConsultarTodosLosUsers() {	
		
		List<Users> users=null;
		
		users=usersService.findAll();
		
		users.forEach(user->log.info(user.getName()));
		
		assertFalse(users.isEmpty(),"No consulto users");					
		
	}	
	

}
