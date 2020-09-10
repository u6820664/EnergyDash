package team.ienergy.energydash.exception;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class MyExceptionHandler {
    @ExceptionHandler(NormalException.class)
    public Map<String,String> handleException(NormalException e){
        Map<String,String> map = new HashMap<>();
        map.put("errorCode",e.getErrorCode());
        map.put("message", e.getMessage());
        return map;
    }
}