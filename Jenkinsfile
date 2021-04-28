pipeline{
        agent any  
        
        
        stages{
		
          stage('svn'){
		  steps{
            git 'https://github.com/methelegend-sys/sonarqube.git'
          	}
		}


              stage('Quality Gate Statuc Check'){

                  steps{
                      script{
                      withSonarQubeEnv('sonarqube') { 
                      bat "mvn sonar:sonar"
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
