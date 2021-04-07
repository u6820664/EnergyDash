package team.ienergy.energydash.beans;

import lombok.Data;

@Data
public class RecommendPlan {
    String pid;
    String planName;
    String companyName;
    float totalCost;
    float saveMoney;
}
