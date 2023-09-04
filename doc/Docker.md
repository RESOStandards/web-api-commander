# RESO Commander and Docker
RESO automated testing tools and Commander utilities can both be run in a Docker containers. 
The containers are slightly different in each case. 

### RESO Automated Testing Tools
A separate file called [GradleDockerfile](../GradleDockerfile) has been provided in order to prepare a Gradle environment for the Commander. This can also be used in CI/CD environments such as Jenkins or TravisCI.

#### Building the Docker Container

In order to build the Docker container for yourself, download the source code with: 

```git clone https://github.com/RESOStandards/web-api-commander.git```

You can [download Docker here](https://www.docker.com/) or [here](https://www.docker.com/products/docker-desktop/) if you prefer the desktop version. Make sure it's running before proceeding. 

To check, you can use:

```docker --version```

At this point, you can build the container: 

```docker build --file GradleDockerfile -t web-api-commander-gradle --no-cache .```

This will create a Docker container caled `web-api-commander-gradle`, which you should be able to see if you type `docker images`:

```
$ docker images
REPOSITORY                 TAG       IMAGE ID       CREATED              SIZE
web-api-commander-gradle   latest    341991b8d352   About a minute ago   1.06GB
...
```

#### Available Tasks

To see available tasks, issue the following command at the terminal:

```
$ docker run -it web-api-commander-gradle
```

#### Running Data Dictionary 1.7 Tests
Now that the Docker container is working, you can run the Data Dictionary tests. 

First you'll need to create the following:
* An empty directory so the certification results may be viewed
* A configuration (RESOScript) file that you can mount in the Docker container

For example: 

```
$ docker run -it -v /path/to/test.resoscript:/test.resoscript -v /path/to/commander-build-tmp:/certification web-api-commander-gradle testDataDictionary_1_7 -DpathToRESOScript=/test.resoscript
```

In the example, replace `/path/to/test.resoscript` and `/path/to/commander-build-tmp` with your local paths. You can then pass the `pathToRESOScript` arg to the commander, as shown above.




### Commander JAR and Utilities
A [Dockerfile](../Dockerfile) has also been provided to Dockerize the application for the Commander utilities accessible through the JAR file.

To run the Commander utilities, use the following commands:

```
$ docker build -t web-api-commander .
```

Once the container is built, you can see available tasks using the following command:

```
$ docker run -it web-api-commander --help
```

If you have input files you may need to mount your filesystem into the docker container

```
$ docker run -it -v $PWD:/app web-api-commander --validateMetadata --inputFile <pathInContainer>
```
