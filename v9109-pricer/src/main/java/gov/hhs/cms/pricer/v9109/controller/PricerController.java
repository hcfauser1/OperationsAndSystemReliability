package gov.hhs.cms.pricer.v9109.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.hhs.cms.pricer.v9109.core.PricerDispatch;
import gov.hhs.cms.pricer.v9109.exceptions.YearNotImplementedException;
import gov.hhs.cms.pricer.v9109.viewmodel.PricerInput;
import gov.hhs.cms.pricer.v9109.viewmodel.PricerOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class PricerController {

    private final Logger logger = LoggerFactory.getLogger(PricerController.class);

    @RequestMapping(value="/v1/pricer/", method= RequestMethod.POST, consumes="application/json")
    public ResponseEntity<String> run(@RequestBody PricerInput input){

        logger.info("pricer v1 request: {}",input.toString());
        try {
            PricerDispatch pd = new PricerDispatch();
            PricerOutput po = pd.runPricer(input);

            ObjectMapper mapper = new ObjectMapper();
            String output = mapper.writeValueAsString(po);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(output);
        }
        catch(YearNotImplementedException ex){
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("\"message\":\"Year submitted is not permitted\"");
        }
        catch(JsonProcessingException ex){
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("\"message\":\"Unable to process JSON request\"");
        }
        catch (IOException ex){
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Server IOException processing the request");
        }
    }

}
