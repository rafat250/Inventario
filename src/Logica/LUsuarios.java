package Logica;
import Datos.DUsuarios;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
public class LUsuarios {
    ConexionSQL ObjetoCon = new ConexionSQL(); 
    Connection ob = ObjetoCon.getConnection(); 
    public String ValidarLogin(DUsuarios misUsuarios) {
        String perfil = "";
        try{
            CallableStatement cst = ob.prepareCall("Select * from inventariofcj.Datos_Usuarios where BINARY Datos_Usuarios.Usuario = ? AND BINARY Datos_Usuarios.Contraseña = ?;");
            cst.setString(1, misUsuarios.getUsuario());
            cst.setString(2, misUsuarios.getContraseña());
            ResultSet rs = cst.executeQuery();
            if(rs.next()){
                perfil = rs.getString("Perfil");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return perfil;
    }
}