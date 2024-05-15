package com.parkings.parkingsApi.persistence.dao.interfaces;

import com.parkings.parkingsApi.persistence.entity.ParqueaderoEntity;
import java.util.List;
import java.util.Optional;

public interface IParqueaderoDAO {
  List<ParqueaderoEntity> findAll();

  Optional<ParqueaderoEntity> findById(String idParqueadero);

  void saveParqueadero(ParqueaderoEntity parqueaderoEntity);

  void updateParqueadero(ParqueaderoEntity parqueaderoEntity);

  void deleteParqueadero(ParqueaderoEntity parqueaderoEntity);
}
