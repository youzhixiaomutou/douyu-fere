package me.linxiaowei.douyu.fere;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import static me.linxiaowei.douyu.fere.sdk.util.ConverseUtil.bytesToIntLittle;
import static me.linxiaowei.douyu.fere.sdk.util.ConverseUtil.intToBytesLittle;

public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("openbarrage.douyutv.com", 8601);

        //发送登录请求(登入9999房间)
        String loginCMD = "type@=loginreq/roomid@=210868/";
        send(loginCMD, socket);

        //读取登录请求消息
        byte[] bytes = read(socket);
        String msg = new String(Arrays.copyOfRange(bytes, 0, bytes.length));
        System.out.println(msg);


        //加入弹幕分组开始接收弹幕
        String joinGroupCMD = "type@=joingroup/rid@=210868/gid@=-9999/";
        send(joinGroupCMD, socket);

        //循环读取弹幕消息开始
        while (true) {
            byte[] msgBytes = read(socket);
            String s = new String(Arrays.copyOfRange(msgBytes, 0, msgBytes.length));
            System.out.println(s);
            Thread.sleep(1);
        }
        //关闭链接
        //socket.close();
    }

    /**
     * 发送消息
     *
     * @param content
     */
    public static void send(String content, Socket socket) {
        try {
            //计算消息长度 = 消息长度(4) + 消息类型(4) + 真实消息内容长度 + 结尾标识长度(1)
            int contenLeng = 4 + 4 + content.length() + 1;
            //小端模式转换init (长度1)
            byte[] contenLeng1 = intToBytesLittle(contenLeng);
            //小端模式转换init (长度2)
            byte[] contenLeng2 = intToBytesLittle(contenLeng);
            //小端模式转换init (消息类型) (689:客户端发送给弹幕服务器的文本格式数据)
            byte[] msgType = intToBytesLittle(689);
            //标识数据结尾
            int end = 0;

            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            //写入长度1
            byteArray.write(contenLeng1);
            //写入长度2（与长度1相同）
            byteArray.write(contenLeng2);
            //写入消息类型
            byteArray.write(msgType);
            //写入消息内容
            byteArray.write(content.getBytes("ISO-8859-1"));
            //写入数据结尾标识
            byteArray.write(end);

            //发送数据
            OutputStream out = socket.getOutputStream();
            out.write(byteArray.toByteArray());
            out.flush();
        } catch (IOException e) {
        }
    }

    /**
     * 读取消息
     *
     * @return
     */
    public static byte[] read(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            //下条信息的长度
            int contentLen = 0;

            //读取前4个字节，得到数据长度
            byte[] bytes1 = readStream(inputStream, 0, 4);
            contentLen = bytesToIntLittle(bytes1, 0); //用小端模式转换byte数组为
            //System.out.println("数据长度1:" + contentLen);

            //继续读取4个字节，得到第二个 数据长度
            byte[] bytes2 = readStream(inputStream, 0, 4);
            int contentLen2 = bytesToIntLittle(bytes2, 0);
            //System.out.println("数据长度2:" + contentLen2);

            //再次读取4个字节，得到消息类型
            byte[] bytes3 = readStream(inputStream, 0, 4);
            //将小端整数转换为大端整数
            int msgType = bytesToIntLittle(bytes3, 0);
            //System.out.println("消息类型:" + msgType);

            contentLen = contentLen - 8;
            //继续读取真正的消息内容
            int len = 0;        //本次读取数据长度
            int readLen = 0;    //已读数据长度
            byte[] bytes = new byte[contentLen];
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            while ((len = inputStream.read(bytes, 0, contentLen - readLen)) != -1) {
                byteArray.write(bytes, 0, len);
                readLen += len;
                if (readLen == contentLen) {
                    break;
                }
            }
            return byteArray.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * 从流中读取数据
     *
     * @param inputStream
     * @param off
     * @param len
     * @return
     * @throws IOException
     */
    public static byte[] readStream(InputStream inputStream, int off, int len) throws IOException {
        byte[] bytes = new byte[len];
        inputStream.read(bytes, 0, 4);
        return bytes;
    }

}
