package com.achyut.spd.userservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achyut.spd.generator.PasswordGenerator;
import com.achyut.spd.mapper.GenericDynamicMapper;
import com.achyut.spd.userservice.dtos.AddressDto;
import com.achyut.spd.userservice.dtos.CredentialDto;
import com.achyut.spd.userservice.dtos.UserDetailsDto;
import com.achyut.spd.userservice.dtos.request.CreateUserRequest;
import com.achyut.spd.userservice.entities.Address;
import com.achyut.spd.userservice.entities.Credentials;
import com.achyut.spd.userservice.entities.User;
import com.achyut.spd.userservice.entities.UserDetails;
import com.achyut.spd.userservice.repositories.UserRepository;
import com.achyut.spd.userservice.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final GenericDynamicMapper<User, CreateUserRequest> mapper;

	private final GenericDynamicMapper<Credentials, CredentialDto> credentialMapper;

	private final GenericDynamicMapper<UserDetails, UserDetailsDto> detailsMapper;

	private final GenericDynamicMapper<Address, AddressDto> addressMapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, GenericDynamicMapper<User, CreateUserRequest> mapper,
			GenericDynamicMapper<Credentials, CredentialDto> credentialMapper,
			GenericDynamicMapper<UserDetails, UserDetailsDto> detailsMapper,
			GenericDynamicMapper<Address, AddressDto> addressMapper) {
		this.userRepository = userRepository;
		this.mapper = mapper;
		this.credentialMapper = credentialMapper;
		this.detailsMapper = detailsMapper;
		this.addressMapper = addressMapper;

	}

	@Override
	public User createUser(CreateUserRequest userRequest) {

		User user = new User();

		UserDetailsDto detailsDto = userRequest.getUserDetails();

		AddressDto addressDto = detailsDto.getAddress();
		Address address = this.addressMapper.toEntity(addressDto);

		UserDetails userDetails = new UserDetails();
		userDetails.setAddress(address);
		userDetails.setName(detailsDto.getName());
		userDetails.setLastName(detailsDto.getLastName());
		userDetails.setDateOfBirth(detailsDto.getDateOfBirth());
		userDetails.setPhoneNumbers(detailsDto.getPhoneNumbers());;

		CredentialDto credentialDto = userRequest.getUserCredentials();
		credentialDto.setPassword(PasswordGenerator.generatePassword(12));
		Credentials credentials = this.credentialMapper.toEntity(credentialDto);

		user.setUserCredentials(credentials);
		user.setUserDetails(userDetails);

		this.userRepository.save(user);

		return user;
	}

}
