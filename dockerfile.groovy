pipeline{
    agent any
    stages {
        stage ('Build'){
            tools {
                maven 'localMaven'
            }
            steps{
                sh 'mvn clean package'
            }
        }
    }
}
