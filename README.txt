Here is a command to fully compile mvn and gwt:

from: http://stackoverflow.com/questions/7260459/how-can-i-run-a-gwt-app-from-gwt-maven-plugin-without-any-browser-plugins
mvn clean:clean resources:resources compiler:compile war:exploded resources:testResources compiler:testCompile surefire:test gwt:compile war:war

without test:
mvn clean:clean resources:resources compiler:compile war:exploded resources:testResources compiler:testCompile gwt:compile war:war

starting jetty:
mvn jetty:run-war


Notes on starting jetty before selenium:
http://176.34.122.30/blog/2008/09/17/automated-integration-testing-with-selenium-maven-and-jetty/
