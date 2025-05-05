package co.edu.uniandes.dse.ZZZ.repositories;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniandes.dse.ZZZ.entities.CategoriaEntity;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity,Long>{
    CategoriaEntity findByNombreCategoria(String nombreCategoria);
    Optional<CategoriaEntity> findByNombreCategoriaIgnoreCase(String nombreCategoria);



}
