package co.edu.uniandes.dse.ZZZ.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.entities.PqrsEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ZZZ.repositories.PqrsRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PqrsService  {

    @Autowired
    PqrsRepository pqrsRepository;

    //crear pqrs
    public PqrsEntity createPqrs(PqrsEntity pqrs) throws IllegalOperationException {
        log.info("Iniciando creación de PQRS: {}", pqrs.getTexto());

        if (pqrs.getTexto() == null || pqrs.getTexto().isEmpty()) {
            throw new IllegalOperationException("El texto de la PQRS no puede estar vacío.");
        }

        PqrsEntity nuevaPqrs = pqrsRepository.save(pqrs);
        log.info("PQRS creada con ID: {}", nuevaPqrs.getId());
        return nuevaPqrs;
    }

    //obtener todas las pqrs
    public List<PqrsEntity> getPqrs() {
        log.info("Obteniendo todas las PQRS");
        return pqrsRepository.findAll();
    }

    //obtener pqrs por id
    public PqrsEntity getPqrs(Long id) throws EntityNotFoundException {
        log.info("Buscando PQRS con ID: {}", id);
        Optional<PqrsEntity> pqrs = pqrsRepository.findById(id);

        if (pqrs.isEmpty()) {
            throw new EntityNotFoundException("La PQRS con ID " + id + " no existe.");
        }

        return pqrs.get();
    }

    //actualizar pqrs
    public PqrsEntity updatePqrs(Long id, PqrsEntity pqrsActualizado) throws EntityNotFoundException {
        log.info("Actualizando PQRS con ID: {}", id);

        PqrsEntity existente = pqrsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La PQRS con ID " + id + " no existe."));

        existente.setTexto(pqrsActualizado.getTexto());
        existente.setTipo(pqrsActualizado.getTipo());

        return pqrsRepository.save(existente);
    }

    //eliminar pqrs
    public void deletePqrs(Long id) throws EntityNotFoundException {
        log.info("Eliminando PQRS con ID: {}", id);

        PqrsEntity pqrs = pqrsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La PQRS con ID " + id + " no existe."));

        pqrsRepository.delete(pqrs);
    }



}
