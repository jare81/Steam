/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

import Chat.OtroChat;
import Visual.MenuFRAME;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dell
 */
public class Main { 

    public static void main(String[] args) {
        Administrador manejo = new Administrador();

        MenuFRAME menu = new MenuFRAME(manejo);
        menu.setVisible(true);
        
       

}
    
}
