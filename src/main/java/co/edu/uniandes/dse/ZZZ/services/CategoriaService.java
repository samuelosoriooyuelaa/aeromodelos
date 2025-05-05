package co.edu.uniandes.dse.ZZZ.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.entities.CategoriaEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ZZZ.repositories.CategoriaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    //crear categoria
    public CategoriaEntity createCategoria(CategoriaEntity categoria) throws IllegalOperationException {
        log.info("Iniciando creación de categoría: {}", categoria.getNombreCategoria());

        // Evitar categorías duplicadas por nombre
        if (categoriaRepository.findByNombreCategoriaIgnoreCase(categoria.getNombreCategoria()).isPresent()) {
            throw new IllegalOperationException("Ya existe una categoría con ese nombre.");
        }

        CategoriaEntity nuevaCategoria = categoriaRepository.save(categoria);
        log.info("Categoría creada con ID: {}", nuevaCategoria.getId());
        return nuevaCategoria;
    }

    //obtener todas las categorias
    public List<CategoriaEntity> getCategorias() {
        log.info("Obteniendo todas las categorías");
        return categoriaRepository.findAll();
    }
    
    //obtener categoria por id
    public CategoriaEntity getCategoria(Long id) throws EntityNotFoundException {
        log.info("Buscando categoría con ID: {}", id);
        Optional<CategoriaEntity> categoria = categoriaRepository.findById(id);

        if (categoria.isEmpty()) {
            throw new EntityNotFoundException("La categoría con ID " + id + " no existe.");
        }

        return categoria.get();
    }

    //actualizar categoria 
    public CategoriaEntity updateCategoria(Long id, CategoriaEntity categoriaActualizada) throws EntityNotFoundException {
        log.info("Actualizando categoría con ID: {}", id);

        CategoriaEntity existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La categoría con ID " + id + " no existe."));

        existente.setNombreCategoria(categoriaActualizada.getNombreCategoria());
        existente.setDescripcionCategoria(categoriaActualizada.getDescripcionCategoria());

        return categoriaRepository.save(existente);
    }

    //eliminar categoria
    public void deleteCategoria(Long id) throws EntityNotFoundException, IllegalOperationException {
        log.info("Eliminando categoría con ID: {}", id);

        
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La categoría con ID " + id + " no existe."));

       
        if (!categoria.getModelos().isEmpty()) {
            throw new IllegalOperationException("No se puede eliminar la categoría con ID " + id + " porque tiene modelos asociados.");
        }

        categoriaRepository.delete(categoria);
    }

}
