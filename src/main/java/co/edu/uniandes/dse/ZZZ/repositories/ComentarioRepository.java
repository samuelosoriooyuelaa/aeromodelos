package co.edu.uniandes.dse.ZZZ.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.ZZZ.entities.ComentarioEntity;
@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {
    List<ComentarioEntity> findByClienteId(Long clienteId);
    List<ComentarioEntity> findByModeloId(Long modeloId);
    List<ComentarioEntity> findByElementoId(Long elementoId);



}
