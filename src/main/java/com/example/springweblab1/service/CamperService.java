package com.example.springweblab1.service;

import com.example.springweblab1.dto.CamperDTO;
import com.example.springweblab1.model.Camper;
import com.example.springweblab1.repository.CamperRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CamperService {
    @Autowired
    CamperRepository camperRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Camper createCamper(Camper camper) {
        return camperRepository.save(camper);
    }
    public CamperDTO createCamperDTO(CamperDTO camperDTO) {
        try {
            Camper camper = modelMapper.map(camperDTO, Camper.class);
            camper = camperRepository.save(camper);
            return modelMapper.map(camper, CamperDTO.class);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "validation errors");
        }
    }

    public List<Camper> getCampers() {
        return camperRepository.findAll();
    }
    public Camper getCamper(Integer id) {
        return camperRepository.findById(id).get();
    }

    public List<CamperDTO> getCamperDTOs() {
        return camperRepository.findAll().stream().map(camper -> modelMapper.map(camper, CamperDTO.class)).collect(Collectors.toList());
    }
    public CamperDTO getCamperDTO(Integer id) {
        Camper camper =
                camperRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(camper, CamperDTO.class);
    }

    public Camper updateCamper(Integer id, Camper camperData) {
        if (!camperRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found");
        }
        Camper camper = camperRepository.findById(id).get();
        camper.setAge(camperData.getAge());
        camper.setSignup(camperData.getSignup());
        return camperRepository.save(camper);
    }

    public void deleteCamper(Integer id) {
        if (camperRepository.existsById(id)) {
            camperRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found");
        }
    }

    public void deleteAllCampers() {
        camperRepository.deleteAll();
    }

}
