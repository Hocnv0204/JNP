import TCP.Laptop;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

public class TWgIzhqO{
    public static final String server = "203.162.10.109" ;
    public static final int port = 2208;
    public static void main(String[] args) throws Exception {
        InetAddress inetAddress = InetAddress.getByName(server) ;
        DatagramSocket socket = new DatagramSocket();
        String studentCode = "B22DCCN352" ;
        String qCode = "TWgIzhqO";
        String message = ";" + studentCode + ";" + qCode ;
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes()  , message.getBytes().length , inetAddress , port) ;
        socket.send(sendPacket);

        byte[] receivedData = new byte[4096] ;
        DatagramPacket receivedPacket = new DatagramPacket(receivedData , receivedData.length) ;
        socket.receive(receivedPacket);

        String response = new String(receivedPacket.getData() ,0 , receivedPacket.getLength(), "UTF-8" ) ;
        System.out.println(response);

        String[] arr = response.split(";") ;
        String requestId = arr[0] ;
        String[] words = arr[1].toLowerCase().split("\\s+") ;
        List<String> result = new ArrayList<>() ;
        for(String word : words){
            StringBuilder sb = new StringBuilder(word) ;
            sb.setCharAt(0 , Character.toUpperCase(sb.charAt(0)));
            result.add(sb.toString()) ;
        }
        String resultString = String.join(" ", result) ;
        String sendResult = requestId + ";" + resultString ;
        DatagramPacket resultPacket = new DatagramPacket(sendResult.getBytes( ) , sendResult.getBytes().length , inetAddress , port) ;
        socket.send(resultPacket);
    }

}

