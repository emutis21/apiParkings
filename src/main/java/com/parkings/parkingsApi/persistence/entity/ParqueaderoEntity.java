package com.parkings.parkingsApi.persistence.entity;

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
  private String k_id_parqueadero;

  private String n_direccion;
  private boolean is_disponible;
  private String k_id_localidad;
}
