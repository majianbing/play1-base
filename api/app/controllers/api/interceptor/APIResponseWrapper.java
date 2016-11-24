package controllers.api.interceptor;

import controllers.api.RequestId;
import org.apache.commons.lang.StringUtils;
import play.mvc.Controller;
import play.mvc.Finally;

/**
 * 设置一些 api response 通用参数. 比如返回的 header 和 cors
 */
public class APIResponseWrapper extends Controller {

    @Finally
    static void headers() {
        //set default content type
        response.contentType = "application/json; charset=utf-8";
        //set cors
        response.accessControl("*");
        //request id
        if (StringUtils.isNotEmpty(session.getId())) {
            response.setHeader("X-Session-Id", session.getId());
        }
        if (StringUtils.isNotEmpty(RequestId.getId())) {
            response.setHeader("X-Request-Id", RequestId.getId());
        }
    }
}