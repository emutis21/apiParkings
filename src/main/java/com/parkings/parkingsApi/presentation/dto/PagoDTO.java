package com.parkings.parkingsApi.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {

  private String idPago;
  private String formaPago;
  private BigDecimal valorPagado;

  @Column(name = "f_h_pago")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
  private LocalDateTime fechaHoraPago;

  private UUID idRegistro;

  private String placa;
}
