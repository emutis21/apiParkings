package com.parkings.parkingsApi.persistence.dao.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IRegistroDAO;
import com.parkings.parkingsApi.persistence.entity.RegistroEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RegistroDaoImpl implements IRegistroDAO {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<RegistroEntity> findAll() {
    @SuppressWarnings("unchecked")
    List<RegistroEntity> results = (List<RegistroEntity>) this.em.createQuery(
        "SELECT r FROM RegistroEntity r"
      )
      .getResultList();
    return results;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<RegistroEntity> findById(UUID idRegistro) {
    return Optional.ofNullable(this.em.find(RegistroEntity.class, idRegistro));
  }

  @Override
  @Transactional
  public RegistroEntity saveRegistro(RegistroEntity registroEntity) {
    this.em.persist(registroEntity);
    this.em.flush();
    return registroEntity;
  }

  @Override
  @Transactional
  public void updateRegistro(RegistroEntity registroEntity) {
    this.em.merge(registroEntity);
  }

  @Override
  @Transactional
  public void deleteRegistro(RegistroEntity registroEntity) {
    this.em.remove(registroEntity);
  }
}
