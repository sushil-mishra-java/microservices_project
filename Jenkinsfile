pipeline {
  agent any

  environment {
    AWS_REGION = "ap-south-1"
    ACCOUNT_ID = "065025974707"
    ECR = "${ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"
  }

  stages {

    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Push Order Service') {
      steps {
        sh '''
          pwd
          ls

          cd order-service   # <-- adjust if different

          mvn clean package -DskipTests

          aws ecr get-login-password --region $AWS_REGION \
          | docker login --username AWS --password-stdin $ECR

          docker build -t order-service:${BUILD_NUMBER} .
          docker tag order-service:${BUILD_NUMBER} $ECR/order-service:${BUILD_NUMBER}
          docker push $ECR/order-service:${BUILD_NUMBER}
        '''
      }
    }
  }

  post {
    always {
      sh 'docker image prune -f || true'
    }
  }
}
