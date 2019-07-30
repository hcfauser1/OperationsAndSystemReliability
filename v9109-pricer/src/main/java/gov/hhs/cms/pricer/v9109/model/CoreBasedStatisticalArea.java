package gov.hhs.cms.pricer.v9109.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

public class CoreBasedStatisticalArea implements Comparable<CoreBasedStatisticalArea> {
    private String cbsaNumber;
    private String effectiveDate;
    private BigDecimal wageIndex;

    public CoreBasedStatisticalArea(String cbsa, String effectiveDate, BigDecimal wageIndex){
        this.cbsaNumber = cbsa;
        this.effectiveDate = effectiveDate;
        this.wageIndex = wageIndex;
    }

    public String getCbsaNumber() {
        return this.cbsaNumber;
    }

    public String getEffectiveDate(){
        return this.effectiveDate;
    }

    public BigDecimal getWageIndex(){
        return this.wageIndex;
    }

    @Override
    public int compareTo(CoreBasedStatisticalArea other){

        return  Comparator.comparing(CoreBasedStatisticalArea::getCbsaNumber)
                .thenComparing(CoreBasedStatisticalArea::getEffectiveDate)
                .thenComparing(CoreBasedStatisticalArea::getWageIndex)
                .compare(this,other);

    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        if (!(other instanceof CoreBasedStatisticalArea)) return false;
        CoreBasedStatisticalArea otherCoreBasedStatisticalArea = (CoreBasedStatisticalArea)other;
        return Objects.equals(this.cbsaNumber, otherCoreBasedStatisticalArea.cbsaNumber) &&
                Objects.equals(this.effectiveDate, otherCoreBasedStatisticalArea.effectiveDate) &&
                Objects.equals(this.wageIndex, otherCoreBasedStatisticalArea.wageIndex);

    }

    @Override
    public int hashCode(){
        return Objects.hash(this.cbsaNumber,this.effectiveDate,this.wageIndex);
    }
}
