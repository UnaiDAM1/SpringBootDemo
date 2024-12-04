package DTO;

import jakarta.persistence.*;

@Entity
@Table(name = "auditoria")
public class Auditoria {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nickname", nullable = false)
    private DTO.Usuario nickname;

    @Column(name = "password", nullable = false, length = 8)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DTO.Usuario getNickname() {
        return nickname;
    }

    public void setNickname(DTO.Usuario nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Auditoria() {
    }

    public Auditoria(Integer id, Usuario nickname, String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Auditoria: " +
                "\nID = " + id +
                ", Nickname = " + nickname +
                ", password='" + password;
    }
}