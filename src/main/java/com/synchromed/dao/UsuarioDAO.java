package com.synchromed.dao;

import com.synchromed.model.Usuario;
import org.apache.commons.codec.digest.DigestUtils; // Recurso Java (Semana 10)

public class UsuarioDAO {
    
    public boolean autenticar(String username, String password) {
        // En una implementación real, aquí se consulta a la DB (Semana 6)
        // Aplicamos un Hash SHA-256 para cumplir con estándares de seguridad (Semana 9)
        String passwordHashed = DigestUtils.sha256Hex(password);
        
        // Simulación de validación segura
        return "admin".equals(username) && "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918".equals(passwordHashed);
    }
}
