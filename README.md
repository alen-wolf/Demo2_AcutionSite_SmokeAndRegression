# Demo2_AcutionSite_SmokeAndRegression

This app was designed to test all the implemented features of the https://abh-auction.herokuapp.com/ app that was made for the AtlantBh Internship

The chromedriver is specifically for windows. For linux users you will have to download the official chrome driver for linux here: https://chromedriver.chromium.org/downloads
Make sure the version is the same as your chrome desktop app

In order to run the tests you will need to have the following installed:
Maven, JDK 8

Maven and JDK 8 have to be configured in the PATH directory and a MAVEN_HOME and JAVA_HOME system variable applied that link to the folder containing their respective bin folders
If you encounter an error such as: YOU MUST BE USING JRE > JDK. Than you need to move the JDK location path in the PATH directory before the oracle/JRE one.

Following commands work both in linux and windows if all the criteria above has been met:

  1. CD inot the project folder 
  2. mvn test -DsuiteXmlFile=testName.xml -DtestNames=groupName
  3. cd \target\surfire-reports
  4. index.html or cat index.html for linux 
  
 
 In the 2nd lin the testName.xml and group name should be replaced with one of the following things you might want to test:
 
 testName.xml:
 
    1. regression.xml
    2. smoke.xml
    
groupName depens on your test.xml:

    1.For regression.xml:
        a) registrationForm
        b) pageInfo
        c) shopCatalog
        d) itemInspection
        e) all (care as this one runs all the tests in multiple drivers at once, and is really heavy on processing power and ram usage)
       
     2.For smoke.xml:
        a)smoke
        b)smokeHeadless
    
