import java.io.*;
import java.net.Socket;
import java.util.*;

public class xbm9ptm9{
    public static final String server = "203.162.10.109" ;
    public static final int port = 2208 ;
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN352" ;
        String qCode = "xbm9ptm9" ;
        Socket socket = new Socket(server , port) ;
        socket.setSoTimeout(5000);
        String loginMessage = studentCode + ";" + qCode ;
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())) ;
        writer.write(loginMessage) ;
        writer.newLine() ;
        writer.flush();
        String response = reader.readLine() ;
        Map<Character , Integer> result = new LinkedHashMap<>() ;
        String[] arr = response.split("\\s+") ;
        for(String s : arr){
            for(char c : s.toCharArray()){
                if(Character.isLetterOrDigit(c)){
                    result.put(c , result.getOrDefault(c, 0 ) + 1 )  ;
                }
            }
        }
        List<String> res = new ArrayList<>() ;
        for(Character c : result.keySet()){
            if(result.get(c) > 1){
               res.add(c + ":" + result.get(c)) ;
            }
        }
        String temp = String.join("," , res) ;
        writer.write(temp + ",") ;
        writer.newLine() ;
        writer.flush() ;

    }
}