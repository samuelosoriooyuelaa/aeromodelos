package co.edu.uniandes.dse.ZZZ.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.entities.ModeloEntity;
import co.edu.uniandes.dse.ZZZ.entities.CategoriaEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ZZZ.repositories.ModeloRepository;
import co.edu.uniandes.dse.ZZZ.repositories.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ModeloCategoriaService {

    @Autowired
	private ModeloRepository modeloRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;



    //asignar categoria a modelo
    public CategoriaEntity addCategoria(Long modeloId, Long categoriaId) throws EntityNotFoundException {
        ModeloEntity modelo = modeloRepository.findById(modeloId)
            .orElseThrow(() -> new EntityNotFoundException("Modelo no encontrado"));
    
        CategoriaEntity categoria = categoriaRepository.findById(categoriaId)
            .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));
    
        modelo.setCategoria(categoria);
        return categoria;
    }

    //obtener la categoria de un modelo
    public CategoriaEntity getCategoria(Long modeloId) throws EntityNotFoundException {
        ModeloEntity modelo = modeloRepository.findById(modeloId)
            .orElseThrow(() -> new EntityNotFoundException("Modelo no encontrado"));
    
        return modelo.getCategoria();
    }


    //eliminar categoria de un modelo
    public void removeCategoria(Long modeloId) throws EntityNotFoundException {
        ModeloEntity modelo = modeloRepository.findById(modeloId)
            .orElseThrow(() -> new EntityNotFoundException("Modelo no encontrado"));
    
        modelo.setCategoria(null);
    }
    

}
