# Digital Contracting Cloud Service Seed
A Spring Boot seed application to be used as a starting point for new cloud based services

## Configuration
1. Configure Maven settings in IntelliJ.
    - Download and install the latest version of Maven or verify your version is >= 3.3.9.
    - Navigate to IntelliJ Settings -> Build, Execution, Deployment -> Maven
    - Check the "Use Plugin Registry" box.
    - Change your Maven home directory to your local version of Maven *instead of* the bundled version. The bundled version is usually not the latest version of Maven.
1. Import the project into IntelliJ. 
    - Import project from external model -> Maven. Click Next.
    - Check every box in the next menu except for "Sources" and "Documentation" which are horizontally aligned near the bottom.
    - Click next until the project loads.
1. Configure Google style settings: https://confluence.cdk.com/display/NAC/How+to+Configure+IntelliJ+Google+Style+Settings

## Running the Project

### As an Application
1. Select Application in the select run/debug configuration.
1. Click the run button.

### as a local Docker container
1. Go to Run -> Edit Configurations.
1. Click the "+" button.
1. Select "Docker->Dockerfile".
1. Name it something if you wish.
1. Enter the Dockerfile path, generally just Dockerfile.
1. Enter a Container name.
1. Enter 8080:8080 in ports.
1. Click the play button and start it up!
1. In your browser go to localhost:8080/health.


## Running Tests
To run all unit tests simply right click on the src/main/test/java and select "Run 'All Tests'". To view the code coverage select "Run 'All Tests' With Coverage".

## Saving to New Repo
In stash, create new repo
```sh
$ git clone ssh://git@stash.cdk.com/odm/dc.cloud.svc.seed.git [project-name]
$ cd [project-name]
```
Follow the directions in the new stash repo under "**My code is already tracked by Git**"

## For Cucumber Reporting
1. Modify src/test/java/com.cdk.dc.hello/CucumberIntegrationTest updating the output filename (dc-cloud-service-seed.json) to match your project. This is important so that the results file for this project don't overwrite another project's file.

    @RunWith(Cucumber.class)
    @CucumberOptions(strict = false, features = "src/test/resources/features", plugin = { "pretty",
        "html:target/site/cucumber-pretty",
        "json:target/dc-cloud-service-seed.json"}, tags = { "not @ignore" })
    
1. Change the tag in each feature file to match your project name. This way in the dashboard your features will be filterable by project.
    
    @dc.cloud.svc.seed    
    Feature: Get greeting
 
 1. Make sure the Bamboo build plan includes "integration-test" in the Maven configuration Goals and also there's an Artifactory Generic Deploy task.
 
 1. Add the following variables to the plan configuration:
 
    artifactory.plankey     SWYSFE
    artifactory.projectkey  ODM
    
## TO DO
1. Add Logging
1. Add Rest Documentation
1. Add External Dependencies
    1. external API with circuit breaker
    1. persistence layer
    1. add both to health/readiness check
# Fortellis-Admin-API
