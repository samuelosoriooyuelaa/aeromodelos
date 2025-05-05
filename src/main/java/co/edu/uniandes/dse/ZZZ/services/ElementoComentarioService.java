package co.edu.uniandes.dse.ZZZ.services;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.ZZZ.entities.ComentarioEntity;
import co.edu.uniandes.dse.ZZZ.entities.ElementoEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.repositories.ComentarioRepository;
import co.edu.uniandes.dse.ZZZ.repositories.ElementoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ElementoComentarioService {

    @Autowired
    private ElementoRepository elementoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;


    //agregar comentario a elemento
    public ComentarioEntity addComentario(Long elementoId, Long comentarioId) throws EntityNotFoundException {
        log.info("Asociando comentario {} al elemento {}", comentarioId, elementoId);

        ElementoEntity elemento = elementoRepository.findById(elementoId)
            .orElseThrow(() -> new EntityNotFoundException("Elemento no encontrado"));

        ComentarioEntity comentario = comentarioRepository.findById(comentarioId)
            .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado"));

        comentario.setElemento(elemento);
        return comentarioRepository.save(comentario);
    }

    //obtener comentarios de un elemento
    public List<ComentarioEntity> getComentarios(Long elementoId) throws EntityNotFoundException {
        log.info("Obteniendo comentarios del elemento {}", elementoId);

        ElementoEntity elemento = elementoRepository.findById(elementoId)
            .orElseThrow(() -> new EntityNotFoundException("Elemento no encontrado"));

        return elemento.getComentarios();
    }

    //obtener comentario de un elemento por id
    public ComentarioEntity getComentario(Long elementoId, Long comentarioId) throws EntityNotFoundException {
        log.info("Buscando comentario {} en el elemento {}", comentarioId, elementoId);

        ElementoEntity elemento = elementoRepository.findById(elementoId)
            .orElseThrow(() -> new EntityNotFoundException("Elemento no encontrado"));

        ComentarioEntity comentario = comentarioRepository.findById(comentarioId)
            .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado"));

        if (!comentario.getElemento().getId().equals(elemento.getId())) {
            throw new EntityNotFoundException("El comentario no pertenece al elemento");
        }

        return comentario;
    }

    //eliminar comentario de un elemento
    public void removeComentario(Long elementoId, Long comentarioId) throws EntityNotFoundException {
        log.info("Removiendo comentario {} del elemento {}", comentarioId, elementoId);

        ComentarioEntity comentario = getComentario(elementoId, comentarioId);
        comentario.setElemento(null);
        comentarioRepository.save(comentario);
    }

    









}
