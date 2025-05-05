package co.edu.uniandes.dse.ZZZ.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.entities.PqrsEntity;
import co.edu.uniandes.dse.ZZZ.entities.ClienteEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ZZZ.repositories.PqrsRepository;
import co.edu.uniandes.dse.ZZZ.repositories.ClienteRepository;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class ClientePqrsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PqrsRepository pqrsRepository;

    //obtener las pqrs de un cliente
    public List<PqrsEntity> getPqrs(Long clienteId) throws EntityNotFoundException {
        log.info("Buscando PQRS del cliente con id={}", clienteId);
        ClienteEntity cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        return cliente.getPqrs();
    }

    //obtener una pqrs especifica de un cliente
    public PqrsEntity getPqrs(Long clienteId, Long pqrsId) throws EntityNotFoundException {
        log.info("Buscando PQRS con id={} del cliente con id={}", pqrsId, clienteId);
        ClienteEntity cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        return cliente.getPqrs().stream()
            .filter(p -> p.getId().equals(pqrsId))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException("PQRS no asociada al cliente"));
    }

    //asignar pqrs existente a un cliente
    public PqrsEntity addPqrs(Long clienteId, Long pqrsId) throws EntityNotFoundException {
        log.info("Asignando PQRS con id={} al cliente con id={}", pqrsId, clienteId);
        ClienteEntity cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
        PqrsEntity pqrs = pqrsRepository.findById(pqrsId)
            .orElseThrow(() -> new EntityNotFoundException("PQRS no encontrada"));

        pqrs.setCliente(cliente);
        return pqrsRepository.save(pqrs);
    }

    //eliminar pqrs de un cliente
    public void removePqrs(Long clienteId, Long pqrsId) throws EntityNotFoundException {
        log.info("Eliminando relación entre cliente id={} y PQRS id={}", clienteId, pqrsId);
        PqrsEntity pqrs = getPqrs(clienteId, pqrsId);
        pqrs.setCliente(null);
        pqrsRepository.save(pqrs);
    }

}
