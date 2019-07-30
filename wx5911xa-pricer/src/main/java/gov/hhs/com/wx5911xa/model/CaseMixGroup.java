package gov.hhs.com.wx5911xa.model;

public class CaseMixGroup {

    private String cmgCode;
    private double aRelativeWeight;
    private double bRelativeWeight;
    private double cRelativeWeight;
    private double dRelativeWeight;
    private int aAverageLengthOfStay;
    private int bAverageLengthOfStay;
    private int cAverageLengthOfStay;
    private int dAverageLengthOfStay;

    public CaseMixGroup(String cmgCode, double bRelativeWeight, double cRelativeWeight, double dRelativeWeight, double aRelativeWeight, int bAverageLengthOfStay, int cAverageLengthOfStay, int dAverageLengthOfStay, int aAverageLengthOfStay) {
        this.cmgCode = cmgCode;
        this.aRelativeWeight = aRelativeWeight;
        this.bRelativeWeight = bRelativeWeight;
        this.cRelativeWeight = cRelativeWeight;
        this.dRelativeWeight = dRelativeWeight;
        this.aAverageLengthOfStay = aAverageLengthOfStay;
        this.bAverageLengthOfStay = bAverageLengthOfStay;
        this.cAverageLengthOfStay = cAverageLengthOfStay;
        this.dAverageLengthOfStay = dAverageLengthOfStay;
    }

    public String getCmgCode() {
        return cmgCode;
    }

    public double getARelativeWeight() {
        return aRelativeWeight;
    }

    public double getBRelativeWeight() {
        return bRelativeWeight;
    }

    public double getCRelativeWeight() {
        return cRelativeWeight;
    }

    public double getDRelativeWeight() {
        return dRelativeWeight;
    }

    public int getAAverageLengthOfStay() {
        return aAverageLengthOfStay;
    }

    public int getBAverageLengthOfStay() {
        return bAverageLengthOfStay;
    }

    public int getCAverageLengthOfStay() {
        return cAverageLengthOfStay;
    }

    public int getDAverageLengthOfStay() {
        return dAverageLengthOfStay;
    }
}
