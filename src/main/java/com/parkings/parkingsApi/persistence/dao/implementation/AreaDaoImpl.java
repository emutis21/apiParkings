package com.parkings.parkingsApi.persistence.dao.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IAreaDAO;
import com.parkings.parkingsApi.persistence.entity.AreaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AreaDaoImpl implements IAreaDAO {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<AreaEntity> findAll() {
    @SuppressWarnings("unchecked")
    List<AreaEntity> results = (List<AreaEntity>) this.em.createQuery(
        "SELECT a FROM AreaEntity a"
      )
      .getResultList();
    return results;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<AreaEntity> findById(String idArea) {
    return Optional.ofNullable(this.em.find(AreaEntity.class, idArea));
  }

  @Override
  @Transactional
  public void saveArea(AreaEntity areaEntity) {
    this.em.persist(areaEntity);
    this.em.flush();
  }

  @Override
  @Transactional
  public void updateArea(AreaEntity areaEntity) {
    this.em.merge(areaEntity);
  }

  @Override
  @Transactional
  public void deleteArea(AreaEntity areaEntity) {
    this.em.remove(areaEntity);
  }
}
