import UDP.Book;
import UDP.Employee;
import UDP.Student;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class M5PJCFca {
    private static final String server = "203.162.10.109" ;
    private static final int port = 2209 ;
    public static void main(String[] args) {
        DatagramSocket socket = null ;
        String studentCode = "B22DCCN352" ;
        String qCode = "M5PJCFca" ;
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
            Employee employee = (Employee) objectInputStream.readObject() ;
            System.out.println(employee);
            employee.setSalary(salary(employee.getHireDate() , employee.getSalary()));
            employee.setName(name(employee.getName()));
            employee.setHireDate(hireDate(employee.getHireDate()));
            byte[] result = new byte[4096] ;
            System.arraycopy(requestId , 0 , result , 0 , 8) ;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream() ;
            ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream) ;
            oos.writeObject(employee);
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
    public static String name(String input){
        String[] arr = input.trim().split("\\s+") ;
        StringBuilder sb = new StringBuilder() ;
        for(String str : arr){
            sb.append(upperCase(str)).append(" ") ;
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
    public static String hireDate(String input){
        String[] arr = input.split("-") ;
        StringBuilder sb = new StringBuilder() ;
        sb.append(arr[2]).append("/").append(arr[1]).append("/").append(arr[0]) ;
        return sb.toString() ;
    }

    public static double salary(String input , double salary){
        String[] arr = input.split("-") ;
        String year = arr[0] ;
        int x = cal(year) ;
        salary = salary + salary * x / 100 ; 
        System.out.println(x);
        System.out.println(salary);
        return salary;
    }
    public static int cal(String input){
        int sum = 0 ;
        for(int i = 0 ; i < input.length() ; i ++){
            sum = sum + input.charAt(i) - '0' ;
        }
        return sum ;
    }
}