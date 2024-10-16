package proyectoAbilitySwap.talento.beans;

public class HabilidadOfertada {
    private int id_habilidad;
    private int usuario;
    private String nombre;
    private int id_categoria;

    public HabilidadOfertada() {
    }

    public HabilidadOfertada(int idHabilidad, int usuario, String nombre, int idCategoria) {
        this.id_habilidad = idHabilidad;
        this.usuario = usuario;
        this.nombre = nombre;
        this.id_categoria = idCategoria;
    }

    public int getIdHabilidad() {
        return id_habilidad;
    }

    public void setIdHabilidad(int idHabilidad) {
        this.id_habilidad = idHabilidad;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCategoria() {
        return id_categoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.id_categoria = idCategoria;
    }



    @Override
    public String toString() {
        return "HabilidadOfertada{" +
                "idHabilidad=" + id_habilidad +
                ", usuario='" + usuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", idCategoria=" + id_categoria +
                '}';
    }
}

