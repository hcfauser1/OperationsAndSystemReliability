package gov.hhs.com.wx5911xa.viewmodel;

import java.util.Objects;

public class PricerOutput {  //extends IrfObj
  private long requestId;
  private String requestIdentifier;
  private ProspectivePaymentRecord record;

   public PricerOutput() {
     // Needed for Jackson.objectMapper deserialization
   }

  public PricerOutput(long requestId, String requestIdentifier, ProspectivePaymentRecord record) {
    this.requestId = requestId;
    this.requestIdentifier = requestIdentifier;
    this.record = record;
  }

  public long getRequestId() {
    return requestId;
  }

  public String getRequestIdentifier() {
    return requestIdentifier;
  }

  public ProspectivePaymentRecord getRecord() {
    return record;
  }

  public void setRequestId(long requestId) {
    this.requestId = requestId;
  }

  public void setRequestIdentifier(String requestIdentifier) {
    this.requestIdentifier = requestIdentifier;
  }

  public void setRecord(ProspectivePaymentRecord record) {
    this.record = record;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PricerOutput)) return false;
    PricerOutput pricerOutput = (PricerOutput) o;
    return requestId == pricerOutput.requestId
        && Objects.equals(requestIdentifier, pricerOutput.requestIdentifier)
        && Objects.equals(record, pricerOutput.record);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestId, requestIdentifier, record);
  }
}

