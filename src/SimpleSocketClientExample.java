import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleSocketClientExample
{
    int i;
    int first;
    int second;
    int third;
    int fourth;

    int a;
    int b;
    int c;
    int d;

    int z;
    int y;
    int x;
    int w;
    AtomicBoolean running;
  public SimpleSocketClientExample()
  {
      running = new AtomicBoolean(false);
    i=0;
    first = 0;
    second = 0;
    third = 0;
    fourth = 0;

    a=0;
    b=0;
    c=0;
    d=0;

    z = 255;
    y = 255;
    x = 255;
    w = 255;
  }

public static void main( String[] args )
    {
      SimpleSocketClientExample main = new SimpleSocketClientExample();
        //if( args.length < 2 )
        //{
        //    System.out.println( "Usage: SimpleSocketClientExample <server> <path>" );
        //    System.exit( 0 );
        //}
        //String server = args[ 0 ];
        //String path = args[ 1 ];
        //System.out.println( "Loading contents of URL: " + server );
    	System.out.println(Thread.currentThread().getName());
        main.connect("https://openjdk.org/groups/net/httpclient/recipes.html");
        //main.myloop();

    }

    public void myloop()
    {
        for(a=first;a<w;a++)
        {
        	for(b=second;b<x;b++)
        	{
        		for(c=third;c<y;c++)
        		{
        			for(d=fourth;d<z;d++)
        			{
                      new Thread("" + (i++)){
                        public void run(){
                            running.set(true);
                            while (running.get()) {
                                try {
                                    System.out.println("Thread: " + getName() + " running");
                                    System.out.println(Thread.activeCount());
                                    String path = "/index.html";
                                    System.out.println(a + "." + b + "." + c + "." + d);
                                    //System.out.println(path);
                                    System.out.println(a+b+c+d);
                                    connect(a + "." + b + "." + c + "." + d);
                                    running.set(false);
                                    Thread.currentThread().interrupt();
                                    Thread.currentThread().stop();
                                    System.gc();
                                    //276 threads running
                                } catch (Exception e) {

                                }
                            }
                        }
                      }.start();
                    }
                }
            }
       }
    }

    public void connect(String server) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(server)).build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body().toString());
        } catch (Exception e) {
            System.out.println(LocalTime.now());
            System.out.println(e);
        }
    }

    /*
    public void connect(String server, String path)
    {
    	try
        {
            // Connect to the server
            Socket socket = new Socket( server, 80);
	    socket.setSoTimeout(2*1000);
            // Create input and output streams to read from and write to the server
            PrintStream out = new PrintStream( socket.getOutputStream() );
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            // Follow the HTTP protocol of GET <path> HTTP/1.0 followed by an empty line
            out.println( "GET " + path + " HTTP/1.0" );
            out.println();
            out.flush();

            // Read data from the server until we finish reading the document
            String line = in.readLine();
            while( line != null )
            {
                System.out.println( line );
                line = in.readLine();
            }

            // Close our streams
            in.close();
            out.close();
            socket.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
     */
}
