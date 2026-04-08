#Tabla de Usuarios
resource "aws_dynamodb_table" "usuarios_table" {
  name         = "FondoSeguro_Usuarios"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "UserId"

  attribute {
    name = "UserId"
    type = "S" # Cambiado a String para mayor flexibilidad
  }
}

# Tabla de Gastos 
resource "aws_dynamodb_table" "gastos_table" {
  name         = "FondoSeguro_Gastos"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "UserId"
  range_key    = "FechaGasto" # <--- Aquí la fecha ordena los gastos del usuario

  attribute {
    name = "UserId"
    type = "S"
  }

  attribute {
    name = "FechaGasto"
    type = "S" # Formato: 2026-04-08T10:00:00
  }
}

#Tabla de Ingresos (Ordenada por Fecha)
resource "aws_dynamodb_table" "ingreso_table" {
  name         = "FondoSeguro_Ingresos" # Corregido el nombre
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "UserId"
  range_key    = "FechaIngreso" # <--- Orden cronológico de ingresos

  attribute {
    name = "UserId"
    type = "S"
  }

  attribute {
    name = "FechaIngreso"
    type = "S"
  }
}