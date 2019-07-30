package gov.hhs.com.wx5911xa;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.hhs.com.wx5911xa.core.PricerDispatch;
import gov.hhs.com.wx5911xa.exceptions.YearNotImplementedException;
import gov.hhs.com.wx5911xa.viewmodel.PricerInput;
import gov.hhs.com.wx5911xa.viewmodel.PricerOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;


public class PricerHandler implements RequestHandler<Map<String,Object>, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(Handler.class);

    public ApiGatewayResponse handleRequest(Map<String,Object> input, Context context)  {
        LOG.info("received: {}", input != null ? input.toString() : "{}");

        try {
            JsonNode body = new ObjectMapper().readTree(input.get("body").toString());
            PricerInput pi = new ObjectMapper().treeToValue(body, PricerInput.class);

            PricerDispatch pd = new PricerDispatch();
            PricerOutput po = pd.runPricer(pi);

            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(po)
                    .build();
        }
        catch (YearNotImplementedException ex){
            return ApiGatewayResponse.builder()
                    .setStatusCode(400)
                    .build();
        }
        catch (JsonProcessingException ex){
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .build();
        }
        catch (IOException ex){
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .build();
        }

    }
}
