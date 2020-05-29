node {
    try {
        notifyBuild('STARTED')

        stage('Fetch') {
                echo 'Cloning Github Repo..'
                checkout scm
            }

        stage('Build & Static Analysis') {
            sh 'chmod +x gradlew; ./gradlew build -x test'
        }

        stage('Unit Test & Coverage') {
            echo 'JUnit Test Start, Recording Coverages..'
            sh 'chmod +x gradlew; ./gradlew clean coberturaCheck test'
        }

        stage('Automatable System Test') {
            echo 'Not Implemented'
        }
    } catch (e) {
        currentBuild.result = "FAILED"
        throw e
    } finally {
        notifyBuild(currentBuild.result)
    }
}

def notifyBuild(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def summary = "${subject} (${env.BUILD_URL})"

  // Override default values based on build status
  if (buildStatus == 'STARTED') {
    color = 'YELLOW'
    colorCode = '#FFFF00'
  } else if (buildStatus == 'SUCCESSFUL') {
    color = 'GREEN'
    colorCode = '#00FF00'
  } else {
    color = 'RED'
    colorCode = '#FF0000'
  }

  slackSend (color: colorCode, message: summary)
}