package utils;

import dominio.Fee;
import dominio.Person;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Isra
 */
public class Report {

    private Connection conn;

    public Report() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //se carga el driver
            String url = "jdbc:mysql://localhost/eyeprest";
            conn = DriverManager.getConnection(url, "root", "root");
        } catch (Exception ex) {
            System.out.println("errrrrrrrrrrrrrrrrrror: " + ex);
        }
    }
    
    public static void printInput(Fee fee, Person person){
        try{
            String dirReport = Utilidades.dirPath() + "\\reports\\inputReport.jrxml";
            JasperReport principalReport = JasperCompileManager.compileReport(dirReport);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("personName",person.getPn_first_name()+ " " + person.getPn_last_name());
            parameters.put("personIdentification",person.getPn_identification());
            parameters.put("personAddress",person.getListAddress().get(0).getAdr_street());
            parameters.put("feeNumber",fee.getFe_number());
            parameters.put("feeValue",fee.getFe_payment());
            parameters.put("feeDate",fee.getFe_expiration_date());
            
            JasperPrint jp = JasperFillManager.fillReport(principalReport, parameters);//datos
            viewReport(jp);
            
        }catch(JRException ex){
            System.out.println(ex);
        }
    }

//    public static void printFacture(Facture facture, Client client) {
//
//        try {
//            String dirReporte = Utilidades.dirPath() + "\\reports\\buergerKiReport.jrxml";
//             //String dirReporte = Utilidades.dirPath() + "\\reports\\imagenesFacture.jrxml";
//            System.out.println(dirReporte);
//            JasperReport reportePrincipal = JasperCompileManager.compileReport(dirReporte);
//            Map<String, Object> parametros = new HashMap<>();
//            
//            parametros.put("nombreCliente", client.getNombre() + " " + client.getApellido());
//            parametros.put("rucCliente", client.getCedula());
//            parametros.put("dirCliente", client.getDireccion());
//            parametros.put("telfCliente", client.getTelefono());
//
//            parametros.put("subtotal", "" + facture.getSubTotal());
//            parametros.put("iva", "" + facture.getIva());
//            parametros.put("total", "" + facture.getTotalFacture());
//            parametros.put("efectivo", "" + facture.getFactureCash());
//            parametros.put("cambio", "" + facture.getFactureChange());
//
//            //parametros.put("descuentoText", "" + ticket.getDescuentoTicket());
//            //Descripcion,Precio,Cantidad,Total  asi se debe poner los nombres en el modeltable
//            System.out.println("itemsssssssssss: ------------------------\n" + facture.getItemsFacture());
//            ItemsTableModel modelTableItem = new ItemsTableModel(facture.getItemsFacture());
//            JRTableModelDataSource datosIngredientes = new JRTableModelDataSource(modelTableItem);
//            JasperPrint jp = JasperFillManager.fillReport(reportePrincipal, parametros, datosIngredientes);//datos
//            viewReport(jp);
//
//        } catch (JRException ex) {
//            System.out.println(ex);
//        }
//    }
//
//    public static void printPetitionNote(SellNote sellNote, Client client) {
//
//        try {
//            //String dirReporte = Utilidades.dirPath() + "\\reports\\PetitionNoteReport.jrxml";
//            String dirReporte = Utilidades.dirPath() + "\\reports\\imagenesPetitionNote.jrxml";
//            System.out.println(dirReporte);
//            JasperReport reportePrincipal = JasperCompileManager.compileReport(dirReporte);
//            Map<String, Object> parametros = new HashMap<>();
//            //parametros.put("clientText", client.getNombre() + " " + client.getApellido());
//
//            //parametros.put("nombreEmpresa", "BURGER KI");
//            //parametros.put("rucEmpresa", "RUC:" + " 1102120621");
//            //parametros.put("dirEmpresa", "AV GRAN COLOMBIA Y GUARANDA");
//            //parametros.put("telEmpresa", "TELF:"+" 096601451");
//            //parametros.put("nombreGerente", "VICTOR ENRIQUE VIÑAN LUDEÑA");
//            //parametros.put("sriValidityDate", ticket.getFechaTicket().toString());
//            //parametros.put("puestoEmpresa", "GERENTE PROPIETARIO");
//            //parametros.put("numeroTicket", "N° TICKET: " + ticket.getNumeroTicket());
//            parametros.put("nombreCliente", client.getNombre() + " " + client.getApellido());
//            parametros.put("rucCliente", client.getCedula());
//            parametros.put("dirCliente", client.getDireccion());
//            parametros.put("telfCliente", client.getTelefono());
//
//            parametros.put("total", sellNote.getSellNote_total());
//            parametros.put("anticipo", "" + sellNote.getSellNote_advance());
//            parametros.put("saldo", "" + sellNote.getSellNote_balance());
//            //parametros.put("efectivo", "" + facture.getFactureCash());
//            //parametros.put("cambio", "" + facture.getFactureChange());
//
//            //parametros.put("descuentoText", "" + ticket.getDescuentoTicket());
//            //Descripcion,Precio,Cantidad,Total  asi se debe poner los nombres en el modeltable
//            System.out.println("itemsssssssssss: ------------------------\n" + sellNote.getSellNotes_Items());
//            ItemsTableModel modelTableItem = new ItemsTableModel(sellNote.getSellNotes_Items());
//            JRTableModelDataSource datosIngredientes = new JRTableModelDataSource(modelTableItem);
//            JasperPrint jp = JasperFillManager.fillReport(reportePrincipal, parametros, datosIngredientes);//datos
//            viewReport(jp);
//
//        } catch (JRException ex) {
//            System.out.println(ex);
//        }
//    }
//    
//    public static void printDeliveryNote(SellNote sellNote, Client client) {
//
//        try {
//            //String dirReporte = Utilidades.dirPath() + "\\reports\\DeliveryNoteReport.jrxml";
//            String dirReporte = Utilidades.dirPath() + "\\reports\\imagenesDeliveryNote.jrxml";
//            System.out.println(dirReporte);
//            JasperReport reportePrincipal = JasperCompileManager.compileReport(dirReporte);
//            Map<String, Object> parametros = new HashMap<>();
//            //parametros.put("clientText", client.getNombre() + " " + client.getApellido());
//
//            //parametros.put("nombreEmpresa", "BURGER KI");
//            //parametros.put("rucEmpresa", "RUC:" + " 1102120621");
//            //parametros.put("dirEmpresa", "AV GRAN COLOMBIA Y GUARANDA");
//            //parametros.put("telEmpresa", "TELF:"+" 096601451");
//            //parametros.put("nombreGerente", "VICTOR ENRIQUE VIÑAN LUDEÑA");
//            //parametros.put("sriValidityDate", ticket.getFechaTicket().toString());
//            //parametros.put("puestoEmpresa", "GERENTE PROPIETARIO");
//            //parametros.put("numeroTicket", "N° TICKET: " + ticket.getNumeroTicket());
//            parametros.put("nombreCliente", client.getNombre() + " " + client.getApellido());
//            parametros.put("rucCliente", client.getCedula());
//            parametros.put("dirCliente", client.getDireccion());
//            parametros.put("telfCliente", client.getTelefono());
//
//            parametros.put("total", sellNote.getSellNote_total());
//            parametros.put("anticipo", "" + sellNote.getSellNote_advance());
//            parametros.put("saldo", "" + sellNote.getSellNote_balance());
//            //parametros.put("efectivo", "" + facture.getFactureCash());
//            //parametros.put("cambio", "" + facture.getFactureChange());
//
//            //parametros.put("descuentoText", "" + ticket.getDescuentoTicket());
//            //Descripcion,Precio,Cantidad,Total  asi se debe poner los nombres en el modeltable
//            System.out.println("itemsssssssssss: ------------------------\n" + sellNote.getSellNotes_Items());
//            ItemsTableModel modelTableItem = new ItemsTableModel(sellNote.getSellNotes_Items());
//            JRTableModelDataSource datosIngredientes = new JRTableModelDataSource(modelTableItem);
//            JasperPrint jp = JasperFillManager.fillReport(reportePrincipal, parametros, datosIngredientes);//datos
//            viewReport(jp);
//
//        } catch (JRException ex) {
//            System.out.println(ex);
//        }
//    }

    public void printAllTickets(Date ini, Date fin, double total) {
        try {
            String reporte = System.getProperty("user.dir") + "/reports/AllTicket.jasper";
            System.out.println(reporte);
            if (reporte == null) {
                JOptionPane.showMessageDialog(null, "No se ha podido Generar el Reporte", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(reporte);
            } catch (Exception jre) {
                JOptionPane.showMessageDialog(null, "Problema al cargar archivo maestro", "ERROR REPORTE", JOptionPane.ERROR_MESSAGE);
                System.out.println("Error: " + jre);
                return;
            }
            //parametros.put("clientText", client.getNombre() + " " + client.getApellido());

            String in = Operaciones.formatDate(ini);
            String fn = Operaciones.formatDate(fin);

            Map parametros = new HashMap();
            parametros.put("ini", in);
            parametros.put("fin", fn);
            parametros.put("totalTic", Math.rint(total * 100) / 100 + "");

            //printReport(masterReport, parametros, "CIERRE DE CAJA");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

//    public void printClosingCashPetitionNote(CCPetitionNoteTableModel ccpntm, Date in, Date fin, double totAdvance, double totBalance) {
//        try {
//
//            String reporte = System.getProperty("user.dir") + "/src/moduleclosingcash/report/closingCashPetitionNote.jasper";
//            if (reporte == null) {
//                mensajeErrorReporte();
//                return;
//            }
//            JasperReport masterReport = null;
//            try {
//                masterReport = (JasperReport) JRLoader.loadObject(reporte);
//            } catch (Exception jre) {
//                JOptionPane.showMessageDialog(null, "Problema al cargar archivo maestro", "ERROR REPORTE", JOptionPane.ERROR_MESSAGE);
//                System.out.println("Error: " + jre);
//                return;
//            }
//
//            Map parametro = new HashMap();
//
//            parametro.put("dateIni", Operaciones.formatDate(in));
//            parametro.put("dateFin", Operaciones.formatDate(fin));
//            //parametro.put("totVent", Operaciones.parteDecimal(Math.rint(valV * 100) / 100, GeneralParameter.PRECISION));
//            parametro.put("totAdvance", Math.rint(totAdvance * 100) / 100 + "");
//            parametro.put("totBalance", Math.rint(totBalance * 100) / 100 + "");
//            parametro.put("enterpriseName", "IMAGENES " + " - REPORTE NOTAS DE PEDIDO");
//
//            JRTableModelDataSource datosIngredientes = new JRTableModelDataSource(ccpntm);
//            printReport(masterReport, parametro, datosIngredientes, "REPORTE NOTA DE PEDIDO");
//
//            //printReport(masterReport, parametro, "TODAS LAS VENTAS");
//        } catch (Exception e) {
//            System.out.println("errorrrrrrrrrrrrrrrrrrrrrrr: " + e);
//            e.printStackTrace();
//        }
//    }
//    
//     public void printClosingCashDeliveryNote(CCDeliveryNoteTableModel ccdntm, Date in, Date fin, double totDeliveryNote) {
//        try {
//
//            String reporte = System.getProperty("user.dir") + "/src/moduleclosingcash/report/closingCashDeliveryNote.jasper";
//            if (reporte == null) {
//                mensajeErrorReporte();
//                return;
//            }
//            JasperReport masterReport = null;
//            try {
//                masterReport = (JasperReport) JRLoader.loadObject(reporte);
//            } catch (Exception jre) {
//                JOptionPane.showMessageDialog(null, "Problema al cargar archivo maestro", "ERROR REPORTE", JOptionPane.ERROR_MESSAGE);
//                System.out.println("Error: " + jre);
//                return;
//            }
//
//            Map parametro = new HashMap();
//
//            parametro.put("dateIni", Operaciones.formatDate(in));
//            parametro.put("dateFin", Operaciones.formatDate(fin));
//            //parametro.put("totVent", Operaciones.parteDecimal(Math.rint(valV * 100) / 100, GeneralParameter.PRECISION));
//            parametro.put("totDeliveryNote", Math.rint(totDeliveryNote * 100) / 100 + "");
//            //parametro.put("totBalance", Math.rint(totBalance * 100) / 100 + "");
//            parametro.put("enterpriseName", "IMAGENES " + " - REPORTE NOTAS DE ENTREGA");
//
//            JRTableModelDataSource datosIngredientes = new JRTableModelDataSource(ccdntm);
//            printReport(masterReport, parametro, datosIngredientes, "REPORTE NOTA DE ENTREGA");
//
//            //printReport(masterReport, parametro, "TODAS LAS VENTAS");
//        } catch (Exception e) {
//            System.out.println("errorrrrrrrrrrrrrrrrrrrrrrr: " + e);
//            e.printStackTrace();
//        }
//    }
     
//     public void printInventory(InventoryTableModel itm) {
//        try {
//
//            String reporte = System.getProperty("user.dir") + "/src/views/reports/InvetaryReport.jasper";
//            if (reporte == null) {
//                mensajeErrorReporte();
//                return;
//            }
//            JasperReport masterReport = null;
//            try {
//                masterReport = (JasperReport) JRLoader.loadObject(reporte);
//            } catch (JRException jre) {
//                JOptionPane.showMessageDialog(null, "Problema al cargar archivo maestro", "ERROR REPORTE", JOptionPane.ERROR_MESSAGE);
//                System.out.println("Error: " + jre);
//                return;
//            }
//
//            Map parametro = new HashMap();
//
//            //parametro.put("dateIni", Operaciones.formatDate(in));
//            //parametro.put("dateFin", Operaciones.formatDate(fin));
//            //parametro.put("totVent", Operaciones.parteDecimal(Math.rint(valV * 100) / 100, GeneralParameter.PRECISION));
//            //parametro.put("totDeliveryNote", Math.rint(totDeliveryNote * 100) / 100 + "");
//            //parametro.put("totBalance", Math.rint(totBalance * 100) / 100 + "");
//            parametro.put("enterpriseName", "IMAGENES " + " - REPORTE DE INVENTARIO");
//
//            JRTableModelDataSource datosIngredientes = new JRTableModelDataSource(itm);
//            printReport(masterReport, parametro, datosIngredientes, "REPORTE DE INVENTARIO");
//
//            //printReport(masterReport, parametro, "TODAS LAS VENTAS");
//        } catch (HeadlessException e) {
//            System.out.println("errorrrrrrrrrrrrrrrrrrrrrrr: " + e);
//        }
//    }
     
//      public void printClosingCashFactures(CCFactureTableModel ccftm, Date in, Date fin, double totFacture) {
//        try {
//
//            String reporte = System.getProperty("user.dir") + "/src/moduleclosingcash/report/closingCashFacture.jasper";
//            if (reporte == null) {
//                mensajeErrorReporte();
//                return;
//            }
//            JasperReport masterReport = null;
//            try {
//                masterReport = (JasperReport) JRLoader.loadObject(reporte);
//            } catch (JRException jre) {
//                JOptionPane.showMessageDialog(null, "Problema al cargar archivo maestro", "ERROR REPORTE", JOptionPane.ERROR_MESSAGE);
//                System.out.println("Error: " + jre);
//                return;
//            }
//
//            Map parametro = new HashMap();
//
//            parametro.put("dateIni", Operaciones.formatDate(in));
//            parametro.put("dateFin", Operaciones.formatDate(fin));
//            //parametro.put("totVent", Operaciones.parteDecimal(Math.rint(valV * 100) / 100, GeneralParameter.PRECISION));
//            parametro.put("totFactures", Math.rint(totFacture * 100) / 100 + "");
//            //parametro.put("totBalance", Math.rint(totBalance * 100) / 100 + "");
//            parametro.put("enterpriseName", "IMAGENES " + " - REPORTE FACTURAS");
//
//            JRTableModelDataSource datosIngredientes = new JRTableModelDataSource(ccftm);
//            printReport(masterReport, parametro, datosIngredientes, "REPORTE FACTURA");
//
//            //printReport(masterReport, parametro, "TODAS LAS VENTAS");
//        } catch (Exception e) {
//            System.out.println("errorrrrrrrrrrrrrrrrrrrrrrr: " + e);
//            e.printStackTrace();
//        }
//    }
//      
//      public void printClosingCashEgress(AccountingEntryTableModel aetm, Date in, Date fin, double totEgress) {
//        try {
//
//            String reporte = System.getProperty("user.dir") + "/src/moduleclosingcash/report/closingCashEgress.jasper";
//            if (reporte == null) {
//                mensajeErrorReporte();
//                return;
//            }
//            JasperReport masterReport = null;
//            try {
//                masterReport = (JasperReport) JRLoader.loadObject(reporte);
//            } catch (Exception jre) {
//                JOptionPane.showMessageDialog(null, "Problema al cargar archivo maestro", "ERROR REPORTE", JOptionPane.ERROR_MESSAGE);
//                System.out.println("Error: " + jre);
//                return;
//            }
//
//            Map parametro = new HashMap();
//
//            parametro.put("dateIni", Operaciones.formatDate(in));
//            parametro.put("dateFin", Operaciones.formatDate(fin));
//            //parametro.put("totVent", Operaciones.parteDecimal(Math.rint(valV * 100) / 100, GeneralParameter.PRECISION));
//            parametro.put("totEgress", Math.rint(totEgress * 100) / 100 + "");
//            //parametro.put("totBalance", Math.rint(totBalance * 100) / 100 + "");
//            parametro.put("enterpriseName", "IMAGENES " + " - REPORTE EGRESOS");
//
//            JRTableModelDataSource datosIngredientes = new JRTableModelDataSource(aetm);
//            printReport(masterReport, parametro, datosIngredientes, "REPORTE EGRESO");
//
//            //printReport(masterReport, parametro, "TODAS LAS VENTAS");
//        } catch (Exception e) {
//            System.out.println("errorrrrrrrrrrrrrrrrrrrrrrr: " + e);
//            e.printStackTrace();
//        }
//    }

    private void mensajeErrorReporte() {
        JOptionPane.showMessageDialog(null, "No se ha podido Generar el Reporte", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
    }

    public void printReport(JasperReport masterReport, Map parametro, JRTableModelDataSource datos, String titul) {
        try {
            //@SuppressWarnings("rawtypes")

            //System.out.println("aqui");
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, datos);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            JDialog vistaPrevia = new JDialog(jviewer, true);
            vistaPrevia.setTitle(titul);
            vistaPrevia.setContentPane(jviewer.getContentPane());
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            vistaPrevia.setBounds(100, 100, d.width - 200, d.height - 200);
            vistaPrevia.setLocationRelativeTo(null);
            vistaPrevia.validate();
            vistaPrevia.setVisible(true);
        } catch (JRException ex) {
            System.out.println("Error en el reporte: " + ex);
        }
    }

//     public void printReport(JasperReport masterReport, Map parametro, String titul) {
//        try {
//            //@SuppressWarnings("rawtypes")
//
//            //System.out.println("aqui");            
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conn);
//
//            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
//            JDialog vistaPrevia = new JDialog(jviewer, true);
//            vistaPrevia.setTitle(titul);
//            vistaPrevia.setContentPane(jviewer.getContentPane());
//            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//            vistaPrevia.setBounds(100, 100, d.width - 200, d.height - 200);
//            vistaPrevia.setLocationRelativeTo(null);
//            vistaPrevia.validate();
//            vistaPrevia.setVisible(true);
//        } catch (JRException ex) {
//            System.out.println("Error en el reporte: " + ex);
//        }
//    }
    public static void viewReport(JasperPrint jasperPrint) {
        try {
            //@SuppressWarnings("rawtypes")
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            JDialog vistaPrevia = new JDialog(jviewer, true);
            vistaPrevia.setTitle(jviewer.getName());
            vistaPrevia.setContentPane(jviewer.getContentPane());
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            vistaPrevia.setBounds(100, 100, d.width - 200, d.height - 200);
            vistaPrevia.setLocationRelativeTo(null);
            vistaPrevia.validate();
            vistaPrevia.setVisible(true);
        } catch (Exception ex) {
            System.out.println("Error en el viewer " + ex);
        }
    }
}
