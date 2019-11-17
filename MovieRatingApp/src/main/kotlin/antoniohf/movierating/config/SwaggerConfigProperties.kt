package antoniohf.movierating.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration("swaggerConfigProperties")
class SwaggerConfigProperties {

    @Value("\${api.version:unknown}")
    lateinit var apiVersion: String

    @Value("\${swagger.enabled:false}")
    lateinit var enabled: String

    @Value("\${swagger.title}")
    lateinit var title: String

    @Value("\${swagger.description}")
    lateinit var description: String

    @Value("\${swagger.useDefaultResponseMessages}")
    lateinit var useDefaultResponseMessages: String

    @Value("\${swagger.enableUrlTemplating:true}")
    lateinit var enableUrlTemplating: String

    @Value("\${swagger.deepLinking:false}")
    lateinit var deepLinking: String

    @Value("\${swagger.defaultModelsExpandDepth:1}")
    lateinit var defaultModelsExpandDepth: String

    @Value("\${swagger.defaultModelExpandDepth:1}")
    lateinit var defaultModelExpandDepth: String

    @Value("\${swagger.displayOperationId}")
    lateinit var displayOperationId: String

    @Value("\${swagger.displayRequestDuration:false}")
    lateinit var displayRequestDuration: String

    @Value("\${swagger.filter:true}")
    lateinit var filter: String

    @Value("\${swagger.maxDisplayedTags:0}")
    lateinit var maxDisplayedTags: String

    @Value("\${swagger.showExtensions:false}")
    lateinit var showExtensions: String
}