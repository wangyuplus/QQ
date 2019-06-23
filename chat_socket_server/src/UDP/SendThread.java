package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendThread implements Runnable {

	private DatagramSocket ds;

	public SendThread(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		try {
			System.out.println("请输入昵称：");
               	// 封装键盘录入数据
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String line = null;
			while ((line = br.readLine()) != null) {
				byte[] bys = line.getBytes();
				// DatagramPacket dp = new DatagramPacket(bys, bys.length,
				// InetAddress.getByName("192.168.12.92"), 12345);
				DatagramPacket dp = new DatagramPacket(bys, bys.length,
						InetAddress.getByName("127.0.0.1"), 12306);

				// 发送数据
				ds.send(dp);
                  break;
			}

			BufferedReader br1 = new BufferedReader(new InputStreamReader(
					System.in));
			String line1 = null;

				while ((line1 = br1.readLine()) != null) {
					if ("886".equals(line)) {
						break;
					}

					// 创建数据并打包
					byte[] bys1 = line1.getBytes();
					// DatagramPacket dp = new DatagramPacket(bys, bys.length,
					// InetAddress.getByName("192.168.12.92"), 12345);
					DatagramPacket dp1 = new DatagramPacket(bys1, bys1.length,
							InetAddress.getByName("127.0.0.1"), 12306);

					// 发送数据
					ds.send(dp1);
				}


			// 释放资源
			ds.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
