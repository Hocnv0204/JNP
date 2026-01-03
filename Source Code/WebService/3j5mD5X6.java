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
        String qCode = "3j5mD5X6" ;
        DataService_Service service = new DataService_Service() ;
        DataService port = service.getDataServicePort() ;
        List<Integer> list = port.getData(studentCode, qCode) ;
        List<String> result = new ArrayList<>() ;
        for(Integer number : list){
            StringBuilder sb = new StringBuilder() ;
            sb.append(octal(number)).append("|").append(hexalDecimal(number)) ;
            result.add(sb.toString()) ;
        }
        port.submitDataStringArray(studentCode, qCode, result);
        System.out.println(list.size());
    }
    private static String octal(Integer input){
        StringBuilder sb = new StringBuilder() ;
        while(input > 0){
            int remainder = input % 8 ;
            sb.append(remainder) ;
            input /= 8 ;
        }
        return sb.reverse().toString() ;
    }
    private static String hexalDecimal(Integer input){
        StringBuilder sb = new StringBuilder() ;
        char start = 'A' ;
        Map<Integer , Character> refer = new LinkedHashMap<>() ;
        for(int i = 10 ; i < 16 ; i ++){
            refer.put(i, start) ;
            start ++ ;
        }
        while(input > 0 ){
            int remainder = input % 16 ;
            if(remainder > 9){
                sb.append(refer.get(remainder)) ;
            }else{
                sb.append(remainder) ;
            }
            input /= 16 ;
        }
        return sb.reverse().toString() ;
    }


}
