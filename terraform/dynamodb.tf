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

#Tabla de Ahorros (Ordenada por Fecha)
resource "aws_dynamodb_table" "ahorro-table" {
  name         = "FondoSeguro_Ahorro" # Corregido el nombre
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "UserId"
  range_key    = "Fecha" # <--- Orden cronológico de ingresos

  attribute {
    name = "UserId"
    type = "S"
  }

  attribute {
    name = "Fecha"
    type = "S"
  }

  # Tabla de Ahorros Generados (El que nos da la app de capital )
  resource "aws_dynamodb_table" "ahorro_generado" {
    name         = "FondoSeguro_Ahorro_Generado"
    billing_mode = "PAY_PER_REQUEST"
    hash_key     = "UserId"
    range_key    = "Fecha"
  
    attribute { name = "UserId" type = "S" }
    attribute { name = "Fecha"  type = "S" }
  }
  
  # Tabla de Ahorros Recomendados (IA)
  resource "aws_dynamodb_table" "ahorro_recomendado" {
    name         = "FondoSeguro_Ahorro_Recomendado"
    billing_mode = "PAY_PER_REQUEST"
    hash_key     = "UserId"
    range_key    = "Fecha"
  
    attribute { name = "UserId" type = "S" }
    attribute { name = "Fecha"  type = "S" }
  }
}