package team.ienergy.energydash.beans;

import lombok.Data;

/**
 * @author: Hao Cao
 * @date: 26 August 2020
 * @throws
 */
@Data
public class User {
    private String userId;
    private String userName;
    private String password;
    private String planId;
    private String energyPlan;

}