package com.parkings.parkingsApi.service.interfaces;

import com.parkings.parkingsApi.presentation.dto.PagoDTO;
import java.util.List;

public interface IPagoService {
  List<PagoDTO> findAll();

  PagoDTO findById(String idPago);

  PagoDTO createPago(PagoDTO pagoDTO);

  PagoDTO updatePago(String idPago, PagoDTO pagoDTO);

  String deletePago(String idPago);
}
