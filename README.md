# opencart
web automation devops setup. The project uses containers built from various browser images set into a grid where tests can be run parallely. The project can be setup and run by following the below steps:

1. The first step is to set the hub and node environment. There are many approaches using docker like docker network, swarm, etc...I chose to use docker compose which uses yaml. Link for the yaml is below:
  https://github.com/SeleniumHQ/docker-selenium/blob/trunk/docker-compose-v3.yml
  for more info, do check https://github.com/SeleniumHQ/docker-selenium
2. Download the yaml file and change the versions of all the images to latest so it can look something like this:
   ![image](https://github.com/vinayviga/opencart/assets/10867207/c011ea4a-14fc-4a6a-a28a-e49adeedeed6)
3. Run the yaml file, # To execute this docker-compose yml file use `docker-compose -f docker-compose-v3.yml up`
   Add the `-d` flag at the end for detached execution.
4. Use this url to check whether the setup is running fine or not http://localhost:4444/ui
5. In case if you want more nodes for a particular browser, use the scale command in a seperate terminal/cmd instance: 
  docker-compose scale <browser_name>=<total number of browser nodes needed including the one already present>, ex: docker-compose scale chrome=3
6. Clone the code and do an mvn clean test to run the tests
7. after test runs, To stop the execution, hit Ctrl+C, and then `docker-compose down` in the location of yaml file.

All the above steps can be turned into commands and can be run from a .bat or .sh files to automate the whole process to save time and effort(Laziness :stuck_out_tongue:)

