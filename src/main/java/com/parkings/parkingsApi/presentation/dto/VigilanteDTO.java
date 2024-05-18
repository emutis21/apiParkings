package com.parkings.parkingsApi.presentation.dto;

import com.parkings.parkingsApi.persistence.entity.CompositeId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VigilanteDTO {

  private CompositeId id;
  private String apellido1;
  private String apellido2;
  private Boolean hasPerro;
  private String idArea;
  private String nombre1;
  private String nombre2;
}
