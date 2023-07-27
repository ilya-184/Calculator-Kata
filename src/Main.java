import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // ВВОД ВЫРАЖЕНИЯ //
        Scanner scan1 = new Scanner(System.in);
        System.out.println("Введите выражение");
        String str1 = scan1.nextLine();
        //double res1 = Double.parseDouble(str1); // returns double primitive;

        //String[] res1 = str1.split();

        // ПРОВЕРКА НАЛИЧИЯ ЗНАКА В ВЫРАЖЕНИИ
        boolean check1 = str1.contains("+") || str1.contains("-") || str1.contains("*") || str1.contains("/");
        //System.out.println(check1);

        if (check1) {
            //System.out.println(str1);
            String[] numb0 = str1.split("[/*+-]");

            if (numb0.length > 2) {
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

            } else {


                String[] rom_symb_all = new String[]{"I", "V", "X", "L", "C", "D", "M"};

                //АНАЛИЗ НАЛИЧИЯ РИМСКИХ ЦИФР

                Rome_check rome_check1 = new Rome_check();
                rome_check1.rom_symb = rom_symb_all;
                rome_check1.obj = numb0[0];
                boolean check2 = rome_check1.romCheck();

                Rome_check rome_check2 = new Rome_check();
                rome_check2.rom_symb = rom_symb_all;
                rome_check2.obj = numb0[1];
                boolean check3 = rome_check2.romCheck();

                if (check2 != check3) {
                    System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");

                } else {




                    if (check2) {

                        // РИМСКИЕ ЦИФРЫ

                        // ПРЕОБРАЗОВАНИЕ РИМСКИХ ЦИФР В АРАБСКИЕ
                        Convert res1 = new Convert();
                        int part1 = res1.romanToArab(numb0[0].strip());
                        //System.out.println(part1);
                        int part2 = res1.romanToArab(numb0[1].strip());
                        //System.out.println(part2);

                        if(part1>10|part2>10){
                            System.out.println("throws Exception //т.к. на вход принимаются только числа от I до X");

                        } else {

                            //Арифметическое действие

                            Operation oper = new Operation();
                            oper.x1 = part1;
                            oper.x2 = part2;
                            oper.str2 = str1;
                            boolean check4 = oper.mathOper()<=0;
                            //System.out.println(oper.mathOper());
                            if (check4){
                                System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                            }  else {

                                //ОБРАТНОЕ ПРЕОБРАЗОВАНИЕ ЦИФР В РИМСКИЕ
                                System.out.println(res1.arabToRom(oper.mathOper()));
                            }
                        }


                    }  else {
                        // АРАБСКИЕ ЦИФРЫ

                        Operation oper = new Operation();
                        oper.x1 = Integer.parseInt(numb0[0].strip());
                        //System.out.println(oper.x1);
                        oper.x2 = Integer.parseInt(numb0[1].strip());
                        oper.str2 = str1;

                        if(oper.x1>10|oper.x2>10){
                            System.out.println("throws Exception //т.к. на вход принимаются только числа от 1 до 10");
                        } else {
                            System.out.println(oper.mathOper());

                        }
                    }
                }

            }

        } else {
            System.out.println("throws Exception //т.к. строка не является математической операцией");
        }
    }
}

class Rome_check{
    String[] rom_symb;
    String obj;
    boolean romCheck() {
        boolean rom_bool = false;
        for (String k : rom_symb) {
            rom_bool = obj.contains(k);
            if (rom_bool) break;
        }
        return rom_bool;
    }
}



class Convert{

    public int romanToArab(String x){
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);

        char[] arr1 = x.toCharArray(); // Разбиение римкой цифры на части
        int arabian_res = map.get(arr1[arr1.length-1]);
        for (int i=arr1.length-2; i>=0;i--){
            if (map.get(arr1[i+1])>map.get(arr1[i])){
                arabian_res -= map.get(arr1[i]);
            } else {
                arabian_res += map.get(arr1[i]);
            }
        }

        return arabian_res;
    }

    public String arabToRom(int x){

        String[] Arab19 = new String[] {"","I","II","III","IV","V","VI","VII","VIII","IX"};

        String final_res = "";
        String res1 = "";
        String res2 = "";
        int rem =0;

        if (x>0 & x<10){
            res1 = "";
            rem = x;
        } else if (x>=10 & x<20){
            res1 = "X";
            rem = x-10;
        } else if (x>=20 & x<30){
            res1 = "XX";
            rem = x-20;
        } else if (x>=30 & x<40){
            res1 = "XXX";
            rem = x-30;
        } else if (x>=40 & x<50){
            res1 = "XL";
            rem = x-40;
        } else if (x>=50 & x<60){
            res1 = "L";
            rem = x-50;
        } else if (x>=60 & x<70){
            res1 = "LX";
            rem = x-60;
        } else if (x>=70 & x<80){
            res1 = "LXX";
            rem = x-70;
        } else if (x>=80 & x<90){
            res1 = "LXXX";
            rem = x-80;
        } else if (x>=90 & x<100){
            res1 = "XC";
            rem = x-90;
        } else if (x==100){
            res1 = "C";
        }

        res2 = Arab19[rem];


        final_res = res1+res2;
        return final_res;


    }

}

class Operation{
    String str2;
    int res_final,x1,x2;
    public int mathOper(){

        if (str2.contains("+")){
            res_final = x1 + x2;
        } else if (str2.contains("-")){
            res_final = x1 - x2;
        } else if (str2.contains("*")){
            res_final = x1 * x2;
        } else if (str2.contains("/")){
            res_final = x1 / x2;
        }

        return res_final;
    }
}
