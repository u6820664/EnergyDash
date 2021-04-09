package team.ienergy.energydash.beans;

import lombok.Data;

import java.sql.Blob;

@Data
public class RecommendPlan {
    String pid;
    String planName;
    String companyName;
    float totalCost;
    float saveMoney;
    String image;
}
