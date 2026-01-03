import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


import TCP.Address;

public class xhIUM0D9{
    public static final String server = "203.162.10.109" ;
    public static final int port = 2209;
    public static final int time_out = 5000 ;
    public static void main(String[] args){
        Socket socket = null ;
        ObjectOutputStream oos = null ;
        ObjectInputStream ois = null ;
        try{
            String studentCode = "B22DCCN352" ;
            String qCode = "xhIUM0D9" ;
            String loginMessage = studentCode + ";" + qCode ;
            socket = new Socket(server , port) ;
            socket.setSoTimeout(time_out);
            oos = new ObjectOutputStream(socket.getOutputStream()) ;
            oos.flush();
            ois = new ObjectInputStream(socket.getInputStream()) ;
            oos.writeObject(loginMessage);
            oos.flush();
            Address address = (Address) ois.readObject() ;
            System.out.println(address);
            String addressLine = solve_1(address.getAddressLine()) ;
            System.out.println(addressLine);
            address.setAddressLine(addressLine);
            String postalCode = editPostalCode(address.getPostalCode()) ;
            System.out.println(postalCode);
            address.setPostalCode(postalCode);
            oos.writeObject(address);
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
    public static String solve_1(String str){
        StringBuilder sb = new StringBuilder() ;
        String[] arr = str.toLowerCase().trim().split("\\s+") ;
        for(String temp : arr){
            sb.append(deleteCharacter(temp)).append(" ") ;
        }
        return sb.toString().trim() ;
    }
    public static String deleteCharacter(String input){
        StringBuilder result = new StringBuilder() ;
        for(char c : input.toCharArray()){
            if(Character.isLetterOrDigit(c)){
                result.append(c) ;
            }
        }
        if(!result.isEmpty() &&Character.isLetter(result.charAt(0))  ){
            result.setCharAt(0,Character.toUpperCase(result.charAt(0)) );
        }
        return result.toString() ;
    }
    public static String editPostalCode(String postalCode){
        StringBuilder sb = new StringBuilder() ;
        for(char c : postalCode.toCharArray()){
            if(Character.isDigit(c) || c == '-'){
                sb.append(c) ;
            }
        }
        return sb.toString().trim() ;
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
