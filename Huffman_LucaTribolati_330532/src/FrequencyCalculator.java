import java.util.HashMap;
import java.util.Map;

class FrequencyCalculator {
	
	static Map<Character, Integer> frequencyMap;
	
	// Mappa per memorizzare le frequenze
    public static Map<Character, Integer> calculateFrequency(String text) {
        frequencyMap = new HashMap<>();
        
        // Scorre il testo
        for (char c : text.toCharArray()) { 
        	// Controlla se il carattere è già nella mappa
        	if (frequencyMap.containsKey(c)) {
                int count = frequencyMap.get(c);
                frequencyMap.put(c, count + 1); // Incrementa la frequenza
                
            } else {
                frequencyMap.put(c, 1); // Aggiungi con frequenza iniziale di 1
            
            }
        }
        return frequencyMap;
    }
    
    public static Map<Character, Integer> getMap(){
    	return frequencyMap;
    }
}