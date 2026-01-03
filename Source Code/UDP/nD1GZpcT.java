import TCP.Laptop;
import UDP.Product;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

public class nD1GZpcT{
    public static final String server = "203.162.10.109" ;
    public static final int port = 2209;
    public static void main(String[] args) throws Exception {
        InetAddress inetAddress = InetAddress.getByName(server) ;
        DatagramSocket socket = new DatagramSocket();
        String studentCode = "B22DCCN352" ;
        String qCode = "nD1GZpcT";
        String message = ";" + studentCode + ";" + qCode ;
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes()  , message.getBytes().length , inetAddress , port) ;
        socket.send(sendPacket);

        byte[] receivedData = new byte[4096] ;
        DatagramPacket receivedPacket = new DatagramPacket(receivedData , receivedData.length) ;
        socket.receive(receivedPacket);

        byte[] requestId = new byte[8] ;
        System.arraycopy(receivedPacket.getData() , 0 , requestId , 0 , 8 );
        byte[] object = new byte[receivedPacket.getLength() - 8 ] ;
        System.arraycopy(receivedPacket.getData(), 8 , object , 0 , object.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(object) ;
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream) ;
        Product product = (Product) objectInputStream.readObject() ;
        StringBuilder sb = new StringBuilder(String.valueOf(product.getQuantity())) ;
        product.setQuantity(Integer.parseInt(sb.reverse().toString()));

        StringBuilder newName = new StringBuilder() ;
        String[] words = product.getName().split("\\s+") ;
        newName.append(words[words.length - 1]).append(" ") ;
        for(int i = 1 ; i < words.length - 1 ; i ++){
            newName.append(words[i]).append(" ") ;
        }
        newName.append(words[0]) ;
        product.setName(newName.toString()) ;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream() ;
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream) ;
        objectOutputStream.writeObject(product);
        objectOutputStream.flush();

        byte[] result = new byte[4096] ;
        System.arraycopy(requestId , 0 , result , 0 , 8);
        System.arraycopy(byteArrayOutputStream.toByteArray() , 0 , result , 8 , byteArrayOutputStream.toByteArray().length);
        DatagramPacket resultPacket = new DatagramPacket(result , result.length , inetAddress , port) ;
        socket.send(resultPacket) ;
    }

}

