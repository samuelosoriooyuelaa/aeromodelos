package co.edu.uniandes.dse.ZZZ.entities;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class ModeloEntity extends BaseEntity{

    //atributos
    private String nombre;
    private String descripcion;
    private String imagen;
    private float precio;
    private String tipo;
    private String material;
    private String calidad;
    private int disponibilidad;

    //un modelo le pertenece a una empresa
    @PodamExclude
    @ManyToOne()
    private EmpresaEntity empresa;

    //un modelo le pertenece a una categoria
    @PodamExclude
    @ManyToOne()
    private CategoriaEntity categoria;

    //un modelo tiene cero, uno o muchos comentarios
    @PodamExclude
    @OneToMany(mappedBy = "modelo")
    private List<ComentarioEntity> listaComentarios = new ArrayList<>();

    



}



