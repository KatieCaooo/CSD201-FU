/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checktag;

/**
 *
 * @author ADMIN
 */
public class HTML {
    private static final String DOCTYPE_TAG = "<!DOCTYPE html>";
    private static final String[] SINGLE_TAG = {"<meta [\\w\\W]*>$", "<br>", 
        "<[\\w]*( [\\w\\W]*)*/>", "<img( [\\w\\W]*)*>", "<area( [\\w\\W]*)*>"}; //cho các tag như <br>, <input />
    public static boolean checkOpeningTag(String tag) {
        if (tag.matches("<[^/>]*>$")){
            
            if (!tag.equals(DOCTYPE_TAG)) {
                boolean isSingleTag = false;
                for(String regex: SINGLE_TAG) {
                    if(tag.matches(regex)) {
                        isSingleTag = true;
                    }
                }
                if (!isSingleTag)
                    return true;
            }
            
        }
        return false;
    }
    
    public static boolean checkClosingTag(String tag) {
        if (tag.matches("</[^>]*>$")){
            return true;
        }
        return false;
    }
    
    public static boolean checkMatchOpeningClosingTags(String openTag, String closeTag) {
        String openName;
        if( !openTag.contains(" ")) {
            openName = openTag.substring(1, openTag.length() - 1);
        } else {
            int index = openTag.indexOf(" ");
            openName = openTag.substring(1, index);
        }
        
        String closeName = closeTag.substring(2, closeTag.length() - 1);
        //System.out.println(openName + " - " + closeName);
        return openName.equalsIgnoreCase(closeName);
    }
    
    public static String getClosingTagFromOpeningTag(String openTag) {
        String openName;
        if( !openTag.contains(" ")) {
            openName = openTag.substring(1, openTag.length() - 1);
        } else {
            int index = openTag.indexOf(" ");
            openName = openTag.substring(1, index);
        }
        return "</" + openName + ">";
    }
    
    public static String getOpeningTag(String openTag) {
        if( !openTag.contains(" ")) {
            return "<" + openTag.substring(1, openTag.length() - 1) + ">";
        } else {
            int index = openTag.indexOf(" ");
            return "<" + openTag.substring(1, index) + ">";
        }
    }
}
