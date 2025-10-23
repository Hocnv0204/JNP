import UDP.Product;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class v4a8q9pf {
    public static final String server = "203.162.10.109" ;
    public static final int port = 2208 ;
    public static void main(String[] args) {
        DatagramSocket socket = null ;
        String studentCode = "B22DCCN352" ;
        String qCode = "v4a8q9pf" ;
        try{
            socket = new DatagramSocket() ;
            InetAddress inetAddress = InetAddress.getByName(server) ;
            String loginMessage = ";" + studentCode +  ";" + qCode ;
            DatagramPacket datagramPacket = new DatagramPacket(loginMessage.getBytes() , loginMessage.length() ,inetAddress , port) ;
            socket.send(datagramPacket);

            byte[] response  = new byte[4096] ;
            DatagramPacket responsePacket = new DatagramPacket(response , response.length ) ;
            socket.receive(responsePacket);
            String received = new String(responsePacket.getData(),0 ,responsePacket.getLength()) ;
            String[] arr = received.split(";") ;
            String requestId = arr[0] ;
            String words = String.join(",",Arrays.stream(arr[1].split("\\s+")).sorted((a,b) -> b.toLowerCase().compareTo(a.toLowerCase())).collect(Collectors.toList())) ;
            String result = requestId + ";" + words ;
            DatagramPacket resultPacket = new DatagramPacket(result.getBytes() , result.getBytes().length , inetAddress , port) ;
            socket.send(resultPacket);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}