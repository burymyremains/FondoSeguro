package com.fondoseguro.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@DynamoDbBean
public class Usuario {
    private String id;        // Este será el ID que nos dé Amazon Cognito
    private String nombre;
    private String email;
    private Double saldoTotal;
    private Integer scoreIA;  // Aquí guardaremos el resultado de la Lambda de Python
    private String ultimaActualizacion;

    // Constructor vacío obligatorio para DynamoDB
    public Usuario() {}

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
}