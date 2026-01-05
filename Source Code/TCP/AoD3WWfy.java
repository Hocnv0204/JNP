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
        String qCode = "AoD3WWfy";
        int port = 2208 ;
        Socket socket = new Socket(server , port) ;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())) ;
        String message = studentCode + ";" + qCode ;
        bw.write(message) ;
        bw.newLine();
        bw.flush();
        String request = br.readLine() ;
        System.out.println(request);
        Set<Character> set = new LinkedHashSet<>() ;
        for(char c : request.toCharArray()){
            if(Character.isLetter(c)){
                set.add(c) ;
            }
        }
        String result = String.join( "",set.stream().map(String :: valueOf).toList()) ;
        bw.write(result) ;
        bw.newLine();
        bw.flush();
        socket.close();
    }
    private static int countAppearance(char c , String str){
        int count = 0 ;
        for(char temp : str.toCharArray() ){
            if(c == temp){
                count ++ ;
            }
        }
        return count ;
    }
}