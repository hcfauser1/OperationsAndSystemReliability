package gov.hhs.com.wx5911xa.viewmodel;


import gov.hhs.com.wx5911xa.core.ResultCode;

import java.util.Objects;

public class PricerResult {
  private int code;
  private ResultCode resultCode;
  private String explanation;

  public PricerResult() {
    // Needed for Jackson.objectMapper deserialization
  }

  public PricerResult(ResultCode code) {
    this.resultCode = code;
    this.code = code.getCode();
    this.explanation = code.getExplanation();
  }

  public int getCode() {
    return code;
  }

  public ResultCode getResultCode() {
    return resultCode;
  }

  public String getExplanation() {
    return explanation;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public void setResultCode(ResultCode code) {
    this.resultCode = code;
    this.code = code.getCode();
    this.explanation = code.getExplanation();
  }

  public void setExplanation(String explanation) {
    this.explanation = explanation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PricerResult)) return false;
    PricerResult pricerResult = (PricerResult) o;
    return code == pricerResult.code && Objects.equals(explanation, pricerResult.explanation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, explanation);
  }
}
