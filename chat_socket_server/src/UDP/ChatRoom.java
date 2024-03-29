package UDP;

import java.io.IOException;
import java.net.DatagramSocket;

/*
 * 通过多线程改进刚才的聊天程序，这样我就可以实现在一个窗口发送和接收数据了
 */
public class ChatRoom {
	public static void main(String[] args) throws IOException {
		DatagramSocket dsSend = new DatagramSocket();
		DatagramSocket dsSend2 = new DatagramSocket();
		DatagramSocket dsReceive = new DatagramSocket(12306);

		SendThread st = new SendThread(dsSend);
		SendThread st2 = new SendThread(dsSend2);
		ReceiveThread rt = new ReceiveThread(dsReceive);

		Thread t1 = new Thread(st);
		Thread t2 = new Thread(rt);
		Thread t3 = new Thread(st2);

		t1.start();
		t2.start();
		t3.start();
	}
}
