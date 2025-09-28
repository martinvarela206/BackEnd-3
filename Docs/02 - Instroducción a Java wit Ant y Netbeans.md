## Aplicaciones Web con Java with Ant

La cátedra de Backend 3 utiliza Java+Tomcat+Netbeans para aprender a desarrollar Aplicaciones Web en Java con Ant.

### Que es Ant?

Ant es una herramienta de automatización de compilación para proyectos Java. Es anterior a sistemas como Maven o Gradle. Ant se usa para compilar, construir, empaquetar y desplegar aplicaciones Java, especialmente en proyectos grandes.

NetBeans tiene integración nativa con Ant y sus proyectos "Java with Ant" (por eso puedes crear, ejecutar y depurar fácil). Al trabajar con un proyecto "Java with Ant" significa que NetBeans crea archivos de configuración (como build.xml) para que Ant se encargue de estos procesos. 

Otros IDEs como VSCode e IntelliJ pueden abrir proyectos Ant, pero necesitas instalar plugins/extensiones y a veces configurar manualmente la ejecución de los scripts de Ant.

<https://youtu.be/5p82gwr9MWk>

### Que es Tomcat?

Tomcat es un servidor para aplicaciones Java basadas en Servlets y soporte para JSP, el cual es mas ligero que GlassFish o WildFly.

## Crear un proyecto Java Web con Ant en NetBeans

1. Abre NetBeans y selecciona "File" > "New Project".
2. En la ventana "New Project", selecciona "Java with Ant" en la categoría y "Java Web" en los proyectos. Haz clic en "Next".
3. Ingresa el nombre del proyecto y la ubicación donde deseas guardarlo. Haz clic en "Next".
4. Selecciona el servidor (Apache Tomcat) y la versión de Java EE (Java EE 7 Web). Haz clic en "Finish".

## Scaffolding de un proyecto en NetBeans

Desde Netbeans, el proyecto muestra un scaffolding diferente al que se puede observar accediendo a la carpeta del proyecto desde el explorador de archivos. Esto es porque Netbeans oculta ciertos archivos y carpetas que no son relevantes para el desarrollo diario. Pero también porque Netbeans organiza el proyecto de una manera que facilita la navegación y gestión del código.

Estructura del proyecto desde el explorador de archivos:

```
miweb/
├─ nbproject/ # Carpeta oculta por Netbeans, contiene las configuraciones para Netbeans y Ant.
│  ├─ private/
│  │  └─ private.properties # Configuraciones locales del usuario, como rutas específicas.
│  ├─ ant-deploy.xml # Script de Ant para desplegar la aplicación.
│  ├─ build-impl.xml # Implementación interna del proceso de construcción (no modificar).
│  ├─ genfiles.properties # Lista de archivos generados automáticamente.
│  ├─ project.properties # Propiedades del proyecto, como rutas y configuraciones de compilación.
│  └─ project.xml # Configuración del proyecto para Netbeans en formato XML.
├─ src/
│  ├─ conf/
│  │  └─ MANIFEST.MF # Metadatos del proyecto (versión, clase principal, etc).
│  ├─ java/
│  │  └─ # Todo el codigo fuente Java (servlets, beans, etc.)
├─ web/
│  ├─ META-INF/ # Metadatos de la aplicación web.
│  ├─ WEB-INF/ # Configuración interna (no accesible desde el navegador).
│  └─ index.html # Página principal pública de la aplicación.
└─ build.xml # Archivo principal de Ant para compilar, construir y desplegar el proyecto.
```

Estructura del proyecto desde Netbeans:

![alt text](<assets/scaffolding.png>)

**WebApplication1:** Es el proyecto creado.
- **Web Pages:** Es completamente publica y es donde se encuentra el index.html. Aquí mismo debe crearse una carpeta assets, donde se añaden las carpetas js, css, img, etc. Teóricamente la carpeta assets es publica pero no visible (?)
- **Source Packages:** Aquí es donde se crean las carpetas para el modelo y controlador, etc, es decir todos los archivos java (servlets, javabeans, etc).
- **Libraries:** Aquí se encuentran las librerías añadidas al proyecto, como GSON (para trabajar con JSON) o el conector de MySQL.