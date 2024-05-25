package com.parkings.parkingsApi.presentation.controller;

import com.parkings.parkingsApi.presentation.dto.ParqueaderoDTO;
import com.parkings.parkingsApi.service.interfaces.IParqueaderoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/parqueadero")
public class ParqueaderoController {

  @Autowired
  private IParqueaderoService parqueaderoService;

  @GetMapping("/find")
  public ResponseEntity<List<ParqueaderoDTO>> findAll() {
    return new ResponseEntity<>(
      this.parqueaderoService.findAll(),
      HttpStatus.OK
    );
  }

  @GetMapping("/find/{idParqueadero}")
  public ResponseEntity<ParqueaderoDTO> findById(
    @PathVariable String idParqueadero
  ) {
    return new ResponseEntity<>(
      this.parqueaderoService.findById(idParqueadero),
      HttpStatus.OK
    );
  }

  @GetMapping("/find/localidad/{idLocalidad}")
  public ResponseEntity<List<ParqueaderoDTO>> findAllByLocalidad(
    @PathVariable String idLocalidad
  ) {
    return new ResponseEntity<>(
      this.parqueaderoService.findAllByLocalidad(idLocalidad),
      HttpStatus.OK
    );
  }

  @PostMapping("/create")
  public ResponseEntity<ParqueaderoDTO> createParqueadero(
    @RequestBody ParqueaderoDTO parqueaderoDTO
  ) {
    return new ResponseEntity<>(
      this.parqueaderoService.createParqueadero(parqueaderoDTO),
      HttpStatus.CREATED
    );
  }

  @PutMapping("/update/{idParqueadero}")
  public ResponseEntity<ParqueaderoDTO> updateParqueadero(
    @PathVariable String idParqueadero,
    @RequestBody ParqueaderoDTO parqueaderoDTO
  ) {
    return new ResponseEntity<>(
      this.parqueaderoService.updateParqueadero(idParqueadero, parqueaderoDTO),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/delete/{idParqueadero}")
  public ResponseEntity<String> deleteParqueadero(
    @PathVariable String idParqueadero
  ) {
    return new ResponseEntity<>(
      this.parqueaderoService.deleteParqueadero(idParqueadero),
      HttpStatus.OK
    );
  }
}
