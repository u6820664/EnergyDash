package team.ienergy.energydash.exception;

/**
 * @author Hao Cao
 * @desc exception
 * @team infinite energy
 * @date 1 September 2020
 **/
public class NormalException extends RuntimeException {


    private String errorCode;
    public static String ERROR_CODE_SYSTEM="99";
    //001  empty parameter
    public static String ERROR_CODE_NO_PARA="001";
    //002  illegal string in parameters
    public static String ERROR_CODE_ILLEGALSTR="002";
    //003  wrong format of parameters
    public static String ERROR_CODE_ERR_FORMAT="003";
    //004  illegal length of parameters
    public static String ERROR_CODE_ERR_LENGTH="004";
    //005  no result
    public static String ERROR_CODE_NO_RESULT="005";

    public NormalException(String msg) {
        super(msg);
    }

    public  NormalException(String errorCode,String msg){
        super(msg);
        this.errorCode = errorCode;
    }

    public NormalException(String msg, Exception e) {
        super(msg, e);
    }

    public String getErrorCode() {
        return errorCode;
    }
}

