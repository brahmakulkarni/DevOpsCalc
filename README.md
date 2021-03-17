# CS 816 - Software Production Engineering: Mini Project

Author: Brahma Kulkarni
Roll Number: IMT2017011

# Introduction

In this Mini Project, we were tasked with creating a simple scientific calculator application using the DevOps methodology. The following were the functions implemented in the code of my scientific calculator:

- Square root function - √$x$
- Factorial function - $x!$
- Natural logarithm (base е) - $ln(x)$
- Power function - $x^b$

### Tools used

1. **IntelliJ:** was used as the IDE of choice to avail various features like its integration with git, maven, JUnit, etc. Using this made the process of coding a breeze.
2. **Git:** was the Source Control Management tool of choice. The following is the link to my git repository: [https://github.com/brahmakulkarni/DevOpsCalc](https://github.com/brahmakulkarni/DevOpsCalc)
3. **Maven:** was the build tool of choice.
4. **Jenkins:** was the Continuous Integration tool of choice.
5. **Docker and Docker Hub:** were the containerization tools of choice. Docker was used to do the actual containerization, whereas Docker Hub was the online repository to which the image of the docker containers was pushed. Here is the link to the same: [https://hub.docker.com/repository/docker/brahma99/calcdevops](https://hub.docker.com/repository/docker/brahma99/calcdevops)
6. **Ansible:** was the tool used to deploy the application to the nodes (only [localhost](http://localhost) in my case)
7. **Log4j:** was the tools used to generate the logs of my application. These logs are fed as input to the ELK stack to perform monitoring on.
8. **ELK stack:** Elasticsearch, Logstash and Kibana were used to do Monitoring.

# What is DevOps and why it is used?

DevOps is just truncated form of development and operations. Historically, developers and operations teams have been separate entities. Developers generally want to make changes rapidly while the operations team seeks organizational stability. DevOps aims to bring all the teams close so that they can work together and be on the same page.

Before the advent of DevOps, the two teams functioned disjointly. Because of this, there used to be a lot issues that came up due to a lack of communication. This would lead to pointing fingers at each other. So, instead attempting to resolve the issue, more time is wasted on determining whose fault it was. Also, as a result of operating separately, the two teams might be using completely different tools for different stages. Such discrepancies will lead to further slowing down of the entire pipeline. As we can see the earlier models seem pretty inefficient.

By employing the DevOps methodology, we can automate and quicken the whole process. After making an approved change in the codebase, we could set up the project in such a way that, a git push would trigger the rest of the pipeline (building, testing, deploying and monitoring). Also, due to continuous monitoring, the feedback is also brought back to the teams quickly, enabling bug fixes and new features to be coded quickly and efficiently.

NOTE: My entire report is specific to Linux users. To be more specific, I am using Ubuntu 20.04.

# Prerequisites

- Install git on your computer
- Install openjdk8. If you have multiple versions of java installed, you can switch to java using the following command on your terminal:

```bash
sudo update-alternatives --config java
```

- Install IntelliJ All IIITB students can get the premium version for free.
- Install Jenkins. Use this link to do the same: [https://www.digitalocean.com/community/tutorials/how-to-install-jenkins-on-ubuntu-18-04](https://www.digitalocean.com/community/tutorials/how-to-install-jenkins-on-ubuntu-18-04). After installing Jenkins, follow the steps given in the following link to complete the setup of Jenkins: [https://www.digitalocean.com/community/tutorials/how-to-install-jenkins-on-ubuntu-18-04#step-4-—-setting-up-jenkins](https://www.digitalocean.com/community/tutorials/how-to-install-jenkins-on-ubuntu-18-04#step-4-%E2%80%94-setting-up-jenkins)
- Install Maven using the following commands:

```bash
sudo apt update
sudo apt install maven
```

- Install docker. Follow the following commands:

```bash
Installing Docker on Ubuntu 18/20
Step 1
sudo apt install apt-transport-https ca-certificates curl software-properties-common
Step 2
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
Step 3
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu  $(lsb_release -cs)  stable"
Step 4
sudo apt update
Step 5
sudo apt install docker-ce
Step 6 - Verify docker is running
sudo systemctl status docker

To add your user and Jenkins to docker group
sudo usermod -aG docker username
sudo usermod -aG docker jenkins (this needs to be done for seamless integration of docker and jenkins)
```

- Create an account on [https://hub.docker.com/](https://hub.docker.com/).
- Install and setup Ansible using the following commands:

```bash
sudo apt install openssh-server
ssh-keygen -t rsa

sudo apt update
sudo apt install ansible

check your ansible version using
ansible --version

check your ansible_python_version using
ansible -m setup localhost | grep ansible_python_version
```

- Use the following link to download Elastic Search: [https://www.elastic.co/downloads/elasticsearch](https://www.elastic.co/downloads/elasticsearch)
- Use the following link to download Logstash: [https://www.elastic.co/downloads/logstash](https://www.elastic.co/downloads/logstash)
- Use the following link to download Kibana: [https://www.elastic.co/downloads/kibana](https://www.elastic.co/downloads/kibana)

# Coding

The editor of choice was IntelliJ. This is good because of its very convenient integration with Maven, Git, JUnit, etc. This makes the process of coding seamless and quick and overall. Shown below is the directory structure created when a Maven project is created:

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/intellij_dir_structure.png](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/intellij_dir_structure.png)

To create a Maven project in IntelliJ, go to File → New → Project and press enter. Then select Maven in the side-panel, choose the appropriate version of Java and click next. In the next screen, provide the project name and select the location where you want the project to be created. Pressing "Finish" will create your project. 

Shown below is the code of my application:

```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        int toggle = 0;
        do {
            System.out.println("Enter the option you want to choose:");
            System.out.println("1. Square Root");
            System.out.println("2. Factorial (of an integer)");
            System.out.println("3. Natural logarithm");
            System.out.println("4. Power function");
            System.out.println("5. Leave");

            Scanner in = new Scanner(System.in);
            int option = in.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter the number: ");
                    double x = in.nextDouble();
                    System.out.println("The Square Root of "+ x + " is: "+ squareRoot(x));
                    break;
                case 2:
                    System.out.println("Enter the number (an integer): ");
                    int y = in.nextInt();
                    System.out.println("The Factorial of "+ y + " is: "+ factorial(y));
                    break;
                case 3:
                    System.out.println("Enter the number: ");
                    double z = in.nextDouble();
                    System.out.println("The Natural Logarithm of "+ z + " is: "+ nLog(z));
                    break;
                case 4:
                    System.out.println("Enter the base: ");
                    double a = in.nextDouble();
                    System.out.println("Enter the exponent: ");
                    double b = in.nextDouble();
                    System.out.println(a + " raised to the power of "+ b + " is: "+ power(a,b));
                    break;
                case 5:
                    System.out.println("Fin");
                    toggle = 1;
                    break;
                default:
                    System.out.println("Invalid Option. Please try again.");
            }
        } while (toggle == 0);

    }

    private static final Logger logger = LogManager.getLogger(Calculator.class);

    public static double squareRoot(double num) {
        logger.info("Performing square root operation on "+num);
        double ret =  Math.sqrt(num);
        logger.info("Result of square root operation on "+num+" is: "+ret);
        return ret;
    }

    public static int factorial(int num) {
        logger.info("Performing factorial operation on "+num);
        int ret = 1;
        for (int i = 1; i <= num; i++) {
            ret *= i;
        }
        logger.info("Result of factorial operation on "+num+" is: "+ret);
        return ret;
    }

    public static double nLog(double num) {
        logger.info("Performing natural logarithm operation on "+num);
        double ret = Math.log(num);
        logger.info("Result of natural logarithm operation on "+num+" is: "+ret);
        return ret;
    }

    public static double power(double x, double y) {
        logger.info("Performing power operation on "+ x + "," + y);
        double ret =  Math.pow(x,y);
        logger.info("Result of power operation on "+ x + "," + y + " is: " + ret);
        return ret;
    }
}
```

# Source Control Management

Source control management is used to keep track of various versions of code under development. Commits (and their IDs) are used to keep track of each version. Additionally, using SCM, we can set up plugins in Jenkins (our continuous integration tool of choice), to pull code directly from my git repository.

### Creating repository without having developed code

- Go to your GitHub account.
- Click on the "+" symbol on the top-right.
- Select "New repository"
- Create a repository as shown in the image below. For this project, I made my repository public

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/git_repo.png](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/git_repo.png)

- After clicking the "Create repository" button, follow the instructions on the resulting page to set a local repository for the created GitHub repository on your local computer.

### Creating a repository after having developed some amount of code

Follow the same process (as shown in the section above) to create a repository on GitHub. After that, follow the following commands by opening a terminal in the directory of the project that you're developing:

```bash
git init ./
git remote add origin <git_repo_url>
git add .
git commit -m "Initial commit"
git push -u origin master (or main depending on whether you renamed your master branch)
```

After this, whenever you make any changes to your code and want to push the changes to the remote repository (GitHub repository), use the following commands:

```bash
git add <the_files_that_you_changed>
git commit -m "<commit_message>"
git push -u origin master (or main depending on whether you renamed your master branch)
```

# Building

The Build tool of choice used was Maven. Maven is a project management tool. However, its most popular uses are for build and dependency management. In a Maven project, there is a file called "pom.xml". This is the configuration file for the Maven project. This file houses project metadata, dependencies, and plugins. 

In this project, we used the dependencies of JUnit and Log4j. JUnit dependency is used for testing the code and Log4j dependencies are used to generate and store logs of the code execution. In addition, when using Log4j, it requires us to provide the JAR file of Log4j (as it is an external dependency) as part of the command when running the JAR file of our packaged code. Alternatively, I wrote a plugin in my pom.xml file that automatically creates the packaged JAR file of my code by roping in all of the external dependencies as well. As the plugin that I wrote is for the "package" phase, the jar file is created using the following command:

```bash
mvn clean package
```

The package phase also encompasses the Maven test phase. Hence, just running the above command will also run the test cases. If any of the test cases fail, the execution will be stopped, we will be notified and the JAR file won't be created.

Shown below is my pom.xml:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>DevOpsCalc</artifactId>
    <version>1.0-SNAPSHOT</version>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                        <configuration>
                            <archive>
                                <manifest>
                                    <mainClass>calculator.Calculator</mainClass>
                                </manifest>
                            </archive>
                            <descriptorRefs>
                                <descriptorRef>jar-with-dependencies</descriptorRef>
                            </descriptorRefs>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.14.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.14.0</version>
        </dependency>
    </dependencies>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
</project>
```

# Testing

It is important to test a codebase thoroughly before deploying. This ensures that the application is working the way it's supposed. For this JUnit was used. I wrote test cases for both true positive cases (when the functions return the expected output and the test case is meant to verify the output is correct) and true negative cases (when the functions returns an incorrect output and the test case is meant to verify the output is false). Shown below is the code from my CalculatorTest.java file:

```java
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CalculatorTest {
    @Test
    public void sqrtTestTP() {
        double ret = Calculator.squareRoot(4);
        assertEquals(2,ret, 0.0f);
    }

    @Test
    public void sqrtTestTN() {
        double ret = Calculator.squareRoot(16);
        assertNotEquals(3,ret, 0.0f);
    }

    @Test
    public void factorialTestTP() {
        int ret = Calculator.factorial(4);
        assertEquals(24,ret);
    }

    @Test
    public void factorialTestTN() {
        int ret = Calculator.factorial(3);
        assertNotEquals(7,ret);
    }

    @Test
    public void nLogTestTP() {
        double ret = Calculator.nLog(2.718);
        assertEquals(1,ret, 0.2f);
    }

    @Test
    public void nLogTestTN() {
        double ret = Calculator.nLog(2.718);
        assertNotEquals(2,ret, 0.2f);
    }

    @Test
    public void powerTestTP() {
        double ret = Calculator.power(2, 2);
        assertEquals(4,ret, 0.0f);
    }

    @Test
    public void powerTestTN() {
        double ret = Calculator.power(2, 2);
        assertNotEquals(5,ret, 0.0f);
    }
}
```

# Continuous Integration

Continuous Integration is the process of various members working on a project continuously/frequently integrate their work with the work of others. The continuous integration tool that I used was Jenkins. Jenkins has various plugins for git, GitHub, Maven, Docker and Ansible, all of which we are using in this project. These plugins help with the seamless integration of all these tools and enable Jenkins to automate the whole pipeline with the tap of a single button. Plugins can be installed by going to Manage Jenkins → Manage Plugins ("Available" tab).

## Creating a Jenkins pipeline project

- On the main Jenkins Dashboard, click on "New Item"
- Select Pipeline and provide a project name to create a new Pipeline project. This is shown below:

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/jenkins_pipeline_creation.png](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/jenkins_pipeline_creation.png)

## Pipeline creation

- If you just created the pipeline project, scroll down to the Pipeline section.
- This is where we write the pipeline script. The pipeline script elaborates on the stages and steps that need to be followed to integrate various stages of the whole DevOps methodology.

Shown below is my pipeline script:

```tsx
pipeline {
    environment {
        dockerImage = ""
    }
    agent any

    stages {
        stage('Git pull') {
            steps {
                git 'https://github.com/brahmakulkarni/DevOpsCalc.git'
            }
        }
        stage('Build and test') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Building docker image') {
            steps {
                script {
                    dockerImage = docker.build "brahma99/calcdevops:latest"
                }
            }
        }
        stage('Push Docker image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('', 'docker-jenkins') {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Deployment using Ansible') {
            steps {
                ansiblePlaybook becomeUser: null, colorized: true, disableHostKeyChecking: true, installation: 'Ansible', inventory: 'deploy-docker/inventory', playbook: 'deploy-docker/deploy_image.yml', sudoUser: null
            }
        }
    }
}
```

## Global Tool Configurations, Managing Credentials, and more

- To globally configure Ansible in Jenkins, go to Dashboard → Manage Jenkins → Global Tool Configuration.
- Scroll down to the Ansible section and select add Ansible.
- Fill it in as shown below:

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/ansible_setup_jenkins.png](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/ansible_setup_jenkins.png)

- To fill the field "Path to ansible executables directory", open a terminal on your computer and enter the command:

```bash
whereis ansible
```

- To ensure smooth operation between Docker Hub and Jenkins, we must save our Docker Hub credentials on Jenkins. To do so, go to Dashboard → Manage Jenkins → Manage Credentials.
- Now under "Stores scoped to Jenkins", click on global and then on Add credentials.
- Then select "Username with password" in the "Kind" field.
- Then fill in the details as shown below:

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/add_credentials.png](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/add_credentials.png)

- We also need to make it possible for Jenkins to be able to login into our remoteuser without the use of a password. For this, follow the commands given below:

```bash
sudo su - jenkins (this logs us into the jenkins user)
ssh-keygen -t rsa (generates ssh keys)
ssh-copy-id REMOTEUSER@<REMOTE IP ADDRESS> (eg. ssh-copy-id brahma@localhost)
(This last line will copy the remote ssh keys to the known hosts in Jenkins, enabling Jenkins to login to the remoteuser without needing a password)
```

## Generating pipeline syntax

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/pipeline_syntax_annotated.jpeg](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/pipeline_syntax_annotated.jpeg)

- For the git pipeline syntax, press the "Pipeline Syntax" as shown above. In "Sample Step", select the "git: Git" option in the pull-down menu and fill in the other details and click on generate pipeline syntax. If your repository is private, you can add your git credentials here too. This is shown below:

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/git_pipeline.png](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/git_pipeline.png)

- The pipeline syntax for Maven, Docker, and Docker Hub stages was written manually and can be seen in the pipeline syntax. The docker credentials we created is used in the Docker Hub stage as reflected in the pipeline syntax and is also shown below:

```tsx
stage('Push Docker image to Docker Hub') {
    steps {
        script {
            docker.withRegistry('', 'docker-jenkins') {
                dockerImage.push()
            }
        }
    }
}
```

- To generate the pipeline syntax for the ansible stage, press the "Pipeline Syntax" button. In "Sample Step", select the "ansiblePlaybook: Invoke an ansible playbook" option in the pull-down menu and fill in the other details and click on generate pipeline syntax. This is shown below:

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/ansible_syntax.png](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/ansible_syntax.png)

As you can see, the playbook file (.yml file) and inventory file are inside a deploy-docker folder in the project directory. This was also seen in the directory structure shown before.

# Docker Containerization and Image pushing to Docker Hub

The containerization tool of choice used was Docker. Docker creates containers that are basically visualizations at the Operating System level. Docker containers can be thought of as light-weight virtual machines. They have smaller memory footprints when compared to virtual machines as VMs have an entire copy of an operating system while docker containers have the bare only bare minimum facilities like a root file system and an interactive terminal. Docker containers help in isolation. Whatever is put within a docker container is completely independent, isolated, and can be tended to separately.

In this project, the jar file of the application we developed is pushed to a Docker container. Then a Docker Image is built of this container, with the help of a "Dockerfile" that we create, and pushed to Docker Hub. All of this is done by specifying the same in the Jenkins pipeline as shown before. The Dockerfile is shown below:

```docker
FROM openjdk:8
COPY ./target/DevOpsCalc-1.0-SNAPSHOT-jar-with-dependencies.jar ./
WORKDIR ./
CMD ["java", "-cp", "DevOpsCalc-1.0-SNAPSHOT-jar-with-dependencies.jar", "Calculator"]
```

# Continuous Deployment using Ansible

Continuous deployment is the process of automating the process of deploying applications to multiple client machines. Continuous deployment enables developers to quickly make the application available to their clients in its current state. Their feedback is quickly brought back to developers and they can fix bugs/add new features, and the cycle continues.

Ansible is actually a configuration management tool. However, it can also be used to push anything to multiple "managed hosts". According to Ansible, there are two types of machines: control nodes and managed hosts. Ansible is installed and run from the control node. A control node houses all the copies of your Ansible project files and configuration information. Managed hosts are systems to which the application/infrastructure as code is pushed. Ansible acts as a central distributor from the control node to all the managed hosts. The managed hosts are listed in the inventory file.

In this project, I simply used Ansible to pull the Docker image from Docker Hub and push it to my local system. For the purpose of this project, I only used one managed host, which was my local machine. Shown below is the ansible-playbook file (deploy_image.yml):

```yaml
---
- name: Pull Docker image of DevOpsCalc
  hosts: all
  vars:
    ansible_python_interpreter: /usr/bin/python3
  tasks:
    - name: Pull image calcdevops image
      docker_image:
        name: brahma99/calcdevops
        source: pull
```

Observe the following line:

```yaml
vars:
  ansible_python_interpreter: /usr/bin/python3
```

This line was needed to point to the correct python version (when there are multiple versions of python installed). To know which, python version to point to, check the ansible_python_version using:

```bash
ansible -m setup localhost | grep ansible_python_version
```

If the version is 3.x, specify /usr/bin/python3 (as in my case). Otherwise, if the ansible_python_version is 2.x, specify /usr/bin/python instead.

The inventory file is shown below:

```bash
localhost ansible_user=brahma
```

# Logging using Log4j

Logging is an important part of any development process. It keeps track of all the operations that were performed in the application, errors, warnings, debugging information, etc. To generate the logs, I used the log4j tool. This has a convenient plugin in Maven for seamless operations. This is seen in the pom.xml shared earlier. We also need to create a "log4j2.xml" file. This file provides the structure based on which the logs should be generated. My log4j2.xml is shown below:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="INFO">
    <Appenders>
        <Console name="ConsoleAppender" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{dd/MMM/yyy:HH:mm:ss SSS} [%F] [%level] %logger{36} %msg%n"/>
        </Console>
        <File name="FileAppender" fileName="calculator.log" immediateFlush="false" append="true">
            <PatternLayout pattern="%d{dd/MMM/yyy:HH:mm:ss SSS} [%F] [%level] %logger{36} %msg%n"/>
        </File>
    </Appenders>
    <Loggers>
        <Root level="debug">
<!--            <AppenderRef ref="ConsoleAppender"/>-->
            <AppenderRef ref="FileAppender"/>
        </Root>
    </Loggers>
</Configuration>
```

In addition to this, we need to add some lines of code in our main application file ([Calculator.java](http://calculator.java) in my case) so as to actually generate the logs. This involves steps like initializing a logger object and provide customized messages, at various places, that need to be logged in addition to the boilerplate structure provided by us in the log4j2.xml. These lines of code can be seen in an earlier section where I shared my Calculator.java code.

An example of a log file is shown below:

```
17/Mar/2021:06:07:10 957 [Calculator.java] [INFO] Calculator Performing square root operation on 64.0
17/Mar/2021:06:07:10 980 [Calculator.java] [INFO] Calculator Result of square root operation on 64.0 is: 8.0
17/Mar/2021:06:07:15 289 [Calculator.java] [INFO] Calculator Performing factorial operation on 3
17/Mar/2021:06:07:15 290 [Calculator.java] [INFO] Calculator Result of factorial operation on 3 is: 6
17/Mar/2021:06:07:18 061 [Calculator.java] [INFO] Calculator Performing natural logarithm operation on 10.0
17/Mar/2021:06:07:18 062 [Calculator.java] [INFO] Calculator Result of natural logarithm operation on 10.0 is: 2.302585092994046
17/Mar/2021:06:07:34 631 [Calculator.java] [INFO] Calculator Performing power operation on 2.0,3.0
17/Mar/2021:06:07:34 632 [Calculator.java] [INFO] Calculator Result of power operation on 2.0,3.0 is: 8.0
```

# Running the application on the local computer, retrieving the log file from Docker container on local machine

Before getting to the crux of this section, let us have a short recap. The following are all the stages in my Jenkins pipeline:

1. Git pull
2. Building and Testing using Maven
3. Containerizing the application JAR file and building a Docker image of it
4. Pushing the Docker image to Docker Hub
5. Pulling the Docker image from Docker Hub and pushing to all managed hosts using Ansible

Having done all this, now, the docker image has been pulled to my local computer. I will run this Docker image on my laptop and run through the application and its various options so as to generate some logs. Follow the commands given below to run the application using the Docker image:

```bash
docker images (This is to view all docker images. Use the latest image in the next command)
docker run -it brahma99/calcdevops
```

Now just try out the application and perform some operations to generate the log file. However, after we close the application, the container automatically exits and the log file remains in the docker container. Hence, we need to copy the log file to our laptop. Follow the following commands to do the same:

```bash
docker ps -a (This command lists all the latest containers. Use the container id of the most recently closed container for the next step)
docker copy <container-id>:<path-to-log-file-in-container> <dest-path-on-local-machine>
```

Having copied the log file to our local machine, we can now use this with the ELK stack for monitoring.

# Monitioring using ELK stack

ELK stands for ElasticSearch, Logstash and Kibana. These three form a stack of sorts, hence the name ELK "stack". They follow the following pipeline:

1. Logstash: collects logs from various sources and user filters like grok to parse the logs and organize them in fields defined by us and how we want to parse the logs.
2. ElasticSearch: is a search and analytics engine that takes input from Logstash and sifts through the preprocessed logs.
3. Kibana: is a visualization engine that runs on top of ElasticSearch. This is used to visualize various aspects of the logs and gain insights that we might not make by just looking at the logs in plaintext form.

The download and set up of the ELK stack was discussed in the Prerequisites section. Before actually running the ELK stack it is important to note that all three of these tools are pretty heavy. For example, ElasticSearch itself uses up around 8.3 GB of RAM when running. Hence, it is recommended that you run it on the cloud or by increasing your swap space (videos for the same can be found on YouTube). I had 8 GB of RAM. Just to be safe, I made my swap space 8 GB too leaving me with a total of around 16 GB to play with.

Logstash requires a configuration file (.conf file). This file majorly has three sections: Input, Filter and Output. Input deals with providing the log files as input. In my case, I just provide the path to my log file. The filter section is where the parsing happens. For example, the grok plugin uses regular expressions to match expressions in the logs to patterns we are looking for and map them in fields defined by us. The output section sends the preprocessed logs to a particular destination (ElasticSearch in my case) and also sets an index name that will be useful for ElasticSearch and Kibana. My configuration file is shown below (calculator.conf):

```tsx
input {
  file {
    path => "/home/brahma/IIITB/8th\ sem/SPE/calculator.log"
    start_position => "beginning"
  }
}

filter {
  grok {
    match => [
      "message", "%{HTTPDATE:timestamp} \[%{GREEDYDATA:Main_File}\] \[%{LOGLEVEL:log_level}\] %{GREEDYDATA:logger_message}"
    ]
  }

  date {
    match => ["timestamp", "dd/MMM/YYYY:HH:mm:ss SSS"]
  }
}

output {
  elasticsearch {
    hosts => ["http://localhost:9200"]
    index => "scientific_calculator"
  }
  
  stdout {
    codec => rubydebug
  }
}
```

## Running the ELK stack

Let's first run ElasticSearch using the following command:

```tsx
<path-to-elastic-search-dir>/bin/elasticsearch
```

ElasticSearch runs on [http://locahost:9200](http://localhost:9200). Verify that it is running by going to the URL.

Next run Kibana using the following command (on a separate terminal):

```tsx
<path-to-kibana-dir>/bin/kibana
```

Kibana runs on [http://locahost:5601](http://localhost:5601). Verify that it is running by going to the URL.

Next run Logstash using the following command (on a separate terminal):

```tsx
<path-to-logstash-dir>/bin/kibana -f <path-to-conf-file>
```

When everything is up an running, on the terminal on which we ran Logstash, the console output should look similar to this:

```bash
{
          "@version" => "1",
         "timestamp" => "17/Mar/2021:06:07:10 957",
              "host" => "rahul-Lenovo-Y520-15IKBN",
           "message" => "17/Mar/2021:06:07:10 957 [Calculator.java] [INFO] Calculator Performing square root operation on 64.0",
         "Main_File" => "Calculator.java",
              "path" => "/home/rahul/Downloads/calculator.log",
    "logger_message" => "Calculator Performing square root operation on 64.0",
        "@timestamp" => 2021-03-17T00:37:10.957Z,
         "log_level" => "INFO"
}
{
          "@version" => "1",
         "timestamp" => "17/Mar/2021:06:07:15 289",
              "host" => "rahul-Lenovo-Y520-15IKBN",
           "message" => "17/Mar/2021:06:07:15 289 [Calculator.java] [INFO] Calculator Performing factorial operation on 3",
         "Main_File" => "Calculator.java",
              "path" => "/home/rahul/Downloads/calculator.log",
    "logger_message" => "Calculator Performing factorial operation on 3",
        "@timestamp" => 2021-03-17T00:37:15.289Z,
         "log_level" => "INFO"
}
{
          "@version" => "1",
         "timestamp" => "17/Mar/2021:06:07:15 290",
              "host" => "rahul-Lenovo-Y520-15IKBN",
           "message" => "17/Mar/2021:06:07:15 290 [Calculator.java] [INFO] Calculator Result of factorial operation on 3 is: 6",
         "Main_File" => "Calculator.java",
              "path" => "/home/rahul/Downloads/calculator.log",
    "logger_message" => "Calculator Result of factorial operation on 3 is: 6",
        "@timestamp" => 2021-03-17T00:37:15.290Z,
         "log_level" => "INFO"
}
{
          "@version" => "1",
         "timestamp" => "17/Mar/2021:06:07:18 061",
              "host" => "rahul-Lenovo-Y520-15IKBN",
           "message" => "17/Mar/2021:06:07:18 061 [Calculator.java] [INFO] Calculator Performing natural logarithm operation on 10.0",
         "Main_File" => "Calculator.java",
              "path" => "/home/rahul/Downloads/calculator.log",
    "logger_message" => "Calculator Performing natural logarithm operation on 10.0",
        "@timestamp" => 2021-03-17T00:37:18.061Z,
         "log_level" => "INFO"
}
{
          "@version" => "1",
         "timestamp" => "17/Mar/2021:06:07:34 631",
              "host" => "rahul-Lenovo-Y520-15IKBN",
           "message" => "17/Mar/2021:06:07:34 631 [Calculator.java] [INFO] Calculator Performing power operation on 2.0,3.0",
         "Main_File" => "Calculator.java",
              "path" => "/home/rahul/Downloads/calculator.log",
    "logger_message" => "Calculator Performing power operation on 2.0,3.0",
        "@timestamp" => 2021-03-17T00:37:34.631Z,
         "log_level" => "INFO"
}
{
          "@version" => "1",
         "timestamp" => "17/Mar/2021:06:07:10 980",
              "host" => "rahul-Lenovo-Y520-15IKBN",
           "message" => "17/Mar/2021:06:07:10 980 [Calculator.java] [INFO] Calculator Result of square root operation on 64.0 is: 8.0",
         "Main_File" => "Calculator.java",
              "path" => "/home/rahul/Downloads/calculator.log",
    "logger_message" => "Calculator Result of square root operation on 64.0 is: 8.0",
        "@timestamp" => 2021-03-17T00:37:10.980Z,
         "log_level" => "INFO"
}
{
          "@version" => "1",
         "timestamp" => "17/Mar/2021:06:07:18 062",
              "host" => "rahul-Lenovo-Y520-15IKBN",
           "message" => "17/Mar/2021:06:07:18 062 [Calculator.java] [INFO] Calculator Result of natural logarithm operation on 10.0 is: 2.302585092994046",
         "Main_File" => "Calculator.java",
              "path" => "/home/rahul/Downloads/calculator.log",
    "logger_message" => "Calculator Result of natural logarithm operation on 10.0 is: 2.302585092994046",
        "@timestamp" => 2021-03-17T00:37:18.062Z,
         "log_level" => "INFO"
}
{
          "@version" => "1",
         "timestamp" => "17/Mar/2021:06:07:34 632",
              "host" => "rahul-Lenovo-Y520-15IKBN",
           "message" => "17/Mar/2021:06:07:34 632 [Calculator.java] [INFO] Calculator Result of power operation on 2.0,3.0 is: 8.0",
         "Main_File" => "Calculator.java",
              "path" => "/home/rahul/Downloads/calculator.log",
    "logger_message" => "Calculator Result of power operation on 2.0,3.0 is: 8.0",
        "@timestamp" => 2021-03-17T00:37:34.632Z,
         "log_level" => "INFO"
}
```

Now open a browser and go to [http://localhost:5601](http://localhost:5601)(Kibana) and follow the following steps:

- Go to the sidebar and go to Management → Stack Management
- Then go to Kibana → Index Patterns in the secondary side menu as shown below:

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/index_pattern_1.png](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/index_pattern_1.png)

- Then type in a matching index pattern as shown in the image below and click on "Next step".

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/index_pattern_2.png](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/index_pattern_2.png)

- On the next page select @timestamp as the option in the "Time field" as shown below and click on "Create index pattern".

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/index_pattern_3.png](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/index_pattern_3.png)

- Now go to Analytics → Discover in the primary side pane to and select the index pattern that we just created in the pull-down menu and adjust the date by clicking on the calendar icon in the top bar to whenever your logs were generated. This page will show us various aspects of the logs and we can use the ElasticSearch powered search to view specific fields, and search for sub content within some fields. This is shown in the image below:

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/brahma_discover.png](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/brahma_discover.png)

- Now go to Analytics → Dashboard in the primary side pane.
- Click on "Create new dashboard"
- Click on "Create panel". This is used to make new visualizations. There are various different kinds of visualizations that are possible. In this project, I used the "Lens" visualization.
- After clicking on Lens, you can just drag and drop the fields you want on to the central portion of the page (where it asks you to drop the attribute) and select a visualization from a plethora visualization options. Shown below is a doughnut visualization of the logger message:

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/brahma_donut.png](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/brahma_donut.png)

# Demonstration of Scientific Calculator Application

Here is a screenshot of the execution of all the functions/operation in my scientific calculator application:

![CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/calc_demo_full.png](CS%20816%20-%20Software%20Production%20Engineering%20Mini%20Proj%20019483cfdfb44ee893e36a39a5355a41/calc_demo_full.png)

[]()
