pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn clean test'
      }
    }
    stage('Deploy') {
      steps {
        build 'AEM Deploy and Cache Invalidation'
      }
    }
  }
}