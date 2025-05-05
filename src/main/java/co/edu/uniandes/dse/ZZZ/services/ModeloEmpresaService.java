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
public class ModeloEmpresaService {

    @Autowired
	private ModeloRepository modeloRepository;

	@Autowired
	private EmpresaRepository empresaRepository;


    //asignar empresa a un modelo
    public EmpresaEntity addEmpresa(Long modeloId, Long empresaId) throws EntityNotFoundException {
    ModeloEntity modelo = modeloRepository.findById(modeloId)
        .orElseThrow(() -> new EntityNotFoundException("Modelo no encontrado"));

    EmpresaEntity empresa = empresaRepository.findById(empresaId)
        .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada"));

    modelo.setEmpresa(empresa);
    return empresa;
}

//obtener la empresa de un modelo
public EmpresaEntity getEmpresa(Long modeloId) throws EntityNotFoundException {
    ModeloEntity modelo = modeloRepository.findById(modeloId)
        .orElseThrow(() -> new EntityNotFoundException("Modelo no encontrado"));

    return modelo.getEmpresa();
}

//eliminar un modelo de una empresa
public void removeEmpresa(Long modeloId) throws EntityNotFoundException {
    ModeloEntity modelo = modeloRepository.findById(modeloId)
        .orElseThrow(() -> new EntityNotFoundException("Modelo no encontrado"));

    modelo.setEmpresa(null);
}


}
