package team.ienergy.energydash.beans;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Hao Cao
 * @date: 27 September 2020
 * @throws
 */

@Data
public class RealtimeData {

    private String userId;

    private Date datetime;

    private String wholesaleSale;

    private String carbonIntensity;

    private String coalProfits;

    private String renewableProfits;

    private String userConsumption;


//    public void setDatetime(Date datetime) {
//
//        this.datetime = datetime;
//        if (datetime != null) {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//            this.datetime = sdf.format(datetime);
//        }
//    }

}
