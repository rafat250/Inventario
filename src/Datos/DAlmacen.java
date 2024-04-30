package Datos;
import Logica.ConexionSQL;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
public class DAlmacen {
    String IDAlmacen;
    int IDLinea;
    String Descrip;
    int Stock;
    String CodigoB;
    String UMedida;
    Double PrecioU;
    String ImagenDir;
    
    public DAlmacen() {
    }

    public DAlmacen(String IDAlmacen, int IDLinea, String Descrip, int Stock,String CodigoB, String UMedida, Double PrecioU) {
        this.IDAlmacen = IDAlmacen;
        this.IDLinea = IDLinea;
        this.Descrip = Descrip;
        this.Stock = Stock;
        this.CodigoB = CodigoB;
        this.UMedida = UMedida;
        this.PrecioU = PrecioU;
    }
    
    public String getIDAlmacen() {
        return IDAlmacen;
    }

    public void setIDAlmacen(String IDAlmacen) {
        this.IDAlmacen = IDAlmacen;
    }

    public int getIDLinea() {
        return IDLinea;
    }

    public void setIDLinea(int IDLinea) {
        this.IDLinea = IDLinea;
    }

    public String getDescrip() {
        return Descrip;
    }

    public void setDescrip(String Descrip) {
        this.Descrip = Descrip;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public String getCodigoB() {
        return CodigoB;
    }

    public void setCodigoB(String CodigoB) {
        this.CodigoB = CodigoB;
    }

    public String getUMedida() {
        return UMedida;
    }

    public void setUMedida(String UMedida) {
        this.UMedida = UMedida;
    }

    public Double getPrecioU() {
        return PrecioU;
    }

    public void setPrecioU(Double PrecioU) {
        this.PrecioU = PrecioU;
    }

    public String getImagenDir() {
        return ImagenDir;
    }

    public void setImagenDir(String ImagenDir) {
        this.ImagenDir = ImagenDir;
    }
    
    public void InsertarDatosALM(JTextField txtLineaID,JTextField txtDescrip,JTextField txtCant,JTextField txtCodigoBarras,JComboBox txtUM,JTextField txtPrecioU, JLabel Imag){
        setIDLinea(Integer.parseInt(txtLineaID.getText()));
        setDescrip(txtDescrip.getText());
        setStock(Integer.parseInt(txtCant.getText()));
        setCodigoB(txtCodigoBarras.getText());
        setUMedida((String) txtUM.getSelectedItem());
        setPrecioU(Double.parseDouble(txtPrecioU.getText()));
        setImagenDir(Imag.getText());
        ConexionSQL ObjetoCon = new ConexionSQL();  
        Connection ob = ObjetoCon.getConnection();
        String Consulta = "INSERT INTO inventariofcj.Datos_Almacen (IDLinea,Descripcion,Stock,CodigoB,UMedida,PrecioUnitario,Imagen,ImagenDir) values (?,?,?,?,?,?,?,?);";
        try{
            PreparedStatement cs = ob.prepareStatement(Consulta);
            FileInputStream imagen = new FileInputStream(Imag.getText());
            cs.setInt(1, getIDLinea());
            cs.setString(2, getDescrip());
            cs.setInt(3, getStock());
            cs.setString(4, getCodigoB());
            cs.setString(5, getUMedida());
            cs.setDouble(6, getPrecioU());      
            cs.setBinaryStream(7, imagen,imagen.available());
            cs.setString(8,getImagenDir());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se agregaron los datos de forma correcta"); 
        }catch (SQLException | IOException e){
            JOptionPane.showMessageDialog(null, "No se inserto de forma correcta "+e.toString());
        }  
    }
    public void MostrarDatosALM(JTable Tabla_Inventario){
        ConexionSQL ObjetoCon = new ConexionSQL();     
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_Inventario.setRowSorter(OrdenarTabla);
        modelo.addColumn("IDCodigo");
        modelo.addColumn("IDLinea");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("CodigoB");
        modelo.addColumn("U/Medida");
        modelo.addColumn("P/Unitario");
        Tabla_Inventario.setModel(modelo);
        String sql = "select * from inventariofcj.Datos_Almacen";
        String[] datos = new String[7];
        Statement st;
        try {
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
                modelo.addRow(datos);
            }     
            Tabla_Inventario.setModel(modelo);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos "+e.toString());
        } 
    }
    public void MostrarImg(JTable Tabla_Inventario,JLabel Label_Foto){  
        try {          
            setIDAlmacen(Tabla_Inventario.getValueAt(Tabla_Inventario.getSelectedRow(), 0).toString());            
            ConexionSQL ObjetoCon = new ConexionSQL();
            Connection ob = ObjetoCon.getConnection(); 
            String sql = "Select Imagen FROM inventariofcj.Datos_Almacen WHERE IDAlmacen = ?";
            PreparedStatement statement = ob.prepareStatement(sql);
            statement.setString(1, getIDAlmacen());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                byte[] imagenBytes = resultSet.getBytes("Imagen");
                Image imagen = new ImageIcon(imagenBytes).getImage();
                imagen = imagen.getScaledInstance(90, 90, Image.SCALE_DEFAULT);
                Label_Foto.setIcon(new ImageIcon(imagen));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    public void SeleccionarALM(JTable Tabla_Inventario, JTextField txtLineaID, JTextField txtDescrip, JTextField txtCant,JTextField txtCodigoBarras, JTextField txtPrecioU, JLabel Imag){
        try{
            int fila = Tabla_Inventario.getSelectedRow();  
            if (fila >=0){ 
                setIDAlmacen(Tabla_Inventario.getValueAt(Tabla_Inventario.getSelectedRow(), 0).toString());            
                ConexionSQL ObjetoCon = new ConexionSQL();
                Connection ob = ObjetoCon.getConnection(); 
                String sql = "Select Imagen,ImagenDir FROM inventariofcj.Datos_Almacen WHERE IDAlmacen = ?";
                PreparedStatement statement = ob.prepareStatement(sql);
                statement.setString(1, getIDAlmacen());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String imagePath = resultSet.getString("ImagenDir");
                    Imag.setText(imagePath);
                }
                txtLineaID.setText((Tabla_Inventario.getValueAt(fila, 1).toString()));  
                txtDescrip.setText((Tabla_Inventario.getValueAt(fila, 2).toString()));  
                txtCant.setText((Tabla_Inventario.getValueAt(fila, 3).toString())); 
                txtCodigoBarras.setText(Tabla_Inventario.getValueAt(fila, 4).toString());
                txtPrecioU.setText((Tabla_Inventario.getValueAt(fila, 6).toString())); 
            }else {
                JOptionPane.showMessageDialog(null, "Fila no encontrada");
            }          
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la seleccion, error "+e.toString());
        }
    }
    public void ModificarALM(JTable Tabla_Inventario,JTextField txtLineaID,JTextField txtDescrip,JTextField txtCant,JTextField txtCodigoBarras,JComboBox txtUM,JTextField txtPrecioU){
        setIDAlmacen(Tabla_Inventario.getValueAt(Tabla_Inventario.getSelectedRow(), 0).toString());
        setIDLinea(Integer.parseInt(txtLineaID.getText()));
        setDescrip(txtDescrip.getText());
        setStock(Integer.parseInt(txtCant.getText()));
        setCodigoB(txtCodigoBarras.getText());
        setUMedida((String) txtUM.getSelectedItem());     
        setPrecioU(Double.parseDouble(txtPrecioU.getText()));
        ConexionSQL ObjetoCon = new ConexionSQL();  
        String Consulta = "UPDATE inventariofcj.Datos_Almacen SET Datos_Almacen.IDLinea = ?, Datos_Almacen.Descripcion = ?, Datos_Almacen.Stock = ?, Datos_Almacen.CodigoB = ?,Datos_Almacen.UMedida = ? , Datos_Almacen.PrecioUnitario = ? WHERE Datos_Almacen.IDAlmacen = ?;";       
        try {  
            CallableStatement cs =ObjetoCon.getConnection().prepareCall(Consulta);      
            cs.setInt(1, getIDLinea());
            cs.setString(2, getDescrip());
            cs.setInt(3, getStock());
            cs.setString(4, getCodigoB());
            cs.setString(5, getUMedida());
            cs.setDouble(6, getPrecioU());
            cs.setString(7, getIDAlmacen());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");           
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error en modificar, error " +e.toString());
        }
    }
    public void ModificarIMG(JTable Tabla_Inventario,JLabel Imag){
        setIDAlmacen(Tabla_Inventario.getValueAt(Tabla_Inventario.getSelectedRow(), 0).toString());
        setImagenDir(Imag.getText());
        ConexionSQL ObjetoCon = new ConexionSQL();  
        String Consulta = "UPDATE inventariofcj.Datos_Almacen SET Datos_Almacen.Imagen = ?, Datos_Almacen.ImagenDir = ? WHERE Datos_Almacen.IDAlmacen = ?;";       
        try {  
            CallableStatement cs =ObjetoCon.getConnection().prepareCall(Consulta);      
            FileInputStream imagen;
            imagen = new FileInputStream(Imag.getText());
            cs.setBinaryStream(1, imagen);
            cs.setString(2,getImagenDir());
            cs.setString(3, getIDAlmacen());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Imagen Modificada");           
        }catch (SQLException | IOException e){
            JOptionPane.showMessageDialog(null, "Error en modificar, error " +e.toString());
        }
    }
    public void EliminarDatos(JTable Tabla_Inventario){
        setIDAlmacen(Tabla_Inventario.getValueAt(Tabla_Inventario.getSelectedRow(), 0).toString());
        ConexionSQL ObjetoCon = new ConexionSQL(); 
        String Consulta = "DELETE FROM inventariofcj.Datos_Almacen WHERE Datos_Almacen.IDAlmacen = ?;";        
        try{
            CallableStatement cs =ObjetoCon.getConnection().prepareCall(Consulta);  
            cs.setString(1, getIDAlmacen());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Dato eliminado");  
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error en eliminar, error " +e.toString());
        }
    }
    public void MostrarDatosReport(JTable Tabla_Inventario){
        ConexionSQL ObjetoCon = new ConexionSQL();     
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_Inventario.setRowSorter(OrdenarTabla);
        modelo.addColumn("ID");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("U/Medida");
        modelo.addColumn("P/Unitario");
        modelo.addColumn("Total");
        modelo.addColumn("Linea");
        Tabla_Inventario.setModel(modelo);
        String sql = "Select IDAlmacen,Descripcion,Stock,UMedida,PrecioUnitario, (Stock*PrecioUnitario),Lineas as Total FROM inventariofcj.Datos_Almacen INNER JOIN inventariofcj.datos_lineas ON IDLinea = IDLin ;";
        String[] datos = new String[7];
        Statement st;
        try {
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
                modelo.addRow(datos);
            }     
            Tabla_Inventario.setModel(modelo);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos "+e.toString());
        }  
    } 
}