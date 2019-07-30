package gov.hhs.cms.pricer.v9109.controller;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class HealthControllerTest {

    @Test
    public void healthControllerReturns200() {
        HealthController hc = new HealthController();
        ResponseEntity<String> resp = hc.home();
        assertEquals(resp.getBody(), "web service is active");
        assertEquals(resp.getStatusCodeValue(), 200);
    }


}