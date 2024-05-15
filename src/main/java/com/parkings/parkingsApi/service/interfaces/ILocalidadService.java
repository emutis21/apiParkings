package com.parkings.parkingsApi.service.interfaces;

import com.parkings.parkingsApi.presentation.dto.LocalidadDTO;
import java.util.List;

public interface ILocalidadService {
  List<LocalidadDTO> findAll();

  LocalidadDTO findById(String idLocalidad);

  LocalidadDTO createLocalidad(LocalidadDTO localidadDTO);

  LocalidadDTO updateLocalidad(String idLocalidad, LocalidadDTO localidadDTO);

  String deleteLocalidad(String idLocalidad);
}
