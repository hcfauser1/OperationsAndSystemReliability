package gov.hhs.com.wx5911xa.viewmodel;

import java.math.BigDecimal;
import java.util.Objects;

public class ProviderRecord {
    private String npi;
    private String providerNumber;
    private String effectiveDate;
    private String fiscalYearBeginDate;
    private String fiscalYearEndDate;
    private String reportDate;
    private String terminationDate;
    private String waiverCode;
    private String providerType;
    private int intermediaryNumber;
    private int currentCensusDivision;
    private MsaData msaData;
    private String soleCommunityHospitalYear;
    private String lugarReclassCode;
    private String temporaryReliefIndicator;
    private String federalPpsBlendIndicator;
    private BigDecimal facilitySpecificRate;
    private BigDecimal costOfLivingAdjustment;
    private BigDecimal internToBedRatio;
    private int bedSize;
    private BigDecimal costToChargeRatio;
    private BigDecimal caseMixIndex;
    private BigDecimal ssiRatio;
    private BigDecimal medicaidRatio;
    private BigDecimal ppsBlendYearIndicator;
    private BigDecimal specialProviderUpdateFactor;
    private BigDecimal disproportionateShareAdjustmentPercent;
    private CbsaData cbsaData;
    private BigDecimal passthruAmountCapital;
    private BigDecimal passthruAmountDirectMedicalEducation;
    private BigDecimal passthruAmountOrganAcquisition;
    private BigDecimal passthruAmountPlusMisc;
    private String capitalPpsPayCode;
    private BigDecimal capitalPpsHospitalSpecificRate;
    private BigDecimal oldCapitalHoldHarmlessRate;
    private BigDecimal newCapitalHoldHarmlessRatio;
    private BigDecimal capitalCostToChargeRatio;
    private String newHospitalIndicator;
    private BigDecimal capitalIndirectMedicalEducationRatio;
    private BigDecimal capitalExceptionPaymentRate;
    private BigDecimal valueBasedPurchaseScore;

    public ProviderRecord() {
        // Needed for Jackson.objectMapper deserialization
      }
    
      public ProviderRecord(
              String npi,
              String providerNumber,
              String effectiveDate,
              String fiscalYearBeginDate,
              String fiscalYearEndDate,
              String reportDate,
              String terminationDate,
              String waiverCode,
              String providerType,
              int intermediaryNumber,
              int currentCensusDivision,
              MsaData msaData,
              String soleCommunityHospitalYear,
              String lugarReclassCode,
              String temporaryReliefIndicator,
              String federalPpsBlendIndicator,
              BigDecimal facilitySpecificRate,
              BigDecimal costOfLivingAdjustment,
              BigDecimal internToBedRatio,
              int bedSize,
              BigDecimal costToChargeRatio,
              BigDecimal caseMixIndex,
              BigDecimal ssiRatio,
              BigDecimal medicaidRatio,
              BigDecimal ppsBlendYearIndicator,
              BigDecimal specialProviderUpdateFactor,
              BigDecimal disproportionateShareAdjustmentPercent,
              CbsaData cbsaData,
              BigDecimal passthruAmountCapital,
              BigDecimal passthruAmountDirectMedicalEducation,
              BigDecimal passthruAmountOrganAcquisition,
              BigDecimal passthruAmountPlusMisc,
              String capitalPpsPayCode,
              BigDecimal capitalPpsHospitalSpecificRate,
              BigDecimal oldCapitalHoldHarmlessRate,
              BigDecimal newCapitalHoldHarmlessRatio,
              BigDecimal capitalCostToChargeRatio,
              String newHospitalIndicator,
              BigDecimal capitalIndirectMedicalEducationRatio,
              BigDecimal capitalExceptionPaymentRate,
              BigDecimal valueBasedPurchaseScore) {
        this.npi = npi;
        this.providerNumber = providerNumber;
        this.effectiveDate = effectiveDate;
        this.fiscalYearBeginDate = fiscalYearBeginDate;
        this.fiscalYearEndDate = fiscalYearEndDate;
        this.reportDate = reportDate;
        this.terminationDate = terminationDate;
        this.waiverCode = waiverCode;
        this.providerType = providerType;
        this.intermediaryNumber = intermediaryNumber;
        this.currentCensusDivision = currentCensusDivision;
        this.msaData = msaData;
        this.soleCommunityHospitalYear = soleCommunityHospitalYear;
        this.lugarReclassCode = lugarReclassCode;
        this.temporaryReliefIndicator = temporaryReliefIndicator;
        this.federalPpsBlendIndicator = federalPpsBlendIndicator;
        this.facilitySpecificRate = facilitySpecificRate;
        this.costOfLivingAdjustment = costOfLivingAdjustment;
        this.internToBedRatio = internToBedRatio;
        this.bedSize = bedSize;
        this.costToChargeRatio = costToChargeRatio;
        this.caseMixIndex = caseMixIndex;
        this.ssiRatio = ssiRatio;
        this.medicaidRatio = medicaidRatio;
        this.ppsBlendYearIndicator = ppsBlendYearIndicator;
        this.specialProviderUpdateFactor = specialProviderUpdateFactor;
        this.disproportionateShareAdjustmentPercent = disproportionateShareAdjustmentPercent;
        this.cbsaData = cbsaData;
        this.passthruAmountCapital = passthruAmountCapital;
        this.passthruAmountDirectMedicalEducation = passthruAmountDirectMedicalEducation;
        this.passthruAmountOrganAcquisition = passthruAmountOrganAcquisition;
        this.passthruAmountPlusMisc = passthruAmountPlusMisc;
        this.capitalPpsPayCode = capitalPpsPayCode;
        this.capitalPpsHospitalSpecificRate = capitalPpsHospitalSpecificRate;
        this.oldCapitalHoldHarmlessRate = oldCapitalHoldHarmlessRate;
        this.newCapitalHoldHarmlessRatio = newCapitalHoldHarmlessRatio;
        this.capitalCostToChargeRatio = capitalCostToChargeRatio;
        this.newHospitalIndicator = newHospitalIndicator;
        this.capitalIndirectMedicalEducationRatio = capitalIndirectMedicalEducationRatio;
        this.capitalExceptionPaymentRate = capitalExceptionPaymentRate;
        this.valueBasedPurchaseScore = valueBasedPurchaseScore;
      }
    
      public String getNpi() {
        return npi;
      }
    
      public String getProviderNumber() {
        return providerNumber;
      }
    
      public String getEffectiveDate() {
        return effectiveDate;
      }
    
      public String getFiscalYearBeginDate() {
        return fiscalYearBeginDate;
      }
    
      public String getFiscalYearEndDate() {
        return fiscalYearEndDate;
      }
    
      public String getReportDate() {
        return reportDate;
      }
    
      public String getTerminationDate() {
        return terminationDate;
      }
    
      public String getWaiverCode() {
        return waiverCode;
      }
    
      public String getProviderType() {
        return providerType;
      }
    
      public int getIntermediaryNumber() {
        return intermediaryNumber;
      }
    
      public int getCurrentCensusDivision() {
        return currentCensusDivision;
      }
    
      public MsaData getMsaData() {
        return msaData;
      }
    
      public String getSoleCommunityHospitalYear() {
        return soleCommunityHospitalYear;
      }
    
      public String getLugarReclassCode() {
        return lugarReclassCode;
      }
    
      public String getTemporaryReliefIndicator() {
        return temporaryReliefIndicator;
      }
    
      public String getFederalPpsBlendIndicator() {
        return federalPpsBlendIndicator;
      }
    
      public BigDecimal getFacilitySpecificRate() {
        return facilitySpecificRate;
      }
    
      public BigDecimal getCostOfLivingAdjustment() {
        return costOfLivingAdjustment;
      }
    
      public BigDecimal getInternToBedRatio() {
        return internToBedRatio;
      }
    
      public int getBedSize() {
        return bedSize;
      }
    
      public BigDecimal getCostToChargeRatio() {
        return costToChargeRatio;
      }
    
      public BigDecimal getCaseMixIndex() {
        return caseMixIndex;
      }
    
      public BigDecimal getSsiRatio() {
        return ssiRatio;
      }
    
      public BigDecimal getMedicaidRatio() {
        return medicaidRatio;
      }
    
      public BigDecimal getPpsBlendYearIndicator() {
        return ppsBlendYearIndicator;
      }
    
      public BigDecimal getSpecialProviderUpdateFactor() {
        return specialProviderUpdateFactor;
      }
    
      public BigDecimal getDisproportionateShareAdjustmentPercent() {
        return disproportionateShareAdjustmentPercent;
      }
    
      public CbsaData getCbsaData() {
        return cbsaData;
      }
    
      public BigDecimal getPassthruAmountCapital() {
        return passthruAmountCapital;
      }
    
      public BigDecimal getPassthruAmountDirectMedicalEducation() {
        return passthruAmountDirectMedicalEducation;
      }
    
      public BigDecimal getPassthruAmountOrganAcquisition() {
        return passthruAmountOrganAcquisition;
      }
    
      public BigDecimal getPassthruAmountPlusMisc() {
        return passthruAmountPlusMisc;
      }
    
      public String getCapitalPpsPayCode() {
        return capitalPpsPayCode;
      }
    
      public BigDecimal getCapitalPpsHospitalSpecificRate() {
        return capitalPpsHospitalSpecificRate;
      }
    
      public BigDecimal getOldCapitalHoldHarmlessRate() {
        return oldCapitalHoldHarmlessRate;
      }
    
      public BigDecimal getNewCapitalHoldHarmlessRatio() {
        return newCapitalHoldHarmlessRatio;
      }
    
      public BigDecimal getCapitalCostToChargeRatio() {
        return capitalCostToChargeRatio;
      }
    
      public String getNewHospitalIndicator() {
        return newHospitalIndicator;
      }
    
      public BigDecimal getCapitalIndirectMedicalEducationRatio() {
        return capitalIndirectMedicalEducationRatio;
      }
    
      public BigDecimal getCapitalExceptionPaymentRate() {
        return capitalExceptionPaymentRate;
      }
    
      public BigDecimal getValueBasedPurchaseScore() {
        return valueBasedPurchaseScore;
      }
    
      public void setNpi(String npi) {
        this.npi = npi;
      }
    
      public void setProviderNumber(String providerNumber) {
        this.providerNumber = providerNumber;
      }
    
      public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
      }
    
      public void setFiscalYearBeginDate(String fiscalYearBeginDate) {
        this.fiscalYearBeginDate = fiscalYearBeginDate;
      }
    
      public void setFiscalYearEndDate(String fiscalYearEndDate) {
        this.fiscalYearEndDate = fiscalYearEndDate;
      }
    
      public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
      }
    
      public void setTerminationDate(String terminationDate) {
        this.terminationDate = terminationDate;
      }
    
      public void setWaiverCode(String waiverCode) {
        this.waiverCode = waiverCode;
      }
    
      public void setProviderType(String providerType) {
        this.providerType = providerType;
      }
    
      public void setIntermediaryNumber(int intermediaryNumber) {
        this.intermediaryNumber = intermediaryNumber;
      }
    
      public void setCurrentCensusDivision(int currentCensusDivision) {
        this.currentCensusDivision = currentCensusDivision;
      }
    
      public void setMsaData(MsaData msaData) {
        this.msaData = msaData;
      }
    
      public void setSoleCommunityHospitalYear(String soleCommunityHospitalYear) {
        this.soleCommunityHospitalYear = soleCommunityHospitalYear;
      }
    
      public void setLugarReclassCode(String lugarReclassCode) {
        this.lugarReclassCode = lugarReclassCode;
      }
    
      public void setTemporaryReliefIndicator(String temporaryReliefIndicator) {
        this.temporaryReliefIndicator = temporaryReliefIndicator;
      }
    
      public void setFederalPpsBlendIndicator(String federalPpsBlendIndicator) {
        this.federalPpsBlendIndicator = federalPpsBlendIndicator;
      }
    
      public void setFacilitySpecificRate(BigDecimal facilitySpecificRate) {
        this.facilitySpecificRate = facilitySpecificRate;
      }
    
      public void setCostOfLivingAdjustment(BigDecimal costOfLivingAdjustment) {
        this.costOfLivingAdjustment = costOfLivingAdjustment;
      }
    
      public void setInternToBedRatio(BigDecimal internToBedRatio) {
        this.internToBedRatio = internToBedRatio;
      }
    
      public void setBedSize(int bedSize) {
        this.bedSize = bedSize;
      }
    
      public void setCostToChargeRatio(BigDecimal costToChargeRatio) {
        this.costToChargeRatio = costToChargeRatio;
      }
    
      public void setCaseMixIndex(BigDecimal caseMixIndex) {
        this.caseMixIndex = caseMixIndex;
      }
    
      public void setSsiRatio(BigDecimal ssiRatio) {
        this.ssiRatio = ssiRatio;
      }
    
      public void setMedicaidRatio(BigDecimal medicaidRatio) {
        this.medicaidRatio = medicaidRatio;
      }
    
      public void setPpsBlendYearIndicator(BigDecimal ppsBlendYearIndicator) {
        this.ppsBlendYearIndicator = ppsBlendYearIndicator;
      }
    
      public void setSpecialProviderUpdateFactor(BigDecimal specialProviderUpdateFactor) {
        this.specialProviderUpdateFactor = specialProviderUpdateFactor;
      }
    
      public void setDisproportionateShareAdjustmentPercent(
          BigDecimal disproportionateShareAdjustmentPercent) {
        this.disproportionateShareAdjustmentPercent = disproportionateShareAdjustmentPercent;
      }
    
      public void setCbsaData(CbsaData cbsaData) {
        this.cbsaData = cbsaData;
      }
    
      public void setPassthruAmountCapital(BigDecimal passthruAmountCapital) {
        this.passthruAmountCapital = passthruAmountCapital;
      }
    
      public void setPassthruAmountDirectMedicalEducation(
          BigDecimal passthruAmountDirectMedicalEducation) {
        this.passthruAmountDirectMedicalEducation = passthruAmountDirectMedicalEducation;
      }
    
      public void setPassthruAmountOrganAcquisition(BigDecimal passthruAmountOrganAcquisition) {
        this.passthruAmountOrganAcquisition = passthruAmountOrganAcquisition;
      }
    
      public void setPassthruAmountPlusMisc(BigDecimal passthruAmountPlusMisc) {
        this.passthruAmountPlusMisc = passthruAmountPlusMisc;
      }
    
      public void setCapitalPpsPayCode(String capitalPpsPayCode) {
        this.capitalPpsPayCode = capitalPpsPayCode;
      }
    
      public void setCapitalPpsHospitalSpecificRate(BigDecimal capitalPpsHospitalSpecificRate) {
        this.capitalPpsHospitalSpecificRate = capitalPpsHospitalSpecificRate;
      }
    
      public void setOldCapitalHoldHarmlessRate(BigDecimal oldCapitalHoldHarmlessRate) {
        this.oldCapitalHoldHarmlessRate = oldCapitalHoldHarmlessRate;
      }
    
      public void setNewCapitalHoldHarmlessRatio(BigDecimal newCapitalHoldHarmlessRatio) {
        this.newCapitalHoldHarmlessRatio = newCapitalHoldHarmlessRatio;
      }
    
      public void setCapitalCostToChargeRatio(BigDecimal capitalCostToChargeRatio) {
        this.capitalCostToChargeRatio = capitalCostToChargeRatio;
      }
    
      public void setNewHospitalIndicator(String newHospitalIndicator) {
        this.newHospitalIndicator = newHospitalIndicator;
      }
    
      public void setCapitalIndirectMedicalEducationRatio(
          BigDecimal capitalIndirectMedicalEducationRatio) {
        this.capitalIndirectMedicalEducationRatio = capitalIndirectMedicalEducationRatio;
      }
    
      public void setCapitalExceptionPaymentRate(BigDecimal capitalExceptionPaymentRate) {
        this.capitalExceptionPaymentRate = capitalExceptionPaymentRate;
      }
    
      public void setValueBasedPurchaseScore(BigDecimal valueBasedPurchaseScore) {
        this.valueBasedPurchaseScore = valueBasedPurchaseScore;
      }
    
      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProviderRecord)) {
          return false;
        }
        ProviderRecord that = (ProviderRecord) o;
        return intermediaryNumber == that.intermediaryNumber
            && currentCensusDivision == that.currentCensusDivision
            && Objects.equals(that.facilitySpecificRate, facilitySpecificRate)
            && Objects.equals(that.costOfLivingAdjustment, costOfLivingAdjustment)
            && Objects.equals(that.internToBedRatio, internToBedRatio)
            && bedSize == that.bedSize
            && Objects.equals(that.costToChargeRatio, costToChargeRatio)
            && Objects.equals(that.caseMixIndex, caseMixIndex)
            && Objects.equals(that.ssiRatio, ssiRatio)
            && Objects.equals(that.medicaidRatio, medicaidRatio)
            && Objects.equals(that.ppsBlendYearIndicator, ppsBlendYearIndicator)
            && Objects.equals(that.specialProviderUpdateFactor, specialProviderUpdateFactor)
            && Objects.equals(
                that.disproportionateShareAdjustmentPercent, disproportionateShareAdjustmentPercent)
            && Objects.equals(that.passthruAmountCapital, passthruAmountCapital)
            && Objects.equals(
                that.passthruAmountDirectMedicalEducation, passthruAmountDirectMedicalEducation)
            && Objects.equals(that.passthruAmountOrganAcquisition, passthruAmountOrganAcquisition)
            && Objects.equals(that.passthruAmountPlusMisc, passthruAmountPlusMisc)
            && Objects.equals(that.capitalPpsHospitalSpecificRate, capitalPpsHospitalSpecificRate)
            && Objects.equals(that.oldCapitalHoldHarmlessRate, oldCapitalHoldHarmlessRate)
            && Objects.equals(that.newCapitalHoldHarmlessRatio, newCapitalHoldHarmlessRatio)
            && Objects.equals(that.capitalCostToChargeRatio, capitalCostToChargeRatio)
            && Objects.equals(
                that.capitalIndirectMedicalEducationRatio, capitalIndirectMedicalEducationRatio)
            && Objects.equals(that.capitalExceptionPaymentRate, capitalExceptionPaymentRate)
            && Objects.equals(that.valueBasedPurchaseScore, valueBasedPurchaseScore)
            && Objects.equals(npi, that.npi)
            && Objects.equals(providerNumber, that.providerNumber)
            && Objects.equals(effectiveDate, that.effectiveDate)
            && Objects.equals(fiscalYearBeginDate, that.fiscalYearBeginDate)
            && Objects.equals(fiscalYearEndDate, that.fiscalYearEndDate)
            && Objects.equals(reportDate, that.reportDate)
            && Objects.equals(terminationDate, that.terminationDate)
            && Objects.equals(waiverCode, that.waiverCode)
            && Objects.equals(providerType, that.providerType)
            && Objects.equals(msaData, that.msaData)
            && Objects.equals(soleCommunityHospitalYear, that.soleCommunityHospitalYear)
            && Objects.equals(lugarReclassCode, that.lugarReclassCode)
            && Objects.equals(temporaryReliefIndicator, that.temporaryReliefIndicator)
            && Objects.equals(federalPpsBlendIndicator, that.federalPpsBlendIndicator)
            && Objects.equals(cbsaData, that.cbsaData)
            && Objects.equals(capitalPpsPayCode, that.capitalPpsPayCode)
            && Objects.equals(newHospitalIndicator, that.newHospitalIndicator);
      }
    
      @Override
      public int hashCode() {
    
        return Objects.hash(
            npi,
            providerNumber,
            effectiveDate,
            fiscalYearBeginDate,
            fiscalYearEndDate,
            reportDate,
            terminationDate,
            waiverCode,
            providerType,
            intermediaryNumber,
            currentCensusDivision,
            msaData,
            soleCommunityHospitalYear,
            lugarReclassCode,
            temporaryReliefIndicator,
            federalPpsBlendIndicator,
            facilitySpecificRate,
            costOfLivingAdjustment,
            internToBedRatio,
            bedSize,
            costToChargeRatio,
            caseMixIndex,
            ssiRatio,
            medicaidRatio,
            ppsBlendYearIndicator,
            specialProviderUpdateFactor,
            disproportionateShareAdjustmentPercent,
            cbsaData,
            passthruAmountCapital,
            passthruAmountDirectMedicalEducation,
            passthruAmountOrganAcquisition,
            passthruAmountPlusMisc,
            capitalPpsPayCode,
            capitalPpsHospitalSpecificRate,
            oldCapitalHoldHarmlessRate,
            newCapitalHoldHarmlessRatio,
            capitalCostToChargeRatio,
            newHospitalIndicator,
            capitalIndirectMedicalEducationRatio,
            capitalExceptionPaymentRate,
            valueBasedPurchaseScore);
      }
    }
    