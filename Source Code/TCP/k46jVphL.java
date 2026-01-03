import java.io.*;
import java.net.Socket;
import java.util.*;

public class k46jVphL{
    public static final String server = "203.162.10.109" ;
    public static final int port = 2207 ;
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN352" ;
        String qCode = "k46jVphL" ;
        Socket socket = new Socket(server , port) ;
        socket.setSoTimeout(5000);
        String loginMessage = studentCode + ";" + qCode ;
        DataInputStream dis = new DataInputStream(socket.getInputStream()) ;
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream()) ;
        dos.writeUTF(loginMessage);
        dos.flush();
        int number = dis.readInt() ;
        String result = binary(number) + ";" + hexa(number) ;
        dos.writeUTF(result);
        dos.flush();

    }
    public static String binary(int number){
        StringBuilder sb = new StringBuilder() ;
        while(number > 0){
            int remainder = number % 2 ;
            number /= 2 ;
            sb.append(remainder) ;
        }
        return sb.reverse().toString() ;
    }

    public static String hexa(int number){
        StringBuilder sb = new StringBuilder() ;
        Map<Integer, Character> refer = new TreeMap<>() ;
        char c = 'A' ;
        for(int i = 10 ; i < 16 ; i ++){
            refer.put(i, c) ;
            c ++ ;
        }
        while(number > 0 ){
            int remainder = number % 16 ;
            if( remainder > 10 ){
                sb.append(refer.get(remainder)) ;
            }else{
                sb.append(remainder) ;
            }
            number /= 16 ;
        }
        return sb.reverse().toString() ;
    }
}

