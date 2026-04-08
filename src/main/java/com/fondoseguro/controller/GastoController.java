package com.fondoseguro.controller;

import com.fondoseguro.model.Gasto;
import com.fondoseguro.service.GastoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gastos")
@CrossOrigin(origins = "*") // Permite que el Frontend se conecte sin bloqueos de CORS
public class GastoController {

    private final GastoService gastoService;

    public GastoController(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    @PostMapping
    public ResponseEntity<Gasto> crearGasto(@RequestBody Gasto gasto) {
        Gasto nuevoGasto = gastoService.registrarGasto(gasto);
        return ResponseEntity.ok(nuevoGasto);
    }
}