import java.io.* ;
import java.net.* ;
import java.util.* ;
public class Main{
    private static final String server = "203.162.10.109" ;
    private static final int port = 2208 ;
    private static final int timeout = 5000 ;
    public static void main(String[] args){
        Socket socket = null ;
        BufferedReader br = null ;
        BufferedWriter bw = null ;
        try{
            String studentCode = "B22DCCN352" ;
            String qCode = "OBZTSzEk" ;
            socket = new Socket(server , port) ;
            socket.setSoTimeout(timeout);

            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())) ;
            br = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;

            // a
            String loginMessage = studentCode + ";" + qCode ;
            bw.write(loginMessage) ;
            bw.newLine() ;
            bw.flush() ;

            //b
            String response = br.readLine() ;
            System.out.println(response);
            //c
            String result = solve(response);
            System.out.println(result);
            bw.write(result) ;
            bw.newLine() ;
            bw.flush() ;

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeResource(socket , br , bw);
        }
    }
    public static String solve(String response){
        String[] arr = response.split(" ") ;
        StringBuilder answer = new StringBuilder() ;
        for(String s : arr){
            String reverse_s = reverse(s).trim();
            answer.append(encode(reverse_s)).append(" ") ;
        }
        return answer.toString().trim() ;
    }
    public static String reverse(String input){
        StringBuilder result = new StringBuilder(input) ;
        return result.reverse().toString() ;
    }
    public static String encode(String input){
        StringBuilder result = new StringBuilder() ;
        int count = 1 ;
        for(int i = 1 ; i <= input.length() ; i ++){
            if(i < input.length() && input.charAt(i -1 ) == input.charAt(i)){
                count ++ ;
            }else{
                result.append(input.charAt(i -1 )) ;
                if(count > 1){
                    result.append(count) ;
                }
                count = 1 ;
            }
        }
        return result.toString() ;
    }
    public static void closeResource(Socket socket , BufferedReader br , BufferedWriter bw){
        try{
            if(br != null ){
                br.close() ;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            if(bw != null ){
                bw.close() ;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            if(socket != null && !socket.isClosed()){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}