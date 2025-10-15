# Tutorial de Persistencia en Java (NetBeans: Ant y Maven)

## Objetivo
Este tutorial paso a paso explica cómo crear una aplicación Java con persistencia usando JPA en NetBeans. Incluye: conceptos clave, instrucciones para proyectos con Ant y con Maven, ejemplos de `persistence.xml`, entidades, un servlet manejador, y cómo conectar a MySQL o añadir tablas y relaciones.

## Contrato (qué entregamos)
- Inputs: NetBeans (Ant o Maven), JDK 11+, GlassFish (opcional) o servidor de tu preferencia, base de datos (Derby embebida o MySQL).
- Outputs: Proyecto web con persistencia JPA funcional y ejemplo de CRUD simple (crear/listar) y JSP para ver resultados.
- Errores: problemas típicos de classpath, datasource mal configurado, nombre de unidad de persistencia incorrecto.

## Requisitos previos
- JDK 11 instalado.
- NetBeans (recomendado NetBeans 12+ o 25 según apuntes).
- GlassFish (incluye Derby) o tener una instalación de MySQL y su conector JDBC.
- Conocimientos básicos de Servlets/JSP y SQL.

## Conceptos fundamentales
- JDBC: API básica para conectarse a bases de datos desde Java mediante drivers. JPA abstrae el acceso y permite trabajar con objetos.
- JPA (Jakarta Persistence API): especificación para mapear objetos Java a tablas relacionales. Proveedores comunes: EclipseLink (predeterminado en GlassFish), Hibernate.
- Entidad: clase Java anotada con `@Entity` que representa una tabla.
- Unidad de persistencia (persistence unit): definida en `persistence.xml`, indica el proveedor, la datasource o propiedades JDBC, y el nombre que usarás en código.
- EntityManager / EntityManagerFactory: API principal para operar sobre entidades (persistir, buscar, eliminar).
- Transaction types:
  - JTA: gestionado por el contenedor (ej. GlassFish). Usualmente en aplicaciones web con servidor.
  - RESOURCE_LOCAL: gestionado por la aplicación (útil en ejecución por `main` o pruebas con Maven/Tomcat sin gestor JTA).
- Anotaciones JPA importantes:
  - `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`.
  - Relaciones: `@OneToMany`, `@ManyToOne`, `@ManyToMany`, `@OneToOne`, `@JoinColumn`, `@JoinTable`.
  - Fetch: `FetchType.LAZY` / `EAGER`.
  - Cascade: `CascadeType.PERSIST`, `MERGE`, etc.

## Estructura general del ejemplo
- Entidad: `Alumno` (id, nombre, apellido, email)
- Servlet manejador: `Manejador` que procesa peticiones para crear y listar alumnos.
- JSP: `listar.jsp` para mostrar la lista.

---

## Parte A — Guía NetBeans (Ant)
Usa este flujo si trabajas con proyectos históricos basados en Ant.

### 1) Preparar el servidor y la base de datos
Recomendado: GlassFish (incluye Derby). Alternativa: configurar MySQL y conector.

En NetBeans:
- Tools > Servers > Add Server > GlassFish Server: selecciona la carpeta de GlassFish instalada.
- Services > Databases: crear nueva conexión Java DB (Embedded) o añadir conexión a MySQL.

Crear BD en Java DB (Derby embebido):
- Services > Databases > Java DB > New Database: nombre `DBAlumno`, usuario `app`, pass `app`.
- Luego, conéctate y crea la tabla `ALUMNO` con el SQL:

```sql
CREATE TABLE ALUMNO (
  ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  NOMBRE VARCHAR(100),
  APELLIDO VARCHAR(100),
  EMAIL VARCHAR(150)
);
```

Si usas MySQL, ejecuta en MySQL:

```sql
CREATE DATABASE dbalumno CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE dbalumno;
CREATE TABLE alumno (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  apellido VARCHAR(100),
  email VARCHAR(150)
);
```

### 2) Crear proyecto web con Ant
- File > New Project > Java Web > Web Application > Next.
- Selecciona GlassFish como servidor y Java EE 8+ o Jakarta EE 9/10 según NetBeans.
- Elige el nombre del proyecto, p. ej. `GestionAlumnoAnt`.

### 3) Generar Entidad desde BD
- Project > New > Other > Persistence > Entity Classes from Database.
- Selecciona el Data Source que creaste (DBAlumno) > Next.
- Selecciona la tabla `ALUMNO`, marca generar atributos y Finalizar.
- NetBeans genera `Alumno.java` con anotaciones JPA.

### 4) Configurar `persistence.xml`
En el proyecto Ant, NetBeans usualmente crea `persistence.xml` en `Configuration Files`.
Asegúrate que la versión sea 3.0 (Jakarta) o 2.2 (Javax) según dependencias. Ejemplo para JTA (cuando usas GlassFish y datasource):

```xml
<persistence version="3.0"
  xmlns="https://jakarta.ee/xml/ns/persistence"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd">
  <persistence-unit name="AlumnosPU" transaction-type="JTA">
    <jta-data-source>java:app/DBAlumno</jta-data-source>
    <exclude-unlisted-classes>false</exclude-unlisted-classes>
  </persistence-unit>
</persistence>
```

Si prefieres usar conexión directa (RESOURCE_LOCAL) para Derby o MySQL, usa `transaction-type="RESOURCE_LOCAL"` y propiedades JDBC:

```xml
<persistence-unit name="AlumnosPU" transaction-type="RESOURCE_LOCAL">
  <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
  <class>com.example.model.Alumno</class>
  <properties>
    <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/dbalumno"/>
    <property name="jakarta.persistence.jdbc.user" value="tuusuario"/>
    <property name="jakarta.persistence.jdbc.password" value="tuclave"/>
    <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
  </properties>
</persistence-unit>
```

Nota: para Ant + GlassFish es cómodo usar JTA y configurar un JDBC resource en GlassFish con el nombre `java:app/DBAlumno`.

### 5) Crear servlet manejador (Manejador)
Ejemplo mínimo (esquema):

```java
// paquete com.example.servlet
@WebServlet("/manejador")
public class Manejador extends HttpServlet {
  @PersistenceContext(unitName = "AlumnosPU")
  private EntityManager em;

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String nombre = req.getParameter("nombre");
    String apellido = req.getParameter("apellido");
    String email = req.getParameter("email");
    Alumno a = new Alumno();
    a.setNombre(nombre);
    a.setApellido(apellido);
    a.setEmail(email);
    em.persist(a); // JTA: container maneja la transacción
    resp.sendRedirect("listar.jsp");
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Alumno> lista = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
    req.setAttribute("alumnos", lista);
    req.getRequestDispatcher("listar.jsp").forward(req, resp);
  }
}
```

### 6) JSP `listar.jsp`
Muestra la lista de alumnos:

```jsp
<%@ page import="java.util.List, com.example.model.Alumno" %>
<% List<Alumno> alumnos = (List<Alumno>) request.getAttribute("alumnos"); %>
<html>
<body>
<h2>Alumnos</h2>
<table>
<tr><th>ID</th><th>Nombre</th><th>Apellido</th><th>Email</th></tr>
<% for (Alumno a : alumnos) { %>
  <tr>
    <td><%=a.getId()%></td>
    <td><%=a.getNombre()%></td>
    <td><%=a.getApellido()%></td>
    <td><%=a.getEmail()%></td>
  </tr>
<% } %>
</table>
</body>
</html>
```

### 7) Ejecutar y probar
- Clean & Build y Run en NetBeans con GlassFish.
- Accede a `/manejador` para ver la lista o enviar un POST para insertar.

---

## Parte B — Guía Maven
Esta sección muestra cómo montar el proyecto con Maven y ejecutar localmente (por ejemplo con Tomcat o Jetty) o empaquetar para GlassFish.

### 1) Crear proyecto Maven
En NetBeans: File > New Project > Maven > Web Application.
Elige `groupId` y `artifactId`, p. ej. `com.example` / `gestion-alumno`.

Añade dependencias esenciales en `pom.xml` (ejemplo con EclipseLink y MySQL):

```xml
<dependencies>
  <dependency>
    <groupId>jakarta.persistence</groupId>
    <artifactId>jakarta.persistence-api</artifactId>
    <version>3.1.0</version>
  </dependency>
  <dependency>
    <groupId>org.eclipse.persistence</groupId>
    <artifactId>org.eclipse.persistence.jpa</artifactId>
    <version>3.0.2</version>
  </dependency>
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.34</version>
  </dependency>
</dependencies>
```

> Nota: Ajusta versiones según el año y compatibilidades. Para usar Hibernate reemplaza la dependencia de EclipseLink por Hibernate Core y Hibernate JPA.

### 2) `persistence.xml` (RESOURCE_LOCAL)
En Maven coloca `src/main/resources/META-INF/persistence.xml`.
Ejemplo mínimo con provider y propiedades MySQL:

```xml
<persistence version="3.0"
  xmlns="https://jakarta.ee/xml/ns/persistence"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd">
  <persistence-unit name="AlumnosPU" transaction-type="RESOURCE_LOCAL">
    <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
    <class>com.example.model.Alumno</class>
    <properties>
      <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/dbalumno?useSSL=false&serverTimezone=UTC"/>
      <property name="jakarta.persistence.jdbc.user" value="root"/>
      <property name="jakarta.persistence.jdbc.password" value="rootpass"/>
      <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
      <!-- Opcional: crear/actualizar esquema automaticamente (dev only) -->
      <property name="jakarta.persistence.schema-generation.database.action" value="create"/>
    </properties>
  </persistence-unit>
</persistence>
```

### 3) Código para usar EntityManager en RESOURCE_LOCAL
En entornos sin contenedor JTA, crea un DAO que gestione EntityManagerFactory:

```java
public class AlumnoDAO {
  private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AlumnosPU");

  public void crear(Alumno a) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(a);
      tx.commit();
    } catch (Exception ex) {
      if (tx.isActive()) tx.rollback();
      throw ex;
    } finally {
      em.close();
    }
  }

  public List<Alumno> listar() {
    EntityManager em = emf.createEntityManager();
    try {
      return em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
    } finally {
      em.close();
    }
  }
}
```

### 4) Ejecutar con Maven
- Empaqueta: `mvn clean package`.
- Despliega el WAR en Tomcat/GlassFish o ejecuta con `mvn jetty:run` (ajusta plugin si quieres)

---

## Añadir tablas y relaciones (ejemplos)
### Caso: Alumno (N) <-> Curso (M) (Many-to-Many)
En BD crea tablas `ALUMNO`, `CURSO` y tabla intermedia `ALUMNO_CURSO`.

Entidad `Curso`:

```java
@Entity
public class Curso {
  @Id @GeneratedValue
  private Integer id;
  private String nombre;

  @ManyToMany(mappedBy = "cursos")
  private List<Alumno> alumnos = new ArrayList<>();
  // getters/setters
}
```

Entidad `Alumno` modificada:

```java
@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
@JoinTable(name = "ALUMNO_CURSO",
  joinColumns = @JoinColumn(name = "alumno_id"),
  inverseJoinColumns = @JoinColumn(name = "curso_id"))
private List<Curso> cursos = new ArrayList<>();
```

Notas:
- Si modificas la BD y quieres que NetBeans regenere las entidades, usa `Entity Classes from Database` nuevamente o edita las clases manualmente.
- Controla `cascade` y `fetch` para evitar carga innecesaria.

## Conectar a MySQL desde GlassFish (JTA)
1. Copia el `mysql-connector-java.jar` en `glassfish/domains/domain1/lib` o añádelo via Admin Console.
2. Admin Console: Resources > JDBC > JDBC Connection Pools > New: crea pool con el driver `com.mysql.cj.jdbc.Driver` y la URL `jdbc:mysql://localhost:3306/dbalumno`.
3. Resources > JDBC > JDBC Resources > New: crea recurso con JNDI name `java:app/DBAlumno` apuntando al pool.
4. En `persistence.xml` usa `<jta-data-source>java:app/DBAlumno</jta-data-source>` y `transaction-type="JTA"`.

## Migrar entre Derby y MySQL
- Cambia el datasource o las propiedades JDBC en `persistence.xml`.
- Asegúrate de incluir el driver JDBC en el classpath del servidor o en `WEB-INF/lib` (Maven lo empaqueta con la dependencia).
- Revisa diferencias de SQL (IDENTITY vs AUTO_INCREMENT) y tipos.

## Buenas prácticas y recomendaciones
- Mantén entidad y BD sincronizadas: en equipos grandes, el DDL debería manejarse por migraciones (Flyway/Liquibase) en lugar de `schema-generation` automático.
- No uses `create` en producción (pierdes datos). Prefiere `none` o `validate`.
- Usa DTOs para transferir datos fuera de la capa de persistencia si la entidad contiene lógica o relaciones pesadas.
- Controla el tamaño de colecciones para evitar OutOfMemory (paginación en consultas).

## Errores comunes y cómo resolverlos
- ClassNotFoundException: falta driver JDBC en classpath.
- javax.persistence.PersistenceException: persistence unit name mal escrito o `persistence.xml` no está en `META-INF`.
- TransactionRequiredException: en RESOURCE_LOCAL olvidaste gestionar la transacción.
- LazyInitializationException: intentas acceder a una colección LAZY fuera del EntityManager.

## Comandos útiles (resumen)
```bash
# Maven: empaquetar
mvn clean package

# Ejecutar Jetty (si está configurado)
mvn jetty:run
```

## Recursos adicionales
- Documentación Jakarta Persistence: https://jakarta.ee/specifications/persistence/
- EclipseLink: https://www.eclipse.org/eclipselink/
- Hibernate ORM: https://hibernate.org/orm/

---

## Siguientes pasos (para el alumno)
- Implementar operaciones UPDATE y DELETE en el servlet/DAO.
- Añadir validación de datos en formularios.
- Agregar paginación y filtros en las consultas.
- Aprender a usar migraciones (Flyway/Liquibase).

---

File creado: `Docs/tutorial-de-persistencia.md`.
