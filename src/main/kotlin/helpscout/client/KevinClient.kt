package helpscout.client

import helpscout.customer.Customer
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@FeignClient(name = "kevin", configuration = arrayOf(KevinConfiguration::class), url = "\${kevin.url}")
interface KevinClient {

    @RequestMapping(method = arrayOf(RequestMethod.GET), path = arrayOf("/v1/customers/{id}"))
    fun getCustomer(@PathVariable("id") id: Long): Customer

}

