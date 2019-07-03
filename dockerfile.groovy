pipeline{
    agent any
    stages {
        stage ('Build'){
            tools {
                maven 'localMaven'
            }
            steps{
                sh 'mvn clean package'
                sh "docker build . -t tomcatwebapp:${env.BUILD_ID}"
            }
        }
    }
}
