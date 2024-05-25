package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IParqueaderoDAO;
import com.parkings.parkingsApi.persistence.entity.ParqueaderoEntity;
import com.parkings.parkingsApi.presentation.dto.ParqueaderoDTO;
import com.parkings.parkingsApi.service.interfaces.IParqueaderoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParqueaderoServiceImpl implements IParqueaderoService {

  @Autowired
  private IParqueaderoDAO parqueaderoDAO;

  @Override
  public List<ParqueaderoDTO> findAll() {
    return this.parqueaderoDAO.findAll()
      .stream()
      .map(entity -> {
        ParqueaderoDTO dto = new ParqueaderoDTO();
        dto.setIdParqueadero(entity.getIdParqueadero());
        dto.setDireccion(entity.getDireccion());
        dto.setDisponible(entity.isDisponible());
        dto.setIdLocalidad(entity.getIdLocalidad());

        return dto;
      })
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

    ParqueaderoEntity currentParqueaderoEntity = parqueaderoEntity.get();

    ParqueaderoDTO dto = new ParqueaderoDTO();

    dto.setIdParqueadero(currentParqueaderoEntity.getIdParqueadero());
    dto.setDireccion(currentParqueaderoEntity.getDireccion());
    dto.setDisponible(currentParqueaderoEntity.isDisponible());
    dto.setIdLocalidad(currentParqueaderoEntity.getIdLocalidad());

    return dto;
  }

  @Override
  public List<ParqueaderoDTO> findAllByLocalidad(String idLocalidad) {
    return this.parqueaderoDAO.findAll()
      .stream()
      .filter(entity -> entity.getIdLocalidad().equals(idLocalidad))
      .map(entity -> {
        ParqueaderoDTO dto = new ParqueaderoDTO();
        dto.setIdParqueadero(entity.getIdParqueadero());
        dto.setDireccion(entity.getDireccion());
        dto.setDisponible(entity.isDisponible());
        dto.setIdLocalidad(entity.getIdLocalidad());

        return dto;
      })
      .collect(Collectors.toList());
  }

  @Override
  public ParqueaderoDTO createParqueadero(ParqueaderoDTO parqueaderoDTO) {
    try {
      ParqueaderoEntity parqueaderoEntity = new ParqueaderoEntity();

      parqueaderoEntity.setIdParqueadero(parqueaderoDTO.getIdParqueadero());
      parqueaderoEntity.setDireccion(parqueaderoDTO.getDireccion());
      parqueaderoEntity.setDisponible(parqueaderoDTO.isDisponible());
      parqueaderoEntity.setIdLocalidad(parqueaderoDTO.getIdLocalidad());

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

    ParqueaderoDTO dto = new ParqueaderoDTO();
    dto.setIdParqueadero(currentParqueaderoEntity.getIdParqueadero());
    dto.setDireccion(currentParqueaderoEntity.getDireccion());
    dto.setDisponible(currentParqueaderoEntity.isDisponible());
    dto.setIdLocalidad(currentParqueaderoEntity.getIdLocalidad());

    return dto;
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
