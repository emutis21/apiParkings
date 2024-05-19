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
import org.modelmapper.ModelMapper;
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
    ModelMapper modelMapper = new ModelMapper();

    return this.registroDAO.findAll()
      .stream()
      .map(entity -> modelMapper.map(entity, RegistroDTO.class))
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

    ModelMapper modelMapper = new ModelMapper();

    RegistroEntity currentRegistroEntity = registroEntity.get();

    return modelMapper.map(currentRegistroEntity, RegistroDTO.class);
  }

  @Override
  public RegistroDTO createRegistro(RegistroDTO registroDTO) {
    try {
      ModelMapper modelMapper = new ModelMapper();

      RegistroEntity registroEntity = modelMapper.map(
        registroDTO,
        RegistroEntity.class
      );

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

      return registroDTO;
    } catch (Exception e) {
      e.printStackTrace();
      throw new UnsupportedOperationException(
        "Ocurrió un error al intentar crear el registro",
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

    ModelMapper modelMapper = new ModelMapper();

    return modelMapper.map(currentRegistroEntity, RegistroDTO.class);
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
