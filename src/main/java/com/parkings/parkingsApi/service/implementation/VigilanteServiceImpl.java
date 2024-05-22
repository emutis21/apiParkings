package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IVigilanteEntity;
import com.parkings.parkingsApi.persistence.entity.CompositeId;
import com.parkings.parkingsApi.persistence.entity.VigilanteEntity;
import com.parkings.parkingsApi.presentation.dto.VigilanteDTO;
import com.parkings.parkingsApi.service.interfaces.IVigilanteService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VigilanteServiceImpl implements IVigilanteService {

  @Autowired
  private IVigilanteEntity vigilanteEntity;

  @Override
  public List<VigilanteDTO> findAll() {
    return this.vigilanteEntity.findAll()
      .stream()
      .map(entity -> {
        VigilanteDTO dto = new VigilanteDTO();
        dto.setId(entity.getId());
        dto.setApellido1(entity.getApellido1());
        dto.setApellido2(entity.getApellido2());
        dto.setHasPerro(entity.getHasPerro());
        dto.setIdArea(entity.getIdArea());
        dto.setNombre1(entity.getNombre1());
        dto.setNombre2(entity.getNombre2());

        return dto;
      })
      .collect(Collectors.toList());
  }

  @Override
  public VigilanteDTO findById(CompositeId id) {
    Optional<VigilanteEntity> vigilanteEntity =
      this.vigilanteEntity.findById(id);

    if (!vigilanteEntity.isPresent()) return new VigilanteDTO();

    VigilanteEntity currentVigilante = vigilanteEntity.get();

    VigilanteDTO dto = new VigilanteDTO();

    dto.setId(currentVigilante.getId());
    dto.setApellido1(currentVigilante.getApellido1());
    dto.setApellido2(currentVigilante.getApellido2());
    dto.setHasPerro(currentVigilante.getHasPerro());
    dto.setIdArea(currentVigilante.getIdArea());
    dto.setNombre1(currentVigilante.getNombre1());
    dto.setNombre2(currentVigilante.getNombre2());

    return dto;
  }

  @Override
  public VigilanteDTO createVigilante(VigilanteDTO vigilanteDTO) {
    try {
      VigilanteEntity vigilanteEntity = new VigilanteEntity();
      vigilanteEntity.setId(vigilanteDTO.getId());
      vigilanteEntity.setApellido1(vigilanteDTO.getApellido1());
      vigilanteEntity.setApellido2(vigilanteDTO.getApellido2());
      vigilanteEntity.setHasPerro(vigilanteDTO.getHasPerro());
      vigilanteEntity.setIdArea(vigilanteDTO.getIdArea());
      vigilanteEntity.setNombre1(vigilanteDTO.getNombre1());
      vigilanteEntity.setNombre2(vigilanteDTO.getNombre2());

      this.vigilanteEntity.saveVigilante(vigilanteEntity);

      return vigilanteDTO;
    } catch (Exception e) {
      throw new UnsupportedOperationException(
        "Error al crear el vigilante: " + e.getMessage(),
        e
      );
    }
  }

  @Override
  public VigilanteDTO updateVigilante(
    CompositeId id,
    VigilanteDTO vigilanteDTO
  ) {
    Optional<VigilanteEntity> vigilanteEntity =
      this.vigilanteEntity.findById(id);

    if (!vigilanteEntity.isPresent()) throw new IllegalArgumentException(
      "El vigilante no existe"
    );

    VigilanteEntity currentVigilanteEntity = vigilanteEntity.get();

    if (vigilanteDTO.getApellido1() != null) {
      currentVigilanteEntity.setApellido1(vigilanteDTO.getApellido1());
    }
    if (vigilanteDTO.getApellido2() != null) {
      currentVigilanteEntity.setApellido2(vigilanteDTO.getApellido2());
    }
    if (vigilanteDTO.getNombre1() != null) {
      currentVigilanteEntity.setNombre1(vigilanteDTO.getNombre1());
    }
    if (vigilanteDTO.getNombre2() != null) {
      currentVigilanteEntity.setNombre2(vigilanteDTO.getNombre2());
    }
    if (vigilanteDTO.getHasPerro() != null) {
      currentVigilanteEntity.setHasPerro(vigilanteDTO.getHasPerro());
    }

    this.vigilanteEntity.updateVigilante(currentVigilanteEntity);

    VigilanteDTO dto = new VigilanteDTO();
    dto.setId(currentVigilanteEntity.getId());
    dto.setApellido1(currentVigilanteEntity.getApellido1());
    dto.setApellido2(currentVigilanteEntity.getApellido2());
    dto.setHasPerro(currentVigilanteEntity.getHasPerro());
    dto.setIdArea(currentVigilanteEntity.getIdArea());
    dto.setNombre1(currentVigilanteEntity.getNombre1());
    dto.setNombre2(currentVigilanteEntity.getNombre2());

    return dto;
  }

  @Override
  public String deleteVigilante(CompositeId id) {
    Optional<VigilanteEntity> vigilanteEntity =
      this.vigilanteEntity.findById(id);

    if (!vigilanteEntity.isPresent()) return "El vigilante no existe";

    VigilanteEntity currentVigilanteEntity = vigilanteEntity.get();

    this.vigilanteEntity.deleteVigilante(currentVigilanteEntity);

    return (
      "Vigilante con id " + id.getTipoId() + "-" + id.getNumId() + " eliminado"
    );
  }
}
