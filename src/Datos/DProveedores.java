package Datos;
import Logica.ConexionSQL;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
public class DProveedores {
    int ID;
    String Nombre;
    String Telefono;
    String Domicilio;

    public DProveedores() {
    }

    public DProveedores(int ID, String Nombre, String Telefono, String Domicilio) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Telefono = Telefono;
        this.Domicilio = Domicilio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String Domicilio) {
        this.Domicilio = Domicilio;
    }
    public void InsertarDatosProvee (JTextField txtNom,JTextField txtTel, JTextField txtDomi){
        setNombre(txtNom.getText());
        setTelefono(txtTel.getText());
        setDomicilio(txtDomi.getText());
        ConexionSQL ObjetoCon = new ConexionSQL();  
        String Consulta = "INSERT INTO inventariofcj.Datos_Proveedores (NombrePRO,Telefono,Domicilio) values (?,?,?);";  
        try{
            CallableStatement cs = ObjetoCon.getConnection().prepareCall(Consulta);
            cs.setString(1,getNombre());
            cs.setString(2,getTelefono());
            cs.setString(3, getDomicilio());
            cs.execute();     
            JOptionPane.showMessageDialog(null, "Se guardo de forma correcta");  
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en insertar datos error: "+e.toString());  
        }
    }
    public void MostrarDatosProvee(JTable Tabla_Proveedores){
        ConexionSQL ObjetoCon = new ConexionSQL();  
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_Proveedores.setRowSorter(OrdenarTabla);
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Domicilio");
        Tabla_Proveedores.setModel(modelo);
        String sql = "select * from inventariofcj.Datos_Proveedores";
        String[] datos = new String[4];
        Statement st;
        try{
            st = ObjetoCon.getConnection().createStatement();  
            ResultSet rs = st.executeQuery(sql);        
            while (rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);       
                modelo.addRow(datos);
            }     
            Tabla_Proveedores.setModel(modelo);    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        } 
    }
    public void SeleccionarProveedor(JTable Tabla_Proveedores,JTextField txtNom,JTextField txtTel, JTextField txtDomi){
        try {
            int fila = Tabla_Proveedores.getSelectedRow();  
            if (fila >=0) {
                txtNom.setText((Tabla_Proveedores.getValueAt(fila, 1).toString()));  
                txtTel.setText((Tabla_Proveedores.getValueAt(fila, 2).toString()));  
                txtDomi.setText((Tabla_Proveedores.getValueAt(fila, 3).toString()));  
            }else {
                JOptionPane.showMessageDialog(null, "Fila no encontrada");
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la seleccion, error"+e.toString());
        }  
    }
    public void ModificarProveedor(JTable Tabla_Proveedores,JTextField txtNom,JTextField txtTel, JTextField txtDomi){
        setID(Integer.parseInt(Tabla_Proveedores.getValueAt(Tabla_Proveedores.getSelectedRow(), 0).toString()));
        setNombre(txtNom.getText());
        setTelefono(txtTel.getText());
        setDomicilio(txtDomi.getText());
        ConexionSQL ObjetoCon = new ConexionSQL(); 
        String Consulta = "UPDATE inventariofcj.Datos_Proveedores SET Datos_Proveedores.NombrePRO = ?, Datos_Proveedores.Telefono = ?, Datos_Proveedores.Domicilio = ? WHERE Datos_Proveedores.IDProveedores = ?;";
        try {  
            CallableStatement cs =ObjetoCon.getConnection().prepareCall(Consulta);   
            cs.setString(1, getNombre());
            cs.setString(2, getTelefono());
            cs.setString(3, getDomicilio());
            cs.setInt(4, getID());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");           
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en modificar, error" +e.toString());
        }
    }
    public void EliminarProveedor(JTable Tabla_Proveedores){
        setID(Integer.parseInt(Tabla_Proveedores.getValueAt(Tabla_Proveedores.getSelectedRow(), 0).toString()));
        ConexionSQL ObjetoCon = new ConexionSQL(); 
        String Consulta = "DELETE FROM inventariofcj.Datos_Proveedores WHERE Datos_Proveedores.IDProveedores = ?";        
        try{
            CallableStatement cs =ObjetoCon.getConnection().prepareCall(Consulta);  
            cs.setInt(1, getID());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Proveedor eliminado");  
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en eliminar, error" +e.toString());
        }
    }   
}