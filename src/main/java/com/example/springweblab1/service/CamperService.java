package com.example.springweblab1.service;

import com.example.springweblab1.dto.ActivityDTO;
import com.example.springweblab1.dto.CamperDTO;
import com.example.springweblab1.model.Activity;
import com.example.springweblab1.model.Camper;
import com.example.springweblab1.repository.CamperRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class CamperService {

    // The CamperRepository object is automatically created and injected by Spring into the CamperService class because of the @Autowired annotation.
    @Autowired
    CamperRepository camperRepository;

    @Autowired
    private ModelMapper mapper;

    // This method uses the default save method on the repository object to persist the member in the database.
    public Camper createCamper(Camper camper) {
        return camperRepository.save(camper);
    }
    public CamperDTO createCamperDTO(CamperDTO camperDTO) {
        // Convert the CamperDTO to a Camper entity
        Camper camper = mapper.map(camperDTO, Camper.class);
        // createDTO has an id and other property
        // activity has an id property
        // the mapper will create a signup with the name from the createDTO
        camper = camperRepository.save(camper);
        // camper will now have an id and other things
        // then we map that camper entity to a CamperDTO and then return that
        return mapper.map(camper, CamperDTO.class);
    }

    // It uses the findById method on the repository to find a record with the ID of id and returns the object.
    public List<Camper> getCampers() {
        return camperRepository.findAll();
    }
    public Camper getCamper(Integer id) {
        return camperRepository.findById(id).get();
    }

    public List<CamperDTO> getCamperDTOs() {
        return (List<CamperDTO>) camperRepository.findAll().stream().map(camper -> mapper.map(camper, CamperDTO.class));
    }

    public CamperDTO getCamperDTO(Integer id) {
        Camper camper =
                camperRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapper.map(camper, CamperDTO.class);
    }

    // We can’t simply use a default method for this method and have to add some custom method instead.
    // The controller will call this method with the ID of the member (id) the client wants to update and the data (memberData) to update it with.
    // We update the member instance and call the save method to persist the updated record.
    public Camper updateCamper(Integer id, Camper camperData) {
        if (!camperRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Camper camper = camperRepository.findById(id).get();
        camper.setAge(camperData.getAge());
        camper.setSignup(camperData.getSignup());
        return camperRepository.save(camper);
    }

    // It removes the member with the ID of id from the database.
    public void deleteCamper(Integer id) {
        if (camperRepository.existsById(id)) {
            camperRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
