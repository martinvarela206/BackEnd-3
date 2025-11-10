# Informe Final

## Caratula

Martín Miguel Varela Ochoa

Inventario LIA

Backend 3

2025

## Introducción

## Descripción General del Proyecto

El proyecto "Inventario LIA" es una aplicación web, desarrollada para gestionar el inventario del LIA (Laboratorio de Informática Aplicada). Su objetivo es facilitar el control y seguimiento de los recursos disponibles y sus ubicaciones dentro del LIA. Además sienta las bases para un futuro sistema de tickets que incluya una gestión automática del inventario cuando corresponda.

El sistema incluye funcionalidades clave como:

- Registro, Autenticación y Autorización de Usuarios.
- Registro y gestión de elementos del inventario.
- Generación de reportes detallados.
- Integración con bases de datos para almacenamiento seguro y confiable.
- Interfaz web intuitiva para facilitar el acceso y uso por parte de los usuarios.

## Contexto del Problema

El LIA necesita una solución mas eficiente que el uso de un archivo de Excel para gestionar su inventario, ya que actualmente no es posible llevar un control actualizado ni con un historial de cambios adecuado.

El proyecto "Inventario LIA" surge como una intención de dar una solución integral a estos problemas, proporcionando una herramienta tecnológica que automatiza y optimiza los procesos de inventario, mejorando la eficiencia operativa y reduciendo los errores humanos.

## Descripción Técnica

El sistema va a contar con una bbdd donde se tienen las tablas elementos, movimientos, usuarios, roles y usuarios_roles.

Existen 4 roles: user_admin (gestiona usuarios y roles), coordinador (puede modificar y borrar movimientos y elementos en el inventario), técnico (crear movimientos y añadir elementos) y revisores (pueden listar elementos).

Un elemento puede tener muchos movimientos, ya que cada movimiento representa una acción realizada sobre el elemento (alta, baja, préstamo, reclamo de garantía, etc). Los movimientos también registran quien los hizo y cuando.

Las tecnologías a utilizar son:

- **Frontend:** Svelte
- **Backend:** Java, JPA, API Criteria, JAX-RS, GlassFish, Maven
- **Base de Datos:** MariaDB
- **Autenticación:** JWT (JSON Web Tokens)

## Desarrollo

### a) Requisitos Funcionales

El sistema "Inventario LIA" implementa las siguientes funcionalidades:

1. **Gestión de Usuarios y Roles:**
   - Registro de nuevos usuarios.
   - Autenticación y autorización basada en roles.
   - Gestión de roles: user_admin, coordinador, técnico y revisores.

2. **Gestión de Elementos del Inventario:**
   - Registro de nuevos elementos.
   - Modificación y eliminación de elementos existentes.
   - Listado de elementos con filtros y búsquedas.

3. **Gestión de Movimientos:**
   - Registro de movimientos asociados a elementos (alta, baja, préstamo, reclamo de garantía, etc.).
   - Registro de la fecha y el usuario que realiza cada movimiento.

4. **Reportes:**
   - Generación de reportes detallados sobre el estado del inventario y los movimientos realizados.

5. **Interfaz Web Intuitiva:**
   - Navegación sencilla y diseño amigable para facilitar el uso por parte de los usuarios.

### b) Modo de Uso

El sistema "Inventario LIA" está diseñado para ser utilizado a través de un navegador web. A continuación, se describe una guía básica para el usuario:

1. **Inicio de Sesión:**
   - Acceder a la página principal e ingresar las credenciales de usuario.
   - Según el rol asignado, se habilitarán diferentes opciones en el menú.

2. **Gestión de Elementos:**
   - Navegar a la sección "Elementos" para registrar, modificar o listar elementos del inventario.

3. **Gestión de Movimientos:**
   - Acceder a la sección "Movimientos" para registrar acciones realizadas sobre los elementos.

4. **Reportes:**
   - Generar reportes desde la sección correspondiente, seleccionando los filtros deseados.

5. **Gestión de Usuarios y Roles:**
   - Disponible solo para el rol "user_admin". Permite registrar nuevos usuarios y asignar roles.

### c) Representación Visual

A continuación, se incluyen capturas de pantalla y esquemas que ilustran la interfaz y la navegación del sistema:


## Especificaciones Técnicas

### a) Diagrama Entidad–Relación

El sistema "Inventario LIA" utiliza un modelo entidad-relación que incluye las siguientes entidades principales:

1. **Usuarios:**
   - Atributos: id, nombre, email, contraseña.
   - Relación: Un usuario puede tener uno o más roles.

2. **Roles:**
   - Atributos: id, nombre.
   - Relación: Un rol puede estar asignado a varios usuarios.

3. **Elementos:**
   - Atributos: id, nombre, descripción, estado.
   - Relación: Un elemento puede tener muchos movimientos.

4. **Movimientos:**
   - Atributos: id, tipo, fecha, usuario_id, elemento_id.
   - Relación: Cada movimiento está asociado a un elemento y a un usuario.

### b) Modelo Orientado a Objetos

El sistema utiliza un modelo orientado a objetos basado en las siguientes clases principales:

1. **Usuario:**
   - Representa a los usuarios del sistema.
   - Métodos principales: autenticación, asignación de roles.

2. **Rol:**
   - Define los permisos y responsabilidades de los usuarios.
   - Métodos principales: gestión de permisos.

3. **Elemento:**
   - Representa los objetos físicos en el inventario.
   - Métodos principales: registro, actualización de estado.

4. **Movimiento:**
   - Registra las acciones realizadas sobre los elementos.
   - Métodos principales: creación de movimientos, consulta de historial.

Estas clases están mapeadas a las tablas de la base de datos utilizando JPA (Java Persistence API).

### c) Descripción Tecnológica

El sistema "Inventario LIA" adopta un enfoque basado en tecnologías modernas y estándares de la industria para garantizar escalabilidad, seguridad y facilidad de mantenimiento. A continuación, se describen las tecnologías utilizadas:

1. **Frontend:**
   - **Svelte:** Framework para construir interfaces de usuario reactivas y eficientes.

2. **Backend:**
   - **Java:** Lenguaje de programación principal.
   - **JPA (Java Persistence API):** Para el mapeo objeto-relacional.
   - **API Criteria:** Para consultas dinámicas a la base de datos.
   - **JAX-RS:** Para la implementación de servicios RESTful.
   - **GlassFish:** Servidor de aplicaciones.
   - **Maven:** Herramienta de gestión de dependencias y construcción del proyecto.

3. **Base de Datos:**
   - **MariaDB:** Sistema de gestión de bases de datos relacional.

4. **Autenticación:**
   - **JWT (JSON Web Tokens):** Para la autenticación segura y sin estado.

Este conjunto de tecnologías permite desarrollar un sistema robusto, seguro y fácil de escalar.

## Conclusiones

El desarrollo del proyecto "Inventario LIA" ha sido una experiencia enriquecedora y desafiante. A pesar de haber trabajado previamente con arquitecturas como Laravel y .NET, trabajar con Java representó un reto significativo debido a su curva de aprendizaje más elevada. La cantidad de herramientas y tecnologías involucradas, como GlassFish, JPA, Maven, API Criteria, paquetes y dependencias, hizo que el proceso fuera complejo. Sin el apoyo de herramientas como la inteligencia artificial, habría sido difícil alcanzar los objetivos planteados.

Uno de los aprendizajes más destacados fue la implementación de autenticación y autorización utilizando JWT. Este fue un tema completamente nuevo para mí, y el hecho de tener que desarrollarlo desde cero con una API propia añadió un nivel adicional de dificultad. Sin embargo, este desafío me permitió adquirir conocimientos valiosos y mejorar mis habilidades como desarrollador.

En conclusión, este proyecto no solo me permitió desarrollar una solución tecnológica útil para la gestión de inventarios, sino que también me ayudó a crecer profesionalmente al enfrentar y superar obstáculos técnicos significativos.
