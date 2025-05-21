/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * *
 * @author LUIS
 */
public class UtilImagenes2 {
     public void redimensionarImagen(JLabel label, String rutaImagen) {
          ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
          Image imagenOriginal = iconoOriginal.getImage();

          // Usar tamaño preferido explícito si el label aún no está renderizado
          int ancho = label.getPreferredSize().width;
          int alto = label.getPreferredSize().height;

          Image imagenRedimensionada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
          ImageIcon iconoEscalado = new ImageIcon(imagenRedimensionada);

          label.setIcon(iconoEscalado);
          label.repaint();
     }
}
