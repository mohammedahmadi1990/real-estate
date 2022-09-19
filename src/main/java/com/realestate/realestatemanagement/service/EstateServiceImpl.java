package com.realestate.realestatemanagement.service;

import com.realestate.realestatemanagement.model.Estate;
import com.realestate.realestatemanagement.model.User;
import com.realestate.realestatemanagement.repository.EstateRepository;
import com.realestate.realestatemanagement.web.dto.EstateAddDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstateServiceImpl implements EstateService {

    private EstateRepository repo;

    public EstateServiceImpl(EstateRepository estateRepository) {
        super();
        this.repo = estateRepository;
    }

    @Override
    public List<Estate> listAll() {
        return repo.findAll();
    }

    @Override
    public void save(EstateAddDto dto) {
        Estate estate = new Estate(
                dto.getAddress(),
                dto.getListedDate(),
                dto.getPrice(),
                dto.getUser()
        );
        repo.save(estate);
    }

    @Override
    public Estate get(long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(Estate estate) {
        repo.delete(estate);
    }

    @Override
    public void update(EstateAddDto dto, long id) {
        repo.findById(id).get().setAddress(dto.getAddress());
        repo.findById(id).get().setListedDate(dto.getListedDate());
        repo.findById(id).get().setPrice(dto.getPrice());
    }
}
