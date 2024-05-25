package com.parkings.parkingsApi.presentation.controller;

import com.parkings.parkingsApi.presentation.dto.AreaDTO;
import com.parkings.parkingsApi.service.interfaces.IAreaService;
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
@RequestMapping("/area")
public class AreaController {

  @Autowired
  private IAreaService areaService;

  @GetMapping("/find")
  public ResponseEntity<List<AreaDTO>> findAll() {
    return new ResponseEntity<>(this.areaService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/find/{idArea}")
  public ResponseEntity<AreaDTO> findById(@PathVariable String idArea) {
    return new ResponseEntity<>(
      this.areaService.findById(idArea),
      HttpStatus.OK
    );
  }

  @GetMapping("/find/parqueadero/{idParqueadero}")
  public ResponseEntity<List<AreaDTO>> findAllByParqueadero(
    @PathVariable String idParqueadero
  ) {
    return new ResponseEntity<>(
      this.areaService.findAllByParqueadero(idParqueadero),
      HttpStatus.OK
    );
  }

  @PostMapping("/create")
  public ResponseEntity<AreaDTO> createArea(@RequestBody AreaDTO areaDTO) {
    return new ResponseEntity<>(
      this.areaService.createArea(areaDTO),
      HttpStatus.CREATED
    );
  }

  @PutMapping("/update/{idArea}")
  public ResponseEntity<AreaDTO> updateArea(
    @PathVariable String idArea,
    @RequestBody AreaDTO areaDTO
  ) {
    return new ResponseEntity<>(
      this.areaService.updateArea(idArea, areaDTO),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/delete/{idArea}")
  public ResponseEntity<String> deleteArea(@PathVariable String idArea) {
    return new ResponseEntity<>(
      this.areaService.deleteArea(idArea),
      HttpStatus.OK
    );
  }
}
