import java.util.*;

public class test {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
    Boolean flag=true;
    while(flag==true)
    {
        
        try{
            int x=input.nextInt();
            flag=false;
        }catch(Exception e)
        {
           // flag=true;
            System.out.println("ERROR");
            input.nextLine();
        }
    } 
    }
   
}
