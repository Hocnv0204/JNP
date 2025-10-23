import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress ;
import java.net.Socket;

public class JavaNetworkProgramming {
    Socket socket = null ;
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintWriter out = new PrintWriter(socket.getOutputStream() , true) ;
    public JavaNetworkProgramming() throws IOException {
    }
}