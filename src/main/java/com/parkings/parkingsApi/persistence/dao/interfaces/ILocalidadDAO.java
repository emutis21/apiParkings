package com.parkings.parkingsApi.persistence.dao.interfaces;

import com.parkings.parkingsApi.persistence.entity.LocalidadEntity;
import java.util.List;
import java.util.Optional;

public interface ILocalidadDAO {
  List<LocalidadEntity> findAll();

  Optional<LocalidadEntity> findById(String idLocalidad);

  void saveLocalidad(LocalidadEntity localidadEntity);

  void updateLocalidad(LocalidadEntity localidadEntity);

  void deleteLocalidad(LocalidadEntity localidadEntity);
}
