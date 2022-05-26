# Axon Snapshot Error Replication

This repo is an attempt to replicate an error when snapshots are taken and to understand why this occurs.

## Usage

1. Have a local postgres DB running and set up.
   - The DB should be called "axon-app" (see src/resources/db) 
2. Change the `application.properties` datasource properties with the correct postgres DB details.
   - The current settings should work. 
3. Change the `build.gradle` flyway closure with the correct postgres DB details.
    - The current settings should work. 
4. Run `./gradlew flywayClean` && `./gradlew flywayMigrate` to get the DB schema in place.
5. Run Axon Server.
6. Run the application using `./gradlew bootRun` or using the autogenerated Spring Boot run configuration (if using IntelliJ).
7. Check `localhost:8080/swagger-ui.html` for the swagger UI and play around with the app.
8. Run the /demo/trigger-account-snapshot endpoint and see the error: 
   >Aggregate identifier must be non-null after applying an event. Make sure the aggregate identifier is initialized at the latest when handling the creation event.

N.B. this error does not occur:
- if you create the account aggregate using the POST /accounts endpoint.
- if you have snapshotting turned off
 
Notes:
- See ApplicationAxonConfiguration.java for snapshotting config.