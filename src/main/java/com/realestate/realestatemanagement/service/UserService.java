package com.realestate.realestatemanagement.service;

import com.realestate.realestatemanagement.model.User;
import com.realestate.realestatemanagement.web.dto.UserRegisterationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
        User save(UserRegisterationDto registerationDto);
        User getUserByEmail(String email);
}
