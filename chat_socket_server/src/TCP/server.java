package TCP;

import com.server.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server {
    private ServerSocket ss;
    private  int port=8888;
    private ExecutorService executorService; // 连接池
    private final int POOL_SIZE = 5;
    public Map<InetAddress, Integer> map=new HashMap<>();
    List<Socket> list =new LinkedList<>();
    public server() throws Exception{
        ss=new ServerSocket(port);


    }

    public  void  service() throws IOException {
        Socket s=null;
        //executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);// 初始化线程池

        while (true){

            s=ss.accept();
            System.out.println("等待连接");
            list.add(s);
            //executorService.execute(new Handler(s));
            new Thread(new Handler(s)).start();
            System.out.println("ssss");
            //send(list);

        }
    }
    /*public void send(List list) throws IOException {
          Iterator<Socket> iterator =list.iterator();
        Socket aa= (Socket) list.get(list.size()-1);
         while (iterator.hasNext()) {
             Socket a = iterator.next();
             //System.out.println(a);

             PrintWriter writer1 = new PrintWriter(a.getOutputStream());

             writer1.println(aa.getPort() + "上线");
             writer1.flush();
         }

    }*/
    class Handler implements Runnable{
        private Socket socket = null;

        public Handler(Socket socket) {

            this.socket = socket;
        }


        @Override
        public void run() {

            Iterator<Socket> iterator =list.iterator();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                Socket aa= (Socket) list.get(list.size()-1);
                while (iterator.hasNext()){
                    Socket a=iterator.next();
                    PrintWriter writer1= null;
                    try {
                        writer1 = new PrintWriter(a.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    writer1.println(aa.getPort()+"上线");
                    writer1.flush();
                    System.out.println(br.readLine());

                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }
     }


    public static void main(String[] args) throws Exception {

       new server().service();

    }
}
