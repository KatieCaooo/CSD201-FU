/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author CaoThu
 */
public class Handling {

    private static String getFileExtension(String fileName) {
        String extension = "";
        int index = fileName.lastIndexOf(".");

        if (index >= 1) {
            extension = fileName.substring(index + 1);
        }

        return extension;
    }

    public static void readFile(String fileName) throws IOException {
        BufferedReader br = null;
        FileReader fr = null;

        if (getFileExtension(fileName).equals("html")) {
            try {
                String line;
                fr = new FileReader(fileName);
                br = new BufferedReader(fr);
                for (int i = 1; i < fileName.length(); i++) {
                    while ((line = br.readLine()) != null) {
                        System.out.println("[" + i + "]" + line);
                        i++;
                    }
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

    }

    public static void checkHtmlFile() throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Paste your link file here:");
        String fileName = sc.nextLine().trim();
        readFile(fileName);
        System.out.println("------Result-----");
        if (getFileExtension(fileName).equals("html")) {
            try {
                FileReader rf = new FileReader(fileName);
                BufferedReader bf = new BufferedReader(rf);
                String line;
                int count = 0;
                int error = 0;
                Stack<String> stacks = new Stack<>();
                while ((line = bf.readLine()) != null) {
                    count++;
                    String tagOpen = "<\\w+>";
                    String tagClose = "<\\/\\w+>";
                    Pattern r = Pattern.compile(tagOpen);
                    Matcher matchOpen = r.matcher(line);
                    Pattern r1 = Pattern.compile(tagClose);
                    Matcher matchClose = r1.matcher(line);
                    if (matchOpen.find()) {
                        stacks.push(matchOpen.group());
                        if (matchClose.find() && !stacks.isEmpty()) {
                            String tagInStack = stacks.pop();
                            String contentOpen = tagInStack.replaceAll("<", "").replaceAll(">", "");
                            String contentClose = matchClose.group().replaceAll("</", "").replaceAll(">", "");
                            if (!contentOpen.equals(contentClose)) {
                                System.out.println("Tag NOT MATCHING line: " + count);
                                error++;
                            }
                        }
                    }
                    if (matchClose.find()) {
                        if (stacks.isEmpty()) {
                            System.out.println("Odd tag right line: " + count);
                            error++;
                        } else if (!stacks.isEmpty()) {
                            String tagInStack = stacks.pop();
                            String contentOpen = tagInStack.replaceAll("<", "").replaceAll(">", "");
                            String contentClose = matchClose.group().replaceAll("</", "").replaceAll(">", "");
                            if (!contentOpen.equals(contentClose)) {
                                System.out.println("Tag NOT MATCHING line: " + count);
                                error++;
                            }
                        }
                    }
                }
                if (!stacks.isEmpty()) {
                    System.out.println("Odd " + stacks.size() + " open tag");
                    error++;
                }
                if (error == 0) {
                    System.out.println("Check complete with NO error");
                } else {
                    System.out.println("Check complete with " + error + " ERROR");
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        } else {
            System.out.println("Format file invalid");
        }
    }
}
