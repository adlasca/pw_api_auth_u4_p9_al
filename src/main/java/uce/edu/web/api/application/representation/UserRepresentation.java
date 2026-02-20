package uce.edu.web.api.application.representation;

public class UserRepresentation {
    private Long id;
    private String nombre;
    private String role;

    public UserRepresentation() {
    }

    public UserRepresentation(Long id, String nombre, String role) {
        this.id = id;
        this.nombre = nombre;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
