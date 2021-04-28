pipeline{
        agent any  
        
        
        stages{
		steps{
          stage('svn'){
            git 'https://github.com/methelegend-sys/fisrt_jenkins.git'
          }
		}


              stage('Quality Gate Statuc Check'){

                  steps{
                      script{
                      withSonarQubeEnv('sonarqube') { 
                      sh "mvn sonar:sonar"
                       }
                      timeout(time: 1, unit: 'HOURS') {
                      def qg = waitForQualityGate()
                      if (qg.status != 'OK') {
                           error "Pipeline aborted due to quality gate failure: ${qg.status}"
                      }
                    }
		                bat "mvn clean install"
                  }
                }  
              }
	}
}
