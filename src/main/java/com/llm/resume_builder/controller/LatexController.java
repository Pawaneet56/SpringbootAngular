package com.llm.resume_builder.controller;

import com.llm.resume_builder.service.LatexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class LatexController {
    @Autowired
    private LatexService latexService;

    @PostMapping(value="/upload-pdf",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> uploadPdf(@RequestPart("file")MultipartFile file){
        byte[] latexBytes=latexService.convertPdfToLatex(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"converted.tex\"")
                .contentType(MediaType.TEXT_PLAIN)
                .body(latexBytes);
    }
}
