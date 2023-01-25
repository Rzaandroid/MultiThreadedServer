import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Server class
class HTML5Server
{
    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("Usage: MultiThreadedServer port");
        } else {
            ServerSocket server = null;
            try {
                server = new ServerSocket(Integer.valueOf(args[0]));
                server.setReuseAddress(true);

                while (true) {
                    Socket client = server.accept();
                    System.out.println("New client connected" + client.getInetAddress().getHostAddress());
                    HTML5out clientSock = new HTML5out(client);
                    new Thread(clientSock).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (server != null) {
                    try {
                        server.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}