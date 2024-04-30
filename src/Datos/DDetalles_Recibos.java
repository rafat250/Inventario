package Datos;
import Logica.ConexionSQL;
import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
public class DDetalles_Recibos {
    int IDdetalleRecib;
    int ReciboID;
    String Productosid;
    String Lineas;
    int Cantidad;
    Double Total;

    public DDetalles_Recibos() {
    }

    public DDetalles_Recibos(int IDdetalleRecib,String Productosid, String Lineas, int Cantidad, Double Total) {
        this.IDdetalleRecib = IDdetalleRecib;
        this.Productosid = Productosid;
        this.Lineas = Lineas;
        this.Cantidad = Cantidad;
        this.Total = Total;
    }

    public int getIDdetalleRecib() {
        return IDdetalleRecib;
    }

    public void setIDdetalleRecib(int IDdetalleRecib) {
        this.IDdetalleRecib = IDdetalleRecib;
    }

    public int getReciboID() {
        return ReciboID;
    }

    public void setReciboID(int ReciboID) {
        this.ReciboID = ReciboID;
    }
    
    public String getProductosid() {
        return Productosid;
    }

    public void setProductosid(String Productosid) {
        this.Productosid = Productosid;
    }

    public String getLineas() {
        return Lineas;
    }

    public void setLineas(String Lineas) {
        this.Lineas = Lineas;
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
    
    public void InsertarDatosDetalleRecibos (){
        ConexionSQL ObjetoCon = new ConexionSQL();  
        String Consulta = "Insert into inventariofcj.Datos_DetalleRecibo (IDDetalleRecib,ReciboID,Productosid,Lineas,Cantidad,Total) values (?,?,?,?,?,?)";  
        try{;
            CallableStatement cs = ObjetoCon.getConnection().prepareCall(Consulta);         
            cs.setInt(1, getIDdetalleRecib());
            cs.setInt(2, getReciboID());
            cs.setString(3,getProductosid());
            cs.setString(4, getLineas());
            cs.setInt(5, getCantidad());
            cs.setDouble(6, getTotal());
            cs.execute();      
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en insertar datos error: "+e.toString());  
        }
    }  
    public void EliminarRecibos (JTable Tabla_ListaRec){
        setIDdetalleRecib(Integer.parseInt(Tabla_ListaRec.getValueAt(Tabla_ListaRec.getSelectedRow(), 0).toString()));
        ConexionSQL ObjetoCon = new ConexionSQL(); 
        String Consulta = "DELETE Datos_Recibos,Datos_DetalleRecibo FROM inventariofcj.Datos_Recibos JOIN inventariofcj.Datos_DetalleRecibo ON Datos_Recibos.IDRecibo = Datos_DetalleRecibo.IDDetalleRecib WHERE IDDetalleRecib = ?;";        
        try{
            CallableStatement cs =ObjetoCon.getConnection().prepareCall(Consulta);  
            cs.setInt(1, getIDdetalleRecib());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Recibo eliminado");  
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en eliminar, error " +e.toString());
        }
    }
}