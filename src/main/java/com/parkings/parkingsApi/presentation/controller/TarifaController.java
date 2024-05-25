package com.parkings.parkingsApi.presentation.controller;

import com.parkings.parkingsApi.presentation.dto.TarifaDTO;
import com.parkings.parkingsApi.service.interfaces.ITarifaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tarifa")
public class TarifaController {

  @Autowired
  private ITarifaService tarifaService;

  @GetMapping("/find")
  public ResponseEntity<List<TarifaDTO>> findAll() {
    return new ResponseEntity<>(this.tarifaService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/find/{idTarifa}")
  public ResponseEntity<TarifaDTO> findById(@PathVariable String idTarifa) {
    return new ResponseEntity<>(
      this.tarifaService.findById(idTarifa),
      HttpStatus.OK
    );
  }

  @PostMapping("/create")
  public ResponseEntity<TarifaDTO> createTarifa(
    @RequestBody TarifaDTO tarifaDTO
  ) {
    if (tarifaDTO.getIdTarifa() == null || tarifaDTO.getIdTarifa().isEmpty()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(
      this.tarifaService.createTarifa(tarifaDTO),
      HttpStatus.CREATED
    );
  }

  @PutMapping("/update/{idTarifa}")
  public ResponseEntity<TarifaDTO> updateTarifa(
    @PathVariable String idTarifa,
    @RequestBody TarifaDTO tarifaDTO
  ) {
    return new ResponseEntity<>(
      this.tarifaService.updateTarifa(idTarifa, tarifaDTO),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/delete/{idTarifa}")
  public ResponseEntity<String> deleteTarifa(@PathVariable String idTarifa) {
    String response = this.tarifaService.deleteTarifa(idTarifa);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
