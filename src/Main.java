import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by eduardohenrique on 04/06/17.
 */
public class Main {

    static RBTree rbTree = new RBTree();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = null;
        String fileName = args[0];
        if(fileName.isEmpty()){
            System.out.println("Verify if the filename is correct!");
        } else {

            try {
                reader = new BufferedReader(new FileReader(fileName));
                String line;
                StringTokenizer tokenizer;
                String word;
                String value;
                do {
                    line = reader.readLine();

                    tokenizer = ((line != null) && !line.isEmpty())
                            ? new StringTokenizer(line, " ") : null;

                    if (tokenizer != null) {
                        word = tokenizer.nextToken();
                        if (tokenizer.hasMoreTokens()) {
                            value = tokenizer.nextToken();
                            callOperations(word, value);
                        }
                    }

                } while (line != null);
                System.out.println("\n==========    WORDS   ============");
                rbTree.rbPrint(rbTree.getRoot());
                System.out.println("\n========== FINAL TREE ============");
                rbTree.rbCheck(rbTree.getRoot());

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
    }

    public static void callOperations(String word, String value) {
        String VALUE_INSERT = "1";
        String VALUE_DElETE = "0";
        if(value.equals(VALUE_INSERT)){
            System.out.println("\nTo Insert ===> " + word);
            if(rbTree.rbSearch(word) == null) {
                rbTree.rbInsert(word);
                System.out.println("The word '"+ word +"' was inserted!");
            } else {
                System.out.println("The word '"+ word +"' already exists in tree!");
            }
        } else if(value.equals(VALUE_DElETE)){
            System.out.println("\nTo Delete ===> " + word);
            if(rbTree.rbSearch(word) != null) {
                rbTree.rbDelete(word);
                System.out.println("The word '" + word + "' was deleted!");
                System.out.println("\n========== PRINT ============");
                rbTree.rbPrint(rbTree.getRoot());
                System.out.println("\n========== CHECK ============");
                rbTree.rbCheck(rbTree.getRoot());
            } else {
                System.out.println("The word '" + word + "' do not are in tree!");
            }
        }
    }


}
