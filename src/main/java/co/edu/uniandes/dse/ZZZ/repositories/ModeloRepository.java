package co.edu.uniandes.dse.ZZZ.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniandes.dse.ZZZ.entities.ModeloEntity;

@Repository
public interface ModeloRepository extends JpaRepository<ModeloEntity,Long> {
    Optional<ModeloEntity> findByNombre(String nombre);
    List<ModeloEntity> findByMaterialIgnoreCase(String material);
    List<ModeloEntity> findByEmpresaId(Long empresaId);
    List<ModeloEntity> findByCategoriaId(Long categoriaId);

}
