# 1. Tabla de Usuarios
resource "aws_dynamodb_table" "usuarios_table" {
  name         = "FondoSeguro_Usuarios"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "UserId"

  attribute {
    name = "UserId"
    type = "S"
  }
}

resource "aws_dynamodb_table" "gastos_table" {
  name         = "FondoSeguro_Gastos"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "UserId"
  range_key    = "FechaGasto"

  attribute {
    name = "UserId"
    type = "S"
  }
  attribute {
    name = "FechaGasto"
    type = "S"
  }
}

resource "aws_dynamodb_table" "ingreso_table" {
  name         = "FondoSeguro_Ingresos"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "UserId"
  range_key    = "FechaIngreso"

  attribute {
    name = "UserId"
    type = "S"
  }
  attribute {
    name = "FechaIngreso"
    type = "S"
  }
}

resource "aws_dynamodb_table" "ahorro_table" {
  name         = "FondoSeguro_Ahorro"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "UserId"
  range_key    = "Fecha"

  attribute {
    name = "UserId"
    type = "S"
  }
  attribute {
    name = "Fecha"
    type = "S"
  }
}

resource "aws_dynamodb_table" "ahorro_generado" {
  name         = "FondoSeguro_Ahorro_Generado"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "UserId"
  range_key    = "Fecha"

  attribute {
    name = "UserId"
    type = "S"
  }
  attribute {
    name = "Fecha"
    type = "S"
  }
}

resource "aws_dynamodb_table" "ahorro_recomendado" {
  name         = "FondoSeguro_Ahorro_Recomendado"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "UserId"
  range_key    = "Fecha"

  attribute {
    name = "UserId"
    type = "S"
  }
  attribute {
    name = "Fecha"
    type = "S"
  }
}