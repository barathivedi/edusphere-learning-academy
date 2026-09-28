pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/barathivedi/edusphere-learning-academy.git'
            }
        }

        stage('Environment Check') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t edusphere:1.0 .'
            }
        }

        stage('Docker Deploy') {
            steps {
                sh 'docker stop edusphere-app || true'
                sh 'docker rm edusphere-app || true'
                sh 'docker run -d --name edusphere-app -p 8080:8080 edusphere:1.0'
            }
        }
    }
}
