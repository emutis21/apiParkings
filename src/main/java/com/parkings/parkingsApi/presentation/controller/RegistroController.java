package com.parkings.parkingsApi.presentation.controller;

import com.parkings.parkingsApi.presentation.dto.RegistroDTO;
import com.parkings.parkingsApi.service.interfaces.IRegistroService;
import java.util.List;
import java.util.UUID;
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
@RequestMapping("/registro")
public class RegistroController {

  @Autowired
  private IRegistroService registroService;

  @GetMapping("/find")
  public ResponseEntity<List<RegistroDTO>> findAll() {
    return new ResponseEntity<>(this.registroService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/find/{idRegistro}")
  public ResponseEntity<RegistroDTO> findById(@PathVariable UUID idRegistro) {
    return new ResponseEntity<>(
      this.registroService.findById(idRegistro),
      HttpStatus.OK
    );
  }

  @PostMapping("/create")
  public ResponseEntity<RegistroDTO> createRegistro(
    @RequestBody RegistroDTO registroDTO
  ) {
    return new ResponseEntity<>(
      this.registroService.createRegistro(registroDTO),
      HttpStatus.CREATED
    );
  }

  @PutMapping("/update/{idRegistro}")
  public ResponseEntity<RegistroDTO> updateRegistro(
    @PathVariable UUID idRegistro,
    @RequestBody RegistroDTO registroDTO
  ) {
    return new ResponseEntity<>(
      this.registroService.updateRegistro(idRegistro, registroDTO),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/delete/{idRegistro}")
  public ResponseEntity<String> deleteRegistro(@PathVariable UUID idRegistro) {
    return new ResponseEntity<>(
      this.registroService.deleteRegistro(idRegistro),
      HttpStatus.OK
    );
  }
}
