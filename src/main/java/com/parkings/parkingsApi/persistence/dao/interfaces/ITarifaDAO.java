package com.parkings.parkingsApi.persistence.dao.interfaces;

import com.parkings.parkingsApi.persistence.entity.TarifaEntity;
import java.util.List;
import java.util.Optional;

public interface ITarifaDAO {
  List<TarifaEntity> findAll();

  Optional<TarifaEntity> findById(String idTarifa);

  void saveTarifa(TarifaEntity tarifaEntidad);

  void updateTarifa(TarifaEntity tarifaEntidad);

  void deleteTarifa(TarifaEntity tarifaEntidad);
}
