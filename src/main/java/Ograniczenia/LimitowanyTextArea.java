package Ograniczenia;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class LimitowanyTextArea extends JTextArea {
    public LimitowanyTextArea(int maxLiczbaZnakow)
    {
        super();

        // Pobierz dokument powiązany z JTextArea
        AbstractDocument doc = (AbstractDocument) this.getDocument();

        // Ustaw filtr dokumentu, aby ograniczyć liczbę znaków
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            @SuppressWarnings("unused")
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength());
                // string += text;
                if ((fb.getDocument().getLength() + text.length()) <= maxLiczbaZnakow) {
                    super.replace(fb, offset, length, text, attrs); // Zezwól na dodanie tekstu
                }
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= maxLiczbaZnakow)
                    super.insertString(fb, offset, string, attr);

            }
        });
    }

}

