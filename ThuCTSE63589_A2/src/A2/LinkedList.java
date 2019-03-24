/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A2;

/**
 *
 * @author CaoThu
 */
public class LinkedList {

    public String word;

    LinkedList next;

    public void add(String newWord) throws Exception {
        if (word == null) {
            this.word = newWord;
        } else {
            int result = newWord.compareToIgnoreCase(word);

            if (result == 0) {
                throw new Exception("This couple word is existed in dictionary");
            } else {
                next = new LinkedList();
                next.add(newWord);
            }
        }
    }

    public String getWords() {
        if (next == null) {
            return this.word;
        } else {
            return next.getWords() + ", " + this.word;
        }
    }
}
