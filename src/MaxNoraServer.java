import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

// Server class

class MaxNoraServer
{
    int port;
    public MaxNoraServer()
    {
        port=0;
    }

    public static void main(String[] args) {
        MaxNoraServer mns = new MaxNoraServer();
        mns.port = Integer.valueOf(args[0]);
        if (args.length <= 0) {
            System.out.println("Usage: MaxNoraServer port");
        } else {
            ServerSocket server = null;
            try {
                server = new ServerSocket(Integer.valueOf(args[0]));
                server.setReuseAddress(true);
//main thread need to hand second connection to it like forst
                while (true) {
                    Socket client = server.accept();
                    System.out.println("New client connected" + client.getInetAddress().getHostAddress());

                    try {
                        InputStreamReader in = new InputStreamReader(client.getInputStream());
                        int line=-1;
                        while ((line = in.read()) != -1) {
                            //recv data loop for match no match make new
                            //send matched and port to both
                            //connectup second close connection
                            //recv ok back
                            //if(line.contains("OK"))
                            //{
                            //MaxNora clientSock = new MaxNora(client, //get send port, client2); //add client2
                            //new Thread(clientSock).start();
                        //}
                            //else
                            //{
                            //MaxNora clientSock = new MaxNora(client, port++);
                            System.out.println(line);
                        }
                    }catch(Exception e)
                    {

                    }
                    //read data write matches if it matches and port number then connect to port number and drop connection
                    //pass second client to class if it maches
                    //if it doesnt match do this new class
                    //recieve datat gender etc.
                    //match data
                    MaxNora clientSock = new MaxNora(client, mns.port++);
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