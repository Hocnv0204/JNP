import TCP.Customer;
import UDP.Employee;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) throws Exception{
        String server = "203.162.10.109" ;
        String studentCode = "B22DCCN352" ;
        String qCode = "2hPBcEt4" ;
        int port = 2209 ;
        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();
        String message = ";" + studentCode + ";" + qCode ;
        DatagramPacket packet = new DatagramPacket(message.getBytes() , 0 , message.getBytes().length , inetAddress , port) ;
        socket.send(packet);
        byte[] receiveBytes = new byte[4096] ;
        DatagramPacket receivedPacket = new DatagramPacket(receiveBytes , receiveBytes.length) ;
        socket.receive(receivedPacket);
        byte[] requestId = new byte[8] ;
        System.arraycopy(receivedPacket.getData() , 0 , requestId , 0 , 8);
        byte[] object = new byte[receivedPacket.getLength() - 8] ;
        System.arraycopy(receivedPacket.getData() ,8 , object , 0 , object.length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream() ;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(object) ;
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream) ;
        Employee employee = (Employee) objectInputStream.readObject() ;
        System.out.println(employee);
        employee.setName(rename(employee.getName()));
        employee.setHireDate(changeDayOfBirth(employee.getHireDate()));
        employee.setSalary(upSalary(employee));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream) ;
        objectOutputStream.writeObject(employee);
        objectOutputStream.flush();
        byte[] result = new byte[4096] ;
        System.arraycopy(requestId , 0 , result , 0 , 8);
        System.arraycopy(byteArrayOutputStream.toByteArray() , 0 , result , 8 , byteArrayOutputStream.toByteArray().length);
        DatagramPacket resultPacket = new DatagramPacket(result , 0 , result.length , inetAddress , port) ;
        socket.send(resultPacket) ;
        socket.close() ;
        System.out.println(employee);
    }
    public static String rename(String input){
        String[] arr = input.trim().toLowerCase().split("\\s+") ;
        List<String> fullName = new ArrayList<>() ;
        for(String str : arr){
            StringBuilder sb = new StringBuilder(str) ;
            sb.setCharAt(0 , Character.toUpperCase(sb.charAt(0)));
            fullName.add(sb.toString()) ;
        }
        return String.join(" " , fullName) ;
    }
    public static String changeDayOfBirth(String input){
        String[] arr = input.trim().split("-") ;
        StringBuilder dob = new StringBuilder() ;
        dob.append(arr[2]).append("/").append(arr[1]).append("/").append(arr[0]) ;
        return dob.toString() ;
    }
    public static double upSalary(Employee employee){
        String dob = employee.getHireDate() ;
        String[] arr = dob.split("/") ;
        String year = arr[2] ;
        int x = 0 ;
        for(char c : year.toCharArray()){
            x += c -'0' ;
        }
        return (double) employee.getSalary() + employee.getSalary() * x / 100  ;
    }
}