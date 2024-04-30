package Presentacion;
import Datos.DDetalles_Recibos;
import Logica.ConexionSQL;
import Logica.LDetalleRecibos;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
public class Dialog_DetalleRecibos extends javax.swing.JDialog {
    DefaultTableModel miModelo;
    public Dialog_DetalleRecibos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(getIconImage());
        miModelo = (DefaultTableModel) Tabla_Recibos.getModel();
        Tabla_Recibos.getColumn("Codigo").setWidth(10);
        Tabla_Recibos.getColumn("Codigo").setPreferredWidth(10);     
        Tabla_Recibos.getColumn("Descripcion").setWidth(220);
        Tabla_Recibos.getColumn("Descripcion").setPreferredWidth(220);     
        Tabla_Recibos.getColumn("Cantidad").setWidth(20);
        Tabla_Recibos.getColumn("Cantidad").setPreferredWidth(20);      
        Tabla_Recibos.getColumn("U/Medida").setWidth(20);
        Tabla_Recibos.getColumn("U/Medida").setPreferredWidth(20);      
        Tabla_Recibos.getColumn("P/Unitario").setWidth(20);
        Tabla_Recibos.getColumn("P/Unitario").setPreferredWidth(20);       
        Tabla_Recibos.getColumn("Lineas").setWidth(20);
        Tabla_Recibos.getColumn("Lineas").setPreferredWidth(20);
        Tabla_Recibos.getColumn("Total").setWidth(20);
        Tabla_Recibos.getColumn("Total").setPreferredWidth(20);
        txtIDRecibo.setEditable(false);
        txtPersonaR.setEditable(false);
        txtPersonaE.setEditable(false);
        txtFecha.setEditable(false);
        txt_Proyecto.setEditable(false);
        txt_Cliente.setEditable(false);
        txt_Ubic.setEditable(false);
        txt_Hora.setEditable(false);
        txtTotal.setEditable(false); 
        txtTotalCant.setEditable(false);
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
    public java.awt.Image getIconImage (){
        java.awt.Image Log = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/PedidoD.png")); 
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
        txtIDRecibo = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPersonaE = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPersonaR = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_Hora = new javax.swing.JTextField();
        txt_Proyecto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_Ubic = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_Cliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Recibos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        Bt_Exportar = new javax.swing.JButton();
        Bt_ExportarPDF = new javax.swing.JButton();
        txtTotalCant = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalles de Salidas");
        setIconImage(null);

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID:");

        txtIDRecibo.setBackground(new java.awt.Color(153, 153, 153));
        txtIDRecibo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtFecha.setBackground(new java.awt.Color(153, 153, 153));
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Fecha: ");

        txtPersonaE.setBackground(new java.awt.Color(153, 153, 153));
        txtPersonaE.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Persona Entrega:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Persona Recibe:");

        txtPersonaR.setBackground(new java.awt.Color(153, 153, 153));
        txtPersonaR.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Hora:");

        txt_Hora.setBackground(new java.awt.Color(153, 153, 153));
        txt_Hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_Proyecto.setBackground(new java.awt.Color(153, 153, 153));
        txt_Proyecto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Proyecto:");
        jLabel8.setToolTipText("");

        txt_Ubic.setBackground(new java.awt.Color(153, 153, 153));
        txt_Ubic.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ubicacion:");

        txt_Cliente.setBackground(new java.awt.Color(153, 153, 153));
        txt_Cliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Cliente:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFecha)
                            .addComponent(txt_Hora, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(txtIDRecibo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txt_Ubic, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(258, 258, 258))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(258, 258, 258))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPersonaR, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPersonaE, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(txt_Proyecto)
                            .addComponent(txt_Cliente))
                        .addGap(44, 44, 44))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtIDRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPersonaE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPersonaR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txt_Hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_Proyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_Ubic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos del Salidas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        Tabla_Recibos.setBackground(new java.awt.Color(204, 204, 204));
        Tabla_Recibos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad", "U/Medida", "P/Unitario", "Lineas", "Total"
            }
        ));
        jScrollPane1.setViewportView(Tabla_Recibos);

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

        Bt_ExportarPDF.setBackground(new java.awt.Color(0, 51, 153));
        Bt_ExportarPDF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bt_ExportarPDF.setForeground(new java.awt.Color(255, 255, 255));
        Bt_ExportarPDF.setText("Exportar PDF");
        Bt_ExportarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_ExportarPDFActionPerformed(evt);
            }
        });

        txtTotalCant.setBackground(new java.awt.Color(153, 153, 153));
        txtTotalCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Total Cantidad:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Bt_Exportar)
                .addGap(44, 44, 44)
                .addComponent(Bt_ExportarPDF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalCant, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(Bt_Exportar)
                    .addComponent(Bt_ExportarPDF)
                    .addComponent(txtTotalCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(15, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            String nombrereporte = ruta+""+txtIDRecibo.getText()+".xlsx";
            String nombrehoja = "Factura " +txtIDRecibo.getText();
            XSSFWorkbook libroinventario = new XSSFWorkbook();
            XSSFSheet hojainventario = libroinventario.createSheet(nombrehoja);
            String[] titulos = new String[]{"Codigo","Descripcion","Cantidad","U/Medida","P/Unitario","Lineas","Total"};
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
            XSSFRow Idrecibo = hojainventario.createRow(1);
            XSSFCell celdarecibo1 = Idrecibo.createCell(0);
            XSSFCell celdarecibo2 = Idrecibo.createCell(1);
            celdarecibo1.setCellValue("Recibo: ");
            celdarecibo2.setCellValue(txtIDRecibo.getText());
            celdarecibo2.setCellStyle(csdatos);
            XSSFRow fecha = hojainventario.createRow(3);
            XSSFCell celdafecha1 = fecha.createCell(0);
            XSSFCell celdafecha2 = fecha.createCell(1);
            celdafecha1.setCellValue("Fecha de Registro: ");
            celdafecha2.setCellValue(txtFecha.getText());
            celdafecha2.setCellStyle(csdatos);
            XSSFRow Hora = hojainventario.createRow(5);
            XSSFCell celdaHora1 = Hora.createCell(0);
            XSSFCell celdaHora2 = Hora.createCell(1);
            celdaHora1.setCellValue("Hora de Registro: ");
            celdaHora2.setCellValue(txt_Hora.getText());
            celdaHora2.setCellStyle(csdatos);
            XSSFRow UBI = hojainventario.createRow(7);
            XSSFCell celdaUBI1 = UBI.createCell(0);
            XSSFCell celdaUBI2 = UBI.createCell(1);
            celdaUBI1.setCellValue("Ubicacion: ");
            celdaUBI2.setCellValue(txt_Ubic.getText());
            celdaUBI2.setCellStyle(csdatos);
            XSSFRow PE = hojainventario.createRow(9);
            XSSFCell celdaPE1 = PE.createCell(0);
            XSSFCell celdaPE2 = PE.createCell(1);
            celdaPE1.setCellValue("Persona Entrega: ");
            celdaPE2.setCellValue(txtPersonaE.getText());
            celdaPE2.setCellStyle(csdatos);
            XSSFRow PR = hojainventario.createRow(11);
            XSSFCell celdaPR1 = PR.createCell(0);
            XSSFCell celdaPR2 = PR.createCell(1);
            celdaPR1.setCellValue("Persona Recibe: ");
            celdaPR2.setCellValue(txtPersonaR.getText());
            celdaPR2.setCellStyle(csdatos);     
            XSSFRow proyecto = hojainventario.createRow(13);
            XSSFCell celdaproye1 = proyecto.createCell(0);
            XSSFCell celdaproye2 = proyecto.createCell(1);
            celdaproye1.setCellValue("Proyecto: ");
            celdaproye2.setCellValue(txt_Proyecto.getText());
            celdaproye2.setCellStyle(csdatos);
            XSSFRow cliente = hojainventario.createRow(15);
            XSSFCell celdacli1 = cliente.createCell(0);
            XSSFCell celdacli2 = cliente.createCell(1);
            celdacli1.setCellValue("Cliente: ");
            celdacli2.setCellValue(txt_Cliente.getText());
            celdacli2.setCellStyle(csdatos);
            XSSFRow titulo = hojainventario.createRow(17);
            for(int i=0;i<titulos.length;i++){
                XSSFCell celda = titulo.createCell(i);
                celda.setCellValue(titulos[i]);
                celda.setCellStyle(cscabeza);
            }
            int filacontenido = 18;
            for(int i=0;i<Tabla_Recibos.getRowCount();i++){
                XSSFRow contenido = hojainventario.createRow(filacontenido);
                filacontenido++;
                for(int j=0;j<Tabla_Recibos.getColumnCount();j++){
                    XSSFCell celda = contenido.createCell(j);
                    celda.setCellValue(Tabla_Recibos.getValueAt(i, j).toString()); 
                    celda.setCellStyle(cscontenido);
                }
            }
            XSSFRow Total = hojainventario.createRow(filacontenido+2);
            XSSFCell celdatotal1 = Total.createCell(5);
            XSSFCell celdatotal2 = Total.createCell(6);
            celdatotal1.setCellValue("Total: ");
            celdatotal2.setCellValue(Double.parseDouble(txtTotal.getText()));
            celdatotal2.setCellStyle(csdatos2);
            XSSFRow TotalCant = hojainventario.createRow(filacontenido+3);
            XSSFCell celdatotalC1 = TotalCant.createCell(5);
            XSSFCell celdatotalC2 = TotalCant.createCell(6);
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
            hojainventario.autoSizeColumn(6);
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
        DDetalles_Recibos DR = new DDetalles_Recibos(); 
        Connection ob = ObjetoCon.getConnection();
        if (opcionPDF == JFileChooser.APPROVE_OPTION){
            try {
                Document document = new Document(PageSize.A4);
                String ruta = seleccionarPD.getSelectedFile().getAbsolutePath();
                String nombrereporte = ruta+""+txtIDRecibo.getText()+".PDF";
                PdfWriter.getInstance(document, new FileOutputStream(nombrereporte));
                com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/Imagenes/FCJ IMAGEN.jpg");
                header.scaleToFit(150,150);
                header.setAlignment(Chunk.ALIGN_LEFT);     
                document.open(); 
                document.add(header);
                Paragraph title = new Paragraph("Recibos Grupo FCJ");
                title.setAlignment(Paragraph.ALIGN_CENTER);
                title.setFont(FontFactory.getFont("Tahoma",30,BaseColor.DARK_GRAY));
                document.add(title);
                document.add(new Paragraph("   "));
                document.add(new Paragraph("Numero de Recibo: "+txtIDRecibo.getText()));
                document.add(new Paragraph("Cliente: "+txt_Cliente.getText()));
                document.add(new Paragraph("Proyecto: "+txt_Proyecto.getText()));
                document.add(new Paragraph("Fecha de Registro: "+txtFecha.getText()));
                document.add(new Paragraph("Hora de Registro: "+txt_Hora.getText()));
                document.add(new Paragraph("Ubicacion: "+txt_Ubic.getText()));
                document.add(new Paragraph("   "));
                document.add(new Paragraph("   "));
                PdfPTable table = new PdfPTable(7);
                float[] columnWidths = {30f, 160f, 75f, 75f, 75f, 75f, 60f}; 
                table.setWidths(columnWidths);    
                table.addCell("ID");
                table.addCell("Descripcion");
                table.addCell("Cantidad");
                table.addCell("U/Medida");
                table.addCell("P/Unitario");
                table.addCell("Ubicacion");
                table.addCell("Total");
                try {
                    DR.setIDdetalleRecib(Integer.parseInt(txtIDRecibo.getText()));
                    PreparedStatement pst = ob.prepareStatement("Select ProductosID, Descripcion, Cantidad, UMedida, PrecioUnitario, Lineas ,Total FROM inventariofcj.Datos_DetalleRecibo INNER JOIN inventariofcj.Datos_Almacen ON IDAlmacen = Productosid WHERE ReciboID = ?;");
                    pst.setInt(1, DR.getIDdetalleRecib());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()){
                        do {
                            table.addCell(rs.getString(1));
                            table.addCell(rs.getString(2));
                            table.addCell(rs.getString(3));
                            table.addCell(rs.getString(4));
                            table.addCell(rs.getString(5));
                            table.addCell(rs.getString(6));
                            table.addCell(rs.getString(7));
                        } while (rs.next());
                        document.add(table);   
                    }             
                }catch (Exception e){
                }
                document.add(new Paragraph("   "));
                document.add(new Paragraph("Total Cantidad: "+txtTotalCant.getText()));
                document.add(new Paragraph("Total: "+txtTotal.getText()));
                document.add(new Paragraph("   "));
                document.add(new Paragraph("   "));
                document.add(new Paragraph("   "));
                document.add(new Paragraph(" _______________________________                        _______________________________     "));
                document.add(new Paragraph("  Firma de Persona Entrega "+txtPersonaE.getText()+"                                Firma de Persona Recibo "+txtPersonaR.getText()+""));         
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
            java.util.logging.Logger.getLogger(Dialog_DetalleRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dialog_DetalleRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dialog_DetalleRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dialog_DetalleRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dialog_DetalleRecibos dialog = new Dialog_DetalleRecibos(new javax.swing.JFrame(), true);
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
    private static javax.swing.JTextField txtFecha;
    private static javax.swing.JTextField txtIDRecibo;
    private static javax.swing.JTextField txtPersonaE;
    private static javax.swing.JTextField txtPersonaR;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalCant;
    private javax.swing.JTextField txt_Cliente;
    private javax.swing.JTextField txt_Hora;
    private javax.swing.JTextField txt_Proyecto;
    private javax.swing.JTextField txt_Ubic;
    // End of variables declaration//GEN-END:variables

    void setdatosR(String IDRec, String FechaRec, String PersonaE, String PersonaR,String Client,String Proye,String ubic,String Hora) {
        txtIDRecibo.setText(IDRec);
        txtFecha.setText(FechaRec);
        txtPersonaE.setText(PersonaE);
        txtPersonaR.setText(PersonaR);
        txt_Ubic.setText(ubic);
        txt_Proyecto.setText(Proye);
        txt_Cliente.setText(Client);
        txt_Hora.setText(Hora);
        MostrarDetalleRec(Integer.parseInt(IDRec));
    }
    public void MostrarDetalleRec(int IDRec){
        miModelo = (DefaultTableModel) Tabla_Recibos.getModel();
        DDetalles_Recibos dts = new DDetalles_Recibos();
        LDetalleRecibos LDT = new LDetalleRecibos();
        dts.setReciboID(IDRec);
        miModelo = LDT.MDetalleRecibos(dts);
        Tabla_Recibos.setModel(miModelo);
        SumaTotal();
        SumarCant();
    }
    public void SumaTotal(){
        DecimalFormat ft = new DecimalFormat("####.00");
        double TotalParcial= 0, TotalFinal = 0;
        if (Tabla_Recibos.getRowCount() > 0){
            for (int i=0; i < Tabla_Recibos.getRowCount(); i++){
            TotalParcial = Double.parseDouble(Tabla_Recibos.getValueAt(i, 6).toString());
            TotalFinal += TotalParcial;
            txtTotal.setText(ft.format(TotalFinal));
            txtTotal.setForeground(Color.black);
            }
        }     
    }  
}