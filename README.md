# REST Api carritos Garbarino

Esta es la parte de backend del ejercicio de carritos para la entrevista de trabajo en Garbarino.

# General

Se utilizó Java 8 con el framework Spring para armar el backend rest. 
La aplicación fue creada en base al archetype maven-archetype-webapp.
Se utilizó jackson-databind para la conversión implícita entre objetos JSON y clases POJO.
Se utilizó log4j para el logeo de errores.

En el pom.xml se encuentran las dependencias utilizadas y que hay que descargar para poder levantar el proyecto.

# Estructura

La aplicación está estructurada en las siguientes capas:
 ```
  /collections -> Listas de carritos de compras y de productos almacenados en memoria.
  /controllers -> controller de la api REST y del controlador de processing de carritos.
  /daos -> data access objects para el acceso a los datos de las collections.
  /dto -> data transfer objects para el traspaso de datos entre capas.
  /interfaces -> interfaces para los métodos del service y el dao.
  /models -> modelo de objeto para productos y carritos. 
  /services -> capa de servicios que transfiere entre controllers y daos
  ```
 
# Instalación

 ```
 1. git clone https://github.com/lnzzz/cart.git
 2. importar el proyecto maven al IDE.
 3. cambiar el parámetro File al cual se escribirán los errores de log en el log4j.properties
 3. deployar el proyecto en un servidor (en mi caso fue en un Tomcat 9.0)
 4. iniciar el servidor.
 ```
 
 Si todo salió OK, la api deberá levantar en la url http://localhost:8080/backend/rest y el servicio de processing comenzará a procesar los carritos cargados en memoria.

  
  
  # Rutas
  
  ```
  POST a /backend/rest/carts -> crea un carrito nuevo.
  POST a /backend/rest/carts/:id/products -> agrega un producto al carrito.
  POST a /backend/rest/carts/:id/checkout -> setea el carrito en estado READY.
  GET a /backend/rest/carts/:id -> devuelve un carrito completo.
  GET a /backend/rest/carts/:id/products -> devuelve los productos del carrito.
  DELETE a /backend/rest/carts/:idCarrito/products/:idProducto -> elimina por completo un producto con id = a :idProducto de un carrito con id = a :idCarrito.
  ```

  Todas las peticiones producen una respuesta JSON, ya sean exitosas o haya ocurrido un error.
  La respuesta vuelve en forma de objeto JSON con la siguiente estructura:
  
  ``` 
  {
	"code" : 200 / 404 / 500,
	"message" : String o JSON
  }
  ```
  
  A su vez, todos los headers de las peticiones POST deben contener Content-type: 'application/json' y enviar los parámetros a modo de JSON.
  Ejemplo:
  
  
  POST a http://localhost:8080/backend/rest/carts
  debe enviar los parámetros en raw y con la siguiente estructura:
  ```
  {
	"fullName": "asdasdasd",
	"email": "asdasdasd@gmails.com"
  }
  ```
