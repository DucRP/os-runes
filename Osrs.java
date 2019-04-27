/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osrs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class Osrs {

    public Osrs() { }
    
    public void startOSRS() {
        
        try {
            
            String com = "java -Duser.home=${SNAP_USER_COMMON}'/runescape' -Djava.class.path=${SNAP_USER_COMMON}'/runescape/oldschool/jagexappletviewer.jar' -Dcom.jagex.config='http://oldschool.runescape.com/jav_config.ws' -Dawt.useSystemAAFontSettings='on' -Dswing.aatext='true' -Dhttps.protocols='TLSv1.2' -Dsun.java2d.opengl='false' -Dsun.java2d.uiScale='1' 'jagexappletviewer' 'oldschool' > /dev/null";
            
            String[] cmd = { "/bin/sh", "-c", com };
            Process p = Runtime.getRuntime().exec(cmd);
            
            String line;
            
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            while ((line = in.readLine()) != null) {
              System.out.println(line);
            }
            
            in.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Osrs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateOSRS() {
                   
        execute("rm -rf ${SNAP_USER_COMMON}'/runescape/oldschool' && mkdir -p ${SNAP_USER_COMMON}'/runescape/oldschool' && cd ${SNAP_USER_COMMON}'/runescape/oldschool'", false);

        System.out.println(">> Downloading game from runescape.com...");

        execute("cd ${SNAP_USER_COMMON}'/runescape/oldschool' && wget -O 'osrs.msi' 'http://www.runescape.com/downloads/oldschool.msi'", false);

        System.out.println(">> Extracting files...");

        execute("${SNAP}/usr/lib/p7zip/7z e -o${SNAP_USER_COMMON}'/runescape/oldschool/tmp' ${SNAP_USER_COMMON}'/runescape/oldschool/osrs.msi'", false);
        
        execute("${SNAP}/usr/lib/p7zip/7z e -o${SNAP_USER_COMMON}'/runescape/oldschool/tmp' -y ${SNAP_USER_COMMON}'/runescape/oldschool/tmp/rslauncher.cab'", false);
        
        execute("cp ${SNAP_USER_COMMON}'/runescape/oldschool/tmp/JagexAppletViewerJarFile'* ${SNAP_USER_COMMON}'/runescape/oldschool/jagexappletviewer.jar' && cp ${SNAP_USER_COMMON}'/runescape/oldschool/tmp/JagexAppletViewerPngFile' ${SNAP_USER_COMMON}'/runescape/oldschool/jagexappletviewer.png' && rm -rf ${SNAP_USER_COMMON}'/runescape/oldschool/tmp'", false);

        System.out.println(">> Done!");
        
    }
    
    public boolean existsFiles() {
        
        try {
        
            File file = new File(getCommonFolder() + "/runescape/oldschool/jagexappletviewer.jar");
            
            return file.exists();
            
        } catch(Exception ex) { }
        
        return false;
        
    }
    
    private String getCommonFolder() {

        try {
        
            String[] cmd = { "/bin/sh", "-c", "echo $SNAP_USER_COMMON" };
            Process p = Runtime.getRuntime().exec(cmd);
            
            String line;
            
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            line = in.readLine();
            
            in.close();
            
            return line;
            
        } catch(Exception ex) { }
        
        return null;

    }
    
    private void execute(String com, boolean print) {
        
        try {
            String[] cmd = { "/bin/sh", "-c", com };
            Process p = Runtime.getRuntime().exec(cmd);
                        
            String line;
            
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            while((line = in.readLine()) != null) {
                if(print) System.out.println(line);
            }
            
            in.close();
            
            in = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            
            while((line = in.readLine()) != null) {
                if(print) System.out.println(line);
            }
            
            in.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Osrs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
