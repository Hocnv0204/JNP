import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TCP{
    private static  final int port = 2208 ;
    private static final String server = "203.162.10.109" ;
    private static final int timeout = 5000 ;
    public static void main(String[] args){
        String studentCode = "B22DCCN352" ;
        String qCode = "4KkQanR4" ;
        Socket socket = null ;
        BufferedWriter bw = null ;
        BufferedReader br = null ;
        try{
            socket = new Socket(server , port) ;
            socket.setSoTimeout(timeout);
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())) ;
            br = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;
            // a
            String loginMessage = studentCode + ";" + qCode ;
            bw.write(loginMessage) ;
            bw.newLine() ;
            bw.flush() ;

            //b
            String response = br.readLine() ;

            //c
            String result = solve(response) ;
            System.out.println(result);
            bw.write(result) ;
            bw.newLine() ;
            bw.flush() ;

        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (SocketException e){
            throw new RuntimeException(e) ;
        }catch (IOException e){
            throw new RuntimeException(e) ;
        }finally {
            closeResource(socket , br , bw );
        }
    }
    public static String solve(String response){
        String[] arr = response.split(" ") ;
        List<String> list = Arrays.stream(arr).map(String::trim).sorted((a,b) -> a.length() - b.length()).toList() ;
        return String.join(", " , list) ;
    }
    public static void closeResource(Socket socket , BufferedReader br , BufferedWriter bw){
        try{
            if(br != null ){
                br.close() ;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            if(bw != null ){
                bw.close() ;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            if(socket != null && !socket.isClosed()){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}