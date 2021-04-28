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
sleep 10
stage("Quality Gate") {
    timeout(time: 1, unit: 'HOURS') { 
        def qg = waitForQualityGate() 
        if (qg.status != 'OK') {
            error "Pipeline aborted due to quality gate failure: ${qg.status}"                
            }
        }
    }
