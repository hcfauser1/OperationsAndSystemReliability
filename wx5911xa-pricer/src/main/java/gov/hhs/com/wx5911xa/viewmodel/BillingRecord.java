package gov.hhs.com.wx5911xa.viewmodel;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BillingRecord {
    private String npi;
    private String providerNumber;
    private String patientStatus;
    private String cmgCode;
    private int lengthOfStay;
    private int coveredDays;
    private int lifetimeReserveDays;
    private String specialPayIndicator;
    private String dischargeDate;
    private BigDecimal coveredCharges;

    public BillingRecord() {
        // Needed for Jackson.objectMapper deserialization
      }
    
    //   @JsonCreator
      public BillingRecord(
              String npi,
              String providerNumber,
              String patientStatus,
              String cmgCode,
              int lengthOfStay,
              int coveredDays,
              int lifetimeReserveDays,
              String specialPayIndicator,
              String dischargeDate,
              BigDecimal coveredCharges)
        {
        this.npi = npi;
        this.providerNumber = providerNumber;
        this.patientStatus = patientStatus;
        this.cmgCode = cmgCode;
        this.lengthOfStay = lengthOfStay;
        this.coveredDays = coveredDays;
        this.lifetimeReserveDays = lifetimeReserveDays;
        this.specialPayIndicator = specialPayIndicator;
        this.dischargeDate = dischargeDate;
        this.coveredCharges = coveredCharges;
      }
    
      public String getNpi() {
        return npi;
      }
    
      public String getProviderNumber() {
        return providerNumber;
      }
    
      public String getPatientStatus() {
        return patientStatus;
      }
    
      public String getCmgCode() {
        return cmgCode;
      }
    
      public int getLengthOfStay() {
        return lengthOfStay;
      }
    
      public int getCoveredDays() {
        return coveredDays;
      }
    
      public int getLifetimeReserveDays() {
        return lifetimeReserveDays;
      }
    
      public String getSpecialPayIndicator() {
        return specialPayIndicator;
      }
    
      public String getDischargeDate() {
        return dischargeDate;
      }
    
      public BigDecimal getCoveredCharges() {
        return coveredCharges;
      }
    
      public void setNpi(String npi) {
        this.npi = npi;
      }
    
      public void setProviderNumber(String providerNumber) {
        this.providerNumber = providerNumber;
      }
    
      public void setPatientStatus(String patientStatus) {
        this.patientStatus = patientStatus;
      }
    
      public void setCmgCode(String cmgCode) {
        this.cmgCode = cmgCode;
      }
    
      public void setLengthOfStay(int lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
      }
    
      public void setCoveredDays(int coveredDays) {
        this.coveredDays = coveredDays;
      }
    
      public void setLifetimeReserveDays(int lifetimeReserveDays) {
        this.lifetimeReserveDays = lifetimeReserveDays;
      }
    
      public void setSpecialPayIndicator(String specialPayIndicator) {
        this.specialPayIndicator = specialPayIndicator;
      }
    
      public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
      }
    
      public void setCoveredCharges(BigDecimal coveredCharges) {
        this.coveredCharges = coveredCharges;
      }
    
      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BillingRecord)) return false;
        BillingRecord that = (BillingRecord) o;
        return lengthOfStay == that.lengthOfStay
            && coveredDays == that.coveredDays
            && lifetimeReserveDays == that.lifetimeReserveDays
            && Objects.equals(that.coveredCharges, coveredCharges)
            && Objects.equals(npi, that.npi)
            && Objects.equals(providerNumber, that.providerNumber)
            && Objects.equals(patientStatus, that.patientStatus)
            && Objects.equals(cmgCode, that.cmgCode)
            && Objects.equals(specialPayIndicator, that.specialPayIndicator)
            && Objects.equals(dischargeDate, that.dischargeDate);
      }
    
      @Override
      public int hashCode() {
    
        return Objects.hash(
            npi,
            providerNumber,
            patientStatus,
            cmgCode,
            lengthOfStay,
            coveredDays,
            lifetimeReserveDays,
            specialPayIndicator,
            dischargeDate,
            coveredCharges);
      }
    
      private static final String PATIENT_EXPIRED = "20";
    
      public boolean isPatientExpired() {
        return this.patientStatus.equals(PATIENT_EXPIRED);
      }
    
      // Note: These codes were taken from 88 codes in the original COBOL
      private static Set<String> patientTransferred =
          new HashSet<>(
              Arrays.asList("02", "03", "61", "62", "63", "64", "82", "83", "89", "90", "91", "92"));
    
      public boolean isPatientTransferred() {
        return patientTransferred.contains(this.patientStatus);
      }
    
      public boolean isNotOutlier() {
        return this.specialPayIndicator.equals("1") || this.specialPayIndicator.equals("3");
      }
    
      public boolean hasPenalties() {
        return this.specialPayIndicator.equals("2") || this.specialPayIndicator.equals("3");
      }
    }
    
