package co.edu.uniandes.dse.ZZZ.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.entities.ModeloEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ZZZ.repositories.ModeloRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;


    //crear modelo
    public ModeloEntity createModelo(ModeloEntity modeloEntity) {
        log.info("Creando modelo");
        return modeloRepository.save(modeloEntity);
    }

    //obtener todos los modelos
    public List<ModeloEntity> getModelos() {
        log.info("Consultando todos los modelos");
        return modeloRepository.findAll();
    }

    //obtener modelo por id
    public ModeloEntity getModelo(Long id) throws EntityNotFoundException {
        log.info("Buscando modelo con id = {}", id);
        return modeloRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("El modelo con ID " + id + " no existe."));
    }

    //actualizar un modelo
    public ModeloEntity updateModelo(Long id, ModeloEntity modeloEntity) throws EntityNotFoundException {
        log.info("Actualizando modelo con id = {}", id);
        ModeloEntity existing = modeloRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("El modelo con ID " + id + " no existe."));

        modeloEntity.setId(id);
        return modeloRepository.save(modeloEntity);
    }

    //eliminar un modelo
    public void deleteModelo(Long id) throws EntityNotFoundException {
        log.info("Eliminando modelo con id = {}", id);
        ModeloEntity modeloEntity = modeloRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("El modelo con ID " + id + " no existe."));
        
        modeloRepository.delete(modeloEntity);
    }



}
