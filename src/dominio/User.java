package dominio;


public class User {
    private Long id_usuario;
    private String nombre_usuario;
    private String password_usuario;
    private Customer socio;

    /**
     * @return the id_usuario
     */
    public Long getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the nombre_usuario
     */
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    /**
     * @param nombre_usuario the nombre_usuario to set
     */
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     * @return the password_usuario
     */
    public String getPassword_usuario() {
        return password_usuario;
    }

    /**
     * @param password_usuario the password_usuario to set
     */
    public void setPassword_usuario(String password_usuario) {
        this.password_usuario = password_usuario;
    }

    /**
     * @return the socio
     */
    public Customer getSocio() {
        return socio;
    }

    /**
     * @param socio the socio to set
     */
    public void setSocio(Customer socio) {
        this.socio = socio;
    }
    
    

}