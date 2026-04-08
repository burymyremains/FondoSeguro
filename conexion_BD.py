import boto3
import json
from botocore.exceptions import ClientError

dynamodb = boto3.resource('dynamodb', region_name='us-east-1')
table = dynamodb.Table('FondoSeguro_Usuarios') # Nombre de la tabla

def lambda_handler(event, context):
    # El ID del usuario vendrá en el evento enviado por el Backend
    user_id = event.get('userId')
    
    try:
        # 1. Obtener datos de DynamoDB
        response = table.get_item(Key={'userId': user_id})
        user_data = response.get('Item')
        
        if not user_data:
            return {'statusCode': 404, 'body': 'Usuario no encontrado'}

        # 2. Preparar los datos para la Regresión Logística preparacion de los datos.
        features = [
            float(user_data['ingreso_mensual']),
            float(user_data['gasto_total']),
            float(user_data['saldo_ahorro'])
        ]
        
        # 3. Ejecutar lógica de Score
        # score = modelo_entrenado.predict_proba([features])
        score = calcular_score_logistico(features) # Tu función de IA
        
        return {
            'statusCode': 200,
            'body': json.dumps({'userId': user_id, 'score': score})
        }

    except ClientError as e:
        print(e.response['Error']['Message'])
        return {'statusCode': 500, 'body': 'Error al consultar DynamoDB'}