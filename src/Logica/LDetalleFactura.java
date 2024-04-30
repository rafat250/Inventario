package Logica;
import java.sql.CallableStatement;
import Datos.DDetalleFactura;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
public class LDetalleFactura {  
    public DefaultTableModel MDetalleFactura(DDetalleFactura Midetalle){
        DefaultTableModel miModelo = null;
        ConexionSQL ObjetoCon = new ConexionSQL();          
        Connection ob = ObjetoCon.getConnection();
        try{
           String titulos[] = {"Codigo","Descripcion","Stock","UMedida","PUnitario","Total"} ;
           String datos [] = new String[6];
           miModelo = new DefaultTableModel(null,titulos);
           CallableStatement cst = ob.prepareCall("Select ProductosID, Descripcion, Cantidad, UMedida, PrecioUnitario, Total FROM inventariofcj.Datos_Detallesfactura INNER JOIN inventariofcj.Datos_Almacen ON IDAlmacen = ProductosID WHERE FacturasID = ?;");
           cst.setInt(1,Midetalle.getFacturaID());
           ResultSet rs = cst.executeQuery();
           while(rs.next()){
               datos[0] = rs.getString("ProductosID");
               datos[1] = rs.getString("Descripcion");
               datos[2] = rs.getString("Cantidad");
               datos[3] = rs.getString("UMedida");
               datos[4] = rs.getString("PrecioUnitario");
               datos[5] = rs.getString("Total");
               miModelo.addRow(datos);
           }        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        }
        return miModelo;
    }
}