package corpus.preprocessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.trees.PennTreeReader;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeReader;

public class Text {
	private List<Tree> trees;
	
	public Text(){
		
	}
	
	public void loadTree(String path) throws IOException{
		this.trees = new ArrayList<Tree>();
		File modelFile = new File(path);
    	
    	BufferedReader reader = new BufferedReader(new FileReader(modelFile));
    	TreeReader tr = new PennTreeReader(reader);
    	
    	Tree tree;
    	
    	while((tree = tr.readTree()) != null){
    		trees.add(tree);
    	}
    	
    	tr.close();
    	reader.close();
	}
	
	public void extractText(String outputPath) throws IOException{
		File output = new File(outputPath);
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));
		
		for(Tree tree : trees){
			List<Tree> text = tree.getLeaves();

			for(Tree string: text){
				writer.write(string + " ");
			}
			
			writer.write("\n");
		}
		
		writer.close();
	}
	
	public static void main(String[] args) throws IOException{
		Text text = new Text();
		
		text.loadTree("corpus/8. ID-train.treebank");
		text.extractText("corpus/text.txt");
	}
}
