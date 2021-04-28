pipeline{
    agent any
    stages{
        stage("Clone"){
            steps{
                echo "========executing Clone========"
                git 'https://github.com/methelegend-sys/fisrt_jenkins.git'
            }
            post{
                always{
                    echo "========always========"
                }
                success{
                    echo "========Clone executed successfully========"
                }
                failure{
                    echo "========Clone execution failed========"
                }
            }
        }
		
    	stage('SonarQube analysis') {
		steps{
        	withSonarQubeEnv('sonarqube') {
            		bat 'mvn clean package sonar:sonar'
        	}
		}
    	}
	sleep 10
	stage("Quality Gate") {
		steps{
    			timeout(time: 1, unit: 'HOURS') { 
        			def qg = waitForQualityGate() 
        			if (qg.status != 'OK') {
            				error "Pipeline aborted due to quality gate failure: ${qg.status}"                
            			}
        		}
		}
    	}
        stage("Initialize Artifactory"){
            steps{
                echo "====++++executing Initialize Artifactory++++===="
                rtServer (
                    id: 'Artifactory-Server',
                    url: 'http://localhost:8081/artifactory',
                    credentialsId: 'ArtifactoryLocal',
                    timeout: 300
                )
                rtMavenDeployer (
                    id: 'MAVEN_DEPLOYER',
                    releaseRepo: 'maven-release',
                    snapshotRepo: 'maven-snapshot',
                    serverId: 'Artifactory-Server'
                )
                // rtMavenResolver (
                //     id: "MAVEN_RESOLVER",
                //     serverId: "ARTIFACTORY_SERVER",
                //     releaseRepo: "local",
                //     snapshotRepo: "local"
                // )
            }
            post{
                always{
                    echo "====++++always++++===="
                }
                success{
                    echo "====++++Initialize Artifactory executed successfully++++===="
                }
                failure{
                    echo "====++++Initialize Artifactory execution failed++++===="
                }
        
            }
        }
        stage("Build"){
            steps{
                echo "====++++executing Build++++===="
                rtMavenRun (
                    tool: "Maven_3", // Tool name from Jenkins configuration
                    pom: 'myapp/pom.xml',
                    goals: 'clean install',
                    deployerId: "MAVEN_DEPLOYER",
                    // resolverId: "MAVEN_RESOLVER"
                )
                
            }
            post{
                always{
                    echo "====++++always++++===="
                }
                success{
                    echo "====++++Build executed successfully++++===="
                }
                failure{
                    echo "====++++Build execution failed++++===="
                }
        
            }
        }
        stage("Upload Artifacts"){
            steps{
                echo "====++++executing Upload Artifacts++++===="
                rtPublishBuildInfo (
                    serverId: "Artifactory-Server"
                )
            }
            post{
                always{
                    echo "====++++always++++===="
                }
                success{
                    echo "====++++Upload Artifacts executed successfully++++===="
                }
                failure{
                    echo "====++++Upload Artifacts execution failed++++===="
                }
        
            }
        }
    }
    post{
        always{
            echo "========always========"
        }
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}
