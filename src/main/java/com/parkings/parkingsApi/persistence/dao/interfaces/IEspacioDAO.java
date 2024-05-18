package com.parkings.parkingsApi.persistence.dao.interfaces;

import com.parkings.parkingsApi.persistence.entity.EspacioEntity;
import java.util.List;
import java.util.Optional;

public interface IEspacioDAO {
  List<EspacioEntity> findAll();

  Optional<EspacioEntity> findById(Long idEspacio);

  EspacioEntity saveEspacio(EspacioEntity espacioEntity);

  void updateEspacio(EspacioEntity espacioEntity);

  void deleteEspacio(EspacioEntity espacioEntity);
}
