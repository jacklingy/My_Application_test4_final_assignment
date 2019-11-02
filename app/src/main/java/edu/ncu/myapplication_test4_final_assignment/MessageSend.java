/**
 * Copyright 2019 bejson.com
 */
package edu.ncu.myapplication_test4_final_assignment;

/**
 * Auto-generated: 2019-11-01 21:37:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */

/**
 * json源数据
 * {
 * 	"reason":"请求成功",
 * 	"result":{
 * 		"code":100000,
 * 		"text":"你见过这么机灵懂事乖巧的机器人吗？"
 *        },
 * 	"error_code":0
 * }
 */
public class MessageSend {

    private String reason;
    private Result result;
    private int error_code;
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }

    public void setResult(Result result) {
        this.result = result;
    }
    public Result getResult() {
        return result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
    public int getError_code() {
        return error_code;
    }

}