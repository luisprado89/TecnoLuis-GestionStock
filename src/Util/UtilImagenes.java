package Util;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
/**
 *
 * @author LUIS
 */
/**
 * Clase de utilidad para redimensionar imágenes con calidad mejorada.
 */

public class UtilImagenes {
    /**
     * Redimensiona una imagen desde recurso (classpath) al ancho y alto dados.
     */
    public static ImageIcon redimensionarDesdeRecurso(String ruta, int ancho, int alto) {
        try {
            InputStream input = UtilImagenes.class.getResourceAsStream(ruta);
            if (input == null) {
                System.err.println("No se encontró la imagen: " + ruta);
                return null;
            }

            BufferedImage original = ImageIO.read(input);
            BufferedImage escalada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = escalada.createGraphics();

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(original, 0, 0, ancho, alto, null);
            g2d.dispose();

            return new ImageIcon(escalada);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Redimensiona una imagen desde recurso según el tamaño del JLabel.
     */
    public static ImageIcon redimensionarDesdeRecurso(String ruta, JLabel label) {
        int ancho = label.getWidth();
        int alto = label.getHeight();

        if (ancho <= 0 || alto <= 0) {
            System.err.println("El JLabel aún no tiene tamaño válido.");
            return null;
        }

        return redimensionarDesdeRecurso(ruta, ancho, alto);
    }

    /**
     * Redimensiona un ImageIcon ya existente al tamaño del JLabel (no requiere ruta).
     */
    public static ImageIcon redimensionarDesdeIcono(ImageIcon icono, int ancho, int alto) {
        if (icono == null || icono.getImage() == null) {
            System.err.println("El icono original es nulo.");
            return null;
        }

        Image original = icono.getImage();
        BufferedImage escalada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = escalada.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(original, 0, 0, ancho, alto, null);
        g2d.dispose();

        return new ImageIcon(escalada);
    }

}