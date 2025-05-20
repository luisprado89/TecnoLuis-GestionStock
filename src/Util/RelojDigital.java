/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author LUIS
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.Timer;

public class RelojDigital {

    private javax.swing.JLabel lblReloj;
    private Timer timer;

    public RelojDigital(javax.swing.JLabel lblReloj) {
        this.lblReloj = lblReloj;
        iniciarReloj();
    }

    private void iniciarReloj() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date ahora = new Date();
                Locale locale = new Locale("es", "ES");

                // Formatos separados para hora y fecha con nombre del día
                String hora = new SimpleDateFormat("HH:mm:ss").format(ahora);
                String fecha = new SimpleDateFormat("EEEE d 'de' MMMM 'del' yyyy", locale).format(ahora);

                // Capitaliza la primera letra del día (porque en español es en minúscula)
                fecha = fecha.substring(0, 1).toUpperCase() + fecha.substring(1);

                lblReloj.setText(hora + "    " + fecha);
            }
        });
        timer.start();
    }

    public void detenerReloj() {
        if (timer != null) {
            timer.stop();
        }
    }
}
