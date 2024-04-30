package Logica;
import Datos.DDetalles_Recibos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class LDetalleRecibos {
    public DefaultTableModel MDetalleRecibos(DDetalles_Recibos Midetalle){
        DefaultTableModel miModelo = null;
        ConexionSQL ObjetoCon = new ConexionSQL();          
        Connection ob = ObjetoCon.getConnection();
        try{
           String titulos[] = {"Codigo","Descripcion","Cantidad","UMedida","PUnitario","Linea","Total"} ;
           String datos [] = new String[7];
           miModelo = new DefaultTableModel(null,titulos);
           CallableStatement cst = ob.prepareCall("Select ProductosID, Descripcion, Cantidad, UMedida, PrecioUnitario, Lineas ,Total FROM inventariofcj.Datos_DetalleRecibo INNER JOIN inventariofcj.Datos_Almacen ON IDAlmacen = Productosid WHERE ReciboID = ?;");
           cst.setInt(1,Midetalle.getReciboID());
           ResultSet rs = cst.executeQuery();
           while(rs.next()){
               datos[0] = rs.getString("ProductosID");
               datos[1] = rs.getString("Descripcion");
               datos[2] = rs.getString("Cantidad");
               datos[3] = rs.getString("UMedida");
               datos[4] = rs.getString("PrecioUnitario");
               datos[5] = rs.getString("Lineas");
               datos[6] = rs.getString("Total");
               miModelo.addRow(datos);
           }        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        }
        return miModelo;
    }
}