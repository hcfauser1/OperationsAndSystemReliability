package gov.hhs.cms.pricer.v9109.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Intermediate values for Pricer calculation
 */
public class PpsComponents {
  private BigDecimal disproportionateShareAdjustment;
  private BigDecimal teachPercent;
  private BigDecimal laborPortion;
  private BigDecimal nonLaborPortion;
  private BigDecimal outlierLaborPortion;
  private BigDecimal outlierNonLaborPortion;
  private BigDecimal fpOutlierThreshold;
  private BigDecimal outlierThreshold;
  private BigDecimal chargeOutlierThreshold;
  private String fiscalYearBeginDate;
  private String dischargeDate;
  private String ppsBlendIndicator;

  public BigDecimal getDisproportionateShareAdjustment() {
    return disproportionateShareAdjustment;
  }

  public void setDisproportionateShareAdjustment(BigDecimal disproportionateShareAdjustment) {
    this.disproportionateShareAdjustment =
        disproportionateShareAdjustment.setScale(4, RoundingMode.HALF_UP);
  }

  public BigDecimal getTeachPercent() {
    return teachPercent;
  }

  public void setTeachPercent(BigDecimal teachPercent) {
    this.teachPercent = teachPercent.setScale(4, RoundingMode.HALF_UP);
  }

  public BigDecimal getLaborPortion() {
    return laborPortion;
  }

  public void setLaborPortion(BigDecimal laborPortion) {
    this.laborPortion = laborPortion.setScale(6, RoundingMode.HALF_UP);
  }

  public BigDecimal getNonLaborPortion() {
    return nonLaborPortion;
  }

  public void setNonLaborPortion(BigDecimal nonLaborPortion) {
    this.nonLaborPortion = nonLaborPortion.setScale(6, RoundingMode.HALF_UP);
  }

  public BigDecimal getOutlierLaborPortion() {
    return outlierLaborPortion;
  }

  public void setOutlierLaborPortion(BigDecimal outlierLaborPortion) {
    this.outlierLaborPortion = outlierLaborPortion.setScale(6, RoundingMode.HALF_UP);
  }

  public BigDecimal getOutlierNonLaborPortion() {
    return outlierNonLaborPortion;
  }

  public void setOutlierNonLaborPortion(BigDecimal outlierNonLaborPortion) {
    this.outlierNonLaborPortion = outlierNonLaborPortion.setScale(6, RoundingMode.HALF_UP);
  }

  public BigDecimal getFpOutlierThreshold() {
    return fpOutlierThreshold;
  }

  public void setFpOutlierThreshold(BigDecimal fpOutlierThreshold) {
    this.fpOutlierThreshold = fpOutlierThreshold.setScale(6, RoundingMode.HALF_UP);
  }

  public BigDecimal getOutlierThreshold() {
    return outlierThreshold;
  }

  public void setOutlierThreshold(BigDecimal outlierThreshold) {
    this.outlierThreshold = outlierThreshold.setScale(6, RoundingMode.HALF_UP);
  }

  public BigDecimal getChargeOutlierThreshold() {
    return chargeOutlierThreshold;
  }

  public void setChargeOutlierThreshold(BigDecimal chargeOutlierThreshold) {
    this.chargeOutlierThreshold = chargeOutlierThreshold.setScale(4, RoundingMode.HALF_UP);
  }

  public String getFiscalYearBeginDate() {
    return fiscalYearBeginDate;
  }

  public void setFiscalYearBeginDate(String fiscalYearBeginDate) {
    this.fiscalYearBeginDate = fiscalYearBeginDate;
  }

  public String getDischargeDate() {
    return dischargeDate;
  }

  public void setDischargeDate(String dischargeDate) {
    this.dischargeDate = dischargeDate;
  }

  public String getPpsBlendIndicator() {
    return ppsBlendIndicator;
  }

  public void setPpsBlendIndicator(String ppsBlendIndicator) {
    this.ppsBlendIndicator = ppsBlendIndicator;
  }
}
