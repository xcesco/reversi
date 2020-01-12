[![Build Status](https://travis-ci.org/xcesco/reversi.svg?branch=master)](https://travis-ci.org/xcesco/reversi)
[![codecov](https://codecov.io/gh/xcesco/reversi/branch/master/graph/badge.svg)](https://codecov.io/gh/xcesco/reversi)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=it.fmt.games.reversi%3Areversi-project&metric=alert_status)](https://sonarcloud.io/dashboard?id=it.fmt.games.reversi%3Areversi-project)
[![GitHub issues](https://img.shields.io/github/issues/xcesco/reversi.svg)](https://github.com/xcesco/reversi/issues)


<img src="https://github.com/xcesco/reversi/blob/master/reversi-desktop/src/main/resources/logo.png" alt="logo" width="200"/>

# Reversi
By [Francesco Benincasa](https://github.com/xcesco), [Matteo Franco](https://github.com/M4TT3O-91), [Tiziano Bonifazi](https://github.com/tboni91) 

## Introduction
The Reversi project is our collaborative work repository for [Software Development Methods](https://github.com/software-development-methods-19-20) exam of DSSC MSc @ UniTS/SISSA. Our goal is realize a Java implementation of Reversi game following the knowledge acquired during the course about Java, Functional programing, Agile and Test Driven Development.

### Project organization
The project is composed by 4 modules (plus the main project):

 - **reversi-core**: it is the module that contains all the game logic. It's used by the other modules.
 - **reversi-console**: it is the reversi console implementation. It allows to play the game using a command line.
 - **reversi-desktop**: it is the reversi swing implementation. 
 - **reversi-parent**: it is the parent project of other moudules. It's used to avoid project settings duplication.
 - **reversi-project**: it's a multi module maven project. It's composed by other projects.
 
 Moreover there's the Android Reversi implementation that has its source code in [this repository](https://github.com/xcesco/reversi-android).

## The game
There are different set of rules applied to Reversi. Our project take its from [Wikipedia](https://en.wikipedia.org/wiki/Reversi) 
and [Federazione Nazionale Gioco Othello](http://www.fngo.it/regole.asp).

## How to compile the project
To compile the project you need:

 - Java OpenJDK 11+
 - Maven 3.6.3
 
Once you clone or download the project's repository, you can build the entire project from command line positioned in 
the project root folder and using the command:

`mvn clean install`

Otherwise you can simply import the project in your preferred IDE and build it there.

### Prepare the Android maven artifact
The Android platform supports Java 7 & 8. Reversi is written with OpenJDK 11. The
 [reversi-android](https://github.com/xcesco/reversi-android) project need the `reversi-core` jar in its dependencies.
 To create the jdk8 edition of the core module, you can use the profile `jdk8` in the `reversi-core` project:
 
 `mvn clean install -Pjdk8`
 
 This command store the specialized jar in maven local repository. Once it was build, you can open the Android project and build
 the app.
 

## How to execute the programs
Once you compile the entire project, you can execute the desktop and the console version of the game.

### Execute the console version
From your preferred IDE simply execute the main method of the class `it.fmt.games.reversi.console.App`. If you want to
 execute it from a command line go to the folder `<PROJECT-ROOT>\reversi-console\target` and execute the command 
 ```
java -jar reversi-console-1.0.0-jar-with-dependencies.jar
 ```

### Execute the desktop version
From your preferred IDE simply execute the main method of the class `it.fmt.games.reversi.desktop.App`. If you want to
 execute it from a command line go to the folder `<PROJECT-ROOT>\reversi-desktop\target` and execute the command 
 ```
java -jar reversi-desktop-1.0.0-jar-with-dependencies.jar
 ```
 
 ## Some screnshoots
 It's quite easy to build the project, but if you want simply see the results, here there are some screenshots about Android, desktop (swing) and console reversi implementation.
 
 <table>
 <row>
 <td><img src="https://github.com/xcesco/reversi/blob/master/docs/android_screenshot.png" alt="logo" width="300"/></td>
 <td><img src="https://github.com/xcesco/reversi/blob/master/docs/desktop_screenshot.png" alt="logo" width="400"/></td>
 <td><img src="https://github.com/xcesco/reversi/blob/master/docs/console_screenshot.png" alt="logo" width="400"/></td>
 </row>
 </table>
