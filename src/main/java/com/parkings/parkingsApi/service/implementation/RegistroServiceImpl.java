package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IEspacioDAO;
import com.parkings.parkingsApi.persistence.dao.interfaces.IRegistroDAO;
import com.parkings.parkingsApi.persistence.entity.EspacioEntity;
import com.parkings.parkingsApi.persistence.entity.RegistroEntity;
import com.parkings.parkingsApi.presentation.dto.RegistroDTO;
import com.parkings.parkingsApi.service.interfaces.IRegistroService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroServiceImpl implements IRegistroService {

  @Autowired
  private IRegistroDAO registroDAO;

  @Autowired
  private IEspacioDAO espacioDAO;

  @Override
  public List<RegistroDTO> findAll() {
    return this.registroDAO.findAll()
      .stream()
      .map(entity -> {
        RegistroDTO dto = new RegistroDTO();
        dto.setIdRegistro(entity.getIdRegistro());
        dto.setPlaca(entity.getPlaca());
        dto.setFechaHoraEntrada(entity.getFechaHoraEntrada());
        dto.setFechaHoraSalida(entity.getFechaHoraSalida());
        dto.setTipoVehiculo(entity.getTipoVehiculo());
        dto.setIdEspacio(entity.getIdEspacio());
        dto.setIdArea(entity.getIdArea());

        return dto;
      })
      .collect(Collectors.toList());
  }

  @Override
  public RegistroDTO findById(UUID idRegistro) {
    if (idRegistro == null || idRegistro.toString().isEmpty()) {
      throw new IllegalArgumentException(
        "El identificador del registro no puede ser nulo o vacío"
      );
    }

    Optional<RegistroEntity> registroEntity =
      this.registroDAO.findById(idRegistro);

    if (!registroEntity.isPresent()) return new RegistroDTO();

    RegistroEntity currentRegistroEntity = registroEntity.get();

    RegistroDTO dto = new RegistroDTO();

    dto.setIdRegistro(currentRegistroEntity.getIdRegistro());
    dto.setPlaca(currentRegistroEntity.getPlaca());
    dto.setFechaHoraEntrada(currentRegistroEntity.getFechaHoraEntrada());
    dto.setFechaHoraSalida(currentRegistroEntity.getFechaHoraSalida());
    dto.setTipoVehiculo(currentRegistroEntity.getTipoVehiculo());
    dto.setIdEspacio(currentRegistroEntity.getIdEspacio());
    dto.setIdArea(currentRegistroEntity.getIdArea());

    return dto;
  }

  @Override
  public RegistroDTO createRegistro(RegistroDTO registroDTO) {
    try {
      RegistroEntity registroEntity = new RegistroEntity();
      registroEntity.setPlaca(registroDTO.getPlaca());
      registroEntity.setFechaHoraSalida(registroDTO.getFechaHoraSalida());
      registroEntity.setTipoVehiculo(registroDTO.getTipoVehiculo());
      registroEntity.setIdEspacio(registroDTO.getIdEspacio());
      registroEntity.setIdArea(registroDTO.getIdArea());

      Optional<EspacioEntity> espacioEntityOptional =
        this.espacioDAO.findById(Long.valueOf(registroDTO.getIdEspacio()));

      if (!espacioEntityOptional.isPresent()) {
        throw new IllegalArgumentException(
          "El espacio que intenta asignar al registro no existe"
        );
      }

      EspacioEntity espacioEntity = espacioEntityOptional.get();
      espacioEntity.setDisponible(false);
      this.espacioDAO.updateEspacio(espacioEntity);

      UUID idRegistro = UUID.randomUUID();
      registroEntity.setIdRegistro(idRegistro);
      this.registroDAO.saveRegistro(registroEntity);

      RegistroDTO savedRegistroDTO = new RegistroDTO();
      savedRegistroDTO.setIdRegistro(registroEntity.getIdRegistro());
      savedRegistroDTO.setPlaca(registroEntity.getPlaca());
      savedRegistroDTO.setFechaHoraEntrada(
        registroEntity.getFechaHoraEntrada()
      );
      savedRegistroDTO.setFechaHoraSalida(registroEntity.getFechaHoraSalida());
      savedRegistroDTO.setTipoVehiculo(registroEntity.getTipoVehiculo());
      savedRegistroDTO.setIdEspacio(registroEntity.getIdEspacio());
      savedRegistroDTO.setIdArea(registroEntity.getIdArea());

      return savedRegistroDTO;
    } catch (Exception e) {
      e.printStackTrace();
      throw new UnsupportedOperationException(
        "Ocurrió un error al intentar crear el registro" + e.getMessage(),
        e
      );
    }
  }

  @Override
  public RegistroDTO updateRegistro(UUID idRegistro, RegistroDTO registroDTO) {
    Optional<RegistroEntity> registroEntity =
      this.registroDAO.findById(idRegistro);

    if (!registroEntity.isPresent()) {
      throw new IllegalArgumentException(
        "El registro que intenta actualizar no existe"
      );
    }

    RegistroEntity currentRegistroEntity = registroEntity.get();

    currentRegistroEntity.setFechaHoraSalida(registroDTO.getFechaHoraSalida());

    this.registroDAO.saveRegistro(currentRegistroEntity);

    RegistroDTO dto = new RegistroDTO();
    dto.setIdRegistro(currentRegistroEntity.getIdRegistro());
    dto.setPlaca(currentRegistroEntity.getPlaca());
    dto.setFechaHoraEntrada(currentRegistroEntity.getFechaHoraEntrada());
    dto.setFechaHoraSalida(currentRegistroEntity.getFechaHoraSalida());
    dto.setTipoVehiculo(currentRegistroEntity.getTipoVehiculo());
    dto.setIdEspacio(currentRegistroEntity.getIdEspacio());
    dto.setIdArea(currentRegistroEntity.getIdArea());

    return dto;
  }

  @Override
  public String deleteRegistro(UUID idRegistro) {
    Optional<RegistroEntity> registroEntity =
      this.registroDAO.findById(idRegistro);

    if (!registroEntity.isPresent()) {
      throw new IllegalArgumentException(
        "El registro que intenta eliminar no existe"
      );
    }

    RegistroEntity currentRegistroEntity = registroEntity.get();

    this.registroDAO.deleteRegistro(currentRegistroEntity);

    return "Registro " + idRegistro + " eliminado";
  }
}
