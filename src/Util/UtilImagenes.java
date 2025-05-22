package Util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
/**
 *
 * @author LUIS
 */

/**
 * Clase de utilidad para redimensionar y escalar imágenes desde recursos.
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
            BufferedImage escalada = escalarImagen(original, ancho, alto);
            return new ImageIcon(escalada);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Redimensiona una imagen desde recurso al tamaño de un JLabel.
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
     * Redimensiona un ImageIcon existente a un tamaño específico.
     */
    public static ImageIcon redimensionarDesdeIcono(ImageIcon icono, int ancho, int alto) {
        if (icono == null || icono.getImage() == null) {
            System.err.println("El icono original es nulo.");
            return null;
        }

        BufferedImage escalada = escalarImagen(toBufferedImage(icono.getImage()), ancho, alto);
        return new ImageIcon(escalada);
    }

    /**
     * Establece una imagen redimensionada en un JLabel usando su tamaño preferido.
     */
    public static void establecerImagenEnLabel(JLabel label, String rutaImagen) {
        ImageIcon iconoOriginal = new ImageIcon(UtilImagenes.class.getResource(rutaImagen));
        Image imagenOriginal = iconoOriginal.getImage();
        int ancho = label.getPreferredSize().width;
        int alto = label.getPreferredSize().height;

        Image imagenRedimensionada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imagenRedimensionada));
        label.repaint();
    }

    /**
     * Escala una imagen desde recurso teniendo en cuenta el escalado del sistema operativo.
     */
    public static ImageIcon escalarImagenAuto(String ruta, int anchoLogico, int altoLogico) {
        try {
            URL recurso = UtilImagenes.class.getResource(ruta);
            if (recurso == null) {
                throw new IllegalArgumentException("No se encontró la imagen: " + ruta);
            }

            ImageIcon icon = new ImageIcon(recurso);
            Image original = icon.getImage();

            double escalaSistema = obtenerEscalaSistema();
            int anchoEscalado = (int) Math.round(anchoLogico * escalaSistema);
            int altoEscalado = (int) Math.round(altoLogico * escalaSistema);

            BufferedImage buffered = escalarImagen(toBufferedImage(original), anchoEscalado, altoEscalado);
            return new ImageIcon(buffered);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al escalar imagen: " + ruta);
        }
    }

    /**
     * Escala una imagen manteniendo calidad.
     */
    private static BufferedImage escalarImagen(Image original, int ancho, int alto) {
        BufferedImage escalada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = escalada.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(original, 0, 0, ancho, alto, null);
        g2d.dispose();

        return escalada;
    }

    /**
     * Convierte una Image a BufferedImage.
     */
    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) return (BufferedImage) img;

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    /**
     * Devuelve la escala del sistema operativo (ej. 1.0, 1.25, 1.5).
     */
    private static double obtenerEscalaSistema() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        AffineTransform at = gc.getDefaultTransform();
        return at.getScaleX();
    }

    /**
     * Devuelve el porcentaje de escalado del sistema (100%, 125%, etc.).
     */
    public static int obtenerEscaladoPorcentaje() {
        return (int) Math.round(obtenerEscalaSistema() * 100);
    }
}
