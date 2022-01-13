pipeline {
    agent any
    stages{
        stage ('git') {
            steps {
                git 'https://github.com/alen-wolf/Demo2_AcutionSite_SmokeAndRegression.git'
            }
        }
        stage ('Build') {
            steps{
                withMaven (maven : 'Maven') {
                  bat "mvn test -DsuiteXmlFile=smoke.xml -DtestNames=smokeHeadless"
                }
            }
        }
        stage ('infoTest') {
            steps{
                withMaven (maven : 'Maven') {
                  bat "mvn test -DsuiteXmlFile=regression.xml -DtestNames=pageInfo"
                }
            }
        }
        stage ('registrationTest') {
            steps{
                withMaven (maven : 'Maven') {
                  bat "mvn test -DsuiteXmlFile=regression.xml -DtestNames=registrationForm"
                }
            }
        }
    }
}
