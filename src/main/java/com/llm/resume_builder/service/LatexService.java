package com.llm.resume_builder.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class LatexService {

    public byte [] convertPdfToLatex(MultipartFile file){
        try {
            PDDocument document = PDDocument.load(file.getInputStream());
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            document.close();

            // Wrap extracted text in a basic LaTeX document structure
            String latexContent = "\\documentclass{article}\n\\begin{document}\n"
                    + text.replace("\n", "\n\n")
                    + "\n\\end{document}";

            // Convert LaTeX string to byte array

            return latexContent.getBytes();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error processing PDF: " + e.getMessage());
             return null;
        }
    }
}
