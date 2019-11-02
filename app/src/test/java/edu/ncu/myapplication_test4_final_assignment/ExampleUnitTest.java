package edu.ncu.myapplication_test4_final_assignment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import org.junit.Test;

import static org.junit.Assert.*;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void toUrl() throws Exception{

        System.out.println(URLEncoder.encode("南昌大学位于哪里？","utf-8"));
    }

   //
    @Test
   public  void jsonToJsonBean() {
       String jsonStr="{\n" +
               "\t\"reason\":\"请求成功\",\n" +
               "\t\"result\":{\n" +
               "\t\t\"code\":100000,\n" +
               "\t\t\"text\":\"你好，终于等到你。\"\n" +
               "\t},\n" +
               "\t\"error_code\":0\n" +
               "}";
       MessageSend messageSend= JSON.parseObject(jsonStr,new TypeReference<MessageSend>(){});
       System.out.println("reason"+messageSend.getReason());
       System.out.println("resultcode"+messageSend.getResult().getCode());
       System.out.println("resulttext"+messageSend.getResult().getText());
       System.out.println("errorcode"+messageSend.getError_code());





   }
}