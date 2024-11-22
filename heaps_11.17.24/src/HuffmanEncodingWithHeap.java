public class HuffmanEncodingWithHeap{
    /**
     * creates nodes in forest for each unique value
     * @param frequencies
     * @return minHeap: forest, returns the forest that contains every node
     */
    public static MinHeap<HuffmanNode> buildForest(int[] frequencies){
        MinHeap<HuffmanNode> forest = new MinHeap();
        for(int ascii = 0; ascii < frequencies.length; ascii++){
            if(frequencies[ascii] > 0){
                HuffmanNode newNode = new HuffmanNode((char)ascii, frequencies[ascii]);
                forest.insert(newNode);
            }
        }
        return forest;
    }
    /**
     * builds the individual trees in forest with left and right pointers, until there is one root point
     * @param forest
     * @return minimum node (root)
     */
    public static HuffmanNode buildTree(MinHeap<HuffmanNode> forest){
        while(forest.size() > 1){
            HuffmanNode t1 = forest.removeMin();
            HuffmanNode t2 = forest.removeMin();
            HuffmanNode newNode = new HuffmanNode(t1.getFrequency() + t2.getFrequency());
            newNode.setLeft(t1);
            newNode.setRight(t2);
            forest.insert(newNode);
        }
        return forest.getMin();
    }
    /**
     * builds and returns an array with the frequency of the letters in the string given
     * @param words
     * @return frequency
     */
    public static int[] getFrequency(String words){
        char chars[] = words.toCharArray();
        int asciiSize = 256;
        int[] frequencies = new int[asciiSize];
        for (char c : chars) {
            frequencies[c]++;
        }
        int distinctCount = 0;
        for (int freq : frequencies) {
            if (freq > 0) {
                distinctCount++;
            }
        } 
        int[] frequenciesNew = new int[distinctCount];
        int index = 0;
        for (int freq : frequencies) {
            if (freq > 0) {
                frequenciesNew[index++] = freq; 
            }
        }
        return frequenciesNew;
    }

    public static void main(String[] args) {
        String words = "Hello World";
        int[] frequencies = getFrequency(words);
        MinHeap<HuffmanNode> forest = buildForest(frequencies);
        HuffmanNode root = buildTree(forest);
        System.out.println(root.toString());
    }
}