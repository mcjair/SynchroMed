package com.synchromed.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    public MainFrame() {
        setTitle("SynchroMed - Panel de Gestión Clínica");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Superior: Información del Bien Común (Semana 1)
        JPanel header = new JPanel();
        header.setBackground(new Color(41, 128, 185));
        header.add(new JLabel("<html><font color='white'>SISTEMA SYNCHROMED | Estado del Servidor: Óptimo</font></html>"));
        add(header, BorderLayout.NORTH);

        // Panel Central: Módulos del Proyecto (Semana 4)
        JPanel menuPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        menuPanel.add(new JButton("Triaje Digital"));
        menuPanel.add(new JButton("Agenda Médica"));
        menuPanel.add(new JButton("Control de Inventario"));
        
        add(menuPanel, BorderLayout.CENTER);

        // Barra de Estado: Monitoreo (Semana 16)
        JLabel statusLabel = new JLabel(" Usuario: Admin | Horas Laborales: 4/8 (Saludable)");
        add(statusLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
