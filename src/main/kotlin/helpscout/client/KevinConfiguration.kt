package helpscout.client

import feign.Logger.Level
import feign.Logger.Level.FULL
import feign.RequestInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails


@Configuration
open class KevinConfiguration {

    @Bean
    open fun feignLoggerLevel(): Level = FULL

    @Bean
    open fun oauth2FeignRequestInterceptor(
            @Value("\${client.id}") clientId: String,
            @Value("\${client.secret}") clientSecret: String,
            @Value("\${kevin.url}") kevinUrl: String): RequestInterceptor {
        return OAuth2FeignRequestInterceptor(clientContext(), resource(clientId, clientSecret, kevinUrl))
    }

    @Bean
    open fun clientContext(): OAuth2ClientContext {
        val accessTokenRequest = DefaultAccessTokenRequest()
        return DefaultOAuth2ClientContext(accessTokenRequest)
    }

    protected open fun resource(clientId: String, clientSecret: String, kevinUrl: String): OAuth2ProtectedResourceDetails {
        val details = ClientCredentialsResourceDetails()
        details.clientId = clientId
        details.clientSecret = clientSecret
        details.accessTokenUri = kevinUrl + "/oauth2/token"
        return details
    }

}