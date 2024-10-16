package proyectoAbilitySwap.talento.beans;

public class Categoria {

    private String nombre; 
    private int id_categoria;
    
    public Categoria() {

    }

    public Categoria(String nombre, int id_categoria) { 
        super();
        this.nombre = nombre;
        this.id_categoria = id_categoria;
    }

    public String getNombre() {  
        return nombre;
    }

    public void setNombre(String nombre) {  
        this.nombre = nombre;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
}

