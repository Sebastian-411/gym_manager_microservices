## Ejemplos de Peticiones (Postman y JSON)

### 1. Añadir un Nuevo Entrenador (Solo ROLE_ADMIN)

**Método:** `POST`  
**URL:** `/api/gym/trainer`  
**Descripción:** Permite a un administrador añadir un nuevo entrenador al sistema.

#### Ejemplo de Petición en Postman:

- **Authorization:** Bearer Token (Solo para `ROLE_ADMIN`)
- **Headers:**
  - `Content-Type: application/json`
- **Body:**
```json
|
```

#### Respuesta Exitosa (200 OK):
```json
{
  "id": 1,
  "name": "Carlos Martínez",
  "expertise": "Entrenamiento de fuerza"
}
```

### 2. Obtener Todos los Entrenadores (Solo ROLE_ADMIN)

**Método:** `GET`  
**URL:** `/api/gym/trainer`  
**Descripción:** Recupera una lista de todos los entrenadores registrados en el sistema.

#### Ejemplo de Petición en Postman:

- **Authorization:** Bearer Token (Solo para `ROLE_ADMIN`)
- **Headers:**
  - `Content-Type: application/json`

#### Respuesta Exitosa (200 OK):
```json
[
  {
    "id": 1,
    "name": "Carlos Martínez",
    "expertise": "Entrenamiento de fuerza"
  },
  {
    "id": 2,
    "name": "Sofía Ramírez",
    "expertise": "Yoga"
  }
]
```

### 3. Verificar si un Entrenador Existe por ID

**Método:** `GET`  
**URL:** `/api/gym/trainer/{id}`  
**Descripción:** Verifica si un entrenador existe en el sistema mediante su ID.

#### Ejemplo de Petición en Postman:

- **Authorization:** No es necesario
- **Headers:**
  - `Content-Type: application/json`

#### Ejemplo de URL:
`/api/gym/trainer/1`

#### Respuesta Exitosa (200 OK):
```json
true
```
