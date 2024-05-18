package com.parkings.parkingsApi.persistence.dao.interfaces;

import com.parkings.parkingsApi.persistence.entity.CompositeId;
import com.parkings.parkingsApi.persistence.entity.VigilanteEntity;
import java.util.List;
import java.util.Optional;

public interface IVigilanteEntity {
  List<VigilanteEntity> findAll();

  Optional<VigilanteEntity> findById(CompositeId id);

  void saveVigilante(VigilanteEntity vigilanteEntity);

  void updateVigilante(VigilanteEntity vigilanteEntity);

  void deleteVigilante(VigilanteEntity vigilanteEntity);
}
