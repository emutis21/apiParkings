package com.parkings.parkingsApi.persistence.dao.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IEspacioDAO;
import com.parkings.parkingsApi.persistence.entity.EspacioEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EspacioDaoImpl implements IEspacioDAO {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<EspacioEntity> findAll() {
    @SuppressWarnings("unchecked")
    List<EspacioEntity> results = (List<EspacioEntity>) this.em.createQuery(
        "SELECT a FROM EspacioEntity a"
      )
      .getResultList();
    return results;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<EspacioEntity> findById(Long idEspacio) {
    return Optional.ofNullable(this.em.find(EspacioEntity.class, idEspacio));
  }

  @Override
  @Transactional
  public EspacioEntity saveEspacio(EspacioEntity espacioEntity) {
    this.em.persist(espacioEntity);
    this.em.flush();
    return espacioEntity;
  }

  @Override
  @Transactional
  public void updateEspacio(EspacioEntity espacioEntity) {
    this.em.merge(espacioEntity);
  }

  @Override
  @Transactional
  public void deleteEspacio(EspacioEntity espacioEntity) {
    this.em.remove(espacioEntity);
  }
}
