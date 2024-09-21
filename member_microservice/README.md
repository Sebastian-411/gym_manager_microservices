## Ejemplos de Peticiones (Postman y JSON)

### 1. Añadir un Nuevo Miembro (Solo ROLE_TRAINER y ROLE_ADMIN)

**Método:** `POST`  
**URL:** `/api/members`  
**Descripción:** Permite a un administrador o entrenador añadir un nuevo miembro al sistema.

#### Ejemplo de Petición en Postman:

- **Authorization:** Bearer Token (Solo para `ROLE_TRAINER` o `ROLE_ADMIN`)
- **Headers:**
  - `Content-Type: application/json`
- **Body:**
```json
{
  "name": "Juan Pérez",
  "email": "juan.perez@example.com",
  "registrationDate": "2024-09-01"
}
```

#### Respuesta Exitosa (200 OK):
```json
{
  "id": 1,
  "name": "Juan Pérez",
  "email": "juan.perez@example.com",
  "registrationDate": "2024-09-01"
}
```

### 2. Obtener Todos los Miembros (Solo ROLE_TRAINER y ROLE_ADMIN)

**Método:** `GET`  
**URL:** `/api/members`  
**Descripción:** Recupera una lista de todos los miembros registrados en el sistema.

#### Ejemplo de Petición en Postman:

- **Authorization:** Bearer Token (Solo para `ROLE_TRAINER` o `ROLE_ADMIN`)
- **Headers:**
  - `Content-Type: application/json`

#### Respuesta Exitosa (200 OK):
```json
[
  {
    "id": 1,
    "name": "Juan Pérez",
    "email": "juan.perez@example.com",
    "registrationDate": "2024-09-01"
  },
  {
    "id": 2,
    "name": "María Gómez",
    "email": "maria.gomez@example.com",
    "registrationDate": "2024-08-15"
  }
]
```

### 3. Verificar si un Miembro Existe por ID

**Método:** `GET`  
**URL:** `/api/members/exist/{id}`  
**Descripción:** Verifica si un miembro existe en el sistema mediante su ID.

#### Ejemplo de Petición en Postman:

- **Authorization:** No es necesario
- **Headers:**
  - `Content-Type: application/json`

#### Ejemplo de URL:
`/api/members/exist/1`

#### Respuesta Exitosa (200 OK):
```json
true
```
