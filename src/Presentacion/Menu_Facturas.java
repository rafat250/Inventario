package Presentacion;
import Datos.DAlmacen;
import Datos.DDetalleFactura;
import Datos.DFacturas_Ingreso;
import Logica.ConexionSQL;
import Logica.LAlmacen;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.text.NumberFormat;
public class Menu_Facturas extends javax.swing.JFrame {
    DefaultTableModel MiModelo;
    public Menu_Facturas() {
        initComponents();
        MiModelo = (DefaultTableModel) Tabla_Facturas.getModel();
        setIconImage(getIconImage());
        Tabla_Facturas.getColumn("Codigo").setWidth(15);
        Tabla_Facturas.getColumn("Codigo").setPreferredWidth(15);     
        Tabla_Facturas.getColumn("Descripcion").setWidth(220);
        Tabla_Facturas.getColumn("Descripcion").setPreferredWidth(220);     
        Tabla_Facturas.getColumn("Cantidad").setWidth(20);
        Tabla_Facturas.getColumn("Cantidad").setPreferredWidth(20);      
        Tabla_Facturas.getColumn("U/Medida").setWidth(20);
        Tabla_Facturas.getColumn("U/Medida").setPreferredWidth(20);      
        Tabla_Facturas.getColumn("P/Unitario").setWidth(20);
        Tabla_Facturas.getColumn("P/Unitario").setPreferredWidth(20);       
        Tabla_Facturas.getColumn("Total").setWidth(20);
        Tabla_Facturas.getColumn("Total").setPreferredWidth(20);      
        ImageIcon icNuevo = new ImageIcon(getClass().getResource("/Imagenes/Nuevo.png"));
        Icon icononu = new ImageIcon(icNuevo.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        Bt_Nuevo.setIcon(icononu);
        ImageIcon icGuardar = new ImageIcon(getClass().getResource("/Imagenes/Guardar.png"));
        Icon iconoGu = new ImageIcon(icGuardar.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        Bt_Guardar.setIcon(iconoGu);   
        ImageIcon icCancelar = new ImageIcon(getClass().getResource("/Imagenes/Cancelar.png"));
        Icon iconoCa = new ImageIcon(icCancelar.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        Bt_Cancelar.setIcon(iconoCa);
        ImageIcon icEliminar = new ImageIcon(getClass().getResource("/Imagenes/Eliminar.png"));
        Icon iconoELI = new ImageIcon(icEliminar.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        Bt_Eliminar.setIcon(iconoELI);
        txtTotal.setEditable(false);
        txtTotalCant.setEditable(false);
        txtLinID.setEnabled(false);
        txtProveeID.setEnabled(false); 
        txtLinea.setEnabled(false);
        txtProvee.setEnabled(false);
        Tabla_Busqueda.setVisible(false);
        Tabla_Busqueda.getTableHeader().setVisible(false);
        jScrollPane3.setVisible(false);
        timer.start();
        habilitar(true);
    }   
    Timer timer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Calendar cal = new GregorianCalendar();
                int hh, mm, ss, dias, mes, aa;
                hh = cal.get(Calendar.HOUR_OF_DAY);
                ss = cal.get(Calendar.SECOND);
                mm = cal.get(Calendar.MINUTE);              
                dias = cal.get(Calendar.DAY_OF_MONTH);
                mes = cal.get(Calendar.MONTH);
                aa = cal.get(Calendar.YEAR);            
                txtFecha.setText(dias+":"+(mes+1)+":"+aa);
                Label_H.setText(hh+":"+mm+":"+ss);
            }
    });
    public void habilitar(boolean b){
        txtBuscar.setEnabled(!b);
        txtProyecto.setEnabled(!b);
        txtUbi.setEnabled(!b);
        Bt_MenuLinea.setEnabled(!b);
        Bt_MenuProvee.setEnabled(!b);
        Bt_Nuevo.setEnabled(b);
        Bt_Guardar.setEnabled(!b);
        Bt_Cancelar.setEnabled(!b);
        Bt_Eliminar.setEnabled(!b);
    }
    public static void setProveedores(String ID, String Proveedor){
        txtProveeID.setText(ID);
        txtProvee.setText(Proveedor);
    }
    public static void setLineas(String ID, String Linea){
        txtLinID.setText(ID);
        txtLinea.setText(Linea);
    }   
    public void limpiar(){
        txtLinID.setText("");
        txtLinea.setText("");
        txtProveeID.setText("");
        txtProvee.setText("");
        txtProyecto.setText("");
        txtUbi.setText("");
        txtTotal.setText("");
        txtTotalCant.setText("");
        try {
            DefaultTableModel modelo = (DefaultTableModel) Tabla_Facturas.getModel();
            int filas = Tabla_Facturas.getRowCount();
            for(int i = 0; filas > i;i++){
                modelo.removeRow(0);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(rootPane, "No se pudo limpiar la tabla");
        }
    }
    public boolean estaenTabla(String texto){
        boolean res = false;
        DecimalFormat ft = new DecimalFormat("####.00");
        for(int i = 0; i < Tabla_Facturas.getRowCount(); i++){
            String codigo = Tabla_Facturas.getValueAt(i, 0).toString();
            if (codigo.equals(texto)){
                int cantidad = Integer.parseInt(Tabla_Facturas.getValueAt(i, 2).toString());
                double preciounitario = Double.parseDouble(Tabla_Facturas.getValueAt(i, 4).toString());
                int TotalCant = cantidad + 1;
                Tabla_Facturas.setValueAt(TotalCant, i, 2);
                
                double TotalParci = TotalCant * preciounitario;
                Tabla_Facturas.setValueAt(ft.format(TotalParci), i, 5);                
                res = true;
            }
        }
        SumaTotal();
        SumarCant();
        return res;
    }
    public void SumaTotal(){
        double TotalParcial= 0, TotalFinal = 0;
        for (int i=0; i < Tabla_Facturas.getRowCount(); i++){
            TotalParcial = Double.parseDouble(Tabla_Facturas.getValueAt(i, 5).toString());
            TotalFinal += TotalParcial;           
            NumberFormat formatoDeMoneda = NumberFormat.getCurrencyInstance();
            String resultadoFormateado = formatoDeMoneda.format(TotalFinal);           
            txtTotal.setText(resultadoFormateado);
        }
    }
    public void SumarCant(){
        DecimalFormat ft = new DecimalFormat("####.00");
        double TotalCantidad= 0, TotalFinalCant = 0;
        for (int i=0; i < Tabla_Facturas.getRowCount(); i++){
            TotalCantidad = Double.parseDouble(Tabla_Facturas.getValueAt(i, 2).toString());
            TotalFinalCant += TotalCantidad;           
            txtTotalCant.setText(ft.format(TotalFinalCant));
        }
    }
    public void MostrarProductos(String producto){
        LAlmacen LAL = new LAlmacen();
        DAlmacen ALM = new DAlmacen();
        DefaultTableModel TB = (DefaultTableModel) Tabla_Facturas.getModel();
        ALM.setIDAlmacen(producto); 
        String[] registros = LAL.AdquirirProductos(ALM);
        if (registros[0] != null){            
            TB.insertRow(0, registros);
            Tabla_Facturas.setModel(TB); 
        }else {
            JOptionPane.showMessageDialog(null, "El producto no esta registrado");
        }
    }   
    public void MostrarDatos(){      
        ConexionSQL ObjetoCon = new ConexionSQL();     
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_Busqueda.setRowSorter(OrdenarTabla);      
        modelo.addColumn("IDFacturas");
        modelo.addColumn("IDLinea");
        modelo.addColumn("Linea");
        modelo.addColumn("IDProvee");
        modelo.addColumn("Provee");
        modelo.addColumn("FechaEntrada");
        modelo.addColumn("Proyecto");
        modelo.addColumn("Ubicacion");
        modelo.addColumn("Hora");
        Tabla_Busqueda.setModel(modelo);
        String sql = "Select * from inventariofcj.Datos_ingresofactu WHERE IDFacturas = (SELECT MAX(IDFacturas) FROM inventariofcj.Datos_ingresofactu);";
        String[] datos = new String[9];
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
                modelo.addRow(datos);
            }     
            Tabla_Busqueda.setModel(modelo);    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos "+e.toString());
        }     
    }
    @Override
    public Image getIconImage (){
        Image Log = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/Facturas.png")); 
        return Log;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtLinea = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Bt_MenuLinea = new javax.swing.JButton();
        txtLinID = new javax.swing.JTextField();
        txtProvee = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Bt_MenuProvee = new javax.swing.JButton();
        txtProveeID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtProyecto = new javax.swing.JTextField();
        txtUbi = new javax.swing.JTextField();
        Label_H = new javax.swing.JLabel();
        txtFecha = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Facturas = new javax.swing.JTable();
        Bt_Nuevo = new javax.swing.JButton();
        Bt_Guardar = new javax.swing.JButton();
        Bt_Cancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTotalCant = new javax.swing.JTextField();
        Bt_Eliminar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla_Busqueda = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu Altas");

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Altas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        txtLinea.setBackground(new java.awt.Color(153, 153, 153));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ubicacion:");

        Bt_MenuLinea.setBackground(new java.awt.Color(204, 204, 204));
        Bt_MenuLinea.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Bt_MenuLinea.setText("......");
        Bt_MenuLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_MenuLineaActionPerformed(evt);
            }
        });

        txtLinID.setBackground(new java.awt.Color(153, 153, 153));

        txtProvee.setBackground(new java.awt.Color(153, 153, 153));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Proveedor:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha: ");

        Bt_MenuProvee.setBackground(new java.awt.Color(204, 204, 204));
        Bt_MenuProvee.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Bt_MenuProvee.setText("......");
        Bt_MenuProvee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_MenuProveeActionPerformed(evt);
            }
        });

        txtProveeID.setBackground(new java.awt.Color(153, 153, 153));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Hora:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Ubicacion");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Proyecto");

        txtProyecto.setBackground(new java.awt.Color(153, 153, 153));

        txtUbi.setBackground(new java.awt.Color(153, 153, 153));

        Label_H.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Label_H.setForeground(new java.awt.Color(255, 255, 255));
        Label_H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_H.setText("Hora");

        txtFecha.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(255, 255, 255));
        txtFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFecha.setText("Fecha");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Bt_MenuLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtLinID, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addComponent(txtProvee, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Bt_MenuProvee, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtProveeID, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(txtUbi)
                    .addComponent(Label_H, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(71, 71, 71))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(Label_H)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(Bt_MenuLinea)
                            .addComponent(txtLinID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Bt_MenuProvee)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtProvee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(txtProveeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtFecha))))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar Productos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        txtBuscar.setBackground(new java.awt.Color(153, 153, 153));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Agregar productos por el ID");

        Tabla_Facturas.setBackground(new java.awt.Color(204, 204, 204));
        Tabla_Facturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad", "U/Medida", "P/Unitario", "Total"
            }
        ));
        Tabla_Facturas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabla_FacturasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla_Facturas);

        Bt_Nuevo.setBackground(new java.awt.Color(0, 0, 153));
        Bt_Nuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Bt_Nuevo.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Nuevo.setText("Nuevo");
        Bt_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_NuevoActionPerformed(evt);
            }
        });

        Bt_Guardar.setBackground(new java.awt.Color(0, 0, 153));
        Bt_Guardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Bt_Guardar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Guardar.setText("Guardar");
        Bt_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_GuardarActionPerformed(evt);
            }
        });

        Bt_Cancelar.setBackground(new java.awt.Color(0, 0, 153));
        Bt_Cancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Bt_Cancelar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Cancelar.setText("Cancelar");
        Bt_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_CancelarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Total:");

        txtTotal.setBackground(new java.awt.Color(153, 153, 153));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Total Cantidad:");

        txtTotalCant.setBackground(new java.awt.Color(153, 153, 153));
        txtTotalCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Bt_Eliminar.setBackground(new java.awt.Color(0, 0, 153));
        Bt_Eliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Bt_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Eliminar.setText("Borrar producto");
        Bt_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_EliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Bt_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Bt_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Bt_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Bt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotalCant, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Bt_Nuevo)
                        .addComponent(Bt_Guardar)
                        .addComponent(Bt_Cancelar)
                        .addComponent(Bt_Eliminar))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotalCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        Tabla_Busqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(Tabla_Busqueda);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Bt_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_NuevoActionPerformed
        limpiar();
        habilitar(false);
    }//GEN-LAST:event_Bt_NuevoActionPerformed

    private void Bt_MenuLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_MenuLineaActionPerformed
        Dialog_BuscarLineas MIL = new Dialog_BuscarLineas(new JFrame(),true);
        MIL.setLocationRelativeTo(null);
        MIL.setVisible(true);
    }//GEN-LAST:event_Bt_MenuLineaActionPerformed

    private void Bt_MenuProveeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_MenuProveeActionPerformed
        Dialog_BuscarProvee MPRO = new Dialog_BuscarProvee(new JFrame(),true);
        MPRO.setLocationRelativeTo(null);
        MPRO.setVisible(true);
    }//GEN-LAST:event_Bt_MenuProveeActionPerformed

    private void Bt_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_GuardarActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) Tabla_Facturas.getModel();
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas realizar esta acción?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            if (txtLinea.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
                Bt_MenuLinea.requestFocusInWindow();
            }else if (txtProvee.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
                Bt_MenuProvee.requestFocusInWindow();
            }else if (txtProyecto.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
                txtProyecto.requestFocusInWindow();
            }else if (txtUbi.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
                txtUbi.requestFocusInWindow();
            }else if (modelo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
                txtBuscar.requestFocusInWindow();
            }else{
                DFacturas_Ingreso misingresos = new DFacturas_Ingreso();
                misingresos.setHora(Label_H.getText());
                misingresos.InsertarDatosFac_Ingreso(txtLinID, txtLinea, txtProveeID, txtProvee, txtFecha, txtProyecto, txtUbi); 
                MostrarDatos();
                DDetalleFactura misfacturas = new DDetalleFactura(); 
                int filas = Tabla_Facturas.getRowCount();
                for (int i=0; i < filas;i++){
                    int ID = Integer.parseInt(Tabla_Busqueda.getValueAt(0, 0).toString());
                    int IDDetalleFactura = ID;
                    int IDFactura = ID;
                    String idproduct = Tabla_Facturas.getValueAt(i, 0).toString();
                    int cant = Integer.parseInt(Tabla_Facturas.getValueAt(i, 2).toString());
                    double total = Double.parseDouble(Tabla_Facturas.getValueAt(i, 5).toString());
                    misfacturas.setIDDetalleFacturas(IDDetalleFactura);
                    misfacturas.setFacturaID(IDFactura);
                    misfacturas.setProductosID(idproduct);
                    misfacturas.setCantidad(cant);
                    misfacturas.setTotal(total);
                    misfacturas.InsertarDatosDetalleFactura();
                }
                JOptionPane.showMessageDialog(null, "Se inserto de forma correcta"); 
                habilitar(true);
                limpiar(); 
            }       
        } 
    }//GEN-LAST:event_Bt_GuardarActionPerformed
    
    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        int key = evt.getKeyCode();
        if(key == KeyEvent.VK_ENTER){
            boolean b = estaenTabla(txtBuscar.getText());
            if (b == false){
               MostrarProductos(txtBuscar.getText()); 
            }  
            SumaTotal();
            SumarCant();
            txtBuscar.setText("");
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void Bt_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_CancelarActionPerformed
        limpiar();
        habilitar(true);
    }//GEN-LAST:event_Bt_CancelarActionPerformed

    private void Tabla_FacturasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla_FacturasKeyPressed
        DecimalFormat ft = new DecimalFormat("####.00");
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            for (int i=0; i < Tabla_Facturas.getRowCount(); i++){
                int cantidad = Integer.parseInt(Tabla_Facturas.getValueAt(i, 2).toString());
                double preciounitario = Double.parseDouble(Tabla_Facturas.getValueAt(i, 4).toString());
                double totalparcial = cantidad * preciounitario;
                Tabla_Facturas.setValueAt(ft.format(totalparcial), i, 5);
            }
            SumaTotal();
            SumarCant();
        }
    }//GEN-LAST:event_Tabla_FacturasKeyPressed

    private void Bt_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_EliminarActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) Tabla_Facturas.getModel();
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres borrar estos datos?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            int indiceFilaSeleccionada = Tabla_Facturas.getSelectedRow();       
            if (indiceFilaSeleccionada != -1) {
                MiModelo.removeRow(indiceFilaSeleccionada);
            }
            SumaTotal();
            SumarCant();
            if (modelo.getRowCount() == 0){
                txtTotalCant.setText("");
                txtTotal.setText("");              
            }   
        }       
    }//GEN-LAST:event_Bt_EliminarActionPerformed
 
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
            java.util.logging.Logger.getLogger(Menu_Facturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Facturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Facturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Facturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Facturas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bt_Cancelar;
    private javax.swing.JButton Bt_Eliminar;
    private javax.swing.JButton Bt_Guardar;
    private javax.swing.JButton Bt_MenuLinea;
    private javax.swing.JButton Bt_MenuProvee;
    private javax.swing.JButton Bt_Nuevo;
    private javax.swing.JLabel Label_H;
    private javax.swing.JTable Tabla_Busqueda;
    private javax.swing.JTable Tabla_Facturas;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JLabel txtFecha;
    private static javax.swing.JTextField txtLinID;
    private static javax.swing.JTextField txtLinea;
    private static javax.swing.JTextField txtProvee;
    private static javax.swing.JTextField txtProveeID;
    private javax.swing.JTextField txtProyecto;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalCant;
    private javax.swing.JTextField txtUbi;
    // End of variables declaration//GEN-END:variables
}