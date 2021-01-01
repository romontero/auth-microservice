pipeline {
   agent any

   stages {
      stage('test') {
         agent {
            docker {
                image 'arm32v7/gradle:6.7-jdk11'
            }
         }
         steps {
            sh './gradlew clean build bootjar'
         }
      }
   }
}