package team.ienergy.energydash.beans;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Hao Cao
 * @date: 1 October 2020
 * @throws
 */

@Data
public class HistoricalData {

    private String userId;

    private Date datetime;

    private String datetimeStr;

    private String userConsumption;


    public void setDatetime(Date datetime) {

        this.datetime = datetime;
        if (datetime != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            this.datetimeStr = sdf.format(datetime);
        }
    }

}
