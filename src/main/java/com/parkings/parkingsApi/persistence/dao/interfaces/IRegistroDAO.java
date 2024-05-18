package com.parkings.parkingsApi.persistence.dao.interfaces;

import com.parkings.parkingsApi.persistence.entity.RegistroEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRegistroDAO {
  List<RegistroEntity> findAll();

  Optional<RegistroEntity> findById(UUID idRegistro);

  void saveRegistro(RegistroEntity registroEntity);

  void updateRegistro(RegistroEntity registroEntity);

  void deleteRegistro(RegistroEntity registroEntity);
}
