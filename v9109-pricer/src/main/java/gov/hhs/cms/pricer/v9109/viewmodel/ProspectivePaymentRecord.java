package gov.hhs.cms.pricer.v9109.viewmodel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;


/**
 * Output values for the IrfPricer calculation
 *
 * <p>Notes: - The # of decimal places for each fixed point decimal class was taken from the
 * original COBOL definition
 */
// @XmlType
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

//   @JsonProperty
  public PricerResult getResult() {
    return result;
  }

//   @JsonProperty
  public BigDecimal getTotalPaymentAmount() {
    return totalPaymentAmount;
  }

//   @JsonProperty
  public int getLengthOfStay() {
    return lengthOfStay;
  }

//   @JsonProperty
  public int getRegularDaysUsed() {
    return regularDaysUsed;
  }

//   @JsonProperty
  public int getLifetimeReserveDaysUsed() {
    return lifetimeReserveDaysUsed;
  }

//   @JsonProperty
  public int getAverageLengthOfStay() {
    return averageLengthOfStay;
  }

//   @JsonProperty
  public String getCoreBasedStatisticalArea() {
    return coreBasedStatisticalArea;
  }

//   @JsonProperty
  public String getMetropolitanStatisticalArea() {
    return metropolitanStatisticalArea;
  }

//   @JsonProperty
  public String getSubmittedCmgCode() {
    return submittedCmgCode;
  }

//   @JsonProperty
  public String getPricedCmgCode() {
    return pricedCmgCode;
  }

//   @JsonProperty
  public String getCalcVersionCode() {
    return calcVersionCode;
  }

//   @JsonProperty
  public String getCotInd() {
    return cotInd;
  }

//   @JsonProperty
  public BigDecimal getTotalPenaltyAmount() {
    return totalPenaltyAmount;
  }

//   @JsonProperty
  public BigDecimal getFederalPaymentAmount() {
    return federalPaymentAmount;
  }

//   @JsonProperty
  public BigDecimal getFederalPenaltyAmount() {
    return federalPenaltyAmount;
  }

//   @JsonProperty
  public BigDecimal getFederalRatePercent() {
    return federalRatePercent;
  }

//   @JsonProperty
  public BigDecimal getFacilitySpecificPaymentAmount() {
    return facilitySpecificPaymentAmount;
  }

//   @JsonProperty
  public BigDecimal getFacilitySpecificRatePreblend() {
    return facilitySpecificRatePreblend;
  }

//   @JsonProperty
  public BigDecimal getFacilityRatePercent() {
    return facilityRatePercent;
  }

//   @JsonProperty
  public BigDecimal getFacilityCosts() {
    return facilityCosts;
  }

//   @JsonProperty
  public BigDecimal getOutlierPaymentAmount() {
    return outlierPaymentAmount;
  }

//   @JsonProperty
  public BigDecimal getOutlierPenaltyAmount() {
    return outlierPenaltyAmount;
  }

//   @JsonProperty
  public BigDecimal getOutlierThreshold() {
    return outlierThreshold;
  }

//   @JsonProperty
  public BigDecimal getChargeOutlierThreshold() {
    return chargeOutlierThreshold;
  }

//   @JsonProperty
  public BigDecimal getLowIncomePaymentAmount() {
    return lowIncomePaymentAmount;
  }

//   @JsonProperty
  public BigDecimal getLowIncomePaymentPenaltyAmount() {
    return lowIncomePaymentPenaltyAmount;
  }

//   @JsonProperty
  public BigDecimal getLowIncomePaymentPercent() {
    return lowIncomePaymentPercent;
  }

//   @JsonProperty
  public BigDecimal getTeachPaymentAmount() {
    return teachPaymentAmount;
  }

//   @JsonProperty
  public BigDecimal getTeachPenaltyAmount() {
    return teachPenaltyAmount;
  }

//   @JsonProperty
  public BigDecimal getNationalLaborPercent() {
    return nationalLaborPercent;
  }

//   @JsonProperty
  public BigDecimal getNationalNonLaborPercent() {
    return nationalNonLaborPercent;
  }

//   @JsonProperty
  public BigDecimal getNationalThresholdAdjustment() {
    return nationalThresholdAdjustment;
  }

//   @JsonProperty
  public BigDecimal getBudgetNeutralConversionAmount() {
    return budgetNeutralConversionAmount;
  }

//   @JsonProperty
  public BigDecimal getRuralAdjustment() {
    return ruralAdjustment;
  }

//   @JsonProperty
  public BigDecimal getRelativeWeight() {
    return relativeWeight;
  }

//   @JsonProperty
  public BigDecimal getTransferPercent() {
    return transferPercent;
  }

//   @JsonProperty
  public BigDecimal getStandardPaymentAmount() {
    return standardPaymentAmount;
  }

//   @JsonProperty
  public BigDecimal getWageIndex() {
    return wageIndex;
  }

//   @XmlElement
  public void setResult(PricerResult result) {
    this.result = result;
  }

//   @XmlElement
  public void setLengthOfStay(int lengthOfStay) {
    this.lengthOfStay = lengthOfStay;
  }

//   @XmlElement
  public void setRegularDaysUsed(int regularDaysUsed) {
    this.regularDaysUsed = regularDaysUsed;
  }

//   @XmlElement
  public void setLifetimeReserveDaysUsed(int lifetimeReserveDaysUsed) {
    this.lifetimeReserveDaysUsed = lifetimeReserveDaysUsed;
  }

//   @XmlElement
  public void setCoreBasedStatisticalArea(String coreBasedStatisticalArea) {
    this.coreBasedStatisticalArea = coreBasedStatisticalArea;
  }

//   @XmlElement
  public void setMetropolitanStatisticalArea(String metropolitanStatisticalArea) {
    this.metropolitanStatisticalArea = metropolitanStatisticalArea;
  }

//   @XmlElement
  public void setSubmittedCmgCode(String submittedCmgCode) {
    this.submittedCmgCode = submittedCmgCode;
  }

//   @XmlElement
  public void setPricedCmgCode(String pricedCmgCode) {
    this.pricedCmgCode = pricedCmgCode;
  }

//   @XmlElement
  public void setCalcVersionCode(String calcVersionCode) {
    this.calcVersionCode = calcVersionCode;
  }

//   @XmlElement
  public void setCotInd(String cotInd) {
    this.cotInd = cotInd;
  }

//   @XmlElement
  public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
    this.totalPaymentAmount = totalPaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setTotalPenaltyAmount(BigDecimal totalPenaltyAmount) {
    this.totalPenaltyAmount = totalPenaltyAmount.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setFederalPaymentAmount(BigDecimal federalPaymentAmount) {
    this.federalPaymentAmount = federalPaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setFederalPenaltyAmount(BigDecimal federalPenaltyAmount) {
    this.federalPenaltyAmount = federalPenaltyAmount.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setFederalRatePercent(BigDecimal federalRatePercent) {
    this.federalRatePercent = federalRatePercent.setScale(4, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setFacilitySpecificPaymentAmount(BigDecimal facilitySpecificPaymentAmount) {
    this.facilitySpecificPaymentAmount =
        facilitySpecificPaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setFacilitySpecificRatePreblend(BigDecimal facilitySpecificRatePreblend) {
    this.facilitySpecificRatePreblend =
        facilitySpecificRatePreblend.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setFacilityRatePercent(BigDecimal facilityRatePercent) {
    this.facilityRatePercent = facilityRatePercent.setScale(4, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setFacilityCosts(BigDecimal facilityCosts) {
    this.facilityCosts = facilityCosts.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setOutlierPaymentAmount(BigDecimal outlierPaymentAmount) {
    this.outlierPaymentAmount = outlierPaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setOutlierPenaltyAmount(BigDecimal outlierPenaltyAmount) {
    this.outlierPenaltyAmount = outlierPenaltyAmount.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setOutlierThreshold(BigDecimal outlierThreshold) {
    this.outlierThreshold = outlierThreshold.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setChargeOutlierThreshold(BigDecimal chargeOutlierThreshold) {
    this.chargeOutlierThreshold = chargeOutlierThreshold.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setLowIncomePaymentAmount(BigDecimal lowIncomePaymentAmount) {
    this.lowIncomePaymentAmount = lowIncomePaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setLowIncomePaymentPenaltyAmount(BigDecimal lowIncomePaymentPenaltyAmount) {
    this.lowIncomePaymentPenaltyAmount =
        lowIncomePaymentPenaltyAmount.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setLowIncomePaymentPercent(BigDecimal lowIncomePaymentPercent) {
    this.lowIncomePaymentPercent = lowIncomePaymentPercent.setScale(4, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setTeachPaymentAmount(BigDecimal teachPaymentAmount) {
    this.teachPaymentAmount = teachPaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setTeachPenaltyAmount(BigDecimal teachPenaltyAmount) {
    this.teachPenaltyAmount = teachPenaltyAmount.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setNationalLaborPercent(BigDecimal nationalLaborPercent) {
    this.nationalLaborPercent = nationalLaborPercent.setScale(5, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setNationalNonLaborPercent(BigDecimal nationalNonLaborPercent) {
    this.nationalNonLaborPercent = nationalNonLaborPercent.setScale(5, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setNationalThresholdAdjustment(BigDecimal nationalThresholdAdjustment) {
    this.nationalThresholdAdjustment =
        nationalThresholdAdjustment.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setBudgetNeutralConversionAmount(BigDecimal budgetNeutralConversionAmount) {
    this.budgetNeutralConversionAmount =
        budgetNeutralConversionAmount.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setRuralAdjustment(BigDecimal ruralAdjustment) {
    this.ruralAdjustment = ruralAdjustment.setScale(4, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setRelativeWeight(double relativeWeight) {
    this.relativeWeight = BigDecimal.valueOf(relativeWeight).setScale(4, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setAverageLengthOfStay(int averageLengthOfStay) {
    this.averageLengthOfStay = averageLengthOfStay;
  }

//   @XmlElement
  public void setTransferPercent(BigDecimal transferPercent) {
    this.transferPercent = transferPercent.setScale(4, RoundingMode.HALF_UP);
  }

//   @XmlElement
  public void setStandardPaymentAmount(BigDecimal standardPaymentAmount) {
    this.standardPaymentAmount = standardPaymentAmount.setScale(2, RoundingMode.HALF_UP);
  }

//   @XmlElement
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
