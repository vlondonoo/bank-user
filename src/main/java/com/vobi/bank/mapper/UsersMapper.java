package com.vobi.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vobi.bank.domain.Users;
import com.vobi.bank.dto.UsersDTO;

@Mapper
public interface UsersMapper {
	@Mapping(source = "userType.ustyId", target= "ustyId")
	public UsersDTO  usersToUsersDTO(Users users);
	
	@Mapping(target = "userType.ustyId", source= "ustyId")
	public Users usersDTOtoUsers(UsersDTO usersDTO);
	
	public List<UsersDTO>  usersListToUsersDTOList(List<Users> allUsers);
	
	public List<Users> usersDTOListToUsersList(List<UsersDTO> usersDTOs);

}
