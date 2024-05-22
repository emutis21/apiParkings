package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.ILocalidadDAO;
import com.parkings.parkingsApi.persistence.entity.LocalidadEntity;
import com.parkings.parkingsApi.presentation.dto.LocalidadDTO;
import com.parkings.parkingsApi.service.interfaces.ILocalidadService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalidadServiceImpl implements ILocalidadService {

  @Autowired
  private ILocalidadDAO localidadDAO;

  @Override
  public List<LocalidadDTO> findAll() {
    return this.localidadDAO.findAll()
      .stream()
      .map(entity -> {
        LocalidadDTO dto = new LocalidadDTO();
        dto.setIdLocalidad(entity.getIdLocalidad());
        dto.setNombreLocalidad(entity.getNombreLocalidad());

        return dto;
      })
      .collect(Collectors.toList());
  }

  @Override
  public LocalidadDTO findById(String idLocalidad) {
    Optional<LocalidadEntity> localidadEntity =
      this.localidadDAO.findById(idLocalidad);

    if (!localidadEntity.isPresent()) return new LocalidadDTO();

    LocalidadEntity currentLocalidadEntity = localidadEntity.get();

    LocalidadDTO dto = new LocalidadDTO();

    dto.setIdLocalidad(currentLocalidadEntity.getIdLocalidad());
    dto.setNombreLocalidad(currentLocalidadEntity.getNombreLocalidad());

    return dto;
  }

  @Override
  public LocalidadDTO createLocalidad(LocalidadDTO localidadDTO) {
    try {
      LocalidadEntity localidadEntity = new LocalidadEntity();
      localidadEntity.setIdLocalidad(localidadDTO.getIdLocalidad());
      localidadEntity.setNombreLocalidad(localidadDTO.getNombreLocalidad());

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

    LocalidadDTO dto = new LocalidadDTO();
    dto.setIdLocalidad(currentLocalidadEntity.getIdLocalidad());
    dto.setNombreLocalidad(currentLocalidadEntity.getNombreLocalidad());

    return dto;
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
