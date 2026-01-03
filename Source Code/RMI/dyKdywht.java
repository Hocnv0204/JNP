import RMI.ByteService;
import RMI.CharacterService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.* ;
public class Main {
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN352" ;
        String qCode = "dyKdywht" ;
        String server = "203.162.10.109" ;
        int port = 1099 ;
        Registry registry = LocateRegistry.getRegistry(server, port) ;
        CharacterService characterService = (CharacterService) registry.lookup("RMICharacterService") ;
        String response = characterService.requestCharacter(studentCode , qCode) ;
        System.out.println(response);
        Map<Character , Integer> result = new LinkedHashMap<>() ;
        for(char c : response.toCharArray()){
            result.put(c , result.getOrDefault(c , 0) +  1) ;
        }
        List<String> list = new ArrayList<>() ;
        for(char c : result.keySet()){
            String temp = "\"" + c + "\"" + ": " + result.get(c) ;
            list.add(temp) ;
        }
        String data = "{" + String.join(", " , list) + "}" ;
        characterService.submitCharacter(studentCode , qCode , data) ;
    }

}