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
def retryForTimeoutExceeded(count = 3, Closure closure) {
    for (int i = 1; i <= count; i++) {
        try {
            closure()
            break
        } catch (FlowInterruptedException error) {
            int retriesLeft = count - i
            def hasTimeoutExceeded = error.causes[0].getClass().toString() == 'class org.jenkinsci.plugins.workflow.steps.TimeoutStepExecution$ExceededTimeout'
            println "Timeout Exceeded for clousre.\nRetries left: $retriesLeft"
            if (retriesLeft == 0 || !hasTimeoutExceeded) {
                throw error
            }
        }
    }
}

stage("Quality Gate") {
        retryForTimeoutExceeded {
            timeout(time: 5, unit: 'MINUTES') {
                // Just in case something goes wrong, pipeline will be killed after a timeout
                def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
                if (qg.status != 'OK') {
                    error "Pipeline aborted due to sonar quality gate failure: ${qg.status}"
                }
            }
        }
    }
