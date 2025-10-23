import java.io.* ;
import java.net.* ;
import java.util.* ;
public class z1CpegfK {
    private static final String server = "203.162.10.109" ;
    private static final int port = 2207 ;
    private static final int timeout = 5000 ;
    public static void main(String[] args){
        Socket socket = null ;
        DataOutputStream dos  = null ;
        DataInputStream dis = null ;    
        try{
            String studentCode = "B22DCCN352" ;
            String qCode = "z1CpegfK" ;
            socket = new Socket(server , port) ;
            socket.setSoTimeout(timeout);

            dis = new DataInputStream(socket.getInputStream()) ;
            dos = new DataOutputStream(socket.getOutputStream()) ;

            // a
            String loginMessage = studentCode + ";" + qCode ;
            dos.writeUTF(loginMessage);
            dos.flush() ;

            //b
            int a = dis.readInt() ;
            System.out.println(a);
            int b = dis.readInt() ;
            System.out.println(b);
            // c
            int sum = a + b ;
            dos.writeInt(sum) ;
            System.out.println(sum);
            int product = a * b;
            dos.writeLong(product) ;
            dos.flush() ;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeResource(socket , dis , dos);
        }
    }

    public static void closeResource(Socket socket, DataInputStream dis , DataOutputStream dos){
        try{
            if(dos != null ){
                dos.close() ;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            if(dis != null ){
                dis.close() ;
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