package trees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Huffman 
{
	
	private File input;
	private Tree<Letter> huffmanTree;
	private LinkedList<Tree<Letter>> list;
	private StringBuffer result;
	
	public Huffman(String title)
	{
		huffmanTree = null;
		input = new File(title);
		list = new LinkedList<Tree<Letter>>();
		result = new StringBuffer("");
	}
	
	public void encodeFile() 
	{		
		Scanner sc = null;
		try
		{
			sc = new Scanner(input);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		int whitespaces = 0;
				
		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			
			for(char znak = 'A';znak <= 'Z';znak++)
			{
				int licznik = 0;
				for(int i=0;i<line.length();i++)
				{
					if(line.charAt(i) == znak)
						++licznik;
				}
				
				if(licznik != 0)
				{					
					if(czyIstnieje(znak) != null)
					{
						czyIstnieje(znak).get_root().getKey().setCount(czyIstnieje(znak).get_root().getKey().getCount() + licznik);
					}
					else
					{
						Letter toEnter = new Letter(znak, licznik);
						Tree<Letter> tmpTree = new Tree<Letter>();
						tmpTree.insert(toEnter);
						list.add(tmpTree);						
					}										
				}
			}
			
			for(int i=0;i<line.length();i++)
			{
				if(line.charAt(i) == ' ')
					++whitespaces;
			}
			
			
		}
		
		if(whitespaces != 0)
		{
			Letter toEnter = new Letter(' ', whitespaces);
			Tree<Letter> tmpTree = new Tree<Letter>();
			tmpTree.insert(toEnter);
			list.add(tmpTree);		
		}
		
	
		
		createHuffmanTree();
		sc.close();
		
		try
		{
			sc = new Scanner(input);
		} 
		catch (FileNotFoundException e1) 
		{
			
			e1.printStackTrace();
		}
		
		
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter(new File("output.txt"));
			
			while(sc.hasNextLine())
			{
				result.setLength(0);
				
				String line = sc.nextLine();
				
				for(int i=0;i<line.length();i++)
				{
					char letter = line.charAt(i);
					code(letter);
				}
				
				String toSave = result.toString();
				pw.println(toSave);
																
			}				
			
		} 
		catch (IOException e) 
		{				
			e.printStackTrace();
		}
		finally
		{
			pw.close();
		}	
		
		sc.close();
	}	
	
	private Tree<Letter> czyIstnieje(char znak)
	{
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).get_root().getKey().getLetter() == znak)
				return list.get(i);
		}
		
		return null;
	}
	
	
	
	
	public void showList()
	{
		for(int i=0;i<list.size();i++)
		{
			list.get(i).inOrder(list.get(i).get_root());
			System.out.println("___________________________________________");
		}
	}
	
	public void createHuffmanTree()
	{
		while(list.size()>1)
		{
			sort();
			Letter tmpLetter = new Letter('?', list.get(0).get_root().getKey().getCount() + list.get(1).get_root().getKey().getCount());
			Tree<Letter> tmpNode = new Tree<Letter>();
			tmpNode.insert(tmpLetter);
			tmpNode.get_root().setLeftChild(list.get(0).get_root());
			tmpNode.get_root().setRightChild(list.get(1).get_root());
			list.remove(0);
			list.remove(0);
			list.add(0, tmpNode);			
		}
		
		
		huffmanTree = list.get(0);
		
	}
	
	private void sort()
	{
		for(int i=0;i<list.size();i++)
		{
			int tmp = i;
			for(int j = i+1;j<list.size();j++)
			{
				if(list.get(j).get_root().getKey().getCount() < list.get(tmp).get_root().getKey().getCount())
				{
					tmp = j;
				}
			}
						
			Tree<Letter> tmpTree = list.get(tmp);
			list.set(tmp, list.get(i));
			list.set(i, tmpTree);		
			
		}
	}
	
	
	public void printTree()
	{
		PrintTree(huffmanTree.get_root(),"");
	}
	
	private void PrintTree(Node<Letter> node, String b)
	{
	  if(node.getLeftChild() == null)
		  System.out.println(node.getKey().getLetter() + " " + b);
	  else
	  {
	    PrintTree(node.getLeftChild(), b + "0");
	    PrintTree(node.getRightChild(),b + "1");
	  }
	}
	

	private void code(char letter)
	{		
		PrintCodeTree(huffmanTree.get_root(),"", letter);			
	}
	
	private void PrintCodeTree(Node<Letter> node, String b, char letter)
	{
	  if(node.getLeftChild() == null)
	  {
		  if(node.getKey().getLetter() == letter)
		  {
			  result.append(b);
		  }
	  }
	  	
	  else
	  {
	    PrintCodeTree(node.getLeftChild(), b + "0",letter);
	    PrintCodeTree(node.getRightChild(),b + "1", letter);
	  }
	}
	
	
	public void decodeFile()
	{
		Scanner sc = null;
		
		try
		{
			sc = new Scanner(new File("output.txt"));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		int i = 0;
		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			i = 0;
			Node<Letter> node = huffmanTree.get_root();
						
			while(true)
			{
				if(i >= line.length())
					break;
				
				while(node.getLeftChild() != null && node.getRightChild() != null)
				{
					char znak = line.charAt(i);
					
					if(znak == '0')
					{
						node = node.getLeftChild();
					}
					else if(znak == '1')
					{
						node = node.getRightChild();
					}
															
					i++;
					
				}
				
				System.out.print(node.getKey().getLetter());
				node = huffmanTree.get_root();
				
			}
			
			System.out.println();
		}		
		
	}

}
