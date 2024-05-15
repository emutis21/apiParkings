package com.parkings.parkingsApi.presentation.controller;

import com.parkings.parkingsApi.presentation.dto.LocalidadDTO;
import com.parkings.parkingsApi.service.interfaces.ILocalidadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localidad")
public class LocalidadController {

  @Autowired
  private ILocalidadService localidadService;

  @GetMapping("/findLocalidades")
  public ResponseEntity<List<LocalidadDTO>> findAll() {
    return new ResponseEntity<>(this.localidadService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/findLocalidad/{idLocalidad}")
  public ResponseEntity<LocalidadDTO> findById(
    @PathVariable String idLocalidad
  ) {
    return new ResponseEntity<>(
      this.localidadService.findById(idLocalidad),
      HttpStatus.OK
    );
  }

  @PostMapping("/createLocalidad")
  public ResponseEntity<LocalidadDTO> createLocalidad(
    @RequestBody LocalidadDTO localidadDTO
  ) {
    if (
      localidadDTO.getIdLocalidad() == null ||
      localidadDTO.getIdLocalidad().isEmpty()
    ) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(
      this.localidadService.createLocalidad(localidadDTO),
      HttpStatus.CREATED
    );
  }

  @PutMapping("/updateLocalidad/{idLocalidad}")
  public ResponseEntity<LocalidadDTO> updateLocalidad(
    @PathVariable String idLocalidad,
    @RequestBody LocalidadDTO localidadDTO
  ) {
    return new ResponseEntity<>(
      this.localidadService.updateLocalidad(idLocalidad, localidadDTO),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/deleteLocalidad/{idLocalidad}")
  public ResponseEntity<String> deleteLocalidad(
    @PathVariable String idLocalidad
  ) {
    String response = this.localidadService.deleteLocalidad(idLocalidad);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
