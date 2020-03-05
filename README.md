# Animals Live Simulation

## Background

![](https://raw.githubusercontent.com/mohistzh/animals-live-simulation/master/static/story%231.png)
![](https://raw.githubusercontent.com/mohistzh/animals-live-simulation/master/static/story%232.png)

## Design 

> Please refer static/DesignDoc.pdf


## Installation

### Maven user

Recommended to use local Maven to execute this program.

> mvn clean package

> mvn exec:java -Dexec.mainClass=com.github.mohistzh.Application

or the wrapper solution.

>./mvnw exec:java -Dexec.mainClass=com.github.mohistzh.Application

*You could specify a number of days like this: -Dexec.mainClass=com.github.mohistzh.Application -Dexec.args=100*


## Environment

* JDK 8+
* Maven 3+


## Screenshot

The screenshot belongs to the last part of the task.

![example](https://raw.githubusercontent.com/mohistzh/animals-live-simulation/master/static/screenshot.png)