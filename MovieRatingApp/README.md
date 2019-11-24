# MovieRatingApp

This is intended to be a kotlin springboot starter project with:
  * Springboot base structure in kotlin
  * Liquibase db migration with a memory H2 DB
  * JWT authentication
  * Swagger ui

This project is intended to be used as a base for rapid protopyting. It is not production ready code!

# Development server

Run ```gradlew bootrun``` to start springboot. 

Run ```gradlew docker``` to generate docker image

Access http://localhost:8080/swagger-ui.html to test endpoints and authenticate with credentials -> "user:pass"

# Notes
 * Upon start, springboot will create a h2 database and run liquibase migrations on it. You can configure an external DB or define a path to a H2 DB (in disk) and this will avoid creating a memory H2 DB and running all migrations, everytime you start springboot.
 * swagger-ui is accessible by everyone. However, you need to authenticate to be able to call endpoints.
 * Only the endpoints annotated with "Api" and (the paths) defined in the swagger config file, will be shown in the swagger-ui.

