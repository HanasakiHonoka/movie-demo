package com.xzx.util;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;

public class SocketUtil {

    private static String server = "10.103.244.129";

    public static StringBuilder getSocket(String sendStr){
        Socket socket = null;
        StringBuilder sb = null;
        try {
            //localhost
            server = InetAddress.getLocalHost().getHostAddress();

            //String ip=addr.getHostAddress().toString(); //获取本机ip
            //log.info("调用远程接口:host=>"+ip+",port=>"+12345);
            InetAddress addr = InetAddress.getByName(server);
            // 初始化套接字，设置访问服务的主机和进程端口号，HOST是访问python进程的主机名称，可以是IP地址或者域名，PORT是python进程绑定的端口号
            //socket = new Socket(host,12345);
            socket = new Socket(addr,12345);

            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            // 发送内容

            System.out.println("sendStr = " + sendStr);
            out.print(sendStr);
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");

            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String tmp = null;
            sb = new StringBuilder();
            // 读取内容
            while((tmp=br.readLine())!=null)
                sb.append(tmp).append('\n');
            //System.out.print(sb);
            // 解析结果
            //Map res = JSON.parseObject(sb.toString(), Map.class);
            //System.out.println("res = " + res);
            //JSONArray jsonArray = JSON.parseArray(sb.toString());
            //Iterator<Object> iterator = jsonArray.iterator();
            //while (iterator.hasNext()) {
            //    JSONObject jsonObject = (JSONObject) iterator.next();
            //    System.out.println("jsonObject.keySet() = " + jsonObject.keySet());
            //}
            //System.out.println(jsonArray.size());
        } catch (IOException e) {
            e.printStackTrace();
            sb = new StringBuilder();
        }finally {
            try {if(socket!=null) socket.close();} catch (IOException e) {}
            System.out.println("远程接口调用结束.");

        }
        return sb;
    }
}
