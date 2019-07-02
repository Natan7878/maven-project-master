pipeline{
    agent any
    stages {
        stage ('Build') {
            tools{
                maven 'localMaven'
            }
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                    echo 'Now Archiving...'
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }

        }
        stage ('Deply to staging ....'){
            steps{
                build job: 'deploy-to-staging'
            }
        }


        stage ('Deploy to Production'){
            steps{
                timeout(time:5, unit: 'DAYS'){
                    input message: 'Approve PRODUCTIONDeployment?', submitter: natan
                }

                build job: 'deploy-to-production'
            }

            post {
                success {
                    echo 'Code deployed to Production'
                }

                failure {
                    echo 'Deployment failed.'
                }
            }
        }

    }
}
