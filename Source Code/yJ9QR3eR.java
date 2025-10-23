import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import TCP.Product ;

public class yJ9QR3eR{
    public static final String server = "203.162.10.109" ;
    public static final int port = 2209;
    public static final int time_out = 5000 ;
    public static void main(String[] args){
        Socket socket = null ;
        ObjectOutputStream oos = null ;
        ObjectInputStream ois = null ;
        try{
            String studentCode = "B22DCCN352" ;
            String qCode = "yJ9QR3eR" ;
            String loginMessage = studentCode + ";" + qCode ;
            socket = new Socket(server , port) ;
            socket.setSoTimeout(time_out);
            oos = new ObjectOutputStream(socket.getOutputStream()) ;
            oos.flush();
            ois = new ObjectInputStream(socket.getInputStream()) ;
            oos.writeObject(loginMessage);
            oos.flush();
            Product product = (Product) ois.readObject() ;
            System.out.println(product);
            System.out.println(product.getPrice());
            String[] str = Double.toString(product.getPrice()).split("\\.") ;
            String str_left = str[0] ;
            int discount = 0  ;
            for(int i = 0 ; i < str_left.length() ; i ++){
                discount += (str_left.charAt(i) - '0') ;
            }
            product.setDiscount(discount);
            oos.writeObject(product) ;
            oos.flush();
            closeResource(socket , oos , ois );
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void closeResource(Socket socket , ObjectOutputStream ous , ObjectInputStream ois){
        if(socket != null && !socket.isClosed()){
            try{
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(ois != null){
            try{
                ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(ous != null){
            try{
                ous.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
