package com.parkings.parkingsApi.persistence.dao.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IVigilanteEntity;
import com.parkings.parkingsApi.persistence.entity.CompositeId;
import com.parkings.parkingsApi.persistence.entity.VigilanteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class VigilanteDaoImpl implements IVigilanteEntity {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<VigilanteEntity> findAll() {
    @SuppressWarnings("unchecked")
    List<VigilanteEntity> results = (List<VigilanteEntity>) this.em.createQuery(
        "SELECT v FROM VigilanteEntity v"
      )
      .getResultList();
    return results;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<VigilanteEntity> findById(CompositeId id) {
    return Optional.ofNullable(this.em.find(VigilanteEntity.class, id));
  }

  @Override
  @Transactional
  public void saveVigilante(VigilanteEntity vigilanteEntity) {
    this.em.persist(vigilanteEntity);
    this.em.flush();
  }

  @Override
  @Transactional
  public void updateVigilante(VigilanteEntity vigilanteEntity) {
    this.em.merge(vigilanteEntity);
  }

  @Override
  @Transactional
  public void deleteVigilante(VigilanteEntity vigilanteEntity) {
    this.em.remove(vigilanteEntity);
  }
}
