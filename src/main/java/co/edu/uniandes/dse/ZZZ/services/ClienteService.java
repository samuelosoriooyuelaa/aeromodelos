package co.edu.uniandes.dse.ZZZ.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.entities.ClienteEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ZZZ.repositories.ClienteRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    //crear cliente
    public ClienteEntity createCliente(ClienteEntity cliente) throws IllegalOperationException {
        log.info("Iniciando creación de cliente: {}", cliente.getNombre());

        if (cliente.getCorreo() == null || cliente.getCorreo().isEmpty()) {
            throw new IllegalOperationException("El correo del cliente no puede estar vacío.");
        }

        ClienteEntity nuevoCliente = clienteRepository.save(cliente);
        log.info("Cliente creado con ID: {}", nuevoCliente.getId());
        return nuevoCliente;
    }

    //obtener los clientes
    public List<ClienteEntity> getClientes() {
        log.info("Obteniendo todos los clientes");
        return clienteRepository.findAll();
    }

    //obtener cliente por id
    public ClienteEntity getCliente(Long id) throws EntityNotFoundException {
        log.info("Buscando cliente con ID: {}", id);
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            throw new EntityNotFoundException("El cliente con ID " + id + " no existe.");
        }

        return cliente.get();
    }

    //actualizar cliente
    public ClienteEntity updateCliente(Long id, ClienteEntity clienteActualizado) throws EntityNotFoundException {
        log.info("Actualizando cliente con ID: {}", id);

        ClienteEntity existente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El cliente con ID " + id + " no existe."));

       
        existente.setNombre(clienteActualizado.getNombre());
        existente.setApellido(clienteActualizado.getApellido());
        existente.setCorreo(clienteActualizado.getCorreo());

        return clienteRepository.save(existente);
    }

    //eliminar cliente
    public void deleteCliente(Long id) throws EntityNotFoundException, IllegalOperationException {
        log.info("Eliminando cliente con ID: {}", id);

        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El cliente con ID " + id + " no existe."));

        if (!cliente.getComentarios().isEmpty()) {
            throw new IllegalOperationException("El cliente tiene comentarios asociados y no puede ser eliminado.");
        }

        if (!cliente.getPqrs().isEmpty()) {
            throw new IllegalOperationException("El cliente tiene PQRS asociados y no puede ser eliminado.");
        }

        clienteRepository.delete(cliente);
    }







}
