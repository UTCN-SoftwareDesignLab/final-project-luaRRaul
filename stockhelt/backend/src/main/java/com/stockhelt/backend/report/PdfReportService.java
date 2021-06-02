package com.stockhelt.backend.report;

import com.stockhelt.backend.user.UserRepository;
import com.stockhelt.backend.user.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

import static com.stockhelt.backend.report.ReportType.PDF;

@Service
@RequiredArgsConstructor
public class PdfReportService implements ReportService {
    private final UserRepository userRepository;
    @Override
    public ByteArrayOutputStream export() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            PDDocument document = new PDDocument();
            PDPage pdPage = new PDPage();
            document.addPage(pdPage);
            List<User> database = userRepository.findAll();

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, pdPage);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 15);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 700);
            contentStream.showText(HEADER);
            contentStream.newLine();
            contentStream.newLine();

            for (User u :database){
                contentStream.showText(u.getUsername()+" "+u.getEmail()+" "+u.getClass().getSimpleName());
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();
            document.save(outputStream);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream;
    }


        @Override
    public ReportType getType() {
        return PDF;
    }
}
