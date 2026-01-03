import RMI.CharacterService;
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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.* ;
public class Main{
    public static void main(String[] args) throws Exception{
        String server = "203.162.10.109" ;
        String studentCode = "B22DCCN352" ;
        String qCode = "Yzf9OutQ" ;
        int port = 1099 ;
        Registry registry = LocateRegistry.getRegistry(server , port) ;
        CharacterService characterService = (CharacterService) registry.lookup("RMICharacterService") ;
        String request = characterService.requestCharacter(studentCode , qCode) ;
        System.out.println(request);
        Map<Character , Integer> map = new LinkedHashMap<>() ;
        for(char c : request.toCharArray()){
            map.put(c , map.getOrDefault(c ,0) + 1) ;
        }
        StringBuilder result = new StringBuilder() ;
        for(char c : map.keySet()){
            result.append(c).append(map.get(c)) ;
        }
        characterService.submitCharacter(studentCode , qCode , result.toString());
    }

}