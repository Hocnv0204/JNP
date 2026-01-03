import TCP.Customer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) throws Exception{
        String server = "203.162.10.109" ;
        String studentCode = "B22DCCN352" ;
        String qCode = "tlQJQ3rk" ;
        int port = 2208 ;
        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();
        String message = ";" + studentCode + ";" + qCode ;
        DatagramPacket packet = new DatagramPacket(message.getBytes() , 0 , message.getBytes().length , inetAddress , port) ;
        socket.send(packet);
        byte[] receiveBytes = new byte[4096] ;
        DatagramPacket receivedPacket = new DatagramPacket(receiveBytes , receiveBytes.length) ;
        socket.receive(receivedPacket);
        String result = new String(receivedPacket.getData() , 0 , receivedPacket.getLength()) ;
        System.out.println(result);
        String[] arr = result.trim().split(";") ;
        String requestId = arr[0] ;
        String str1 = arr[1] ;
        String str2 = arr[2] ;
        StringBuilder strOutput = new StringBuilder() ;
        for(char c : str1.toCharArray()){
            if(!str2.contains(String.valueOf(c))){
                strOutput.append(c) ;
            }
        }
        String messageResult = requestId + ";" + strOutput ;
        DatagramPacket resultPacket = new DatagramPacket(messageResult.getBytes() , 0 , messageResult.getBytes().length , inetAddress , port) ;
        socket.send(resultPacket);
        socket.close();
    }
    public static String rename(String input){
        String[] arr = input.toLowerCase().trim().split("\\s+") ;
        StringBuilder fullName = new StringBuilder( );
        fullName.append(arr[arr.length - 1].toUpperCase()) ;
        fullName.append(", ") ;
        List<String> firstName = new ArrayList<>() ;
        for(int i = 0 ; i < arr.length - 1 ; i ++){
            StringBuilder sb = new StringBuilder(arr[i]) ;
            sb.setCharAt(0 , Character.toUpperCase(sb.charAt(0)));
            firstName.add(sb.toString()) ;
        }
        fullName.append(String.join(" " , firstName)) ;
        return fullName.toString().trim() ;
    }
    public static String getUserName(String input){
        String[] arr = input.toLowerCase().trim().split("\\s+") ;
        StringBuilder userName = new StringBuilder() ;
        for(int i = 0 ; i < arr.length - 1 ; i ++){
            userName.append(arr[i].charAt(0)) ;
        }
        userName.append(arr[arr.length - 1]) ;
        return userName.toString().trim() ;
    }
    public static String changeDayOfBirth(String input){
        String[] arr = input.split("-") ;
        StringBuilder dayOfBirth = new StringBuilder() ;
        dayOfBirth.append(arr[1]).append("/").append(arr[0]).append("/").append(arr[2]) ;
        return dayOfBirth.toString() ;
    }
}