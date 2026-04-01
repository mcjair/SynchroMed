package com.synchromed.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOTest {

    @Test
    public void testAutenticacionFallida() {
        UsuarioDAO dao = new UsuarioDAO();
        // Intento con contraseña incorrecta
        boolean acceso = dao.autenticar("admin", "password_erroneo");
        
        assertFalse(acceso, "El sistema no debe permitir el acceso con credenciales inválidas.");
    }
}
