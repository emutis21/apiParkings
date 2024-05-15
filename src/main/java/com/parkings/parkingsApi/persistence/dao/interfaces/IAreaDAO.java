package com.parkings.parkingsApi.persistence.dao.interfaces;

import com.parkings.parkingsApi.persistence.entity.AreaEntity;
import java.util.List;
import java.util.Optional;

public interface IAreaDAO {
  List<AreaEntity> findAll();

  Optional<AreaEntity> findById(String idArea);

  void saveArea(AreaEntity areaEntity);

  void updateArea(AreaEntity areaEntity);

  void deleteArea(AreaEntity areaEntity);
}
