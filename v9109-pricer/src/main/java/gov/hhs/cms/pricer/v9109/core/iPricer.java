package gov.hhs.cms.pricer.v9109.core;

import gov.hhs.cms.pricer.v9109.viewmodel.PricerInput;
import gov.hhs.cms.pricer.v9109.viewmodel.PricerOutput;

public interface iPricer {
  PricerOutput run(PricerInput input);
}
