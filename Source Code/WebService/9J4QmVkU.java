/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package webservice;
import vn.medianews.* ;
import java.util.* ;
public class WebService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String studentCode = "B22DCCN352" ;
        String qCode = "9J4QmVkU" ;
        CharacterService_Service service = new CharacterService_Service() ;
        CharacterService port = service.getCharacterServicePort() ;
        List<String> request = port.requestStringArray(studentCode, qCode) ;
        for(String str : request){
            System.out.println(str);
        }
        Map<Integer , List<String>> map = new TreeMap<>() ;
        for(String str : request){
            int count = countVowels(str) ;
            if(!map.containsKey(count)){
                List<String> value = new ArrayList<>() ;
                value.add(str) ;
                map.put(count, value) ;
            }else{
                List<String> value = map.get(count) ;
                value.add(str) ;
            }
        }
        List<String> result = new ArrayList<>() ;
        for(Integer key : map.keySet()){
            List<String> value = map.get(key) ;
            Collections.sort(value , (a,b) -> a.compareTo(b));
            String str = String.join(", ", value).trim() ;
            result.add(str) ;
        }

        port.submitCharacterStringArray(studentCode, qCode, result);
    }

    private static int countVowels(String input){
        String constStr = "ueoai" ;
        int count = 0 ;
        for(char c : input.toLowerCase().toCharArray()){
            if(constStr.contains(String.valueOf(c))){
                count ++ ;
            }
        }
        return count ;
    }
}
