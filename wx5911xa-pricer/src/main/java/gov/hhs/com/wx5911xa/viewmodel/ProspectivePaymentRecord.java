package gov.hhs.com.wx5911xa.viewmodel;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ProspectivePaymentRecord {
  private PricerResult result;
  private BigDecimal totalPaymentAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
  private BigDecimal totalPenaltyAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

  private BigDecimal federalPaymentAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
  private BigDecimal federalPenaltyAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
  private BigDecimal federalRatePercent = BigDecimal.ZERO.setScale(4, RoundingMode.HALF_UP);

  private BigDecimal facilitySpecificPaymentAmount =
      BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
  private BigDecimal facilitySpecificRatePreblend =
      BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
  private BigDecimal facilityRatePercent = BigDecimal.ZERO.setScale(4, RoundingMode.HALF_UP);
  private BigDecimal facilityCosts = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

  private BigDecimal outlierPaymentAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
  private BigDecimal outlierPenaltyAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
  private BigDecimal outlierThreshold = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
  private BigDecimal chargeOutlierThreshold = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

  private BigDecimal lowIncomePaymentAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
  private BigDecimal lowIncomePaymentPenaltyAmount =
      BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
  private BigDecimal lowIncomePaymentPercent = BigDecimal.ZERO.setScale(4, RoundingMode.HALF_UP);

  private BigDecimal teachPaymentAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
  private BigDecimal teachPenaltyAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

  private BigDecimal nationalLaborPercent = BigDecimal.ZERO.setScale(5, RoundingMode.HALF_UP);
  private BigDecimal nationalNonLaborPercent = BigDecimal.ZERO.setScale(5, RoundingMode.HALF_UP);
  private BigDecimal nationalThresholdAdjustment =
      BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

  private BigDecimal budgetNeutralConversionAmount =
      BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
  private BigDecimal ruralAdjustment = BigDecimal.ZERO.setScale(4, RoundingMode.HALF_UP);

  private int lengthOfStay;
  private int regularDaysUsed;
  private int lifetimeReserveDaysUsed;
  private int averageLengthOfStay;

  private BigDecimal relativeWeight = BigDecimal.ZERO.setScale(4, RoundingMode.HALF_UP);
  private BigDecimal transferPercent = BigDecimal.ZERO.setScale(4, RoundingMode.HALF_UP);
  private BigDecimal standardPaymentAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

  private String coreBasedStatisticalArea = "";
  private String metropolitanStatisticalArea = "";

  private BigDecimal wageIndex = BigDecimal.ZERO.setScale(4, RoundingMode.HALF_UP);

  private String submittedCmgCode = "";
  private String pricedCmgCode = "";
  private String calcVersionCode = "";
  private String cotInd = "";

  public ProspectivePaymentRecord() {
    // Needed for Jackson.objectMapper deserialization
  }

  public PricerResult getResult() {
    return result;
  }

  public BigDecimal getTotalPaymentAmount() {
    return totalPaymentAmount;
  }

  public int getLengthOfStay() {
    return lengthOfStay;
  }

  public int getRegularDaysUsed() {
    return regularDaysUsed;
  }

  public int getLifetimeReserveDaysUsed() {
    return lifetimeReserveDaysUsed;
  }

  public int getAverageLengthOfStay() {
    return averageLengthOfStay;
  }

  public String getCoreBasedStatisticalArea() {
    return coreBasedStatisticalArea;
  }

  public String getMetropolitanStatisticalArea() {
    return metropolitanStatisticalArea;
  }

  public String getSubmittedCmgCode() {
    return submittedCmgCode;
  }

  public String getPricedCmgCode() {
    return pricedCmgCode;
  }

  public String getCalcVersionCode() {
    return calcVersionCode;
  }

  public String getCotInd() {
    return cotInd;
  }

  public BigDecimal getTotalPenaltyAmount() {
    return totalPenaltyAmount;
  }

  public BigDecimal getFederalPaymentAmount() {
    return federalPaymentAmount;
  }

  public BigDecimal getFederalPenaltyAmount() {
    return federalPenaltyAmount;
  }

  public BigDecimal getFederalRatePercent() {
    return federalRatePercent;
  }

  public BigDecimal getFacilitySpecificPaymentAmount() {
    return facilitySpecificPaymentAmount;
  }

  public BigDecimal getFacilitySpecificRatePreblend() {
    return facilitySpecificRatePreblend;
  }

  public BigDecimal getFacilityRatePercent() {
    return facilityRatePercent;
  }

  public BigDecimal getFacilityCosts() {
    return facilityCosts;
  }

  public BigDecimal getOutlierPaymentAmount() {
    return outlierPaymentAmount;
  }

  public BigDecimal getOutlierPenaltyAmount() {
    return outlierPenaltyAmount;
  }

  public BigDecimal getOutlierThreshold() {
    return outlierThreshold;
  }

  public BigDecimal getChargeOutlierThreshold() {
    return chargeOutlierThreshold;
  }

  public BigDecimal getLowIncomePaymentAmount() {
    return lowIncomePaymentAmount;
  }

  public BigDecimal getLowIncomePaymentPenaltyAmount() {
    return lowIncomePaymentPenaltyAmount;
  }

  public BigDecimal getLowIncomePaymentPercent() {
    return lowIncomePaymentPercent;
  }

  public BigDecimal getTeachPaymentAmount() {
    return teachPaymentAmount;
  }

  public BigDecimal getTeachPenaltyAmount() {
    return teachPenaltyAmount;
  }

  public BigDecimal getNationalLaborPercent() {
    return nationalLaborPercent;
  }

  public BigDecimal getNationalNonLaborPercent() {
    return nationalNonLaborPercent;
  }

  public BigDecimal getNationalThresholdAdjustment() {
    return nationalThresholdAdjustment;
  }

  public BigDecimal getBudgetNeutralConversionAmount() {
    return budgetNeutralConversionAmount;
  }

  public BigDecimal getRuralAdjustment() {
    return ruralAdjustment;
  }

  public BigDecimal getRelativeWeight() {
    return relativeWeight;
  }

  public BigDecimal getTransferPercent() {
    return transferPercent;
  }

  public BigDecimal getStandardPaymentAmount() {
    return standardPaymentAmount;
  }

  public BigDecimal getWageIndex() {
    return wageIndex;
  }

  public void setResult(PricerResult result) {
    this.result = result;
  }

  public void setLengthOfStay(int lengthOfStay) {
    this.lengthOfStay = lengthOfStay;
  }

  public void setRegularDaysUsed(int regularDaysUsed) {
    this.regularDaysUsed = regularDaysUsed;
  }

  public void setLifetimeReserveDaysUsed(int lifetimeReserveDaysUsed) {
    this.lifetimeReserveDaysUsed = lifetimeReserveDaysUsed;
  }

  public void setCoreBasedStatisticalArea(String coreBasedStatisticalArea) {
    this.coreBasedStatisticalArea = coreBasedStatisticalArea;
  }

  public void setMetropolitanStatisticalArea(String metropolitanStatisticalArea) {
    this.metropolitanStatisticalArea = metropolitanStatisticalArea;
  }

  public void setSubmittedCmgCode(String submittedCmgCode) {
    this.submittedCmgCode = submittedCmgCode;
  }

  public void setPricedCmgCode(String pricedCmgCode) {
    this.pricedCmgCode = pricedCmgCode;
  }

  public void setCalcVersionCode(String calcVersionCode) {
    this.calcVersionCode = calcVersionCode;
  }

  public void setCotInd(String cotInd) {
    this.cotInd = cotInd;
  }

  public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
    this.totalPaymentAmount = totalPaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

  public void setTotalPenaltyAmount(BigDecimal totalPenaltyAmount) {
    this.totalPenaltyAmount = totalPenaltyAmount.setScale(2, RoundingMode.HALF_UP);
  }

  public void setFederalPaymentAmount(BigDecimal federalPaymentAmount) {
    this.federalPaymentAmount = federalPaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

  public void setFederalPenaltyAmount(BigDecimal federalPenaltyAmount) {
    this.federalPenaltyAmount = federalPenaltyAmount.setScale(2, RoundingMode.HALF_UP);
  }

  public void setFederalRatePercent(BigDecimal federalRatePercent) {
    this.federalRatePercent = federalRatePercent.setScale(4, RoundingMode.HALF_UP);
  }

  public void setFacilitySpecificPaymentAmount(BigDecimal facilitySpecificPaymentAmount) {
    this.facilitySpecificPaymentAmount =
        facilitySpecificPaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

  public void setFacilitySpecificRatePreblend(BigDecimal facilitySpecificRatePreblend) {
    this.facilitySpecificRatePreblend =
        facilitySpecificRatePreblend.setScale(2, RoundingMode.HALF_UP);
  }

  public void setFacilityRatePercent(BigDecimal facilityRatePercent) {
    this.facilityRatePercent = facilityRatePercent.setScale(4, RoundingMode.HALF_UP);
  }

  public void setFacilityCosts(BigDecimal facilityCosts) {
    this.facilityCosts = facilityCosts.setScale(2, RoundingMode.HALF_UP);
  }

  public void setOutlierPaymentAmount(BigDecimal outlierPaymentAmount) {
    this.outlierPaymentAmount = outlierPaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

  public void setOutlierPenaltyAmount(BigDecimal outlierPenaltyAmount) {
    this.outlierPenaltyAmount = outlierPenaltyAmount.setScale(2, RoundingMode.HALF_UP);
  }

  public void setOutlierThreshold(BigDecimal outlierThreshold) {
    this.outlierThreshold = outlierThreshold.setScale(2, RoundingMode.HALF_UP);
  }

  public void setChargeOutlierThreshold(BigDecimal chargeOutlierThreshold) {
    this.chargeOutlierThreshold = chargeOutlierThreshold.setScale(2, RoundingMode.HALF_UP);
  }

  public void setLowIncomePaymentAmount(BigDecimal lowIncomePaymentAmount) {
    this.lowIncomePaymentAmount = lowIncomePaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

  public void setLowIncomePaymentPenaltyAmount(BigDecimal lowIncomePaymentPenaltyAmount) {
    this.lowIncomePaymentPenaltyAmount =
        lowIncomePaymentPenaltyAmount.setScale(2, RoundingMode.HALF_UP);
  }

  public void setLowIncomePaymentPercent(BigDecimal lowIncomePaymentPercent) {
    this.lowIncomePaymentPercent = lowIncomePaymentPercent.setScale(4, RoundingMode.HALF_UP);
  }

  public void setTeachPaymentAmount(BigDecimal teachPaymentAmount) {
    this.teachPaymentAmount = teachPaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

  public void setTeachPenaltyAmount(BigDecimal teachPenaltyAmount) {
    this.teachPenaltyAmount = teachPenaltyAmount.setScale(2, RoundingMode.HALF_UP);
  }

  public void setNationalLaborPercent(BigDecimal nationalLaborPercent) {
    this.nationalLaborPercent = nationalLaborPercent.setScale(5, RoundingMode.HALF_UP);
  }

  public void setNationalNonLaborPercent(BigDecimal nationalNonLaborPercent) {
    this.nationalNonLaborPercent = nationalNonLaborPercent.setScale(5, RoundingMode.HALF_UP);
  }

  public void setNationalThresholdAdjustment(BigDecimal nationalThresholdAdjustment) {
    this.nationalThresholdAdjustment =
        nationalThresholdAdjustment.setScale(2, RoundingMode.HALF_UP);
  }

  public void setBudgetNeutralConversionAmount(BigDecimal budgetNeutralConversionAmount) {
    this.budgetNeutralConversionAmount =
        budgetNeutralConversionAmount.setScale(2, RoundingMode.HALF_UP);
  }

  public void setRuralAdjustment(BigDecimal ruralAdjustment) {
    this.ruralAdjustment = ruralAdjustment.setScale(4, RoundingMode.HALF_UP);
  }

  public void setRelativeWeight(double relativeWeight) {
    this.relativeWeight = BigDecimal.valueOf(relativeWeight).setScale(4, RoundingMode.HALF_UP);
  }

  public void setAverageLengthOfStay(int averageLengthOfStay) {
    this.averageLengthOfStay = averageLengthOfStay;
  }

  public void setTransferPercent(BigDecimal transferPercent) {
    this.transferPercent = transferPercent.setScale(4, RoundingMode.HALF_UP);
  }

  public void setStandardPaymentAmount(BigDecimal standardPaymentAmount) {
    this.standardPaymentAmount = standardPaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

  public void setWageIndex(BigDecimal wageIndex) {
    this.wageIndex = wageIndex.setScale(4, RoundingMode.HALF_UP);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ProspectivePaymentRecord)) return false;
    ProspectivePaymentRecord that = (ProspectivePaymentRecord) o;
    return lengthOfStay == that.lengthOfStay
        && regularDaysUsed == that.regularDaysUsed
        && lifetimeReserveDaysUsed == that.lifetimeReserveDaysUsed
        && averageLengthOfStay == that.averageLengthOfStay
        && Objects.equals(result, that.result)
        && Objects.equals(totalPaymentAmount, that.totalPaymentAmount)
        && Objects.equals(totalPenaltyAmount, that.totalPenaltyAmount)
        && Objects.equals(federalPaymentAmount, that.federalPaymentAmount)
        && Objects.equals(federalPenaltyAmount, that.federalPenaltyAmount)
        && Objects.equals(federalRatePercent, that.federalRatePercent)
        && Objects.equals(facilitySpecificPaymentAmount, that.facilitySpecificPaymentAmount)
        && Objects.equals(facilitySpecificRatePreblend, that.facilitySpecificRatePreblend)
        && Objects.equals(facilityRatePercent, that.facilityRatePercent)
        && Objects.equals(facilityCosts, that.facilityCosts)
        && Objects.equals(outlierPaymentAmount, that.outlierPaymentAmount)
        && Objects.equals(outlierPenaltyAmount, that.outlierPenaltyAmount)
        && Objects.equals(outlierThreshold, that.outlierThreshold)
        && Objects.equals(chargeOutlierThreshold, that.chargeOutlierThreshold)
        && Objects.equals(lowIncomePaymentAmount, that.lowIncomePaymentAmount)
        && Objects.equals(lowIncomePaymentPenaltyAmount, that.lowIncomePaymentPenaltyAmount)
        && Objects.equals(lowIncomePaymentPercent, that.lowIncomePaymentPercent)
        && Objects.equals(teachPaymentAmount, that.teachPaymentAmount)
        && Objects.equals(teachPenaltyAmount, that.teachPenaltyAmount)
        && Objects.equals(nationalLaborPercent, that.nationalLaborPercent)
        && Objects.equals(nationalNonLaborPercent, that.nationalNonLaborPercent)
        && Objects.equals(nationalThresholdAdjustment, that.nationalThresholdAdjustment)
        && Objects.equals(budgetNeutralConversionAmount, that.budgetNeutralConversionAmount)
        && Objects.equals(ruralAdjustment, that.ruralAdjustment)
        && Objects.equals(relativeWeight, that.relativeWeight)
        && Objects.equals(transferPercent, that.transferPercent)
        && Objects.equals(standardPaymentAmount, that.standardPaymentAmount)
        && Objects.equals(coreBasedStatisticalArea, that.coreBasedStatisticalArea)
        && Objects.equals(metropolitanStatisticalArea, that.metropolitanStatisticalArea)
        && Objects.equals(wageIndex, that.wageIndex)
        && Objects.equals(submittedCmgCode, that.submittedCmgCode)
        && Objects.equals(pricedCmgCode, that.pricedCmgCode)
        && Objects.equals(calcVersionCode, that.calcVersionCode)
        && Objects.equals(cotInd, that.cotInd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        result,
        totalPaymentAmount,
        totalPenaltyAmount,
        federalPaymentAmount,
        federalPenaltyAmount,
        federalRatePercent,
        facilitySpecificPaymentAmount,
        facilitySpecificRatePreblend,
        facilityRatePercent,
        facilityCosts,
        outlierPaymentAmount,
        outlierPenaltyAmount,
        outlierThreshold,
        chargeOutlierThreshold,
        lowIncomePaymentAmount,
        lowIncomePaymentPercent,
        lowIncomePaymentPenaltyAmount,
        teachPaymentAmount,
        teachPenaltyAmount,
        nationalLaborPercent,
        nationalNonLaborPercent,
        nationalThresholdAdjustment,
        budgetNeutralConversionAmount,
        ruralAdjustment,
        lengthOfStay,
        regularDaysUsed,
        lifetimeReserveDaysUsed,
        averageLengthOfStay,
        relativeWeight,
        transferPercent,
        standardPaymentAmount,
        coreBasedStatisticalArea,
        metropolitanStatisticalArea,
        wageIndex,
        submittedCmgCode,
        pricedCmgCode,
        calcVersionCode,
        cotInd);
  }
}
