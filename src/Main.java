
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().replaceAll(" ","");
        String[] arr;
        int oper = 0;
        if (s.contains("+")){
            arr = s.split("\\+");
            oper=1;
        }else if (s.contains("-")){
            arr = s.split("-");
            oper=2;
        }else if (s.contains("*")){
            arr = s.split("\\*");
            oper=3;
        }else if (s.contains("/")){
            arr = s.split("/");
            oper=4;
        }else{
            throw new Exception("//т.к. строка не является математической операцией");
        }
        if (arr.length>2){
            throw new Exception("//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        int a = 0;
        int b = 0;
        boolean int1= false;
        boolean int2= false;
        try{
            a = Integer.parseInt(arr[0]);
            int1 = true;
        } catch (Exception e){
            a = fromRoman(arr[0]);
        }
        try{
            b = Integer.parseInt(arr[1]);
            int2 = true;
        } catch (Exception e){
            b = fromRoman(arr[1]);
        }
        int c = 0;
        if (int1==int2){
            switch (oper){
                case 1:
                    c = a+b;
                    break;
                case 2:
                    c = a-b;
                    break;
                case 3:
                    c = a*b;
                    break;
                case 4:
                    c = a/b;
                    break;
            }
            if (int1){
                System.out.println(c);
            } else {
                if (c<0){
                    throw new Exception("//т.к. в римской системе нет отрицательных чисел");
                }
                System.out.println(toRoman(c));
            }
        } else {
            throw new Exception("//т.к. используются одновременно разные системы счисления");
        }
    }

    public static String toRoman(int n) {
        int[]arr = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String [] arr2 = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        String res = "";
        while (n>0){
            for (int i = 0; i <arr.length; i++) {
                if (n>=arr[i]){
                    res = res+arr2[i];
                    n = n - arr[i];
                    break;
                }
            }
        }
        return res;
    }


    public static int fromRoman(String romanNumeral) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int result  = 0;
        int prev = 0;
        for (int i = romanNumeral.length()-1; i>=0; i--) {
            int curr = map.get(romanNumeral.charAt(i));
            if (curr<prev){
                result = result-curr;
            } else {
                result = result+curr;
            }
            prev = curr;
        }
        return result;
    }
}