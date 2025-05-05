package co.edu.uniandes.dse.ZZZ.services;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;
import co.edu.uniandes.dse.ZZZ.services.CategoriaService;
import co.edu.uniandes.dse.ZZZ.entities.CategoriaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import co.edu.uniandes.dse.ZZZ.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ZZZ.entities.EmpresaEntity;
import co.edu.uniandes.dse.ZZZ.entities.ModeloEntity;
import co.edu.uniandes.dse.ZZZ.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@DataJpaTest
@Transactional
@Import(CategoriaService.class)
public class CategoriaServiceTest {

    @Autowired
	private CategoriaService categoriaService;

    @Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

	private List<CategoriaEntity> categoriaList = new ArrayList<>();

	@BeforeEach
	void setUp() {
		clearData();
		insertData();
	}

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from CategoriaEntity").executeUpdate();
		
	}

    private void insertData() {

		for (int i = 0; i < 3; i++) {
			CategoriaEntity categoriaEntity = factory.manufacturePojo(CategoriaEntity.class);
			entityManager.persist(categoriaEntity);
			categoriaList.add(categoriaEntity);
		}
	}

    //test crear categoria
    @Test
    void testCreateCategoria() throws Exception {
    CategoriaEntity nueva = factory.manufacturePojo(CategoriaEntity.class);
    nueva.setNombreCategoria("UnicoNombre");

    CategoriaEntity result = categoriaService.createCategoria(nueva);

    assertNotNull(result);
    CategoriaEntity entity = entityManager.find(CategoriaEntity.class, result.getId());
    assertEquals(nueva.getNombreCategoria(), entity.getNombreCategoria());
    assertEquals(nueva.getDescripcionCategoria(), entity.getDescripcionCategoria());
   }

   //test crear categoria doble --error
    @Test
    void testCreateCategoriaConNombreDuplicado() throws Exception {
    CategoriaEntity duplicada = factory.manufacturePojo(CategoriaEntity.class);
    duplicada.setNombreCategoria(categoriaList.get(0).getNombreCategoria());

    assertThrows(IllegalOperationException.class, () -> categoriaService.createCategoria(duplicada));
    }  

    //test obtener categoria
    @Test
    void testGetCategorias() {
    List<CategoriaEntity> list = categoriaService.getCategorias();
    assertEquals(categoriaList.size(), list.size());
    }

    //test get categoria por id
    @Test
    void testGetCategoriaPorId() throws Exception {
    CategoriaEntity original = categoriaList.get(0);
    CategoriaEntity result = categoriaService.getCategoria(original.getId());

    assertNotNull(result);
    assertEquals(original.getId(), result.getId());
    assertEquals(original.getNombreCategoria(), result.getNombreCategoria());
   }

    //test get categorio id no existe --error
    @Test
    void testGetCategoriaPorIdInexistente() {
    assertThrows(EntityNotFoundException.class, () -> {
        categoriaService.getCategoria(999L);
     });
    }

    //test actualizar categoria
    @Test
    void testUpdateCategoria() throws Exception {
    CategoriaEntity original = categoriaList.get(0);
    CategoriaEntity actualizada = factory.manufacturePojo(CategoriaEntity.class);

    CategoriaEntity result = categoriaService.updateCategoria(original.getId(), actualizada);

    assertNotNull(result);
    CategoriaEntity entity = entityManager.find(CategoriaEntity.class, original.getId());
    assertEquals(actualizada.getNombreCategoria(), entity.getNombreCategoria());
    assertEquals(actualizada.getDescripcionCategoria(), entity.getDescripcionCategoria());
    }

    //test actualizar categoria inexistente
    @Test
    void testUpdateCategoriaInexistente() {
    CategoriaEntity nueva = factory.manufacturePojo(CategoriaEntity.class);
    assertThrows(EntityNotFoundException.class, () -> {
        categoriaService.updateCategoria(999L, nueva);
    });
   }

   //test borrar categoria
   @Test
    void testDeleteCategoria() throws Exception {
    CategoriaEntity categoria = categoriaList.get(0);
    categoria.setModelos(new ArrayList<>()); 

    categoriaService.deleteCategoria(categoria.getId());

    CategoriaEntity deleted = entityManager.find(CategoriaEntity.class, categoria.getId());
    assertNull(deleted);
    }

   //test borrar categoria con modelos --error
   @Test
    void testDeleteCategoriaConModelos() {
    CategoriaEntity categoria = categoriaList.get(0);
    List<ModeloEntity> modelos = new ArrayList<>();
    modelos.add(factory.manufacturePojo(ModeloEntity.class));
    categoria.setModelos(modelos);
    entityManager.merge(categoria);

    assertThrows(IllegalOperationException.class, () -> categoriaService.deleteCategoria(categoria.getId()));
}

  //test borrar categoria que no existe --error
  @Test
   void testDeleteCategoriaInexistente() {
    assertThrows(EntityNotFoundException.class, () -> categoriaService.deleteCategoria(999L));
   }





    
    



}
