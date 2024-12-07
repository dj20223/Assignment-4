import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class Main {

    public static void main (String[] args) {
        String pathOfWordList = "wordlist10000.txt";

        if (args.length == 0) {
            System.out.println("Please provide a password as input.");
            return;
        }
        String password = args[0];

        try {
            // Initialize hash tables
            SeparateChainingHashST<String, Integer> oldSch = new SeparateChainingHashST<>(1000);
            SeparateChainingHashST<String, Integer> currSch = new SeparateChainingHashST<>(1000);
            LinearProbingHashST<String, Integer> oldLph = new LinearProbingHashST<>(20000);
            LinearProbingHashST<String, Integer> currLph = new LinearProbingHashST<>(20000);
            
            // Populate hash tables
            populateHT(pathOfWordList, oldSch);
            populateHT(pathOfWordList, currSch);
            populateHT(pathOfWordList, oldLph);
            populateHT(pathOfWordList, currLph);

            // Test passwords
            System.out.println("\nPassword Strength Results:\n");
                System.out.println("Password: " + password);
                passwordCheck(password, oldSch, "Separate Chaining (Old Hash)");
                passwordCheck(password, currSch, "Separate Chaining (Current Hash)");
                passwordCheck(password, oldLph, "Linear Probing (Old Hash)");
                passwordCheck(password, currLph, "Linear Probing (Current Hash)");
                System.out.println();

        } catch (IOException e) {
            System.err.println("Error reading the wordlist: " + e.getMessage());
        }
    }

    private static boolean passwordIsStrong(String password, SymbolTable<String, Integer> table) {
        if (password.length() < 8){
            return false;
        }
        String lowCPassword = password.toLowerCase();
        if (table.contains(lowCPassword)){
            return false;
        }

        if (lowCPassword.length() > 1 && Character.isDigit(lowCPassword.charAt(lowCPassword.length() - 1))) {
            String possibleW = lowCPassword.substring(0, lowCPassword.length() - 1);
            if (table.contains(possibleW)){
                return false;
            }
        }
        return true;
    }

    private static void populateHT(String path, SymbolTable<String, Integer> table) throws IOException {
        BufferedReader buff = new BufferedReader(new FileReader(path));
        String word;
        int lineNumber = 1;
        while ((word = buff.readLine()) != null) {
            table.put(word.trim().toLowerCase(), lineNumber++);
        }
        buff.close();
    }

    private static void passwordCheck(String password, SymbolTable<String, Integer> table, String tableName) {
        table.zeroComparisons(); // Reset search cost counter
        boolean strong = passwordIsStrong(password, table);
        int cost = table.getComparisons();
        System.out.println("\t" + tableName + ": " + (strong ? "Strong" : "Weak") + ", Search Cost: " + cost + " comparisons");
    }
}
