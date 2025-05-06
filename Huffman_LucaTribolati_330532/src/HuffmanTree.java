import java.util.Map;
import java.util.PriorityQueue;

class HuffmanTree {
    public static HuffmanNode buildTree(Map<Character, Integer> frequencyMap) {
    	
    	// Coda di priorità per costruire l'albero
    	PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>();

    	// Aggiunge tutti i caratteri come nodi foglia
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            char character = entry.getKey();
            int frequency = entry.getValue();
            HuffmanNode node = new HuffmanNode(character, frequency);
            minHeap.add(node);
        }

        // Costruisce l'albero unendo i nodi
        while (minHeap.size() > 1) {
            HuffmanNode left = minHeap.poll();
            HuffmanNode right = minHeap.poll();
            
            // Nodo genitore con somma delle frequenze
            int parentFrequency = left.frequency + right.frequency;
            HuffmanNode parent = new HuffmanNode(parentFrequency, left, right);
            
            // Aggiunge il nodo genitore alla coda
            minHeap.add(parent);
        }
        // Restituiscce la radice dell'albero
        return minHeap.poll();
    }
}