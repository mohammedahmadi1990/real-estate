package com.realestate.realestatemanagement.service;

import com.realestate.realestatemanagement.model.Estate;
import com.realestate.realestatemanagement.web.dto.EstateAddDto;
import java.util.List;

public interface EstateService {

    List<Estate> listAll();
    void save(EstateAddDto dto);
    Estate get(long id);
    void delete(Estate estate);

    void update(EstateAddDto dto, long id);
}
