package co.edu.uniandes.dse.ZZZ.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.entities.EmpresaEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ZZZ.repositories.EmpresaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    //crear empresa
    public EmpresaEntity createEmpresa(EmpresaEntity empresa) throws IllegalOperationException {
        log.info("Iniciando creación de empresa: {}", empresa.getNombreEmpresa());

        if (empresaRepository.findByNombreEmpresaIgnoreCase(empresa.getNombreEmpresa()).isPresent()) {
            throw new IllegalOperationException("Ya existe una empresa con ese nombre.");
        }

        EmpresaEntity nuevaEmpresa = empresaRepository.save(empresa);
        log.info("Empresa creada con ID: {}", nuevaEmpresa.getId());
        return nuevaEmpresa;
    }

    //obtener todas las empresas
    public List<EmpresaEntity> getEmpresas() {
        log.info("Obteniendo todas las empresas");
        return empresaRepository.findAll();
    }

    //obtener empresa por el id
    public EmpresaEntity getEmpresa(Long id) throws EntityNotFoundException {
        log.info("Buscando empresa con ID: {}", id);
        Optional<EmpresaEntity> empresa = empresaRepository.findById(id);

        if (empresa.isEmpty()) {
            throw new EntityNotFoundException("La empresa con ID " + id + " no existe.");
        }

        return empresa.get();
    }

    //actualizar empresa 
    public EmpresaEntity updateEmpresa(Long id, EmpresaEntity empresaActualizada) throws EntityNotFoundException {
        log.info("Actualizando empresa con ID: {}", id);

        EmpresaEntity existente = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La empresa con ID " + id + " no existe."));

        existente.setNombreEmpresa(empresaActualizada.getNombreEmpresa());
        existente.setPaisOrigen(empresaActualizada.getPaisOrigen());
        existente.setCiudadOrigen(empresaActualizada.getCiudadOrigen());
        existente.setCantidadEmpleados(empresaActualizada.getCantidadEmpleados());

        return empresaRepository.save(existente);
    }

    //eliminar empresa
    public void deleteEmpresa(Long id) throws EntityNotFoundException, IllegalOperationException {
        log.info("Eliminando empresa con ID: {}", id);

        EmpresaEntity empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La empresa con ID " + id + " no existe."));

        
        if (!empresa.getModelos().isEmpty()) {
            throw new IllegalOperationException("No se puede eliminar la empresa con ID " + id + " porque tiene modelos asociados.");
        }

        empresaRepository.delete(empresa);
    }



    




}
