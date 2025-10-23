import TCP.Laptop;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class HWiL3lM7{
    public static final String server = "203.162.10.109" ;
    public static final int port = 2209 ;
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN352" ;
        String qCode = "HWiL3lM7" ;
        Socket socket = new Socket(server , port) ;
        socket.setSoTimeout(5000);
        String loginMessage = studentCode + ";" + qCode ;
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream()) ;
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream()) ;
        objectOutputStream.writeObject(loginMessage);
        objectOutputStream.flush();
        Laptop laptop = (Laptop) objectInputStream.readObject() ;
        String name = laptop.getName() ;
        String[] arr = name.split("\\s+") ;
        StringBuilder sb = new StringBuilder() ;
        sb.append(arr[arr.length - 1]).append(" ") ;
        for(int i = 1 ; i < arr.length - 1 ; i ++){
            sb.append(arr[i]).append(" ") ;
        }
        sb.append(arr[0]) ;
        laptop.setName(sb.toString());
        StringBuilder number = new StringBuilder(String.valueOf(laptop.getQuantity()) );
        laptop.setQuantity(Integer.parseInt(number.reverse().toString()));
        objectOutputStream.writeObject(laptop);
        objectOutputStream.flush();
        System.out.println(laptop);
    }

}

