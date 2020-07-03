
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class MainClass {

    public static void main(String[] args) throws IOException {
        Helper helper = new Helper();
        String url = "https://bigtruyen.net/one-piece-full-mau/";
        CrawlerOnepiece crawler = new CrawlerOnepiece(url);
        while (true) {
            menu();
            int choice = helper.getIntInRange(1, 3);
            switch (choice) {
                case 1:
                    crawler.GetListChap();
                    break;
                case 2:
                    crawler.SaveChap();
                    break;
                case 3:
                    return;
            }
        }

    }

    static void menu() {
        System.out.println("----- OnePiece -----");
        System.out.println("1. Get list Chap.");
        System.out.println("2. Dowload chap");
        System.out.println("3. Exit");
    }

}
