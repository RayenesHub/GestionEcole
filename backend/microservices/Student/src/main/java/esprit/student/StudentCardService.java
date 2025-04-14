package esprit.student;

import com.google.zxing.WriterException;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class StudentCardService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    public byte[] generateCardForStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new IllegalArgumentException("Student not found with ID: " + id);
        }
        Student student = optionalStudent.get();

        try (ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(pdfOutput);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc, PageSize.A6);

            document.add(new Paragraph("Student Card").setBold().setFontSize(16));
            document.add(new Paragraph("Name: " + student.getFirstName() + " " + student.getLastName()));
            document.add(new Paragraph("Date of Birth: " + student.getDateOfBirth()));

            if (student.getClasse() != null) {
                document.add(new Paragraph("Classe: " + student.getClasse().getNiveau() + " - " + student.getClasse().getNom()));
            }

            // Lien vers un template HTML (par exemple une page de profil en ligne)
            String studentUrl = "https://www.instagram.com/rihem.benaissa/?hl=fr";
            System.out.println("Lien encodé dans le QR code : " + studentUrl);
            System.out.println("Longueur du lien : " + studentUrl.length());

            byte[] qrCode = qrCodeGenerator.generateQRCodeImage(studentUrl, 100, 100);
            com.itextpdf.io.image.ImageData imageData = ImageDataFactory.create(qrCode);
            Image qrImage = new Image(imageData);
            qrImage.setAutoScale(true);
            document.add(qrImage);

            document.close();
            return pdfOutput.toByteArray();

        } catch (IOException | WriterException e) {
            throw new RuntimeException("Failed to generate student card", e);
        }
    }
}
