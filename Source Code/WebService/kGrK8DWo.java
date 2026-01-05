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
        String studentCode = "B22DCCN244" ;
        String qCode = "kGrK8DWo" ;
        CharacterService_Service service = new CharacterService_Service() ;
        CharacterService port = service.getCharacterServicePort() ;
        String request = port.requestString(studentCode, qCode) ;
        String[] arr = request.toLowerCase().split("[\\s_]+") ;
        System.out.println(request);
        List<String> result = new ArrayList<>() ;
        result.add(pascalCase(arr)) ;
        result.add(camelCase(arr)) ;
        result.add(snakeCase(arr)) ;
        port.submitCharacterStringArray(studentCode, qCode, result);
    }

    private static String pascalCase(String[] input){
        StringBuilder result = new StringBuilder() ;
        for(String str : input){
            StringBuilder sb = new StringBuilder(str) ;
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            result.append(sb) ;
        }
        return result.toString() ;
    }
    private static  String camelCase(String[] input){
        StringBuilder result = new StringBuilder() ;
        result.append(input[0]) ;
        for(int i = 1  ; i < input.length ; i ++){
            StringBuilder sb = new StringBuilder(input[i]) ;
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            result.append(sb) ;
        }
        return result.toString() ;
    }
    private static String snakeCase(String[] input){
        return String.join("_", input) ;
    }
}
