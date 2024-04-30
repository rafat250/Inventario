package Presentacion;
import Datos.DLineas;
import Logica.ConexionSQL;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
public class Menu_Lineas extends javax.swing.JFrame {
    public Menu_Lineas() {
        initComponents(); 
        DLineas VistaLinea = new DLineas();
        VistaLinea.MostrarDatosLineas(Tabla_Lineas);
        setIconImage(getIconImage());
        ImageIcon icNuevo = new ImageIcon(getClass().getResource("/Imagenes/Nuevo.png"));
        Icon icononu = new ImageIcon(icNuevo.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        Bt_Nuevo.setIcon(icononu);
        ImageIcon icEditar = new ImageIcon(getClass().getResource("/Imagenes/Editar.png"));
        Icon iconoEdi = new ImageIcon(icEditar.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        Bt_Editar.setIcon(iconoEdi);     
        ImageIcon icGuardar = new ImageIcon(getClass().getResource("/Imagenes/Guardar.png"));
        Icon iconoGu = new ImageIcon(icGuardar.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        Bt_Guardar.setIcon(iconoGu);       
        ImageIcon icEliminar = new ImageIcon(getClass().getResource("/Imagenes/Eliminar.png"));
        Icon iconoEli = new ImageIcon(icEliminar.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        Bt_Eliminar.setIcon(iconoEli);       
        ImageIcon icCancelar = new ImageIcon(getClass().getResource("/Imagenes/Cancelar.png"));
        Icon iconoCa = new ImageIcon(icCancelar.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        Bt_Cancelar.setIcon(iconoCa);
        ImageIcon icModi = new ImageIcon(getClass().getResource("/Imagenes/Modificar.png"));
        Icon iconoMod = new ImageIcon(icModi.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        Bt_Modificar.setIcon(iconoMod);
        Bt_Modificar.setEnabled(false);
        habilitar(false);
        limpiar();  
    }   
    public void habilitar(boolean b){
        Bt_Nuevo.setEnabled(!b);
        Bt_Editar.setEnabled(!b);
        Bt_Guardar.setEnabled(b);
        Bt_Cancelar.setEnabled(b);
        Bt_Modificar.setEnabled(b);
        txtLinea.setEnabled(b);
    }
    public void limpiar(){
        txtLinea.setText("");
    }
    public void BuscarDatosLineas(String valor){
        ConexionSQL ObjetoCon = new ConexionSQL();     
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_Lineas.setRowSorter(OrdenarTabla);      
        modelo.addColumn("ID");
        modelo.addColumn("Lineas");
        String sql = "select * from inventariofcj.Datos_Lineas WHERE Lineas LIKE '%"+valor+"%'";
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
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos "+e.toString());
        }   
    }
    @Override
    public Image getIconImage (){
        Image Log = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/Lineas.png")); 
        return Log;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Bt_Nuevo = new javax.swing.JButton();
        Bt_Editar = new javax.swing.JButton();
        Bt_Guardar = new javax.swing.JButton();
        Bt_Cancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtLinea = new javax.swing.JTextField();
        Bt_Modificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Lineas = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Bt_Eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ubicaciones");

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ubicaciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

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

        Bt_Cancelar.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Cancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Cancelar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Cancelar.setText("Cancelar");
        Bt_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_CancelarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ubicacion:");

        txtLinea.setBackground(new java.awt.Color(153, 153, 153));

        Bt_Modificar.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Modificar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Modificar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Modificar.setText("Modificar");
        Bt_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_ModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Bt_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                .addComponent(Bt_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Bt_Editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Bt_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Bt_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bt_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bt_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Bt_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Bt_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(Bt_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        Tabla_Lineas.setBackground(new java.awt.Color(204, 204, 204));
        Tabla_Lineas.setModel(new javax.swing.table.DefaultTableModel(
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
        Tabla_Lineas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_LineasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla_Lineas);

        txtBuscar.setBackground(new java.awt.Color(153, 153, 153));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Buscar Ubicacion");

        Bt_Eliminar.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Eliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Eliminar.setText("Eliminar");
        Bt_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_EliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Bt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Bt_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_NuevoActionPerformed
        habilitar(true);
        limpiar();
        Bt_Modificar.setEnabled(false);
    }//GEN-LAST:event_Bt_NuevoActionPerformed

    private void Bt_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_EditarActionPerformed
        habilitar(true);
        Bt_Guardar.setEnabled(false);
    }//GEN-LAST:event_Bt_EditarActionPerformed

    private void Bt_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_GuardarActionPerformed
        if (txtLinea.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
            txtLinea.requestFocusInWindow();
        } else {
            DLineas NuevaLinea = new DLineas();
            NuevaLinea.InsertarDatosLineas(txtLinea);
            NuevaLinea.MostrarDatosLineas(Tabla_Lineas);
            habilitar(false);
            limpiar();
        }
    }//GEN-LAST:event_Bt_GuardarActionPerformed

    private void Bt_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_CancelarActionPerformed
        habilitar(false);
        limpiar();
    }//GEN-LAST:event_Bt_CancelarActionPerformed

    private void Bt_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_EliminarActionPerformed
        int opcion1 = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres borrar esta ubicacion? Esto puede afectar en el manejo de datos en otras ventanas del sistema impidiendo la consulta de datos", "Confirmación", JOptionPane.YES_NO_OPTION);      
        if (opcion1 == JOptionPane.YES_OPTION){
            int opcion2 = JOptionPane.showConfirmDialog(this, "¿Estás seguro de proceder?", "Confirmación", JOptionPane.YES_NO_OPTION);                
            if (opcion2 == JOptionPane.YES_OPTION){
                if (txtLinea.getText().equals("")){  
                   JOptionPane.showMessageDialog(rootPane, "Campo obligatorio","Validacion",JOptionPane.WARNING_MESSAGE);
                   txtLinea.requestFocusInWindow(); 
                   }else {
                    DLineas BorrarLinea = new DLineas();
                    BorrarLinea.EliminarLineas(Tabla_Lineas);
                    BorrarLinea.MostrarDatosLineas(Tabla_Lineas);
                    habilitar(false);
                    limpiar();  
                }
            }else {              
            }
        }
    }//GEN-LAST:event_Bt_EliminarActionPerformed

    private void Tabla_LineasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_LineasMouseClicked
        DLineas SecLinea = new DLineas();
        SecLinea.SeleccionarLineas(Tabla_Lineas,txtLinea);
    }//GEN-LAST:event_Tabla_LineasMouseClicked

    private void Bt_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_ModificarActionPerformed
        DLineas ModLinea = new DLineas();
        ModLinea.ModificarLineas(Tabla_Lineas, txtLinea);
        ModLinea.MostrarDatosLineas(Tabla_Lineas);
    }//GEN-LAST:event_Bt_ModificarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        BuscarDatosLineas(txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarKeyReleased

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
            java.util.logging.Logger.getLogger(Menu_Lineas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Lineas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Lineas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Lineas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Lineas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bt_Cancelar;
    private javax.swing.JButton Bt_Editar;
    private javax.swing.JButton Bt_Eliminar;
    private javax.swing.JButton Bt_Guardar;
    private javax.swing.JButton Bt_Modificar;
    private javax.swing.JButton Bt_Nuevo;
    private javax.swing.JTable Tabla_Lineas;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtLinea;
    // End of variables declaration//GEN-END:variables
}