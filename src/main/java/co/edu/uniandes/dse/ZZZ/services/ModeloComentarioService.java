package co.edu.uniandes.dse.ZZZ.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.entities.ModeloEntity;
import co.edu.uniandes.dse.ZZZ.entities.ComentarioEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ZZZ.repositories.ModeloRepository;
import co.edu.uniandes.dse.ZZZ.repositories.ComentarioRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ModeloComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ModeloRepository modeloRepository;



    //agregar comentario a modelo
    public ComentarioEntity addComentario(Long modeloId, Long comentarioId) throws EntityNotFoundException {
        log.info("Asociando comentario {} al modelo {}", comentarioId, modeloId);

        ModeloEntity modelo = modeloRepository.findById(modeloId)
            .orElseThrow(() -> new EntityNotFoundException("Modelo no encontrado"));

        ComentarioEntity comentario = comentarioRepository.findById(comentarioId)
            .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado"));

        comentario.setModelo(modelo);
        return comentarioRepository.save(comentario);
    }

    //obtener comentarios de un modelo
    public List<ComentarioEntity> getComentarios(Long modeloId) throws EntityNotFoundException {
        ModeloEntity modelo = modeloRepository.findById(modeloId)
            .orElseThrow(() -> new EntityNotFoundException("Modelo no encontrado"));
        return modelo.getListaComentarios();
    }


    //obtener un comentario de un modelo
    public ComentarioEntity getComentario(Long modeloId, Long comentarioId) throws EntityNotFoundException {
        
        ModeloEntity modelo = modeloRepository.findById(modeloId)
            .orElseThrow(() -> new EntityNotFoundException("El modelo no existe"));
    
        ComentarioEntity comentario = comentarioRepository.findById(comentarioId)
            .orElseThrow(() -> new EntityNotFoundException("El comentario no existe"));
    
        if (comentario.getModelo() == null || !comentario.getModelo().getId().equals(modeloId)) {
            throw new EntityNotFoundException("El comentario no está asociado al modelo");
        }
    
        return comentario;
    }
    

    //reemplazar comentarios de un modelo por otros
    public List<ComentarioEntity> replaceComentarios(Long modeloId, List<ComentarioEntity> nuevos) throws EntityNotFoundException {
        ModeloEntity modelo = modeloRepository.findById(modeloId)
            .orElseThrow(() -> new EntityNotFoundException("Modelo no encontrado"));

        for (ComentarioEntity comentario : modelo.getListaComentarios()) {
            comentario.setModelo(null); 
        }

        for (ComentarioEntity nuevo : nuevos) {
            nuevo.setModelo(modelo);
        }

        modelo.setListaComentarios(nuevos);
        modeloRepository.save(modelo);

        return nuevos;
    }

    //eliminar comentario de un modelo
    public void removeComentario(Long modeloId, Long comentarioId) throws EntityNotFoundException {
        ComentarioEntity comentario = getComentario(modeloId, comentarioId);
        comentario.setModelo(null);
        comentarioRepository.save(comentario);
    }


}
