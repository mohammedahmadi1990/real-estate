package com.realestate.realestatemanagement.controller;

import com.realestate.realestatemanagement.model.User;
import com.realestate.realestatemanagement.service.UserService;
import com.realestate.realestatemanagement.web.dto.UserRegisterationDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegisterationDto userRegisterationDto(){
        return new UserRegisterationDto();
    }

    @GetMapping("registration")
    public String showRegisterationForm(){
        return "registration";
    }

    @PostMapping("registration")
    public String registerUser(@ModelAttribute("user")UserRegisterationDto dto){
        userService.save(dto);
        return "redirect:/registration?success";
    }

}
