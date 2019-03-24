/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dll;

public class test {
    public static void main(String[] args) throws Exception {
        DLL<Integer> dll = new DLL<>();
        System.out.println("--- Add --- ");
        dll.add(1);
        dll.add(2);
        dll.add(20);
        dll.add(10);
        dll.add(5);
        dll.print();
        System.out.println("------Add index------");
        System.out.println("Add index 1 = 9");
        dll.add(1, 9);
        System.out.println("Add index 5 = 9");
        dll.add(5, 9);
        dll.print();
        System.out.println("--------addAll----------");
        DLL c = new DLL();
        System.out.println("Add list");
        c.add(1);
        c.add(100);
        c.add(200);
        c.add(600);
        c.add(59);
        dll.addAll(c);
        c.print();
        System.out.println("Added!");
        dll.print();
//        System.out.println("--------");
        DLL l = new DLL();
        System.out.println("--- add all (index) ----");
        l.add(0);
        l.add(0);
        l.add(0);
        System.out.println("Add 3 numbers 1 to index 1");
        dll.addAll(1, l);
        dll.print();
//        System.out.println("-------------");
//        dll.clear();
//        dll.print();
          System.out.println("---------contains---------");
          boolean t = dll.contains(3);
          System.out.println("Contains 3:");
          if (t) {
              System.out.println("co");
            }else{
              System.out.println("khong");
          }
          System.out.println("--------containsAll-----------");
          l.clear();
          l.add(99);
          l.add(9);
          l.add(3);
          System.out.println("Contains list:");
          l.print();
          boolean f= dll.containsAll(l);
          if (!f) {
            System.out.println("co");
            }else{
              System.out.println("khong");
          }
            int m = dll.indexOf(9);
            if (m==-1) {
              System.out.println("Khong tim thay");
            }else{
            System.out.println("Vi tri: "+m);
            }
            System.out.println("-------remove-------");
            dll.print();
            System.out.println("Remove first 0");
              dll.remove(new Integer(0));
              dll.print();
              System.out.println("-------remove index------------");
              System.out.println("Remove index 3");
              dll.remove(3);
              dll.print();
              System.out.println("-----removeAll------");
              l.clear();
              System.out.println("Remove list:");
              l.add(6);
              l.add(5);
              l.print();
              System.out.println("Removed!");
              dll.removeAll(l);
              dll.print();

              

        }
    }
