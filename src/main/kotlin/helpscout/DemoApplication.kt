package helpscout

import helpscout.client.KevinClient
import org.springframework.boot.Banner.Mode.CONSOLE
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties
open class DemoApplication {

    @Bean
    open fun init(kevinClient: KevinClient) = CommandLineRunner { args ->
        val customerId = args[0].toLong()
        println("Loading customer $customerId")

        val customer = kevinClient.getCustomer(customerId)
        println("Got customer $customer")
    }

}

fun main(args: Array<String>) {
    SpringApplicationBuilder().sources(DemoApplication::class.java)
            .web(false)
            .bannerMode(CONSOLE)
            .run(*args)
}
