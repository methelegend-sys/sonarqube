node {
	stage ('svn checkout'){
		git 'https://github.com/methelegend-sys/fisrt_jenkins'
	}
    stage('SonarQube analysis') {
        withSonarQubeEnv('My SonarQube Server') {
            sh 'mvn clean package sonar:sonar'
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
