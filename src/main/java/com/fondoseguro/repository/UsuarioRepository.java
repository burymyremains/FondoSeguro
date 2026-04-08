package com.fondoseguro.repository;

import com.fondoseguro.model.Usuario;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class UsuarioRepository {

    private final DynamoDbTable<Usuario> usuarioTable;

    public UsuarioRepository(DynamoDbEnhancedClient enhancedClient) {
        // La tabla debe llamarse "Usuarios" en la consola de AWS
        this.usuarioTable = enhancedClient.table("Usuarios", TableSchema.fromBean(Usuario.class));
    }

    public void crearOActualizar(Usuario usuario) {
        usuarioTable.putItem(usuario);
    }

    public Usuario obtenerPorId(String id) {
        return usuarioTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public void eliminar(String id) {
        usuarioTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}