package com.parkings.parkingsApi.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pago")
public class PagoEntity {

  @Id
  @Column(name = "k_id_pago")
  private String idPago;

  @Column(name = "n_forma")
  @Pattern(
    regexp = "^(EFECTIVO|TARJETA|TRANSFERENCIA)$",
    message = "La forma de pago solo puede ser EFECTIVO, TARJETA o TRANSFERENCIA"
  )
  private String formaPago;

  @Column(name = "v_valor_pagado")
  private BigDecimal valorPagado;

  @Column(name = "f_h_pago")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
  private LocalDateTime fechaHoraPago;

  @Column(name = "k_id_registro")
  private UUID idRegistro;

  @Column(name = "k_placa")
  @Pattern(
    regexp = "^[A-Z]{3}\\d{2,3}[A-Z]?$",
    message = "La placa debe tener tres letras mayúsculas seguidas de dos o tres dígitos y, opcionalmente, una letra mayúscula"
  )
  private String placa;
}
