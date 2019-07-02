pipeline{
    agent any
    stages {
        stage ('Build'){
            tools {
                maven 'Maven 3.6.1'
            }
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                    echo 'Now Archiving...'
                    archiveArctifacts artifacts: '**/target/*.war'
                }
            }

        }
    }
}
