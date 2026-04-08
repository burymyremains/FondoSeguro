package com.fondoseguro.model; // <--- Fíjate que esto coincida con tu carpeta

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data // Genera getters, setters, toString, etc. (Lombok)
@Builder // Útil para crear objetos de prueba rápidamente
@NoArgsConstructor // Requerido por DynamoDB
@AllArgsConstructor
@DynamoDbBean // Indica que esta clase es una tabla de DynamoDB
public class Gasto {

    private String idGasto;    // Nuestra Partition Key
    private String usuarioId;  // Para saber de quién es el gasto
    private Double monto;
    private String categoria;
    private String descripcion;
    private String fecha;

    @DynamoDbPartitionKey // Marca este campo como la llave primaria en AWS
    public String getIdGasto() {
        return idGasto;
    }
}