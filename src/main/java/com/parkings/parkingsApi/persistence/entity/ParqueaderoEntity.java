package com.parkings.parkingsApi.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "parqueadero")
public class ParqueaderoEntity {

  @Id
  @Column(name = "k_id_parqueadero")
  private String idParqueadero;

  @Column(name = "n_direccion")
  private String direccion;

  @Column(name = "is_disponible")
  private boolean disponible;

  @Column(name = "k_id_localidad")
  private String idLocalidad;
}
