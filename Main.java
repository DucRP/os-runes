/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osrs;

/**
 *
 * @author ripe3@github
 */
public class Main {
    
    public static void main(String[] args) {
        
        System.out.println(" ");
        System.out.println("*******************************************");
        System.out.println("*  Launcher for OS RuneScape v0.3         *");
        System.out.println("*  https://snapcraft.io/os-runes          *");
        System.out.println("*******************************************");
        System.out.println(" ");        
        System.out.println("*******************************************");
        System.out.println("*  Version 0.3 - Changelog                *");
        System.out.println("*                                         *");
        System.out.println("*  - Changed where the game saves the     *");
        System.out.println("*  user data to prevent it from being     *");
        System.out.println("*  erased while updating the game files   *");
        System.out.println("*                                         *");
        System.out.println("*  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-  *");
        System.out.println("*                                         *");
        System.out.println("*  - Apparently there's several links     *");
        System.out.println("*  to download the installer from Jagex   *");
        System.out.println("*  website, they all seems to be the      *");
        System.out.println("*  same file however if you notice any    *");
        System.out.println("*  failure while starting or updating,    *");
        System.out.println("*  please open an issue on GitHub.        *");
        System.out.println("*                                         *");
        System.out.println("*  - Also, the launcher code is also      *");
        System.out.println("*  available on GitHub under the branch   *");
        System.out.println("*  'launcher' =)                          *");
        System.out.println("*                                         *");
        System.out.println("*  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-  *");
        System.out.println("*                                         *");
        System.out.println("*  Don't forget to visit my GitHub and    *");
        System.out.println("*  star it! Thanks.                       *");
        System.out.println("*  https://github.com/ripe3/os-runes      *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
        System.out.println(" ");
        System.out.println("§  Commands available:");
        System.out.println("   --update: Update the game files");
        System.out.println(" ");
        System.out.println("+ How to update the game files?");
        System.out.println(" Open your terminal and type:");
        System.out.println("   os-runes --update");
        System.out.println(" ");
        
        if(args.length > 0) {
            if("--update".equals(args[0])) {
                System.out.println("> Update requested"); 
                new Osrs().updateOSRS();
            } else {
                System.out.println("> Unknown command \"" + args[0] + "\". Exiting.");  
                System.exit(0);
            }
        }
        
        if(!new Osrs().existsFiles()) {
            
            System.out.println("> Missing game data..."); 
            new Osrs().updateOSRS();
            
        }
        
        System.out.println("> Starting game..."); 
        new Osrs().startOSRS();
        
    }
    
}
