package com.parkings.parkingsApi.service.interfaces;

import com.parkings.parkingsApi.presentation.dto.TarifaDTO;
import java.util.List;

public interface ITarifaService {
  List<TarifaDTO> findAll();

  TarifaDTO findById(String idTarifa);

  TarifaDTO createTarifa(TarifaDTO tarifaDTO);

  TarifaDTO updateTarifa(String idTarifa, TarifaDTO tarifaDTO);

  String deleteTarifa(String idTarifa);
}
