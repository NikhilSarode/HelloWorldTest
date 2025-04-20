pipeline {
    agent any

    tools {
        gradle 'Gradle 8.6' // The name defined in Jenkins Global Tool Configuration
	jdk 'jdk-21'
    }

    environment {
        JAVA_HOME = "${tool 'jdk-21'}"
        PATH = "${JAVA_HOME}\\bin;${env.PATH}"
        IMAGE_NAME = 'helloworldtest'
        CONTAINER_NAME = 'helloworldtest-container'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/NikhilSarode/HelloWorldTest.git'
            }
        }

        stage('Build') {
            steps {
                bat '.\\gradlew.bat clean build'
            }
        }

        stage('Test') {
            steps {
                bat '.\\gradlew.bat test'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t %IMAGE_NAME% .'
            }
        }

        stage('Docker Run') {
    		steps {
       			bat """
        		docker rm -f %CONTAINER_NAME% || exit 0
        		docker run -d --name %CONTAINER_NAME% -p 8081:8080 %IMAGE_NAME%
        		"""
    		}
	    }
    }

    post {
        success {
            echo '✅ App built and Docker container is running.'
        }
        failure {
            echo '❌ Build or container setup failed.'
        }
    }
}