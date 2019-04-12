pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh 'mvn clean test'
      }
    }
    stage('Build') {
      steps {
        sh 'mvn clean install'
      }
    }
    stage('Deploy') {
      steps {
        build 'AEM Deploy and Cache Invalidation'
      }
    }
  }
}