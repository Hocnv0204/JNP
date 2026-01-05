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
        String qCode = "0F0f4L3R";
        int port = 2206 ;
        Socket socket = new Socket(server , port) ;
        byte[] request = new byte[4096] ;
        InputStream is = socket.getInputStream() ;
        OutputStream os = socket.getOutputStream() ;
        String message = studentCode + ";" + qCode ;
        os.write(message.getBytes());
        os.flush();
        int len = is.read(request) ;
        String response = new String(request , 0 , len) ;
        int[] arr = Arrays.stream(response.split(",")).mapToInt(Integer :: parseInt).toArray() ;
        String result = findLIS(arr) ;
        os.write(result.getBytes());
        os.flush();
        socket.close();
    }
    private static String findLIS(int[] arr){
        int n = arr.length ;
        int[] dp = new int[n] ;
        int[] parent = new int[n] ;
        Arrays.fill(dp , 1);
        Arrays.fill(parent , - 1) ;
        int maxLen = 1 ;
        int lastIndex = 0 ;

        for(int i = 1 ; i < n ; i ++){
            for(int j = 0 ; j < i ; j ++){
                if(arr[j] < arr[i] && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    parent[i] = j ;
                }
            }
            if(dp[i] > maxLen){
                maxLen = dp[i] ;
                lastIndex = i ;
            }
        }
        List<Integer> list = new ArrayList<>() ;
        while(lastIndex != -1){
            list.add(arr[lastIndex]);
            lastIndex = parent[lastIndex] ;
        }
        Collections.reverse(list);
        List<String> result = list.stream().map(String :: valueOf).toList() ;
        return String.join("," , result) + ";" + maxLen ;
    }

}