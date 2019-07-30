package gov.hhs.cms.pricer.v9109.core;

import gov.hhs.cms.pricer.v9109.model.CaseMixGroup;
import gov.hhs.cms.pricer.v9109.model.CaseMixGroupCodeTable;
import gov.hhs.cms.pricer.v9109.model.CoreBasedStatisticalArea;
import gov.hhs.cms.pricer.v9109.model.WageIndexMap;
import gov.hhs.cms.pricer.v9109.viewmodel.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Pricer2019 implements iPricer {
  private WageIndexMap wageIndexMap;
  private CaseMixGroupCodeTable cmgTable;

  private static final String PRICER_VERSION_2019 = "V19.0";

  private static final BigDecimal HALF_DAY_LENGTH_OF_STAY = BigDecimal.valueOf(0.5);
  private static final int THREE_DAY_LENGTH_OF_STAY = 3;
  private static final int FOURTEEN_DAY_LENGTH_OF_STAY = 14;
  private static final int SIXTEEN_DAY_LENGTH_OF_STAY = 16;
  // private static final double TWENTY_FIVE_PERCENT = 0.25;
  private static final BigDecimal EIGHTY_PERCENT = BigDecimal.valueOf(0.80);
  private static final String YES_INDICATOR = "Y";

  private static final String FY_MM11 = "11";
  private static final int MAX_LIFETIME_RESERVE_DAYS = 60;
  private static final String CMG_5001 = "5001";
  private static final String CMG_9999 = "9999";
  private static final String CMG_2103 = "2103";
  private static final String DATE_20011231 = "20011231";
  private static final String DATE_20020930 = "20020930";
  private static final String DATE_20030101 = "20030101";
  private static final String DATE_20050930 = "20050930";
  private static final int ONE_YEAR_TWO_MONTHS = 10200;
  private static final int ONE_YEAR_TWO_MONTHS_AFTER_NOVEMBER = 19000;

  private static final String CMG_A5001 = "A5001";
  private static final String CMG_A5101 = "A5101";
  private static final String CMG_A5102 = "A5102";
  private static final String CMG_A5103 = "A5103";
  private static final String CMG_A5104 = "A5104";

  private static final BigDecimal NATIONAL_LABOR_PERCENTAGE = BigDecimal.valueOf(0.70500);
  private static final BigDecimal NATIONAL_NON_LABOR_PERCENTAGE = BigDecimal.valueOf(0.29500);
  private static final BigDecimal NATIONAL_THRESHOLD_ADJUSTMENT = BigDecimal.valueOf(9402.00);
  private static final BigDecimal BUDGET_NEUTRAL_CONVERSION_AMOUNT = BigDecimal.valueOf(16021.00);
  private static final BigDecimal BUDGET_NEUTRAL_CONVERSION_AMOUNT2 = BigDecimal.valueOf(15705.00);
  // private static final double RURAL_ADJUSTMENT = 1.0497;
  private static final BigDecimal RURAL_ADJUSTMENT2 = BigDecimal.valueOf(1.1490);
  private static final BigDecimal CALC_LOW_INCOME_PERCENTAGE = BigDecimal.valueOf(0.3177);
  private static final BigDecimal CALC_TEACH = BigDecimal.valueOf(1.0163);

  private static final BigDecimal FEDERAL_RATE_FULL = BigDecimal.ONE;
  private static final BigDecimal FEDERAL_RATE_TWO_THIRDS = BigDecimal.valueOf(0.6667);
  private static final BigDecimal FACILITY_RATE_0 = BigDecimal.ZERO;
  private static final BigDecimal FACILITY_RATE_ONE_THIRD = BigDecimal.valueOf(0.3333);
  private static final BigDecimal RURAL_ADJUSTMENT_NONE = BigDecimal.ONE;
  private static final BigDecimal TRANSFER_PERCENT_FULL = BigDecimal.ONE;

  private static final String SPECIAL_PAY_INDICATOR = "1";

  // from ProvNewHold
  private static final String PROV_WAIVER_STATE = "Y";
  private static final String PROV_BLEND_INDICATOR_3 = "3";
  private static final String PROV_BLEND_INDICATOR_4 = "4";
  private static final String PROV_CBSA_HOSPITAL_QUALITY_INDICATOR_1 = "1";
  private static final Set<String> ORTHOPEDIC_CMG_PREFIX = new HashSet<>(Arrays.asList("07", "08", "09"));
  // This is the list of rural to urban providers; list not used for rural
  // adjustment in 2019
  /*
   * private static final Set<String> VALID_PROVIDERS = new HashSet<>(
   * Arrays.asList( "013027", "08T009", "10T249", "14T011", "193044", "193079",
   * "193096", "19T003", "19T144", "19T304", "28T023", "33T157", "34T015",
   * "34T131", "393047", "39T151", "42T036", "42T067", "49T018", "50T002"));
   */

  public Pricer2019(WageIndexMap wageIndexMap, CaseMixGroupCodeTable cmgTable) {
    this.wageIndexMap = wageIndexMap;
    this.cmgTable = cmgTable;
  }

  private static BigDecimal truncate(int places, BigDecimal num) {
    return num.setScale(places, RoundingMode.DOWN);
  }

  public ProspectivePaymentRecord mainline(BillingRecord bill, ProviderRecord provider) {
    ProspectivePaymentRecord ppsRecord = new ProspectivePaymentRecord();
    PricerResult result = priceClaim(bill, provider, ppsRecord);

    // *******************
    if (ResultCode.isErrorCode(result.getCode())) {
      String savedVersionCode = ppsRecord.getCalcVersionCode();
      BigDecimal savedChgOutThreshold = ppsRecord.getChargeOutlierThreshold();

      ppsRecord = new ProspectivePaymentRecord();
      ppsRecord.setCalcVersionCode(savedVersionCode);
      // Bug fix: Need to set the charge outlier threshold value if the error code is 67
      if (result.getResultCode() == ResultCode.OUTLIER_LOS_GT_COVERED_DAYS_67) {
        ppsRecord.setChargeOutlierThreshold(savedChgOutThreshold);
      }
    }

    ppsRecord.setResult(result);
    return ppsRecord;
  }

  public PricerResult priceClaim(
      BillingRecord bill, ProviderRecord provider, ProspectivePaymentRecord ppsRecord) {
        PricerResult result = new PricerResult();
    if (bill.getDischargeDate().compareTo("20180930") < 0
        || bill.getDischargeDate().compareTo("20191001") > 0) {
      result.setResultCode(ResultCode.BILL_OUT_OF_PRICER_YEAR_RANGE_97);
      return result;
    }

    ppsRecord.setCalcVersionCode(PRICER_VERSION_2019);

    // *****************************************************************
    // ** RULE: BILLS OLDER THAN JANUARY 1, 2002 WILL NOT BE PROCESSED *
    // ** AND WILL RETURN TO THE CALLING PROGRAM WITH A BILL OLDER THAN*
    // ** JANUARY 1, 2002 CODE (CODE 98) *
    // ** *
    // *****************************************************************
    if (bill.getDischargeDate().compareTo(DATE_20011231) <= 0) {
      result.setResultCode(ResultCode.BILL_OLDER_THAN_2002_98);
      return result;
    }

    CoreBasedStatisticalArea wageIndex;
    if (bill.getDischargeDate().compareTo(DATE_20050930) > 0) {
      wageIndex = getCbsa(bill.getDischargeDate(), provider);
    } else {
      result.setResultCode(ResultCode.BILL_OUT_OF_PRICER_YEAR_RANGE_97);
      return result;
    }

    if (wageIndex == null) {
      result.setResultCode(ResultCode.WAGE_INDEX_NOT_FD_60);
      return result;
    }

    // **********************
    result = editTheBillInfo(bill, provider, wageIndex, ppsRecord);
    if (result.getCode() != 0) {
      return result;
    }

    result = editCmgCode(ppsRecord);
    if (result.getCode() != 0) {
      return result;
    }

    return calcPayment(bill, provider, wageIndex, ppsRecord);
  }

  /**
   * getCbsa ***************************************************************** ** GET CBSA DATA *
   * *****************************************************************
   */
  protected CoreBasedStatisticalArea getCbsa(String dischargeDate, ProviderRecord provider) {
    // **************
    CbsaData cbsa = provider.getCbsaData();
    if (cbsa.getSpecialPaymentIndicator().equals(SPECIAL_PAY_INDICATOR)) {
      return new CoreBasedStatisticalArea(cbsa.getGeolocation(), null, cbsa.getWageIndex());
    }

    return this.wageIndexMap.getCbsaWageIndex(cbsa.getGeolocation(), dischargeDate);
  }

  /**
   * editTheBillInfo ************************************************************** ** BILL DATA
   * EDITS IF ANY FAIL SET PPS-RTC * ** AND DO NOT ATTEMPT TO PRICE. *
   * **************************************************************
   */
  protected PricerResult editTheBillInfo(
      BillingRecord bill,
      ProviderRecord provider,
      CoreBasedStatisticalArea wageIndex,
      ProspectivePaymentRecord ppsRecord) {
    // **********************
    PricerResult result = new PricerResult();
    result.setResultCode(ResultCode.OK_00);
    ppsRecord.setSubmittedCmgCode(bill.getCmgCode());

    ppsRecord.setNationalLaborPercent(NATIONAL_LABOR_PERCENTAGE);
    ppsRecord.setNationalNonLaborPercent(NATIONAL_NON_LABOR_PERCENTAGE);
    ppsRecord.setNationalThresholdAdjustment(NATIONAL_THRESHOLD_ADJUSTMENT);
    if (provider
        .getCbsaData()
        .getHospitalQualityIndicator()
        .equals(PROV_CBSA_HOSPITAL_QUALITY_INDICATOR_1)) {
      ppsRecord.setBudgetNeutralConversionAmount(BUDGET_NEUTRAL_CONVERSION_AMOUNT);
    } else {
      ppsRecord.setBudgetNeutralConversionAmount(BUDGET_NEUTRAL_CONVERSION_AMOUNT2);
    }

    // ********************
    // ** REQUIREMENT: THE SYSTEM MUST VALIDATE THAT THE LENGTH OF STAY
    // ** ON THE BILL IS NUMERIC
    // ** REQUIREMENT: THE SYSTEM MUST VALIDATE THAT THE BILLED LENGTH
    // ** OF STAY IS A NUMBER THAT IS ZERO OR GREATER

    // ** RULE: A BILLED LENGTH OF STAY FOR ZERO DAYS, MEANING THE
    // ** PATIENT WAS RELEASED ON THE SAME DAY, IS ASSIGNED A VALUE OF
    // ** ONE DAY
    // ** REQUIREMENT: THE SYSTEM MUST RETURN AN INVALID LENGTH OF STAY
    // ** CODE (56) TO THE CALLING PROGRAM IF THE BILLED LENGTH OF STAY
    // ** IN NOT NUMERIC OR IS A NUMBER LESS THAN ZERO
    if (bill.getLengthOfStay() == 0) {
      ppsRecord.setLengthOfStay(1);
    } else if (bill.getLengthOfStay() > 0) {
      ppsRecord.setLengthOfStay(bill.getLengthOfStay());
    } else {
      result.setResultCode(ResultCode.INVALID_LENGTH_OF_STAY_56);
      return result;
    }

    PpsComponents components = new PpsComponents();
    components.setFiscalYearBeginDate(provider.getFiscalYearBeginDate());
    components.setPpsBlendIndicator(provider.getFederalPpsBlendIndicator());
    // ** RULE: BILLS WITH A DISCHARGE DATE FOR PATIENTS WHO HAVE
    // ** BEEN IN A FACILITY LONGER THAN 14 MONTHS OR PROVIDERS WITH A
    // ** NEW FISCAL YEAR BEGIN DATE FROM
    // ** OCTOBER 1, 2002 TO DECEMBER 31, 2002 SHALL
    // ** HAVE THEIR BLEND INDICATOR SET SO THAT THE FEDERAL GOVERNMENT
    // ** PAYS 100% OF THE BILL (BLEND INDICATOR = 4)
    // ** THE 14 MONTHS IS THE CALCULATION
    // ** (ADDING 10200 OR 19000), WHICH
    // ** WORKS OUT TO 14 MONTHS DEPENDING ON IF THE MONTH IS LESS THAN
    // ** 11 OR >= 11. THE DATE FORMAT IS YYYYMMDD, SO IF THE MONTH IS
    // ** LESS THAN 11 YOU ARE ADDING 1 YEAR AND 2 MONTHS(14 MONTHS).
    // ** IF THE DATE IS EQUAL TO 11 OR 12 YOU ADD 1 YEAR AND WHAT LOOKS
    // ** LIKE 90 MONTHS, BUT IT WORKS OUT TO 1 YEAR AND 2 MONTHS. SO
    // ** FOR 20171101, YOU ADD 19000 AND YOU GET 20190101
    if (FY_MM11.compareTo(components.getFiscalYearBeginDate().substring(4, 6)) > 0) {
      // TODO: Actually make these dates because this hack is terrible
      components.setFiscalYearBeginDate(
          "" + (Integer.parseInt(components.getFiscalYearBeginDate()) + ONE_YEAR_TWO_MONTHS));
    } else {
      components.setFiscalYearBeginDate(
          ""
              + (Integer.parseInt(components.getFiscalYearBeginDate())
                  + ONE_YEAR_TWO_MONTHS_AFTER_NOVEMBER));
    }

    components.setDischargeDate(bill.getDischargeDate());
    if ((components.getDischargeDate().compareTo(components.getFiscalYearBeginDate()) > 0)
        || ((provider.getFiscalYearBeginDate().compareTo(DATE_20020930) > 0)
            && (provider.getFiscalYearBeginDate().compareTo(DATE_20030101) < 0))) {
      components.setPpsBlendIndicator(PROV_BLEND_INDICATOR_4);
    }
    // ** RULE: PROVIDERS WITH A NEW FISCAL YEAR BEGIN DATE IN 2002 OR
    // ** GREATER AND LESS THAN THE BILL'S DISCHARGE DATE AND HAVING
    // ** THEIR BLEND RATE INDICATING THAT THE GOVERNMENT WILL PAY 100%
    // ** SHALL HAVE THEIR FEDERAL RATE PERCENTAGE SET TO 100% AND THEIR
    // ** PROVIDER RATE SET TO 0%
    if ((provider.getFiscalYearBeginDate().compareTo(DATE_20011231) <= 0)) {
      result.setResultCode(ResultCode.PROV_FY_PRIOR_TO_2002_74);
      return result;
    }

    if (provider.getFiscalYearBeginDate().compareTo(bill.getDischargeDate()) > 0) {
      result.setResultCode(ResultCode.DISCHRG_PRIOR_TO_PROV_FY_73);
      return result;
    }

    if (components.getPpsBlendIndicator().equals(PROV_BLEND_INDICATOR_4)) {
      ppsRecord.setFederalRatePercent(FEDERAL_RATE_FULL);
      ppsRecord.setFacilityRatePercent(FACILITY_RATE_0);
    } else if (components.getPpsBlendIndicator().equals(PROV_BLEND_INDICATOR_3)) {
      // ** RULE: PROVIDERS WITH A NEW FISCAL YEAR BEGIN DATE IN 2002 OR
      // ** GREATER AND LESS THAN THE BILL'S DISCHARGE DATE AND HAVING
      // ** THEIR BLEND RATE INDICATING THAT THE THERE IS A SPLIT BETWEEN
      // ** THE GOVERNMENT AND THE PROVIDER SHALL HAVE THEIR FEDERAL RATE
      // ** PERCENTAGE SET TO .6667% AND THEIR PROVIDER RATE SET TO .3333%
      ppsRecord.setFederalRatePercent(FEDERAL_RATE_TWO_THIRDS);
      ppsRecord.setFacilityRatePercent(FACILITY_RATE_ONE_THIRD);
    } else {
      // ** RULE: PAYMENTS CAN ONLY BE 100% FEDERAL REIMBURSED (BLEND
      // ** INDICATOR = 4) OR SPLIT BETWEEN THE PROVIDER AND THE FEDERAL
      // ** GOVERNMENT (BLEND INDICATOR = 3)
      // ** REQUIREMENT: THE SYSTEM MUST RETURN AN ERROR CODE (72) TO THE
      // ** CALLING PROGRAM IF THE PPS BLEND INDICATOR NOT A 3 (SPLIT
      // ** PAYMENT) OR A 4 (100% GOVERNMENT PAID)
      // early exit
      result.setResultCode(ResultCode.INVALID_BLEND_IND_72);
      return result;
    }

    // ** RULE: WAIVER STATE PRICES ARE NOT CALCULATED BY THE PRICER
    // ** PROGRAMS
    // ** REQUIREMENT: THE SYSTEM MUST RETRUN AN ERROR CODE (53) TO THE
    // ** CALLING PROGRAM IF THE PRICER PROGRAM IS ASKED TO PRICE A
    // ** PAYMENT FROM A WAIVER STATE
    // ** CHECK PPS-RTC
    // ** FOR ERRORS, IN SUCCESSION
    if (provider.getWaiverCode().equals(PROV_WAIVER_STATE)) {
      result.setResultCode(ResultCode.WAIVER_STATE_NOT_CALC_53);
      return result;
    }

    // ** RULE: PAYMENTS WILL NOT BE MADE WHEN THE CLAIM'S DISCHARGE
    // ** DATE IS LESS THAN THE PROVIDER'S PSF RECORD'S EFFECTIVE DATE
    // ** OR THE WAGE INDEX EFFECTIVE DATE
    // ** REQUIREMENT: THE SYSTEM MUST RETURN AN ERROR CODE (55) TO THE
    // ** CALLING PROGRAM WHEN THE CLAIM'S DISCHARGE DATE IS BEFORE/LESS
    // ** THAN THE PROVIDER'S PSF RECORD'S EFFECTIVE DATE OR WAGE INDEX
    // ** EFFECTIVE DATE
    if (!provider.getCbsaData().getSpecialPaymentIndicator().equals(SPECIAL_PAY_INDICATOR)
        && (bill.getDischargeDate().compareTo(provider.getEffectiveDate()) < 0
            || bill.getDischargeDate().compareTo(wageIndex.getEffectiveDate()) < 0)) {
      result.setResultCode(ResultCode.DISCHRG_DT_LT_EFF_START_DT_55);
      return result;
    }
    // ** RULE: A CLAIM WILL NOT BE PAID IF THE CLAIM'S DISCHARGE DATE
    // ** IS ON OR AFTER THE DATE WHEN A PROVIDER TERMINATED COVERAGE
    // ** REQUIREMENT: THE SYSTEM MUST RETURN AN ERROR CODE (51) WHEN
    // ** THE BILL'S DISCHARGE DATE IS ON/AFTER THE PROVIDER TERMINATION
    // ** DATE
    if (provider.getTerminationDate().compareTo("00000000") > 0
        && bill.getDischargeDate().compareTo(provider.getTerminationDate()) >= 0) {
      result.setResultCode(ResultCode.PROV_REC_TERMINATED_51);
      return result;
    }
    // ** REQUIREMENT: THE SYSTEM MUST RETURN AN ERROR CODE (58) IF THE
    // ** BILL'S COVERED CHARGES ARE NOT NUMERIC VALUES
    // TODO: Figure out how to keep this as a specific error code
    // if (! CBLUtil.isNumber(billNewData.getObjectBCovCharges())) {
    // ppsRecord.setResultCode(PpsDataAll.TOT_COVERED_CHRGS_NOT_NUM_58);
    // return;
    // }
    // ** REQUIREMENT: THE SYSTEM MUST RETURN AN ERROR CODE IF THE
    // ** BILL'S LIFETIME RESERVE DAYS IS NOT NUMERIC OR IS GREATER
    // ** THAN 60
    // ** RULE: A BILL'S LIFETIME RESERVE DAYS USED MUST BE INCLUDED
    // ** WHEN CALCULATING A PPS LIFETIME RESERVED DAYS USED
    if (bill.getLifetimeReserveDays() > MAX_LIFETIME_RESERVE_DAYS) {
      result.setResultCode(ResultCode.LTR_NOT_NUM_OR_GT60_61);
      return result;
    }

    ppsRecord.setLifetimeReserveDaysUsed(bill.getLifetimeReserveDays());
    // ** REQUIREMENT: THE SYSTEM MUST RETURN AN ERROR CODE FOR AN
    // ** INVALID NUMBER OF COVERED DAYS (CODE = 62) IF THE NUMBER OF
    // ** BILLED DAYS IS NOT NUMERIC
    // ** REQUIREMENT: THE SYSTEM MUST RETURN AN ERROR CODE FOR AN
    // ** INVALID NUMBER OF COVERED DAYS (CODE = 62) IF THE NUMBER OF
    // ** BILLED DAYS IS EQUAL TO ZERO BUT THE LENGTH OF STAY IS GREATER
    // ** THAN ZERO DAYS
    if (bill.getCoveredDays() == 0 && ppsRecord.getLengthOfStay() > 0) {
      result.setResultCode(ResultCode.INVALID_NBR_COVERED_DAYS_62);
      result.setExplanation(
          "Number of billed days must be numeric and not equal to zero if length of stay is greater than zero");
      return result;
    }
    // ** RULE: A BILL IS NOT PAID IF THE PATIENT HAS EXCEEDED THEIR
    // ** ALLOWABLE LIFETIME DAYS OF COVERAGE
    // ** REQUIREMENT: THE SYSTEM MUST RETURN AN ERROR CODE FOR AN
    // ** INVALID NUMBER OF COVERED DAYS (CODE = 62) IF THE PATIENT HAS
    // ** EXCEEDED THEIR LIFETIME COVERAGE DAYS
    // ** RULE: THE PPS REGULAR DAYS USED IS SET TO THE BILL'S COVERED
    // ** DAYS MINUS THE BILL'S LIFETIME RESERVE DAYS WHEN THE PATIENT
    // ** HAS LIFETIME RESERVE DAYS LEFT
    if (bill.getLifetimeReserveDays() > bill.getCoveredDays()) {
      result.setResultCode(ResultCode.INVALID_NBR_COVERED_DAYS_62);
      return result;
    }

    ppsRecord.setRegularDaysUsed(bill.getCoveredDays() - bill.getLifetimeReserveDays());

    // ** RULE: THE PPS REGULAR DAYS USED IS SET TO WHICHEVER IS THE
    // ** LOWER VALUE, THE BILL'S LENGTH OF STAY OR THE PPS REGULAR DAYS
    // ** USED.
    if (ppsRecord.getRegularDaysUsed() > 0
        && ppsRecord.getRegularDaysUsed() > ppsRecord.getLengthOfStay()) {
      ppsRecord.setRegularDaysUsed(ppsRecord.getLengthOfStay());
    } else if (bill.getLifetimeReserveDays() > ppsRecord.getLengthOfStay()) {
      ppsRecord.setLifetimeReserveDaysUsed(ppsRecord.getLengthOfStay());
    } else {
      ppsRecord.setLifetimeReserveDaysUsed(bill.getLifetimeReserveDays());
    }

    ppsRecord.setFacilitySpecificRatePreblend(provider.getFacilitySpecificRate());
    if (components.getPpsBlendIndicator().equals(PROV_BLEND_INDICATOR_3)
        && ppsRecord.getFacilitySpecificRatePreblend().compareTo(BigDecimal.ZERO) == 0) {
      result.setResultCode(ResultCode.PROV_SPEC_RT_0_FOR_BLEND_57);
      return result;
    }

    // TODO: Add validation return code?
    // if (!CBLUtil.isNumber(provNewHold.getObjectFacSpecRate())) {
    // ppsRecord.setResultCode(PpsDataAll.PROV_SPEC_RATE_NOT_NUM_50);
    // return;
    // }
    // TODO: Add validation return code?
    // if (! CBLUtil.isNumber(provNewHold.getObjectOperCstchgRatio())) {
    // ppsRecord.setResultCode(PpsDataAll.COST_TO_CHRG_NOT_NUM_65);
    // return;
    // }

    if (wageIndex == null || wageIndex.getWageIndex().compareTo(BigDecimal.ZERO) <= 0) {
      result.setResultCode(ResultCode.INVALID_WAGE_INDEX_52);
      return result;
    }

    ppsRecord.setCoreBasedStatisticalArea(wageIndex.getCbsaNumber());
    ppsRecord.setWageIndex(wageIndex.getWageIndex());
    return result;
  }

  /**
   * editCmgCode ************************************************************** ** FIND THE CMG CODE
   * IN THE TABLE. * ** CALL THE SUBMODULE FSSBIRFT, WHICH CONTAINS THE * ** LOGIC TO POPULATE PPS-
   * FIELDS FOR A PARTICULAR FISCAL * ** YEAR. CALL WITH PARM = 'CMG '. *
   * **************************************************************
   */
  protected PricerResult editCmgCode(ProspectivePaymentRecord ppsRecord) {
    PricerResult result = new PricerResult();
    result.setResultCode(ResultCode.OK_00);

    String cmgCode = ppsRecord.getSubmittedCmgCode();

    if (cmgCode.length() < 5) {
      result.setResultCode(ResultCode.CMG_ON_CLAIM_NOT_IN_TABLE_54);
      return result;
    }

    String cmgNumeric = cmgCode.substring(1, 5);

    // ********************
    // ** REQUIREMENT: THE SYSTEM MUST RETURN AN ERROR CODE (CODE = 54)
    // ** IF THE PPS CASE MIX GROUP IS NOT NUMERIC OR IS GREATER THAN
    // ** '2102'
    if (!cmgNumeric.equals(CMG_9999)
        && !cmgNumeric.equals(CMG_5001)
        && cmgNumeric.compareTo(CMG_2103) >= 0) {
      result.setResultCode(ResultCode.CMG_ON_CLAIM_NOT_IN_TABLE_54);
      result.setExplanation("PPS case mix group is not numeric or greater than 2102");
      return result;
    }

    CaseMixGroup cmgEntry = cmgTable.getCmgEntry(cmgNumeric);
    if (cmgEntry == null) {
      result.setResultCode(ResultCode.CMG_ON_CLAIM_NOT_IN_TABLE_54);
      return result;
    }

    // *****************************
    // ** RULE: CASE MIX GROUPS MUST HAVE ONE OF FOUR SEVERITY LEVELS;
    // ** A, B, C, OR D
    // ** REQUIREMENT: THE SYSTEM MUST BE ABLE TO IDENTIFY AND USE THE
    // ** PPS-RELATIVE-WEIGHT AND PPS-AVERAGE LENGTH OF STAY FOR THE
    // ** CLAIM'S ASSIGNED CASE MIX GROUP SEVERITY LEVEL (A, B, C, OR D)
    if (ppsRecord.getSubmittedCmgCode().startsWith("A")) {
      ppsRecord.setRelativeWeight(cmgEntry.getARelativeWeight());
      ppsRecord.setAverageLengthOfStay(cmgEntry.getAAverageLengthOfStay());
    } else if (ppsRecord.getSubmittedCmgCode().startsWith("B")) {
      ppsRecord.setRelativeWeight(cmgEntry.getBRelativeWeight());
      ppsRecord.setAverageLengthOfStay(cmgEntry.getBAverageLengthOfStay());
    } else if (ppsRecord.getSubmittedCmgCode().startsWith("C")) {
      ppsRecord.setRelativeWeight(cmgEntry.getCRelativeWeight());
      ppsRecord.setAverageLengthOfStay(cmgEntry.getCAverageLengthOfStay());
    } else if (ppsRecord.getSubmittedCmgCode().startsWith("D")) {
      ppsRecord.setRelativeWeight(cmgEntry.getDRelativeWeight());
      ppsRecord.setAverageLengthOfStay(cmgEntry.getDAverageLengthOfStay());
      // ** REQUIREMENT: THE SYSTEM MUST RETURN AN ERROR CODE (CODE = 54)
      // ** IF THE CLAIM'S CASE MIX GROUP DOES NOT HAVE A VALID SEVERITY
      // ** LEVEL (EITHER A, B, C, OR D)
    } else {
      result.setResultCode(ResultCode.CMG_ON_CLAIM_NOT_IN_TABLE_54);
      result.setExplanation("CMG provided in claim doesn't have a valid severity level");
      return result;
    }
    return result;
  }

  /**
   * checkRuralAdj ************************************************************** ** POPULATE RURAL
   * ADJUSTMENT AMOUNT * ** POPULATE FIELD USING THE LS-RURAL-ADJ* FIELDS VALUES FOR * ** THAT YEAR
   * (DERIVED FROM WORKING-STORAGE TABLE) *
   * **************************************************************
   */
  protected void checkRuralAdj(
      BillingRecord bill, CoreBasedStatisticalArea wageIndex, ProspectivePaymentRecord ppsRecord) {
    // **************************
    // **************************************************************
    // ** FOR FY17, IF PROVIDER IS FOUND ON TABLE, USE 1.0497 *
    // **************************************************************
    // ** RULE: A BILLING PROVIDER RECEIVES A PPS RURAL ADJUSTMENT
    // ** PERCENTAGE OF 1.0497 PERCENT IF THE PROVIDER IS A VALID RURAL
    // ** TO URBAN PROVIDER OR 1.1490 PERCENT IF THE THE PROVIDER HAS NO
    // ** CORE BASED STATISTICAL AREA ASSIGNED
    // ** IF VALID RURAL TO URBAN PROVIDER, USE THE 1/3 ADJUSTMENT
    // **************************************************************
    // ** FOR FY16, IF PROVIDER IS FOUND ON TABLE, USE 1.0993 *
    // **************************************************************
    // ** RULE: A BILLING PROVIDER RECEIVES A PPS RURAL ADJUSTMENT
    // ** PERCENTAGE OF 1.0993 PERCENT IF THE PROVIDER IS A VALID RURAL
    // ** TO URBAN PROVIDER OR 1.1490 PERCENT IF THE THE PROVIDER HAS NO
    // ** CORE BASED STATISTICAL AREA ASSIGNED
    // ** IF VALID RURAL TO URBAN PROVIDER, USE THE 2/3 ADJUSTMENT
    int cbsaInt = Integer.parseInt(wageIndex.getCbsaNumber().trim());
    ppsRecord.setRuralAdjustment(RURAL_ADJUSTMENT_NONE);
    /*
     * if (VALID_PROVIDERS.contains(bill.getProviderNumber())) {
     * ppsRecord.setRuralAdjustment(RURAL_ADJUSTMENT_NONE); } else
     */
    if (cbsaInt < 100) {
      ppsRecord.setRuralAdjustment(RURAL_ADJUSTMENT2);
    } else {
      ppsRecord.setRuralAdjustment(RURAL_ADJUSTMENT_NONE);
    }
  }

  private static boolean isOrthopedicBill(String cmgCode) {
    return ORTHOPEDIC_CMG_PREFIX.contains(cmgCode.substring(1, 3));
  }

  /**
   * calcPayment ************************************************************** ** THE BILL DATA HAS
   * PASSED ALL EDITS (RTC=00). SO * ** CALL THE SUBMODULE FSSBIRFT, WHICH CONTAINS THE * ** LOGIC
   * TO POPULATE PPS- FIELDS FOR A PARTICULAR FISCAL * ** YEAR. CALL WITH PARM = 'CALC 'IN ORDER TO:
   * * ** CALCULATE THE FEDERAL PORTION * ** CALCULATE THE HOSPITAL PORTION * ** CALCULATE THE
   * COST-OUTLIER PORTION * ** CALCULATE THE LIP ADJUSTMENT PERCENTAGE *
   * **************************************************************
   */
  protected PricerResult calcPayment(
      BillingRecord bill,
      ProviderRecord provider,
      CoreBasedStatisticalArea wageIndex,
      ProspectivePaymentRecord ppsRecord) {
    PpsComponents components = new PpsComponents();
    // *******************
    // ** RULE: WHEN PRICING A CLAIM, THE PPS LOW INCOME PATIENT
    // ** ADJUSTMENT PERCENTAGE IS CALCULATED AS ((1 + (THE PROVIDERS
    // ** SSI RATIO + THE PROVIDERS MEDICAID RATIO) RAISED TO THE POWER
    // ** OF .3177) - 1) ROUNDED
    components.setDisproportionateShareAdjustment(
        provider.getSsiRatio().add(provider.getMedicaidRatio()));

    // These two calculations below are done as doubles instead of as BigDecimal because BigDecimal
    // doesn't have a pow() function that accepts another BigDecimal as the power -- it only takes
    // integers as powers. There are some implementations out there that we could use if we find
    // that this is an issue, but for now it doesn't seem to make a difference and doesn't seem
    // worth the effort since this should be very close
    double dbLowIncomePaymentPercent =
        Math.pow(
                (components.getDisproportionateShareAdjustment().add(BigDecimal.ONE)).doubleValue(),
                CALC_LOW_INCOME_PERCENTAGE.doubleValue())
            - 1.0;
    ppsRecord.setLowIncomePaymentPercent(BigDecimal.valueOf(dbLowIncomePaymentPercent));
    // ** RULE: WHEN PRICING A CLAIM, THE TEACH PERCENTAGE IS CALCULATED
    // ** AS (((1 + THE PROVIDERS NEW CAPI) RAISED TO THE POWER OF
    // ** 1.0163) MINUS 1) ROUNDED
    double dbTeachPercent =
        Math.pow(
                (provider.getCapitalIndirectMedicalEducationRatio().add(BigDecimal.ONE))
                    .doubleValue(),
                CALC_TEACH.doubleValue())
            - 1.0;
    components.setTeachPercent(BigDecimal.valueOf(dbTeachPercent));
    ppsRecord.setTransferPercent(TRANSFER_PERCENT_FULL);

    // *******************
    // ** RULE: THE PROVIDER TRANSFER PERCENTAGE FOR PATIENTS (WHOSE
    // ** PATIENT STATUS CODE INDICATES THAT THEY WERE TRANSFERRED TO
    // ** ANOTHER FACILITY) WHEN THE BILLED LENGTH OF STAY IS LESS THAN
    // ** THE PPS NORMAL AVERAGE LENGTH OF STAY IS COMPUTED AS THE
    // ** BILLED LENGTH OF STAY PLUS A HALF DAY DIVIDED BY THE PPS
    // ** NORMAL AVERAGE LENGTH OF STAY.
    // ** IN OTHER WORDS, THE PROVIDER GETS AN
    // ** ADDITIONAL HALF DAY ADDED TO THEIR TRANSFER PERCENTAGE
    // ** CALCUATION IF THEY TRANSFER THE PATIENT IN LESS TIME THAN IS
    // ** NORMALLY AVERAGE FOR THE TYPE OF CLAIM.
    // ** RULE: THE PPS SUBMITTED CASE MIX GROUP CODE IS USED AS THE PPS
    // ** PRICED CASE MIX GROUP CODE FOR PATIENTS TRANSFERRED TO ANOTHER
    // ** FACILITY (CODES 02, 03, 61, 62, 63, 64, 82, 83, 89, 90, 91,
    // ** 92) WHEN THE BILLED LENGTH OF STAY IS LESS THAN THE PPS NORMAL
    // ** AVERAGE LENGTH OF STAY.
    // ** TRANSFERRED PATIENTS
    if (bill.isPatientTransferred()
        && ppsRecord.getLengthOfStay() < ppsRecord.getAverageLengthOfStay()) {
      // Using a scale of 4 here since the ppsRecord.transferPercent is a 4-decimal value
      ppsRecord.setTransferPercent(
          BigDecimal.valueOf(ppsRecord.getLengthOfStay())
              .add(HALF_DAY_LENGTH_OF_STAY)
              .divide(
                  BigDecimal.valueOf(ppsRecord.getAverageLengthOfStay()), 4, RoundingMode.DOWN));
      ppsRecord.setPricedCmgCode(ppsRecord.getSubmittedCmgCode());
      // ** RULE: BILLS FOR PATIENTS SHALL USE THE PPS CASE MIX GROUP CODE
      // ** OF A5001, ASSOCIATED WITH THE RATE IN THE TABLE, WHEN A BILL
      // ** SHOWS A STAY OF THREE DAYS OR LESS
      // ** STAY OF 3 DAYS OR LESS
    } else if (ppsRecord.getLengthOfStay() <= THREE_DAY_LENGTH_OF_STAY) {
      ppsRecord.setPricedCmgCode(CMG_A5001);
      ppsRecord.setRelativeWeight(cmgTable.getCmgEntry("5001").getARelativeWeight());
      // ** RULE: BILLS FOR PATIENTS WHO DID NOT EXPIRE WHILE IN CARE WILL
      // ** USE THE PPS SUBMITTED CASE MIX GROUP CODE AS THE PPS PRICED
      // ** CASE MIX GROUP CODE.
      // ** PATIENT HAS NOT EXPIRED
    } else if (!bill.isPatientExpired()) {
      ppsRecord.setPricedCmgCode(ppsRecord.getSubmittedCmgCode());
      // ** RULE: PATIENTS WHO EXPIRED DURING CARE, AND WHO HAD AN
      // ** ORTHOPEDIC BILL SUBMITTED, AND THE PATIENT STAY WAS LESS THAN
      // ** 14 DAYS, SHALL USE THE PPS CASE MIX GROUP CODE OF A5101,
      // ** ASSOCIATED WITH THE RATE IN THE TABLE
      // ** PATIENT HAS EXPIRED AND
      // ** THIS IS AN ORTHOPEDIC BILL
    } else if (isOrthopedicBill(ppsRecord.getSubmittedCmgCode())) {
      if (ppsRecord.getLengthOfStay() < FOURTEEN_DAY_LENGTH_OF_STAY) {
        ppsRecord.setPricedCmgCode(CMG_A5101);
        ppsRecord.setRelativeWeight(cmgTable.getCmgEntry("5101").getARelativeWeight());
        // ** RULE: PATIENTS WHO EXPIRED DURING CARE, AND WHO HAD AN
        // ** ORTHOPEDIC BILL SUBMITTED, AND THE PATIENT STAY WAS 14 DAYS OR
        // ** LONGER, SHALL USE THE PPS CASE MIX GROUP CODE OF A5102,
        // ** ASSOCIATED WITH THE RATE IN THE TABLE.
      } else {
        ppsRecord.setPricedCmgCode(CMG_A5102);
        ppsRecord.setRelativeWeight(cmgTable.getCmgEntry("5102").getARelativeWeight());
      }
      // ** RULE: PATIENTS WHO EXPIRED DURING CARE, AND WHO'S BILL IS NOT
      // ** AN ORTHOPEDIC BILL, AND THE PATIENT STAY WAS LESS THAN 16
      // ** DAYS, SHALL USE THE PPS CASE MIX GROUP CODE OF A5103,
      // ** ASSOCIATED WITH THE RATE IN THE TABLE
      // ** PATIENT HAS EXPIRED AND
      // ** NOT AN ORTHOPEDIC BILL
    } else if (ppsRecord.getLengthOfStay() < SIXTEEN_DAY_LENGTH_OF_STAY) {
      ppsRecord.setPricedCmgCode(CMG_A5103);
      ppsRecord.setRelativeWeight(cmgTable.getCmgEntry("5103").getARelativeWeight());
      // ** RULE: PATIENTS WHO EXPIRED DURING CARE, AND WHO'S BILL IS NOT
      // ** AN ORTHOPEDIC BILL, AND THE PATIENT STAY WAS 16 DAYS OR
      // ** GREATER, SHALL USE THE PPS CASE MIX GROUP CODE OF A5104,
      // ** ASSOCIATED WITH THE RATE IN THE TABLE
    } else {
      ppsRecord.setPricedCmgCode(CMG_A5104);
      ppsRecord.setRelativeWeight(cmgTable.getCmgEntry("5104").getARelativeWeight());
    }

    ppsRecord.setStandardPaymentAmount(
        truncate(
            2,
            ppsRecord
                .getTransferPercent()
                .multiply(ppsRecord.getRelativeWeight())
                .multiply(ppsRecord.getBudgetNeutralConversionAmount())));

    checkRuralAdj(bill, wageIndex, ppsRecord);

    // ** RULE: THE LABOR PORTION OF THE PAYMENT IS COMPUTED AS THE PPS
    // ** STANDARD PAY AMOUNT TIMES THE PPS NATIONAL LABOR PERCENTAGE
    // ** TIMES THE PPS WAGE INDEX
    components.setLaborPortion(
        truncate(
            6,
            ppsRecord
                .getStandardPaymentAmount()
                .multiply(ppsRecord.getNationalLaborPercent())
                .multiply(ppsRecord.getWageIndex())));
    // ** RULE: THE NON LABOR PORTION OF THE PAYMENT IS COMPUTED AS THE
    // ** PPS STANDARD PAY AMOUNT TIMES THE PPS NATIONAL NON LABOR
    // ** PERCENTAGE
    components.setNonLaborPortion(
        truncate(
            6,
            ppsRecord.getStandardPaymentAmount().multiply(ppsRecord.getNationalNonLaborPercent())));

    // ** RULE: THE PPS FEDERAL PAY AMOUNT IS COMPUTED AS THE ((LABOR
    // ** PORTION OF THE PAYMENT PLUS THE NON-LABOR PORTION OF THE
    // ** PAYMENT) TIMES THE PPS RURAL ADJUSTMENT FACTOR) ROUNDED.
    ppsRecord.setFederalPaymentAmount(
        (components
            .getLaborPortion()
            .add(components.getNonLaborPortion())
            .multiply(ppsRecord.getRuralAdjustment())));
    // ** RULE: THE PPS LOW INCOME PATIENT PAYMENT AMOUNT IS COMPUTED AS
    // ** THE PPS FEDERAL PAY AMOUNT TIMES THE PPS LOW INCOME PATIENT
    // ** PERCENTAGE) ROUNDED
    ppsRecord.setLowIncomePaymentAmount(
        ppsRecord.getFederalPaymentAmount().multiply(ppsRecord.getLowIncomePaymentPercent()));
    // ** RULE: THE PPS TEACH PAY AMOUNT IS COMPUTED AS THE PPS FEDERAL
    // ** PAY AMOUNT TIMES THE PROVIDERS TEACH PERCENTAGE) ROUNDED
    ppsRecord.setTeachPaymentAmount(
        ppsRecord.getFederalPaymentAmount().multiply(components.getTeachPercent()));

    // *******************
    // ** RULE: THE PPS FACILITIES COSTS ARE COMPUTED AS (THE COVERED
    // ** CHARGES ON THE BILL TIMES THE PROVIDER'S NEW OPERATING COST TO
    // ** CHARGE RATIO) ROUNDED
    ppsRecord.setFacilityCosts(bill.getCoveredCharges().multiply(provider.getCostToChargeRatio()));

    // ** RULE: THE OUTLIER LABOR PORTION IS CALCULATED AS THE (PPS
    // ** NATIONAL THRESHHOLD ADJUSMENT FACTOR TIMES PPS NATIONAL LABOR
    // ** PERCENTAGE) TIMES THE PPS WAGE INDEX
    components.setOutlierLaborPortion(
        ppsRecord
            .getNationalThresholdAdjustment()
            .multiply(ppsRecord.getNationalLaborPercent())
            .multiply(ppsRecord.getWageIndex()));
    // ** RULE: THE OUTLIER NON LABOR PORTION IS CALCULATED AS THE PPS
    // ** NATIONAL THRESHHOLD ADJUSMENT FACTOR TIMES PPS NATIONAL NON
    // ** LABOR PERCENTAGE
    components.setOutlierNonLaborPortion(
        ppsRecord
            .getNationalThresholdAdjustment()
            .multiply(ppsRecord.getNationalNonLaborPercent()));
    // ** RULE: THE FP OUTLIER THRESHHOLD IS COMPUTED AS THE ((PAYMENT
    // ** OUTLIER LABOR PORTION PLUS THE PAYMENT OUTLIER NON LABOR
    // ** PORTION) TIMES THE PPS RURAL ADJUSTMENT TIMES THE (PPS LOW
    // ** INCOME PATIENT PERCENT PLUS THE TEACH PERCENT + 1)) ROUNDED.
    components.setFpOutlierThreshold(
        components
            .getOutlierLaborPortion()
            .add(components.getOutlierNonLaborPortion())
            .multiply(ppsRecord.getRuralAdjustment())
            .multiply(
                ppsRecord
                    .getLowIncomePaymentPercent()
                    .add(components.getTeachPercent())
                    .add(BigDecimal.ONE)));
    // ** RULE: THE OUTLIER THRESHHOLD IS COMPUTED AS THE (PPS FEDERAL
    // ** PAYMENT AMOUNT PLUS THE FP OUTLIER THRESHHOLD PLUS THE PPS LOW
    // ** INCOME PATIENT PAYMENT AMOUNT PLUS THE TEACH PPS PAYMENT
    // ** AMOUNT) ROUNDED.
    components.setOutlierThreshold(
        ppsRecord
            .getFederalPaymentAmount()
            .add(components.getFpOutlierThreshold())
            .add(ppsRecord.getLowIncomePaymentAmount())
            .add(ppsRecord.getTeachPaymentAmount()));
    // Note: setting higher precision component calculation above
    ppsRecord.setOutlierThreshold(truncate(2, components.getOutlierThreshold()));

    // ** RULE: THE PPS OUTLIER PAYMENT AMOUNT IS COMPUTED AS 80% OF
    // ** (THE PPS FACILITY COSTS MINUS THE OUTLIER THRESHHOLD) WHEN THE
    // ** PPS FACILITY COSTS ARE GREATER THAN THE OUTLIER THRESHHOLD. IN
    // ** OTHER WORDS, ONLY 80% OF THE COST DIFFERENTIAL IS PAID.
    if (ppsRecord.getFacilityCosts().compareTo(components.getOutlierThreshold()) > 0) {
      ppsRecord.setOutlierPaymentAmount(
          ppsRecord
              .getFacilityCosts()
              .subtract(components.getOutlierThreshold())
              .multiply(EIGHTY_PERCENT));
    }
    // ** RULE: THE CHARGE OUTLIER THRESHHOLD IS COMPUTED AS THE
    // ** (OUTLIER THRESHHOLD DIVIDED BY THE PROVIDER'S NEW OPERATING
    // ** COST TO CHARGE RATIO) ROUNDED.
    components.setChargeOutlierThreshold(
        components
            .getOutlierThreshold()
            .divide(provider.getCostToChargeRatio(), RoundingMode.HALF_UP));
    // Note: setting higher precision component calculation above
    ppsRecord.setChargeOutlierThreshold(truncate(2, components.getChargeOutlierThreshold()));

    // *********************
    // ** RULE: CLAIMS WITH NO SPECIAL OUTLIER PAYMENTS ARE ASSIGNED
    // ** ZERO DOLLARS FOR THE OUTLIER PAYMENT
    if (bill.isNotOutlier()) {
      ppsRecord.setOutlierPaymentAmount(BigDecimal.ZERO);
    }
    // ** RULE: CLAIMS WHERE THE FED IS PAYING 100% OF THE PAYMENT ARE
    // ** ASSIGNED ZERO DOLLARS FOR THE PPS FACILITY SPECIFIC PAYMENT
    // ** AMOUNT
    // ** RULE: CLAIMS WHERE THE FEDS ARE NOT PAYING 100% OF THE BILL
    // ** HAVE THEIR PPS FEDERAL PAYMENT AMOUNT COMPUTED AS THE (PPS
    // ** FEDERAL RATE PERCENT TIMES THE PPS FEDERAL PAYMENT AMOUNT)
    // ** ROUNDED
    if (ppsRecord.getFederalRatePercent().compareTo(BigDecimal.ONE) == 0) {
      ppsRecord.setFacilitySpecificPaymentAmount(BigDecimal.ZERO);
    } else {
      // ** RULE: CLAIMS WHERE THE FEDS ARE NOT PAYING 100% OF THE BILL
      // ** HAVE THEIR PPS FACILITY SPECIFIC PAYMENT AMOUNT COMPUTED AS
      // ** THE PPS FACILITY SPECIFIC RATE PERCENT TIMES THE PPS FACILITY
      // ** SPECIFIC RATE PREBLEND AMOUNT) ROUNDED
      ppsRecord.setFederalPaymentAmount(
          ppsRecord.getFederalRatePercent().multiply(ppsRecord.getFederalPaymentAmount()));
      // ** RULE: CLAIMS WHERE THE FEDS ARE NOT PAYING 100% OF THE BILL
      // ** HAVE THEIR PPS OUTLIER PAYMENT AMOUNT COMPUTED AS THE (PPS
      // ** FEDERAL RATE PERCENT TIMES THE PPS OUTLIER PAYMENT AMOUNT)
      // ** ROUNDED
      ppsRecord.setFacilitySpecificPaymentAmount(
          ppsRecord.getFacilityRatePercent().multiply(ppsRecord.getFacilitySpecificRatePreblend()));
      // ** RULE: CLAIMS WHERE THE FEDS ARE NOT PAYING 100% OF THE BILL
      // ** HAVE THEIR PPS TEACH PAYMENT AMOUNT COMPUTED AS THE (PPS
      // ** FEDERAL RATE PERCENT TIMES THE PPS TEACH PAYMENT AMOUNT)
      // ** ROUNDED
      ppsRecord.setOutlierPaymentAmount(
          ppsRecord.getFederalRatePercent().multiply(ppsRecord.getOutlierPaymentAmount()));
      // ** RULE: CLAIMS WHERE THE FEDS ARE NOT PAYING 100% OF THE BILL
      // ** HAVE THEIR PPS LOW INCOME PATIENT PAYMENT AMOUNT COMPUTED AS
      // ** THE (PPS FEDERAL RATE PERCENT TIMES THE PPS LOW INCOME PATIENT
      // ** PAYMENT AMOUNT) ROUNDED
      ppsRecord.setTeachPaymentAmount(
          ppsRecord.getFederalRatePercent().multiply(ppsRecord.getTeachPaymentAmount()));
      ppsRecord.setLowIncomePaymentAmount(
          ppsRecord.getFederalRatePercent().multiply(ppsRecord.getLowIncomePaymentAmount()));
    }
    // ** RULE: ALL CLAIMS WITH PENALTIES HAVE THEIR PPS FEDERAL PENALTY
    // ** AMOUNT COMPUTED AS THE (PPS FEDERAL PAY AMOUNT TIMES 25%)
    // ** ROUNDED. IN OTHER WORDS, THEY PAY A 25% PENALTY OF THE FEDERAL
    // ** PAID AMOUNT
    /*
     * if (bill.hasPenalties()) { // ** RULE: ALL CLAIMS WITH PENALTIES HAVE THEIR
     * PPS FEDERAL PENALTY // ** AMOUNT COMPUTED AS THE (PPS FEDERAL PAY AMOUNT
     * TIMES 25%) // ** ROUNDED. THE PENALTY AMOUNT IS SUBTRACTED FROM THE FEDERAL
     * // ** PAY AMOUNT
     * ppsRecord.setFederalPenaltyAmount(ppsRecord.getFederalPaymentAmount() *
     * TWENTY_FIVE_PERCENT); // ** RULE: ALL CLAIMS WITH PENALTIES HAVE THEIR PPS
     * FEDERAL LOW // ** INCOME PATIENT PENALTY AMOUNT COMPUTED AS THE (PPS LOW
     * INCOME // ** PATIENT PAY AMOUNT TIMES 25%) ROUNDED. IN OTHER WORDS, THEY //
     * ** PAY A 25% LOW INCOME PATIENT PENALTY ppsRecord.setFederalPaymentAmount(
     * (ppsRecord.getFederalPaymentAmount() - ppsRecord.getFederalPenaltyAmount()));
     * // ** RULE: ALL CLAIMS WITH PENALTIES HAVE THEIR PPS LOW INCOME // ** PATIENT
     * PENALTY AMOUNT COMPUTED AS THE (PPS LOW INCOME PATIENT // ** PAY AMOUNT MINUS
     * THE PPS LOW INCOME PATIENT PENALTY AMOUNT) // ** ROUNDED.
     * ppsRecord.setLowIncomePaymentPenaltyAmount(
     * ppsRecord.getLowIncomePaymentAmount() * TWENTY_FIVE_PERCENT); // ** RULE: ALL
     * CLAIMS WITH PENALTIES HAVE THEIR PPS OUTLIER PENALTY // ** AMOUNT COMPUTED AS
     * THE (PPS OUTLIER PAY AMOUNT TIMES 25%) // ** ROUNDED. IN OTHER WORDS, THEY
     * PAY A 25% OUTLIER PENALTY ppsRecord.setLowIncomePaymentAmount(
     * (ppsRecord.getLowIncomePaymentAmount() -
     * ppsRecord.getLowIncomePaymentPenaltyAmount())); // ** RULE: ALL CLAIMS WITH
     * PENALTIES HAVE THEIR PPS OUTLIER PENALTY // ** AMOUNT COMPUTED AS THE (PPS
     * OUTLIER PAY AMOUNT MINUS THE PPS // ** OUTLIER PENALTY AMOUNT) ROUNDED.
     * ppsRecord.setOutlierPenaltyAmount(ppsRecord.getOutlierPaymentAmount() *
     * TWENTY_FIVE_PERCENT); // ** RULE: ALL CLAIMS WITH PENALTIES HAVE THEIR PPS
     * TEACH PENALTY // ** AMOUNT COMPUTED AS THE (PPS TEACH PAY AMOUNT TIMES 25%)
     * // ** ROUNDED. IN OTHER WORDS, THEY PAY A 25% PENALTY OF THE TEACH // ** PAID
     * AMOUNT ppsRecord.setOutlierPaymentAmount(
     * (ppsRecord.getOutlierPaymentAmount() - ppsRecord.getOutlierPenaltyAmount()));
     * // ** RULE: ALL CLAIMS WITH PENALTIES HAVE THEIR PPS TEACH PENALTY // **
     * AMOUNT COMPUTED AS THE (PPS TEACH PAY AMOUNT MINUS THE PPS THE // ** PPS
     * TEACH PENALTY AMOUNT) ROUNDED.
     * ppsRecord.setTeachPenaltyAmount(ppsRecord.getTeachPaymentAmount() *
     * TWENTY_FIVE_PERCENT); // ** RULE: ALL CLAIMS WITH PENALTIES HAVE THEIR PPS
     * TOTAL PENALTY // ** AMOUNT COMPUTED AS THE (PPS FEDERAL PENALTY AMOUNT PLUS
     * THE // ** PPS LOW INCOME PATIENT PENALTY AMOUNT PLUS THE PPS OUTLIER // **
     * PENALTY AMOUNT PLUS THE PPS TEACH PENALTY AMOUNT.
     * ppsRecord.setTeachPaymentAmount( (ppsRecord.getTeachPaymentAmount() -
     * ppsRecord.getTeachPenaltyAmount())); ppsRecord.setTotalPenaltyAmount(
     * (ppsRecord.getFederalPenaltyAmount() +
     * ppsRecord.getLowIncomePaymentPenaltyAmount() +
     * ppsRecord.getOutlierPenaltyAmount() + ppsRecord.getTeachPenaltyAmount())); }
     */

    // ** RULE: THE PPS TOTAL PAY AMOUNT IS COMPUTED AS THE PPS FEDERAL
    // ** PAY AMOUNT PLUS THE PPS FACILITY SPECIFIC PAY AMOUNT PLUS THE
    // ** PPS OUTLIER PAY AMOUNT PLUS THE PPS LOW INCOME PATIENT PAY
    // ** AMOUNT + PPS TEACH PAY AMOUNT
    ppsRecord.setTotalPaymentAmount(
        ppsRecord
            .getFederalPaymentAmount()
            .add(ppsRecord.getFacilitySpecificPaymentAmount())
            .add(ppsRecord.getOutlierPaymentAmount())
            .add(ppsRecord.getLowIncomePaymentAmount())
            .add(ppsRecord.getTeachPaymentAmount()));
    // ** REQUIREMENT: THE SYSTEM MUST RETURN A SUCCESSFUL PAYMENT CODE
    // ** SHOWING THAT THE CLAIM WAS PAID AS A NORMAL CASE MIX GROUP
    // ** PAYMENT (CODE 01) IF NO EDITS FAILED AND THE FEDS ARE PAYING
    // ** 100% OF THE CLAIM AND THERE WERE NO PATIENT TRANSFERS AND
    // ** THERE WAS AN OUTLIER PAYMENT.
    // ** REQUIREMENT: THE SYSTEM MUST RETURN A SUCCESSFUL PAYMENT CODE
    // ** SHOWING THAT THE CLAIM WAS PAID AS A NORMAL CASE MIX GROUP
    // ** PAYMENT (CODE 00) IF NO EDITS FAILED AND THE FEDS ARE PAYING
    // ** 100% OF THE CLAIM AND THERE WERE NO PATIENT TRANSFERS AND
    // ** THERE WAS NOT A OUTLIER PAYMENT.
    PricerResult result = new PricerResult();
    result.setResultCode(ResultCode.OK_00);
    if (ppsRecord.getFederalRatePercent().compareTo(BigDecimal.ONE) == 0) {
      if (ppsRecord.getTransferPercent().compareTo(BigDecimal.ONE) == 0) {
        if (ppsRecord.getOutlierPaymentAmount().compareTo(BigDecimal.ZERO) > 0) {
          result.setResultCode(ResultCode.CMG_PAYMENT_OUTLIER_01);
        }
        // ** REQUIREMENT: THE SYSTEM MUST RETURN A SUCCESSFUL PAYMENT CODE
        // ** SHOWING THAT THE CLAIM WAS PAID AS A TRANSFER PER DIEM PAYMENT
        // ** (CODE 03) IF NO EDITS FAILED AND THE FEDS ARE PAYING 100% OF
        // ** THE CLAIM AND THERE WAS AN OUTLIER PAYMENT.
        // ** REQUIREMENT: THE SYSTEM MUST RETURN A SUCCESSFUL PAYMENT CODE
        // ** SHOWING THAT THE CLAIM WAS PAID AS A TRANSFER PER DIEM PAYMENT
        // ** (CODE 02) IF NO EDITS FAILED AND THE FEDS ARE PAYING 100% OF
        // ** THE CLAIM AND THERE WAS NO OUTLIER PAYMENT.
      } else if (ppsRecord.getOutlierPaymentAmount().compareTo(BigDecimal.ZERO) > 0) {
        result.setResultCode(ResultCode.TRANSFER_PAID_OUTLIER_03);
      } else {
        result.setResultCode(ResultCode.TRANSFER_PAID_NO_OUTLIER_02);
      }
      // ** REQUIREMENT: THE SYSTEM MUST RETURN A SUCCESSFUL PAYMENT CODE
      // ** SHOWING THAT THE CLAIM WAS PAID AS A BLENDED PAYMENT (CODE 05)
      // ** IF NO EDITS FAILED AND THE FEDS ARE NOT PAYING 100% OF THE
      // ** CLAIM AND THERE WERE NO PATIENT TRANSFERS AND THERE WAS AN
      // ** OUTLIER PAYMENT.
      // ** REQUIREMENT: THE SYSTEM MUST RETURN A SUCCESSFUL PAYMENT CODE
      // ** SHOWING THAT THE CLAIM WAS PAID AS A BLENDED PAYMENT (CODE 04)
      // ** IF NO EDITS FAILED AND THE FEDS ARE NOT PAYING 100% OF THE
      // ** CLAIM AND THERE WERE NO PATIENT TRANSFERS AND THERE WAS NO
      // ** OUTLIER PAYMENT.
    } else if (ppsRecord.getTransferPercent().compareTo(BigDecimal.ONE) == 0) {
      if (ppsRecord.getOutlierPaymentAmount().compareTo(BigDecimal.ZERO) > 0) {
        result.setResultCode(ResultCode.BLEND_CMG_PAY_OUTLIER_05);
      } else {
        result.setResultCode(ResultCode.BLEND_CMG_PAY_NO_OUTLIER_04);
      }
      // ** REQUIREMENT: THE SYSTEM MUST RETURN A SUCCESSFUL PAYMENT CODE
      // ** SHOWING THAT THE CLAIM WAS PAID AS A BLENDED TRANSFER PAYMENT
      // ** (CODE 07) IF NO EDITS FAILED AND THE FEDS ARE NOT PAYING 100%
      // ** OF THE CLAIM AND THERE WAS A PATIENT TRANSFER AND THERE WAS AN
      // ** OUTLIER PAYMENT.
      // ** REQUIREMENT: THE SYSTEM MUST RETURN A SUCCESSFUL PAYMENT CODE
      // ** SHOWING THAT THE CLAIM WAS PAID AS A BLENDED TRANSFER PAYMENT
      // ** (CODE 06) IF NO EDITS FAILED AND THE FEDS ARE NOT PAYING 100%
      // ** OF THE CLAIM AND THERE WAS A PATIENT TRANSFER AND THERE WAS NO
      // ** OUTLIER PAYMENT.
    } else if (ppsRecord.getOutlierPaymentAmount().compareTo(BigDecimal.ZERO) > 0) {
      result.setResultCode(ResultCode.BLEND_TRNSFR_PAY_OUTLIER_07);
    } else {
      result.setResultCode(ResultCode.BLEND_TRNSFR_PAY_NO_OUTLIER_06);
    }
    // ** REQUIREMENT: THE SYSTEM MUST PROVIDE RETURN CODES FOR
    // ** PROCESSING PAYMENTS WITH PENALTIES THAT ARE DIFFERENT THAN THE
    // ** RETURN CODE FOR PAYMENTS PROCESSED WITHOUT PENALTIES. PENALTY
    // ** RETURN CODES ARE ALWAYS 10 MORE THAN (ADD 10 TO) THE
    // ** CORRESPONDING NO PENALTY RETURN CODE
    if (bill.hasPenalties()) {
      result.setResultCode(result.getResultCode().adjustForPenalty());
    }
    // ** REQUIREMENT:THE SYSTEM MUST RETURN AN ERROR (CODE 67) WHEN THE
    // ** BILLED LENGTH OF STAY EXCEEDS THE ALLOWED (PPS REGULAR DAYS
    // ** USED PLUS THE PPS LIFETIME RESERVED DAYS) OR THIS IS A COST
    // ** OUTLIER THRESHOLD CALCULATION. IN OTHER WORDS, FLAG AN ERROR
    // ** WHEN THE PATIENT HAS EXCEEDED THEIR MAXIMUM ALLOWABLE DAYS OF
    // ** COVERAGE OR THIS IS A COST OUTLIER THRESHOLD CALCULATION.
    if (result.getResultCode().isOutlier()
        && (ppsRecord.getLengthOfStay()
                > (ppsRecord.getRegularDaysUsed() + ppsRecord.getLifetimeReserveDaysUsed())
            || ppsRecord.getCotInd().equals(YES_INDICATOR))) {
      result.setResultCode(ResultCode.OUTLIER_LOS_GT_COVERED_DAYS_67);
    }
    return result;
  }

  public PricerOutput run(PricerInput input) {
    ProspectivePaymentRecord ppsRecord =
        mainline(input.getBillingRecord(), input.getProviderRecord());
    return new PricerOutput(input.getRequestId(), input.getRequestIdentifier(), ppsRecord);
  }
}
