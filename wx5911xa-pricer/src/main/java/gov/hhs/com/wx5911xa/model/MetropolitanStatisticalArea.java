package gov.hhs.com.wx5911xa.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

public class MetropolitanStatisticalArea implements Comparable<MetropolitanStatisticalArea> {
    private String msaNumber;
    private String effectiveDate;
    private BigDecimal wageIndex;

    public MetropolitanStatisticalArea(){
        //needed for Jackson.objectMapper deserialization
    }
    public MetropolitanStatisticalArea(String msaNumber, String effectiveDate, BigDecimal wageIndex) {
        this.msaNumber = msaNumber;
        this.effectiveDate = effectiveDate;
        this.wageIndex = wageIndex;
    }

    public String getMsaNumber() {
        return msaNumber;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public BigDecimal getWageIndex() {
        return wageIndex;
    }

    @Override
    public int compareTo(MetropolitanStatisticalArea other){

        return  Comparator.comparing(MetropolitanStatisticalArea::getMsaNumber)
                .thenComparing(MetropolitanStatisticalArea::getEffectiveDate)
                .thenComparing(MetropolitanStatisticalArea::getWageIndex)
                .compare(this,other);
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        if (!(other instanceof MetropolitanStatisticalArea)) return false;
        MetropolitanStatisticalArea otherMetropolitanStatisticalArea = (MetropolitanStatisticalArea)other;
        return Objects.equals(this.msaNumber, otherMetropolitanStatisticalArea.msaNumber) &&
                Objects.equals(this.effectiveDate, otherMetropolitanStatisticalArea.effectiveDate) &&
                Objects.equals(this.wageIndex, otherMetropolitanStatisticalArea.wageIndex);

    }
}
