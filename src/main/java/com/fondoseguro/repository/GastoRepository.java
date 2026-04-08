package com.fondoseguro.repository; // <--- Fíjate que coincida con tu carpeta real

import com.fondoseguro.model.Gasto;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository // Esta anotación le dice a Spring que esta clase maneja datos
public class GastoRepository {

    private final DynamoDbTable<Gasto> gastoTable;

    public GastoRepository(DynamoDbEnhancedClient enhancedClient) {
        // "Gastos" es el nombre que debe tener tu tabla en la consola de AWS
        this.gastoTable = enhancedClient.table("Gastos", TableSchema.fromBean(Gasto.class));
    }

    public void guardarGasto(Gasto gasto) {
        gastoTable.putItem(gasto);
    }

    public Gasto obtenerGasto(String id) {
        return gastoTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }
}
