package com.parkings.parkingsApi.service.interfaces;

import com.parkings.parkingsApi.presentation.dto.AreaDTO;
import java.util.List;

public interface IAreaService {
  List<AreaDTO> findAll();

  AreaDTO findById(String idArea);

  AreaDTO createArea(AreaDTO areaDTO);

  AreaDTO updateArea(String idArea, AreaDTO areaDTO);

  String deleteArea(String idArea);
}
