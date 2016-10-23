package net.txie.java.cc;

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
        MyQueue<Integer> q = new MyQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println(q.peek());
        q.dequeue();
        System.out.println(q.peek());
        q.enqueue(4);
    }
    
    
// === 10/23/2016 ===
    
    
    // Trees  
    // M Tree: Huffman Decoding
    void decode(String S ,Node root){
        
    }
    
    
// === 10/22/2016 ===
    
    
    // Algorithms  Warmup
    // E Diagonal Difference
    // sums+=a[i][a.length-1-i]
    public void diagonalDiff(int[][] a){
        int sump = 0, sums = 0;
        for(int i=0; i<a.length; i++){
            sump+= a[i][i];
            sums+= a[i][a.length-i-1];
        }
        System.out.println(Math.abs(sump-sums));
    }
    
    
    // Algorithms  Warmup
    // E A Very Big Sum
    // use String.format(%.0f) to display number without exponential form
    public void aVeryBigSum(int[] arr){
        Double sum = 0.0;
        for(int i=0; i<arr.length; i++)
            sum += arr[i];
        System.out.println(String.format("%.0f", sum));
    }
    
    
// === 10/21/2016 ===    
    
    
    // CTCI And Trees
    // M Trees: Is This a Binary Search Tree?
    // Pass node with valid value range(min, max) recursively
    boolean checkBST(Node root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    boolean isBST(Node node, int min, int max){
        if(node==null) return true;
        if(node.data<=min || node.data>=max) return false;
        return isBST(node.left, min, node.data) && isBST(node.right, node.data, max);
    }
    
    
// === 10/17/2016 ===
    
    
    // CTCI
    // M Queues: A Tale of Two Stacks
    // alwasy enqueue to stackNewestOnTop
    // when peek/dequeue, return from stackOldestOnTop first,
    // if empty, pop stackNewestOnTop to stackOldestOnTop, then return
    public static class MyQueue<T> {
        Stack<T> stackNewestOnTop = new Stack<>();
        Stack<T> stackOldestOnTop = new Stack<>();

        public void enqueue(T value) { // Push onto newest stack
            stackNewestOnTop.push(value);
        }

        public T peek() {
            if(!stackOldestOnTop.isEmpty()) return stackOldestOnTop.peek();
            else if(stackNewestOnTop.isEmpty()) return null;
            while(!stackNewestOnTop.isEmpty())
                stackOldestOnTop.push(stackNewestOnTop.pop());
            return stackOldestOnTop.peek();
        }

        public T dequeue() {
            if(!stackOldestOnTop.isEmpty()) return stackOldestOnTop.pop();
            else if(stackNewestOnTop.isEmpty()) return null;
            while(!stackNewestOnTop.isEmpty())
                stackOldestOnTop.push(stackNewestOnTop.pop());
            return stackOldestOnTop.pop();
        }
    }


// === 10/08/2016 ===
    
    
    // 30 Days of Code
    // Day 29: Bitwise AND
    public static void day29(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int k = in.nextInt();
            int max=0;
            for(int i=1; i<n; i++)
                for(int j=i+1; j<=n; j++)
                    if((i&j)<k) max = Math.max(max, i&j);
            System.out.println(max);
        }  
    }
    
    
    // 30 Days of Code
    // Day 28: RegEx, Patterns, and Intro to Databases
    public static void day28(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap<String, String> hm = new HashMap<>();
        int N = in.nextInt();
        for(int a0 = 0; a0 < N; a0++){
            String firstName = in.next();
            String emailID = in.next();
            hm.put(emailID, firstName);
        }
        ArrayList<String> names = new ArrayList<>();
        for(String emailID : hm.keySet())
            if(emailID.contains("gmail.com")) names.add(hm.get(emailID));
        Collections.sort(names);
        for(String name : names)
            System.out.println(name);
    }
    
    
    // 30 Days of Code
    // Day 26: Nested Logic
    // returned earilier
    // returned same day
    // returned same month
    // returned same year
    // returned different year
    public static void fineCalculator(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int actDay = in.nextInt();
            int actMonth = in.nextInt();
            int actYear = in.nextInt();
            int dueDay = in.nextInt();
            int dueMonth = in.nextInt();
            int dueYear = in.nextInt();
            if(dueYear<actYear) System.out.println(10000);
            else if (dueYear==actYear){
                if(dueMonth<actMonth) System.out.println(500*(actMonth-dueMonth));
                else if(dueDay<actDay) System.out.println(15*(actDay-dueDay));
                else System.out.println(0);
            }
            else System.out.println(0);
        }
    }
    
    
    // 30 Days of Code
    // Day 25: Running Time and Complexity
    // 1 is not prime
    // test from 2 to Math.sqrt(n)
    // because both dividents cannot be greater than root of n
    public static void isPrime(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int numOfTestCases = in.nextInt();
            int[] nums = new int[numOfTestCases];
            for(int i=0;i <numOfTestCases; i++)
                nums[i] = in.nextInt();
            for(int i=0; i< nums.length; i++){
                if(nums[i]<=1) {System.out.println("Not prime");continue;}
                boolean isPrime= true;
                for(int j=2; j<Math.sqrt(nums[i]); j++){
                    if(nums[i]%j==0) {isPrime=false;break;}
                }
                if(isPrime) System.out.println("Prime");
                else System.out.println("Not prime");
            }
        }
    }
    
    
    // 30 Days of Code
    // Day 24: More Linked Lists
    // Two pointer
    public static ListNode removeDuplicates(ListNode head) {
        if(head==null) return null;
        if(head.next==null) return head;
        ListNode currNode = head;
        ListNode nextDiffNode = head.next;
        while(nextDiffNode!=null){
            if(currNode.val!=nextDiffNode.val){
                currNode.next=nextDiffNode;
                currNode = nextDiffNode;
                nextDiffNode = nextDiffNode.next;
            }
            else{
                if(nextDiffNode.next==null){
                    currNode.next = null;
                    break;
                }
                else
                    nextDiffNode = nextDiffNode.next;
            }
        }
        return head;
    }
    
    
    // 30 Days of Code
    // Day 23: BST Level-Order Traversal
    static void levelOrder(Node root){
        if(root==null) System.out.println("");
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            root = queue.poll();
            System.out.print(String.format("%d ",root.data));
            if(root.left!=null) queue.offer(root.left);
            if(root.right!=null) queue.offer(root.right);
        }
    }    
    
    
// === 10/01/2016 ===
    
    
    // 30 Days of Code 
    // Day 21: Generics
    // generic method signature mush include <T> before return type
    public static <T> void printArray(T[] arr){
        for(T t : arr)
            System.out.println(t);
    }
    
    
// === 09/29/2016 ===
    
    
    // Moody's Analytics University Hackathon
    // H Distinctly Colored Nodes in a Tree
    // SC:
    public static void distinctlyColoredNodes(){
        
    }
    
    
    // Moody's Analytics University Hackathon
    // H Presidential Gala
    // SC:
    public static void presidentialGala(){
        
    }
    
    
    // Moody's Analytics University Hackathon
    // M Asterisk Expressions
    // SC:
    public static void asteriskExpressions(){
        Scanner in = new Scanner(System.in);
        int numOfExps = in.nextInt();
        in.nextLine();
        String[] exps = new String[numOfExps];
        for(int i=0; i<numOfExps; i++)
            exps[i] = in.nextLine();
        
        for(String exp : exps){
            Solution.solve(exp);
        }
    }
    public static void solve(String exp){
        if(Solution.isValid(exp))
            System.out.println(String.format("%.0f", Solution.calculate(exp)));
        else
            System.out.println("Syntax Error");
    }
    public static boolean isValid(String exp){
        exp = exp.replaceAll("\\*\\*", "*");
        if(exp.charAt(0)=='*'||exp.charAt(exp.length()-1)=='*') return false;
        
        String[] ops = exp.split("\\*");
        if(ops.length==0) return false;
        for(String op : ops)
            if(op.isEmpty()) return false;
        return true;
    }
    public static double calculate(String exp){
        String[] ops = exp.replaceAll("\\*\\*", "^").split("\\*");
        double res = 1;
        for(String op : ops)
            res *= (op.contains("^") ? Solution.pow(op) : Double.parseDouble(op));
        return res%(Math.pow(10, 9)+7);
    }
    public static double pow(String exp){
        String[] ops = exp.split("\\^");
        double res = Double.parseDouble(ops[0]);
        for(int i=1; i<ops.length; i++)
            res = Math.pow(res, Double.parseDouble(ops[i]));
        return res;
    }
    
    
    // Moody's Analytics University Hackathon
    // M Small Risk Trading
    // SC: 
    public static void smallRiskTrading(){
        Scanner in = new Scanner(System.in);
        String[] nums = in.nextLine().split(" ");
        int numOfT = Integer.parseInt(nums[0]);
        int maxNumOfT = Integer.parseInt(nums[1]);
        
        String[] ps = in.nextLine().split(" ");
        double[] p = Solution.toDoubleArray(ps);
        
        String[] xs = in.nextLine().split(" ");
        double[] x = Solution.toDoubleArray(xs);
        
        String[] ys = in.nextLine().split(" ");
        double[] y = Solution.toDoubleArray(ys);
        
        for(int i=0; i<numOfT; i++)
            p[i] = Math.round(100*(p[i]*x[i] - (1-p[i])*y[i]))/100;
        
        Arrays.sort(p);
        float max = 0;
        for(int i=0; i<maxNumOfT; i++)
            if(p[numOfT-i-1]>0) max += p[numOfT-i-1];
        System.out.println(max);
    }
    public static double[] toDoubleArray(String[] s){
        double[] f = new double[s.length];
        for(int i=0; i<s.length; i++)
            f[i] = Double.parseDouble(s[i]);
        return f;
    }
    
    
    // Moody's Analytics University Hackathon
    // E Learning From the Past
    // SC: PASS
    public static void learnFromPast(){
        Scanner in = new Scanner(System.in);
        int numOfDays = in.nextInt();
        in.nextLine();
        int max = 0;
        for(int i=1; i<=numOfDays; i++){
            String[] ps = in.nextLine().split(" ");
            int mp = Solution.maxProfit(new int[]{Integer.parseInt(ps[0]), Integer.parseInt(ps[1]), Integer.parseInt(ps[2])});
            max = Math.max(max, mp);
        }
        System.out.println(max);
    }
    public static int maxProfit(int[] p){
        Arrays.sort(p);
        return p[2]+p[1];
    }
    
    
// === 09/28/2016 ===
    
    
    // CTCI: M Stacks: Balanced Brackets
    // SC: PASS
    // need to test if stack is empty at the end, in case reach string end before pop all elements from stack
    public static boolean isBalanced(String expression) {
        if(expression.length()%2==1) return false;
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<expression.length();i++){
            Character c = expression.charAt(i);
            if(c=='(' || c=='[' || c=='{'){
                stack.push(c);
                continue;
            }
            if(stack.empty()) return false;
            Character t = stack.pop();
            if((c==')' && t!='(') || (c==']' && t!='[') || (c=='}' && t!='{'))
                return false;
        }
        return true&&stack.empty();
    }
    
    
    // CTCI: E Linked Lists: Detect a Cycle
    // SC: PASS
    boolean hasCycle(ListNode head) {
        HashSet<ListNode> history = new HashSet<>();
        while(head!=null){
            if(history.contains(head)) return true;
            history.add(head);
            head = head.next;
        }
        return false;
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
    // String to array, sort, 2 pointer
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




