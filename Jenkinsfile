pipeline {
    agent any
    stages{
        stage ('Build') {
            steps {
                git 'https://github.com/alen-wolf/Demo2_AcutionSite_SmokeAndRegression.git'
            }
        }
        stage ('Smoke Test') {
            steps{
                withMaven (maven : 'Maven') {
                  bat "mvn test -DsuiteXmlFile=smoke.xml -DtestNames=smokeHeadless"
                }
            }
        }
        stage ('Registration Tests') {
            steps{
                withMaven (maven : 'Maven') {
                  bat "mvn test -DsuiteXmlFile=regression.xml -DtestNames=registrationForm"
                }
            }
        }
        stage ('Shop Catalog Tests') {
            steps{
                withMaven (maven : 'Maven') {
                  bat "mvn test -DsuiteXmlFile=regression.xml -DtestNames=shopCatalog"
                }
            }
        }
        stage ('Item Inspection Tests') {
            steps{
                withMaven (maven : 'Maven') {
                  bat "mvn test -DsuiteXmlFile=regression.xml -DtestNames=itemInspection"
                }
            }
        }
        stage ('Info Tests') {
            steps{
                withMaven (maven : 'Maven') {
                  bat "mvn test -DsuiteXmlFile=regression.xml -DtestNames=pageInfo"
                }
            }
        }
    }
}
