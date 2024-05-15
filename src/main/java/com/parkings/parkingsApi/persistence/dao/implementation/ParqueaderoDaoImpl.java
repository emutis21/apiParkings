package com.parkings.parkingsApi.persistence.dao.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IParqueaderoDAO;
import com.parkings.parkingsApi.persistence.entity.ParqueaderoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ParqueaderoDaoImpl implements IParqueaderoDAO {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<ParqueaderoEntity> findAll() {
    @SuppressWarnings("unchecked")
    List<ParqueaderoEntity> results = (List<ParqueaderoEntity>) this.em.createQuery(
        "SELECT p FROM ParqueaderoEntity p"
      )
      .getResultList();
    return results;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<ParqueaderoEntity> findById(String idParqueadero) {
    return Optional.ofNullable(
      this.em.find(ParqueaderoEntity.class, idParqueadero)
    );
  }

  @Override
  @Transactional
  public void saveParqueadero(ParqueaderoEntity parqueaderoEntity) {
    this.em.persist(parqueaderoEntity);
    this.em.flush();
  }

  @Override
  @Transactional
  public void updateParqueadero(ParqueaderoEntity parqueaderoEntity) {
    this.em.merge(parqueaderoEntity);
  }

  @Override
  @Transactional
  public void deleteParqueadero(ParqueaderoEntity parqueaderoEntity) {
    this.em.remove(parqueaderoEntity);
  }
}
