# RESO Commander and Docker
RESO automated testing tools and Commander utilities can both be run in a Docker containers. 
The containers are slightly different in each case. 

### RESO Automated Testing Tools
A [GradleDockerfile](../GradleDockerfile) has been provided in order to prepare a Gradle 
environment for the Commander. The container builds itself from the main branch of the source code, so you don't need
the entire repo checked out locally, just the file.

This can also be used in CI/CD environments such as Jenkins or TravisCI.

Run the RESO Certification tests in a Docker container locally by issuing one of the following commands.
Docker must be running on your local machine. 

One way to do this is to build the container first and then run it:

```docker build --file GradleDockerfile -t web-api-commander-gradle .```

Once the container is built, you can use the Gradle commands normally with:
```docker run -it web-api-commander-gradle testWebApiCore_2_0_0 -DpathToRESOScript=/home/gradle/project/resoscripts/your.resoscript -DshowResponses=true```

You can also build the container on the fly:

```docker run --rm -it -v "$PWD":/home/gradle/project -v /path/to/your/resoscripts:/home/gradle/project/resoscripts -w /home/gradle/project -it $(docker build -f GradleDockerfile -q .) testWebApiCore_2_0_0 -DpathToRESOScript=/home/gradle/project/resoscripts/your.resoscript -DshowResponses=true```

Note that this will create a directory in your home directory for the project, and build artifacts and the log will be placed in that directory,
which is also where you will end up after runtime.

You may need to adjust the path separators if using Windows.


### Commander Utilities
A [Dockerfile](../Dockerfile) has also been provided to Dockerize the application for Commander utilities.

To run the Commander utilities, use the following commands:

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
