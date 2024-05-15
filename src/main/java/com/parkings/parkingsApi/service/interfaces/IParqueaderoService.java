package com.parkings.parkingsApi.service.interfaces;

import com.parkings.parkingsApi.presentation.dto.ParqueaderoDTO;
import java.util.List;

public interface IParqueaderoService {
  List<ParqueaderoDTO> findAll();

  ParqueaderoDTO findById(String idParqueadero);

  ParqueaderoDTO createParqueadero(ParqueaderoDTO parqueaderoDTO);

  ParqueaderoDTO updateParqueadero(
    String idParqueadero,
    ParqueaderoDTO parqueaderoDTO
  );

  String deleteParqueadero(String idParqueadero);
}
