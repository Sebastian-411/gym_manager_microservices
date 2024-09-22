# API Gym Payment - Endpoint para Agregar Pago

### Agregar un Pago (Solo `ROLE_TRAINER` y `ROLE_ADMIN`)

**Método:** `POST`  
**URL:** `/api/gym/payment`  
**Descripción:** Permite registrar un nuevo pago de un miembro para un mes específico. Si ya existe un pago registrado para ese miembro y mes, se devuelve un error.

#### Requisitos:

- **Roles Permitidos:** `ROLE_TRAINER` o `ROLE_ADMIN`
- **Autorización:** Token JWT (Bearer Token)
- **Headers:**
  - `Authorization: Bearer <token>`
  - `Content-Type: application/json`

#### Ejemplo de Petición en Postman:

```json
{
  "memberId": {
    "idTrainer": 1
  },
  "amountPaid": 50000,
  "paymentMonth": "2024-09"  // formato Year-Month
}
```

#### Respuesta Exitosa (200 OK):

```json
{
  "id": 1,
  "memberId": {
    "idTrainer": 1
  },
  "amountPaid": 50000,
  "paymentMonth": "2024-09"
}
```

#### Respuesta de Error (409 Conflict) - Pago Duplicado:

```json
{
  "timestamp": "2024-09-22T12:34:56",
  "status": 409,
  "error": "Conflict",
  "message": "Ya existe un pago registrado para este miembro en el mes especificado.",
  "path": "/api/gym/payment"
}
```
