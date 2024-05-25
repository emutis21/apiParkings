package com.parkings.parkingsApi.presentation.controller;

import com.parkings.parkingsApi.presentation.dto.EspacioDTO;
import com.parkings.parkingsApi.service.interfaces.IEspacioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/espacio")
public class EspacioController {

  @Autowired
  private IEspacioService espacioService;

  @GetMapping("/find")
  public ResponseEntity<List<EspacioDTO>> findAll() {
    return new ResponseEntity<>(this.espacioService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/find/{idEspacio}")
  public ResponseEntity<EspacioDTO> findById(@PathVariable Long idEspacio) {
    return new ResponseEntity<>(
      this.espacioService.findById(idEspacio),
      HttpStatus.OK
    );
  }

  @GetMapping("/find/area/{idArea}")
  public ResponseEntity<List<EspacioDTO>> findAllByArea(
    @PathVariable String idArea
  ) {
    return new ResponseEntity<>(
      this.espacioService.findAllByArea(idArea),
      HttpStatus.OK
    );
  }

  @PostMapping("/create")
  public ResponseEntity<EspacioDTO> createEspacio(
    @RequestBody EspacioDTO espacioDTO
  ) {
    return new ResponseEntity<>(
      this.espacioService.createEspacio(espacioDTO),
      HttpStatus.CREATED
    );
  }

  @PutMapping("/update/{idEspacio}")
  public ResponseEntity<EspacioDTO> updateEspacio(
    @PathVariable Long idEspacio,
    @RequestBody EspacioDTO espacioDTO
  ) {
    return new ResponseEntity<>(
      this.espacioService.updateEspacio(idEspacio, espacioDTO),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/delete/{idEspacio}")
  public ResponseEntity<String> deleteEspacio(@PathVariable Long idEspacio) {
    String response = this.espacioService.deleteEspacio(idEspacio);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
