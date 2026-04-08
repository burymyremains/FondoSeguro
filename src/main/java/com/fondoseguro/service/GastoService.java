package com.fondoseguro.service;

import com.fondoseguro.model.Gasto;
import com.fondoseguro.repository.GastoRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class GastoService {

    private final GastoRepository gastoRepository;

    public GastoService(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }

    public Gasto registrarGasto(Gasto gasto) {
        // Generamos un ID único si no viene uno
        if (gasto.getIdGasto() == null) {
            gasto.setIdGasto(UUID.randomUUID().toString());
        }

        // Aquí podrías añadir lógica: "Si el gasto > 1000, lanzar alerta"
        gastoRepository.guardarGasto(gasto);
        return gasto;
    }
}