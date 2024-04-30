package Datos;
import Logica.ConexionSQL;
import com.toedter.calendar.JDateChooser;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
public class DFacturas_Ingreso {
    int ID;
    int IDLinea;
    String Linea;
    int IDProvee;
    String Provee;
    String Fecha;
    String Proyecto;
    String Ubicacion;
    String Hora;

    public DFacturas_Ingreso() {
    }

    public DFacturas_Ingreso(int ID, int IDLinea, String Linea, int IDProvee, String Provee, String Fecha) {
        this.ID = ID;
        this.IDLinea = IDLinea;
        this.Linea = Linea;
        this.IDProvee = IDProvee;
        this.Provee = Provee;
        this.Fecha = Fecha;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDLinea() {
        return IDLinea;
    }

    public void setIDLinea(int IDLinea) {
        this.IDLinea = IDLinea;
    }

    public String getLinea() {
        return Linea;
    }

    public void setLinea(String Linea) {
        this.Linea = Linea;
    }

    public int getIDProvee() {
        return IDProvee;
    }

    public void setIDProvee(int IDProvee) {
        this.IDProvee = IDProvee;
    }

    public String getProvee() {
        return Provee;
    }

    public void setProvee(String Provee) {
        this.Provee = Provee;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getProyecto() {
        return Proyecto;
    }

    public void setProyecto(String Proyecto) {
        this.Proyecto = Proyecto;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }
    
    public void InsertarDatosFac_Ingreso(JTextField txtLinID, JTextField txtLinea, JTextField txtProveeID, JTextField txtProvee, JLabel txtFecha,JTextField txtProyecto,JTextField txtUbi){
        setIDLinea(Integer.parseInt(txtLinID.getText()));
        setLinea(txtLinea.getText());
        setIDProvee(Integer.parseInt(txtProveeID.getText()));
        setProvee(txtProvee.getText());  
        setFecha(txtFecha.getText());
        setProyecto(txtProyecto.getText());
        setUbicacion(txtUbi.getText());
        ConexionSQL ObjetoCon = new ConexionSQL();  
        String Consulta = "INSERT INTO inventariofcj.Datos_IngresoFactu (IDLinea,Linea,IDProvee,Provee,FechaEntrada,Proyecto,Ubicacion,Hora) values (?,?,?,?,?,?,?,?);";  
        try {
            CallableStatement cs = ObjetoCon.getConnection().prepareCall(Consulta);
            cs.setInt(1,getIDLinea());
            cs.setString(2,getLinea());
            cs.setInt(3, getIDProvee());
            cs.setString(4,getProvee());
            cs.setString(5, getFecha());
            cs.setString(6, getProyecto());
            cs.setString(7, getUbicacion());
            cs.setString(8, getHora());
            cs.execute(); 
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se inserto de forma correcta"+e.toString());
        }        
    }      
    public void MostrarDatosFac (JTable Tabla_Registro){
        ConexionSQL ObjetoCon = new ConexionSQL();     
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_Registro.setRowSorter(OrdenarTabla);      
        modelo.addColumn("ID");
        modelo.addColumn("IDLinea");
        modelo.addColumn("Linea");
        modelo.addColumn("IDProvee");
        modelo.addColumn("Provee");
        modelo.addColumn("Fecha");
        Tabla_Registro.setModel(modelo);
        String sql = "select * from inventariofcj.datos_ingresofactu";
        String[] datos = new String[6];
        Statement st;
        try{
            st = ObjetoCon.getConnection().createStatement();  
            ResultSet rs = st.executeQuery(sql);        
            while (rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);   
                datos[5] = rs.getString(6);
                modelo.addRow(datos);
            }     
            Tabla_Registro.setModel(modelo);    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        }
    }
}