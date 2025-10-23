import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class XRuXlLth {
    private static final String server = "203.162.10.109" ;
    private static final int port = 2207 ;

    public static void main(String[] args) {
        String studentCode = "B22DCCN352" ;
        String qCode = "XRuXlLth" ;
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
            String[] response_arr = response.trim().split(";") ;
            String requestId = response_arr[0] ;
            List<Integer> numbers = Arrays.stream(response_arr[1].split(",")).map(Integer :: parseInt).sorted().collect(Collectors.toList()) ;
            int maxNumber = numbers.get(numbers.size() - 1) ;
            int minNumber = numbers.get(0) ;
            String result = requestId + ";" + maxNumber + "," + minNumber ;
            byte[] bytes = result.getBytes( ) ;
            DatagramPacket sendResult = new DatagramPacket(bytes , bytes.length , inetAddress , port) ;
            socket.send(sendResult);
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
}