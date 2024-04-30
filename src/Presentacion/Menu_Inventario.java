package Presentacion;
import Datos.DAlmacen;
import Logica.ConexionSQL;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
public class Menu_Inventario extends javax.swing.JFrame {
    public Menu_Inventario() {
        initComponents();
        DAlmacen mialm = new DAlmacen();
        mialm.MostrarDatosALM(Tabla_Inventario);
        setIconImage(getIconImage());
        ImageIcon icNuevo = new ImageIcon(getClass().getResource("/Imagenes/Nuevo.png"));
        Icon icononu = new ImageIcon(icNuevo.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        Bt_Nuevo.setIcon(icononu);        
        ImageIcon icEditar = new ImageIcon(getClass().getResource("/Imagenes/Editar.png"));
        Icon iconoEdi = new ImageIcon(icEditar.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        Bt_Editar.setIcon(iconoEdi);     
        ImageIcon icGuardar = new ImageIcon(getClass().getResource("/Imagenes/Guardar.png"));
        Icon iconoGu = new ImageIcon(icGuardar.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        Bt_Guardar.setIcon(iconoGu);       
        ImageIcon icEliminar = new ImageIcon(getClass().getResource("/Imagenes/Eliminar.png"));
        Icon iconoEli = new ImageIcon(icEliminar.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        Bt_Eliminar.setIcon(iconoEli);       
        ImageIcon icCancelar = new ImageIcon(getClass().getResource("/Imagenes/Cancelar.png"));
        Icon iconoCa = new ImageIcon(icCancelar.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        Bt_Cancelar.setIcon(iconoCa);    
        ImageIcon icModi = new ImageIcon(getClass().getResource("/Imagenes/Modificar.png"));
        Icon iconoMO = new ImageIcon(icModi.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        Bt_Modificar.setIcon(iconoMO);
        ImageIcon icEditarIMG = new ImageIcon(getClass().getResource("/Imagenes/Editar.png"));
        Icon iconoEdiIMG = new ImageIcon(icEditarIMG.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        Bt_Cambiar.setIcon(iconoEdiIMG);       
        ImageIcon icModiIMG = new ImageIcon(getClass().getResource("/Imagenes/Modificar.png"));
        Icon iconoMOIMG = new ImageIcon(icModiIMG.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        Bt_CambiarIMG.setIcon(iconoMOIMG);
        limpiar();
        habilitar(true);
    }
    public static void setLineas(String ID, String Linea){        
        txtLineaID.setText(ID);
    }
    public void limpiar(){
        txtDescrip.setText("");
        txtLineaID.setText("");
        txtCant.setText("");
        txtCodigoBarras.setText("");
        txtUM.setSelectedIndex(0);
        txtPrecioU.setText("");
        Imag.setText("DI");
        Label_Foto.setIcon(null);
        Label_Foto.setText("Imagen");
    }
    public void habilitar(boolean b){
        txtDescrip.setEnabled(!b);
        txtLineaID.setEnabled(!b);
        txtCant.setEnabled(!b);
        txtCodigoBarras.setEnabled(!b);
        txtUM.setEnabled(!b);          
        txtPrecioU.setEnabled(!b);
        Bt_Img.setEnabled(!b);
        Bt_MenuLinea.setEnabled(!b);
        Bt_Nuevo.setEnabled(b);
        Bt_Editar.setEnabled(b);
        Bt_Cambiar.setEnabled(b);
        Bt_Guardar.setEnabled(!b);
        Bt_Cancelar.setEnabled(!b);
        Bt_CambiarIMG.setEnabled(!b);
        Bt_Modificar.setEnabled(!b);
    }
    public void habilitarIMG(boolean b){
        Bt_Nuevo.setEnabled(b);
        Bt_Editar.setEnabled(b);
        Bt_Cambiar.setEnabled(b);
        Bt_CambiarIMG.setEnabled(!b);
        Bt_Img.setEnabled(!b);
        Bt_Cancelar.setEnabled(!b);
    }
    public void BuscarDatos(String valor){      
        ConexionSQL ObjetoCon = new ConexionSQL();     
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_Inventario.setRowSorter(OrdenarTabla);      
        modelo.addColumn("IDAlmacen");
        modelo.addColumn("IDUbicacion");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("CodigoB");
        modelo.addColumn("U/Medida");
        modelo.addColumn("P/Unitario");
        Tabla_Inventario.setModel(modelo);
        String sql = "select * from inventariofcj.Datos_Almacen WHERE Descripcion LIKE '%"+valor+"%'";
        String[] datos = new String[7];
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
                modelo.addRow(datos);
            }     
            Tabla_Inventario.setModel(modelo);    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        }     
    }
    public void BuscarCodigo (String valor2){
        ConexionSQL ObjetoCon = new ConexionSQL();     
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_Inventario.setRowSorter(OrdenarTabla);      
        modelo.addColumn("IDAlmacen");
        modelo.addColumn("IDUbicacion");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("CodigoB");
        modelo.addColumn("U/Medida");
        modelo.addColumn("P/Unitario");
        Tabla_Inventario.setModel(modelo);
        String sql = "select * from inventariofcj.Datos_Almacen WHERE CodigoB LIKE '%"+valor2+"%'";
        String[] datos = new String[7];
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
                modelo.addRow(datos);
            }     
            Tabla_Inventario.setModel(modelo);    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        }
    }  
    @Override
    public Image getIconImage (){
        Image Log = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/Almacen.png")); 
        return Log;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtLineaID = new javax.swing.JTextField();
        txtDescrip = new javax.swing.JTextField();
        txtPrecioU = new javax.swing.JTextField();
        Bt_Nuevo = new javax.swing.JButton();
        Bt_Editar = new javax.swing.JButton();
        Bt_Guardar = new javax.swing.JButton();
        Bt_Modificar = new javax.swing.JButton();
        txtCant = new javax.swing.JTextField();
        Bt_Cancelar = new javax.swing.JButton();
        Bt_MenuLinea = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Bt_Img = new javax.swing.JButton();
        Bt_CambiarIMG = new javax.swing.JButton();
        Bt_Cambiar = new javax.swing.JButton();
        Imag = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        Label_Foto = new javax.swing.JLabel();
        easter4 = new javax.swing.JLabel();
        txtUM = new javax.swing.JComboBox<>();
        txtCodigoBarras = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtbuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Bt_Eliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla_Inventario = new javax.swing.JTable();
        txtbuscarCodigoB = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Almacen");

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inventario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID Ubicacion");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Descripcion");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cantidad");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("U/Medida");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Precio Unitario");

        txtLineaID.setBackground(new java.awt.Color(153, 153, 153));

        txtDescrip.setBackground(new java.awt.Color(153, 153, 153));

        txtPrecioU.setBackground(new java.awt.Color(153, 153, 153));

        Bt_Nuevo.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Nuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Nuevo.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Nuevo.setText("Nuevo");
        Bt_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_NuevoActionPerformed(evt);
            }
        });

        Bt_Editar.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Editar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Editar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Editar.setText("Editar");
        Bt_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_EditarActionPerformed(evt);
            }
        });

        Bt_Guardar.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Guardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Guardar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Guardar.setText("Guardar");
        Bt_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_GuardarActionPerformed(evt);
            }
        });

        Bt_Modificar.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Modificar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Modificar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Modificar.setText("Modificar");
        Bt_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_ModificarActionPerformed(evt);
            }
        });

        txtCant.setBackground(new java.awt.Color(153, 153, 153));

        Bt_Cancelar.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Cancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Cancelar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Cancelar.setText("Cancelar");
        Bt_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_CancelarActionPerformed(evt);
            }
        });

        Bt_MenuLinea.setBackground(new java.awt.Color(204, 204, 204));
        Bt_MenuLinea.setText("......");
        Bt_MenuLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_MenuLineaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Imagen articulo");

        Bt_Img.setBackground(new java.awt.Color(204, 204, 204));
        Bt_Img.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Bt_Img.setText("Elegir Imagen");
        Bt_Img.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_ImgActionPerformed(evt);
            }
        });

        Bt_CambiarIMG.setBackground(new java.awt.Color(0, 51, 153));
        Bt_CambiarIMG.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_CambiarIMG.setForeground(new java.awt.Color(255, 255, 255));
        Bt_CambiarIMG.setText("Cambiar IMG");
        Bt_CambiarIMG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_CambiarIMGActionPerformed(evt);
            }
        });

        Bt_Cambiar.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Cambiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Cambiar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Cambiar.setText("Editar Imagen");
        Bt_Cambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_CambiarActionPerformed(evt);
            }
        });

        Imag.setForeground(new java.awt.Color(255, 255, 255));
        Imag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Imag.setText("NI");

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        Label_Foto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Label_Foto.setForeground(new java.awt.Color(255, 255, 255));
        Label_Foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Foto.setText("Imagen");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_Foto, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_Foto, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        easter4.setForeground(new java.awt.Color(0, 0, 102));
        easter4.setText("jLabel8");
        easter4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                easter4MousePressed(evt);
            }
        });

        txtUM.setBackground(new java.awt.Color(153, 153, 153));
        txtUM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una unidad", "Piezas", "Metros", "Cajas", "mm", "Litros" }));

        txtCodigoBarras.setBackground(new java.awt.Color(153, 153, 153));
        txtCodigoBarras.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Codigo de Barras");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtLineaID, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Bt_MenuLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescrip, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(easter4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Bt_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Bt_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Bt_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Bt_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(Bt_Cambiar, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                            .addComponent(Bt_CambiarIMG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(Bt_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPrecioU)
                            .addComponent(txtUM, 0, 164, Short.MAX_VALUE)
                            .addComponent(Bt_Img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Imag, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtLineaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Bt_MenuLinea))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Bt_Nuevo)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Bt_Editar)
                                .addComponent(Bt_Cambiar)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Bt_Guardar)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Bt_Modificar)
                                .addComponent(Bt_CambiarIMG)))
                        .addGap(18, 18, 18)
                        .addComponent(Bt_Cancelar)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(easter4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPrecioU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Bt_Img)
                                    .addComponent(jLabel2)))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Imag)
                        .addGap(25, 25, 25))))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        txtbuscar.setBackground(new java.awt.Color(153, 153, 153));
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buscar");

        Bt_Eliminar.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Eliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Eliminar.setText("Eliminar");
        Bt_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_EliminarActionPerformed(evt);
            }
        });

        Tabla_Inventario.setBackground(new java.awt.Color(204, 204, 204));
        Tabla_Inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Tabla_Inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_InventarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabla_Inventario);

        txtbuscarCodigoB.setBackground(new java.awt.Color(153, 153, 153));
        txtbuscarCodigoB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtbuscarCodigoB.setToolTipText("");
        txtbuscarCodigoB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarCodigoBKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Buscar Codigo de Barras");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbuscarCodigoB, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Bt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bt_Eliminar)
                    .addComponent(txtbuscarCodigoB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Bt_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_NuevoActionPerformed
        limpiar();
        habilitar(false);
        Bt_Modificar.setEnabled(false);
        Bt_Cambiar.setEnabled(false);
        Bt_CambiarIMG.setEnabled(false);
    }//GEN-LAST:event_Bt_NuevoActionPerformed

    private void Bt_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_EditarActionPerformed
        habilitar(false);
        Bt_Guardar.setEnabled(false);
        Bt_Cambiar.setEnabled(false);
        Bt_CambiarIMG.setEnabled(false);
    }//GEN-LAST:event_Bt_EditarActionPerformed

    private void Bt_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_GuardarActionPerformed
        if (txtLineaID.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            txtLineaID.requestFocusInWindow();
        }else if (txtDescrip.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            txtDescrip.requestFocusInWindow();
        }else if (txtCant.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            txtCant.requestFocusInWindow();
        } else if (txtCodigoBarras.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            txtCodigoBarras.requestFocusInWindow();
        }else if (txtUM.getSelectedItem() == "Selecciona una unidad"){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            txtUM.requestFocusInWindow();
        }else if (txtPrecioU.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            txtPrecioU.requestFocusInWindow();
        } else if (Imag.getText().equals("NI")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            Bt_Img.requestFocusInWindow();
        }else {
            DAlmacen mialm = new DAlmacen();
            mialm.InsertarDatosALM(txtLineaID, txtDescrip, txtCant, txtCodigoBarras, txtUM, txtPrecioU,Imag);
            mialm.MostrarDatosALM(Tabla_Inventario);
            limpiar();
            habilitar(true);
            Bt_Cambiar.setEnabled(true);
        }
    }//GEN-LAST:event_Bt_GuardarActionPerformed

    private void Bt_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_ModificarActionPerformed
        if (txtLineaID.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            txtLineaID.requestFocusInWindow();
        }else if (txtDescrip.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            txtDescrip.requestFocusInWindow();
        }else if (txtCant.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            txtCant.requestFocusInWindow();
        }else if (txtCodigoBarras.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            txtCodigoBarras.requestFocusInWindow();
        }else if (txtUM.getSelectedItem() == "Selecciona una unidad"){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            txtUM.requestFocusInWindow();
        }else if (txtPrecioU.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            txtPrecioU.requestFocusInWindow();
        } else if (Imag.getText().equals("NI")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            Bt_Img.requestFocusInWindow();
        }else {
            DAlmacen mialm = new DAlmacen();
            mialm.ModificarALM(Tabla_Inventario, txtLineaID, txtDescrip, txtCant, txtCodigoBarras, txtUM, txtPrecioU);
            mialm.MostrarDatosALM(Tabla_Inventario);
            limpiar();
            habilitar(true);
            Bt_Cambiar.setEnabled(true);
        }
    }//GEN-LAST:event_Bt_ModificarActionPerformed

    private void Bt_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_CancelarActionPerformed
        limpiar();
        habilitar(true);
        Bt_CambiarIMG.setEnabled(false);
        Bt_Cambiar.setEnabled(true);
    }//GEN-LAST:event_Bt_CancelarActionPerformed

    private void Bt_MenuLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_MenuLineaActionPerformed
        Dialog_BuscarLineasProvee MILP = new Dialog_BuscarLineasProvee(new JFrame(),true);
        MILP.setLocationRelativeTo(null);
        MILP.setVisible(true);
    }//GEN-LAST:event_Bt_MenuLineaActionPerformed

    private void Bt_ImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_ImgActionPerformed
        JFileChooser seleccionar = new JFileChooser();
        int opcion = seleccionar.showSaveDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION){
            File file = seleccionar.getSelectedFile();
            Imag.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(Imag.getText());
            foto = foto.getScaledInstance(90, 90, Image.SCALE_DEFAULT);
            Label_Foto.setText("");
            Label_Foto.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_Bt_ImgActionPerformed

    private void Bt_CambiarIMGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_CambiarIMGActionPerformed
        if (Imag.getText().equals("NI")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            Bt_Img.requestFocusInWindow();
        }else if (Imag.getText().equals("DI")) {
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            Bt_Img.requestFocusInWindow();
        }else {
            DAlmacen mialm = new DAlmacen();
            mialm.ModificarIMG(Tabla_Inventario, Imag);
            limpiar();
            habilitar(true);
        }
    }//GEN-LAST:event_Bt_CambiarIMGActionPerformed

    private void Bt_CambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_CambiarActionPerformed
        limpiar();
        habilitarIMG(false);
    }//GEN-LAST:event_Bt_CambiarActionPerformed

    private void easter4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_easter4MousePressed
        if (evt.getClickCount() == 5){
            JOptionPane.showMessageDialog(null, "Soy el inventario de la empresa GRUPO FCJ");
            JOptionPane.showMessageDialog(null, "Mi creador es: RafaT250 (Rafael Garcia Garcia)");
            JOptionPane.showMessageDialog(null, "Te felicito por encontrar este Easter Egg de mi creador");
            JOptionPane.showMessageDialog(null, "Ahora seguire con mi funcionamiento");
        }
    }//GEN-LAST:event_easter4MousePressed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        BuscarDatos(txtbuscar.getText());
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void Bt_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_EliminarActionPerformed
        int opcion1 = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres borrar este articulo?", "Confirmación", JOptionPane.YES_NO_OPTION);      
        if (opcion1 == JOptionPane.YES_OPTION){    
            if (txtLineaID.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Debe elegir un articulo a eliminar","Validacion",JOptionPane.WARNING_MESSAGE);
                Bt_MenuLinea.requestFocusInWindow();              
            }else {
                DAlmacen mialm = new DAlmacen();
                mialm.EliminarDatos(Tabla_Inventario);
                mialm.MostrarDatosALM(Tabla_Inventario);
                limpiar();
            }   
        } else {
        }  
    }//GEN-LAST:event_Bt_EliminarActionPerformed

    private void Tabla_InventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_InventarioMouseClicked
        Label_Foto.setText("");
        DAlmacen mialm = new DAlmacen();
        mialm.SeleccionarALM(Tabla_Inventario, txtLineaID, txtDescrip, txtCant,txtCodigoBarras, txtPrecioU, Imag);
        mialm.MostrarImg(Tabla_Inventario, Label_Foto);
    }//GEN-LAST:event_Tabla_InventarioMouseClicked

    private void txtbuscarCodigoBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarCodigoBKeyReleased
        BuscarCodigo(txtbuscarCodigoB.getText());
    }//GEN-LAST:event_txtbuscarCodigoBKeyReleased

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bt_Cambiar;
    private javax.swing.JButton Bt_CambiarIMG;
    private javax.swing.JButton Bt_Cancelar;
    private javax.swing.JButton Bt_Editar;
    private javax.swing.JButton Bt_Eliminar;
    private javax.swing.JButton Bt_Guardar;
    private javax.swing.JButton Bt_Img;
    private javax.swing.JButton Bt_MenuLinea;
    private javax.swing.JButton Bt_Modificar;
    private javax.swing.JButton Bt_Nuevo;
    private javax.swing.JLabel Imag;
    private javax.swing.JLabel Label_Foto;
    private javax.swing.JTable Tabla_Inventario;
    private javax.swing.JLabel easter4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCodigoBarras;
    private javax.swing.JTextField txtDescrip;
    private static javax.swing.JTextField txtLineaID;
    private javax.swing.JTextField txtPrecioU;
    private javax.swing.JComboBox<String> txtUM;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtbuscarCodigoB;
    // End of variables declaration//GEN-END:variables
}