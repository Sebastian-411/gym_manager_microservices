## Ejemplos de Peticiones (Postman y JSON)

### 1. Añadir un Nuevo Equipo (Solo ROLE_TRAINER y ROLE_ADMIN)

**Método:** `POST`  
**URL:** `/api/gym/equiment`  
**Descripción:** Permite a un administrador o entrenador añadir un nuevo equipo al gimnasio.

#### Ejemplo de Petición en Postman:

- **Authorization:** Bearer Token (Solo para `ROLE_TRAINER` o `ROLE_ADMIN`)
- **Headers:**
  - `Content-Type: application/json`
- **Body:**
```json
{
  "name": "Mancuernas",
  "description": "Juego de mancuernas de 10 a 30 kg",
  "amount": 15
}
```

#### Respuesta Exitosa (200 OK):
```json
{
  "id": 1,
  "name": "Mancuernas",
  "description": "Juego de mancuernas de 10 a 30 kg",
  "amount": 15
}
```

### 2. Obtener Todos los Equipos (Solo ROLE_TRAINER y ROLE_ADMIN)

**Método:** `GET`  
**URL:** `/api/gym/equiment`  
**Descripción:** Recupera una lista de todos los equipos registrados en el gimnasio.

#### Ejemplo de Petición en Postman:

- **Authorization:** Bearer Token (Solo para `ROLE_TRAINER` o `ROLE_ADMIN`)
- **Headers:**
  - `Content-Type: application/json`

#### Respuesta Exitosa (200 OK):
```json
[
  {
    "id": 1,
    "name": "Mancuernas",
    "description": "Juego de mancuernas de 10 a 30 kg",
    "amount": 15
  },
  {
    "id": 2,
    "name": "Cintas de correr",
    "description": "Cintas de correr con múltiples velocidades",
    "amount": 5
  }
]
```
