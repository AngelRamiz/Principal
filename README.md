# Sistema de Atención con Turnos

Este proyecto implementa un **backend completo** para gestionar turnos de atención al cliente, simulando sistemas utilizados en bancos, clínicas o centros de servicio. El desarrollo se hizo cumpliendo con los requerimientos por semana y utilizando estructuras de datos personalizadas, APIs REST, mensajería asíncrona y almacenamiento de logs.

---

## Estructura de Proyectos

Este sistema está dividido en **tres módulos Maven**:

 Proyecto                               Funcionalidad 
 `Principal` -Proyecto principal (Spring Boot) con controladores, servicios, RabbitMQ, MongoDB y configuración general.
 
 `Persistencia`  Contiene entidades JPA (Usuario, Tarea, Historial) y repositorios. Conecta con MySQL. 
 
| `Estructura` Estructuras de datos personalizadas (Lista, Pila, Cola, Árbol)-


## Objetivos del Sistema

-Crear, editar, eliminar y marcar tareas como completadas.  
-Clasificar tareas por estado, prioridad o tipo.  
-Jerarquizar tareas en subniveles usando un **árbol personalizado**.  
-Registrar historial de acciones con posibilidad de **deshacer con pila**.  
-Exponer todas las funciones como **APIs REST documentadas con Swagger**.  
-Integrar eventos con **RabbitMQ (Docker)** para notificaciones.  
-Guardar **logs en MongoDB** (opcional pero implementado).

---

## Estructuras de Datos Personalizadas

| Estructura | Uso |
|-----------|-----|
| `Lista<T>` | Guarda y lista tareas por usuario. 
| `Pila<T>` | Deshacer la última acción realizada. |
| `Cola<T>` | Manejo de tareas programadas por orden de llegada. |
| `Árbol<T>` | Representación jerárquica de tareas y subtareas. |

> Todas las estructuras están dentro de `Estructuras`.

##  Tecnologías Usadas

- Java 17 + Maven
- Spring Boot 3.2.5
- MySQL (base de datos `SystemPriority_db`)
- MongoDB (logs)
- RabbitMQ (mensajería) vía Docker
- Postman (pruebas)

##  API REST (Principales Rutas)

- `POST /api/usuarios` → Crear usuario  
- `POST /api/tareas/{idUsuario}` → Crear tarea  
- `PUT /api/tareas/{id}` → Editar tarea  
- `DELETE /api/tareas/{id}` → Eliminar tarea  
- `GET /api/tareas` → Listar todas las tareas  
- `POST /api/tareas/programar/{idUsuario}` → Agendar tarea programada  
- `GET /api/tareas/programada/siguiente` → Obtener siguiente tarea programada  
- `GET /api/tareas/arbol` → Ver árbol de tareas  
- `DELETE /api/tareas/deshacer` → Deshacer eliminación  
- `GET /api/historial` → Ver historial de acciones  

---

## Configuración de RabbitMQ

docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

- Interfaz web: http://localhost:15672  
- Usuario: `guest`  
- Contraseña: `guest`

##  Configuración de MongoDB (logs)

docker run -d \
  --name mongodb \
  -p 27017:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=admin \
  -e MONGO_INITDB_ROOT_PASSWORD=admin123 \
  mongo


- Acceso vía **MongoDB Compass** usando URI:

mongodb://admin:admin123@localhost:27017/?authSource=admin


Los eventos se almacenan automáticamente en la colección `log_evento`.


## Pruebas y Documentación

- Todas las rutas se probaron con Postman.
- Se verificó el historial, los eventos, la jerarquía de tareas y la programación por fecha.
- Logs en consola y en MongoDB reflejan acciones como creación y eliminación de tareas.
