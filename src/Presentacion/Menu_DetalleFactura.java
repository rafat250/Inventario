package Presentacion;
import Datos.DDetalleFactura;
import Logica.ConexionSQL;
import Logica.LFactura;
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
public class Menu_DetalleFactura extends javax.swing.JFrame {
    public Menu_DetalleFactura() {
        initComponents();
        setIconImage(getIconImage());
        Tabla_ListaFac.getColumnModel().getColumn(0).setMaxWidth(0);
        Tabla_ListaFac.getColumnModel().getColumn(0).setMinWidth(0);
        Tabla_ListaFac.getColumnModel().getColumn(0).setPreferredWidth(0);    
        MostrarFAC();   
    } 
    public void MostrarFAC(){
        DefaultTableModel MiModelo;
        LFactura LAL = new LFactura();        
        MiModelo = LAL.MostrarFacturas();
        Tabla_ListaFac.setModel(MiModelo);  
    }
    public void BuscarDatosFactura(String valor){
        ConexionSQL ObjetoCon = new ConexionSQL();  
        DefaultTableModel modelo = new DefaultTableModel();     
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla_ListaFac.setRowSorter(OrdenarTabla);
        modelo.addColumn("Codigo");
        modelo.addColumn("Proveedor");
        modelo.addColumn("Linea");
        modelo.addColumn("Fecha de Registro");
        modelo.addColumn("Proyecto");
        modelo.addColumn("Ubicacion");
        modelo.addColumn("Hora");
        Tabla_ListaFac.setModel(modelo);
        String sql = "SELECT IDFacturas,NombrePRO,Linea,FechaEntrada,Proyecto,Ubicacion,Hora FROM inventariofcj.datos_ingresofactu INNER JOIN inventariofcj.Datos_proveedores on IDProveedores = IDProvee INNER JOIN inventariofcj.datos_lineas on IDLin = IDLinea Where NombrePRO LIKE '%"+valor+"%';";
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
            Tabla_ListaFac.setModel(modelo);    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        } 
    }
    @Override
    public Image getIconImage (){
        Image Log = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/FacturaD.png")); 
        return Log;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_ListaFac = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Bt_Exportar = new javax.swing.JButton();
        BtEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalles de Altas");

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Altas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        txtBuscar.setBackground(new java.awt.Color(153, 153, 153));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        Tabla_ListaFac.setBackground(new java.awt.Color(204, 204, 204));
        Tabla_ListaFac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Proveedor", "Linea", "Fecha de Registro"
            }
        ));
        Tabla_ListaFac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Tabla_ListaFacMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla_ListaFac);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Buscar Proveedor");

        Bt_Exportar.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Exportar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Exportar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Exportar.setText("Exportar");
        Bt_Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_ExportarActionPerformed(evt);
            }
        });

        BtEliminar.setBackground(new java.awt.Color(0, 51, 153));
        BtEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtEliminar.setForeground(new java.awt.Color(255, 255, 255));
        BtEliminar.setText("Eliminar Factura");
        BtEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Bt_Exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtEliminar)))
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
                    .addComponent(Bt_Exportar)
                    .addComponent(BtEliminar))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
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

    private void Tabla_ListaFacMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_ListaFacMousePressed
        if (evt.getClickCount() == 2){
            String IDFac = Tabla_ListaFac.getValueAt(Tabla_ListaFac.getSelectedRow(), 0).toString();
            String Provee = Tabla_ListaFac.getValueAt(Tabla_ListaFac.getSelectedRow(), 1).toString();
            String Linea = Tabla_ListaFac.getValueAt(Tabla_ListaFac.getSelectedRow(), 2).toString();
            String FechaR = Tabla_ListaFac.getValueAt(Tabla_ListaFac.getSelectedRow(), 3).toString();        
            String Proye = Tabla_ListaFac.getValueAt(Tabla_ListaFac.getSelectedRow(), 4).toString();
            String Ubi = Tabla_ListaFac.getValueAt(Tabla_ListaFac.getSelectedRow(), 5).toString();
            String Hr = Tabla_ListaFac.getValueAt(Tabla_ListaFac.getSelectedRow(), 6).toString();
            Dialog_DetalleFactura miDetFac = new Dialog_DetalleFactura(new JFrame(),true);
            miDetFac.setdatos(IDFac,Provee,Linea,FechaR,Proye,Ubi,Hr);
            miDetFac.setLocationRelativeTo(null);
            miDetFac.setVisible(true);
        }
    }//GEN-LAST:event_Tabla_ListaFacMousePressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        BuscarDatosFactura(txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void Bt_ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_ExportarActionPerformed
        JFileChooser seleccionar = new JFileChooser();
        int opcion = seleccionar.showSaveDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION){
            String ruta = seleccionar.getSelectedFile().getAbsolutePath();
            String nombrereporte = ruta + ".xlsx";
            String nombrehoja = "Detalles de Facturas";
            XSSFWorkbook libroinventario = new XSSFWorkbook();
            XSSFSheet hojainventario = libroinventario.createSheet(nombrehoja);
            String[] titulos = new String[]{"ID","Proveedor","Linea","Fecha de Registro"};
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
            for(int i=0;i<Tabla_ListaFac.getRowCount();i++){
                XSSFRow contenido = hojainventario.createRow(filacontenido);
                filacontenido++;
                for(int j=0;j<Tabla_ListaFac.getColumnCount();j++){
                    XSSFCell celda = contenido.createCell(j);
                    celda.setCellValue(Tabla_ListaFac.getValueAt(i, j).toString()); 
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
    }//GEN-LAST:event_Bt_ExportarActionPerformed

    private void BtEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtEliminarActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro que quieres borrar estos datos?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            DDetalleFactura misfac = new DDetalleFactura();
            misfac.EliminarFacturas(Tabla_ListaFac);
            MostrarFAC();           
        }    
    }//GEN-LAST:event_BtEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(Menu_DetalleFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_DetalleFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_DetalleFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_DetalleFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_DetalleFactura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtEliminar;
    private javax.swing.JButton Bt_Exportar;
    private javax.swing.JTable Tabla_ListaFac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}