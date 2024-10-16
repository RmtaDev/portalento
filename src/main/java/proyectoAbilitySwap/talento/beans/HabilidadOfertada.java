package proyectoAbilitySwap.talento.beans;

public class HabilidadOfertada {
    private int idHabilidad;
    private String usuario;
    private String nombre;
    private int idCategoria;
    private Categoria categoria; 

    public HabilidadOfertada() {
    }

    public HabilidadOfertada(int idHabilidad, String usuario, String nombre, int idCategoria) {
        this.idHabilidad = idHabilidad;
        this.usuario = usuario;
        this.nombre = nombre;
        this.idCategoria = idCategoria;
    }

    public int getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(int idHabilidad) {
        this.idHabilidad = idHabilidad;
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
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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
                "idHabilidad=" + idHabilidad +
                ", usuario='" + usuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", idCategoria=" + idCategoria +
                ", categoria=" + (categoria != null ? categoria.getNombre() : "null") +
                '}';
    }
}

