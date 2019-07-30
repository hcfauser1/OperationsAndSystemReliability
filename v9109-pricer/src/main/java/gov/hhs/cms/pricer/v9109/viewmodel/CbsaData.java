package gov.hhs.cms.pricer.v9109.viewmodel;

import java.math.BigDecimal;
import java.util.Objects;

public class CbsaData {
  private BigDecimal wageIndex;
  private String standardAmountLocation;
  private String specialPaymentIndicator;
  private String hospitalQualityIndicator;
  private String geolocation;
  private String reclassifiedLocation;

  //@JsonCreator
  public CbsaData(
          BigDecimal wageIndex,
          String standardAmountLocation,
          String specialPaymentIndicator,
          String hospitalQualityIndicator,
          String geolocation,
          String reclassifiedLocation) {
    this.wageIndex = wageIndex;
    this.standardAmountLocation = standardAmountLocation;
    this.specialPaymentIndicator = specialPaymentIndicator;
    this.hospitalQualityIndicator = hospitalQualityIndicator;
    this.geolocation = geolocation;
    this.reclassifiedLocation = reclassifiedLocation;
  }

   public CbsaData() {

   }

  public BigDecimal getWageIndex() {
    return wageIndex;
  }

  public String getStandardAmountLocation() {
    return standardAmountLocation;
  }

  public String getSpecialPaymentIndicator() {
    return specialPaymentIndicator;
  }

  public String getHospitalQualityIndicator() {
    return hospitalQualityIndicator;
  }

  public String getGeolocation() {
    return geolocation;
  }

  public String getReclassifiedLocation() {
    return reclassifiedLocation;
  }

  public void setWageIndex(BigDecimal wageIndex) {
    this.wageIndex = wageIndex;
  }

  public void setStandardAmountLocation(String standardAmountLocation) {
    this.standardAmountLocation = standardAmountLocation;
  }

  public void setSpecialPaymentIndicator(String specialPaymentIndicator) {
    this.specialPaymentIndicator = specialPaymentIndicator;
  }

  public void setHospitalQualityIndicator(String hospitalQualityIndicator) {
    this.hospitalQualityIndicator = hospitalQualityIndicator;
  }

  public void setGeolocation(String geolocation) {
    this.geolocation = geolocation;
  }

  public void setReclassifiedLocation(String reclassifiedLocation) {
    this.reclassifiedLocation = reclassifiedLocation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CbsaData)) return false;
    CbsaData that = (CbsaData) o;
    return Objects.equals(wageIndex, that.wageIndex)
        && Objects.equals(standardAmountLocation, that.standardAmountLocation)
        && Objects.equals(hospitalQualityIndicator, that.hospitalQualityIndicator)
        && Objects.equals(specialPaymentIndicator, that.specialPaymentIndicator)
        && Objects.equals(geolocation, that.geolocation)
        && Objects.equals(reclassifiedLocation, that.reclassifiedLocation);
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        wageIndex,
        standardAmountLocation,
        hospitalQualityIndicator,
        specialPaymentIndicator,
        geolocation,
        reclassifiedLocation);
  }
}
