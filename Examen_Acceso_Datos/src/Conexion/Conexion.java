package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    static final String url = "jdbc:mariadb://localhost:3306/";
    static final String user = "unai";
    static final String pass = "1234";

    public Connection conexion;

    static final String bd = "db_exa";
    public Conexion() {
        try {
            conexion = DriverManager.getConnection(url, user, pass);
            conectBD();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void conectBD() throws SQLException {
        try (Statement st = conexion.createStatement()){
            st.execute(String.format("USE %s", bd));
        }
    }
}