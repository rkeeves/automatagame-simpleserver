# automatagame-simpleserver

Simple server for storing data for this semester's Software Development Methodologies course.

## Dependencies

### Lombok, Annotation Processing

You **MUST** install lombok to your **IDE** first, and also you must enable **Annotation Processing**.

I really encourage the use of Intellij IDEA, but Eclipse also works for me.

Use this guide to enable [Auto Reload](https://mkyong.com/spring-boot/intellij-idea-spring-boot-template-reload-is-not-working/) in IntelliJ.

### Standalone MongoDB

The server **requires** a running mongodb instance it can connect to.
This is not an inmemory db, so you must make sure to have one, and configure it in the application.properties file.

Steps to make mongo work for development purposes:
1. [Install Mongodb (NOT as a service)](https://docs.mongodb.com/manual/installation/)
1. Create ../data/db directory somewhere
1. Run it or create batch file etc. 

The **server** can be started like this for example(I think you are on Win):

`"C:\Program Files\MongoDB\Server\4.4\bin\mongod.exe" --dbpath="C:\somewhere\over\the\rainbow\data\db"`

The double quotes are used to overcome spaces in paths, but you could also use the shorthand form like PROGRA~1, sigh...

You can also interact with the db manually via the mongo client.
By default, this one should work:

`"C:\Program Files\MongoDB\Server\4.4\bin\mongo.exe"`

The application.properties must be good if you don't change port etc. for the mongod process.

(You could do the whole thing with Docker, but I think you want to keep it simple)

## Features

The current version has the following major features:
* User Sign up
* User Sign in
* User Sign Out
* User Edit (ADMIN)
* User Delete (ADMIN)
* View list of Users
* Play game (reached scores are persistent)
* View own scores
* View others' scores