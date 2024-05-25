package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IEspacioDAO;
import com.parkings.parkingsApi.persistence.entity.EspacioEntity;
import com.parkings.parkingsApi.presentation.dto.EspacioDTO;
import com.parkings.parkingsApi.service.interfaces.IEspacioService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspacioServiceImpl implements IEspacioService {

  @Autowired
  private IEspacioDAO espacioDAO;

  @Override
  public List<EspacioDTO> findAll() {
    return this.espacioDAO.findAll()
      .stream()
      .map(entity -> {
        EspacioDTO dto = new EspacioDTO();
        dto.setIdEspacio(entity.getIdEspacio());
        dto.setDisponible(entity.isDisponible());
        dto.setIdArea(entity.getIdArea());

        return dto;
      })
      .collect(Collectors.toList());
  }

  @Override
  public EspacioDTO findById(Long idEspacio) {
    if (idEspacio == null || idEspacio <= 0) {
      throw new IllegalArgumentException(
        "El identificador del espacio no puede ser nulo o vacío"
      );
    }

    Long idEspacioLong = Long.valueOf(idEspacio);

    Optional<EspacioEntity> espacioEntity =
      this.espacioDAO.findById(idEspacioLong);

    if (!espacioEntity.isPresent()) return new EspacioDTO();

    EspacioEntity currentEspacioEntity = espacioEntity.get();

    EspacioDTO dto = new EspacioDTO();

    dto.setIdEspacio(currentEspacioEntity.getIdEspacio());
    dto.setDisponible(currentEspacioEntity.isDisponible());
    dto.setIdArea(currentEspacioEntity.getIdArea());

    return dto;
  }

  @Override
  public List<EspacioDTO> findAllByArea(String idArea) {
    return this.espacioDAO.findAll()
      .stream()
      .filter(entity -> entity.getIdArea().equals(idArea))
      .map(entity -> {
        EspacioDTO dto = new EspacioDTO();
        dto.setIdEspacio(entity.getIdEspacio());
        dto.setDisponible(entity.isDisponible());
        dto.setIdArea(entity.getIdArea());

        return dto;
      })
      .collect(Collectors.toList());
  }

  @Override
  public EspacioDTO createEspacio(EspacioDTO espacioDTO) {
    try {
      EspacioEntity espacioEntity = new EspacioEntity();
      espacioEntity.setDisponible(espacioDTO.isDisponible());
      espacioEntity.setIdArea(espacioDTO.getIdArea());

      EspacioEntity savedEspacioEntity =
        this.espacioDAO.saveEspacio(espacioEntity);

      EspacioDTO dto = new EspacioDTO();
      dto.setIdEspacio(savedEspacioEntity.getIdEspacio());
      dto.setDisponible(savedEspacioEntity.isDisponible());
      dto.setIdArea(savedEspacioEntity.getIdArea());

      return dto;
    } catch (Exception e) {
      throw new UnsupportedOperationException(
        "Ha ocurrido un error al intentar crear el espacio " + e.getMessage()
      );
    }
  }

  @Override
  public EspacioDTO updateEspacio(Long idEspacio, EspacioDTO espacioDTO) {
    Optional<EspacioEntity> currentEspacio =
      this.espacioDAO.findById(idEspacio);

    if (!currentEspacio.isPresent()) throw new IllegalArgumentException(
      "El espacio no existe"
    );

    EspacioEntity currentEspacioEntity = currentEspacio.get();

    currentEspacioEntity.setDisponible(espacioDTO.isDisponible());

    this.espacioDAO.updateEspacio(currentEspacioEntity);

    EspacioDTO dto = new EspacioDTO();
    dto.setIdEspacio(currentEspacioEntity.getIdEspacio());
    dto.setDisponible(currentEspacioEntity.isDisponible());
    dto.setIdArea(currentEspacioEntity.getIdArea());

    return dto;
  }

  @Override
  public String deleteEspacio(Long idEspacio) {
    Optional<EspacioEntity> localidadEntity =
      this.espacioDAO.findById(idEspacio);

    if (!localidadEntity.isPresent()) return "El espacio no existe";

    EspacioEntity currentLocalidadEntity = localidadEntity.get();

    this.espacioDAO.deleteEspacio(currentLocalidadEntity);

    return "Espacio con id " + idEspacio + " eliminado";
  }
}
