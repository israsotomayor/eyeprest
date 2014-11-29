package utils;

import com.toedter.calendar.JDateChooser;
import dominio.Fee;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class Operaciones {
    
    public static  BigDecimal roundValues(Double value, int precision){
      //String val = value+"";
      BigDecimal big = new BigDecimal("" + value);
      big = big.setScale(precision, RoundingMode.HALF_UP);
      System.out.println("Número : "+big);
      
      return big;
    }

    //Para saber que JRadioButton esta seleccionado del button group
    public static JRadioButton getSelection(ButtonGroup group) {

        JRadioButton rb = new JRadioButton();

        for (Enumeration e = group.getElements(); e.hasMoreElements();) {
            JRadioButton b = (JRadioButton) e.nextElement();
            if (b.getModel() == group.getSelection()) {
                rb = b;
            }
        }

        return rb;
    }

    public static java.sql.Date formatearDate(String fecha) {
        int dd, mm, yy;

        dd = Integer.parseInt(fecha.charAt(0) + "" + fecha.charAt(1));
        mm = Integer.parseInt(fecha.charAt(3) + "" + fecha.charAt(4)) - 1;
        yy = Integer.parseInt(fecha.charAt(6) + "" + fecha.charAt(7) + "" + fecha.charAt(8) + "" + fecha.charAt(9)) - 1900;

        return (new java.sql.Date(yy, mm, dd));
    }

    public static String formatearFecha(JDateChooser fec) {
        String aux;
        Date s = fec.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String cadenafecha = formato.format(s);
        aux = cadenafecha;
        return aux;
    }

    public static String dateFormat(Date fec) {
        String aux;
        //Date s;
        System.out.println("" + fec);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        //s = formato.parse(fec);
        String cadenafecha = formato.format(fec);
        aux = cadenafecha;
        return aux;
    }

    public static Time getTime() {
        Calendar Calendario = Calendar.getInstance();

//        String año = Integer.toString(Calendario.get(Calendar.YEAR));
//        String mes = Integer.toString(Calendario.get(Calendar.MONTH) + 1);
//        String dia = Integer.toString(Calendario.get(Calendar.DATE));
        int hora = Calendario.get(Calendar.HOUR);
        int minuto = Calendario.get(Calendar.MINUTE);
        int segundo = Calendario.get(Calendar.SECOND);
        return (new Time(hora, minuto, segundo));
    }

    public static Time formatearHora(String hora) {
        int h, m, s;
        h = Integer.parseInt(hora.charAt(0) + "" + hora.charAt(1));
        m = Integer.parseInt(hora.charAt(3) + "" + hora.charAt(4));
        s = Integer.parseInt(hora.charAt(6) + "" + hora.charAt(7));
        return (new Time(h, m, s));
    }

    public static void soloNumerosDecimales(java.awt.event.KeyEvent evt, String s) {
        if (evt.getKeyChar() == '.' && s.contains(".")) {
            evt.consume();
            return;
        }
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)) {
            if (caracter != '.') {
                evt.consume();
            }
        }
    }

    public static String parteDecimal(double d, int numDecimal) {
        String este = d + "";
        String val = "";
        for (int i = 0; i < numDecimal; i++) {
            val = val + 1;
        }
        int num = Integer.parseInt(val);
        for (int i = 0; i < este.length(); i++) {
            if (este.charAt(i) == '.') {
                int n = Integer.parseInt(este.substring(i + 1));
                if (n < num && este.substring(i + 1).length() < numDecimal) {
                    for (int j = este.substring(i + 1).length(); j < numDecimal; j++) {
                        este = este + "0";
                    }
                }
                break;
            }
        }
        return este;
    }

    public double formatToDecimales(double valor, int numberDecimal) {
        BigDecimal big = new BigDecimal(valor);
        big = big.setScale(numberDecimal, RoundingMode.HALF_UP);
        return big.doubleValue();
    }

    /**
     * Da formato a una fecha recibida, en el siguiente orden 'Dddddd dd de
     * Mmmmm de yyyy'
     *
     * @param fecha
     * @return
     */
    public static String formFechaToLarge(Date fecha) {
        int dia = fecha.getDate();
        int mes = fecha.getMonth();
        int año = fecha.getYear() + 1900;
        int diasem = fecha.getDay();

        String fec = getDia(diasem) + " " + dia + " de " + getMes(mes) + " de " + año;

        return fec;
    }

    public static String getDia(int dia) {
        if (dia == 1) {
            return "Lunes";
        } else if (dia == 2) {
            return "Martes";
        } else if (dia == 3) {
            return "Miércoles";
        } else if (dia == 4) {
            return "Jueves";
        } else if (dia == 5) {
            return "Viernes";
        } else if (dia == 6) {
            return "Sábado";
        } else if (dia == 0) {
            return "Domingo";
        } else {
            return "Error";
        }
    }

    public static String getMes(int mes) {
        if (mes == 0) {
            return "Enero";
        } else if (mes == 1) {
            return "Febrero";
        } else if (mes == 2) {
            return "Marzo";
        } else if (mes == 3) {
            return "Abril";
        } else if (mes == 4) {
            return "Mayo";
        } else if (mes == 5) {
            return "Junio";
        } else if (mes == 6) {
            return "Julio";
        } else if (mes == 7) {
            return "Agosto";
        } else if (mes == 8) {
            return "Septiembre";
        } else if (mes == 9) {
            return "Octubre";
        } else if (mes == 10) {
            return "Noviembre";
        } else if (mes == 11) {
            return "Diciembre";
        } else {
            return "Error";
        }
    }

    /**
     * Da formato a la fecha recibida en el siguiente orden 'yyyy-mm-dd'
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        int dia = date.getDate();
        int mes = date.getMonth() + 1;
        int año = date.getYear() + 1900;
        String fecha = año + "-" + mes + "-" + dia;
        return fecha;
    }

    public static TableCellRenderer getRenderCellToRigth() {
        TableCellRenderer render = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
//aqui obtengo el render de la calse superior
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//Alineo, puedes alinear cambiar fondo etc, solo seria cuention de validar fia, columna
//si esta seleccionana, depende de tu necesidad
                l.setHorizontalAlignment(SwingConstants.RIGHT);
                if (hasFocus) {
                    l.setForeground(Color.RED);
                } else {
                    l.setForeground(Color.BLACK);
                }
                return l;
            }
        };
        return render;
    }

    public static TableCellRenderer getRenderCellToColor(final Color color) {
        TableCellRenderer render = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
//aqui obtengo el render de la calse superior
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //JLabel jl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//Alineo, puedes alinear cambiar fondo etc, solo seria cuention de validar fia, columna
//si esta seleccionana, depende de tu necesidad
//                l.setHorizontalAlignment(SwingConstants.RIGHT);
                if (hasFocus) {
                    //l.setBackground(Color.BLACK);
                } else {
                    l.setForeground(Color.BLACK);
                }
                //l.setBackground(color);
                 l.setForeground(color);
                
//                for (int i = 0; i < feeList.size(); i++) {
//                    if(feeList.get(i).getFe_state().equals("Pagado")){
//                        l.setForeground(color);
//                    }
//                    if(feeList.get(i).getFe_state().equals("Pendiente")){
//                        l.setForeground(Color.RED);
//                    }
//                    
//                    
//                }
                
                

                return l;
            }
        };
        return render;
    }
}
