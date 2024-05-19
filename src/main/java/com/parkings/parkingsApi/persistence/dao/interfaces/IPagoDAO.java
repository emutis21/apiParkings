package com.parkings.parkingsApi.persistence.dao.interfaces;

import com.parkings.parkingsApi.persistence.entity.PagoEntity;
import java.util.List;
import java.util.Optional;

public interface IPagoDAO {
  List<PagoEntity> findAll();

  Optional<PagoEntity> findById(String idPago);

  void savePago(PagoEntity pagoEntity);

  void updatePago(PagoEntity pagoEntity);

  void deletePago(PagoEntity pagoEntity);
}
