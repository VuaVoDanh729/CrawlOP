
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class CrawlerOnepiece {

    Helper helper = new Helper();
    ArrayList<Chap> listChap = new ArrayList<>();
    String url;

    public CrawlerOnepiece(String url) {
        this.url = url;
    }

    // lấy tất cả chap trên web và in ra
    public void GetListChap() throws IOException {
        listChap = getAllChapInPage(url);
        printListChap();
    }

    public void SaveChap() throws IOException {
        String direct = helper.getDirect();
        String chapNumber = helper.inputString();
        Chap chap = getChapByChapNumber(chapNumber);
        System.out.println("Chap : "+ chap.getChap_number());
        saveFile(chap, direct);
    }

    // Lấy chap có số tương ứng
    private Chap getChapByChapNumber(String chapNumber) {
        for (int i = 0; i < listChap.size(); i++) {
            if (listChap.get(i).getChap_number().equalsIgnoreCase(chapNumber)) {
                return listChap.get(i);
            }
        }
        System.out.println("No chap has name : " + chapNumber);
        return null;
    }

    private void printListChap() {
        System.out.println("List Chaps : ");
        for (int i = 0; i < listChap.size(); i++) {
            System.out.println("Chap : " + listChap.get(i).getChap_number());
        }
    }

    // lấy tất cả chap 
    private ArrayList<Chap> getAllChapInPage(String urls) throws IOException {
        ArrayList<Chap> list_chap = new ArrayList<>();
        Document document = Jsoup.connect(urls).get();
        Elements elms = document.getElementsByClass("row");
        for (int i = 0; i < elms.size(); i++) {
            Elements elm_row = elms.get(i).getElementsByTag("a");
            for (int j = 0; j < elm_row.size(); j++) {
                String link_chap = elm_row.first().absUrl("href");
                list_chap.add(new Chap(link_chap));
            }
        }
        return list_chap;
    }

    // lấy tất cả IMG trong chap
    private ArrayList<String> listImgOnPage(String pageURL) throws IOException {
        Document document = Jsoup.connect(pageURL).get();
        ArrayList<String> list_img = new ArrayList<>();
        Elements elms = document.getElementsByClass("grab-content-chap");
        Elements e = document.getElementsByTag("img");
        for (int i = 0; i < e.size(); i++) {
            String url = e.get(i).absUrl("src");
            if (url.equals("")) {
                continue;
            }
            list_img.add(url);
        }
        return list_img;
    }

    // lưu IMG
    private void saveImg(String src, String name, String dir) {
        try {
            URL url = new URL(src);
            InputStream in = url.openStream();
            OutputStream out = new BufferedOutputStream(new FileOutputStream(dir + "\\" + name));
            for (int b; (b = in.read()) != -1;) {
                out.write(b);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not Dowload File !");
        }
    }

    private void saveFile(Chap chap, String dir) {
        try {
            ArrayList<String> list_img = listImgOnPage(chap.getUrl());
            for (int i = 0; i < list_img.size(); i++) {
                String name = i + ".jpg";
                saveImg(list_img.get(i), name, dir);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Error to save file !", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, "Dowload sucessfull chap " + chap.getChap_number());
    }
}
