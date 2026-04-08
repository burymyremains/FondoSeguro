# 1. Configuración de Terraform y Providers
terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = "us-east-2"
}

# 3. Recurso de S3
resource "aws_s3_bucket" "estados_cuenta_bucket" {
  bucket = "fondoseguro-documentos-${random_id.suffix.hex}"
  
  tags = {
    Name        = "FondoSeguro_Docs"
    Environment = "Dev"
  }
}

# 4. Generador de ID aleatorio
resource "random_id" "suffix" {
  byte_length = 4
}

# 5. Bloqueo de acceso público para S3
resource "aws_s3_bucket_public_access_block" "block_public" {
  bucket = aws_s3_bucket.estados_cuenta_bucket.id

  block_public_acls       = true
  block_public_policy     = true
  ignore_public_acls      = true
  restrict_public_buckets = true
  
}
# 1. Crear el usuario de IAM para la aplicación
resource "aws_iam_user" "app_user" {
  name = "fondoseguro-app-service"
}

# 2. Crear las llaves de acceso para este usuario específico
resource "aws_iam_access_key" "app_user_keys" {
  user = aws_iam_user.app_user.name
}

# 3. Definir la política de permisos (Solo S3 y DynamoDB)
resource "aws_iam_user_policy" "app_policy" {
  name = "FondoSeguroAppPolicy"
  user = aws_iam_user.app_user.name

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = [
          "dynamodb:PutItem",
          "dynamodb:GetItem",
          "dynamodb:UpdateItem",
          "dynamodb:Query"
        ]
        Effect   = "Allow"
        Resource = "${aws_dynamodb_table.usuarios_table.arn}"
      },
      {
        Action = [
          "s3:PutObject",
          "s3:GetObject",
          "s3:ListBucket"
        ]
        Effect   = "Allow"
        Resource = [
          "${aws_s3_bucket.estados_cuenta_bucket.arn}",
          "${aws_s3_bucket.estados_cuenta_bucket.arn}/*"
        ]
      }
    ]
  })
}

# Mostrar las nuevas llaves en la consola al terminar
output "app_access_key" {
  value = aws_iam_access_key.app_user_keys.id
}

output "app_secret_key" {
  value     = aws_iam_access_key.app_user_keys.secret
  sensitive = true
}
