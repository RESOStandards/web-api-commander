# RESO Commander and Docker
Both the command-line and automated testing tools can be run in a Docker container.

A [Dockerfile](./Dockerfile) has been provided to dockerize the application. 
This can be used for CI/CD environments such as Jenkins or TravisCI. The following command will build an image for you:


### Commander Features Other Than Automated Web API Testing
```
$ docker build -t web-api-commander .
```

The usage for the docker container is the same for `web-api-commander.jar` presented above.

```
$ docker run -it web-api-commander --help
```

If you have input files you may need to mount your filesystem into the docker container

```
$ docker run -it -v $PWD:/app web-api-commander --validateMetadata --inputFile <pathInContainer>
```

### Automated Web API Testing

You may also run the tests in a Docker container locally by issuing one of the following commands. 
Docker must be running on your local machine.

#### MacOS or Linux All-In-One Commands
```
cd ~; \
rm -rf commander-tmp/; \
mkdir commander-tmp; \
cd commander-tmp; \
git clone https://github.com/RESOStandards/web-api-commander.git; \
cd web-api-commander; \
docker run --rm -u gradle -v "$PWD":/home/gradle/project -v /path/to/your/resoscripts:/home/gradle/project/resoscripts -w /home/gradle/project gradle gradle testWebAPIServer_1_0_2_Core -DpathToRESOScript=/home/gradle/project/resoscripts/your.web-api-server.core.1.0.2.resoscript -DshowResponses=true
```

Note that this will create a directory in your home directory for the project, and build artifacts and the log will be placed in that directory, 
which is also where you will end up after runtime.


#### Windows All-In-One WIP
```
cd C:\;mkdir commander-tmp;cd commander-tmp;git clone https://github.com/RESOStandards/web-api-commander.git;cd web-api-commander; docker run --rm -u gradle -v C:\current\path\web-api-commander:/home/gradle/project -v C:\path\to\your\resoscripts:/home/gradle/project/resoscripts -w /home/gradle/project gradle gradle testWebAPIServer_1_0_2_Core -DpathToRESOScript=/home/gradle/project/resoscripts/your.web-api-server.core.1.0.2.resoscript -DshowResponses=true
```
