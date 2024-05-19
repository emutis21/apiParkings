package com.parkings.parkingsApi.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import java.sql.Date;
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
@Table(name = "tarifa")
public class TarifaEntity {

  @Id
  @Column(name = "k_id_tarifa")
  private String idTarifa;

  @Column(name = "n_tipo_vehiculo")
  @Pattern(
    regexp = "^(BICICLETA|MOTOCICLETA|AUTOMOVIL)$",
    message = "El tipo de vehículo solo puede ser BICICLETA, MOTOCICLETA o AUTOMOVIL"
  )
  private String tipoVehiculo;

  @Column(name = "f_vigencia")
  private Date vigencia;

  @Column(name = "v_valor")
  private double valor;

  @Column(name = "f_factor_tarifa")
  private double factorTarifa;
}
