package com.parkings.parkingsApi.persistence.dao.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.ITarifaDAO;
import com.parkings.parkingsApi.persistence.entity.TarifaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TarifaDaoImpl implements ITarifaDAO {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<TarifaEntity> findAll() {
    @SuppressWarnings("unchecked")
    List<TarifaEntity> results = (List<TarifaEntity>) this.em.createQuery(
        "SELECT t FROM TarifaEntity t"
      )
      .getResultList();
    return results;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<TarifaEntity> findById(String idTarifa) {
    return Optional.ofNullable(this.em.find(TarifaEntity.class, idTarifa));
  }

  @Override
  @Transactional
  public void saveTarifa(TarifaEntity tarifaEntidad) {
    this.em.persist(tarifaEntidad);
    this.em.flush();
  }

  @Override
  @Transactional
  public void updateTarifa(TarifaEntity tarifaEntidad) {
    this.em.merge(tarifaEntidad);
  }

  @Override
  @Transactional
  public void deleteTarifa(TarifaEntity tarifaEntidad) {
    this.em.remove(tarifaEntidad);
  }
}
