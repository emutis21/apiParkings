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
@Table(name = "localidad")
public class LocalidadEntity {

  @Id
  @Column(name = "k_id_localidad")
  private String idLocalidad;

  @Column(name = "n_nombre_localidad")
  private String nombreLocalidad;
}
