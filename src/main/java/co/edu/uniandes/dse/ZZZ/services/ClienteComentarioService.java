package co.edu.uniandes.dse.ZZZ.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.entities.ComentarioEntity;
import co.edu.uniandes.dse.ZZZ.entities.ClienteEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ZZZ.repositories.ComentarioRepository;
import co.edu.uniandes.dse.ZZZ.repositories.ClienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    //asocia comentario a cliente
    public ComentarioEntity addComentario(Long clienteId, Long comentarioId) throws EntityNotFoundException {
        log.info("Asociando comentario {} al cliente {}", comentarioId, clienteId);

        ComentarioEntity comentario = comentarioRepository.findById(comentarioId)
            .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado"));

        ClienteEntity cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        comentario.setCliente(cliente);
        return comentarioRepository.save(comentario);
    }


    //obtener los comentarios de un cliente 
    public List<ComentarioEntity> getComentarios(Long clienteId) throws EntityNotFoundException {
        log.info("Obteniendo comentarios del cliente {}", clienteId);

        ClienteEntity cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        return cliente.getComentarios();
    }


    //obtener comentario especifico de un cliente
    public ComentarioEntity getComentario(Long clienteId, Long comentarioId) throws EntityNotFoundException {
        log.info("Obteniendo comentario {} del cliente {}", comentarioId, clienteId);
        List<ComentarioEntity> comentarios = getComentarios(clienteId);

        return comentarios.stream()
            .filter(c -> c.getId().equals(comentarioId))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException("Comentario no asociado al cliente"));
    }

    //elimina un comentario de un cliente
    public void removeComentario(Long clienteId, Long comentarioId) throws EntityNotFoundException {
        log.info("Eliminando comentario {} del cliente {}", comentarioId, clienteId);

        ComentarioEntity comentario = comentarioRepository.findById(comentarioId)
            .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado"));

        if (comentario.getCliente() == null || !comentario.getCliente().getId().equals(clienteId)) {
            throw new EntityNotFoundException("Comentario no asociado al cliente");
        }

        comentario.setCliente(null);
        comentarioRepository.save(comentario);
    }

}
