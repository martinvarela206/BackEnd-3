# Instalación

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

