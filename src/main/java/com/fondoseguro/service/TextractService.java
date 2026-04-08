package com.fondoseguro.service;

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

    public String extraerDatosDirecto(MultipartFile file) throws IOException {
        // Convertimos el PDF a bytes para enviarlo directamente
        SdkBytes sourceBytes = SdkBytes.fromInputStream(file.getInputStream());

        AnalyzeDocumentRequest request = AnalyzeDocumentRequest.builder()
                .document(Document.builder().bytes(sourceBytes).build())
                .featureTypes(FeatureType.TABLES, FeatureType.FORMS) // Buscamos tablas de gastos
                .build();

        AnalyzeDocumentResponse response = textractClient.analyzeDocument(request);

        // Aquí solo extraemos lo que nos sirve (ejemplo: montos)
        StringBuilder datosExtraidos = new StringBuilder();
        response.blocks().forEach(block -> {
            if (block.blockType() == BlockType.LINE) {
                datosExtraidos.append(block.text()).append(" ");
            }
        });

        return datosExtraidos.toString(); // Esto es lo que le pasarás a la IA
    }
}