package com.parkings.parkingsApi.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspacioDTO {

  private Long idEspacio;

  private boolean disponible;

  private String idArea;
}
