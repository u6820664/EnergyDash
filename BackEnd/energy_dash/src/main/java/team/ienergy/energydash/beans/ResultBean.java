package team.ienergy.energydash.beans;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author: Hao Cao
 * @date: 29 August 2020
 * @throws
 */

@Data
public class ResultBean {
    //error，0 stands for normal
    @JSONField(name = "error_no")
    private int errNo = 0;

    //error information
    @JSONField(name = "error_info")
    private String errInfo = "operation success！";

    private Object data;

    /**
     * @param data
     * @return void
     * @desc filter null
     * @author Hao Cao
     * @date 28 August 2020
     * @func_name setData
     */
    public void setData(Object data) {
        /*String jsonStr = JSON.toJSONString(data);
        Object object;
        if (data instanceof Collection) {
            object = JSONArray.parseArray(jsonStr);
        } else {
            object = JSONObject.parseObject(jsonStr);
        }*/
        this.data = data;
    }

}


