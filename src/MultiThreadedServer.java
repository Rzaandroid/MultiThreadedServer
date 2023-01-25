import java.io.*;
import java.net.*;

// Server class
class MultiThreadedServer
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
                    ClientHandler clientSock = new ClientHandler(client);
                    //array of clienthandles if thread1 prints then you know its thread1
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