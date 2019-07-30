package gov.hhs.com.wx5911xa.core;

public class Constants {
  private double nationalLaborPercentage;
  private double nationalNonLaborPercentage;
  private double nationalThresholdAdjustment;
  private double budgetNeutralConversionAmount;
  private double budgetNeutralConversionAmount2;
  private double ruralAdjustment;
  private double ruralAdjustment2;
  private double calcLowIncomePercentage;
  private double calcTeach;

  private Constants(
      double nationalLaborPercentage,
      double nationalNonLaborPercentage,
      double nationalThresholdAdjustment,
      double budgetNeutralConversionAmount,
      double budgetNeutralConversionAmount2,
      double ruralAdjustment,
      double ruralAdjustment2,
      double calcLowIncomePercentage,
      double calcTeach) {
    this.nationalLaborPercentage = nationalLaborPercentage;
    this.nationalNonLaborPercentage = nationalNonLaborPercentage;
    this.nationalThresholdAdjustment = nationalThresholdAdjustment;
    this.budgetNeutralConversionAmount = budgetNeutralConversionAmount;
    this.budgetNeutralConversionAmount2 = budgetNeutralConversionAmount2;
    this.ruralAdjustment = ruralAdjustment;
    this.ruralAdjustment2 = ruralAdjustment2;
    this.calcLowIncomePercentage = calcLowIncomePercentage;
    this.calcTeach = calcTeach;
  }

  public static Constants getConstants(String year) {
    if (year.equals("2017")) {
      return new Constants(
          0.70900, 0.29100, 7984.00, 15708.00, 15399.00, 1.0497, 1.1490, 0.3177, 1.0163);
    } else if (year.equals("2016")) {
      return new Constants(
          0.71000, 0.29000, 8658.00, 15478.00, 15174.00, 1.0993, 1.1490, 0.3177, 1.0163);
    } else if (year.equals("2015")) {
      return new Constants(
          0.69294, 0.30706, 8848.00, 15198.00, 14901.00, 0, 1.1490, 0.3177, 1.0163);
    }
    return null;
  }

  public double getNationalLaborPercentage() {
    return nationalLaborPercentage;
  }

  public double getNationalNonLaborPercentage() {
    return nationalNonLaborPercentage;
  }

  public double getNationalThresholdAdjustment() {
    return nationalThresholdAdjustment;
  }

  public double getBudgetNeutralConversionAmount() {
    return budgetNeutralConversionAmount;
  }

  public double getBudgetNeutralConversionAmount2() {
    return budgetNeutralConversionAmount2;
  }

  public double getRuralAdjustment() {
    return ruralAdjustment;
  }

  public double getRuralAdjustment2() {
    return ruralAdjustment2;
  }

  public double getCalcLowIncomePercentage() {
    return calcLowIncomePercentage;
  }

  public double getCalcTeach() {
    return calcTeach;
  }
}
