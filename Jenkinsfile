pipeline{
    stages{
    stage('SCM Checkout'){
        steps{
       git 'https://github.com/methelegend-sys/fisrt_jenkins'
        }
        }
        stage('mvn-Build'){
    	    bat 'mvn clean install'
        }
        stage('Build'){
            steps{
                script{
                    dir("test"){
                        bat 'touch $WORKSPACE/Artifact_$BUILD_NUMBER'
                    }
                }
            }
        }
        stage ('Upload') {
            steps {
                rtUpload (
                    buildName: JOB_NAME,
                    buildNumber: BUILD_NUMBER,
                    serverId: SERVER_ID, // Obtain an Artifactory server instance, defined in Jenkins --> Manage:
                    spec: '''{
                              "files": [
                                 {
                                  "pattern": "$WORKSPACE/Demo-Artifactory/Artifact_*",
                                  "target": "result/",
                                  "recursive": "false"
                                } 
                             ]
                        }'''    
                    )
            }
        }
        stage ('Publish build info') {
            steps {
                rtPublishBuildInfo (
                    buildName: JOB_NAME,
                    buildNumber: BUILD_NUMBER,
                    serverId: SERVER_ID
                )

                rtPublishBuildInfo (
                    buildName: JOB_NAME,
                    buildNumber: BUILD_NUMBER,
                    serverId: SERVER_ID
                )
            }
        }
         stage ('Add interactive promotion') {
            steps {
                rtAddInteractivePromotion (
                    //Mandatory parameter
                    serverId: SERVER_ID,

                    //Optional parameters
                    targetRepo: 'result/',
                    displayName: 'Promote me please',
                    buildName: JOB_NAME,
                    buildNumber: BUILD_NUMBER,
                    comment: 'this is the promotion comment',
                    sourceRepo: 'result/',
                    status: 'Released',
                    includeDependencies: true,
                    failFast: true,
                    copy: true
                )

                rtAddInteractivePromotion (
                    serverId: SERVER_ID,
                    buildName: JOB_NAME,
                    buildNumber: BUILD_NUMBER
                )
            }
         }
         stage ('Removing files') {
            steps {
                bat 'rm -rf $WORKSPACE/*'
            }
        }
        
        
    }
}
