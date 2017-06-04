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
        String VALUE_INSERT = "1";
        String VALUE_DElETE = "0";

        try{
            reader = new BufferedReader(new FileReader("dicionario2.txt"));
            String line;
            StringTokenizer tokenizer;
            String word;
            String value = null;
            do {
                line = reader.readLine();
                tokenizer = (line != null) ? new StringTokenizer(line, " ") : null;
                if(tokenizer != null) {
                    word = tokenizer.nextToken();
                    if (tokenizer.hasMoreTokens()) {
                        value = tokenizer.nextToken();
                        callOperations(word, value);
                        // limpar as variáveis
                    }
                }

            } while (line != null);
            System.out.println("\n========== TREE ============");
            rbTree.rbCheck(rbTree.getRoot());

        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if(reader != null){
                reader.close();
            }
        }

    }

    public static void callOperations(String word, String value) {
        String VALUE_INSERT = "1";
        String VALUE_DElETE = "0";
        if(value.equals(VALUE_INSERT)){
            rbTree.rbInsert(word);
            System.out.println("\n========== CHECK INSERT ============");
            System.out.println("\nTo Inserto ===> "+ word);
        } else if(value.equals(VALUE_DElETE)){
            System.out.println("\nTo Delete ===> "+ word);
            rbTree.rbDelete(word);
            System.out.println("\n========== PRINT ============");
            rbTree.rbPrint(rbTree.getRoot());
            System.out.println("\n========== CHECK DELETE ============");
            rbTree.rbCheck(rbTree.getRoot());
        }
    }

}
