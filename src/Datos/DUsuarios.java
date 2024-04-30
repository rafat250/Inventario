package Datos;
import Logica.ConexionSQL;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
public class DUsuarios {   
    int ID;
    String NombreUSU;
    String Usuario;
    String Contraseña;
    String Perfil;
    public DUsuarios() {
        
    }   
    public DUsuarios(int ID, String NombreUSU, String Usuario, String Contraseña, String Perfil) {
        this.ID = ID;
        this.NombreUSU = NombreUSU;
        this.Usuario = Usuario;
        this.Contraseña = Contraseña;
        this.Perfil = Perfil;
    }
        
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombreUSU() {
        return NombreUSU;
    }

    public void setNombreUSU(String NombreUSU) {
        this.NombreUSU = NombreUSU;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public String getPerfil() {
        return Perfil;
    }

    public void setPerfil(String Perfil) {
        this.Perfil = Perfil;
    }
    
    public void InsertarDatosUsuario(JTextField txtNombre,JTextField txtUsuario,JTextField txtContra ,JComboBox Lista_Perfil){    
        setNombreUSU(txtNombre.getText());
        setUsuario(txtUsuario.getText());
        setContraseña(txtContra.getText());  
        setPerfil((String) Lista_Perfil.getSelectedItem());    
        ConexionSQL ObjetoCon = new ConexionSQL();  
        String Consulta = "INSERT INTO inventariofcj.datos_usuarios (NombreUSU,Usuario,Contraseña,Perfil) values (?,?,?,?);";  
        try {
            CallableStatement cs = ObjetoCon.getConnection().prepareCall(Consulta);
            cs.setString(1,getNombreUSU());
            cs.setString(2,getUsuario());
            cs.setString(3, getContraseña());
            cs.setString(4,getPerfil());
            cs.execute();           
            JOptionPane.showMessageDialog(null, "Se agregaron los datos de forma correcta");  
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se inserto de forma correcta "+e.toString());
        }        
    }
    public void MostrarDatos(JTable Tabla_Usuarios){      
        ConexionSQL ObjetoCon = new ConexionSQL();     
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_Usuarios.setRowSorter(OrdenarTabla);      
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Usuario");
        modelo.addColumn("Contraseña");
        modelo.addColumn("Perfil");
        Tabla_Usuarios.setModel(modelo);
        String sql = "select * from inventariofcj.datos_usuarios";
        String[] datos = new String[5];
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
                modelo.addRow(datos);
            }     
            Tabla_Usuarios.setModel(modelo);    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos "+e.toString());
        }     
    }  
    public void SeleccionarUsuario(JTable Tabla_Usuarios,JTextField txtNombre,JTextField txtUsuario,JTextField txtContra){   
        try {
            int fila = Tabla_Usuarios.getSelectedRow();  
            if (fila >=0) {
                txtNombre.setText((Tabla_Usuarios.getValueAt(fila, 1).toString()));  
                txtUsuario.setText((Tabla_Usuarios.getValueAt(fila, 2).toString()));  
                txtContra.setText((Tabla_Usuarios.getValueAt(fila, 3).toString())); 
            }else {
                JOptionPane.showMessageDialog(null, "Fila no encontrada");
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la seleccion, error "+e.toString());
        }    
    }
    public void ModificarUsuarios(JTable Tabla_Usuarios,JTextField txtNombre,JTextField txtUsuario,JTextField txtContra,JComboBox Lista_Perfil){       
        setID(Integer.parseInt(Tabla_Usuarios.getValueAt(Tabla_Usuarios.getSelectedRow(),0).toString()));
        setNombreUSU(txtNombre.getText());
        setUsuario(txtUsuario.getText());
        setContraseña(txtContra.getText());  
        setPerfil((String) Lista_Perfil.getSelectedItem());  
        ConexionSQL ObjetoCon = new ConexionSQL();  
        String Consulta = "UPDATE inventariofcj.datos_usuarios SET datos_usuarios.NombreUSU = ?, datos_usuarios.Usuario = ?, datos_usuarios.Contraseña = ?, datos_usuarios.Perfil = ? WHERE datos_usuarios.ID = ?;";     
        try {  
            CallableStatement cs =ObjetoCon.getConnection().prepareCall(Consulta);   
            cs.setString(1, getNombreUSU());
            cs.setString(2, getUsuario());
            cs.setString(3, getContraseña());
            cs.setString(4, getPerfil());
            cs.setInt(5, getID());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");           
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en modificar, error " +e.toString());
        }
    }   
    public void EliminarDatos (JTable Tabla_Usuarios){
        setID(Integer.parseInt(Tabla_Usuarios.getValueAt(Tabla_Usuarios.getSelectedRow(),0).toString()));
        ConexionSQL ObjetoCon = new ConexionSQL(); 
        String Consulta = "DELETE FROM inventariofcj.datos_usuarios WHERE datos_usuarios.ID = ?";        
        try{
            CallableStatement cs =ObjetoCon.getConnection().prepareCall(Consulta);  
            cs.setInt(1, getID());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Usuario eliminado");  
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en eliminar, error " +e.toString());
        }
    }     
}