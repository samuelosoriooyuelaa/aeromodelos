package co.edu.uniandes.dse.ZZZ.repositories;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.ZZZ.entities.EmpresaEntity;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity,Long>{
    EmpresaEntity findByNombreEmpresa(String nombreEmpresa);
    List<EmpresaEntity> findByCiudadOrigenIgnoreCase(String ciudadOrigen);
    List<EmpresaEntity> findByPaisOrigenIgnoreCase(String paisOrigen);
    Optional<EmpresaEntity> findByNombreEmpresaIgnoreCase(String nombreEmpresa);







}
