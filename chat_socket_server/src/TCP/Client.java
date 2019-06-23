package TCP;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket s;
    private String host="127.0.0.1";
    private int port=8888;

    public Client() throws Exception {
        s = new Socket(host,port);

    }

    public void talk() throws IOException {//接收数据

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader lbr = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(s.getOutputStream(),true);
        String line = null;

        while ((line = lbr.readLine()) != null) {

            writer.println(line);
            System.out.println(line);


        }
        writer.close();
        br.close();
        s.close();
    }


    public static void main(String[] args) throws Exception {

 new Client().talk();
    }
}
