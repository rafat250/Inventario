package Datos;
import Logica.ConexionSQL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
public class DLineas {
    int ID;
    String Lineas;

    public DLineas() {
    }

    public DLineas(int ID, String Lineas) {
        this.ID = ID;
        this.Lineas = Lineas;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLineas() {
        return Lineas;
    }

    public void setLineas(String Lineas) {
        this.Lineas = Lineas;
    }
    
    public void InsertarDatosLineas(JTextField txtLinea){
        setLineas(txtLinea.getText());
        ConexionSQL ObjetoCon = new ConexionSQL();  
        String Consulta = "INSERT INTO inventariofcj.Datos_Lineas (Lineas) values (?);";
        try{
            CallableStatement cs = ObjetoCon.getConnection().prepareCall(Consulta);
            cs.setString(1,getLineas());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se agrego la ubicacion de forma correcta"); 
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se inserto de forma correcta"+e.toString());
        }   
    }
    public void MostrarDatosLineas(JTable Tabla_Lineas){
        ConexionSQL ObjetoCon = new ConexionSQL();     
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_Lineas.setRowSorter(OrdenarTabla);      
        modelo.addColumn("ID");
        modelo.addColumn("Ubicaciones");
        String sql = "Select * from Datos_Lineas";
        String[] datos = new String[2];
        Statement st;
        try{
            st = ObjetoCon.getConnection().createStatement();  
            ResultSet rs = st.executeQuery(sql); 
            while (rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                modelo.addRow(datos);
            }
            Tabla_Lineas.setModel(modelo);            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        }   
    }
    public void SeleccionarLineas(JTable Tabla_Lineas,JTextField txtLinea){
        try{
            int fila = Tabla_Lineas.getSelectedRow();
            if (fila >=0) {
                txtLinea.setText((Tabla_Lineas.getValueAt(fila, 1).toString()));  
            }else {
                JOptionPane.showMessageDialog(null, "Fila no encontrada");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la seleccion, error"+e.toString());
        }
    }
    public void ModificarLineas(JTable Tabla_Lineas,JTextField txtLinea){
        setID(Integer.parseInt(Tabla_Lineas.getValueAt(Tabla_Lineas.getSelectedRow(), 0).toString()));
        setLineas(txtLinea.getText());
        ConexionSQL ObjetoCon = new ConexionSQL();  
        String Consulta = "UPDATE inventariofcj.Datos_Lineas SET Datos_Lineas.Lineas = ? WHERE Datos_Lineas.IDLin = ?;";
        try{
            CallableStatement cs =ObjetoCon.getConnection().prepareCall(Consulta);   
            cs.setString(1, getLineas());
            cs.setInt(2, getID());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en modificar, error" +e.toString());
        }
    }
    public void EliminarLineas(JTable Tabla_Lineas){
        setID(Integer.parseInt(Tabla_Lineas.getValueAt(Tabla_Lineas.getSelectedRow(), 0).toString()));
        ConexionSQL ObjetoCon = new ConexionSQL(); 
        String Consulta = "Delete from inventariofcj.Datos_Lineas WHERE Datos_Lineas.IDLin = ?;";        
        try{
            CallableStatement cs =ObjetoCon.getConnection().prepareCall(Consulta);  
            cs.setInt(1, getID());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Ubicacion eliminada");  
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en eliminar, error" +e.toString());
        }
    }    
}