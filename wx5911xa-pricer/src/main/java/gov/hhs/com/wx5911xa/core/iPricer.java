package gov.hhs.com.wx5911xa.core;

import gov.hhs.com.wx5911xa.viewmodel.PricerInput;
import gov.hhs.com.wx5911xa.viewmodel.PricerOutput;

public interface iPricer {
  PricerOutput run(PricerInput input);
}
