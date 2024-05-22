package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.ITarifaDAO;
import com.parkings.parkingsApi.persistence.entity.TarifaEntity;
import com.parkings.parkingsApi.presentation.dto.TarifaDTO;
import com.parkings.parkingsApi.service.interfaces.ITarifaService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarifaServiceImpl implements ITarifaService {

  @Autowired
  private ITarifaDAO tarifaDAO;

  @Override
  public List<TarifaDTO> findAll() {
    return this.tarifaDAO.findAll()
      .stream()
      .map(entity -> {
        TarifaDTO dto = new TarifaDTO();
        dto.setIdTarifa(entity.getIdTarifa());
        dto.setTipoVehiculo(entity.getTipoVehiculo());
        dto.setVigencia(entity.getVigencia());
        dto.setValor(entity.getValor());
        dto.setFactorTarifa(entity.getFactorTarifa());

        return dto;
      })
      .collect(Collectors.toList());
  }

  @Override
  public TarifaDTO findById(String idTarifa) {
    Optional<TarifaEntity> tarifaEntity = this.tarifaDAO.findById(idTarifa);

    if (!tarifaEntity.isPresent()) return new TarifaDTO();

    TarifaEntity currentTarifaEntity = tarifaEntity.get();

    TarifaDTO dto = new TarifaDTO();
    dto.setIdTarifa(currentTarifaEntity.getIdTarifa());
    dto.setTipoVehiculo(currentTarifaEntity.getTipoVehiculo());
    dto.setVigencia(currentTarifaEntity.getVigencia());
    dto.setValor(currentTarifaEntity.getValor());
    dto.setFactorTarifa(currentTarifaEntity.getFactorTarifa());

    return dto;
  }

  @Override
  public TarifaDTO createTarifa(TarifaDTO tarifaDTO) {
    try {
      TarifaEntity tarifaEntity = new TarifaEntity();
      tarifaEntity.setIdTarifa(tarifaDTO.getIdTarifa());
      tarifaEntity.setTipoVehiculo(tarifaDTO.getTipoVehiculo());
      tarifaEntity.setVigencia(tarifaDTO.getVigencia());
      tarifaEntity.setValor(tarifaDTO.getValor());
      tarifaEntity.setFactorTarifa(tarifaDTO.getFactorTarifa());

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

    TarifaDTO dto = new TarifaDTO();
    dto.setIdTarifa(currentTarifaEntity.getIdTarifa());
    dto.setTipoVehiculo(currentTarifaEntity.getTipoVehiculo());
    dto.setVigencia(currentTarifaEntity.getVigencia());
    dto.setValor(currentTarifaEntity.getValor());
    dto.setFactorTarifa(currentTarifaEntity.getFactorTarifa());

    return dto;
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
