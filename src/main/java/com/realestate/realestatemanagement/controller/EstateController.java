package com.realestate.realestatemanagement.controller;

import com.realestate.realestatemanagement.model.Estate;
import com.realestate.realestatemanagement.model.Note;
import com.realestate.realestatemanagement.service.EstateService;
import com.realestate.realestatemanagement.service.UserService;
import com.realestate.realestatemanagement.web.dto.EstateAddDto;
import com.realestate.realestatemanagement.web.dto.NewNoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class EstateController {

    EstateService estateService;
    @Autowired
    UserService userService;

    public EstateController(EstateService estateService, UserService userService) {
        super();
        this.estateService = estateService;
        this.userService = userService;
    }

    @ModelAttribute("estate")
    public EstateAddDto estateAdditionDto(){
        return new EstateAddDto();
    }

    @GetMapping("listings/new")
    public String showNewEstateForm(){
        return "new";
    }

    @PostMapping("listings/new")
    public String addNewEstate(@ModelAttribute("estate") EstateAddDto dto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = "";
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }

        dto.setUser(userService.getUserByEmail(email));
        dto.setListedDate(new Date());
        estateService.save(dto);
        return "redirect:/home";
    }

    @GetMapping("listings/{id}")
    public String showEstateNotes(@PathVariable(value="id") int id, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = "";
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }
        List<Estate> estateList = estateService.listAll();
        List<Note> noteList = estateList.get(id).getNotes();
        StringBuilder noteInString = new StringBuilder();
        for (Note note:noteList
             ) {
            noteInString.append("Added by ").append(note.getUser()).append("\n  - ").append(note.getNote()).append("\n\n");
        }
        model.addAttribute("noteInString", noteInString.toString());
        model.addAttribute("estateList", estateList);
        model.addAttribute("notes", new Note());
        model.addAttribute("name", userService.getUserByEmail(email).getUserName() );
        return "newNote";
    }

    @PostMapping("listings/{id}/note")
    public String addNoteToEstate(@PathVariable(value="id") int id, @ModelAttribute("notes") Note dtoNote) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = "";
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }
        dtoNote.setUser(userService.getUserByEmail(email));
        estateService.get(id).getNotes().add(dtoNote);
        return "redirect:/listings/{"+id+"}";
    }

    @GetMapping("listings/{id}/delete")
    public String DeleteEstate(@PathVariable(value="id") int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = "";
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }
        estateService.delete(estateService.get(id));
        return "redirect:/home?success";
    }

    @GetMapping("listings/{id}/edit")
    public String showEditEstate(@PathVariable(value="id") int id, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = "";
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }

        List<Estate> estateList = estateService.listAll();
        model.addAttribute("estate", estateList.get(id));
        model.addAttribute("id", id);
        return "edit";
    }

    @PostMapping("listings/{id}/edit")
    public String updateEdit(@PathVariable(value="id") int id, @ModelAttribute("estate") EstateAddDto dto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = "";
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }

        dto.setUser(userService.getUserByEmail(email));
        estateService.update(dto,id);
        return "redirect:/home?success";
    }

    @GetMapping("home")
    public String showEstateList(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = "";
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }
        List<Estate> estateList = estateService.listAll();
        model.addAttribute("estateList", estateList);
        model.addAttribute("name", userService.getUserByEmail(email).getUserName() );
        return "home";
    }
}
