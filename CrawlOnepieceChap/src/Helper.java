
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class Helper {

    Scanner sc = new Scanner(System.in);

    public void SaveFile() {

    }

    public String getDirect() throws IOException {
        String path = "";
        JFileChooser file_chooser = new JFileChooser();
        file_chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (file_chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            path = file_chooser.getCurrentDirectory().getPath();
        }
        return path;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Helper().getDirect());
    }
    public String inputString() {
        String chapName = JOptionPane.showInputDialog("Enter Chap number : ");
        System.out.println(chapName);
        return chapName;
    }

    public int getIntInRange(int min , int max) {
        int choose  = 0;
        while (true) {
            try {
                System.out.println("Enter number : ");
                choose = Integer.parseInt(sc.nextLine());
                if(choose >= min && choose <= max){
                    return choose;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Choosen wrong !!!");
            }
        }
    }
}
