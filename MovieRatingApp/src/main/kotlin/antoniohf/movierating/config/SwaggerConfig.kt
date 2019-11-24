package antoniohf.movierating.config

import io.swagger.annotations.Api
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.swagger.web.UiConfiguration
import springfox.documentation.swagger.web.TagsSorter
import springfox.documentation.swagger.web.OperationsSorter
import springfox.documentation.swagger.web.DocExpansion
import springfox.documentation.swagger.web.ModelRendering
import springfox.documentation.swagger.web.UiConfigurationBuilder
import org.springframework.http.ResponseEntity
import java.time.LocalDate
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.service.contexts.SecurityContext

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun eDesignApi(swaggerConfigProperties: SwaggerConfigProperties): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(swaggerConfigProperties))
                .enable(swaggerConfigProperties.enabled.toBoolean())
                .securityContexts(listOf(securityContext()))
                .securitySchemes(listOf(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api::class.java))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate::class.java, String::class.java)
                .genericModelSubstitutes(ResponseEntity::class.java)
                .useDefaultResponseMessages(swaggerConfigProperties.useDefaultResponseMessages.toBoolean())
                .enableUrlTemplating(swaggerConfigProperties.enableUrlTemplating.toBoolean())
    }

    @Bean
    fun uiConfig(swaggerConfigProperties: SwaggerConfigProperties): UiConfiguration {
        return UiConfigurationBuilder
                .builder()
                .deepLinking(swaggerConfigProperties.deepLinking.toBoolean())
                .displayOperationId(swaggerConfigProperties.displayOperationId.toBoolean())
                .defaultModelsExpandDepth(swaggerConfigProperties.defaultModelsExpandDepth.toInt())
                .defaultModelExpandDepth(swaggerConfigProperties.defaultModelExpandDepth.toInt())
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(swaggerConfigProperties.displayRequestDuration.toBoolean())
                .docExpansion(DocExpansion.NONE)
                .filter(swaggerConfigProperties.filter.toBoolean())
                .maxDisplayedTags(swaggerConfigProperties.maxDisplayedTags.toInt())
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(swaggerConfigProperties.showExtensions.toBoolean())
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build()
    }

    private fun apiInfo(swaggerConfigProperties: SwaggerConfigProperties): ApiInfo {
        return ApiInfoBuilder()
                .title(swaggerConfigProperties.title)
                .description(swaggerConfigProperties.description)
                .version(swaggerConfigProperties.apiVersion)
                .build()
    }

    private fun apiKey(): ApiKey {
        return ApiKey("JWT", "Authorization", "header")
    }

    private fun securityContext(): SecurityContext {
        return SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/api/v1/movies.*"))
                .build()
    }

    fun defaultAuth(): List<SecurityReference> {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return listOf(SecurityReference("JWT", authorizationScopes))
    }
}