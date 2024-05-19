package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.ITarifaDAO;
import com.parkings.parkingsApi.persistence.entity.TarifaEntity;
import com.parkings.parkingsApi.presentation.dto.TarifaDTO;
import com.parkings.parkingsApi.service.interfaces.ITarifaService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarifaServiceImpl implements ITarifaService {

  @Autowired
  private ITarifaDAO tarifaDAO;

  @Override
  public List<TarifaDTO> findAll() {
    ModelMapper modelMapper = new ModelMapper();

    return this.tarifaDAO.findAll()
      .stream()
      .map(entity -> modelMapper.map(entity, TarifaDTO.class))
      .collect(Collectors.toList());
  }

  @Override
  public TarifaDTO findById(String idTarifa) {
    Optional<TarifaEntity> tarifaEntity = this.tarifaDAO.findById(idTarifa);

    if (!tarifaEntity.isPresent()) return new TarifaDTO();

    ModelMapper modelMapper = new ModelMapper();

    TarifaEntity currentTarifaEntity = tarifaEntity.get();

    return modelMapper.map(currentTarifaEntity, TarifaDTO.class);
  }

  @Override
  public TarifaDTO createTarifa(TarifaDTO tarifaDTO) {
    try {
      ModelMapper modelMapper = new ModelMapper();

      TarifaEntity tarifaEntity = modelMapper.map(
        tarifaDTO,
        TarifaEntity.class
      );

      this.tarifaDAO.saveTarifa(tarifaEntity);

      return tarifaDTO;
    } catch (Exception e) {
      throw new UnsupportedOperationException(
        "Error al crear la tarifa: " + e.getMessage(),
        e
      );
    }
  }

  @Override
  public TarifaDTO updateTarifa(String idTarifa, TarifaDTO tarifaDTO) {
    Optional<TarifaEntity> tarifaEntity = this.tarifaDAO.findById(idTarifa);

    if (!tarifaEntity.isPresent()) throw new IllegalArgumentException(
      "La tarifa no existe"
    );

    TarifaEntity currentTarifaEntity = tarifaEntity.get();

    currentTarifaEntity.setValor(tarifaDTO.getValor());
    currentTarifaEntity.setFactorTarifa(tarifaDTO.getFactorTarifa());

    this.tarifaDAO.updateTarifa(currentTarifaEntity);

    ModelMapper modelMapper = new ModelMapper();

    return modelMapper.map(currentTarifaEntity, TarifaDTO.class);
  }

  @Override
  public String deleteTarifa(String idTarifa) {
    Optional<TarifaEntity> tarifaEntity = this.tarifaDAO.findById(idTarifa);

    if (!tarifaEntity.isPresent()) return "La tarifa no existe";

    TarifaEntity currentTarifaEntity = tarifaEntity.get();

    this.tarifaDAO.deleteTarifa(currentTarifaEntity);

    return "Tarifa " + idTarifa + " eliminada";
  }
}
