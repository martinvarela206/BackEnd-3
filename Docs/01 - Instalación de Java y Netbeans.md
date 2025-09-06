# Instalación

## Apps Web con Java with Ant

La cátedra de Backend 3 utiliza Java+Tomcat+Netbeans para aprender a desarrollar Aplicaciones Web en Java con Ant.

Ant es una herramienta que automatiza la compilacion, el empaquetado de archivos WAR y permite ejecutar test y desplegar aplicaciones. Otras herramientas mas actuales son Maven y Gradle.

Ant se incluye con Netbeans, por eso no es necesario instalarlo, pero si se quiere trabajar con Ant usando VSCode o IntelliJ, si habra que descargarlo.

<https://youtu.be/5p82gwr9MWk>

Tomcat es un servidor para aplicaciones Java basadas en Servlets y soporte para JSP, el cual es mas ligero que GlassFish o WildFly.

## Instalación en Windows

- Instalar JDK 24: Ingresar a <https://www.oracle.com/latam/java/technologies/downloads/>, seleccionar Windows y descargar el x64 Installer.
- Instalar NetBeans 25: Ingresar a <https://netbeans.apache.org/front/main/download/nb25/>, descargar el exe.
- Instalar Tomcat: Ingresar a <https://tomcat.apache.org/download-11.cgi> y descargar el zip, si se descarga Windows Service Installer, este se instalara en una carpeta con permisos de administrador y luego NetBeans no podrá acceder.

> Esto último, también sucede en Linux, si se instala tomcat en /opt, es mejor instalarlo en /home/usuario o /tmp.

## Instalación en Linux

- Ingresar a <https://www.oracle.com/latam/java/technologies/downloads/>, seleccionar Linux y descargar el .deb
- Instalar con: `sudo dpkg -i jdk-24_linux-x64_bin.deb`
- Descargar de <https://netbeans.apache.org/front/main/download/nb25/> el .deb.
- Instalar con `sudo dpkg -i apache-netbeans_25-1_all.deb`
- Dar permisos para tomcat:
```sh
sudo groupadd tomcat
sudo useradd -s /bin/false -g tomcat -d /opt/tomcat tomcat
sudo mkdir /opt/tomcat
```
- Estando en la carpeta de descargas:
```sh
curl -O https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.44/bin/apache-tomcat-10.1.44.tar.gz
sudo tar xzvf apache-tomcat-10.1.44.tar.gz -C /opt/tomcat --strip-components=1
```
- Ajustar permisos:
```sh
sudo chown -R tomcat: /opt/tomcat
sudo bash -c 'chmod +x /opt/tomcat/bin/*.sh'
```
- Crear el servicio: `sudo nano /etc/systemd/system/tomcat.service` y añadir:
```ini
[Unit]
Description=Apache Tomcat Web App Container
After=network.target

[Service]
Type=forking
User=tomcat
Group=tomcat
Environment="JAVA_HOME=/usr/lib/jvm/jdk-24.0.2-oracle-x64"
Environment="CATALINA_HOME=/opt/tomcat"
Environment="CATALINA_BASE=/opt/tomcat"
Environment="CATALINA_PID=/opt/tomcat/temp/tomcat.pid"
Environment="CATALINA_OPTS=-Xms512M -Xmx1024M"
ExecStart=/opt/tomcat/bin/startup.sh
ExecStop=/opt/tomcat/bin/shutdown.sh
Restart=always

[Install]
WantedBy=multi-user.target

```

Por ultimo crear los demonios y abrir el firewall:

```sh
sudo systemctl daemon-reload
sudo systemctl start tomcat
sudo systemctl enable tomcat
sudo systemctl status tomcat
sudo ufw allow 8080
```

La ubicación de tomcat puede generar problemas de permisos con netbeans, en lugar de instalarlo (descomprimirlo) bajo opt, seria mejor hacerlo bajo home o tmp, y configurar el tomcat.service de acuerdo a esas carpetas.

El usuario y contraseña se encuentra en `/tomcat/conf/users.xml`.

## Nuevo Proyecto en NetBeans

![alt text](<assets/nombre de proyecto.png>)

![alt text](<assets/configuracion de tomcat.png>)

## **Guía paso a paso: VSCode + Ant + Tomcat en Windows 11**

### **1. Instalar Java JDK 24**
- Descarga el JDK desde [Oracle](https://www.oracle.com/java/technologies/downloads/) o [Adoptium](https://adoptium.net/).
- Instala el JDK y toma nota de la ruta de instalación (por ejemplo, `C:\Program Files\Java\jdk-24`).

### **2. Instalar Apache Tomcat**
- Descarga la versión más reciente de Tomcat desde [tomcat.apache.org](https://tomcat.apache.org/download-90.cgi).
- Descomprime el archivo ZIP en una carpeta, por ejemplo: `C:\apache-tomcat-9.0.xx`.

### **3. Instalar Apache Ant**
- Descarga Ant desde [ant.apache.org](https://ant.apache.org/bindownload.cgi).
- Descomprime el archivo ZIP en una carpeta, por ejemplo: `C:\apache-ant-1.10.xx`.

### **4. Configurar variables de entorno**
- Abre el **Panel de Control > Sistema > Configuración avanzada del sistema > Variables de entorno**.
- Agrega/modifica las siguientes variables de usuario o del sistema:

| Variable      | Valor                                                          |
|---------------|---------------------------------------------------------------|
| JAVA_HOME     | C:\Program Files\Java\jdk-24                                  |
| ANT_HOME      | C:\apache-ant-1.10.xx                                         |
| PATH          | Agrega: `;%JAVA_HOME%\bin;%ANT_HOME%\bin` al final de la variable PATH |

- Guarda los cambios y reinicia la terminal o VSCode para que se apliquen.

### **5. Instalar y configurar extensiones en VSCode**
- Instala estas extensiones desde el marketplace de VSCode:
  - [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) (incluye soporte para Java y Maven, pero también sirve para Ant)
  - [Ant Target Runner](https://marketplace.visualstudio.com/items?itemName=mihn.ant-runner) (opcional, para lanzar tareas Ant desde VSCode)
  - [Tomcat for Java](https://marketplace.visualstudio.com/items?itemName=adashen.vscode-tomcat) (para administrar Tomcat desde VSCode)

### **6. Configurar Tomcat en VSCode**
- Abre la paleta de comandos (`Ctrl+Shift+P`) y busca `Tomcat: Add Tomcat Server`.
- Selecciona la carpeta donde descomprimiste Tomcat (`C:\apache-tomcat-9.0.xx`).
- El servidor Tomcat aparecerá en la barra lateral de VSCode, desde donde podrás iniciarlo/detenerlo y desplegar aplicaciones.

### **7. Crear un proyecto Java Web**
- Crea una nueva carpeta para tu proyecto y ábrela en VSCode.
- Estructura básica recomendada:
  ```
  tu-proyecto/
  ├─ src/
  │  └─ (tus archivos .java)
  ├─ web/
  │  └─ (tus archivos .jsp, .html, WEB-INF, etc.)
  └─ build.xml
  ```
- Crea el archivo `build.xml` en la raíz. Este es un ejemplo básico para compilar y empaquetar en un archivo WAR:

```xml name=build.xml
<project name="miweb" default="war" basedir=".">
    <property name="src.dir" value="src"/>
    <property name="web.dir" value="web"/>
    <property name="build.dir" value="build"/>
    <property name="dist.dir" value="dist"/>

    <target name="clean">
        <delete dir="${build.dir}"/>
        <delete dir="${dist.dir}"/>
    </target>

    <target name="compile">
        <mkdir dir="${build.dir}"/>
        <javac srcdir="${src.dir}" destdir="${build.dir}"/>
    </target>

    <target name="war" depends="compile">
        <mkdir dir="${dist.dir}"/>
        <war destfile="${dist.dir}/miweb.war" webxml="${web.dir}/WEB-INF/web.xml">
            <fileset dir="${web.dir}"/>
            <classes dir="${build.dir}"/>
        </war>
    </target>
</project>
```
- Asegúrate de tener la estructura de carpetas correcta y un archivo `web.xml` en `web/WEB-INF/`.

### **8. Compilar y empaquetar tu aplicación usando Ant**
- Abre una terminal en VSCode (`Ctrl+ñ`).
- Ejecuta:
  ```sh
  ant war
  ```
- Esto generará el archivo `miweb.war` en la carpeta `dist`.

### **9. Desplegar la aplicación en Tomcat**
- Copia el archivo `miweb.war` en `C:\apache-tomcat-9.0.xx\webapps\`.
- Inicia Tomcat desde VSCode (o ejecutando `bin\startup.bat` en la carpeta de Tomcat).
- Tomcat desplegará automáticamente el WAR.

### **10. Acceder a la aplicación**
- Abre [http://localhost:8080/miweb](http://localhost:8080/miweb) en tu navegador para ver la aplicación.

---

## **Consejos extra**
- Puedes automatizar aún más el despliegue agregando una tarea Ant que copie el WAR directamente a la carpeta `webapps` de Tomcat.
- Si usas la extensión "Tomcat for Java" en VSCode puedes desplegar el WAR haciendo clic derecho sobre el WAR y seleccionando "Deploy to Tomcat".

---

¿Te gustaría un ejemplo más detallado de un archivo `build.xml` o de la estructura de carpetas? ¿O tienes dudas con la configuración de alguna extensión?