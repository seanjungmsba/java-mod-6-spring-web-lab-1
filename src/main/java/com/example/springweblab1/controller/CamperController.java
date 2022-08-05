package com.example.springweblab1.controller;

import com.example.springweblab1.dto.CamperDTO;
import com.example.springweblab1.model.Camper;
import com.example.springweblab1.service.CamperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CamperController {

    // CamperService Injection
    // The CamperService object is injected automatically by Spring into the controller, so we can use it in the class.
    @Autowired
    private CamperService camperService;

    // This method is creating the /api/campers endpoint for the POST HTTP method and persisting the data sent from the client.
    // The @PostMapping annotation is a shorthand for the @RequestMapping(value="/campers", method=RequestMethod.POST) annotation.
    // Both of these annotations define the endpoint path as /api/campers
//    @PostMapping("/campers")
//    public ResponseEntity<Camper> createCamper(@Valid @RequestBody Camper camper) {
//        Camper newCamper = camperService.createCamper(camper);
//        // Notice that we are returning a ResponseEntity instead of a Camper object like earlier.
//        // The ResponseEntity allows us to modify response information (status code, headers) before sending them back to the client.
//        return ResponseEntity.ok(newCamper);
//    }

    @PostMapping("/campers")
    public CamperDTO createCamper(@Valid @RequestBody CamperDTO camper) {
        return camperService.createCamperDTO(camper);
    }

    // The @GetMapping is a shorthand for the @RequestMapping(value="/campers", method=RequestMethod.GET) annotation.
    // Both of these annotations define the endpoint path as /api/campers.
    // The readCampers method calls the camperService to retrieve all the camper records from the database and returns a JSON
//    @GetMapping("/campers")
//    public List<Camper> readCampers() {
//        return camperService.getCampers();
//    }
    @GetMapping("/campers")
    public List<CamperDTO> readCampers() {
        return camperService.getCamperDTOs();
    }

    // The {camperId} in the @GetMapping value is a dynamic value which takes on the value of the client’s request path parameter.
    // For example, the following URL would assign the value of 1 to camperId and id:
    // http://localhost:8080/api/campers/1
    // The @PathVariable annotation maps the dynamic path value to the method parameter id.
//    @GetMapping("/campers/{camperId}")
//    public Camper readCamper(@PathVariable(value = "camperId") Integer id) {
//        return camperService.getCamper(id);
//    }
    @GetMapping("/campers/{camperId}")
    public CamperDTO readCamper(@PathVariable(value = "camperId") Integer id) {
        return camperService.getCamperDTO(id);
    }

    // The @PutMapping is a shorthand for the @RequestMapping(value="/campers/{camperId}", method=RequestMethod.PUT).
    // This method is using @PathVariable to get the ID of the record that needs to be updated and @RequestBody to get the data sent by the client.
    @PutMapping("/campers/{camperId}")
    public Camper updateCamper(@PathVariable(value = "camperId") Integer id, @RequestBody Camper camperData) {
        return camperService.updateCamper(id, camperData);
    }

    // The @DeleteMapping is a shorthand for the @RequestMapping(value="/campers/{camperId}", method=RequestMethod.DELETE) annotation.
    // This method calls the deleteCamper method on the camperService class which removes the record with the given ID from the database.
    @DeleteMapping("/campers/{camperId}")
    public void deleteCamper(@PathVariable(value = "camperId") Integer id) {
        camperService.deleteCamper(id);
    }

}
