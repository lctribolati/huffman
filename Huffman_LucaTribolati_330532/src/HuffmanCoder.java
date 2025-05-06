import java.util.HashMap;
import java.util.Map;

class HuffmanCoder {
	// Mappa usata per codifica
    private static Map<Character, String> huffmanCodeMap = new HashMap<>();
    //Mappa usata per decodifica
    private Map<String, Character> reverseHuffmanCodeMap = new HashMap<>();

    // Genera i codici di Huffman dall'albero
    public void generateCodes(HuffmanNode root, String code) {
        if (root == null) {
            return;
        }  
        // Nodo foglia
        if (root.character != '\0') {
            huffmanCodeMap.put(root.character, code);
            reverseHuffmanCodeMap.put(code, root.character);
        }
        // Ricorre a sinistra ed a destra
        generateCodes(root.left, code + "0");
        generateCodes(root.right, code + "1");
    }

    // Restituisce la mappa dei codici di Huffman creata
    public static Map<Character, String> getHuffmanCodeMap() {
        return huffmanCodeMap;
    }

    // Codifica il testo usando i codici generati
    public String encode(String text) {
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            String code = huffmanCodeMap.get(c);
            encodedText.append(code);
        }
        return encodedText.toString();
    }

    // Decodifica testo codificato
    public String decode(String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        String temp = ""; // Buffer

        for (char c : encodedText.toCharArray()) {
            temp += c;
            if (reverseHuffmanCodeMap.containsKey(temp)) {
                char originalChar = reverseHuffmanCodeMap.get(temp);
                decodedText.append(originalChar);
                temp = ""; // Reset buffer
            }
        }
        return decodedText.toString();
    }
    
}