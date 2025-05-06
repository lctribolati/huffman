class HuffmanNode implements Comparable<HuffmanNode> {
    
	// Attributi
	char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    // Costruttore per nodo foglia
    HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    // Costruttore per nodo interno
    HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = '\0';
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}