package Presentacion;
import Datos.DDetalles_Recibos;
import Logica.ConexionSQL;
import Logica.LRecibos;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Menu_DetRecibos extends javax.swing.JFrame {
    public Menu_DetRecibos() {
        initComponents();
        setIconImage(getIconImage());
        Tabla_ListaRec.getColumnModel().getColumn(0).setMaxWidth(0);
        Tabla_ListaRec.getColumnModel().getColumn(0).setMinWidth(0);
        Tabla_ListaRec.getColumnModel().getColumn(0).setPreferredWidth(0); 
        MostrarRec();
    }
    public void MostrarRec(){
        DefaultTableModel MiModelo;
        LRecibos Rec = new LRecibos();        
        MiModelo = Rec.MostrarRecibos();
        Tabla_ListaRec.setModel(MiModelo);  
    }
    public void BuscarDatosRecibos(String valor){
        ConexionSQL ObjetoCon = new ConexionSQL();  
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_ListaRec.setRowSorter(OrdenarTabla);
        modelo.addColumn("ID");
        modelo.addColumn("Fecha");
        modelo.addColumn("Persona Entrega");
        modelo.addColumn("Persona Recibe");
        modelo.addColumn("Cliente");
        modelo.addColumn("Proyecto");
        modelo.addColumn("Ubicacion");
        modelo.addColumn("Hora");
        Tabla_ListaRec.setModel(modelo);
        String sql = "Select * from inventariofcj.Datos_Recibos WHERE PersonaEntrega LIKE '%"+valor+"%';";
        String[] datos = new String[8];
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
                modelo.addRow(datos);
            }     
            Tabla_ListaRec.setModel(modelo);    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        } 
    }
    @Override
    public Image getIconImage (){
        Image Log = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/PedidoD.png")); 
        return Log;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_ListaRec = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Bt_Expo = new javax.swing.JButton();
        Bt_Eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalles de los Salidas");

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Salidas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        txtBuscar.setBackground(new java.awt.Color(153, 153, 153));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        Tabla_ListaRec.setBackground(new java.awt.Color(204, 204, 204));
        Tabla_ListaRec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Persona Entrega", "Persona Recibe"
            }
        ));
        Tabla_ListaRec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Tabla_ListaRecMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla_ListaRec);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Buscar Persona Entrega");

        Bt_Expo.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Expo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Expo.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Expo.setText("Exportar XLSX");
        Bt_Expo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_ExpoActionPerformed(evt);
            }
        });

        Bt_Eliminar.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Eliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Eliminar.setText("Eliminar Recibo");
        Bt_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_EliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Bt_Expo)
                                .addGap(18, 18, 18)
                                .addComponent(Bt_Eliminar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bt_Expo)
                    .addComponent(Bt_Eliminar))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Tabla_ListaRecMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_ListaRecMousePressed
        if (evt.getClickCount() == 2){
            String IDRec = Tabla_ListaRec.getValueAt(Tabla_ListaRec.getSelectedRow(), 0).toString();
            String FechaRec = Tabla_ListaRec.getValueAt(Tabla_ListaRec.getSelectedRow(), 1).toString();
            String PersonaE = Tabla_ListaRec.getValueAt(Tabla_ListaRec.getSelectedRow(), 2).toString();
            String PersonaR = Tabla_ListaRec.getValueAt(Tabla_ListaRec.getSelectedRow(), 3).toString();
            String Client = Tabla_ListaRec.getValueAt(Tabla_ListaRec.getSelectedRow(), 4).toString();
            String Proye = Tabla_ListaRec.getValueAt(Tabla_ListaRec.getSelectedRow(), 5).toString();
            String ubic = Tabla_ListaRec.getValueAt(Tabla_ListaRec.getSelectedRow(), 6).toString();
            String Hora = Tabla_ListaRec.getValueAt(Tabla_ListaRec.getSelectedRow(), 7).toString();
            Dialog_DetalleRecibos miDetRec = new Dialog_DetalleRecibos(new JFrame(),true);
            miDetRec.setdatosR(IDRec,FechaRec,PersonaE,PersonaR,Client,Proye,ubic,Hora);
            miDetRec.setLocationRelativeTo(null);
            miDetRec.setVisible(true);
        }
    }//GEN-LAST:event_Tabla_ListaRecMousePressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        BuscarDatosRecibos(txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void Bt_ExpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_ExpoActionPerformed
        JFileChooser seleccionar = new JFileChooser();
        int opcion = seleccionar.showSaveDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION){
            String ruta = seleccionar.getSelectedFile().getAbsolutePath();
            String nombrereporte = ruta + ".xlsx";
            String nombrehoja = "Inventario";
            XSSFWorkbook libroinventario = new XSSFWorkbook();
            XSSFSheet hojainventario = libroinventario.createSheet(nombrehoja);
            String[] titulos = new String[]{"ID","Fecha","Persona Entrega","Persona Recibe"};
            Font fontcabeza = libroinventario.createFont();
            fontcabeza.setBold(true);
            fontcabeza.setColor(IndexedColors.WHITE.getIndex());
            CellStyle cscabeza = libroinventario.createCellStyle();
            cscabeza.setBorderBottom(BorderStyle.THIN);
            cscabeza.setBorderLeft(BorderStyle.THIN);
            cscabeza.setBorderRight(BorderStyle.THIN);
            cscabeza.setBorderTop(BorderStyle.THIN);
            cscabeza.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
            cscabeza.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cscabeza.setFont(fontcabeza);
            CellStyle cscontenido = libroinventario.createCellStyle();
            cscontenido.setBorderBottom(BorderStyle.THIN);
            cscontenido.setBorderLeft(BorderStyle.THIN);
            cscontenido.setBorderRight(BorderStyle.THIN);
            cscontenido.setBorderTop(BorderStyle.THIN);
            XSSFRow titulo = hojainventario.createRow(0);
            for(int i=0;i<titulos.length;i++){
                XSSFCell celda = titulo.createCell(i);
                celda.setCellValue(titulos[i]);
                celda.setCellStyle(cscabeza);
            }
            int filacontenido = 1;
            for(int i=0;i<Tabla_ListaRec.getRowCount();i++){
                XSSFRow contenido = hojainventario.createRow(filacontenido);
                filacontenido++;
                for(int j=0;j<Tabla_ListaRec.getColumnCount();j++){
                    XSSFCell celda = contenido.createCell(j);
                    celda.setCellValue(Tabla_ListaRec.getValueAt(i, j).toString()); 
                    celda.setCellStyle(cscontenido);
                }
            }                       
            hojainventario.autoSizeColumn(0);
            hojainventario.autoSizeColumn(1);
            hojainventario.autoSizeColumn(2);
            hojainventario.autoSizeColumn(3);
            try(OutputStream archivo = new FileOutputStream(nombrereporte)){
                libroinventario.write(archivo);
                JOptionPane.showMessageDialog(null, "Reporte creado");  
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error: "+e.toString());  
            }   
        }
    }//GEN-LAST:event_Bt_ExpoActionPerformed

    private void Bt_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_EliminarActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas realizar esta acción?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            DDetalles_Recibos misdet = new DDetalles_Recibos();
            misdet.EliminarRecibos(Tabla_ListaRec);
            MostrarRec();
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
            java.util.logging.Logger.getLogger(Menu_DetRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_DetRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_DetRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_DetRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_DetRecibos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bt_Eliminar;
    private javax.swing.JButton Bt_Expo;
    private javax.swing.JTable Tabla_ListaRec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}