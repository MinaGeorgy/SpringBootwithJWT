# 1 Description
This is the mytaxi Server Applicant Test. Your task description should be included in the email you got from our Career-Team, good luck!

# 2 How to start
* open/import the project in your favorite IDE
* execute com.mytaxi.MytaxiServerApplicantTestApplication::main to start an embedded http server
* open http://localhost:8080/ to see documentation and test existing endpoints

# 3 Endpoints
* / will be shown the API documentaion an a http test client
    * in this overview you can also read about the existing APIs /v1/drivers... and test it - the important one is 
    the *driver-controller*
* /h2-console will give you the ability to check the existing and new created data in the db #dbclient
    * Saved Settings: Generic H2
    * Setting Name: Generic H2
    * Driver Class: org.h2.Driver
    * jdbc URL: jdbc:h2:mem:testdb
    * User Name: sa
    * Password: <empty>
    
