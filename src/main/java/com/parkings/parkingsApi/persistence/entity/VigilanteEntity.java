package com.parkings.parkingsApi.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "vigilante")
public class VigilanteEntity {

  @EmbeddedId
  private CompositeId id;

  @Column(name = "n_apellido1")
  private String apellido1;

  @Column(name = "n_apellido2")
  private String apellido2;

  @Column(name = "has_perro")
  private Boolean hasPerro;

  @Column(name = "k_id_area")
  private String idArea;

  @Column(name = "n_nombre1")
  private String nombre1;

  @Column(name = "n_nombre2")
  private String nombre2;
}
