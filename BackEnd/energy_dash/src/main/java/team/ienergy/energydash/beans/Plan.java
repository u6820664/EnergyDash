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
}
