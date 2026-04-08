package com.fondoseguro.model; // <--- Fíjate que esto coincida con tu carpeta

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data //getters y setters de todo lol
@Builder 
@NoArgsConstructor 
@AllArgsConstructor
@DynamoDbBean 
public class Ingreso {

    private String ingresoId;    
    private String usuarioId; 
    private Double monto;
    private String categoria;
    private String descripcion;
    private String fecha;

    @DynamoDbPartitionKey //  fpara AWS
    public String getingresoId() {
        return ingresoId;
    }
}