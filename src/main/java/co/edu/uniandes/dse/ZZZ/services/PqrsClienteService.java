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
public class PqrsClienteService {

    @Autowired
    private PqrsRepository pqrsRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    //obtener cliente de un pqrs
    public ClienteEntity getCliente(Long pqrsId) throws EntityNotFoundException {
        log.info("Obteniendo cliente de la PQRS con id={}", pqrsId);
        PqrsEntity pqrs = pqrsRepository.findById(pqrsId)
            .orElseThrow(() -> new EntityNotFoundException("PQRS no encontrada"));

        ClienteEntity cliente = pqrs.getCliente();
        if (cliente == null) {
            throw new EntityNotFoundException("La PQRS no tiene un cliente asociado");
        }

        return cliente;
    }

    //asignar cliente a una pqrs
    public ClienteEntity addCliente(Long pqrsId, Long clienteId) throws EntityNotFoundException {
        log.info("Asociando cliente con id={} a la PQRS con id={}", clienteId, pqrsId);
        PqrsEntity pqrs = pqrsRepository.findById(pqrsId)
            .orElseThrow(() -> new EntityNotFoundException("PQRS no encontrada"));

        ClienteEntity cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        pqrs.setCliente(cliente);
        pqrsRepository.save(pqrs);

        return cliente;
    }

    //eliminar pqrs de un cliente
    public void removeCliente(Long pqrsId) throws EntityNotFoundException {
        log.info("Eliminando cliente asociado a la PQRS con id={}", pqrsId);
        PqrsEntity pqrs = pqrsRepository.findById(pqrsId)
            .orElseThrow(() -> new EntityNotFoundException("PQRS no encontrada"));

        if (pqrs.getCliente() == null) {
            throw new EntityNotFoundException("La PQRS no tiene un cliente asociado");
        }

        pqrs.setCliente(null);
        pqrsRepository.save(pqrs);
    }
}

