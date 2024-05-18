package com.parkings.parkingsApi.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO {

  private UUID idRegistro;
  private String placa;

  @Column(name = "f_h_entrada")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
  private LocalDateTime fechaHoraEntrada;

  @Column(name = "f_h_salida")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
  private LocalDateTime fechaHoraSalida;

  private String tipoVehiculo;
  private int idEspacio;
  private String idArea;
}
