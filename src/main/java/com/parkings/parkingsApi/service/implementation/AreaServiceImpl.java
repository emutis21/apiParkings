package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IAreaDAO;
import com.parkings.parkingsApi.persistence.entity.AreaEntity;
import com.parkings.parkingsApi.presentation.dto.AreaDTO;
import com.parkings.parkingsApi.service.interfaces.IAreaService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl implements IAreaService {

  @Autowired
  private IAreaDAO areaDAO;

  @Override
  public List<AreaDTO> findAll() {
    return this.areaDAO.findAll()
      .stream()
      .map(entity -> {
        AreaDTO dto = new AreaDTO();
        dto.setIdArea(entity.getIdArea());
        dto.setDescripcion(entity.getDescripcion());
        dto.setTipo(entity.getTipo());
        dto.setIdParqueadero(entity.getIdParqueadero());

        return dto;
      })
      .collect(Collectors.toList());
  }

  @Override
  public AreaDTO findById(String idArea) {
    if (idArea == null || idArea.trim().isEmpty()) {
      throw new IllegalArgumentException(
        "El identificador del área no puede ser nulo o vacío"
      );
    }

    Optional<AreaEntity> areaEntity = this.areaDAO.findById(idArea);

    if (!areaEntity.isPresent()) return new AreaDTO();

    AreaEntity currentAreaEntity = areaEntity.get();

    AreaDTO dto = new AreaDTO();

    dto.setIdArea(currentAreaEntity.getIdArea());
    dto.setDescripcion(currentAreaEntity.getDescripcion());
    dto.setTipo(currentAreaEntity.getTipo());
    dto.setIdParqueadero(currentAreaEntity.getIdParqueadero());

    return dto;
  }

  @Override
  public List<AreaDTO> findAllByParqueadero(String idParqueadero) {
    return this.areaDAO.findAll()
      .stream()
      .filter(entity -> entity.getIdParqueadero().equals(idParqueadero))
      .map(entity -> {
        AreaDTO dto = new AreaDTO();
        dto.setIdArea(entity.getIdArea());
        dto.setDescripcion(entity.getDescripcion());
        dto.setTipo(entity.getTipo());
        dto.setIdParqueadero(entity.getIdParqueadero());

        return dto;
      })
      .collect(Collectors.toList());
  }

  @Override
  public AreaDTO createArea(AreaDTO areaDTO) {
    try {
      AreaEntity areaEntity = new AreaEntity();
      areaEntity.setIdArea(areaDTO.getIdArea());
      areaEntity.setDescripcion(areaDTO.getDescripcion());
      areaEntity.setTipo(areaDTO.getTipo());
      areaEntity.setIdParqueadero(areaDTO.getIdParqueadero());

      this.areaDAO.saveArea(areaEntity);

      return areaDTO;
    } catch (Exception e) {
      throw new UnsupportedOperationException(
        "No se pudo crear el área" + e.getMessage()
      );
    }
  }

  @Override
  public AreaDTO updateArea(String idArea, AreaDTO areaDTO) {
    Optional<AreaEntity> areaEntity = this.areaDAO.findById(idArea);

    if (!areaEntity.isPresent()) throw new IllegalArgumentException(
      "El área no existe"
    );

    AreaEntity currentAreaEntity = areaEntity.get();

    currentAreaEntity.setDescripcion(areaDTO.getDescripcion());

    this.areaDAO.saveArea(currentAreaEntity);

    AreaDTO dto = new AreaDTO();
    dto.setIdArea(currentAreaEntity.getIdArea());
    dto.setDescripcion(currentAreaEntity.getDescripcion());
    dto.setTipo(currentAreaEntity.getTipo());
    dto.setIdParqueadero(currentAreaEntity.getIdParqueadero());

    return dto;
  }

  @Override
  public String deleteArea(String idArea) {
    Optional<AreaEntity> areaEntity = this.areaDAO.findById(idArea);

    if (!areaEntity.isPresent()) throw new IllegalArgumentException(
      "El área no existe"
    );

    AreaEntity currentAreaEntity = areaEntity.get();

    this.areaDAO.deleteArea(currentAreaEntity);

    return "Área eliminada " + idArea + " eliminada";
  }
}
