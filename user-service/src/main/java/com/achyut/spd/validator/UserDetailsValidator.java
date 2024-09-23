package com.achyut.spd.validator;

import java.util.List;

import com.achyut.spd.userservice.dtos.AddressDto;
import com.achyut.spd.userservice.dtos.UserDetailsDto;

public class UserDetailsValidator {
    
    public static void checkDetails(UserDetailsDto details) {
        
        String name = details.getName();
        
        String lastName = details.getLastName();
        
        String dateOfBirth = details.getDateOfBirth();
        
        AddressDto address = details.getAddress();
        
        List<String> phoneNumbers = details.getPhoneNumbers();
        
        if(phoneNumbers != null && !phoneNumbers.isEmpty()) {
            phoneNumbers.forEach(ph -> PhoneNumberValidator.checkNumber(ph));
        }
        
        String city = address.getCity();
        
        String state = address.getState();
        
        Integer zipCode = address.getZipCode();
        
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        
        if(lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be blank");
        }
        
        if(dateOfBirth == null || dateOfBirth.isBlank()) {
            throw new IllegalArgumentException("Date of birth cannot be blank");
        }
        
        if(city == null || city.isBlank()) {
            
        }
    }

}
