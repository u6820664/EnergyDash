package team.ienergy.energydash.beans;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Hao Cao
 * @date: 11 October 2020
 * @throws
 */

@Data
public class EnergyPlan {

    private String pid;

    private String planName;

    private String companyName;

    private String customerType;

    private String postcode;

    private String tariffType;

    private int isDemand;

    private String supplyPrice;

    private String singlePrice;

    private int usageLimit;

    private String usagePrice1;

    private String usagePrice2;

    private String peakPrice;

    private String offPeakPrice;

    private String shoulderPrice;

    private int tou1;

    private int tou2;

    private int tou3;

    private int tou4;

    private int tou5;

    private int tou6;

    private int p1id;

    private int p2id;

    private int p3id;

    private int p4id;

    private int p5id;

    private int p6id;

    private int demandStart;

    private int demandEnd;

    private String demandPrice;

}
