package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IEspacioDAO;
import com.parkings.parkingsApi.persistence.entity.EspacioEntity;
import com.parkings.parkingsApi.presentation.dto.EspacioDTO;
import com.parkings.parkingsApi.service.interfaces.IEspacioService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspacioServiceImpl implements IEspacioService {

  @Autowired
  private IEspacioDAO espacioDAO;

  @Override
  public List<EspacioDTO> findAll() {
    ModelMapper modelMapper = new ModelMapper();

    return this.espacioDAO.findAll()
      .stream()
      .map(entity -> modelMapper.map(entity, EspacioDTO.class))
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

    ModelMapper modelMapper = new ModelMapper();

    EspacioEntity currentEspacio = espacioEntity.get();

    return modelMapper.map(currentEspacio, EspacioDTO.class);
  }

  @Override
  public EspacioDTO createEspacio(EspacioDTO espacioDTO) {
    try {
      ModelMapper modelMapper = new ModelMapper();

      EspacioEntity espacioEntity = modelMapper.map(
        espacioDTO,
        EspacioEntity.class
      );

      EspacioEntity savedEspacioEntity =
        this.espacioDAO.saveEspacio(espacioEntity);

      return modelMapper.map(savedEspacioEntity, EspacioDTO.class);
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

    ModelMapper modelMapper = new ModelMapper();

    return modelMapper.map(currentEspacioEntity, EspacioDTO.class);
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
