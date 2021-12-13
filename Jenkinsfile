pipeline{
    agent any
    stages{
        stage("Clone"){
            steps{
                echo "========executing Clone========"
                git 'https://github.com/methelegend-sys/sonarqube.git'
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
      stage('OWSAP Dependency Check')
        {
            steps
            {
                echo "Running OWSAP Dependency Check"
                bat 'bash owasp-dependency-check.sh'
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
