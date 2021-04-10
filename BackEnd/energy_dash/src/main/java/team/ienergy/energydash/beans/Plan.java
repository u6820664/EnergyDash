package team.ienergy.energydash.beans;

import lombok.Data;

/**
 * @author: Hao Cao
 * @date: 27 September 2020
 * @throws
 */

@Data
public class Plan {
    private String pid;

    private String planName;

    private String companyName;

    private String customerType;

    private String postcode;

    private String tariffType;

    private String supplyPrice;

    private String singlePrice;

    private String peakPrice;

    private String offPeakPrice;

    private String shoulderPrice;

    private String tou1;
    private String tou2;
    private String tou3;
    private String tou4;
    private String tou5;
    private String tou6;

    private String demandStart;
    private String demandEnd;
    private String demandPrice;
}
