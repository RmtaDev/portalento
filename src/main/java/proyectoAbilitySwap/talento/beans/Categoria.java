package proyectoAbilitySwap.talento.beans;

public class Categoria {

    private String nombre;  // Cambiado a String
    private int id_categoria;

    public Categoria(String nombre, int id_categoria) {  // Cambiado a String
        super();
        this.nombre = nombre;
        this.id_categoria = id_categoria;
    }

    public String getNombre() {  // Cambiado a String
        return nombre;
    }

    public void setNombre(String nombre) {  // Cambiado a String
        this.nombre = nombre;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
}

