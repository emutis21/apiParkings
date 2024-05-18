package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IVigilanteEntity;
import com.parkings.parkingsApi.persistence.entity.CompositeId;
import com.parkings.parkingsApi.persistence.entity.VigilanteEntity;
import com.parkings.parkingsApi.presentation.dto.VigilanteDTO;
import com.parkings.parkingsApi.service.interfaces.IVigilanteService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VigilanteServiceImpl implements IVigilanteService {

  @Autowired
  private IVigilanteEntity vigilanteEntity;

  @Override
  public List<VigilanteDTO> findAll() {
    ModelMapper modelMapper = new ModelMapper();

    return this.vigilanteEntity.findAll()
      .stream()
      .map(entity -> modelMapper.map(entity, VigilanteDTO.class))
      .collect(Collectors.toList());
  }

  @Override
  public VigilanteDTO findById(CompositeId id) {
    Optional<VigilanteEntity> vigilanteEntity =
      this.vigilanteEntity.findById(id);

    if (!vigilanteEntity.isPresent()) return new VigilanteDTO();

    ModelMapper modelMapper = new ModelMapper();

    VigilanteEntity currentVigilante = vigilanteEntity.get();

    return modelMapper.map(currentVigilante, VigilanteDTO.class);
  }

  @Override
  public VigilanteDTO createVigilante(VigilanteDTO vigilanteDTO) {
    try {
      ModelMapper modelMapper = new ModelMapper();

      VigilanteEntity vigilanteEntity = modelMapper.map(
        vigilanteDTO,
        VigilanteEntity.class
      );

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

    ModelMapper modelMapper = new ModelMapper();

    return modelMapper.map(currentVigilanteEntity, VigilanteDTO.class);
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
