import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HTML5out implements Runnable
{
    private final Socket clientSocket;
    public HTML5out(Socket socket)
    {
        this.clientSocket = socket;
    }
    public void run()
    {
        PrintWriter out = null;
        BufferedReader in = null;
        try
        {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //if recieves html5 it wants this out. if it recieves bytes it wants the other out.write

            String line;
            while ((line = in.readLine()) != null)
            {
                System.out.printf(" Sent from the client: %s\n", line);
                out.println(line);

                //generics then make a class that handels in. out. and does something with it
                //send in and out to a class with methods in out (string)
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
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}