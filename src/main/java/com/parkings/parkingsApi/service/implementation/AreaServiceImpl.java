package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IAreaDAO;
import com.parkings.parkingsApi.persistence.entity.AreaEntity;
import com.parkings.parkingsApi.presentation.dto.AreaDTO;
import com.parkings.parkingsApi.service.interfaces.IAreaService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl implements IAreaService {

  @Autowired
  private IAreaDAO areaDAO;

  @Override
  public List<AreaDTO> findAll() {
    ModelMapper modelMapper = new ModelMapper();

    return this.areaDAO.findAll()
      .stream()
      .map(entity -> modelMapper.map(entity, AreaDTO.class))
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

    ModelMapper modelMapper = new ModelMapper();

    AreaEntity currentArea = areaEntity.get();

    return modelMapper.map(currentArea, AreaDTO.class);
  }

  @Override
  public AreaDTO createArea(AreaDTO areaDTO) {
    try {
      ModelMapper modelMapper = new ModelMapper();

      AreaEntity areaEntity = modelMapper.map(areaDTO, AreaEntity.class);

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

    AreaEntity currentArea = areaEntity.get();

    currentArea.setDescripcion(areaDTO.getDescripcion());

    this.areaDAO.saveArea(currentArea);

    ModelMapper modelMapper = new ModelMapper();

    return modelMapper.map(currentArea, AreaDTO.class);
  }

  @Override
  public String deleteArea(String idArea) {
    Optional<AreaEntity> areaEntity = this.areaDAO.findById(idArea);

    if (!areaEntity.isPresent()) throw new IllegalArgumentException(
      "El área no existe"
    );

    AreaEntity currentArea = areaEntity.get();

    this.areaDAO.deleteArea(currentArea);

    return "Área eliminada " + idArea + " eliminada";
  }
}
