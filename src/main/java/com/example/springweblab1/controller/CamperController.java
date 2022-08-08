package com.example.springweblab1.controller;

import com.example.springweblab1.dto.CamperDTO;
import com.example.springweblab1.model.Camper;
import com.example.springweblab1.service.CamperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CamperController {

    @Autowired
    private CamperService camperService;

    @PostMapping("/campers")
    public CamperDTO createCamper(@Valid @RequestBody CamperDTO camper) {
        return camperService.createCamperDTO(camper);
    }

    @GetMapping("/campers")
    public List<CamperDTO> readCampers() {
        return camperService.getCamperDTOs();
    }

    @GetMapping("/campers/{camperId}")
    public CamperDTO readCamper(@PathVariable(value = "camperId") Integer id) {
        return camperService.getCamperDTO(id);
    }

    @PutMapping("/campers/{camperId}")
    public Camper updateCamper(@PathVariable(value = "camperId") Integer id, @RequestBody Camper camperData) {
        return camperService.updateCamper(id, camperData);
    }

    @DeleteMapping("/campers/{camperId}")
    public void deleteCamper(@PathVariable(value = "camperId") Integer id) {
        camperService.deleteCamper(id);
    }
    @DeleteMapping("/campers")
    public void deleteAllCampers() {
        camperService.deleteAllCampers();
    }

}
