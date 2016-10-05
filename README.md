<h1>Example Spring 4 Java 8 RESTful Java Service Layer</h1>

<h3>Requirements</h3>

<ul>
<li>1) Java 8 installed<li>
<li>2) Apache Maven 3.3.x installed<li>
<li>3) Ideally Jetbrains Intellij IDE<li>
</ul>


<h3>To run it</h3>

<p>1) Git clone the project</p>

<p>2) Setup the database, a simple local MySQL will do initially
   There's a simple dataset that can be imported into the database
   /src/main/resources/simple-dataset.sql
   note database name needs to be Stories
</p>

<p>3) From the directory with the pom file, run mvn clean jetty:run
   This will download all the dependencies the first time it's run
   Once the internet is downloaded and there're no errors browse to</p>
   <ul> 
   <li>http://localhost:9090/Story/stories/</li>
   <li>http://localhost:9090/Story/author/</li>
   <li>http://localhost:9090/Story/author/stories/</li>
   </ul>

   <p>These give you a picture of a one-to-many json data representation, with one author to many stories
</p>

<h3>To modify it</h3>

<p>
1) Open the project with Intellij and head straight for StoryController
   From this it can be seen from the methods that these relate to endpoints, and that
   the http method is expecting get/post/put/delete
   Each Controller is backed up by a Service which in turn calls the Dao which in turn uses the Entities
</p>

<h3>To understand it</h3>

<p>Older versions of Spring MVC required extensive xml configuration files, whereas we can now use @Annotations.
This is a very good thing.
Not every file can be got rid of though, there's still a web.xml and a dispatcher-servlet.xml, which i've reduced to a bare minimum
</p>
<p>On project start up, Spring will look for the @Configuration annotation before a class and run it. It can be seen in this project that there's
configuration classes for Application, JPA and Web. Once theses have been executed the project will instantiate the Hibernate entities and bind them to
the datasource.</p>
<p>Here's a good resource for understanding JPA v Hibernate http://stackoverflow.com/questions/9881611/whats-the-difference-between-jpa-and-hibernate</p>

<p>Have a look at the tests, the StoryDaoTest is a straightforward JUnit test, whereas the StoryControllerTest is a more complex Mock test due to the nature of http.</p>

<h3>What needs to be done to it</h3>

<p>The author controller/service/dao needs the rest of the crud functions</p>
<p>Tests need to be written for said classes</p>
<p>Tests need to written for the service package</p>



