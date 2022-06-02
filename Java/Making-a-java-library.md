1. Make a Maven project (IntelliJ IDEA)
2. Add dependencies needed (if any)
3. Do the code as usual in src/ folder
3.5 In the pom.xml a <packaging>jar</packaging> should be added if it doesn't exist.
4. When the class library is done, you run the `mvn clean install` or `mvn clean install -DskipTests`
5. This generates a .jar file in the /target folder
6. This .jar file can be put into a directory in another project you're making e.g. a 'libs' folder
7. In that other project (in IntelliJ) right click the libs folder (after you've moved the .jar file in there)
8. Select the 'Add as library' option (one of the lowest options)
9. Use the code of the library in the application.
