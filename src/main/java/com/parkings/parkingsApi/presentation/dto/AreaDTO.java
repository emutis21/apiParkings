package com.parkings.parkingsApi.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaDTO {

  private String idArea;

  private String descripcion;

  private String tipo;

  private String idParqueadero;
}
