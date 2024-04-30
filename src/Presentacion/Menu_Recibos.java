package Presentacion;
import Datos.DAlmacen;
import Datos.DDetalles_Recibos;
import Datos.DRecibos;
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
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.Timer;
public class Menu_Recibos extends javax.swing.JFrame {
    DefaultTableModel MiModelo;
    public Menu_Recibos() {
        initComponents();
        MiModelo = (DefaultTableModel) Tabla_Recibos.getModel();
        setIconImage(getIconImage());
        Tabla_Recibos.getColumn("Codigo").setWidth(15);
        Tabla_Recibos.getColumn("Codigo").setPreferredWidth(15);     
        Tabla_Recibos.getColumn("Descripcion").setWidth(220);
        Tabla_Recibos.getColumn("Descripcion").setPreferredWidth(220);     
        Tabla_Recibos.getColumn("Cantidad").setWidth(20);
        Tabla_Recibos.getColumn("Cantidad").setPreferredWidth(20);  
        Tabla_Recibos.getColumn("P/Unitario").setWidth(20);
        Tabla_Recibos.getColumn("P/Unitario").setPreferredWidth(20); 
        Tabla_Recibos.getColumn("U/Medida").setWidth(20);
        Tabla_Recibos.getColumn("U/Medida").setPreferredWidth(20); 
        Tabla_Recibos.getColumn("Linea").setWidth(20);
        Tabla_Recibos.getColumn("Linea").setPreferredWidth(20); 
        Tabla_Recibos.getColumn("Total").setWidth(20);
        Tabla_Recibos.getColumn("Total").setPreferredWidth(20); 
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
        Tabla_Busqueda.setVisible(false);
        Tabla_Busqueda.getTableHeader().setVisible(false);
        jScrollPane2.setVisible(false);
        timer.start();
        habilitar(true);
        limpiar();
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
                txtFechaR.setText(dias+":"+(mes+1)+":"+aa);
                Label_H.setText(hh+":"+mm+":"+ss);
            }
    }); 
    public void habilitar(boolean b){
        txtPersonaR.setEnabled(!b);
        txtBuscar.setEnabled(!b);
        txtCliente.setEnabled(!b);
        txtProyecto.setEnabled(!b);
        txtUbi.setEnabled(!b);
        Bt_Guardar.setEnabled(!b);
        Bt_Cancelar.setEnabled(!b);
        Bt_Nuevo.setEnabled(b);
        Bt_Guardar.setEnabled(!b);
        Bt_Cancelar.setEnabled(!b);
        Bt_Eliminar.setEnabled(!b);
    }
    public void limpiar(){
        txtPersonaR.setText("");
        txtCliente.setText("");
        txtProyecto.setText("");
        txtUbi.setText("");
        txtBuscar.setText("");
        txtTotal.setText("");
        txtTotalCant.setText("");
        try {
            DefaultTableModel modelo = (DefaultTableModel) Tabla_Recibos.getModel();
            int filas = Tabla_Recibos.getRowCount();
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
        for(int i = 0; i < Tabla_Recibos.getRowCount(); i++){
            String codigo = Tabla_Recibos.getValueAt(i, 0).toString();
            if (codigo.equals(texto)){
                int cantidad = Integer.parseInt(Tabla_Recibos.getValueAt(i, 2).toString());
                double preciounitario = Double.parseDouble(Tabla_Recibos.getValueAt(i, 3).toString());
                int TotalCant = cantidad + 1;
                Tabla_Recibos.setValueAt(TotalCant, i, 2);    
                double TotalParci = TotalCant * preciounitario;
                Tabla_Recibos.setValueAt(ft.format(TotalParci), i, 6);                
                res = true;
            }
        }
        SumaTotal();
        SumarCant();
        return res;
    }   
    public void SumaTotal(){
        double TotalParcial= 0, TotalFinal = 0;
        for (int i=0; i < Tabla_Recibos.getRowCount(); i++){
            TotalParcial = Double.parseDouble(Tabla_Recibos.getValueAt(i, 6).toString());
            TotalFinal += TotalParcial;
            NumberFormat formatoDeMoneda = NumberFormat.getCurrencyInstance();
            String resultadoFormateado = formatoDeMoneda.format(TotalFinal);
            txtTotal.setText(resultadoFormateado);
        }
    }
    public void SumarCant(){
        DecimalFormat ft = new DecimalFormat("####.00");
        double TotalCantidad= 0, TotalFinalCant = 0;
        for (int i=0; i < Tabla_Recibos.getRowCount(); i++){
            TotalCantidad = Double.parseDouble(Tabla_Recibos.getValueAt(i, 2).toString());
            TotalFinalCant += TotalCantidad;
            txtTotalCant.setText(ft.format(TotalFinalCant));
        }      
    }
    public void MostrarProductos(String producto){
        LAlmacen LAL = new LAlmacen();
        DAlmacen ALM = new DAlmacen();
        DefaultTableModel TB = (DefaultTableModel) Tabla_Recibos.getModel();
        ALM.setIDAlmacen(producto);       
        String[] registros = LAL.AdquirirProductosRec(ALM);
        if (registros[0] != null){            
            TB.insertRow(0, registros);
            Tabla_Recibos.setModel(TB); 
        }else {
            JOptionPane.showMessageDialog(rootPane, "El producto no esta registrado");
        }
    }
    public void MostrarDatos(){      
        ConexionSQL ObjetoCon = new ConexionSQL();     
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_Busqueda.setRowSorter(OrdenarTabla);      
        modelo.addColumn("IDRecibo");
        modelo.addColumn("FechasR");
        modelo.addColumn("PersonaEntrega");
        modelo.addColumn("PersonaRecibe");
        modelo.addColumn("Cliente");
        modelo.addColumn("Proyecto");
        modelo.addColumn("Ubicacion");
        Tabla_Busqueda.setModel(modelo);
        String sql = "Select * from inventariofcj.Datos_Recibos WHERE IDRecibo = (SELECT MAX(IDRecibo) FROM inventariofcj.Datos_Recibos);";
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
            Tabla_Busqueda.setModel(modelo);    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos "+e.toString());
        }  
    }
    @Override
    public Image getIconImage (){
        Image Log = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/Recibos.png")); 
        return Log;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPersonaR = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtProyecto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtUbi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Label_H = new javax.swing.JLabel();
        txtFechaR = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Recibos = new javax.swing.JTable();
        Bt_Nuevo = new javax.swing.JButton();
        Bt_Guardar = new javax.swing.JButton();
        Bt_Cancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTotalCant = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        Bt_Eliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla_Busqueda = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu Salidas");

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salidas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fecha");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Persona Recibo");

        txtPersonaR.setBackground(new java.awt.Color(153, 153, 153));

        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("jLabel5");

        txtCliente.setBackground(new java.awt.Color(153, 153, 153));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Cliente");

        txtProyecto.setBackground(new java.awt.Color(153, 153, 153));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Proyecto");

        txtUbi.setBackground(new java.awt.Color(153, 153, 153));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Ubicacion");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Hora:");

        Label_H.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Label_H.setForeground(new java.awt.Color(255, 255, 255));
        Label_H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_H.setText("Hora");

        txtFechaR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtFechaR.setForeground(new java.awt.Color(255, 255, 255));
        txtFechaR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFechaR.setText("Fecha");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPersonaR, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                    .addComponent(txtFechaR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtUbi)
                    .addComponent(txtProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(Label_H, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(123, 123, 123))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtFechaR)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Label_H))))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPersonaR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar Productos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        txtBuscar.setBackground(new java.awt.Color(153, 153, 153));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        Tabla_Recibos.setBackground(new java.awt.Color(204, 204, 204));
        Tabla_Recibos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad", "P/Unitario", "U/Medida", "Linea", "Total"
            }
        ));
        Tabla_Recibos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabla_RecibosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla_Recibos);

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Total:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Agregar productos por el ID");

        txtTotalCant.setBackground(new java.awt.Color(153, 153, 153));
        txtTotalCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Total Cantidad:");

        txtTotal.setBackground(new java.awt.Color(153, 153, 153));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Bt_Eliminar.setBackground(new java.awt.Color(0, 0, 153));
        Bt_Eliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Bt_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Eliminar.setText("Borrar Producto");
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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1043, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Bt_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Bt_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Bt_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Bt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalCant, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(60, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Bt_Nuevo)
                                .addComponent(Bt_Guardar)
                                .addComponent(Bt_Cancelar)
                                .addComponent(Bt_Eliminar))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtTotalCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))))
        );

        Tabla_Busqueda.setBackground(new java.awt.Color(0, 0, 51));
        Tabla_Busqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(Tabla_Busqueda);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void Bt_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_CancelarActionPerformed
        limpiar();
        habilitar(true);
    }//GEN-LAST:event_Bt_CancelarActionPerformed

    private void Bt_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_GuardarActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) Tabla_Recibos.getModel();       
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro que quieres guardar estos datos?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION){
            if (txtPersonaR.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
                txtPersonaR.requestFocusInWindow();
            }else if (txtCliente.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
                txtCliente.requestFocusInWindow();
            }else if (txtProyecto.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
                txtProyecto.requestFocusInWindow();
            }else if (txtUbi.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
                txtUbi.requestFocusInWindow();
            }else if (modelo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
                txtBuscar.requestFocusInWindow();             
            }else {
                DRecibos misRecibos = new DRecibos();
                misRecibos.setPersonaEntrega(jLabel5.getText());
                misRecibos.setHora(Label_H.getText());
                misRecibos.InsertarDatosRecibo(txtFechaR, txtPersonaR, txtCliente, txtProyecto, txtUbi);
                MostrarDatos();
                DDetalles_Recibos misDetRec = new DDetalles_Recibos();
                int filas = Tabla_Recibos.getRowCount();
                for (int i=0; i < filas;i++){
                    int ID = Integer.parseInt(Tabla_Busqueda.getValueAt(0, 0).toString());
                    int IDDetalleRecib = ID;
                    int ReciboID = ID;
                    String idproduct = Tabla_Recibos.getValueAt(i, 0).toString();
                    int cant = Integer.parseInt(Tabla_Recibos.getValueAt(i, 2).toString());
                    String Linea = Tabla_Recibos.getValueAt(i, 5).toString();
                    double total = Double.parseDouble(Tabla_Recibos.getValueAt(i, 6).toString());      
                    misDetRec.setIDdetalleRecib(IDDetalleRecib);
                    misDetRec.setReciboID(ReciboID);
                    misDetRec.setProductosid(idproduct);
                    misDetRec.setLineas(Linea);
                    misDetRec.setCantidad(cant);
                    misDetRec.setTotal(total);
                    misDetRec.InsertarDatosDetalleRecibos();
                    LAlmacen funAl = new LAlmacen();
                    DAlmacen dtsp = new DAlmacen();
                    dtsp.setIDAlmacen(idproduct);
                    dtsp.setStock(cant);
                    String msgfinal = funAl.ReducirPiezas(dtsp);
                }
                JOptionPane.showMessageDialog(null, "Se inserto de forma correcta "); 
                limpiar();
                habilitar(true);  
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

    private void Tabla_RecibosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla_RecibosKeyPressed
        DecimalFormat ft = new DecimalFormat("####.00");
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            for (int i=0; i < Tabla_Recibos.getRowCount(); i++){
                int cantidad = Integer.parseInt(Tabla_Recibos.getValueAt(i, 2).toString());
                double preciounitario = Double.parseDouble(Tabla_Recibos.getValueAt(i, 4).toString());
                double totalparcial = cantidad * preciounitario;
                Tabla_Recibos.setValueAt(ft.format(totalparcial), i, 6);
            }
            SumaTotal();
            SumarCant();
        }
    }//GEN-LAST:event_Tabla_RecibosKeyPressed

    private void Bt_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_EliminarActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) Tabla_Recibos.getModel();
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro que quieres borrar el producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            int indiceFilaSeleccionada = Tabla_Recibos.getSelectedRow();       
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
            java.util.logging.Logger.getLogger(Menu_Recibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Recibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Recibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Recibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Recibos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bt_Cancelar;
    private javax.swing.JButton Bt_Eliminar;
    private javax.swing.JButton Bt_Guardar;
    private javax.swing.JButton Bt_Nuevo;
    private javax.swing.JLabel Label_H;
    private javax.swing.JTable Tabla_Busqueda;
    private javax.swing.JTable Tabla_Recibos;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JLabel txtFechaR;
    private javax.swing.JTextField txtPersonaR;
    private javax.swing.JTextField txtProyecto;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalCant;
    private javax.swing.JTextField txtUbi;
    // End of variables declaration//GEN-END:variables

    public void setUsuario(String personaE) {
        jLabel5.setText(personaE);
    }
}