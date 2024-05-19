package com.parkings.parkingsApi.persistence.dao.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IPagoDAO;
import com.parkings.parkingsApi.persistence.entity.PagoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PagoDaoImpl implements IPagoDAO {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<PagoEntity> findAll() {
    @SuppressWarnings("unchecked")
    List<PagoEntity> results = (List<PagoEntity>) this.em.createQuery(
        "SELECT p FROM PagoEntity p"
      )
      .getResultList();
    return results;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<PagoEntity> findById(String idPago) {
    return Optional.ofNullable(this.em.find(PagoEntity.class, idPago));
  }

  @Override
  @Transactional
  public void savePago(PagoEntity pagoEntity) {
    this.em.persist(pagoEntity);
    this.em.flush();
  }

  @Override
  @Transactional
  public void updatePago(PagoEntity pagoEntity) {
    this.em.merge(pagoEntity);
  }

  @Override
  @Transactional
  public void deletePago(PagoEntity pagoEntity) {
    this.em.remove(pagoEntity);
  }
}
