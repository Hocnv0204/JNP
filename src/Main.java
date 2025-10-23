import UDP.Book;
import UDP.Student;

import javax.xml.crypto.Data;
import java.io.* ;
import java.net.* ;
import java.util.* ;
public class Main{
    public static String server = "203.162.10.109" ;
    public static int port = 2209 ;
    public static void main(String[] args) {
        DatagramSocket socket = null  ;
        try{
            InetAddress inetAddress = InetAddress.getByName(server) ;
            socket = new DatagramSocket() ;
            String studentCode = "B22DCCN352" ;
            String qCode = "Js6d7sOK" ;
            String message = ";" + studentCode + ";" + qCode ;
            DatagramPacket sendPacket = new DatagramPacket(message.getBytes() , message.getBytes().length , inetAddress , port) ;
            socket.send(sendPacket) ;

            byte[] response = new byte[4096] ;
            DatagramPacket responsePacket = new DatagramPacket(response , response.length) ;
            socket.receive(responsePacket);
            byte[] requestId = new byte[8] ;
            System.arraycopy(responsePacket.getData() , 0 , requestId , 0 , 8);
            byte[] object = new byte[responsePacket.getLength() - 8 ] ;
            System.arraycopy(responsePacket.getData() , 8 , object , 0 , object.length);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(object) ;
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream) ;
            Book book = (Book) objectInputStream.readObject() ;
            System.out.println(book);
            book.setAuthor(author(book.getAuthor()));
            book.setPublishDate(publishDate(book.getPublishDate()));
            book.setIsbn(isbn(book.getIsbn()));
            book.setTitle(title(book.getTitle()));
            byte[] result = new byte[4096] ;
            System.arraycopy(requestId , 0 , result , 0 , 8);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream() ;
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream) ;
            objectOutputStream.writeObject(book);
            objectOutputStream.flush();
            System.arraycopy(byteArrayOutputStream.toByteArray() , 0 , result ,8 , byteArrayOutputStream.toByteArray().length);
            DatagramPacket resultPacket = new DatagramPacket(result , result.length , inetAddress , port);
            socket.send(resultPacket);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public static String upperCase(String input){
        StringBuilder sb = new StringBuilder(input.toLowerCase()) ;
        sb.setCharAt(0 , Character.toUpperCase(sb.charAt(0)));
        return sb.toString() ;
    }

    public static String title(String title){
        String[] words = title.split("\\s+") ;
        StringBuilder sb = new StringBuilder() ;
        for(String str : words){
            sb.append(upperCase(str)).append(" ") ;
        }
        return sb.toString().trim() ;
    }

    public static String author(String author){
        String[] words = author.trim().toLowerCase().split("\\s+") ;
        StringBuilder sb = new StringBuilder(words[0].toUpperCase()).append(", ") ;
        for(int i = 1 ; i < words.length  ; i ++){
            sb.append(upperCase(words[i])) ;
            if( i < words.length - 1){
                sb.append(" ") ;
            }
        }
        return sb.toString().trim() ;
    }
    public static String isbn(String isbn){
        StringBuilder sb = new StringBuilder() ;
        sb.append(isbn.substring(0 , 3)).append("-").append(isbn.charAt(3)).append("-").append(isbn.substring(4,6)).append("-").append(isbn.substring(6,12)).append("-").append(isbn.charAt(12)) ;
        return sb.toString() ;
    }

    public static String publishDate(String publishDate){
        String[] date = publishDate.split("-") ;
        StringBuilder sb = new StringBuilder() ;
        sb.append(date[1]).append("/").append(date[0]) ;
        return sb.toString() ;
    }
}