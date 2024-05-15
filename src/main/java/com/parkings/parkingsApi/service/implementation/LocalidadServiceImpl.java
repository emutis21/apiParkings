package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.ILocalidadDAO;
import com.parkings.parkingsApi.persistence.entity.LocalidadEntity;
import com.parkings.parkingsApi.presentation.dto.LocalidadDTO;
import com.parkings.parkingsApi.service.interfaces.ILocalidadService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalidadServiceImpl implements ILocalidadService {

  @Autowired
  private ILocalidadDAO localidadDAO;

  @Override
  public List<LocalidadDTO> findAll() {
    ModelMapper modelMapper = new ModelMapper();

    return this.localidadDAO.findAll()
      .stream()
      .map(entity -> modelMapper.map(entity, LocalidadDTO.class))
      .collect(Collectors.toList());
  }

  @Override
  public LocalidadDTO findById(String idLocalidad) {
    Optional<LocalidadEntity> localidadEntity =
      this.localidadDAO.findById(idLocalidad);

    if (!localidadEntity.isPresent()) return new LocalidadDTO();

    ModelMapper modelMapper = new ModelMapper();

    LocalidadEntity currentLocalidad = localidadEntity.get();

    return modelMapper.map(currentLocalidad, LocalidadDTO.class);
  }

  @Override
  public LocalidadDTO createLocalidad(LocalidadDTO localidadDTO) {
    try {
      ModelMapper modelMapper = new ModelMapper();

      LocalidadEntity localidadEntity = modelMapper.map(
        localidadDTO,
        LocalidadEntity.class
      );

      this.localidadDAO.saveLocalidad(localidadEntity);

      return localidadDTO;
    } catch (Exception e) {
      throw new UnsupportedOperationException(
        "Error al crear la localidad: " + e.getMessage(),
        e
      );
    }
  }

  @Override
  public LocalidadDTO updateLocalidad(
    String idLocalidad,
    LocalidadDTO localidadDTO
  ) {
    Optional<LocalidadEntity> localidadEntity =
      this.localidadDAO.findById(idLocalidad);

    if (!localidadEntity.isPresent()) throw new IllegalArgumentException(
      "La localidad no existe"
    );

    LocalidadEntity currentLocalidadEntity = localidadEntity.get();

    currentLocalidadEntity.setNombreLocalidad(
      localidadDTO.getNombreLocalidad()
    );

    this.localidadDAO.updateLocalidad(currentLocalidadEntity);

    ModelMapper modelMapper = new ModelMapper();

    return modelMapper.map(currentLocalidadEntity, LocalidadDTO.class);
  }

  @Override
  public String deleteLocalidad(String idLocalidad) {
    Optional<LocalidadEntity> localidadEntity =
      this.localidadDAO.findById(idLocalidad);

    if (!localidadEntity.isPresent()) return "La localidad no existe";

    LocalidadEntity currentLocalidadEntity = localidadEntity.get();

    this.localidadDAO.deleteLocalidad(currentLocalidadEntity);

    return "Localidad con id " + idLocalidad + " eliminada";
  }
}
