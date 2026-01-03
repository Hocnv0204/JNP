import TCP.Customer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) throws Exception{
        String server = "203.162.10.109" ;
        String studentCode = "B22DCCN352" ;
        String qCode = "9EEXZnqX" ;
        int port = 2209 ;
        Socket socket = new Socket(server , port) ;
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream()) ;
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream()) ;
        // send message
        String message = studentCode + ";" + qCode ;
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
        // read Object
        Customer customer = (Customer) objectInputStream.readObject() ;
        customer.setUserName(getUserName(customer.getName()));
        customer.setName(rename(customer.getName()));
        customer.setDayOfBirth(changeDayOfBirth(customer.getDayOfBirth()));
        System.out.println(customer);
        objectOutputStream.writeObject(customer);
        objectOutputStream.flush();
    }
    public static String rename(String input){
        String[] arr = input.toLowerCase().trim().split("\\s+") ;
        StringBuilder fullName = new StringBuilder( );
        fullName.append(arr[arr.length - 1].toUpperCase()) ;
        fullName.append(", ") ;
        List<String> firstName = new ArrayList<>() ;
        for(int i = 0 ; i < arr.length - 1 ; i ++){
            StringBuilder sb = new StringBuilder(arr[i]) ;
            sb.setCharAt(0 , Character.toUpperCase(sb.charAt(0)));
            firstName.add(sb.toString()) ;
        }
        fullName.append(String.join(" " , firstName)) ;
        return fullName.toString().trim() ;
    }
    public static String getUserName(String input){
        String[] arr = input.toLowerCase().trim().split("\\s+") ;
        StringBuilder userName = new StringBuilder() ;
        for(int i = 0 ; i < arr.length - 1 ; i ++){
            userName.append(arr[i].charAt(0)) ;
        }
        userName.append(arr[arr.length - 1]) ;
        return userName.toString().trim() ;
    }
    public static String changeDayOfBirth(String input){
        String[] arr = input.split("-") ;
        StringBuilder dayOfBirth = new StringBuilder() ;
        dayOfBirth.append(arr[1]).append("/").append(arr[0]).append("/").append(arr[2]) ;
        return dayOfBirth.toString() ;
    }
}