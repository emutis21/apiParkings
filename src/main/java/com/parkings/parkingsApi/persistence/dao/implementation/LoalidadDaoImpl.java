package com.parkings.parkingsApi.persistence.dao.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.ILocalidadDAO;
import com.parkings.parkingsApi.persistence.entity.LocalidadEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LoalidadDaoImpl implements ILocalidadDAO {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<LocalidadEntity> findAll() {
    @SuppressWarnings("unchecked")
    List<LocalidadEntity> results = (List<LocalidadEntity>) this.em.createQuery(
        "SELECT l FROM LocalidadEntity l"
      )
      .getResultList();
    return results;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<LocalidadEntity> findById(String idLocalidad) {
    return Optional.ofNullable(
      this.em.find(LocalidadEntity.class, idLocalidad)
    );
  }

  @Override
  @Transactional
  public void saveLocalidad(LocalidadEntity localidadEntity) {
    this.em.persist(localidadEntity);
    this.em.flush();
  }

  @Override
  @Transactional
  public void updateLocalidad(LocalidadEntity localidadEntity) {
    this.em.merge(localidadEntity);
  }

  @Override
  @Transactional
  public void deleteLocalidad(LocalidadEntity localidadEntity) {
    this.em.remove(localidadEntity);
  }
}
