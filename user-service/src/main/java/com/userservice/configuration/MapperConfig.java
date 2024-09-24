package com.achyut.spd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.achyut.spd.mapper.GenericDynamicMapper;
import com.achyut.spd.userservice.dtos.AddressDto;
import com.achyut.spd.userservice.dtos.CredentialDto;
import com.achyut.spd.userservice.dtos.UserDetailsDto;
import com.achyut.spd.userservice.dtos.request.CreateUserRequest;
import com.achyut.spd.userservice.entities.Address;
import com.achyut.spd.userservice.entities.Credentials;
import com.achyut.spd.userservice.entities.User;
import com.achyut.spd.userservice.entities.UserDetails;

@Configuration
public class MapperConfig {
	
	@Bean
    public GenericDynamicMapper<User, CreateUserRequest> userMapper() {
        return new GenericDynamicMapper<User, CreateUserRequest>();
    }
	
	@Bean
    public GenericDynamicMapper<Credentials, CredentialDto> credentialsMapper() {
        return new GenericDynamicMapper<Credentials, CredentialDto>();
    }
	
	@Bean
    public GenericDynamicMapper<UserDetails, UserDetailsDto> detailsMapper() {
        return new GenericDynamicMapper<UserDetails, UserDetailsDto>();
    }
	
	@Bean
    public GenericDynamicMapper<Address, AddressDto> addressMapper() {
        return new GenericDynamicMapper<Address, AddressDto>();
    }

}
