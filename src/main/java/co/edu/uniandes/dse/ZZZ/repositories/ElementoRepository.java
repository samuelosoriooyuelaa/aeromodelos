package co.edu.uniandes.dse.ZZZ.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import co.edu.uniandes.dse.ZZZ.entities.ElementoEntity;

@Repository
public interface ElementoRepository extends JpaRepository<ElementoEntity, Long> {
    ElementoEntity findByNombre(String nombre);
    Optional<ElementoEntity> findByNombreElementoIgnoreCase(String nombreElemento);

    List<ElementoEntity> findByTipoIgnoreCase(String tipo);


}
