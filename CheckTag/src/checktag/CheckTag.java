/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checktag;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author CaoThu
 */
public class CheckTag {

    private static String enough = "Enough tag";
    private static String missingTag = "Missing tag";
    private static String residualTag = "Residual tag";
//    private static final String FILENAME = "D:\\CSD201-B3T\\CheckTag\\CheckCode.html";

    private static String getFileExtension(String fileName) {
        String extension = "";
        int index = fileName.lastIndexOf(".");

        if (index >= 1) {
            extension = fileName.substring(index + 1);
        }

        return extension;
    }

    public static String readFile(String fileName) throws IOException {
        BufferedReader br = null;
        FileReader fr = null;
        String fileContent = "";

        if (getFileExtension(fileName).equals("html")) {
            try {
                String line;
                fr = new FileReader(fileName);
                br = new BufferedReader(fr);
//                for (int i = 1; i < fileName.length(); i++) {
//                    while ((line = br.readLine()) != null) {
//                        System.out.println("[" + i + "]" + line);
//                        i++;
//                    }
//                }
                while ((line = br.readLine()) != null) {
                    fileContent += line;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            }
        } else {
            System.out.println("Format file invalid");
        }

        return fileContent;
    }

    public static void checkHtml() {
        Stack<String> strStack = new Stack<String>();
        String residualTags = "";
        String result = "";

        // Yêu cầu user nhập đường dẫn file
        System.out.println("Paste your link file here:");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine().trim();

        try {
            //Lấy nội dung file
            String input = readFile(fileName);

            //check tag
            input = input.replaceAll("\"", " ");
            String[] array = input.split("<");//chia chuoi dua tren string phan cac nhau boi <
            String[] words = Arrays.copyOfRange(array, 1, array.length); //xoa phan tu rong
            for (String w : words) {
                if (!w.contains("/") && !w.contains("/>")) {
                    strStack.push(w); //neu khong phai the dong se them vao stack
                } else if (w.contains("/>")) {
                } else {
                    if (!strStack.empty()) {
                        String top = strStack.peek(); //lay phan tu tren cung cua stack
                        String temp = w.substring(1, w.length() - 1);
                        if (top.contains(temp)) { //neu phan tu tren cung cua stack giong voi the
                            strStack.pop();
                        } else {
                            residualTags += w;
                        }
                    } else {
                        residualTags += w;
                    }
                }
            }

            if (!strStack.empty()) {
                result = missingTag;
            } else if (residualTags != null && !residualTags.isEmpty()) {
                result = residualTag;
            } else {
                result = enough;
            }

            System.out.println(result);
        } catch (IOException ex) {
            Logger.getLogger(CheckTag.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void checkTag() {
        Scanner sc = new Scanner(System.in);
        BufferedReader br;
        FileReader fr;
        Pattern p;
        Matcher m;
        Stack<String> openingTagsStack = new Stack<>();
        String response = enough;

        System.out.println("Paste your link file here:");
        String fileName = sc.nextLine().trim();

        if (getFileExtension(fileName).equals("html")) {
            try {
                fr = new FileReader(fileName);
                br = new BufferedReader(fr);
                String line;
                int i = 0;  //đếm số hàng
                
                while ((line = br.readLine()) != null) {
                    i++;

                    //lấy tag đóng và tag mở
                    p = Pattern.compile("(<[^>]*>)"); //regex cho tags
                    m = p.matcher(line);

                    while (m.find()) {
                        String tag = m.group(1);

                        if (HTML.checkOpeningTag(tag)) {
                            //nếu là tag mở, push vào stack
                            openingTagsStack.push(HTML.getOpeningTag(tag));

                        } else if (HTML.checkClosingTag(tag)) {
                            //nếu là tag đóng, check
                            String openingTag = openingTagsStack.peek();
                            
                            if (HTML.checkMatchOpeningClosingTags(openingTag, tag)) {
                                openingTagsStack.pop();
                            } else {
                                //KHÔNG MATCH: kiềm tra missing tag
                                String correspondingOpenTag = "<"
                                        + tag.substring(2, tag.length() - 1) + ">";

                                //tìm tag mở tương ứng trong stack
                                if (openingTagsStack.search(correspondingOpenTag) != -1) {
                                    String missedTag;
                                    String topOpenTag;

                                    while( !( openingTagsStack.peek().equals(correspondingOpenTag)) ){
                                        response = missingTag;
                                        topOpenTag = openingTagsStack.pop();
                                        missedTag = HTML.getClosingTagFromOpeningTag(topOpenTag);
                                        System.out.println("Line [" + i + "]: " 
                                                + response + " " + missedTag);
                                    } //vòng lặp dừng khi tag mở ở đầu stack khớp với tag đóng
                                    
                                    openingTagsStack.pop();
                                } else {
                                    response = residualTag;
                                    System.out.println("Line [" + i + "]: " + response + " " + tag);
                                }
                            }
                        }
                    }
                }

                if (!openingTagsStack.isEmpty()) {
                    System.out.println("There are missing tags: ");
                    response = missingTag;

                    while (!openingTagsStack.isEmpty()) {
                        System.out.println("\t" + openingTagsStack.pop());
                    }
                }
                
                if(response.equals(enough)) {
                    System.out.println("There is no error");
                }

            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            } catch (IOException e) {
                System.out.println("Error occured.");
            }
        } else {
            System.out.println("Invalid file format.");
        }
    }
}
