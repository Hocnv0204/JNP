import RMI.*;


import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.* ;
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args) throws Exception {
        String server = "203.162.10.109";
        String studentCode = "B22DCCN352";
        String qCode = "NZnRvFH7";
        int port = 2207 ;
        DatagramSocket socket = new DatagramSocket() ;
        String message = ";" + studentCode + ";" + qCode ;
        InetAddress inetAddress = InetAddress.getByName(server) ;
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes() , 0 , message.getBytes().length , inetAddress , port) ;
        socket.send(sendPacket);
        byte[] receivedByte = new byte[4096] ;
        DatagramPacket receivedPacket = new DatagramPacket(receivedByte , 0 , receivedByte.length) ;
        socket.receive(receivedPacket) ;
        String request = new String(receivedPacket.getData() , 0 , receivedPacket.getLength()) ;
        System.out.println(request);
        String[] arr = request.split(";") ;
        String requestId = arr[0] ;
        int n = Integer.parseInt(arr[1]) ;
        int k = Integer.parseInt(arr[2]) ;
        int[] numbers = Arrays.stream(arr[3].split(",")).mapToInt(Integer :: parseInt).toArray() ;
        List<Integer> list = new ArrayList<>() ;
        for(int i = 0 ; i < n - k + 1 ; i ++){
            int maxValue = Integer.MIN_VALUE ;
            for(int j = i ; j < i + k  ;j ++){
                if(numbers[j] > maxValue){
                    maxValue = numbers[j] ;
                }
            }
            list.add(maxValue) ;
        }
        String result = requestId + ";" + String.join("," ,
                list.stream().map(String :: valueOf).toList()
        ) ;
        DatagramPacket resultPacket = new DatagramPacket(result.getBytes() , 0 , result.getBytes().length , inetAddress , port) ;
        socket.send(resultPacket) ;
        socket.close();
    }


}