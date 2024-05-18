package com.parkings.parkingsApi.service.interfaces;

import com.parkings.parkingsApi.persistence.entity.CompositeId;
import com.parkings.parkingsApi.presentation.dto.VigilanteDTO;
import java.util.List;

public interface IVigilanteService {
  List<VigilanteDTO> findAll();

  VigilanteDTO findById(CompositeId id);

  VigilanteDTO createVigilante(VigilanteDTO vigilanteDTO);

  VigilanteDTO updateVigilante(CompositeId id, VigilanteDTO vigilanteDTO);

  String deleteVigilante(CompositeId id);
}
