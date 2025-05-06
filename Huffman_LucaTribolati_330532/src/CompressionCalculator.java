import java.util.Map;

public class CompressionCalculator {
	
	static double originalBit = 0;
	static double compressedBit = 0;
	
    public static double calculateOriginalBit() {
    	// Scorre tutti gli elementi della mappa
    	for (Map.Entry<Character, Integer> entry : FrequencyCalculator.getMap().entrySet()) {
    	    
    		// Estrae il valore dalla mappa
    	    double frequency = (double) entry.getValue();

    	    // Calcola il prodotto e lo aggiunge alla somma
    	    double product = frequency * 8;
    	    originalBit += product;
    	}
    	return originalBit;
    }
    
    public static double calculateCompressedBit() {
    	// Scorre tutti gli elementi della mappa
    	for (Map.Entry<Character, Integer> entry : FrequencyCalculator.getMap().entrySet()) {
    	    
    		// Estrae il valore dalla mappa
    		char character = entry.getKey();
            double frequency = (double) entry.getValue();
            String code = HuffmanCoder.getHuffmanCodeMap().get(character);
            int codeLength = code.length();

    	    // Calcola il prodotto e lo aggiunge alla somma
    	    double product = frequency * codeLength;
    	    compressedBit += product;
    	}
    	return compressedBit;
    }
    
    public static double calculateFactor() {
    	return originalBit/compressedBit;
    }
    
    public static double percentage() {
    	return (1-compressedBit/originalBit)*100;
    }
    
}
