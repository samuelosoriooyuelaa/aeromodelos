package co.edu.uniandes.dse.ZZZ.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.entities.ElementoEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ZZZ.repositories.ElementoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ElementoService {

    @Autowired
    private ElementoRepository elementoRepository;

    //crear un elemento
    public ElementoEntity createElemento(ElementoEntity elemento) throws IllegalOperationException {
        log.info("Iniciando creación de elemento: {}", elemento.getNombre());

       
        if (elementoRepository.findByNombreElementoIgnoreCase(elemento.getNombre()).isPresent()) {
            throw new IllegalOperationException("Ya existe un elemento con ese nombre.");
        }

        ElementoEntity nuevoElemento = elementoRepository.save(elemento);
        log.info("Elemento creado con ID: {}", nuevoElemento.getId());
        return nuevoElemento;
    }

    //obtener todos los elementos
    public List<ElementoEntity> getElementos() {
        log.info("Obteniendo todos los elementos");
        return elementoRepository.findAll();
    }

    //obtener elemento por id
    public ElementoEntity getElemento(Long id) throws EntityNotFoundException {
        log.info("Buscando elemento con ID: {}", id);
        Optional<ElementoEntity> elemento = elementoRepository.findById(id);

        if (elemento.isEmpty()) {
            throw new EntityNotFoundException("El elemento con ID " + id + " no existe.");
        }

        return elemento.get();
    }

    //actualizar elemento
    public ElementoEntity updateElemento(Long id, ElementoEntity elementoActualizado) throws EntityNotFoundException {
        log.info("Actualizando elemento con ID: {}", id);

        ElementoEntity existente = elementoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El elemento con ID " + id + " no existe."));

        
        existente.setNombre(elementoActualizado.getNombre());
        existente.setPrecio(elementoActualizado.getPrecio());
        existente.setDescripcion(elementoActualizado.getDescripcion());
        existente.setTipo(elementoActualizado.getTipo());
        existente.setCaracteristica(elementoActualizado.getCaracteristica());
        existente.setUrlImagen(elementoActualizado.getUrlImagen());
        existente.setDisponibilidad(elementoActualizado.getDisponibilidad());

        return elementoRepository.save(existente);
    }

    //eliminar elemento
    public void deleteElemento(Long id) throws EntityNotFoundException, IllegalOperationException {
        log.info("Eliminando elemento con ID: {}", id);

        ElementoEntity elemento = elementoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El elemento con ID " + id + " no existe."));

       
        if (!elemento.getComentarios().isEmpty()) {
            throw new IllegalOperationException("No se puede eliminar el elemento con ID " + id + " porque tiene comentarios asociados.");
        }

       
        elementoRepository.delete(elemento);
    }
    









}
