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

    //Metodo para obtner el texto plano

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

    public void guardarTextoEnS3(String textoPlano, String nombreOriginal) {
        String nombreTxt = nombreOriginal.replaceAll("(?i)\\.pdf$", "") + ".txt";
        
        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket("fondoseguro-user-txt-info")
                .key("entradas-ia/" + nombreTxt)
                .contentType("text/plain")
                .build();

        s3Client.putObject(putRequest, RequestBody.fromString(textoPlano));
    }

    // EL METODO QUE LLAMA EL CONTROLLER de paro solo este lol para subir un pdf
    public void flujoCompleto(MultipartFile file) throws IOException {
        String texto = obtenerTextoDesdeBytes(file);        
        guardarTextoEnS3(texto, file.getOriginalFilename());
    }
}