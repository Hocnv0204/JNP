import RMI.*;
import TCP.Customer;
import UDP.Employee;

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
        String studentCode = "B22DCCN508";
        String qCode = "rdI8oPSl";
        int port = 2206 ;
        String message = studentCode + ";" + qCode ;
        Socket socket = new Socket(server , port) ;
        byte[] request = new byte[4096] ;
        InputStream inputStream = socket.getInputStream() ;
        OutputStream outputStream = socket.getOutputStream() ;
        outputStream.write(message.getBytes()) ;
        outputStream.flush();
        int len = inputStream.read(request) ;
        String str = new String(request , 0 , len) ;
        System.out.println(str);
        String result = resolve(Integer.parseInt(str)) ;
        outputStream.write(result.getBytes()) ;
        outputStream.flush();
        socket.close();

    }

    private static String resolve(int number){
        List<Integer> list = new ArrayList<>() ;
        list.add(number) ;
        while(number > 1 ){
            if(number % 2 == 0 ){
                list.add(number / 2);
                number /= 2 ;
            }else{
                list.add(number * 3 + 1) ;
                number = number * 3 + 1 ;
            }
        }
        return String.join(" " ,
                list.stream().map(String :: valueOf).toList()
        ) + "; " + list.size() ;
    }
}