package com.parkings.parkingsApi.presentation.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarifaDTO {

  private String idTarifa;

  private String tipoVehiculo;

  private Date vigencia;

  private double valor;

  private double factorTarifa;
}
