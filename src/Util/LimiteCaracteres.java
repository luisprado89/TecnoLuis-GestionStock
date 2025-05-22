package Util;

import javax.swing.text.*;
/**
 *
 * @author LUIS
 */
public class LimiteCaracteres extends DocumentFilter {
    private int limiteMax;
    private int limiteMin;

    public LimiteCaracteres(int limiteMax) {
        this.limiteMax = limiteMax;
        this.limiteMin = 0; // Por defecto, sin mínimo
    }

    public LimiteCaracteres(int limiteMin, int limiteMax) {
        this.limiteMin = limiteMin;
        this.limiteMax = limiteMax;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (fb.getDocument().getLength() + string.length() <= limiteMax) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        int currentLength = fb.getDocument().getLength();
        int newLength = currentLength - length + (text != null ? text.length() : 0);

        if (newLength <= limiteMax) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    // Método para validar si el texto cumple con el mínimo requerido
    public boolean cumpleMinimo(Document doc) {
        return doc.getLength() >= limiteMin;
    }
}
