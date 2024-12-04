package DTO;

public class UsuariosDTO {
    String nombre;
    String password;
    boolean bloqueado;
    int contador = 0;
    Auditoria_Sesiones oldPass;

    public UsuariosDTO(String nombre, String password) {
        if (validarNombre(nombre)) {
            this.nombre = nombre;
        } else {
            System.out.println("Nombre debe empezar en mayúsculas.");
        }
        if (validarPassword(password)) {
            this.password = password;
        } else {
            System.out.println("Password no valida.");
        }
        this.bloqueado = false;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (validarPassword(password)) {
            if (contador == 3){
                this.password = password;
            } else{
                bloqueado = true;
                System.out.println("Intentos agotados.");
            }
        } else {
            System.out.println("Password no valida.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean validarPassword(String password) {
        if (password.length() >= 4 && password.length() <= 8) {
            if (password.matches(".*[a-zA-Z0-9].*]")){
                if (!oldPass.compararPasswords(password)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean validarNombre(String nombre) {
        if (nombre.substring(0, 1).toUpperCase().equals(nombre.substring(0, 1))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Usuario: " +
                "\nNombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", estado=" + bloqueado;
    }
}
