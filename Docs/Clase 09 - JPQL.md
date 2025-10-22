# EJB (Java Beans Enterprise)

Un EJB es un componente del lado del servidor que encapsula la lógica de negocio de una aplicación Java EE. Los EJBs son gestionados por un contenedor EJB, que proporciona servicios como transacciones, seguridad y concurrencia.

No es mas que una clase de java, un simple POJO (Plain Old Java Object) que sigue ciertas convenciones y anotaciones para ser reconocido como un EJB por el contenedor.

Tiene atributos no publicos, al menos un constructor publico sin parametros y metodos publicos acceden a los atributos.

Se tienen que desplegar en un contenedor EJB, como JBoss, Glassfish o WebLogic.

## Tipos de EJB

1. **Session Beans**: Representan la lógica de negocio y pueden ser de dos tipos:
   - **Stateless**: No mantienen estado entre llamadas. Son ideales para operaciones que no requieren mantener información entre invocaciones.
   - **Stateful**: Mantienen estado entre llamadas. Son útiles cuando se necesita conservar información específica del cliente durante una sesión.
2. **Message-Driven Beans (MDB)**: Son componentes que procesan mensajes asíncronos, generalmente de una cola de mensajes (JMS). Permiten la comunicación entre diferentes partes de una aplicación de manera desacoplada.

JPA (Java Persistence API) define complementariamente entidades que representan tablas en la base de datos y permiten mapear objetos Java a registros de la base de datos.

## Diferencia entre JavaBeans y Session Beans

- **JavaBeans**: Son componentes reutilizables que encapsulan datos y lógica de negocio, pero no están diseñados para ser gestionados por un contenedor EJB. No proporcionan servicios como transacciones o seguridad.
- **Session Beans**: Son EJBs gestionados por un contenedor EJB y proporcionan servicios adicionales como transacciones, seguridad y concurrencia. Están diseñados para manejar la lógica de negocio en aplicaciones empresariales.

Los beans antes los manejaban los servlets, y eran como muy públicos.

## Ventajas de usar EJB

Ofrece los servicios de:
- transacciones y seguridad
- pool de recursos y almacenamiento
- soporte de concurrencia

Cuando se traen los datos desde la bbdd al servidor, es cuando se necesita asegurar la informacion, y esto lo permiten los EJB.

Pero hay que definir su tiempo de vida:
- Aplicacion
- Consulta
- Tiempo determinado

## Tipos de sesiones Beans

- Stateless: No mantienen estado entre llamadas. Son ideales para operaciones que no requieren mantener información entre invocaciones. Es el utilizado por defecto.
- Stateful: Mantienen estado entre llamadas. Son útiles cuando se necesita conservar información específica del cliente durante una sesión. Este se utiliza por ejemplo para mantener el carrito de compras de un usuario, durante la sesion.
- Singleton: Una sola instancia compartida entre todos los clientes. Se utiliza para mantener datos globales o configuraciones de la aplicación. No guardan datos sensibles, que suele existir durante la existencia de la aplicacion.

## Sesion Bean JPA

Hay que combinar las entidades JPA con los EJB para manejar la persistencia de datos en una aplicación Java EE.

El bean de sesion proporciona una interfaz para manipular las entidades, permitiendo tener un CRUD.

Aqui aparece el patron facade.

## Facade

Es una fachada, es un nivel mas de abtraccion, que abstrae las entidades, y expone metodos mas simples para el cliente. Esta fachada permite darle nivel de seguridad a las entidades, y controlar el acceso a los datos.

Aqui es donde se define el nivel de filtro del usuario, es decir, que ciertos usuarios puede acceder solo a ciertos metodos y por lo tanto datos.

La combinacion de EJB y entidades JPA se organiza en las Facades.

## Conclusion

A partir de los EJB, el cliente ya no trabaja directamente con las entidades, son los EJB los que trabajan con las entidades, y el cliente trabaja con los EJB.

