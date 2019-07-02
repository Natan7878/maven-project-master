pipeline{
    agent any
    stages {
        stage ('Build') {
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
        stage ('Deply to staging ....'){
            steps{
                build job: 'deploy-to-staging'
            }
        }


    }
}
