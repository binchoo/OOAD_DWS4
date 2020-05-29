pipeline {
    node('any') {
    // some block
        stage('Git Clone') {
            echo "Cloning Github Repo.."
            scm checkout
        }

        stage('Build & Static Analysis') {
            echo "Build Starts.."
            sh 'chmod +x gradlew; ./gradlew build -x test'
        }

        stage('Unit Test & Coverage') {
            echo "JUnit Test Start, Recording Coverages.."
            sh 'chmod +x gradlew; ./gradlew clean coberturaCheck test'
        }

        stage('Automatable System Test') {
            echo "Not Implemented"
        }
    }
}