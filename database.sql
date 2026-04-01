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

-- Tabla de Triaje: Clasificación de urgencia para evitar el "efecto embudo"
CREATE TABLE Triaje (
    id_triaje INT PRIMARY KEY AUTO_INCREMENT,
    id_paciente INT,
    frecuencia_cardiaca INT,
    temperatura DECIMAL(4,2),
    sintomas_principales TEXT,
    nivel_prioridad ENUM('Rojo', 'Naranja', 'Amarillo', 'Verde', 'Azul') NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_paciente) REFERENCES Pacientes(id_paciente)
) ENGINE=InnoDB;

-- Auditoría: Consideración de seguridad (Semana 6)
CREATE TABLE LogsAcceso (
    id_log INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT,
    accion VARCHAR(100),
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP
);
