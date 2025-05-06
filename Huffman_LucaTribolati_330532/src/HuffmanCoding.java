import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class HuffmanCoding {

	public static void main(String[] args) {

		try {
            // Mostra una finestra per selezionare il file di input
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleziona un file di testo");
            int userSelection = fileChooser.showOpenDialog(null);

            if (userSelection != JFileChooser.APPROVE_OPTION) {
                System.out.println("Nessun file selezionato. Uscita.");
                return;
            }

            File inputFile = fileChooser.getSelectedFile();
            Scanner fileScanner = new Scanner(inputFile);
            StringBuilder inputBuilder = new StringBuilder();

            // Accumula il testo in un unico oggetto di tipo StringBuilder
            // Questa sezione serve a mantenere la formattazione originale del testo
            while (fileScanner.hasNextLine()) {
                inputBuilder.append(fileScanner.nextLine()).append("\n");
            }
            fileScanner.close();

            // Elimina eventuali spazi vuoti iniziali e finali nella stringa generata
            String input = inputBuilder.toString().trim();

            // Calcolo delle frequenze e valutazione del tempo impiegato
            long startTimeFreq = System.nanoTime();
            new FrequencyCalculator();
            Map<Character, Integer> frequencyMap = FrequencyCalculator.calculateFrequency(input);
            long endTimeFreq = System.nanoTime();
            double freqDuration = (endTimeFreq - startTimeFreq)/1000000000.0;

            // Costruzione dell'albero di Huffman
            long startHuffmanCode = System.nanoTime();
            new HuffmanTree();
            HuffmanNode root = HuffmanTree.buildTree(frequencyMap);
            
            // Generazione dei codici di Huffman e valutazione del tempo impiegato
            HuffmanCoder coder = new HuffmanCoder();
            coder.generateCodes(root, "");
            Map<Character, String> huffmanCodeMap = HuffmanCoder.getHuffmanCodeMap();
            long endHuffmanCode = System.nanoTime();
            double huffDuration = (endHuffmanCode - startHuffmanCode)/1000000000.0;

            // Codifica del testo e valutazione del tempo impiegato
            long startTimeEncode = System.nanoTime();
            String encodedText = coder.encode(input);
            long endTimeEncode = System.nanoTime();
            double encodeDuration = (endTimeEncode - startTimeEncode)/1000000000.0;
        
            // Decodifica del testo e valutazione del tempo impiegato
            long startTimeDecode = System.nanoTime();
            String decodedText = coder.decode(encodedText);
            long endTimeDecode = System.nanoTime();
            double decodeDuration = (endTimeDecode - startTimeDecode)/1000000000.0;

            // Scrittura del file di output
            File outputFile = new File("output/" + inputFile.getName().replace(".txt", "_out.txt"));
            outputFile.getParentFile().mkdirs(); // Crea la directory se non esiste
            PrintWriter writer = new PrintWriter(outputFile);

            writer.println("Testo originale:\n" + input + "\n");

            writer.println("Tabella delle frequenze e codici di Huffman:");
            for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
                char character = entry.getKey();
                int frequency = entry.getValue();
                String code = huffmanCodeMap.get(character);
                writer.printf("Carattere: '%c', Frequenza: %d, Codice: %s%n", character, frequency, code);
            }
            writer.println();

            writer.printf("Tempo di calcolo tabella frequenze: " + freqDuration + "s\n");
            writer.printf("Tempo di calcolo codice di Huffman: " + huffDuration + "s\n\n");

            writer.println("Testo codificato:\n" + encodedText + "\n");
            
            writer.printf("Tempo di codifica: " + encodeDuration + "s\n");
            writer.println("Lunghezza file originale: " + CompressionCalculator.calculateOriginalBit() + " bit");
            writer.println("Lunghezza file compresso: " + CompressionCalculator.calculateCompressedBit() + " bit");
            writer.println("Fattore di compressione: " + CompressionCalculator.calculateFactor());
            writer.println(String.format("Il file è compresso del %.1f%% rispetto all'originale.", CompressionCalculator.percentage()));
            writer.println("Tempo di decodifica: " + decodeDuration + "s\n");

            writer.println("Testo decodificato:\n" + decodedText);

            writer.close();
            System.out.println("Risultati salvati in: " + outputFile.getAbsolutePath());

        } catch (FileNotFoundException e) {
            System.err.println("Errore: File non trovato.");
        } catch (@SuppressWarnings("hiding") IOException e) {
            System.err.println("Errore durante la scrittura del file di output.");
        }
	}

}
