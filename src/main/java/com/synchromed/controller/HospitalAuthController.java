package com.synchromed.controller;

import com.synchromed.dao.UsuarioDAO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/hospital")
public class HospitalAuthController {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        // En UsuarioDAO la contraseña correcta es "admin" cuyo hash SHA-256 hace match.
        if (usuarioDAO.autenticar(username, password)) {
            return ResponseEntity.ok("BIENVENIDO");
        } else {
            return ResponseEntity.badRequest().body("Credenciales inválidas. Acceso denegado a los registros hospitalarios.");
        }
    }
}
