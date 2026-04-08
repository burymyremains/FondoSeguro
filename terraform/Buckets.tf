resource "aws_s3_bucket" "ia_processing_bucket" {
  bucket = "fondoseguro-user-txt-info" # El nombre debe ser único globalmente

  lifecycle {
    prevent_destroy = false
  }

  tags = {
    Project     = "FondoSeguro"
    Environment = "Hackathon"
    Owner       = "cuarzo"
  }
}

# Bloquear acceso público 
resource "aws_s3_bucket_public_access_block" "ia_bucket_privacy" {
  bucket = aws_s3_bucket.ia_processing_bucket.id

  block_public_acls       = true
  block_public_policy     = true
  ignore_public_acls      = true
  restrict_public_buckets = true
}

# Habilitar encriptación 
resource "aws_s3_bucket_server_side_encryption_configuration" "ia_bucket_crypto" {
  bucket = aws_s3_bucket.ia_processing_bucket.id

  rule {
    apply_server_side_encryption_by_default {
      sse_algorithm = "AES256"
    }
  }
}