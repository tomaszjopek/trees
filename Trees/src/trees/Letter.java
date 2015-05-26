package trees;

public class Letter implements Comparable<Letter>
{
	private char letter;
	private int count;
	
	public Letter(char letter,int count)
	{
		this.setLetter(letter);
		this.setCount(count);
	}

	
	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public int compareTo(Letter o) 
	{
		if(this.letter < o.getLetter())
			return -1;
		else if(this.letter == o.getLetter())
			return 0;
		else
			return 1;		
	}
	
	public String toString()
	{
		return "||:" + letter + " " + count + ":||"; 
	}
	

}
