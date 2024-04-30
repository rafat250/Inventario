package Logica;
import Datos.DAlmacen;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class LAlmacen {   
    public String[] AdquirirProductos(DAlmacen misproduc){
        ConexionSQL ObjetoCon = new ConexionSQL();          
        Connection ob = ObjetoCon.getConnection();    
        String[] datos = new String[6];
        try{
            CallableStatement cst = ob.prepareCall("select * from inventariofcj.Datos_Almacen WHERE IDAlmacen LIKE ?");
            cst.setString(1, misproduc.getIDAlmacen());       
            ResultSet rs = cst.executeQuery();               
            while (rs.next()){
                datos[0] = rs.getString("IDAlmacen");
                datos[1] = rs.getString("Descripcion");
                datos[2] = "1";
                datos[3] = rs.getString("UMedida");
                datos[4] = rs.getString("PrecioUnitario"); 
                datos[5] = rs.getString("PrecioUnitario");
            }     
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        }
        return datos;
    }     
    public DefaultTableModel MostrarInventario (DAlmacen Misproductos){
        DefaultTableModel Mimodelo = null;       
        ConexionSQL ObjetoCon = new ConexionSQL();          
        Connection ob = ObjetoCon.getConnection();           
        try {
            String[] titulos = {"Codigo","Descripcion","Stock","U/Medida","P/Unitario","Total","Linea"};
            String[] datos = new String[7];
            Mimodelo = new DefaultTableModel(null, titulos);
            CallableStatement cst = ob.prepareCall("SELECT IDAlmacen, Descripcion, Stock, UMedida, PrecioUnitario, (Stock * PrecioUnitario) as Total, Lineas FROM inventariofcj.datos_Almacen INNER JOIN inventariofcj.datos_lineas on IDLin = IDLinea WHERE IDAlmacen LIKE concat(?);");           
            cst.setString(1, Misproductos.getIDAlmacen());       
            ResultSet rs = cst.executeQuery();   
            while (rs.next()){
                datos[0] = rs.getString("IDAlmacen");
                datos[1] = rs.getString("Descripcion");
                datos[2] = rs.getString("Stock");
                datos[3] = rs.getString("UMedida");
                datos[4] = rs.getString("PrecioUnitario"); 
                datos[5] = rs.getString("Total"); 
                datos[6] = rs.getString("Lineas"); 
                Mimodelo.addRow(datos);
            }     
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        }
        return Mimodelo;
    } 
    public String[] AdquirirProductosRec(DAlmacen misproduc){
        ConexionSQL ObjetoCon = new ConexionSQL();          
        Connection ob = ObjetoCon.getConnection();    
        String[] datos = new String[7];
        try{
            CallableStatement cst = ob.prepareCall("Select IDAlmacen, Descripcion, Stock, UMedida, PrecioUnitario, Lineas FROM inventariofcj.Datos_Almacen INNER JOIN inventariofcj.datos_lineas ON IDLin = IDLinea Where IDAlmacen Like concat(?);");
            cst.setString(1, misproduc.getIDAlmacen());       
            ResultSet rs = cst.executeQuery();               
            while (rs.next()){
                datos[0] = rs.getString("IDAlmacen");
                datos[1] = rs.getString("Descripcion");
                datos[2] = "1";
                datos[3] = rs.getString("PrecioUnitario"); 
                datos[4] = rs.getString("UMedida");
                datos[5] = rs.getString("Lineas");
                datos[6] = rs.getString("PrecioUnitario");
            }     
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        }
        return datos;
    }
    public String ReducirPiezas (DAlmacen misALM){
        ConexionSQL ObjetoCon = new ConexionSQL();  
        String Consulta = null;  
        try{
            CallableStatement cs = ObjetoCon.getConnection().prepareCall("UPDATE inventariofcj.Datos_Almacen SET Stock = Stock - ? WHERE IDAlmacen = ? ;");
            cs.setInt(1, misALM.getStock());
            cs.setString(2, misALM.getIDAlmacen());   
            cs.execute();      
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en insertar datos error: "+e.toString());  
        }
        return Consulta;
    }
}