## Ejemplos de Peticiones (Postman y JSON)

### 1. Programar una Nueva Clase (Solo ROLE_ADMIN y ROLE_TRAINER)

**Método:** `POST`  
**URL:** `/api/gym/class`  
**Descripción:** Permite a un entrenador o administrador programar una nueva clase.

#### Ejemplo de Petición en Postman:

- **Authorization:** Bearer Token (Solo para `ROLE_ADMIN` o `ROLE_TRAINER`)
- **Headers:**
  - `Content-Type: application/json`
- **Body:**
```json
{
  "name": "Yoga Avanzado",
  "schedule": "2024-09-21T15:00:00",
  "maximumCapacity": 20,
  "idTrainer": {
    "idTrainer": 1
  }
}
```

#### Respuesta Exitosa (200 OK):
```json
{
  "id": 1,
  "name": "Yoga Avanzado",
  "schedule": "2024-09-21T15:00:00",
  "maximumCapacity": 20,
  "currentCapacity": 0,
  "idTrainer": {
    "idTrainer": 1
  }
}
```

### 2. Obtener Todas las Clases (Disponible para ROLE_ADMIN, ROLE_TRAINER, ROLE_MEMBER)

**Método:** `GET`  
**URL:** `/api/gym/class`  
**Descripción:** Recupera la lista de todas las clases programadas en el gimnasio.

#### Ejemplo de Petición en Postman:

- **Authorization:** Bearer Token (Para `ROLE_ADMIN`, `ROLE_TRAINER`, `ROLE_MEMBER`)
- **Headers:**
  - `Content-Type: application/json`

#### Respuesta Exitosa (200 OK):
```json
[
  {
    "id": 1,
    "name": "Yoga Avanzado",
    "schedule": "2024-09-21T15:00:00",
    "maximumCapacity": 20,
    "currentCapacity": 10,
    "idTrainer": {
      "idTrainer": 1
    }
  },
  {
    "id": 2,
    "name": "Pilates Intermedio",
    "schedule": "2024-09-22T10:00:00",
    "maximumCapacity": 15,
    "currentCapacity": 7,
    "idTrainer": {
      "idTrainer": 2
    }
  }
]
```

