package DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(name = "nickname", nullable = false, length = 20)
    private String nickname;

    @Column(name = "password", length = 8)
    private String password;

    @Column(name = "estado")
    private Boolean estado;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Usuario() {
    }

    public Usuario(String nickname, String password, Boolean estado) {
        this.nickname = nickname;
        this.password = password;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario: " +
                "\nNickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", estado=" + estado;
    }
}