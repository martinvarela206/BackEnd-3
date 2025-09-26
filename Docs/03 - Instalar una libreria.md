## Instalar GSON

- Ir a la página oficial de GSON: <https://repo1.maven.org/maven2/com/google/code/gson/gson/2.13.1/> y descargar el archivo gson-2.13.1.jar.
- En Libraries, hacer clic derecho y luego darle a Add Jar/folder, luego seleccionar el archivo gson-2.13.1.jar descargado.

La librería GSON de Java es utilizada para convertir objetos Java a JSON y viceversa (serialización y deserialización).

Usos principales:
- Serializar objetos Java a formato JSON.
- Deserializar (parsear) JSON a objetos Java.
- Facilita el intercambio de datos entre aplicaciones Java y servicios web (APIs REST).

```java
Gson gson = new Gson();
String json = gson.toJson(miObjeto); // Objeto Java a JSON
MiClase obj = gson.fromJson(json, MiClase.class); // JSON a objeto Java
```

## Instalar JSTL

Boton derecho en Libraries > Add Library > Y seleccionar JSTL.