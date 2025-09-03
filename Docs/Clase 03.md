# Componentes Web de Java
## Temas
- Que es servlet?
- Modelo Requerimiento-Respuesta.
- Ciclo de vida.
- Ejemplo en NetBeans.

## Que es Servlet?

Es el componente mas importante, y conocer su limitaciones. Hay que saber quien le da vida y durante cuanto tiempo.

Los servlets son objetos de Java que extienden las funcionalidades de un servidor HTTP (es decir, entiende el protocolo HTTP), para contenido dinámico.

Se basan en el modelo de programación requerimiento-respuesta (request-response).

```java
// Codigo de ejemplo
public class MiServlet extends HttpServlet { 
	public void processRequest (Http... request, Http.. response){
		response.setContentType("text/html"); PrintWriter out = response.getWriter(); out.println("");
	}
	
	@Override public void doGet(HttpServletRequest request, HttpServletResponse response){
		processRequest (request, response);
	}
	
	@Override public void doPost(HttpServletRequest request,HttpServletResponse response){
		processRequest (request, response); 
	}
	…
}
```

El servidor envía resquest y recibe responses simplemente, de la lógica se encargan los servlets. El servidor es el que tiene el control del ciclo de vida de los servlets, es quien evalua si crea o no una instancia y cuanto tiempo la mantiene viva o la elimina. Si no existe intancia, se crea, sino simplemente se llama. Por servlet solo existe una instancia escuchando en el servidor.

Es decir que es monohilo.

Por lo tanto, el servlet debe estar lo mas desocupado posible, por lo tanto la lógica pesada debe ser derivada a otros componentes. No hay que sobrecargar el servlet con todo el codigo del proyecto.

Ver diferencia en las solicitudes GET y POST, la tabla.

La transferencia de datos por GET es mas rápida que por POST.

TODO: Ver que es un WAR.

Como clase, el servidor se comunica con el servlet por medio de los métodos: init, service y destroy. Estos tres métodos son parte de la interface Servlet y por eso, por herencia, hay que implementarlos. Pero estos metodos esta implementados en GenericServlet.

Ademas en HTTPServlet los metodos doGet, doPost y service son definidos pero no implementados.

Services esta implementado e invoca a doGet y doPost automaticamente. Se aconseja no sobreescribirlo.

La herencia se da desde javax.servlet.http.HttpServlet.

Al darle Proyecto > New, lo que pide es nombre y ubicacion (deberia ser Source Packages) y en Package se pone el nombre de la carpeta (por ejemplo Controller)

## Scaffolding

Proyecto
- Web Pages: Es completamente publica y es donde se encuentra el index.html. Aqui mismo debe crearse una carpeta assets, donde se añaden las carpetas js, css, img, etc. Teoricamente la carpeta assets es publica pero no visible (?)
- Source Packages: Aqui es donde se crean las carpetas para el modelo y controlador, etc. Los servlet son controladores.

## Instalar libreria GSON

En Libraries, hacer clic derecho y luego darle a Add Jar/folder, luego seleccionar el archivo gson-2.13.1.jar descargado.



