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
public class BinaryTree {

    public String word;
    public LinkedList linkedListWord;

    public BinaryTree left;
    public BinaryTree right;

    public void add(String newNodeWord, String newLinkedListWord) throws Exception {
        if (word == null) {
            this.word = newNodeWord;
            this.linkedListWord = new LinkedList();
            this.linkedListWord.add(newLinkedListWord);
        } else {
            int result = newNodeWord.compareTo(this.word);

            if (result > 0) {
                if (right == null) {
                    right = new BinaryTree();
                }
                right.add(newNodeWord, newLinkedListWord);
            } else if (result < 0) {
                if (left == null) {
                    left = new BinaryTree();
                }
                left.add(newNodeWord, newLinkedListWord);
            } else {
                this.linkedListWord.add(newLinkedListWord);
            }
        }
    }

    public String search(String searchWord) throws Exception {
        if (word != null) {
            int result = searchWord.compareToIgnoreCase(this.word);

            if (result > 0) {
                if (right != null) {
                    return right.search(searchWord);
                }
            } else if (result < 0) {
                if (left != null) {
                    return left.search(searchWord);
                }
            } else {
                return linkedListWord.getWords();
            }
        }

        throw new Exception("Not found result!");
    }
}
