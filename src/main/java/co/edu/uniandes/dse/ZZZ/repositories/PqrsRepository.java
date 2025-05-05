package co.edu.uniandes.dse.ZZZ.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniandes.dse.ZZZ.entities.PqrsEntity;

@Repository
public interface PqrsRepository extends JpaRepository<PqrsEntity,Long>{
    List<PqrsEntity> findByClienteId(Long clienteId);
    List<PqrsEntity> findByTipoIgnoreCase(String tipo);






}
