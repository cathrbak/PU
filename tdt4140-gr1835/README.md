# PsykiskHelse

NTNU Software Development Project - Team 35

![](forside.png)

![](registrering.png)

![](hovedside.png)

PsykiskHelse is a software project under development. Our program is made for school nurses to handle eating disorders among students at the University. Our program makes it possible for the school nurses to uncover eating disorders and begin working towards recovery using the information they get through regular surveys the students answers in our App. Inside their profiles the nurses can; find their students, send them messages and filter the information by criteria. The school nurses can also make notes linked to a student, and contact a student if necessary.

*See [PsykiskHelse](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/35.git) for more details about API og issues.*


## Installation
Here you find everything you need to install for our project.

* [Java Eclipse](http://www.eclipse.org/downloads/eclipse-packages/) - Oxygen.2

* [Putty](https://www.putty.org) - Only if you are using Windows, not necessary for Mac

* [Cisco AnyConnect Secure Mobility Client](https://www.cisco.com/c/en/us/support/security/anyconnect-secure-mobility-client-v4-x/model.html)

* [Git URL](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/35.git)


## Running the software

* AS NURSE:

Download the project from git by copy the URL.

Import the project and save the folder in a place you can easily locate.

(If you are connected to a network at NTNU, you can skip this step) Connect to the NTNU network using the Cisco AnyConnect VPN.

see this link for more info on how to do this.
(This might work the same in other IDEs, but this setup was based on Java Eclipce) Open Java Eclipse.

Locate the folder you imported earlier.

Locate the Web Server-package. Start the server by right-clicking and selecting "Run as" -> "Maven build...". Type in "jetty:run" and click run.

Locate the FxApp.java class in the UI-package. Try to run it by right-clicking and selecting Run FxApp.java.

This will make the program up and running.

Should you run into any more trouble, feel free to contact us at jonahag@stud.ntnu.no and we will aid you as best we can!


* AS STUDENT:

Download the project from git by copy the URL.

Import the project and save the folder in a place you can easily locate.

(If you are connected to a network at NTNU, you can skip this step) Connect to the NTNU network using the Cisco AnyConnect VPN.

see this link for more info on how to do this.
(This might work the same in other IDEs, but this setup was based on Java Eclipce) Open Java Eclipse.

Locate the folder you imported earlier.

Locate the Web Server-package. Start the server by right-clicking and selecting "Run as" -> "Maven build...". Type in "jetty:run" and click run.

Locate the FxAppStudent.java class in the UI-package. Try to run it by right-clicking and selecting Run FxAppStudent.java.

This will make the program up and running.

Should you run into any more trouble, feel free to contact us at jonahag@stud.ntnu.no and we will aid you as best we can!


## Test
Here you find all the information about how to run the automated tests for this system.

Using the terminal:
1. Open terminal
2. Write `mvn test`, press enter
3. Maven will run all the tests in the project.

Using Eclipse Oxygen.2:
1. Right-click on the root-folder
2. Click on "Run as" and then click Maven test.
3. Maven will run all the tests in the project.


## API reference
This wikipage lists all the API references in our project:

* [References](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/35/wikis/Dokumentasjon/Design-av-REST-API)

## Built with

* [Maven](https://maven.apache.org/) - Dependency Management

## Contributors

* **Jonas Haga** - *Scrum master* - [PsykiskHelse](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/35.git)
* **Sverre Spetalen** - *Tester* - [PsykiskHelse](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/35.git)
* **Hans Kristian Ovangen** - *Team member* - [PsykiskHelse](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/35.git)
* **Carl Fredrik Lystad** - *Team member* - [PsykiskHelse](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/35.git)
* **Janine Stang** - *Team member* - [PsykiskHelse](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/35.git)
* **Cathrine Akre-Aas** - *Team member* - [PsykiskHelse](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/35.git)
* **Nora Kallager** - *Team member* - [PsykiskHelse](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/35.git)

## License

[Apache License, Version 2.0, January 2004](https://www.apache.org/licenses/LICENSE-2.0)
