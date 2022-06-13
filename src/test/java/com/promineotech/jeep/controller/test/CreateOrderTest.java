/**
 * 
 */
package com.promineotech.jeep.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.web.client.RestTemplate;
import com.promineotech.jeep.controller.support.CreateOrderTestSupport;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Order;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1Jeep_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))
class CreateOrderTest extends CreateOrderTestSupport {

    @LocalServerPort
    private int serverPort;

    @Test
    void testCreateOrderReturnsSuccess201() {
        RestTemplate restTemplate = new RestTemplate();

        String body = createOrderBody();
        String uri = String.format("http://localhost/%d/orders", serverPort);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
        ResponseEntity<Order> response = restTemplate.exchange(uri,
                HttpMethod.POST, bodyEntity, Order.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();

        Order order = response.getBody();
        assertThat(order.getCustomer().getCustomerId()).isEqualTo("MORISON_LINA");
        assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.WRANGLER);
        assertThat(order.getModel().getTrimLevel()).isEqualTo("Sport Altitude");
        assertThat(order.getModel().getNumDoors()).isEqualTo(4);
        assertThat(order.getColor().getColorId()).isEqualTo("EXT_NACHO");
        assertThat(order.getEngine().getEngineId()).isEqualTo("2_0_TURBO");
        assertThat(order.getTire().getTireId()).isEqualTo("35_TOYO");
        assertThat(order.getOptions()).hasSize(6);

    }

    
}
