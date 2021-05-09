package team.ienergy.energydash.beans;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Mingchao Sima
 * @date: 09 May 2021
 * @throws
 */

@Data
public class PredictionData {
    private String userId;

    private String year;

    private double janConsumption;

    private double febConsumption;

    private double marConsumption;

    private double aprConsumption;

    private double mayConsumption;

    private double junConsumption;

    private double julConsumption;

    private double augConsumption;

    private double septConsumption;

    private double octConsumption;

    private double novConsumption;

    private double decConsumption;
}
