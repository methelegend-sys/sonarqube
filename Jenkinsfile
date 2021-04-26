node {

    def server
    def buildInfo
    def rtMaven
    def mvnHome = tool 'Maven3'

stage ("checkout")  {
   git 'https://github.com/methelegend-sys/fisrt_jenkins'
}

  stage ('Artifactory configuration') {
    //server = Artifactory.server('artifactory-server');
    //rtMaven = Artifactory.newMavenBuild()
    //rtMaven.tool = 'Maven3'
    //rtMaven.deployer releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local', server: server
    //rtMaven.resolver releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot', server: server
    //rtMaven.deployer.deployArtifacts = false // Disable artifacts deployment during Maven run

    //buildInfo = Artifactory.newBuildInfo()
    rtMaven.tool = 'Maven3' // Tool name from Jenkins configuration
    rtMaven.deployer releaseRepo:'libs-release-local', snapshotRepo:'libs-snapshot-local', server: server
    rtMaven.resolver releaseRepo:'libs-release', snapshotRepo:'libs-snapshot', server: server
    buildInfo = Artifactory.newBuildInfo()
  }
  
   stage ('Build') {
       //bat 'mvn clean install'
        rtMaven.run pom: 'pom.xml', goals: 'clean install', buildInfo: buildInfo
    }
   
    stage ('Upload Artifacts') {
        rtMaven.deployer.deployArtifacts buildInfo
       server.publishBuildInfo buildInfo
    }
}	


