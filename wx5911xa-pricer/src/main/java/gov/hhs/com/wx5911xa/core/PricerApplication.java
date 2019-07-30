package gov.hhs.com.wx5911xa.core;

import gov.hhs.com.wx5911xa.exceptions.YearNotImplementedException;
import gov.hhs.com.wx5911xa.viewmodel.PricerInput;
import gov.hhs.com.wx5911xa.viewmodel.PricerOutput;

import java.io.IOException;

/**
 * PricerApplication
 */
public class PricerApplication {
    public PricerOutput runPricer(PricerInput input) throws IOException, YearNotImplementedException {

        PricerDispatch pd = new PricerDispatch();
        return pd.runPricer(input);
    }


    
}
