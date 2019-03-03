package me.linxiaowei.douyu.fere;

import me.linxiaowei.douyu.fere.sdk.domain.Message;
import me.linxiaowei.douyu.fere.sdk.domain.data.client.JoinGroupData;
import me.linxiaowei.douyu.fere.sdk.domain.data.client.LoginReqData;
import me.linxiaowei.douyu.fere.sdk.domain.data.client.MrklData;
import me.linxiaowei.douyu.fere.sdk.util.ConstantUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static me.linxiaowei.douyu.fere.sdk.util.ConverseUtil.bytesToIntLittle;

@SpringBootApplication
public class DouyuFereApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DouyuFereApplication.class, args);

        // 测试
        Socket socket = new Socket(ConstantUtil.SERVER_IP(), ConstantUtil.SERVER_PORT());
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        // 登录请求测试
        Message message = new Message((short) 689, new LoginReqData(210868));
        byte[] bytes = message.getBytes();
        out.write(message.getBytes());
        out.flush();
        // 新版心跳测试
        out.write(new Message((short) 689, new MrklData()).getBytes());
        out.flush();
        // 入组消息测试
        out.write(new Message((short) 689, new JoinGroupData(210869, -9999)).getBytes());
        out.flush();
        InputStream inputStream = socket.getInputStream();
        //下条信息的长度
        int contentLen = 0;

        //读取前4个字节，得到数据长度
        byte[] bytes1 = readStream(inputStream, 0, 4);
        contentLen = bytesToIntLittle(bytes1, 0); //用小端模式转换byte数组为
        System.out.println("数据长度1:" + contentLen);
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
