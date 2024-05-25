package com.parkings.parkingsApi.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "registro")
public class RegistroEntity {

  @Id
  @Column(name = "k_id_registro")
  private UUID idRegistro;

  @Column(name = "k_placa")
  @Pattern(
    regexp = "^[A-Z]{3}\\d{2,3}[A-Z]?$",
    message = "La placa debe tener tres letras mayúsculas seguidas de dos o tres dígitos y, opcionalmente, una letra mayúscula"
  )
  private String placa;

  @Column(name = "f_h_entrada", updatable = false)
  @CreationTimestamp
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
  private LocalDateTime fechaHoraEntrada;

  @Column(name = "f_h_salida")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
  private LocalDateTime fechaHoraSalida;

  @Column(name = "n_tipo_vehiculo")
  private String tipoVehiculo;

  @Column(name = "k_id_espacio")
  private int idEspacio;

  @Column(name = "k_id_area")
  private String idArea;
}
