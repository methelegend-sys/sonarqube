node{
    stage('SCM Checkout'){
       git 'https://github.com/methelegend-sys/fisrt_jenkins'
    }
    stage('Build'){
    	sh 'mvn clean install'
    }
}
