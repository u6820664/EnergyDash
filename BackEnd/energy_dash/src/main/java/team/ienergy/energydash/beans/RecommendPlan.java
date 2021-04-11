package team.ienergy.energydash.beans;

import lombok.Data;

@Data
public class RecommendPlan {
    String pid;
    String planName;
    String companyName;
    String tariff;
    float totalCost;
    float saveMoney;
    float energyPer;
    float supplyPer;
    String image;
    String flag;
}
