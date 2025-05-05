package co.edu.uniandes.dse.ZZZ.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.entities.ComentarioEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ZZZ.repositories.ComentarioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ComentarioService {

  @Autowired
  ComentarioRepository comentarioRepository;


  //crear comentario
  public ComentarioEntity createComentario(ComentarioEntity comentarioEntity) {
    log.info("Creando comentario");
    return comentarioRepository.save(comentarioEntity);
}

//obtener todos los comentarios 
public List<ComentarioEntity> getComentarios() {
    log.info("Obteniendo todos los comentarios");
    return comentarioRepository.findAll();
}

//obtener comentario por id
public ComentarioEntity getComentario(Long id) throws EntityNotFoundException {
    log.info("Buscando comentario con id = {}", id);
    return comentarioRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("El comentario con ID " + id + " no existe."));
}

//actualizar comentario 
public ComentarioEntity updateComentario(Long id, ComentarioEntity comentarioEntity) throws EntityNotFoundException {
    log.info("Actualizando comentario con id = {}", id);
    ComentarioEntity existing = comentarioRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("El comentario con ID " + id + " no existe."));
    
    comentarioEntity.setId(id);
    return comentarioRepository.save(comentarioEntity);
}

//eliminar comentario
public void deleteComentario(Long id) throws EntityNotFoundException {
    log.info("Eliminando comentario con id = {}", id);
    ComentarioEntity comentarioEntity = comentarioRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("El comentario con ID " + id + " no existe."));
    
    comentarioRepository.delete(comentarioEntity);
}





}
