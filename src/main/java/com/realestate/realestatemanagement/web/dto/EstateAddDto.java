package com.realestate.realestatemanagement.web.dto;

import com.realestate.realestatemanagement.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstateAddDto {

    private String address;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date listedDate;
    private User user;
    private double price;

}
