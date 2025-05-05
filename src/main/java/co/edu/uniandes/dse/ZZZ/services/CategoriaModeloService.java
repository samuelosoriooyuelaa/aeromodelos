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

public class CategoriaModeloService {
    @Autowired
	private ModeloRepository modeloRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;



    //obtener los modelos de una categoria
    public List<ModeloEntity> getModelos(Long categoriaId) throws EntityNotFoundException {
        CategoriaEntity categoria = categoriaRepository.findById(categoriaId)
            .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));
    
        return categoria.getModelos();
    }

}
