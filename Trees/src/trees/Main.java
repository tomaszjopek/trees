package trees;

import java.util.Random;

/**
 * Klasa zawierajaca metode main sluzaca do testow
 * @author Tomasz Jopek 220917@student.pwr.edu.pl
 *
 */
public class Main 
{
	
	public static void main(String[] args)
	{
		Random rand = new Random();
		
		Tree<Integer> tree = new Tree<Integer>();
		
		for(int i=0;i<30;i++)
		{
			int liczba = rand.nextInt(100)+1;			
			tree.insert(liczba);
		}
						
		tree.actual();
		System.out.println(tree);
		System.out.println("________________________________");
		
		System.out.println("ODKODOWANY TEKST - HUFFMAN");
		Huffman huffman = new Huffman("before coding.txt");
		huffman.encodeFile();
		huffman.printTree();
		huffman.decodeFile();
				
	}
}
