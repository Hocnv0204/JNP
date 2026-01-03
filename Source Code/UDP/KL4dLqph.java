import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KL4dLqph {
    private static final String server = "203.162.10.109" ;
    private static final int port = 2208 ;

    public static void main(String[] args) {
        String studentCode = "B22DCCN352" ;
        String qCode = "KL4dLqph" ;
        DatagramSocket socket = null ;
        try{
            InetAddress inetAddress = InetAddress.getByName(server) ;
            socket = new DatagramSocket();

            String message = ";" + studentCode + ";" + qCode ;
            byte[] sendData = message.getBytes() ;
            DatagramPacket sendPacket = new DatagramPacket(sendData , sendData.length , inetAddress , port) ;
            socket.send(sendPacket) ;

            byte[] receivedData = new byte[4096] ;
            DatagramPacket receivedPacket = new DatagramPacket(receivedData , receivedData.length);
            socket.receive(receivedPacket);

            String response = new String(receivedPacket.getData() , 0 , receivedPacket.getLength()  , "UTF-8") ;
            System.out.println(response);
            String[] responseSplit = response.trim().split(";") ;
            String requestId = responseSplit[0] ;
            String str = responseSplit[1] ;
            String result = requestId + ";" + solve(str) ;
            DatagramPacket sendResult = new DatagramPacket(result.getBytes() , result.getBytes().length , inetAddress , port) ;
            socket.send(sendResult) ; 
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(socket != null && !socket.isClosed()){
                socket.close() ;
            }
        }
    }

    public static String solve(String input){
        String[] arr = input.split(" ") ;
        StringBuilder sb = new StringBuilder() ;
        for(String str : arr){
            StringBuilder temp = new StringBuilder(str.toLowerCase()) ;
            temp.setCharAt(0, Character.toUpperCase(temp.charAt(0)));
            sb.append(temp) ;
            sb.append(" ") ;
        }
        return sb.toString().trim() ;
    }
}