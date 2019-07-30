package gov.hhs.cms.pricer.v9109.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import gov.hhs.cms.pricer.v9109.viewmodel.*;

public class PricerControllerTest {

    @Test
    public void goodRequestShouldReturn200(){

        PricerInput pi = this.createRealRequest();

        PricerController pc = new PricerController();
        ResponseEntity<String> resp = pc.run(pi);

        assertEquals(resp.getStatusCodeValue(),200);
        assertNotNull(resp);

        try{
            ObjectMapper mapper = new ObjectMapper();
            PricerOutput po = mapper.readValue(
                    resp.getBody(),PricerOutput.class);
            assertEquals(pi.getRequestId(),
                    po.getRequestId());
        }
        catch (Exception ex){
            // failure
            assertEquals(1,2);
        }


    }

    private PricerInput createRealRequest() {
        BillingRecord br = new BillingRecord(
                "          ",
                "523027",  //@JsonProperty("providerNumber")
                "02",  //@JsonProperty("patientStatus") S
                "C2004", //@JsonProperty("cmgCode")
                5, //@JsonProperty("lengthOfStay")
                5, //@JsonProperty("coveredDays")
                0, //@JsonProperty("lifetimeReserveDays")
                " ", //@JsonProperty("specialPayIndicator")
                "20170415", //@JsonProperty("dischargeDate")
                new BigDecimal(10212.75)); //@JsonProperty("coveredCharges");

        MsaData msa = new MsaData(
                "0000",  //@JsonProperty("wageIndexLocation")
                "0",  //@JsonProperty("chargeCodeIndex")
                "5080",  //@JsonProperty("geolocation")
                "5080" //@JsonProperty("standardAmountLocation")
        );

        CbsaData cbsa = new CbsaData(
                new BigDecimal(0.0),  //@JsonProperty("wageIndex")
                "00000", //String standardAmountLocation,
                " ", //String specialPaymentIndicator,
                "1", //String hospitalQualityIndicator,
                "33340", //String geolocation,
                "     " //String reclassifiedLocation
        );

        ProviderRecord pr = new ProviderRecord(
                "          ", //npi
                "523027", // provider number
                "20161001", //effectiveDate
                "20160101", //"fiscalYearBeginDate"
                "20161231", //fiscalYearEndDate
                "00000000", //reportDate
                "00000000", //terminationDate
                "N",  //waiverCode
                "04", //providerType
                0, //intermediaryNumber
                4, //currentCensusDivision
                msa, // MsaData
                "00", //soleCommunityHospitalYear
                "0", //lugarReclassCode
                " ", //temporaryReliefIndicator
                "4", //federalPpsBlendIndicator
                new BigDecimal(0.0), //facilitySpecificRate
                new BigDecimal(0.0), //costOfLivingAdjustment
                new BigDecimal(0.0), // internToBedRatio
                0, //bedSize
                new BigDecimal(0.561), //costToChargeRatio
                new BigDecimal(0.0), //caseMixIndex
                new BigDecimal(0.0093), //ssiRatio
                new BigDecimal(0.0591), //medicaidRatio
                new BigDecimal(0.0), //ppsBlendYearIndicator
                new BigDecimal(0.0), //specialProviderUpdateFactor
                new BigDecimal(0.0), //disproportionateShareAdjustmentPercent
                cbsa,
                new BigDecimal(0.0), //passthruAmountCapital
                new BigDecimal(0.0), //passthruAmountDirectMedicalEducation
                new BigDecimal(0.0), //passthruAmountOrganAcquisition
                new BigDecimal(0.0), //passthruAmountPlusMisc
                " ", //capitalPpsPayCode
                new BigDecimal(0.0), //capitalPpsHospitalSpecificRate
                new BigDecimal(0.0), //oldCapitalHoldHarmlessRate
                new BigDecimal(0.0), //newCapitalHoldHarmlessRatio
                new BigDecimal(0.0), //capitalCostToChargeRatio
                " ", //newHospitalIndicator
                new BigDecimal(0.0), //capitalIndirectMedicalEducationRatio
                new BigDecimal(0.0), //capitalExceptionPaymentRate
                new BigDecimal(0.0) //valueBasedPurchaseScore
        );

        PricerInput pi = new PricerInput(123, "123a", br, pr);
        return pi;
    }
}