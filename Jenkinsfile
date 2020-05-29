// Powered by Infostretch 

timestamps {

node () {

	stage ('OOAD_DWS4 - Checkout') {
 	 checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'binchoo', url: 'https://github.com/binchoo/OOAD_DWS4']]]) 
	}
	stage ('OOAD_DWS4 - Build') {
 			// Shell build step
sh """ 
chmod +x gradlew; ./gradlew build; 
 """
		// Checkstyle report
		step([$class: 'CheckStylePublisher', canComputeNew: false, defaultEncoding: '', healthy: '90', pattern: '**/checkstyle-output/checkstyle-report.xml', unHealthy: '40']) 
	}
}
}