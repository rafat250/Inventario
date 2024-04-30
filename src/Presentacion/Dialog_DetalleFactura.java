package Presentacion;
import Datos.DDetalleFactura;
import Logica.ConexionSQL;
import Logica.LDetalleFactura;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Dialog_DetalleFactura extends javax.swing.JDialog {
    DefaultTableModel miModelo;
    public Dialog_DetalleFactura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tablacolumnas();
        miModelo = (DefaultTableModel) Tabla_Facturas.getModel();
        miModelo.fireTableStructureChanged();
        setIconImage(getIconImage());
        txtID.setEditable(false);
        txtLinea.setEditable(false);
        txtProvee.setEditable(false);
        txtProyecto.setEditable(false);
        txtFecha.setEditable(false);
        txtUbicacion.setEditable(false);
        txtHora.setEditable(false);
        txtTotal.setEditable(false);
        txtTotalCant.setEditable(false);
    }
    private void tablacolumnas() {
        TableColumnModel columna1 = Tabla_Facturas.getColumnModel();   
        columna1.getColumn(0).setPreferredWidth(15);     
        TableColumnModel columna2 = Tabla_Facturas.getColumnModel();   
        columna2.getColumn(1).setPreferredWidth(220);
        TableColumnModel columna3 = Tabla_Facturas.getColumnModel();   
        columna3.getColumn(2).setPreferredWidth(15);
        TableColumnModel columna4 = Tabla_Facturas.getColumnModel();   
        columna4.getColumn(3).setPreferredWidth(20);
        TableColumnModel columna5 = Tabla_Facturas.getColumnModel();   
        columna5.getColumn(4).setPreferredWidth(20);
        TableColumnModel columna6 = Tabla_Facturas.getColumnModel();   
        columna6.getColumn(5).setPreferredWidth(10);
    }
    public void SumarCant(){
        DecimalFormat ft = new DecimalFormat("####.00");
        double TotalCantidad = 0, TotalFinalCant = 0;
        for (int i=0; i < Tabla_Facturas.getRowCount(); i++){
            TotalCantidad = Double.parseDouble(Tabla_Facturas.getValueAt(i, 2).toString());
            TotalFinalCant += TotalCantidad;
            txtTotalCant.setText(ft.format(TotalFinalCant));
        }      
    }
    public void setdatos(String IDFac,String Provee,String Linea,String FechaR,String Proye,String Ubi,String Hr){
        txtID.setText(IDFac);
        txtProvee.setText(Provee);
        txtLinea.setText(Linea);
        txtFecha.setText(FechaR);
        txtProyecto.setText(Proye);
        txtUbicacion.setText(Ubi);
        txtHora.setText(Hr);
        MostrarDetalleFac(Integer.parseInt(IDFac));
    }
    public void MostrarDetalleFac(int IDFac){
        miModelo = (DefaultTableModel) Tabla_Facturas.getModel();
        DDetalleFactura dts = new DDetalleFactura();
        LDetalleFactura LDT = new LDetalleFactura();
        dts.setFacturaID(IDFac);
        miModelo = LDT.MDetalleFactura(dts);
        Tabla_Facturas.setModel(miModelo);
        SumaTotal();
        SumarCant();
    }  
    public void SumaTotal(){
        DecimalFormat ft = new DecimalFormat("####.00");
        double TotalParcial = 0, TotalFinal = 0;
        if (Tabla_Facturas.getRowCount() > 0){
            for (int i=0; i < Tabla_Facturas.getRowCount(); i++){
            TotalParcial = Double.parseDouble(Tabla_Facturas.getValueAt(i, 5).toString());
            TotalFinal += TotalParcial;
            txtTotal.setText(ft.format(TotalFinal));
            txtTotal.setForeground(Color.black);
            }
        }     
    }   
    public Image getIconImage (){
        Image Log = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/FacturaD.png")); 
        return Log;
    }
    private static void openPDF(String filePath) throws IOException {
        File file = new File(filePath);
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            } else {
                JOptionPane.showMessageDialog(null, "El archivo PDF no existe.");  
            }
        } else {
            JOptionPane.showMessageDialog(null, "El escritorio no es compatible. No se puede abrir el archivo.");  
        }
    }
    private static void abrirExcel(String nombreArchivo) throws IOException {      
        File file = new File(nombreArchivo);
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            } else {
                JOptionPane.showMessageDialog(null, "El archivo excel no existe.");  
            }
        } else {
            JOptionPane.showMessageDialog(null, "El escritorio no es compatible. No se puede abrir el archivo.");  
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtLinea = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtProvee = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtProyecto = new javax.swing.JTextField();
        txtUbicacion = new javax.swing.JTextField();
        txtHora = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Facturas = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        Bt_Exportar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTotalCant = new javax.swing.JTextField();
        Bt_ExportarPDF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalles de Altas");

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID:");

        txtID.setBackground(new java.awt.Color(153, 153, 153));

        txtLinea.setBackground(new java.awt.Color(153, 153, 153));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ubicacion: ");

        txtProvee.setBackground(new java.awt.Color(153, 153, 153));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Proveedor:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha: ");

        txtFecha.setBackground(new java.awt.Color(153, 153, 153));

        txtProyecto.setBackground(new java.awt.Color(153, 153, 153));

        txtUbicacion.setBackground(new java.awt.Color(153, 153, 153));

        txtHora.setBackground(new java.awt.Color(153, 153, 153));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Proyecto");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Ubicacion");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Hora");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLinea)
                            .addComponent(txtProvee)
                            .addComponent(txtProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtHora, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtUbicacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(68, 68, 68))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtProvee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos de la Alta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        Tabla_Facturas.setBackground(new java.awt.Color(204, 204, 204));
        Tabla_Facturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad", "U/Medida", "P/Unitario", "Total"
            }
        ));
        jScrollPane1.setViewportView(Tabla_Facturas);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Total:");

        txtTotal.setBackground(new java.awt.Color(153, 153, 153));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Bt_Exportar.setBackground(new java.awt.Color(0, 51, 153));
        Bt_Exportar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_Exportar.setForeground(new java.awt.Color(255, 255, 255));
        Bt_Exportar.setText("Exportar .xlsx");
        Bt_Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_ExportarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Total Cantidad:");

        txtTotalCant.setBackground(new java.awt.Color(153, 153, 153));
        txtTotalCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Bt_ExportarPDF.setBackground(new java.awt.Color(0, 51, 153));
        Bt_ExportarPDF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_ExportarPDF.setForeground(new java.awt.Color(255, 255, 255));
        Bt_ExportarPDF.setText("Exportar PDF");
        Bt_ExportarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_ExportarPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Bt_Exportar)
                .addGap(46, 46, 46)
                .addComponent(Bt_ExportarPDF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalCant, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Bt_Exportar)
                        .addComponent(Bt_ExportarPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addComponent(txtTotalCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Bt_ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_ExportarActionPerformed
        JFileChooser seleccionar = new JFileChooser();
        int opcion = seleccionar.showSaveDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION){
            String ruta = seleccionar.getSelectedFile().getAbsolutePath();
            String nombrereporte = ruta+""+txtID.getText()+".xlsx";
            String nombrehoja = "Factura " +txtID.getText();
            XSSFWorkbook libroinventario = new XSSFWorkbook();
            XSSFSheet hojainventario = libroinventario.createSheet(nombrehoja);
            String[] titulos = new String[]{"Codigo","Descripcion","Cantidad","U/Medida","P/Unitario","Total"};
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
            CellStyle csdatos = libroinventario.createCellStyle();
            csdatos.setBorderBottom(BorderStyle.THIN);
            CellStyle csdatos2 = libroinventario.createCellStyle();
            csdatos2.setBorderBottom(BorderStyle.THIN);
            csdatos2.setBorderLeft(BorderStyle.THIN);
            csdatos2.setBorderRight(BorderStyle.THIN);
            csdatos2.setBorderTop(BorderStyle.THIN);
            CellStyle cscontenido = libroinventario.createCellStyle();
            cscontenido.setBorderBottom(BorderStyle.THIN);
            cscontenido.setBorderLeft(BorderStyle.THIN);
            cscontenido.setBorderRight(BorderStyle.THIN);
            cscontenido.setBorderTop(BorderStyle.THIN);
            XSSFRow Idfactura = hojainventario.createRow(1);
            XSSFCell celdafactura1 = Idfactura.createCell(0);
            XSSFCell celdafactura2 = Idfactura.createCell(1);
            celdafactura1.setCellValue("Factura: ");
            celdafactura2.setCellValue(txtID.getText());
            celdafactura2.setCellStyle(csdatos);
            XSSFRow linea = hojainventario.createRow(3);
            XSSFCell celdalinea1 = linea.createCell(0);
            XSSFCell celdalinea2 = linea.createCell(1);
            celdalinea1.setCellValue("Linea: ");
            celdalinea2.setCellValue(txtLinea.getText());
            celdalinea2.setCellStyle(csdatos);
            XSSFRow proveedor = hojainventario.createRow(5);
            XSSFCell celdaprovee1 = proveedor.createCell(0);
            XSSFCell celdaprovee2 = proveedor.createCell(1);
            celdaprovee1.setCellValue("Proveedor: ");
            celdaprovee2.setCellValue(txtProvee.getText());
            celdaprovee2.setCellStyle(csdatos);
            XSSFRow Proyecto = hojainventario.createRow(7);
            XSSFCell celdaPRO1 = Proyecto.createCell(0);
            XSSFCell celdaPRO2 = Proyecto.createCell(1);
            celdaPRO1.setCellValue("Proyecto: ");
            celdaPRO2.setCellValue(txtProyecto.getText());
            celdaPRO2.setCellStyle(csdatos);
            XSSFRow fecha = hojainventario.createRow(9);
            XSSFCell celdafecha1 = fecha.createCell(0);
            XSSFCell celdafecha2 = fecha.createCell(1);
            celdafecha1.setCellValue("Fecha de Registro: ");
            celdafecha2.setCellValue(txtFecha.getText());
            celdafecha2.setCellStyle(csdatos);
            XSSFRow Ubi = hojainventario.createRow(11);
            XSSFCell celdaubi1 = Ubi.createCell(0);
            XSSFCell celdaubi2 = Ubi.createCell(1);
            celdaubi1.setCellValue("Ubicacion: ");
            celdaubi2.setCellValue(txtUbicacion.getText());
            celdaubi2.setCellStyle(csdatos);
            XSSFRow Hora = hojainventario.createRow(13);
            XSSFCell celdaH1 = Hora.createCell(0);
            XSSFCell celdaH2 = Hora.createCell(1);
            celdaH1.setCellValue("Hora: ");
            celdaH2.setCellValue(txtHora.getText());
            celdaH2.setCellStyle(csdatos);
            XSSFRow titulo = hojainventario.createRow(15);
            for(int i=0;i<titulos.length;i++){
                XSSFCell celda = titulo.createCell(i);
                celda.setCellValue(titulos[i]);
                celda.setCellStyle(cscabeza);
            }
            int filacontenido = 16;
            for(int i=0;i<Tabla_Facturas.getRowCount();i++){
                XSSFRow contenido = hojainventario.createRow(filacontenido);
                filacontenido++;
                for(int j=0;j<Tabla_Facturas.getColumnCount();j++){
                    XSSFCell celda = contenido.createCell(j);
                    celda.setCellValue(Tabla_Facturas.getValueAt(i, j).toString()); 
                    celda.setCellStyle(cscontenido);
                }
            }  
            XSSFRow Total = hojainventario.createRow(filacontenido+2);
            XSSFCell celdatotal1 = Total.createCell(5);
            XSSFCell celdatotal2 = Total.createCell(6);
            celdatotal1.setCellValue("Total: ");
            celdatotal2.setCellValue(Double.parseDouble(txtTotal.getText()));
            celdatotal2.setCellStyle(csdatos2); 
            XSSFRow TotalC = hojainventario.createRow(filacontenido+3);
            XSSFCell celdatotalC1 = TotalC.createCell(5);
            XSSFCell celdatotalC2 = TotalC.createCell(6);
            celdatotalC1.setCellValue("Total Cantidad: ");
            celdatotalC2.setCellValue(Double.parseDouble(txtTotalCant.getText()));
            celdatotalC2.setCellStyle(csdatos2);  
            CellRangeAddress regionnumfactura = new CellRangeAddress(5,5,1,3);
            hojainventario.addMergedRegion(regionnumfactura);
            hojainventario.autoSizeColumn(0);
            hojainventario.autoSizeColumn(1);
            hojainventario.autoSizeColumn(2);
            hojainventario.autoSizeColumn(3);
            hojainventario.autoSizeColumn(4);
            hojainventario.autoSizeColumn(5);
            try(OutputStream archivo = new FileOutputStream(nombrereporte)){
                libroinventario.write(archivo);
                JOptionPane.showMessageDialog(null, "Reporte creado"); 
                abrirExcel(nombrereporte);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error: "+e.toString());  
            }  
        }
    }//GEN-LAST:event_Bt_ExportarActionPerformed

    private void Bt_ExportarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_ExportarPDFActionPerformed
        JFileChooser seleccionarPD = new JFileChooser();
        int opcionPDF = seleccionarPD.showSaveDialog(null);
        ConexionSQL ObjetoCon = new ConexionSQL();  
            DDetalleFactura DR = new DDetalleFactura(); 
            Connection ob = ObjetoCon.getConnection();
        if (opcionPDF == JFileChooser.APPROVE_OPTION){
            try {
                Document document = new Document(PageSize.A4);
                String ruta = seleccionarPD.getSelectedFile().getAbsolutePath();
                String nombrereporte = ruta+""+txtID.getText()+".PDF";
                PdfWriter.getInstance(document, new FileOutputStream(nombrereporte));
                com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/Imagenes/FCJ IMAGEN.jpg");
                header.scaleToFit(150,150);
                header.setAlignment(Chunk.ALIGN_LEFT); 
                document.open(); 
                document.add(header);
                Paragraph title = new Paragraph("Facturas Grupo FCJ");
                title.setAlignment(Paragraph.ALIGN_CENTER);
                title.setFont(FontFactory.getFont("Tahoma",30,BaseColor.DARK_GRAY));
                document.add(title);
                document.add(new Paragraph("   "));
                document.add(new Paragraph("Numero de Factura: "+txtID.getText()));
                document.add(new Paragraph("Linea: "+txtLinea.getText()));
                document.add(new Paragraph("Proveedor: "+txtProvee.getText()));
                document.add(new Paragraph("Proyecto: "+txtProyecto.getText()));
                document.add(new Paragraph("Fecha de Registro: "+txtFecha.getText()));
                document.add(new Paragraph("Hora de Registro: "+txtHora.getText()));
                document.add(new Paragraph("Ubicacion: "+txtUbicacion.getText()));
                document.add(new Paragraph("   "));
                PdfPTable table = new PdfPTable(6);        
                float[] columnWidths = {20f, 160f, 60f, 60f, 60f, 60f}; 
                table.setWidths(columnWidths);
                table.addCell("ID");
                table.addCell("Descripcion");
                table.addCell("Cantidad");
                table.addCell("U/Medida");
                table.addCell("P/Unitario");
                table.addCell("Total");
                try {
                    DR.setIDDetalleFacturas(Integer.parseInt(txtID.getText()));
                    PreparedStatement pst = ob.prepareStatement("Select ProductosID, Descripcion, Cantidad, UMedida, PrecioUnitario, Total FROM inventariofcj.Datos_Detallesfactura INNER JOIN inventariofcj.Datos_Almacen ON IDAlmacen = ProductosID WHERE FacturasID = ?;");
                    pst.setInt(1, DR.getIDDetalleFacturas());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()){
                        do {
                            table.addCell(rs.getString(1));
                            table.addCell(rs.getString(2));
                            table.addCell(rs.getString(3));
                            table.addCell(rs.getString(4));
                            table.addCell(rs.getString(5));
                            table.addCell(rs.getString(6));
                        } while (rs.next());
                        document.add(table);   
                    }             
                }catch (Exception e){
                }
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Total: "+txtTotal.getText()));
                document.add(new Paragraph("Total Cantidad: "+txtTotalCant.getText()));
                document.add(new Paragraph(" "));
                document.close();
                JOptionPane.showMessageDialog(null, "Reporte creado");
                openPDF(nombrereporte);
            }catch (Exception e){
                e.printStackTrace();
            }
        }      
    }//GEN-LAST:event_Bt_ExportarPDFActionPerformed
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
            java.util.logging.Logger.getLogger(Dialog_DetalleFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dialog_DetalleFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dialog_DetalleFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dialog_DetalleFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dialog_DetalleFactura dialog = new Dialog_DetalleFactura(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bt_Exportar;
    private javax.swing.JButton Bt_ExportarPDF;
    private javax.swing.JTable Tabla_Facturas;
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
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    private static javax.swing.JTextField txtID;
    private static javax.swing.JTextField txtLinea;
    private static javax.swing.JTextField txtProvee;
    private javax.swing.JTextField txtProyecto;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalCant;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}