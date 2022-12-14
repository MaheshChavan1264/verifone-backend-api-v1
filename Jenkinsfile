node {
    def WORKSPACE = "/var/lib/jenkins/workspace/verifone-backend-api-v1"
    def dockerImageTag = "verifone-backend-api-v1-${env.BUILD_NUMBER}"
try{
    notifyBuild('STARTED')
    stage('Clone Repo') {
        // for display purposes
        // Get some code from a GitHub repository
        git url: 'https://github.com/MaheshChavan1264/verifone-backend-api-v1',
            credentialsId: 'Github',
            branch: 'main'
     }
    stage('Build docker') {
         dockerImage = docker.build("verifone-backend-api-v1:${env.BUILD_NUMBER}")
    }
    stage('Deploy docker'){
          echo "Docker Image Tag Name: ${dockerImageTag}"
          pwsh "docker stop verifone-backend-api-v1 && docker rm verifone-backend-api-v1"
          pwsh "docker run --name verifone-backend-api-v1 -d -p 8081:8081 verifone-backend-api-v1:${env.BUILD_NUMBER}"
    }
}catch(e){
    currentBuild.result = "FAILED"
    throw e
}finally{
    notifyBuild(currentBuild.result)
 }
}


def notifyBuild(String buildStatus = 'STARTED'){

  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def now = new Date()

  // message
  def subject = "${buildStatus}, Job: ${env.JOB_NAME} FRONTEND - Deployment Sequence: [${env.BUILD_NUMBER}] "
  def summary = "${subject} - Check On: (${env.BUILD_URL}) - Time: ${now}"
  def subject_email = "Spring boot Deployment"
  def details = """<p>${buildStatus} JOB </p>
    <p>Job: ${env.JOB_NAME} - Deployment Sequence: [${env.BUILD_NUMBER}] - Time: ${now}</p>
    <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME}</a>"</p>"""

  // Email notification
  emailext (
     to: "mahesh.chavan1264@gmail.com",
     subject: subject_email,
     body: details,
     recipientProviders: [[$class: 'DevelopersRecipientProvider']]
  )
}