package com.parkings.parkingsApi.service.implementation;

import com.parkings.parkingsApi.persistence.dao.interfaces.IPagoDAO;
import com.parkings.parkingsApi.persistence.dao.interfaces.IRegistroDAO;
import com.parkings.parkingsApi.persistence.entity.PagoEntity;
import com.parkings.parkingsApi.persistence.entity.RegistroEntity;
import com.parkings.parkingsApi.persistence.entity.TarifaEntity;
import com.parkings.parkingsApi.presentation.dto.PagoDTO;
import com.parkings.parkingsApi.service.interfaces.IPagoService;
import com.parkings.parkingsApi.service.interfaces.ITarifaService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoServiceImpl implements IPagoService {

  @Autowired
  private IPagoDAO pagoDAO;

  @Autowired
  private IRegistroDAO registroDAO;

  @Autowired
  private ITarifaService tarifaService;

  @Override
  public List<PagoDTO> findAll() {
    ModelMapper modelMapper = new ModelMapper();

    return this.pagoDAO.findAll()
      .stream()
      .map(entity -> modelMapper.map(entity, PagoDTO.class))
      .collect(Collectors.toList());
  }

  @Override
  public PagoDTO findById(String idPago) {
    if (idPago == null || idPago.toString().isEmpty()) {
      throw new IllegalArgumentException(
        "El identificador del pago no puede ser nulo o vacío"
      );
    }

    Optional<PagoEntity> pagoEntity = this.pagoDAO.findById(idPago);

    if (!pagoEntity.isPresent()) return new PagoDTO();

    ModelMapper modelMapper = new ModelMapper();

    PagoEntity currentPagoEntity = pagoEntity.get();

    return modelMapper.map(currentPagoEntity, PagoDTO.class);
  }

  @Override
  public PagoDTO createPago(PagoDTO pagoDTO) {
    try {
      ModelMapper modelMapper = new ModelMapper();

      PagoEntity pagoEntity = modelMapper.map(pagoDTO, PagoEntity.class);

      this.pagoDAO.savePago(pagoEntity);

      // TODO: Implementar lógica para calcular el valor del pago

      Optional<RegistroEntity> registroEntity =
        this.registroDAO.findById(pagoDTO.getIdRegistro());
      if (registroEntity.isPresent()) {
        RegistroEntity registro = registroEntity.get();
        registro.setFechaHoraSalida(pagoDTO.getFechaHoraPago());
        this.registroDAO.updateRegistro(registro);
      }

      return modelMapper.map(pagoEntity, PagoDTO.class);
    } catch (Exception e) {
      throw new IllegalArgumentException(
        "Ha ocurrido un error al intentar crear el pago" + e.getCause()
      );
    }
  }

  @Override
  public PagoDTO updatePago(String idPago, PagoDTO pagoDTO) {
    if (idPago == null || idPago.toString().isEmpty()) {
      throw new IllegalArgumentException(
        "El identificador del pago no puede ser nulo o vacío"
      );
    }

    Optional<PagoEntity> currentPagoEntity = this.pagoDAO.findById(idPago);

    if (!currentPagoEntity.isPresent()) return new PagoDTO();

    ModelMapper modelMapper = new ModelMapper();

    if (pagoDTO.getFormaPago() != null) {
      currentPagoEntity.get().setFormaPago(pagoDTO.getFormaPago());
    }

    this.pagoDAO.updatePago(currentPagoEntity.get());

    return modelMapper.map(currentPagoEntity.get(), PagoDTO.class);
  }

  @Override
  public String deletePago(String idPago) {
    Optional<PagoEntity> pagoEntity = this.pagoDAO.findById(idPago);

    if (idPago == null || idPago.toString().isEmpty()) {
      throw new IllegalArgumentException(
        "El identificador del pago no puede ser nulo o vacío"
      );
    }

    PagoEntity currentPagoEntity = pagoEntity.get();

    this.pagoDAO.deletePago(currentPagoEntity);

    return "Pago " + idPago + " eliminado correctamente";
  }
}
