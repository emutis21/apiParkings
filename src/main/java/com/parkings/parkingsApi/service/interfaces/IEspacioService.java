package com.parkings.parkingsApi.service.interfaces;

import com.parkings.parkingsApi.presentation.dto.EspacioDTO;
import java.util.List;

public interface IEspacioService {
  List<EspacioDTO> findAll();

  EspacioDTO findById(Long idEspacio);

  EspacioDTO createEspacio(EspacioDTO espacioDTO);

  EspacioDTO updateEspacio(Long idEspacio, EspacioDTO espacioDTO);

  String deleteEspacio(Long idEspacio);
}
