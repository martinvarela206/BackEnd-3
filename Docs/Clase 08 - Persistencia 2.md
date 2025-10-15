# Persistencia 2

## Instalacion de Workbench (No hay compatibilidad con Debian o Peppermint)

```sh
wget https://dev.mysql.com/get/mysql-apt-config_0.8.29-1_all.deb
sudo dpkg -i mysql-apt-config_0.8.29-1_all.deb
sudo apt update
## sudo apt install mysql-workbench-community NO FUNCIONA
## sudo dpkg -i mysql-workbench-community_8.0.43-1ubuntu22.04_amd64.deb
## sudo apt -f install
sudo snap install mysql-workbench-community
```

Si no esta instalado snap:

```sh
sudo apt update
sudo apt install snapd
sudo snap install core
```

Iniciar con: `snap run mysql-workbench-community`

Desinstalar:

```sh
sudo snap remove mysql-workbench-community
sudo rm /etc/apt/sources.list.d/mysql.list
sudo apt update
```

## Instalar MariaDB y DBeaver

Es para trabajar visualmente con MySQL:

```sh
cd ~/Descargas
sudo apt install mariadb-server
sudo dpkg -i dbeaver-ce_25.2.2_amd64.deb
sudo apt -f install
```

Luego configurar mariadb:

```sh
ALTER USER 'root'@'localhost' IDENTIFIED BY 'admin1234';
FLUSH PRIVILEGES;
EXIT;
```

## Script para crear la DDBB Universidad

```sql
-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS universidad;
USE universidad;

-- Tabla: facultad
CREATE TABLE facultad (
    idfacultad INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    PRIMARY KEY (idfacultad)
) ENGINE=InnoDB;

-- Tabla: carrera
CREATE TABLE carrera (
    idcarrera INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    facultad_idfacultad INT NOT NULL,
    PRIMARY KEY (idcarrera),
    INDEX fk_carrera_facultad_idx (facultad_idfacultad ASC),
    CONSTRAINT fk_carrera_facultad
        FOREIGN KEY (facultad_idfacultad)
        REFERENCES facultad (idfacultad)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- Tabla: alumno
CREATE TABLE alumno (
    idalumno INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    registro VARCHAR(6) NOT NULL,
    carrera_idcarrera INT NOT NULL,
    PRIMARY KEY (idalumno),
    INDEX fk_alumno_carrera1_idx (carrera_idcarrera ASC),
    CONSTRAINT fk_alumno_carrera1
        FOREIGN KEY (carrera_idcarrera)
        REFERENCES carrera (idcarrera)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- Tabla: materia
CREATE TABLE materia (
    idmateria INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(10) NOT NULL,
    PRIMARY KEY (idmateria)
) ENGINE=InnoDB;

-- Tabla: examen
CREATE TABLE examen (
    materia_idmateria INT NOT NULL,
    alumno_idalumno INT NOT NULL,
    fecha TIMESTAMP(6) NOT NULL,
    nota INT,
    PRIMARY KEY (materia_idmateria, alumno_idalumno),
    INDEX fk_materia_has_alumno_alumno1_idx (alumno_idalumno ASC),
    INDEX fk_materia_has_alumno_materia1_idx (materia_idmateria ASC),
    CONSTRAINT fk_materia_has_alumno_materia1
        FOREIGN KEY (materia_idmateria)
        REFERENCES materia (idmateria)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_materia_has_alumno_alumno1
        FOREIGN KEY (alumno_idalumno)
        REFERENCES alumno (idalumno)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB;

```