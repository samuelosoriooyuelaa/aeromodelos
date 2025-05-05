package co.edu.uniandes.dse.ZZZ.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniandes.dse.ZZZ.entities.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity,Long>{
    ClienteEntity findByCorreo(String correo);

    




}
