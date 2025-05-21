package Util;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;

public class UtilImagenesM {
    /**
     * Escala una imagen desde recurso aplicando corrección según el escalado del sistema.
     *
     * @param ruta     Ruta al recurso de la imagen (ej: "/img/icono.png")
     * @param anchoLogico Ancho lógico deseado (ej. 45)
     * @param altoLogico  Alto lógico deseado (ej. 45)
     * @return Icono escalado visualmente correcto
     */
    public static ImageIcon escalarImagenAuto(String ruta, int anchoLogico, int altoLogico) {
        try {
            URL recurso = UtilImagenesM.class.getResource(ruta);
            if (recurso == null) {
                throw new IllegalArgumentException("No se encontró la imagen: " + ruta);
            }

            ImageIcon icon = new ImageIcon(recurso);
            Image original = icon.getImage();

            double escalaSistema = obtenerEscalaSistema();
            int anchoEscalado = (int) Math.round(anchoLogico * escalaSistema);
            int altoEscalado = (int) Math.round(altoLogico * escalaSistema);

            // Evita getScaledInstance, renderiza directamente sobre el BufferedImage
            BufferedImage buffered = new BufferedImage(anchoLogico, altoLogico, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = buffered.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            g2d.drawImage(original, 0, 0, anchoLogico, altoLogico, null);
            g2d.dispose();

            return new ImageIcon(buffered);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al escalar imagen: " + ruta);
        }
    }


    /**
     * Detecta la escala real del sistema operativo (ej. 1.0, 1.25, 1.5)
     */
    private static double obtenerEscalaSistema() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        AffineTransform at = gc.getDefaultTransform();
        return at.getScaleX(); // suele ser igual que getScaleY()
    }

    /**
     * Devuelve el porcentaje de escalado (100%, 125%, 150%...)
     */
    public static int obtenerEscaladoPorcentaje() {
        return (int) Math.round(obtenerEscalaSistema() * 100);
    }

}
