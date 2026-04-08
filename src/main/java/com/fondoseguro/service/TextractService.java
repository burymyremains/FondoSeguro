package com.fondoseguro.service;

import software.amazon.awssdk.core.SdkBytes;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.*;

import java.io.IOException;

@Service
public class TextractService {
    private final TextractClient textractClient;

    public TextractService(TextractClient textractClient) {
        this.textractClient = textractClient;
    }

    //Metodo para ya no usar las instancias S3 de aws y obtner el texto plano

    public String obtenerTextoDesdeBytes(MultipartFile file) {
        byte[] archivoBytes = file.getBytes();

        SdkBytes sdkBytes = SdkBytes.fromByteArray(archivoBytes);

        Document miDocumento = Document.builder()
                .bytes(sdkBytes)
                .build();

        DetectDocumentTextRequest request = DetectDocumentTextRequest.builder()
                .document(miDocumento)
                .build();

        DetectDocumentTextResponse response = textractClient.detectDocumentText(request);

        return response.blocks().stream()
                .filter(b -> b.blockType() == BlockType.LINE)
                .map(Block::text)
                .collect(Collectors.joining("\n"));
    }
}