node {
  stage('SCM') {
    git 'https://github.com/methelegend-sys/fisrt_jenkins.git'
  }
  stage('SonarQube analysis') {
    def scannerHome = tool 'sonar';
    withSonarQubeEnv('My SonarQube Server') { // If you have configured more than one global server connection, you can specify its name
      sh "${scannerHome}/bin/sonar-scanner"
    }
  }
}
