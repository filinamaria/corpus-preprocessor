package corpus.preprocessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.trees.PennTreeReader;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeReader;

public class Preprocessor {
	public static final String corpusPath = "corpus/Indonesian_Treebank.bracket";
	public static final String editedCorpusPath = "corpus/Edited_Indonesian_Treebank.bracket";
	
	public List<Tree> trees;
	
	public Preprocessor(){
		this.trees = new ArrayList<Tree>();
	}
	
	public void loadTree(String path) throws IOException{
		File modelFile = new File(path);
    	
    	BufferedReader reader = new BufferedReader(new FileReader(modelFile));
    	TreeReader tr = new PennTreeReader(reader);
    	
    	Tree tree;
    	
    	while((tree = tr.readTree()) != null){
    		trees.add(tree);
	    	//List<Tree> sentence = tree.getLeaves();
    	}
    	
    	tr.close();
    	reader.close();
	}
	
	public static void main(String [] args) throws IOException{
		Preprocessor prep = new Preprocessor();
		prep.loadTree(corpusPath);
		
		System.out.println(prep.trees.get(0).firstChild());
		
		/*File editedCorpus = new File(editedCorpusPath);
		BufferedWriter writer = null;
		
		writer = new BufferedWriter(new FileWriter(editedCorpus));
		
		for(int i = 0; i < prep.trees.size(); i++){
			writer.write(prep.trees.get(i).toString());
			writer.write("\n");
		}
		
		writer.close();*/
		
		
	}
}
