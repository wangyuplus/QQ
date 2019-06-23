package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * ���������ڼ���¼��
 * ����¼������Ҫ�Լ�����¼�������
 */
public class SendDemo {
	public static void main(String[] args) throws IOException {

		DatagramSocket ds = new DatagramSocket();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;

		while ((line = br.readLine()) != null) {
			if ("886".equals(line)) {
				break;
			}

			byte[] bys = line.getBytes();
			// DatagramPacket dp = new DatagramPacket(bys, bys.length,
			// InetAddress.getByName("192.168.12.92"), 12345);
			DatagramPacket dp = new DatagramPacket(bys, bys.length,
					InetAddress.getByName("127.0.0.1"), 12306);

			ds.send(dp);
			break;
		}

		while (true) {
			byte[] bys2 = new byte[1024];
			DatagramPacket dp2 = new DatagramPacket(bys2, bys2.length);


			ds.receive(dp2);

			// ��������
			String ip = dp2.getAddress().getHostAddress();
			String s = new String(dp2.getData(), 0, dp2.getLength());
			System.out.println("from " + ip + " data is : " + s);

			// �ͷ���Դ
			//ds.close();
		}
	}
}
