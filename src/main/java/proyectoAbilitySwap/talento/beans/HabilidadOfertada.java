package proyectoAbilitySwap.talento.beans;

public class HabilidadOfertada {
    private int id_habilidad;
    private String usuario;
    private String nombre;
    private int id_categoria;
    private Categoria categoria; 

    public HabilidadOfertada() {
    }

    public HabilidadOfertada(int idHabilidad, String usuario, String nombre, int idCategoria) {
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "HabilidadOfertada{" +
                "idHabilidad=" + id_habilidad +
                ", usuario='" + usuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", idCategoria=" + id_categoria +
                ", categoria=" + (categoria != null ? categoria.getNombre() : "null") +
                '}';
    }
}

