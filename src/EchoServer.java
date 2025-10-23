import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args){
        int port = 12345;
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Listening on port :" + port);

            // accept connection
            Socket clientSocket = serverSocket.accept() ;
            System.out.println("Client connected" + clientSocket.getInetAddress().getHostName());

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true) ;
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())) ;

            String inputLine ;

            while((inputLine = in.readLine())!= null ){
                System.out.println("Received: " + inputLine);
                out.print("Server echo:" + inputLine);
                if("exit".equalsIgnoreCase(inputLine)){
                    break ;
                }
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
