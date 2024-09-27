package com.userservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.userservice.mapper.GenericDynamicMapper;
import com.userservice.dto.AddressDto;
import com.userservice.dto.CredentialDto;
import com.userservice.dto.UserDetailsDto;
import com.userservice.dto.request.CreateUserRequest;
import com.userservice.entity.Address;
import com.userservice.entity.Credentials;
import com.userservice.entity.User;
import com.userservice.entity.UserDetails;

@Configuration
public class MapperConfig {

	@Bean
	public GenericDynamicMapper<User, CreateUserRequest> userMapper() {
		return new GenericDynamicMapper<>(User.class, CreateUserRequest.class);
	}

	@Bean
	public GenericDynamicMapper<Credentials, CredentialDto> credentialsMapper() {
		return new GenericDynamicMapper<>(Credentials.class, CredentialDto.class);
	}

	@Bean
	public GenericDynamicMapper<UserDetails, UserDetailsDto> detailsMapper() {
		return new GenericDynamicMapper<>(UserDetails.class, UserDetailsDto.class);
	}

	@Bean
	public GenericDynamicMapper<Address, AddressDto> addressMapper() {
		return new GenericDynamicMapper<>(Address.class, AddressDto.class);
	}

}
