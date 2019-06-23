package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReceiveThread implements Runnable {
	private DatagramSocket ds;
	Map<String,Integer>  map =new HashMap<>();

	public ReceiveThread(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		try {
			while (true) {
				// 创建一个包裹
				byte[] bys = new byte[1024];
				DatagramPacket dp = new DatagramPacket(bys, bys.length);
				// 接收数据
				ds.receive(dp);

				// 解析数据
				String ip = dp.getAddress().getHostAddress();

				int port=dp.getPort();

				//String name=dp.getAddress().getHostName();


				String s = new String(dp.getData(), 0, dp.getLength());
				map.put(ip,port);
				Integer port1=map.get(ip);
				Set ip1=map.keySet();
				System.out.println("map中的ip"+ ip1 );
				System.out.println("map中的端口"+port1 );
				System.out.println(map);
				byte [] aaa=s.getBytes();
				DatagramPacket dp2 = new DatagramPacket(aaa, aaa.length,
						InetAddress.getByName("127.0.0.1"), port1);
				// 发送数据
				ds.send(dp2);
				System.out.println("from " + ip +"端口"+ port + " data is : " + s);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(map);
	}

}
