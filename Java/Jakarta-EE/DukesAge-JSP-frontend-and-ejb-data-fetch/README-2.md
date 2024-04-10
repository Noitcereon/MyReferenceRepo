# DukesAge-JSP-frontend and ejb data fetch

This is an example of a simple Java Frontend that retrives data from an EJB, while I was learning Java EE (Jakarta EE) 8.

The example was achieved while roughly following the https://javaee.github.io/firstcup/ tutorial (I strayed from the tutorial in places, such as using JSP instead of Java Facelets and using the newer LocalDate instead of the old Date).

The purpose is the give me a rough idea of the flow of data exchange between frontend and backend and how Java Beans and EJB (enterprise java beans) work.

It is by no means a pretty application - neither code nor frontend view.

## Definitions

**Java Bean**: A java class implementing serializable and only allows access to instance fields via methods (e.g getAge, setAge).
**EJB:** A java class with certain annotations. For example: @Stateless, @Stateful or @Singleton (Session beans) or @MessageDriven (Message Driven Beans).









