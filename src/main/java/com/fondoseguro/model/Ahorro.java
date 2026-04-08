package com.fondoseguro.model; 

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

public class Ahorro {

    private String ahorroId;
    private String userId; 
    private Double monto;
    private String descripcion;
    private String fecha;


    @DynamoDbPartitionKey
    public String getUserId() {
         return userId; 
    }

    @DynamoDbSortKey 
    public String getFecha(){ 
        return fecha; 
    }
}
