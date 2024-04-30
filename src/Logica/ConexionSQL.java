package Logica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConexionSQL { 
    private static final String Password = "GRUPOFCJ";
    private static final String Usuario = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/inventariofcj";
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL,Usuario, Password);
            
        } catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la conexion"+e);
            }
        return con;
    }
}