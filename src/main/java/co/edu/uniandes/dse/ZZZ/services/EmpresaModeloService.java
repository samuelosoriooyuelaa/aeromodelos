package co.edu.uniandes.dse.ZZZ.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.entities.ModeloEntity;
import co.edu.uniandes.dse.ZZZ.entities.EmpresaEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ZZZ.repositories.ModeloRepository;
import co.edu.uniandes.dse.ZZZ.repositories.EmpresaRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EmpresaModeloService {

    @Autowired
	private ModeloRepository modeloRepository;

	@Autowired
	private EmpresaRepository empresaRepository;



    //agregar modelo a empresa
    public ModeloEntity addModelo(Long empresaId, Long modeloId) throws EntityNotFoundException {
    EmpresaEntity empresa = empresaRepository.findById(empresaId)
        .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada"));

    ModeloEntity modelo = modeloRepository.findById(modeloId)
        .orElseThrow(() -> new EntityNotFoundException("Modelo no encontrado"));

    modelo.setEmpresa(empresa);
    return modelo;
}

//obtener todos los modelos de una empresa
public List<ModeloEntity> getModelos(Long empresaId) throws EntityNotFoundException {
    EmpresaEntity empresa = empresaRepository.findById(empresaId)
        .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada"));

    return empresa.getModelos();
}

//obtener un modelo especifico  de la empresa
public ModeloEntity getModelo(Long empresaId, Long modeloId) throws EntityNotFoundException {
    EmpresaEntity empresa = empresaRepository.findById(empresaId)
        .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada"));

    return empresa.getModelos().stream()
        .filter(m -> m.getId().equals(modeloId))
        .findFirst()
        .orElseThrow(() -> new EntityNotFoundException("El modelo no está asociado a la empresa"));
}


//reemplazar modelos de una empresa por otros
public List<ModeloEntity> replaceModelos(Long empresaId, List<ModeloEntity> nuevosModelos) throws EntityNotFoundException {
    EmpresaEntity empresa = empresaRepository.findById(empresaId)
        .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada"));

    for (ModeloEntity modelo : nuevosModelos) {
        modelo.setEmpresa(empresa);
    }

    empresa.setModelos(nuevosModelos);
    return nuevosModelos;
}


//eliminar modelo de una empresa
public void removeModelo(Long empresaId, Long modeloId) throws EntityNotFoundException {
    EmpresaEntity empresa = empresaRepository.findById(empresaId)
        .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada"));

    ModeloEntity modelo = modeloRepository.findById(modeloId)
        .orElseThrow(() -> new EntityNotFoundException("Modelo no encontrado"));

    if (!empresa.getModelos().contains(modelo)) {
        throw new EntityNotFoundException("El modelo no está asociado a la empresa");
    }

    empresa.getModelos().remove(modelo);
    modelo.setEmpresa(null);
}




}
