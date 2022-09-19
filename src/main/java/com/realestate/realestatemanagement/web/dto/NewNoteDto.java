package com.realestate.realestatemanagement.web.dto;

import com.realestate.realestatemanagement.model.Note;
import com.realestate.realestatemanagement.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NewNoteDto {

    private Note note;
    private User user;

}
