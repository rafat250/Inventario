package Datos;
import Logica.ConexionSQL;
import com.toedter.calendar.JDateChooser;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
public class DRecibos {
    String FechasR;
    String PersonaEntrega;
    String PersonaRecibe;
    String Cliente;
    String Proyecto;
    String Ubicacion;
    String Hora;
    public DRecibos() {
    }

    public DRecibos(String FechasR, String PersonaEntrega, String PersonaRecibe) {
        this.FechasR = FechasR;
        this.PersonaEntrega = PersonaEntrega;
        this.PersonaRecibe = PersonaRecibe;
    }

    public String getFechasR() {
        return FechasR;
    }

    public void setFechasR(String FechasR) {
        this.FechasR = FechasR;
    }

    public String getPersonaEntrega() {
        return PersonaEntrega;
    }

    public void setPersonaEntrega(String PersonaEntrega) {
        this.PersonaEntrega = PersonaEntrega;
    }

    public String getPersonaRecibe() {
        return PersonaRecibe;
    }

    public void setPersonaRecibe(String PersonaRecibe) {
        this.PersonaRecibe = PersonaRecibe;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
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
    
    public void InsertarDatosRecibo(JLabel txtFechaR,JTextField txtPersonaR,JTextField txtCliente,JTextField txtProyecto,JTextField txtUbi){
        setFechasR(txtFechaR.getText());
        setPersonaRecibe(txtPersonaR.getText());  
        setCliente(txtCliente.getText());
        setProyecto(txtProyecto.getText());
        setUbicacion(txtUbi.getText());
        ConexionSQL ObjetoCon = new ConexionSQL();  
        String Consulta = "Insert into inventariofcj.Datos_Recibos (FechasR,PersonaEntrega,PersonaRecibe,Cliente,Proyecto,Ubicacion,Hora) values (?,?,?,?,?,?,?);";  
        try {
            CallableStatement cs = ObjetoCon.getConnection().prepareCall(Consulta);
            cs.setString(1, getFechasR());
            cs.setString(2,getPersonaEntrega());
            cs.setString(3,getPersonaRecibe());         
            cs.setString(4, getCliente());
            cs.setString(5, getProyecto());
            cs.setString(6, getUbicacion());
            cs.setString(7, getHora());
            cs.execute();             
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se inserto de forma correcta"+e.toString());
        }        
    }   
    public void MostrarDatosSalidas(JTable Tabla_Salidas){
        ConexionSQL ObjetoCon = new ConexionSQL();     
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_Salidas.setRowSorter(OrdenarTabla);      
        modelo.addColumn("ID");
        modelo.addColumn("FechaR");
        modelo.addColumn("Persona Entrega");
        modelo.addColumn("Persona Recibe");
        modelo.addColumn("Cliente");
        modelo.addColumn("Proyecto");
        modelo.addColumn("Ubicacion Cliente");
        modelo.addColumn("Hora");
        modelo.addColumn("IDDetalle");
        modelo.addColumn("IDRecibo");
        modelo.addColumn("Productoid");
        modelo.addColumn("Ubicacion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
        Tabla_Salidas.setModel(modelo);
        String sql = "SELECT * FROM inventariofcj.datos_recibos a, inventariofcj.Datos_DetalleRecibo b Where a.IDRecibo = b.ReciboID;";
        String[] datos = new String [14];
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
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);   
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(12);
                datos[12] = rs.getString(13);
                datos[13] = rs.getString(14); 
                modelo.addRow(datos);
            }
            Tabla_Salidas.setModel(modelo);    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos "+e.toString());
        } 
    }
}