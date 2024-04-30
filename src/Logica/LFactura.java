package Logica;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class LFactura {
    public DefaultTableModel MostrarFacturas (){
        DefaultTableModel Mimodelo = null;       
        ConexionSQL ObjetoCon = new ConexionSQL();          
        Connection ob = ObjetoCon.getConnection();           
        try {
            String[] titulos = {"Codigo","Proveedor","Linea","Fecha de registro","Proyecto","Ubicacion","Hora"};
            String[] datos = new String[7];
            Mimodelo = new DefaultTableModel(null, titulos);
            CallableStatement cst = ob.prepareCall("SELECT IDFacturas,NombrePRO,Linea,FechaEntrada,Proyecto,Ubicacion,Hora FROM inventariofcj.datos_ingresofactu INNER JOIN inventariofcj.Datos_proveedores on IDProveedores = IDProvee INNER JOIN inventariofcj.datos_lineas on IDLin = IDLinea;");                
            ResultSet rs = cst.executeQuery();   
            while (rs.next()){
                datos[0] = rs.getString("IDFacturas");
                datos[1] = rs.getString("NombrePRO");
                datos[2] = rs.getString("Linea");
                datos[3] = rs.getString("FechaEntrada"); 
                datos[4] = rs.getString("Proyecto"); 
                datos[5] = rs.getString("Ubicacion"); 
                datos[6] = rs.getString("Hora"); 
                Mimodelo.addRow(datos);
            }     
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        }
        return Mimodelo;
    }   
}