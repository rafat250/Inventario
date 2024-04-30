package Presentacion;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
public class Principal extends javax.swing.JFrame {
    public Principal() {
        initComponents();
        setIconImage(getIconImage());
        setExtendedState(Principal.MAXIMIZED_BOTH);
        ImageIcon icFacturas = new ImageIcon(getClass().getResource("/Imagenes/Facturas.png"));
        Icon iconofac = new ImageIcon(icFacturas.getImage().getScaledInstance(80, 60, Image.SCALE_DEFAULT));
        Bt_Facturas.setIcon(iconofac);      
        ImageIcon icRecibos = new ImageIcon(getClass().getResource("/Imagenes/Recibos.png"));
        Icon iconoReb = new ImageIcon(icRecibos.getImage().getScaledInstance(80, 70, Image.SCALE_DEFAULT));
        Bt_Recibos.setIcon(iconoReb);
        ImageIcon icAlmacen = new ImageIcon(getClass().getResource("/Imagenes/Almacen.png"));
        Icon iconoAl = new ImageIcon(icAlmacen.getImage().getScaledInstance(80, 70, Image.SCALE_DEFAULT));
        Bt_Almacen.setIcon(iconoAl);                
        ImageIcon icConsu = new ImageIcon(getClass().getResource("/Imagenes/Consulta.png"));
        Icon iconoCo = new ImageIcon(icConsu.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
        MenuCon.setIcon(iconoCo);       
        ImageIcon icBase = new ImageIcon(getClass().getResource("/Imagenes/BD.png"));
        Icon iconoBD = new ImageIcon(icBase.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT));
        MenuBD.setIcon(iconoBD);        
        ImageIcon icHerramientas = new ImageIcon(getClass().getResource("/Imagenes/Herremientas.png"));
        Icon iconoHe = new ImageIcon(icHerramientas.getImage().getScaledInstance(34, 32, Image.SCALE_DEFAULT));
        MenuHerrami.setIcon(iconoHe);      
        ImageIcon icRepo = new ImageIcon(getClass().getResource("/Imagenes/Reporte.png"));
        Icon iconorepo = new ImageIcon(icRepo.getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT));
        ItemReporte.setIcon(iconorepo);       
        ImageIcon icBD = new ImageIcon(getClass().getResource("/Imagenes/Restaurar.png"));
        Icon iconoBase = new ImageIcon(icBD.getImage().getScaledInstance(30, 26, Image.SCALE_DEFAULT));
        ItemBD.setIcon(iconoBase);       
        ImageIcon icUsu = new ImageIcon(getClass().getResource("/Imagenes/Usuarios.png"));
        Icon iconousu = new ImageIcon(icUsu.getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT));
        ItemUsu.setIcon(iconousu);      
        ImageIcon icFacD = new ImageIcon(getClass().getResource("/Imagenes/FacturaD.png"));
        Icon iconoFA = new ImageIcon(icFacD.getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT));
        itemDFactura.setIcon(iconoFA);      
        ImageIcon icPeD = new ImageIcon(getClass().getResource("/Imagenes/PedidoD.png"));
        Icon iconoPE = new ImageIcon(icPeD.getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT));
        itemDRecibo.setIcon(iconoPE);  
        ImageIcon icPro = new ImageIcon(getClass().getResource("/Imagenes/Proveedor.png"));
        Icon iconoProvee = new ImageIcon(icPro.getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT));
        ItemProvee.setIcon(iconoProvee); 
        ImageIcon icLin = new ImageIcon(getClass().getResource("/Imagenes/Lineas.png"));
        Icon iconoLineas = new ImageIcon(icLin.getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT));
        ItemLineas.setIcon(iconoLineas);    
        ImageIcon icLog = new ImageIcon(getClass().getResource("/Imagenes/FCJ IMAGEN.jpg"));
        Icon iconoLog = new ImageIcon(icLog.getImage().getScaledInstance(1060, 600, Image.SCALE_DEFAULT));
        LabelFondoIMD.setIcon(iconoLog); 
        ImageIcon icSAL = new ImageIcon(getClass().getResource("/Imagenes/Reporte.png"));
        Icon iconoSAL = new ImageIcon(icSAL.getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT));
        Item_Salidas.setIcon(iconoSAL); 
        timer.start();
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
                LbFecha.setText(dias+":"+(mes+1)+":"+aa);
                LbHora.setText(hh+":"+mm+":"+ss);
            } 
    }); 
    @Override
    public Image getIconImage (){
        Image Log = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/FCJ_LOG.png")); 
        return Log;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Bt_Facturas = new javax.swing.JButton();
        Bt_Recibos = new javax.swing.JButton();
        Bt_Almacen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LbUsuarios = new javax.swing.JLabel();
        LbFecha = new javax.swing.JLabel();
        LbHora = new javax.swing.JLabel();
        Lbperfil = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ester1 = new javax.swing.JLabel();
        easter3 = new javax.swing.JLabel();
        LabelFondoIMD = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuCon = new javax.swing.JMenu();
        itemDFactura = new javax.swing.JMenuItem();
        itemDRecibo = new javax.swing.JMenuItem();
        ItemReporte = new javax.swing.JMenuItem();
        Item_Salidas = new javax.swing.JMenuItem();
        MenuBD = new javax.swing.JMenu();
        ItemBD = new javax.swing.JMenuItem();
        MenuHerrami = new javax.swing.JMenu();
        ItemProvee = new javax.swing.JMenuItem();
        ItemUsu = new javax.swing.JMenuItem();
        ItemLineas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menu Principal", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        Bt_Facturas.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Facturas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Facturas.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Facturas.setText("Alta Producto");
        Bt_Facturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_FacturasActionPerformed(evt);
            }
        });

        Bt_Recibos.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Recibos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Recibos.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Recibos.setText("Salidas Producto");
        Bt_Recibos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_RecibosActionPerformed(evt);
            }
        });

        Bt_Almacen.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Almacen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Almacen.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Almacen.setText("Almacen");
        Bt_Almacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_AlmacenActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fecha");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Hora");

        LbUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LbUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        LbUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LbUsuarios.setText("jLabel4");

        LbFecha.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LbFecha.setForeground(new java.awt.Color(255, 255, 255));
        LbFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LbFecha.setText("jLabel5");

        LbHora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LbHora.setForeground(new java.awt.Color(255, 255, 255));
        LbHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LbHora.setText("jLabel6");

        Lbperfil.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Lbperfil.setForeground(new java.awt.Color(255, 255, 255));
        Lbperfil.setText("jLabel4");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nivel");

        ester1.setForeground(new java.awt.Color(0, 0, 51));
        ester1.setText("jLabel5");
        ester1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ester1MousePressed(evt);
            }
        });

        easter3.setForeground(new java.awt.Color(0, 0, 51));
        easter3.setText("jLabel5");
        easter3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                easter3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Bt_Facturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bt_Almacen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bt_Recibos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LbFecha)
                            .addComponent(Lbperfil)
                            .addComponent(LbUsuarios)
                            .addComponent(LbHora))
                        .addGap(0, 29, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(ester1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(easter3))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Bt_Facturas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bt_Recibos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bt_Almacen, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(LbUsuarios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Lbperfil))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(LbFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(LbHora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ester1)
                    .addComponent(easter3)))
        );

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 102));

        MenuCon.setText("Consultas");

        itemDFactura.setText("Detalles de Altas");
        itemDFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDFacturaActionPerformed(evt);
            }
        });
        MenuCon.add(itemDFactura);

        itemDRecibo.setText("Detalles de Salidas");
        itemDRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDReciboActionPerformed(evt);
            }
        });
        MenuCon.add(itemDRecibo);

        ItemReporte.setText("Reportes Inventario");
        ItemReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemReporteActionPerformed(evt);
            }
        });
        MenuCon.add(ItemReporte);

        Item_Salidas.setText("Reporte Salidas");
        Item_Salidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Item_SalidasActionPerformed(evt);
            }
        });
        MenuCon.add(Item_Salidas);

        jMenuBar1.add(MenuCon);

        MenuBD.setText("Base de datos");

        ItemBD.setText("Respaldar/Restaurar");
        ItemBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemBDActionPerformed(evt);
            }
        });
        MenuBD.add(ItemBD);

        jMenuBar1.add(MenuBD);

        MenuHerrami.setText("Herramientas");

        ItemProvee.setText("Proveedores");
        ItemProvee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemProveeActionPerformed(evt);
            }
        });
        MenuHerrami.add(ItemProvee);

        ItemUsu.setText("Usuarios");
        ItemUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemUsuActionPerformed(evt);
            }
        });
        MenuHerrami.add(ItemUsu);

        ItemLineas.setText("Ubicaciones");
        ItemLineas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemLineasActionPerformed(evt);
            }
        });
        MenuHerrami.add(ItemLineas);

        jMenuBar1.add(MenuHerrami);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelFondoIMD, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabelFondoIMD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ItemUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemUsuActionPerformed
        Menu_Usuarios misUsuarios = new Menu_Usuarios();
        misUsuarios.setLocationRelativeTo(null);
        misUsuarios.setVisible(true);
    }//GEN-LAST:event_ItemUsuActionPerformed

    private void ItemProveeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemProveeActionPerformed
        MenuProveedores misProveedores = new MenuProveedores();
        misProveedores.setLocationRelativeTo(null);
        misProveedores.setVisible(true);
    }//GEN-LAST:event_ItemProveeActionPerformed

    private void Bt_FacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_FacturasActionPerformed
        Menu_Facturas misFacturas = new Menu_Facturas();
        misFacturas.setLocationRelativeTo(null);
        misFacturas.setVisible(true);
    }//GEN-LAST:event_Bt_FacturasActionPerformed

    private void ItemLineasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemLineasActionPerformed
        Menu_Lineas misLineas = new Menu_Lineas();
        misLineas.setLocationRelativeTo(null);
        misLineas.setVisible(true);
    }//GEN-LAST:event_ItemLineasActionPerformed

    private void itemDFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDFacturaActionPerformed
        Menu_DetalleFactura miDetalleF = new Menu_DetalleFactura();
        miDetalleF.setLocationRelativeTo(null);
        miDetalleF.setVisible(true);
    }//GEN-LAST:event_itemDFacturaActionPerformed

    private void Bt_AlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_AlmacenActionPerformed
        Menu_Inventario miInventario = new Menu_Inventario();
        miInventario.setLocationRelativeTo(null);
        miInventario.setVisible(true);
    }//GEN-LAST:event_Bt_AlmacenActionPerformed

    private void ItemReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemReporteActionPerformed
        Menu_Reporte misReportes = new Menu_Reporte();
        misReportes.setLocationRelativeTo(null);
        misReportes.setVisible(true);
    }//GEN-LAST:event_ItemReporteActionPerformed

    private void Bt_RecibosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_RecibosActionPerformed
        Menu_Recibos misRecibos = new Menu_Recibos();
        misRecibos.setLocationRelativeTo(null);
        misRecibos.setUsuario(LbUsuarios.getText());
        misRecibos.setVisible(true);
    }//GEN-LAST:event_Bt_RecibosActionPerformed

    private void itemDReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDReciboActionPerformed
        Menu_DetRecibos miDetalleR = new Menu_DetRecibos();
        miDetalleR.setLocationRelativeTo(null);
        miDetalleR.setVisible(true);
    }//GEN-LAST:event_itemDReciboActionPerformed

    private void ItemBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemBDActionPerformed
        Menu_Expo_Impor miexpo = new Menu_Expo_Impor();
        miexpo.setLocationRelativeTo(null);
        miexpo.setVisible(true);
    }//GEN-LAST:event_ItemBDActionPerformed

    private void ester1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ester1MousePressed
        if (evt.getClickCount() == 2){
            JOptionPane.showMessageDialog(null, "En la red me encuentro, navegando como en el mar");
            JOptionPane.showMessageDialog(null, "Soy el que navega en la vasta frontera");
            JOptionPane.showMessageDialog(null, "En foros y chats, me puedes hallar");
            JOptionPane.showMessageDialog(null, "¿Quien soy?....");
            JOptionPane.showMessageDialog(null, "Una vez sepas quien soy yo, ve al lugar donde me registro con los demas....");
        }
    }//GEN-LAST:event_ester1MousePressed

    private void easter3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_easter3MousePressed
        if (evt.getClickCount() == 2){
            JOptionPane.showMessageDialog(null, "En estantes y cajas, en un lugar organizado");
            JOptionPane.showMessageDialog(null, "Guardo productos, todo clasificado");
            JOptionPane.showMessageDialog(null, "Depósito de mercancía y más, como en una biblioteca");
            JOptionPane.showMessageDialog(null, "¿Quién soy?....");
            JOptionPane.showMessageDialog(null, "Una vez sepas quien soy yo, ve al lugar donde mi nombre");
        }
    }//GEN-LAST:event_easter3MousePressed

    private void Item_SalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Item_SalidasActionPerformed
        Menu_ReporteSalidas resa = new Menu_ReporteSalidas();
        resa.setLocationRelativeTo(null);
        resa.setVisible(true);
    }//GEN-LAST:event_Item_SalidasActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bt_Almacen;
    private javax.swing.JButton Bt_Facturas;
    private javax.swing.JButton Bt_Recibos;
    private javax.swing.JMenuItem ItemBD;
    private javax.swing.JMenuItem ItemLineas;
    private javax.swing.JMenuItem ItemProvee;
    private javax.swing.JMenuItem ItemReporte;
    private javax.swing.JMenuItem ItemUsu;
    private javax.swing.JMenuItem Item_Salidas;
    private javax.swing.JLabel LabelFondoIMD;
    private javax.swing.JLabel LbFecha;
    private javax.swing.JLabel LbHora;
    private javax.swing.JLabel LbUsuarios;
    private javax.swing.JLabel Lbperfil;
    private javax.swing.JMenu MenuBD;
    private javax.swing.JMenu MenuCon;
    private javax.swing.JMenu MenuHerrami;
    private javax.swing.JLabel easter3;
    private javax.swing.JLabel ester1;
    private javax.swing.JMenuItem itemDFactura;
    private javax.swing.JMenuItem itemDRecibo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public void setdatos(String usuario, String perfil) {
        Menu_Inventario miinv = new Menu_Inventario();
        LbUsuarios.setText(usuario);
        Lbperfil.setText(perfil);      
        if (perfil.equals("Empleado")){
            MenuBD.setVisible(false);
            ItemUsu.setVisible(false);
        } else {
            MenuBD.setVisible(true);
            ItemUsu.setVisible(true);
        }  
    }
}