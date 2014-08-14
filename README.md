Webflix
=======
Simple Java Web Application made with Gradle, Spring Boot, Hibernate, Spring MVC, Thymeleaf, Hibernate and Bootstrap

This is probably the easiest way to run Java Web Application aside from Play Framework. This was done for a class so it won't be able to connect to anything but it should be a good skeleton to start a Java Web Project with Hibernate.

As usual, this was done in a minimal amount of time with a questionnable amount of hacks but it should serve as an example on how to get started with quick Java Web Prototypes.

Things to know:
- Template/View Engine: Thymeleaf
- Uses Gradle: You can manage your libraries and maven repositories with it
- Tomcat: Comes with a portable version of TomCat (Thanks Spring Boot)
- Works out of the box

Screenshot:
![Screenshot](http://i.imgur.com/HPrB37q.png "Screenshot")


Simpliest Setup:

- Download IntelliJ (Best Java IDE hands down)
- Import Project
- Select gradle.config to import
- Use the Default Gradle Wrapper
- Hit Shift + F10 to Run + Compile
- Should be able to browse localhost:8080

Eclipse Setup

- Download Eclipse
- Install Gradle Integration from the Eclipse Marketplace (Help => Eclipse MarketPlace)
- Select File => Import
- Select Gradle Project
- Browse to Webflix Folder
- Click "Build Model"
- Run Application.java as Java Application
- Browse to localhost:8080