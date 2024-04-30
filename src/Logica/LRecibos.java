package Logica;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class LRecibos {
    public DefaultTableModel MostrarRecibos (){
        DefaultTableModel Mimodelo = null;       
        ConexionSQL ObjetoCon = new ConexionSQL();          
        Connection ob = ObjetoCon.getConnection();           
        try {
            String[] titulos = {"ID","Fecha","Persona Entrega","Persona Recibe","Cliente","Proyecto","Ubicacion","Hora"};
            String[] datos = new String[8];
            Mimodelo = new DefaultTableModel(null, titulos);
            CallableStatement cst = ob.prepareCall("Select * from inventariofcj.Datos_Recibos");                
            ResultSet rs = cst.executeQuery();   
            while (rs.next()){
                datos[0] = rs.getString("IDRecibo");
                datos[1] = rs.getString("FechasR");
                datos[2] = rs.getString("PersonaEntrega");
                datos[3] = rs.getString("PersonaRecibe"); 
                datos[4] = rs.getString("Cliente"); 
                datos[5] = rs.getString("Proyecto"); 
                datos[6] = rs.getString("Ubicacion"); 
                datos[7] = rs.getString("Hora"); 
                Mimodelo.addRow(datos);
            }     
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        }
        return Mimodelo;
    } 
}