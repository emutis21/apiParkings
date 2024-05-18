package com.parkings.parkingsApi.service.interfaces;

import com.parkings.parkingsApi.presentation.dto.RegistroDTO;
import java.util.List;
import java.util.UUID;

public interface IRegistroService {
  List<RegistroDTO> findAll();

  RegistroDTO findById(UUID idRegistro);

  RegistroDTO createRegistro(RegistroDTO registroDTO);

  RegistroDTO updateRegistro(UUID idRegistro, RegistroDTO registroDTO);

  String deleteRegistro(UUID idRegistro);
}
