node {
	stage ('svn checkout'){
		git 'https://github.com/methelegend-sys/sonarqube'
	}
    stage('SonarQube analysis') {
        withSonarQubeEnv('sonarqube') {
            bat 'mvn clean package sonar:sonar'
        }
    }
}
stage("Quality Gate") {
        retryForTimeoutExceeded {
            timeout(time: 5, unit: 'MINUTES') {
                def qg = waitForQualityGate() 
                if (qg.status != 'OK') {
                    error "Pipeline aborted due to sonar quality gate failure: ${qg.status}"
                }
            }
        }
    }
