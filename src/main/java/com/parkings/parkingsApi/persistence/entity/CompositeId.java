package com.parkings.parkingsApi.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CompositeId implements Serializable {

  @Column(name = "k_num_id")
  private Long numId;

  @Column(name = "k_tipo_id")
  private String tipoId;

  public CompositeId() {}

  public CompositeId(Long numId, String tipoId) {
    this.numId = numId;
    this.tipoId = tipoId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CompositeId that = (CompositeId) o;
    return (
      Objects.equals(numId, that.numId) && Objects.equals(tipoId, that.tipoId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(numId, tipoId);
  }
}
