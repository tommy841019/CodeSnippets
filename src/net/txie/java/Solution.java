package net.txie.java;

import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;


public class Solution 
{
    public Solution(){}
    
    public static void main(String[] args) 
    {
        Solution hr = new Solution();
        int[] a = arrayLeftRotation(new int[]{1,2,3,4,5}, 5, 3);
        for(int i=0; i<a.length; i++)
            System.out.print(String.format("%d ", a[i]));
    }
    
    
// === 09/28/2016 ===
    
    
    // CTCI: E Linked Lists: Detect a Cycle
    // SC: 
    boolean hasCycle(Node head) {
        
    }
    
    
    // CTCI: E Hash Tables: Ransom Note
    // SC: PASS
    Map<String, Integer> magazineMap;
    Map<String, Integer> noteMap;
    public Solution(String magazine, String note) {
        magazineMap = new HashMap<>();
        for(String s : magazine.split(" "))
            if(magazineMap.containsKey(s))
                magazineMap.put(s, magazineMap.get(s)+1);
            else
                magazineMap.put(s, 1);
        
        noteMap = new HashMap<>();
        for(String s : note.split(" "))
            if(noteMap.containsKey(s))
                noteMap.put(s, noteMap.get(s)+1);
            else
                noteMap.put(s, 1);
    }
    public boolean solve() {
        Iterator<String> noteIt = noteMap.keySet().iterator();
        while(noteIt.hasNext()){
            String note = noteIt.next();
            if(!magazineMap.containsKey(note)) return false;
            if(magazineMap.get(note) < noteMap.get(note)) return false;
        }
        return true;
    }
    
    
    // CTCI: E Strings: Making Anagrams
    // SC: PASS
    public static int numberNeeded(String first, String second) {
        char[] f = first.toCharArray();
        char[] s = second.toCharArray();
        Arrays.sort(f);
        Arrays.sort(s);
        int counter = 0;
        for(int i=0,j=0; i<f.length || j<s.length; ){
            if(i==f.length) {counter += s.length-j;break;}
            else if(j==s.length) {counter += f.length-i;break;}
            else if(f[i]==s[j]) {i++;j++;}
            else if(f[i]<s[j]) {i++;counter++;}
            else {j++;counter++;}
        }
        return counter;
    }
    
    
    // CTCI: E Arrays: Left Rotation
    // SC: Pass
    // When copy tmp back to a, the start point is n-k
    public static int[] arrayLeftRotation(int[] a, int n, int k) {
        k = k%n;
        if(k==0) return a;
        
        int[] tmp = new int[k];
        for(int i=0; i<k; i++)
            tmp[i] = a[i];
        for(int i=k,j=0; i<n; i++)
            a[j++] = a[i];
        for(int i=0; i<k; i++)
            a[n-k+i] = tmp[i];
        
        return a;
    }
        
        
// 09/25/2016
    
    
    // World CodeSprint 7 Gridland Metro
    /**
        4 4 3
        2 2 3
        3 1 4
        4 4 4
     */
    public static void gridlandMetro()
    {
        Scanner in = new Scanner(System.in);
        String paras = in.nextLine();
        String[] para_arr = paras.split(" ");
        
        int row = Integer.parseInt(para_arr[0]);
        int col = Integer.parseInt(para_arr[1]);
        int tracks = Integer.parseInt(para_arr[2]);
        int totalgrid = row * col;
        
        HashMap<Integer, Object[]> hm = new HashMap<>();
        for(int i=0; i<tracks; i++)
        {
            String trackInfo = in.nextLine();
            String[] trackInfo_arr = trackInfo.split(" ");
            int trackRow = Integer.parseInt(trackInfo_arr[0]);
            int trackColS = Integer.parseInt(trackInfo_arr[1])-1;
            int trackColE = Integer.parseInt(trackInfo_arr[2])-1;

            if(hm.containsKey(trackRow))
            {
                Object[] trackhm = hm.get(trackRow);
                Boolean[] grid = (Boolean[])trackhm[1];
                for(int j=trackColS; j<=trackColE; j++)
                    grid[j] = true;
                
                int count = 0;
                for(int j=0; j<grid.length; j++)
                    if(grid[j]) count++;
                
                hm.put(trackRow, new Object[]{count, grid});
            }
            else
            {
                Object[] trackhm = new Object[2];
                Boolean[] grid = new Boolean[col];
                for(int j=trackColS; j<=trackColE; j++)
                    grid[j] = true;
                    
                trackhm[0] = trackColE - trackColS + 1;
                trackhm[1] = grid;
                hm.put(trackRow, trackhm);
            }
        }
        in.close();
        
        int total = 0;
        Iterator<Integer> it = hm.keySet().iterator();
        while(it.hasNext())
        {
            total += (Integer)(hm.get(it.next())[0]);
        }
        
        System.out.println(totalgrid - total);
    }
        
        
    // World CodeSprint 7  Two Characters
    public static int getMaxLength(String s)
    {
        if(s.length()<2) return 0;
        
        s = removeDoubleChars(s);
     
        HashSet<Character> hs = new HashSet<>();
        for(int i=0; i<s.length(); i++)
            hs.add(s.charAt(i));
        
        Character[] uniqueC = hs.toArray(new Character[0]);
        int max = 0;                           
        for(int i=0; i<uniqueC.length; i++)
        {
            String b = s;
            for(int j=i+1; j<uniqueC.length; j++)
            {
                for(int k=0; k<uniqueC.length; k++)
                    if(k!=i && k!=j) 
                        b = b.replaceAll(uniqueC[k]+"", "");
                
                if(isStringValid(b))
                    max = Math.max(max, b.length());
            }
        }
        
        return max;
    }
    public static String removeDoubleChars(String s)
    {
    // remove all double char from string
        boolean hasDouble = false;

        do{
            boolean status = false;
            for(int i=0; i<s.length()-1; i++)
                if(s.charAt(i) == s.charAt(i+1))
                {
                    s = s.replaceAll(s.charAt(i)+"", "");  
                    hasDouble = true;
                    status = true;
                    break;
                } 
            
            if(!status) hasDouble = false;
        } while(hasDouble);
        
        return s;
    }
    public static boolean isStringValid(String s)
    {
        boolean isOK = true;
        for(int k=0; k<s.length()-1; k++)
            if(s.charAt(k)==s.charAt(k+1)) 
                {
                    isOK=false;
                    break;
                }
        
        return isOK;
    }
    
    
// 09/24/2016
        
        
    // World CodeSprint 7  Sock Merchant
    public int getNumberOfPairs(int[] socks)
    {
        if(socks.length < 2) return 0;
        
        HashMap<Integer, Integer> sockCount = new HashMap<>();
        for(int i=0; i<socks.length; i++)
        {
            if(sockCount.containsKey(socks[i]))
                sockCount.put(socks[i], sockCount.get(socks[i])+1);
            else
                sockCount.put(socks[i],1);
        }
        
        int numberOfPairs = 0;
        Iterator<Integer> it = sockCount.values().iterator();
        while(it.hasNext())
            numberOfPairs += it.next()/2;
        
        return numberOfPairs;
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
                
                return new Node();
       
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




