package sae5.mailing;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class Pdf {

    public byte[] generateMailPdf(Mail mail) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            // 1. Générer en mémoire (pour retour HTTP)
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // ===== Affichage amélioré =====
            Paragraph title = new Paragraph("📧 Détail du Mail")
                    .setBold()
                    .setFontSize(20)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);
            document.add(title);

            document.add(new Paragraph("📌 Sujet:")
                    .setBold().setFontSize(12));
            document.add(new Paragraph(mail.getSubject()).setMarginBottom(10));

            document.add(new Paragraph("👤 Destinataire:")
                    .setBold().setFontSize(12));
            document.add(new Paragraph(mail.getSender()).setMarginBottom(10));

            document.add(new Paragraph("🕒 Date d’envoi:")
                    .setBold().setFontSize(12));
            document.add(new Paragraph(mail.getDate().toString()).setMarginBottom(10));

            document.add(new Paragraph("✉️ Contenu:")
                    .setBold().setFontSize(12));
            document.add(new Paragraph(mail.getBody()).setMarginBottom(20));

            document.close();


            String filePath = "C:/Users/LENOVO/Downloads/mail_" + mail.getId() + ".pdf";
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(baos.toByteArray());
            }

        } catch (IOException e) {
            e.printStackTrace(); // tu peux aussi utiliser un logger si tu veux
        }

        return baos.toByteArray();
    }
}
