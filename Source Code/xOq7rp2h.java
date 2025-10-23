import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.* ;
import java.util.stream.Collectors;

public class xOq7rp2h {
    private static final String server = "203.162.10.109" ;
    private static final int port = 2207 ;
    private static final int timeout = 5000 ;
    public static void main(String[] args) {
        Socket socket = null ;
        DataInputStream dis = null ;
        DataOutputStream dos = null ;
        String studentCode = "B22DCCN352" ;
        String qCode = "xOq7rp2h" ;
        try{
            socket = new Socket(server , port) ;
            socket.setSoTimeout(timeout);
            dos = new DataOutputStream(socket.getOutputStream()) ;
            dis = new DataInputStream(socket.getInputStream()) ;

            String loginMessage = studentCode + ";" + qCode ;
            dos.writeUTF(loginMessage);
            dos.flush();

            int k = dis.readInt() ;
            String response = dis.readUTF() ;
            System.out.println(response);
            List<Integer> listNumbers = Arrays.stream(response.trim().split(","))
                    .map(Integer :: parseInt)
                    .collect(Collectors.toList()) ;
            String result = solve(listNumbers , k) ;
            dos.writeUTF(result);
            dos.flush();
            System.out.println(result);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            closeResource(socket , dos , dis);
        }

    }
    public static String solve(List<Integer> listNumbers , int k ){
        List<Integer> result = new ArrayList<>() ;
        int len = listNumbers.size() ;
        for(int i = 0 ; i < len ; i+= k ){
            int endIndex = Math.min(i + k , len) ;
            List<Integer> segment = new ArrayList<>(listNumbers.subList(i , endIndex)) ;
            Collections.reverse(segment);
            result.addAll(segment) ;
        }
        return result.stream()
                .map(String :: valueOf)
                .collect(Collectors.joining(","));
    }
    public static void closeResource(Socket socket , DataOutputStream dos , DataInputStream dis  ){
        try{
            if(socket != null && !socket.isClosed()){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            if(dos != null){
                dos.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            if(dis != null){
                dis.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
