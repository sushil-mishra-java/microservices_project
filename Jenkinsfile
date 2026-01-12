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

    stage('Build Order Service') {
      steps {
        sh '''
          cd OrderService
          mvn clean package -DskipTests
          docker build -t order-service:${BUILD_NUMBER} .
          docker tag order-service:${BUILD_NUMBER} ${ECR}/order-service:${BUILD_NUMBER}
          docker push ${ECR}/order-service:${BUILD_NUMBER}
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
