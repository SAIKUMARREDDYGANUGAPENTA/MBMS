import java.util.Scanner;

interface format{
    abstract void pageFormat();
}

interface basename{
    abstract void selectingTheBase(String b);
    abstract void selectingTheBase(int a,String b);

}

public class INTERFACE implements format,basename{
   
    public void pageFormat() {
        System.out.println("\t\t\t                        ********************************************************************************");
        System.out.println("\t\t\t                        *              ***     ***   * * * * *    ***     ***   * * * * *              *");
        System.out.println("\t\t\t                        *              *  *   *  *   *        *   *  *   *  *   *                      *");
        System.out.println("\t\t\t                        *              *   * *   *   * * * * *    *   * *   *   * * * * *              *");
        System.out.println("\t\t\t                        *              *    *    *   *        *   *    *    *           *              *");
        System.out.println("\t\t\t                        *              *         *   * * * * *    *         *   * * * * *              *");
        System.out.println("\t\t\t                        *                                         --- MILITARY BASE MANAGEMENT SYSTEM  *");
        System.out.println("\t\t\t                        ******************************************************************************** ");
        System.out.println("\t\t\t                           *************************************************************************");
        System.out.println("\t\t\t                           *                                                                       *");
        System.out.println("\t\t\t                           *   **********                                             **********   *");
        System.out.println("\t\t\t                           *   * BASE-1 *                                             * BASE-2 *   *");
        System.out.println("\t\t\t                           *   * 'META' *                                             * 'BETA' *   *");
        System.out.println("\t\t\t                           *   **********                                             **********   *");
        System.out.println("\t\t\t                           *                         *********************                         *");
        System.out.println("\t\t\t                           *                         *       HEAD        *                         *");
        System.out.println("\t\t\t                           *                         *       BASE        *                         *");
        System.out.println("\t\t\t                           *                         *********************                         *");
        System.out.println("\t\t\t                           *   **********                                             **********   *");
        System.out.println("\t\t\t                           *   * BASE-3 *                                             * BASE-4 *   *");
        System.out.println("\t\t\t                           *   * 'PARA' *                                             * 'SOUL' *   *");
        System.out.println("\t\t\t                           *   **********                                             **********   *");
        System.out.println("\t\t\t                           *                                                                       *");
        System.out.println("\t\t\t                           *************************************************************************");
   
    }


    public void selectingTheBase(String Basename) 
    {   
        System.out.println();
        System.out.print("\t\t\t                           ");
        System.out.println("************************************************************************");
        System.out.print("\t\t\t                           ");
        System.out.println("************************************************************************");
        System.out.print("\t\t\t                           ");
        System.out.println("**                              BASE : "+Basename+"                           **");
        System.out.print("\t\t\t                           ");
        System.out.println("************************************************************************");
        System.out.print("\t\t\t                           ");
        System.out.println("************************************************************************");
        System.out.println();
    }

    public void selectingTheBase(int a,String b_name)
    {   System.out.println();
        System.out.print("\t\t\t                       ");
        System.out.println("*********************************************************************************");
        System.out.print("\t\t\t                       ");
        System.out.println("*********************************************************************************");
        System.out.print("\t\t\t                       ");
        System.out.println("**                              BASE : "+b_name+"                           **");
        System.out.print("\t\t\t                       ");
        System.out.println("*********************************************************************************");
        System.out.print("\t\t\t                       ");
        System.out.println("*********************************************************************************");
        System.out.println();
    }


}