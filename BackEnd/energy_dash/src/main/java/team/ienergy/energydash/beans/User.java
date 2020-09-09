package team.ienergy.energydash.beans;

import lombok.Data;

/**
 * @author: Hao Cao
 * @date: 26 August 2020
 * @throws
 */
@Data
public class User {

    private int userId;

    private String userName;

    private String email;

    private String password;

    private String postcode;

    private String planId;

    private Object image;

}