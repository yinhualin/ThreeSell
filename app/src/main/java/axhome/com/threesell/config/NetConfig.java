package axhome.com.threesell.config;

/**
 * Created by axehome on 2017/8/14.
 */

public class NetConfig {
    //基地址
    public final static String BASE_URL="http://157.10.1.108:8080/xingShen/";
    //登录
    public final static String LOGIN_URL=BASE_URL+"user/login.action";
    //注册
    public final static String SIGN_URL=BASE_URL+"user/add.action";
    //注册短信验证码
    public final static String SIGN_SENDCODE_URL=BASE_URL+"user/short_message1.action";
}
