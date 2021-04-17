package team.ienergy.energydash.beans;

import lombok.Data;

/**
 * @author: Mingchao Sima
 * @date: 17 April 2021
 * @throws
 */
@Data
public class Usage {
    private String email;

    private String userName;

    private String houseType;

    private String address;

    private String roomNumber;

    private String contractPeriod;

    private String companyName;

    private String planName;

    private String planId;

    private String tariffType;

    private float estimatedPrice;
}
