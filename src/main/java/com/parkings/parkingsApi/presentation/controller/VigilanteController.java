package com.parkings.parkingsApi.presentation.controller;

import com.parkings.parkingsApi.persistence.entity.CompositeId;
import com.parkings.parkingsApi.presentation.dto.VigilanteDTO;
import com.parkings.parkingsApi.service.interfaces.IVigilanteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vigilante")
public class VigilanteController {

  @Autowired
  private IVigilanteService vigilanteService;

  @GetMapping("/find")
  public ResponseEntity<List<VigilanteDTO>> findAll() {
    return new ResponseEntity<>(this.vigilanteService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/find/{numId}/{tipoId}")
  public ResponseEntity<VigilanteDTO> findById(
    @PathVariable Long numId,
    @PathVariable String tipoId
  ) {
    CompositeId id = new CompositeId(numId, tipoId);
    return new ResponseEntity<>(
      this.vigilanteService.findById(id),
      HttpStatus.OK
    );
  }

  @PostMapping("/create")
  public ResponseEntity<VigilanteDTO> createVigilante(
    @RequestBody VigilanteDTO vigilanteDTO
  ) {
    return new ResponseEntity<>(
      this.vigilanteService.createVigilante(vigilanteDTO),
      HttpStatus.CREATED
    );
  }

  @PatchMapping("/update/{numId}/{tipoId}")
  public ResponseEntity<VigilanteDTO> updateVigilante(
    @PathVariable Long numId,
    @PathVariable String tipoId,
    @RequestBody VigilanteDTO vigilanteDTO
  ) {
    CompositeId id = new CompositeId(numId, tipoId);
    return new ResponseEntity<>(
      this.vigilanteService.updateVigilante(id, vigilanteDTO),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/delete/{numId}/{tipoId}")
  public ResponseEntity<String> deleteVigilante(
    @PathVariable Long numId,
    @PathVariable String tipoId
  ) {
    CompositeId id = new CompositeId(numId, tipoId);
    String response = this.vigilanteService.deleteVigilante(id);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
