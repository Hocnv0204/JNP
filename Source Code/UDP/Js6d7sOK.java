import UDP.Book;
import UDP.Student;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Js6d7sOK {
    private static final String server = "203.162.10.109" ;
    private static final int port = 2209 ;
    public static void main(String[] args) {
        DatagramSocket socket = null ;
        String studentCode = "B22DCCN352" ;
        String qCode = "Js6d7sOK" ;
        try{
            socket = new DatagramSocket() ;
            InetAddress inetAddress = InetAddress.getByName(server) ;
            String message = ";" + studentCode + ";" + qCode ;
            DatagramPacket loginPacket = new DatagramPacket(message.getBytes() , message.length() , inetAddress , port) ;
            socket.send(loginPacket) ;

            byte[] response = new byte[4096] ;
            DatagramPacket responsePacket = new DatagramPacket(response , response.length) ;
            socket.receive(responsePacket);
            byte[] requestId = new byte[8] ;
            System.arraycopy(response, 0 , requestId , 0 , 8);
            byte[] object = new byte[responsePacket.getLength() - 8] ;
            System.arraycopy(response , 8 , object , 0 , object.length) ;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(object) ;
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream) ;
            Book book = (Book) objectInputStream.readObject() ;
            System.out.println(book);
            book.setAuthor(author(book.getAuthor()));
            book.setIsbn(ISBN(book.getIsbn()));
            book.setTitle(title(book.getTitle()));
            book.setPublishDate(publishDate(book.getPublishDate()));
            byte[] result = new byte[4096] ;
            System.arraycopy(requestId , 0 , result , 0 , 8) ;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream() ;
            ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream) ;
            oos.writeObject(book);
            oos.flush();
            System.arraycopy(byteArrayOutputStream.toByteArray() , 0 , result , 8 , byteArrayOutputStream.toByteArray().length);
            DatagramPacket resultPacket = new DatagramPacket(result , result.length , inetAddress , port) ;
            socket.send(resultPacket);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static String title(String input){
        String[] arr = input.trim().toLowerCase().split("\\s+") ;
        StringBuilder sb = new StringBuilder() ;
        for(String str : arr){
            StringBuilder temp = new StringBuilder(str) ;
            temp.setCharAt(0 , Character.toUpperCase(temp.charAt(0))) ;
            sb.append(temp).append(" ") ;
        }
        return sb.toString().trim() ;
    }
    public static String upperCase(String input){
        StringBuilder sb = new StringBuilder(input.toLowerCase()) ;
        sb.setCharAt(0 , Character.toUpperCase(sb.charAt(0)));
        return sb.toString() ;
    }
    public static String author(String input){
        StringBuilder sb = new StringBuilder() ;
        String[] arr = input.split("\\s+") ;
        sb.append(arr[0].toUpperCase()).append(", ") ;
        for(int i = 1 ; i < arr.length  ; i ++){
            sb.append(upperCase(arr[i])) ;
            if(i < arr.length - 1 ){
                sb.append(" ") ;
            }
        }

        return sb.toString() ;
    }
    public static String publishDate(String input){
        String[] arr = input.split("-") ;
        StringBuilder sb = new StringBuilder() ;
        sb.append(arr[1]).append("/").append(arr[0]) ;
        return sb.toString() ;
    }

    public static String ISBN(String input){
        StringBuilder sb = new StringBuilder() ;
        for(int i = 0 ; i < 3 ; i ++){
            sb.append(input.charAt(i)) ;
        }
        sb.append("-").append(input.charAt(3)).append("-") ;
        for(int i = 4 ; i < 6 ; i ++){
            sb.append(input.charAt(i)) ;
        }
        sb.append("-") ;
        for(int i = 6 ; i < 12 ; i ++){
            sb.append(input.charAt(i)) ;
        }
        sb.append("-").append(input.charAt(12)) ;
        return sb.toString() ;
    }
}