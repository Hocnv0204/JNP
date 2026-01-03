import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ssFdsttV{
    public static final String server = "203.162.10.109" ;
    public static final int port = 2207;
    public static final int time_out = 5000 ;
    public static void main(String[] args){
        Socket socket = null ;
        DataOutputStream dos = null ;
        DataInputStream dis = null ;
        try{
            String studentCode = "B22DCCN352" ;
            String qCode = "ssFdsttV" ;
            String loginMessage = studentCode + ";" + qCode ;
            socket = new Socket(server , port) ;
            socket.setSoTimeout(time_out);
            dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream())) ;
            dis = new DataInputStream(new BufferedInputStream(socket.getInputStream())) ;
            dos.writeUTF(loginMessage);
            dos.flush();
            int number = dis.readInt() ;
            String res_8 = solve_2(number) ;
            String res_16 = solve_1(number) ;;
            dos.writeUTF(res_8 + ";" + res_16);
            dos.flush();
            closeResource(socket , dos , dis );
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static String solve_2(int number){
        StringBuilder res_8 = new StringBuilder() ;
        while(number > 0 ){
            int remainder = number % 8 ;
            res_8.append(remainder) ;
            number /= 8 ;
        }
        return res_8.reverse().toString() ;
    }
    public static String solve_1(int number){
        StringBuilder res_16 = new StringBuilder() ;
        Map<Integer , Character> reference = new HashMap<>() ;
        int num = 10 ; char c = 'A' ;
        for (int i = 0 ; i < 6 ; i ++){
            reference.put(num ,c) ;
            num ++ ;
            c ++ ;
        }
        while(number > 0  ){
            int remainder = number % 16 ;
            if(remainder >= 10 ) {
                res_16.append(reference.get(remainder)) ;
            }
            else{
                res_16.append(remainder) ;
            }
            number /= 16 ;
        }
        return res_16.reverse().toString() ;
    }

    public static void closeResource(Socket socket , DataOutputStream dos , DataInputStream dis){
        if(socket != null && !socket.isClosed()){
            try{
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(dis != null){
            try{
                dis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(dos != null){
            try{
                dos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
