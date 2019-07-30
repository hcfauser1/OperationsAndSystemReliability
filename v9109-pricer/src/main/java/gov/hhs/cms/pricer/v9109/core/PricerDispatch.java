
package gov.hhs.cms.pricer.v9109.core;

import gov.hhs.cms.pricer.v9109.exceptions.YearNotImplementedException;
import gov.hhs.cms.pricer.v9109.load.CaseMixGroupCodeTableLoader;
import gov.hhs.cms.pricer.v9109.load.CoreBasedStatisticalAreaTableLoader;
import gov.hhs.cms.pricer.v9109.load.MetropolitanStatisticalAreaTableLoader;
import gov.hhs.cms.pricer.v9109.model.CaseMixGroupCodeTable;
import gov.hhs.cms.pricer.v9109.model.CoreBasedStatisticalArea;
import gov.hhs.cms.pricer.v9109.model.MetropolitanStatisticalArea;
import gov.hhs.cms.pricer.v9109.model.WageIndexMap;
import gov.hhs.cms.pricer.v9109.viewmodel.BillingRecord;
import gov.hhs.cms.pricer.v9109.viewmodel.PricerInput;
import gov.hhs.cms.pricer.v9109.viewmodel.PricerOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PricerDispatch {
  private WageIndexMap wageMap;
  private Map<String, iPricer> yearToPricer;
  private static final String YEAR_2019 = "2019";
  private static final String YEAR_2018 = "2018";
  private static final String YEAR_2017 = "2017";
  private static final String YEAR_2016 = "2016";
  private static final String YEAR_2015 = "2015";

  private final Logger logger = LoggerFactory.getLogger(PricerDispatch.class);

  public PricerDispatch() throws IOException{

    this.yearToPricer = new HashMap<>();
    List<CoreBasedStatisticalArea> cbsaTable = CoreBasedStatisticalAreaTableLoader.getCoreBasedStatisticalAreaTable();
    List<MetropolitanStatisticalArea> msaTable = MetropolitanStatisticalAreaTableLoader.getMetropolitanStatisticalAreaTable();
    wageMap = WageIndexMap.WageIndexMapFactory(
            cbsaTable,
            msaTable
    );

    initPricers();
}

  private void initPricers() throws IOException {
    yearToPricer.put(YEAR_2019, new Pricer2019(wageMap, loadCmgTable(YEAR_2019)));
    yearToPricer.put(YEAR_2018, new Pricer2018(wageMap, loadCmgTable(YEAR_2018)));
    yearToPricer.put(YEAR_2017, new Pricer2017(wageMap, loadCmgTable(YEAR_2017)));
    yearToPricer.put(YEAR_2016, new Pricer2016(wageMap, loadCmgTable(YEAR_2016)));
    yearToPricer.put(YEAR_2015, new Pricer2015(wageMap, loadCmgTable(YEAR_2015)));
    return;
  }

  private static CaseMixGroupCodeTable loadCmgTable(String year) throws IOException {
    CaseMixGroupCodeTable cmgTable = CaseMixGroupCodeTableLoader.getCaseMixGroupCodeTable(year);

    return cmgTable;

  }

  private void recordYearEvent(String year) {
    Map<String, Object> eventAttributes = new HashMap<>();
    final String eventName = "ValidYearSubmitted";
    eventAttributes.put("year", year);
    logger.info(eventName,eventAttributes);
  }

  private String getYear(BillingRecord bill) throws YearNotImplementedException {
    final String year;
    if (bill.getDischargeDate().compareTo("20180930") > 0
        && bill.getDischargeDate().compareTo("20191001") < 0) {
      year = YEAR_2019;
    } else if (bill.getDischargeDate().compareTo("20170930") > 0
        && bill.getDischargeDate().compareTo("20181001") < 0) {
      year = YEAR_2018;
    } else if (bill.getDischargeDate().compareTo("20160930") > 0
        && bill.getDischargeDate().compareTo("20171001") < 0) {
      year = YEAR_2017;
    } else if (bill.getDischargeDate().compareTo("20150930") > 0
        && bill.getDischargeDate().compareTo("20161001") < 0) {
      year = YEAR_2016;
    } else if (bill.getDischargeDate().compareTo("20140930") > 0
        && bill.getDischargeDate().compareTo("20151001") < 0) {
      year = YEAR_2015;
    } else {
      throw new YearNotImplementedException(bill.getDischargeDate());
    }

    recordYearEvent(year);
    return year;
  }

  public iPricer getPricer(PricerInput input) throws YearNotImplementedException {
    String year = getYear(input.getBillingRecord());
    return yearToPricer.get(year);
  }

  public PricerOutput runPricer(PricerInput input) throws YearNotImplementedException {
    final PricerOutput output;

    output = getPricer(input).run(input);

    final int resultCode = output.getRecord().getResult().getCode();
    if (ResultCode.isErrorCode(resultCode)) {
      final Map<String, Object> eventAttributes = new HashMap<>();
      eventAttributes.put("failureCode", resultCode);
      logger.warn("FailedSubmission",eventAttributes);
    }

    return output;
  }
}
