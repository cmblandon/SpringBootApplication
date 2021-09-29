package org.springboot.app.controllers;

import org.springboot.app.models.Cuenta;
import org.springboot.app.models.TransaccionDTO;
import org.springboot.app.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Cuenta detalle(@PathVariable(name = "id") Long id) {
        return cuentaService.findById(id);
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferir(@RequestBody TransaccionDTO dto) {
        cuentaService.transferir(
                dto.getCuentaOrigenId(),
                dto.getGetCuentaDestinoId(),
                dto.getMonto(),
                dto.getBancoId());
        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDate.now().toString());
        response.put("status", "ok");
        response.put("mensaje", "Transferencia realizada con éxito");
        response.put("transaccion", dto);
        return ResponseEntity.ok(response);
    }
}
