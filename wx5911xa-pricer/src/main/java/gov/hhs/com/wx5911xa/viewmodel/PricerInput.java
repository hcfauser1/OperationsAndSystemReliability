package gov.hhs.com.wx5911xa.viewmodel;


import gov.hhs.com.wx5911xa.exceptions.DateValidationException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class PricerInput {
  private long requestId;
  private String requestIdentifier;
  private BillingRecord billingRecord;
  private ProviderRecord providerRecord;

   public PricerInput() {
     // Needed for Jackson.objectMapper deserialization
   }

  public PricerInput(
      long requestId,
      String requestIdentifier,
      BillingRecord billingRecord,
      ProviderRecord providerRecord) {
    this.requestId = requestId;
    this.requestIdentifier = requestIdentifier;
    this.billingRecord = billingRecord;
    this.providerRecord = providerRecord;
  }

  public long getRequestId() {
    return requestId;
  }

  public String getRequestIdentifier() {
    return requestIdentifier;
  }

  public BillingRecord getBillingRecord() {
    return billingRecord;
  }

  public ProviderRecord getProviderRecord() {
    return providerRecord;
  }

  public void setRequestId(long requestId) {
    this.requestId = requestId;
  }

  public void setRequestIdentifier(String requestIdentifier) {
    this.requestIdentifier = requestIdentifier;
  }

  public void setBillingRecord(BillingRecord billingRecord) {
    this.billingRecord = billingRecord;
  }

  public void setProviderRecord(ProviderRecord providerRecord) {
    this.providerRecord = providerRecord;
  }

  public void validate() throws DateValidationException {
    ArrayList<String> invalidLength = new ArrayList<>();
    ArrayList<String> invalid = new ArrayList<>();
    DateFormat formatter = new SimpleDateFormat("yyyyMMDD");
    if (this.billingRecord.getDischargeDate().length() > 8) {
      invalidLength.add("dischargeDate");
    }
    if (this.providerRecord.getFiscalYearBeginDate().length() > 8) {
      invalidLength.add("fiscalYearBeginDate");
    }
    if (this.providerRecord.getTerminationDate().length() > 8) {
      invalidLength.add("terminationDate");
    }
    try {
      formatter.parse(this.billingRecord.getDischargeDate());
    } catch (Exception e) {
      invalid.add("dischargeDate");
    }
    try {
      formatter.parse(this.providerRecord.getFiscalYearBeginDate());
    } catch (Exception e) {
      invalid.add("fiscalYearBeginDate");
    }
    try {
      formatter.parse(this.providerRecord.getTerminationDate());
    } catch (Exception e) {
      invalid.add("terminationDate");
    }
    for (String var : invalidLength) {
      if (!invalid.contains(var)) {
        invalid.add(var);
      }
    }
    if (!invalid.isEmpty()) {
      throw new DateValidationException("Could not parse all dates provided.", invalid);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PricerInput)) return false;
    PricerInput irfInput = (PricerInput) o;
    return requestId == irfInput.requestId
        && Objects.equals(requestIdentifier, irfInput.requestIdentifier)
        && Objects.equals(billingRecord, irfInput.billingRecord)
        && Objects.equals(providerRecord, irfInput.providerRecord);
  }

  @Override
  public int hashCode() {

    return Objects.hash(requestId, requestIdentifier, billingRecord, providerRecord);
  }

  @Override
  public String toString(){

     return String.format("requestId: {} \r " +
             "requestIdentifier: {} \r" +
             "dischargeDate: {}",
             requestId==0 ? "0":requestId,
             requestIdentifier==null ? "null":requestIdentifier,
             this.billingRecord.getDischargeDate()==null ? "null":this.billingRecord.getDischargeDate()
     );
  }
}

