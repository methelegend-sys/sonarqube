node{
    stage('SCM Checkout'){
       git 'https://github.com/methelegend-sys/fisrt_jenkins'
    }
    stage('Build'){
    	bat 'mvn clean install'
    }
}
