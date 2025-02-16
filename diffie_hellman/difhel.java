package diffie_hellman;
import java.util.*;
public class difhel{
    public static int fastExponen(int base, int pow, int n){

        //Convert the power into the binary form
         String binaryString = Integer.toBinaryString(pow);

         int f = 1;
         for(int i = 0; i < binaryString.length(); i++){
            f = (f * f) % n;
            if(binaryString.charAt(i) == '1'){
                f = (f * base) % n;
            }
         }//outer for end

        System.out.println("The value of the modular exponentiation is: " + f);
        return f;

    }//method end

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the base: ");
        int base = sc.nextInt();

        System.out.println("Enter the power: ");
        int pow = sc.nextInt();

        System.out.println("Enter the modulus: ");
        int n = sc.nextInt();

        fastExponen(base, pow, n);

        sc.close();
    }//main end
}//class end