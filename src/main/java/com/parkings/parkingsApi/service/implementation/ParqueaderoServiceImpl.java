package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IParqueaderoDAO;
import com.parkings.parkingsApi.persistence.entity.ParqueaderoEntity;
import com.parkings.parkingsApi.presentation.dto.ParqueaderoDTO;
import com.parkings.parkingsApi.service.interfaces.IParqueaderoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParqueaderoServiceImpl implements IParqueaderoService {

  @Autowired
  private IParqueaderoDAO parqueaderoDAO;

  @Override
  public List<ParqueaderoDTO> findAll() {
    ModelMapper modelMapper = new ModelMapper();

    return this.parqueaderoDAO.findAll()
      .stream()
      .map(entity -> modelMapper.map(entity, ParqueaderoDTO.class))
      .collect(Collectors.toList());
  }

  @Override
  public ParqueaderoDTO findById(String idParqueadero) {
    if (idParqueadero == null || idParqueadero.trim().isEmpty()) {
      throw new IllegalArgumentException(
        "El identificador del parqueadero no puede ser nulo o vacío"
      );
    }

    Optional<ParqueaderoEntity> parqueaderoEntity =
      this.parqueaderoDAO.findById(idParqueadero);

    if (!parqueaderoEntity.isPresent()) return new ParqueaderoDTO();

    ModelMapper modelMapper = new ModelMapper();

    ParqueaderoEntity currentParqueadero = parqueaderoEntity.get();

    return modelMapper.map(currentParqueadero, ParqueaderoDTO.class);
  }

  @Override
  public ParqueaderoDTO createParqueadero(ParqueaderoDTO parqueaderoDTO) {
    try {
      ModelMapper modelMapper = new ModelMapper();

      ParqueaderoEntity parqueaderoEntity = modelMapper.map(
        parqueaderoDTO,
        ParqueaderoEntity.class
      );

      this.parqueaderoDAO.saveParqueadero(parqueaderoEntity);

      return parqueaderoDTO;
    } catch (Exception e) {
      throw new UnsupportedOperationException(
        "Error al crear el parqueadero: " + e.getMessage(),
        e
      );
    }
  }

  @Override
  public ParqueaderoDTO updateParqueadero(
    String idParqueadero,
    ParqueaderoDTO parqueaderoDTO
  ) {
    Optional<ParqueaderoEntity> parqueaderoEntity =
      this.parqueaderoDAO.findById(idParqueadero);

    if (!parqueaderoEntity.isPresent()) throw new IllegalArgumentException(
      "El parqueadero no existe"
    );

    ParqueaderoEntity currentParqueaderoEntity = parqueaderoEntity.get();

    currentParqueaderoEntity.setDireccion(parqueaderoDTO.getDireccion());
    currentParqueaderoEntity.setDisponible(parqueaderoDTO.isDisponible());

    this.parqueaderoDAO.updateParqueadero(currentParqueaderoEntity);

    ModelMapper modelMapper = new ModelMapper();

    return modelMapper.map(currentParqueaderoEntity, ParqueaderoDTO.class);
  }

  @Override
  public String deleteParqueadero(String idParqueadero) {
    Optional<ParqueaderoEntity> parqueaderoEntity =
      this.parqueaderoDAO.findById(idParqueadero);

    if (!parqueaderoEntity.isPresent()) throw new IllegalArgumentException(
      "El parqueadero no existe"
    );

    ParqueaderoEntity currentParqueaderoEntity = parqueaderoEntity.get();

    this.parqueaderoDAO.deleteParqueadero(currentParqueaderoEntity);

    return "Parqueadero" + idParqueadero + " eliminado";
  }
}
