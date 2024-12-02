# OSIRIS: Plaid Banking App

## Author: 
### Mahan Hooshmandkhayat:
######
### Divnoor Pal Singh Chatha:
###### Verify View for Team Use Case, GrabTransaction UseCase, ViewExpenses UseCase
### Shah Jalalul Kabir:
######
### Christopher Han Roy:
#####

## Preamble

The project gets financial information from any bank account in Canada or USA for specific user, and analyzes the
data for it to be displayed onto various pages for your viewing pleasure.

This project is intriguing because it provides a seamless way to access and analyze financial data from any Canadian or 
U.S. bank account, offering personalized insights in an engaging and user-friendly format. By simplifying complex 
financial information and presenting it visually across multiple pages, it empowers users to make informed decisions with ease.

This project solves the problem of fragmented financial management by consolidating data from various bank accounts into 
one accessible platform. It eliminates the need for manual tracking and analysis of financial information, saving users 
time and reducing errors. Additionally, it provides clear, actionable insights, helping users better understand and manage their finances.



## Features of the software
Uses the program uses OkHttp to allow a user to make an OSIRIS account using a username and password along with a 
verfication system, which done by the Brevo API. All the servers are made using the Spring Boot API. Once the user is 
logged in, the user can add a bank account using the bank account button on the home page. This is where the Plaid API 
is called and allows you to add a bank account. Once Bank account is added, the user can navigate to the various pages 
in the homepage to view various analysis regarding thier bank account. Features like looking at a graph of your 
Non-Essential vs Essential spending over the last 30 days. Or ...

## Installation Instructions
Fork the Repo and you are good to go. Make sure you load the maven project. Make sure that the pom.xml 
file is the following

```    
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>plaid-integration</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <name>plaid-integration</name>
    <description>Integration of Plaid API with Spring Boot</description>

    <properties>
        <java.version>17</java.version>
        <spring.boot.version>3.4.0</spring.boot.version>
        <gson.version>2.11.0</gson.version>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>

    <repositories>
        <repository>
            <id>plaid-repo</id>
            <url>https://plaid.com/maven</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.0.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.jfree</groupId>
            <artifactId>jfreechart</artifactId>
            <version>1.5.3</version>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.8.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.plaid</groupId>
            <artifactId>plaid-java</artifactId>
            <version>29.0.0</version> <!-- You can use the latest stable version -->
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.13</version>
        </dependency>
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20240303</version>
        </dependency>
        <dependency>
            <groupId>com.squareup.okhttp3</groupId>
            <artifactId>okhttp</artifactId>
            <version>4.12.0</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.8.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.sendinblue</groupId>
            <artifactId>sib-api-v3-sdk</artifactId>
            <version>7.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.18.1</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.32</version>
        </dependency>
        <dependency>
            <groupId>com.sun.mail</groupId>
            <artifactId>javax.mail</artifactId>
            <version>1.6.2</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Gson: JSON parser -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>${gson.version}</version>
        </dependency>

        <!-- Spring Boot DevTools -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Spring Boot Starter Test (Optional for Testing) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>annotationProcessor</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.14.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>5.14.2</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.jacoco/jacoco-maven-plugin -->
        <dependency>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.12</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${spring.boot.version}</version>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.5.2</version>
            </plugin>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.8</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>test</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>

```

## Usage Guide


* * *

Open the project in IntelliJ and make sure you can successfully run `app/Main.java`.
Note: you may need to set the Project SDK in the `Project Structure...` menu, and possibly
also manually link the Maven project, as you did in Phase 1.

## Task 1: Understanding the Program

You may notice that we have refactored the CA engine code _slightly_ since Phase 1, but the
way we build the engine is drastically different: we have switched from using Factories to
using the Builder design pattern, which we'll be discussing in lecture soon. 

Open up `app.Main` and read it as a team.
- What are the Views and what are the current Use Cases?
- Which Uses Cases are triggered from each View?
- Which version of the DAO is `app.Main` using?

The major change since Phase 1 is that we have added the `app.AppBuilder` class which makes
it easier to understand how our CA engine is being constructed — it also makes `app.Main` nice and concise!
- Why do all those `addX` methods end in `return this;`? 

Run the program and make sure the signup and login Use Cases work.

Currently, you'll notice that the "Log Out" button still doesn't actually log you out. It's time to fix
that button, which is part of the `LoggedInView`.
We have created all the classes for you, but some of the code is missing.
As a team, your task is to fill in the missing code so that the Logout Use Case is implemented.
**The next part of the readme describes how your team will do this.**

* * *

**Your team will know when you are done when:**

- Clicking the "Log Out" button takes the user back to the Login View when you use the program.
- The provided `LogoutInteractorTest` test passes.

The "Log Out" button is an instance variable in class `LoggedInVew`. Go find it.
Also look at the `interface_adapter.change_password.LoggedInViewModel`, which contains any
data showing on the `LoggedInVew`.

* * *

## Task 2: Dividing up the work

There are `TODO` comments left in the files
Recall that you can use the TODO tool window to conveniently pull up a complete list.

Once the TODOs are all complete, the "Log Out" button _should_ work!

As a team, split up the TODOs (see below) between the members of your team.

There are TODOs in seven of the files.
Make sure each member has at least one TODO which they will be responsible for completing.
If your team prefers to work in pairs, that is fine too. Your individual branches
will not be graded for this — only the final, working version.

The TODOs are summarized below (by file) to help your team decide how to split them up:

* * *

- `Main.java`

  - [ ] TODO: add the Logout Use Case to the app using the appBuilder

* * *

- `LoggedInView.java` (tip: refer to the other views for similar code)

  - [ ] TODO: save the logout controller in the instance variable.
  - [ ] TODO: execute the logout use case through the Controller

* * *

- `LogoutController.java` (tip: refer to the other controllers for similar code)

  - [ ] TODO: Save the interactor in the instance variable.
  - [ ] TODO: run the use case interactor for the logout use case

* * *

- `LogoutInputData.java` (should be done with the LogoutInteractor TODOs below)

  - [ ] TODO: save the current email in an instance variable and add a getter.

- `LogoutInteractor.java` (tip: refer to `ChangePasswordInteractor.java` for similar code)

  - [ ] TODO: save the DAO and Presenter in the instance variables.
  - [ ] TODO: implement the logic of the Logout Use Case

* * *

- `LogoutOutputData.java`

  - [ ] TODO: save the parameters in the instance variables.

* * *

- `LogoutPresenter.java` (tip: refer to `SignupPresenter.java` for similar code)

  - [ ] TODO: assign to the three instance variables.
  - [ ] TODO: have prepareSuccessView update the LoggedInState
  - [ ] TODO: have prepareSuccessView update the LoginState

* * *

1. Make a branch named the first part of your UofT email address, everything before the `@`.
For example, if your email address is `paul.gries@mail.utoronto.ca`, then the branch name would
be `paul.gries`.

Make sure you switch to the new branch.

In the terminal, this would look like below, but replaced with your own information:
```
git branch paul.gries
git switch paul.gries
```

2. Complete your assigned TODOs and make a pull request on GitHub. In your pull request,
   briefly describe what your TODOs were and how you implemented them. If you aren't sure
   about part of it, include this in your pull request so everyone knows what to look
   for when reviewing — or you can of course discuss with your team before making your
   pull request since you are physically working in the same space.
   - **Important: don't push any changes to the `.idea` folder, as that
     may cause issues for your other teammates, as some files contain
     configurations specific to your individual IntelliJ projects.**

3. Review each other's pull requests to ensure each TODO is correctly implemented and that
   there are no Checkstyle issues in the files that were modified.

4. Once all TODOs are completed, your team should debug as needed to ensure the
   correctness of the code. Setting a breakpoint where the log-out use case
   interactor starts its work will likely be a great place to start when debugging.

And that's it; you now have a working Logout Use Case! Instructions for
how to submit your work on MarkUs will be posted later.

Your team should spend the rest of the lab working on your project blueprint.

* * *

# Project Blueprint

See Quercus for details about the project blueprint! By the end of the week,
the goal is for your team to have a fully drafted blueprint so that your team
will be ready to get started on your project after Reading Week.
