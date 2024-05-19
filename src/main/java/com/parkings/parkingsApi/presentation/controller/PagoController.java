package com.parkings.parkingsApi.presentation.controller;

import com.parkings.parkingsApi.presentation.dto.PagoDTO;
import com.parkings.parkingsApi.service.interfaces.IPagoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pago")
public class PagoController {

  @Autowired
  private IPagoService pagoService;

  @GetMapping("/find")
  public ResponseEntity<List<PagoDTO>> findAll() {
    return new ResponseEntity<>(this.pagoService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/find/{idPago}")
  public ResponseEntity<PagoDTO> findById(@PathVariable String idPago) {
    return new ResponseEntity<>(
      this.pagoService.findById(idPago),
      HttpStatus.OK
    );
  }

  @PostMapping("/create")
  public ResponseEntity<PagoDTO> createPago(@RequestBody PagoDTO pagoDTO) {
    return new ResponseEntity<>(
      this.pagoService.createPago(pagoDTO),
      HttpStatus.CREATED
    );
  }

  @PutMapping("/update/{idPago}")
  public ResponseEntity<PagoDTO> updatePago(
    @PathVariable String idPago,
    @RequestBody PagoDTO pagoDTO
  ) {
    return new ResponseEntity<>(
      this.pagoService.updatePago(idPago, pagoDTO),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/delete/{idPago}")
  public ResponseEntity<String> deletePago(@PathVariable String idPago) {
    return new ResponseEntity<>(
      this.pagoService.deletePago(idPago),
      HttpStatus.OK
    );
  }
}
