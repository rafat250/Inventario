package Datos;
import Logica.ConexionSQL;
import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
public class DDetalleFactura {
    int IDDetalleFacturas;
    int FacturaID;
    String ProductosID;
    int Cantidad;
    Double Total;
    public DDetalleFactura() {
    }

    public DDetalleFactura(int DetalleFacturas, int FacturaID, String ProductosID, int Cantidad, Double Total) {
        this.IDDetalleFacturas = DetalleFacturas;
        this.FacturaID = FacturaID;
        this.ProductosID = ProductosID;
        this.Cantidad = Cantidad;
        this.Total = Total;
    }

    public int getIDDetalleFacturas() {
        return IDDetalleFacturas;
    }

    public void setIDDetalleFacturas(int IDDetalleFacturas) {
        this.IDDetalleFacturas = IDDetalleFacturas;
    }

    public int getFacturaID() {
        return FacturaID;
    }

    public void setFacturaID(int FacturaID) {
        this.FacturaID = FacturaID;
    }

    public String getProductosID() {
        return ProductosID;
    }

    public void setProductosID(String ProductosID) {
        this.ProductosID = ProductosID;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }     
    
    public void InsertarDatosDetalleFactura (){
        ConexionSQL ObjetoCon = new ConexionSQL();  
        String Consulta = "Insert into inventariofcj.Datos_DetallesFactura (IDDetalleFactura,FacturasID,ProductosID,Cantidad,Total) values (?,?,?,?,?);";  
        try{
            CallableStatement cs = ObjetoCon.getConnection().prepareCall(Consulta);
            cs.setInt(1, getIDDetalleFacturas());
            cs.setInt(2, getFacturaID());
            cs.setString(3,getProductosID());
            cs.setInt(4, getCantidad());
            cs.setDouble(5, getTotal());
            cs.execute();      
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en insertar datos error: "+e.toString());  
        }
    }   
    public void EliminarFacturas(JTable Tabla_ListaFac){
        setIDDetalleFacturas(Integer.parseInt(Tabla_ListaFac.getValueAt(Tabla_ListaFac.getSelectedRow(), 0).toString()));
        ConexionSQL ObjetoCon = new ConexionSQL(); 
        String Consulta = "DELETE Datos_ingresofactu,Datos_Detallesfactura FROM inventariofcj.Datos_ingresofactu JOIN inventariofcj.Datos_Detallesfactura ON Datos_ingresofactu.IDFacturas = Datos_Detallesfactura.IDDetalleFactura WHERE IDDetalleFactura = ?;";        
        try{
            CallableStatement cs =ObjetoCon.getConnection().prepareCall(Consulta);
            cs.setInt(1,getIDDetalleFacturas());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Factura eliminado");          
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en eliminar, error " +e.toString());
        }
    }
}