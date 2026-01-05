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
        String studentCode = "B22DCCN244";
        String qCode = "ukTWv730";
        int port = 2208 ;
        Socket socket = new Socket(server , port) ;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())) ;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = studentCode + ";" + qCode ;
        bw.write(message) ;
        bw.newLine();
        bw.flush();
        String request = br.readLine() ;
        System.out.println(request);
        Map<Character , Integer> resultMap = new LinkedHashMap<>() ;
        for(char c : request.toCharArray()){
            resultMap.put(c , resultMap.getOrDefault(c, 0) + 1) ;
        }
        List<String> result = new ArrayList<>() ;
        for(char c : resultMap.keySet()){
            if(resultMap.get(c) > 1 && Character.isLetterOrDigit(c)) {
                StringBuilder sb = new StringBuilder();
                sb.append(c).append(":").append(resultMap.get(c));
                result.add(sb.toString());
            }
        }
        bw.write(String.join("," , result) + ",") ;
        bw.newLine();
        bw.flush();
    }

}