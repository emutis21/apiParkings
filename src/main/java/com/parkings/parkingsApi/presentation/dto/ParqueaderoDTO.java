package com.parkings.parkingsApi.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParqueaderoDTO {

  private String idParqueadero;

  private String direccion;

  private boolean disponible;

  private String idLocalidad;
}
