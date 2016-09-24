package net.txie.java;

import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class HK 
{
	public static void main(String[] args) 
	{
        HK hr = new HK();
        
        hr.algorithmicCrush();
    }
	
	
// 09/16/2016
	
	
	static Node lca(Node root,int v1,int v2)
    {
		LinkedList<Node> path1 = new LinkedList<>();
		LinkedList<Node> path2 = new LinkedList<>();
		
		if(root != null)
		{
			dfsSearch(root, v1, path1);
			dfsSearch(root, v2, path2);
		}
       
    }
	static Node dfsSearch(Node root, int v, LinkedList<Node> path)
	{
		path.add(root);

		if(root.data == v) return root;
		if(root.left != null) return dfsSearch(root.left, v, path);
		if(root.right != null) return dfsSearch(root.right, v, path);
		
		path.removeLast();
		return null;
	}
	
	
	static Node Insert(Node root,int value)
    {		
		if(root == null) 
		{
			Node newNode = new Node();
			newNode.data = value;
			return newNode;
		}
	
		if(value <= root.data)
			if(root.left != null)
				Insert(root.left, value); 
			else
			{
				Node newNode = new Node();
				newNode.data = value;
				root.left = newNode;
			}
		else
			if(root.right != null)
				Insert(root.right, value);
			else
			{
				Node newNode = new Node();
				newNode.data = value;
				root.right = newNode;
			}

		return root;
    }
	
	
	// BFS
	LinkedList<Node> ll =  new LinkedList<>();
	void LevelOrder(Node root)
	{
		if(root != null) ll.add(root);
		while(ll.size()>0)
		{
			Node node = ll.poll();
			System.out.print(String.format("%d ", node.data));
			if(node.left != null) ll.add(node.left);
			if(node.right != null) ll.add(node.right);
		}
	}
	
	
// 09/12/2016
	
	
	/*
	 * 	3
	 * 	=======
	 *	3
	 *	5 3 2
	 *	-------
	 *	3
	 *	1 2 100
	 *	-------
	 *	4
	 *	1 3 1 2
	 *
	 *	0
	 *	197
	 *	3
	 */
	public void stockMaximize()
	{
		
	}

	
// 09/11/2016
	
	
	public void algorithmicCrush()
	{
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		int n = Integer.parseInt(s.split(" ")[0]);
		int m = Integer.parseInt(s.split(" ")[1]);
		
		int[] ops = new int[m*3];
		for(int i=0; i<m; i++)
		{
			s = in.nextLine();
			ops[i*3] = Integer.parseInt(s.split(" ")[0]);
			ops[i*3+1] = Integer.parseInt(s.split(" ")[1]);
			ops[i*3+2] = Integer.parseInt(s.split(" ")[2]);			
		}
		in.close();
		
		
	}
	
	
// 09/10/2016
	
	
	public void gemstones()
	{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		if(n<=0)
		{
			in.close();
			return;
		}
		
		String[] stones = new String[n];
		for(int i=0; i<n; i++)
			stones[i] = in.nextLine();
		
		in.close();
		
		if(n==1) 
		{
			System.out.println(stones[0].length());
			System.exit(0);
		}
		
		HashMap<Character, Integer> hm = new HashMap<>();
		for(String stone : stones)
		{
			HashSet<Character> hs = new HashSet<>();
			for(int i=0; i<stone.length(); i++)
				hs.add(stone.charAt(i));
			
			Iterator<Character> it = hs.iterator();
			while(it.hasNext())
			{
				Character key = it.next();
				if(hm.containsKey(key))
					hm.put(key, hm.get(key)+1);
				else
					hm.put(key, 1);
			}
		}
		
		Iterator<Character> it = hm.keySet().iterator();
		int counter = 0;
		while(it.hasNext())
		{
			if(hm.get(it.next())==n) counter++;
		}
		
		System.out.println(counter);
	}
	
	
	public void alternatingCharacters()
	{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		if(n <= 0) 
		{
			in.close();
			return;
		}
		
		String[] testCases = new String[n];
		for(int i=0; i<n; i++)
			testCases[i] = in.nextLine();
		
		in.close();
			
		for(String s : testCases)
		{
			if(s.length() < 2) 
				System.out.println(0);
			else
			{
				char lastOne = s.charAt(0);
				int counter = 0;
				for(int i=1; i<s.length(); i++)
				{
					if(s.charAt(i) == lastOne)
						counter++;
					else
						lastOne = s.charAt(i);
				}
				System.out.println(counter);
			}		
		}
	}
	
	
	
	public int countCamelCase(String s)
	{		
		if(s.length() == 0) return 0;
		
		int count = 1;
		
		for(int i=0; i<s.length(); i++)
		{
			if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
				count++;
		}
		
		return count;
	}
	
	
	
	public void isPangram(String s)
	{
		int counter = 0;
		boolean[] flagArray = new boolean[26];
		s = s.toLowerCase();
		for(int i=0; i<s.length(); i++)
		{
			if(s.charAt(i) != ' ' && !flagArray[s.charAt(i)-'a'])
			{	
				flagArray[s.charAt(i)-'a'] = true;
				counter++;
				if(counter == 26)
					break;				
			}
		}
		if(counter == 26)
			System.out.println("pangram");
		else
			System.out.println("not pangram");
	}
	
	
	
	public void beautifulBinaryString(String B, int n)
	{
		if(B.length() != n) throw new IllegalArgumentException("Invalid Input");
		
		int counter = 0;
		for(int i=0; i<n; )
		{
			if(B.charAt(i) == '0' && i+2<B.length() && B.charAt(i+1) == '1' && B.charAt(i+2) == '0')
			{
				i = i + 3;
				counter++;
			}
			else
				i++;
		}
		
		System.out.println(counter);
	}
}




