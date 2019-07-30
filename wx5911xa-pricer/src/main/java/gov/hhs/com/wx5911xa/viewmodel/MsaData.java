package gov.hhs.com.wx5911xa.viewmodel;

import java.util.Objects;

public class MsaData {
  private String wageIndexLocation;
  private String chargeCodeIndex;
  private String geolocation;
  private String standardAmountLocation;

  public MsaData(
          String wageIndexLocation,
          String chargeCodeIndex,
          String geolocation,
          String standardAmountLocation)
  {
    this.wageIndexLocation = wageIndexLocation;
    this.chargeCodeIndex = chargeCodeIndex;
    this.geolocation = geolocation;
    this.standardAmountLocation = standardAmountLocation;
  }

   public MsaData() {
     // Needed for Jackson.objectMapper deserialization
   }

  public String getChargeCodeIndex() {
    return chargeCodeIndex;
  }

  public String getGeolocation() {
    return geolocation;
  }

  public String getWageIndexLocation() {
    return wageIndexLocation;
  }

  public String getStandardAmountLocation() {
    return standardAmountLocation;
  }

  public void setWageIndexLocation(String wageIndexLocation) {
    this.wageIndexLocation = wageIndexLocation;
  }

  public void setChargeCodeIndex(String chargeCodeIndex) {
    this.chargeCodeIndex = chargeCodeIndex;
  }

  public void setGeolocation(String geolocation) {
    this.geolocation = geolocation;
  }

  public void setStandardAmountLocation(String standardAmountLocation) {
    this.standardAmountLocation = standardAmountLocation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MsaData)) return false;
    MsaData data = (MsaData) o;
    return Objects.equals(data.getChargeCodeIndex(), chargeCodeIndex)
        && Objects.equals(data.getGeolocation(), geolocation)
        && Objects.equals(data.getStandardAmountLocation(), standardAmountLocation)
        && Objects.equals(data.getWageIndexLocation(), wageIndexLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(chargeCodeIndex, geolocation, standardAmountLocation, wageIndexLocation);
  }
}
