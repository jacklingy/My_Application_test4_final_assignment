package edu.ncu.myapplication_test4_final_assignment;
//843e3bbf38f70139cf6cdae36c7e2135

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder builder;



    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private RecyclerView recyclerView;
    private List<Message> messageList = new ArrayList<>();
    private MessageAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Button btn_send;
    private EditText editText;
    private ImageView avtar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // showDialog();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMessage();
        //Toast.makeText(getApplicationContext(),getReply("test"),Toast.LENGTH_LONG).show();


        /*************初始化******************/
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //将消息队列加载到屏幕上
        adapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(adapter);


        btn_send = (Button) findViewById(R.id.btn_send);
        editText = (EditText) findViewById(R.id.edit);

        //非空判断

        btn_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String present_message = editText.getText().toString();
                // Log.d("1:",present_message);
                if (present_message.length() != 0) {
                    Message presentMessage = new Message(present_message, R.drawable.avatar);
                    //  Toast.makeText(getApplicationContext(), presentMessage.getContent(), Toast.LENGTH_SHORT).show();
                    sendMessage(presentMessage);
                    getReply(present_message);
                    //sendMessage(presentMessage);
                    //清空内容
                    editText.setText("");
                } else {
                    //  Toast.makeText(getApplicationContext(), "空！", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }






    public void initMessage() {

        Message m1 = new Message("Hello", R.drawable.avatar);
        messageList.add(m1);
        Message m2 = new Message("你好", R.drawable.avatar);
        messageList.add(m2);

    }
    public void sendMessage(Message message) {

        //发送方头像是确定的
        adapter.addData(messageList.size(), message);
        //跳转到最最后一条消息
        adapter.moveToPresent(recyclerView);

    }
    public void receiveMessage(Message message){

        //收消息，改变元素的属性


        adapter.addData(messageList.size(), message);
        adapter.moveToPresent(recyclerView);


    }
    //参数为json字符串，返回MessageSend对象
    public  MessageSend jsonToBean(String jsonStr) {

        MessageSend messageSend=JSON.parseObject(jsonStr,new TypeReference<MessageSend>(){});
         return messageSend;
    }

    //参数为发出去的消息
    //发送并接收消息
    //英文不需要encode！
    public void getReply(String message) {
//        avtar2=(ImageView)findViewById(R.id.avatar2);
//        avtar2.setVisibility(View.VISIBLE);

        String sendUrl_1= getResources().getString(R.string.address);
        String sendUrl_2 = message;
        String key=getResources().getString(R.string.key);
        String encoded_2 = null;
        String encoded=null;
        try {
            encoded_2 = URLEncoder.encode(sendUrl_2, "utf-8");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "网址转换出错！\n" + e.toString()
                    , Toast.LENGTH_LONG).show();
        }                        //转换的消息
        encoded=sendUrl_1+"?info="+encoded_2+"&key="+key;
       // Log.d("66666666666:",encoded);

        //发送请求并获取传回的结果
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, encoded,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        MessageSend messageSend = jsonToBean(response);
                        Message message_recv = new Message(messageSend.getResult().getText(), R.drawable.bing);
                        sendMessage(message_recv);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //网络连接失败
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    //创建右上角菜单
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreatePanelMenu(featureId, menu);
    }

    //右上角菜单的触发事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                Intent intent = new Intent(this, Activity_about.class);
                startActivity(intent);
                break;
                //记住要break，不然会执行后面case的代码！！
            //break;
            case R.id.quit:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /******监听发送过来的消息，并立即显示**********/
    private void showDialog() {

        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("温馨提示")
                .setMessage("人工智能回复次数有限，目前还剩80多次，所以请问一些实际的问题^^").setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                        //  Toast.makeText(, "确定按钮", Toast.LENGTH_LONG).show();
                        dialogInterface.dismiss();
                        Toast.makeText(getApplicationContext(),"感谢理解！",Toast.LENGTH_LONG).show();

                    }
                }).setNegativeButton("我不听！", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                        //  Toast.makeText(MainActivity.this, "关闭按钮", Toast.LENGTH_LONG).show();
                        finish();

                    }
                });
        builder.create().show();
    }

}
