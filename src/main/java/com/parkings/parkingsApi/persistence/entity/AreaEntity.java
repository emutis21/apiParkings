package com.parkings.parkingsApi.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "area")
public class AreaEntity {

  @Id
  @Column(name = "k_id_area")
  private String idArea;

  @Column(name = "n_descripcion")
  private String descripcion;

  @Column(name = "n_tipo")
  @Pattern(
    regexp = "^(AUTOMOVIL|MOTOCICLETA|BICICLETA)$",
    message = "El tipo de vehículo solo puede ser AUTOMOVIL, MOTOCICLETA o BICICLETA"
  )
  private String tipo;

  @Column(name = "k_id_parqueadero")
  private String idParqueadero;
}
