package team.ienergy.energydash.beans;

import lombok.Data;

import java.sql.Blob;

/**
 * @author: Mingchao Sima
 * @date: 09 April 2021
 * @throws
 */

@Data
public class CompanyImage {

    private String companyName;

    private byte[] image;
}
