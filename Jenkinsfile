node {
    stage('Fetch') {
        echo 'Cloning Github Repo..'
        checkout scm
    }

    stage('Build & Static Analysis') {
        echo 'Build Starts..'
        sh 'chmod +x gradlew; ./gradlew build -x test'
    }

    stage('Unit Test & Coverage') {
        echo 'JUnit Test Start, Recording Coverages..'
        sh 'chmod +x gradlew; ./gradlew clean coberturaCheck test'
    }

    stage('Automatable System Test') {
        echo 'Not Implemented'
    }
}