package DTO;

public class Auditoria_Sesiones {
    private String[] oldPassword = null;

    public Auditoria_Sesiones() {
    }

    public boolean compararPasswords(String pass){
        for (int i = 0; i < oldPassword.length; i++) {
            if(oldPassword[i].equals(pass)){
                return true;
            }
        }
        oldPassword[oldPassword.length] = pass;
        return false;
    }
}
