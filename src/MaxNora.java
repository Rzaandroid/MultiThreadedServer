import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MaxNora implements Runnable
{
    private final Socket client1;
    int port;
    ServerSocket svrsock;
    Socket client2;
    public MaxNora(Socket socket, int newportnum)
    {
        this.client1 = socket;
        this.client2 = null;
        this.port = newportnum+1;
        ServerSocket svrsock = null;
    }

    public void setmsg(String msg)
    {

    }
    public void run()
    {
        System.out.println("running...");
        PrintWriter client1out = null;
        BufferedReader client1in = null;
        PrintWriter client2out = null;
        BufferedReader client2in = null;
        try
        {
            try {
                svrsock = new ServerSocket(Integer.valueOf(port));
                System.out.println("Now connect to port "+port);
                svrsock.setReuseAddress(true);

                while (true) {
                    Socket client2 = svrsock.accept();
                    System.out.println("New client connected" + client2.getInetAddress().getHostAddress());
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            //scrap that maybe use server socket instead in this class listens on port put port in array arraylist + []
            //or one class connects bothup after recieving details spawn new thread two server sockets when next person connects that matches connect them up

            //thread getsocket return socket set out thread1.get out
            //call where it matches from array of classes threads get set
            //set both matches
            //pass .getinputstream
            client1out = new PrintWriter(client1.getOutputStream(), true);
            client1in = new BufferedReader(new InputStreamReader(client1.getInputStream()));

            client2out = new PrintWriter(client2.getOutputStream(), true);
            client2in = new BufferedReader(new InputStreamReader(client2.getInputStream()));

            String line;
            String line2="";
            while ((((line = client1in.readLine()) != null)||(line2 = client2in.readLine()) != null))
            {
                //if contains chat, match, switchmode control eacothers or one way

                //if matched
                System.out.printf(" Sent from the client: %s\n", line);
                client2out.write(line);

                System.out.printf(" Sent from the client: %s\n", line2);
                client1out.write(line2);

                //while read in write same out it recieve matched keyword
                //also send keword lovense at startup set out and in to bytes convert to int or ints

                //out.println(line);
                //make out. the other threads sock.out to send data in.
                //Thread1.setmsg
                //Thread2.setmsg
                //match m and f and thread number from data array
                //send sock.in to threadnum sendmsg()
                //sendmsg() send to sock.out
                //recv from second send to first recieve from first send to second

                //make 2 server sockets on port number save port number in data array
                //when match send match:port match:port to out then serversocket on port num and connect or switch outs
                //increment port number

                //make new class reads in writes out on both with methods use to switch outs
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (client1out != null)
                {
                    client1out.close();
                }
                if (client1in != null)
                {
                    client1in.close();
                    client1.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}