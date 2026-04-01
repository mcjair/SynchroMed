-- Creación de la Base de Datos SynchroMed
CREATE DATABASE SynchroMedDB;
USE SynchroMedDB;

-- Tabla de Personal de Salud (Prevención de Burnout)
CREATE TABLE PersonalSalud (
    id_personal INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(50),
    horas_acumuladas_dia INT DEFAULT 0, -- Control de carga laboral
    estado ENUM('Disponible', 'En Descanso', 'Sobrecargado') DEFAULT 'Disponible'
);

-- Tabla de Inventario (Sincronización de Recursos)
CREATE TABLE InventarioInsumos (
    id_item INT PRIMARY KEY AUTO_INCREMENT,
    nombre_insumo VARCHAR(100),
    stock_actual INT NOT NULL,
    stock_minimo_alerta INT NOT NULL
);

-- Tabla de Pacientes (Gestión de Triaje)
CREATE TABLE Pacientes (
    id_paciente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    dni VARCHAR(15) UNIQUE NOT NULL,
    nivel_prioridad INT, -- 1: Crítico, 2: Urgente, 3: Estable
    ultimo_triaje_fecha DATETIME
);

-- Tabla de Citas (El Nexo)
CREATE TABLE Citas (
    id_cita INT PRIMARY KEY AUTO_INCREMENT,
    id_paciente INT,
    id_personal INT,
    fecha_cita DATETIME,
    estado_cita VARCHAR(20),
    FOREIGN KEY (id_paciente) REFERENCES Pacientes(id_paciente),
    FOREIGN KEY (id_personal) REFERENCES PersonalSalud(id_personal)
);
