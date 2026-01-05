import RMI.*;


import java.io.*;
import java.math.BigInteger;
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
        String studentCode = "B22DCCN017";
        String qCode = "Klo2iIcg";
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress inetAddress = InetAddress.getByName(server) ;
        int port = 2208 ;
        String message = ";" + studentCode + ";" + qCode ;
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes() , 0 , message.getBytes().length , inetAddress , port) ;
        socket.send(sendPacket);
        byte[] receivedByte = new byte[4096] ;
        DatagramPacket receivedPacket = new DatagramPacket(receivedByte , 0 , 4096) ;
        socket.receive(receivedPacket);
        String str = new String(receivedPacket.getData() , 0 , receivedPacket.getLength()) ;
        System.out.println(str);
        String[] arr = str.split(";") ;
        String requestId = arr[0] ;
        String[] listBinary = arr[1].split(",") ;
        BigInteger bigInteger1 = new BigInteger(listBinary[0] , 2) ;
        BigInteger bigInteger2 = new BigInteger(listBinary[1] , 2) ;
        BigInteger sum = bigInteger1.add(bigInteger2) ;
        String result = requestId + ";" + sum.toString(10) ;
        DatagramPacket resultPacket = new DatagramPacket(result.getBytes() , 0 , result.getBytes().length , inetAddress , port) ;
        socket.send(resultPacket);
        socket.close();
    }




}