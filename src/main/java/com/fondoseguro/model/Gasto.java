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

    private String gastoId;    // Nuestra Partition Key
    private String usuarioId;  // Para saber de quién es el gasto
    private Double monto;
    private String categoria;
    private String descripcion;
    private String fecha;


    /*el id propio en dinamo te permite hacerlo 
    compuesto entonces se deja como una variable en java mas
    pero en dinamo solo tomamos el user id y el fecha id 
    */
    @DynamoDbPartitionKey 
    public String getUserId() {
        return userId; 
    }

    @DynamoDbSortKey
    public String getFecha(){ 
        return fecha; 
    }
}