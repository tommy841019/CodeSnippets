package net.txie.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.List;

public class CodeSnippets
{
    public static void main(String[] args)
    {
        CodeSnippets cs = new CodeSnippets();
        long start = System.currentTimeMillis();
        
        System.out.println(cs.isPowerOfThree(2147483647));

        System.out.println(String.format("Time: %dms", System.currentTimeMillis() - start));

    }
    
  
// === 10/02/2016 ===
    
    
    // E 326. Power of Three
    // OJ: PASS
    public boolean isPowerOfThree(int n) {
        while(n!=0){
            if(n==1) return true;
            if(n%3!=0) return false;
            n = n/3;
        }
        return false;
    }    
    
    
    // E 409. Longest Palindrome
    // OJ: PASS
    // Put c and count in hashmap
    // loop through values, if divided by 2, add count
    // not divided by 2, add 1st occurrence else add count-1
    public int longestPalindrome(String s) {
        if(s.length()==0) return 0;
        if(s.length()==1) return 1;
        HashMap<Character, Integer> hm = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            Character c = s.charAt(i);
            if(hm.containsKey(c))
                hm.put(c, hm.get(c)+1);
            else
                hm.put(c, 1);
        }
        Iterator<Integer> it = hm.values().iterator();
        int count=0;
        boolean singleFlag = false;
        while(it.hasNext()){
            int i = it.next();
            if(i%2==0) count+=i;
            else{
                if(!singleFlag) {
                    count += i;
                    singleFlag=true;
                }
                else{
                    count += i-1;
                }
            }
        }
        return count;
    }    
    
    
    // E 70. Climbing Stairs
    // OJ:
    public int climbStairs(int n) {
        if(n==0) return 0;
        if(n==1) return 1;
        if(n==2) return 2;
        int count = 0;
        while(n>1){
            if(n-2>=1){
                count += 2;
                n--;
            }
            else{
                count++;
                break;
            }
        }
        return count;
    }
    
      
    // 263. Ugly Number
    // OJ: PASS
    // keep dividing if possible
    public boolean isUgly(int num) {
        if(num <= 0) return false;
        if(num == 1) return true;
        while(num%2==0)
            num = num/2;
        while(num%3==0)
            num = num/3;
        while(num%5==0)
            num = num/5;
        if(num>1) return false;
        else return true;
    }  
    
    
    // E 401. Binary Watch
    // OJ:
    // separate to hrs and minutes, then combine
    public List<String> readBinaryWatch(int num) {
    	
    	LinkedList<String> times = new LinkedList<>();
        for(int i=0;i<=num;i++)
        {
            LinkedList<String> hrs = getCombo(i, 11);
            LinkedList<String> mins = getCombo(num-i, 59);

            if(hrs == null || mins == null) return null;

            Iterator<String> hr_it = hrs.iterator();
            while(hr_it.hasNext())
            {
                    String hr = hr_it.next();
                    Iterator<String> min_it = mins.iterator();
                    while(min_it.hasNext())
                            times.add(String.format("%s:%s", hr, min_it.next()));
            }	
        }
        
        return times;
    }
    public LinkedList<String> getCombo(int n, int max)
    {
    	if(n<0 || n>8) return null;
    	LinkedList<String> combo = new LinkedList<>();
    	if(n==0)
    	{
            combo = addSumToCombo(combo, max, 0);
    	}
    	else
    	{
            int[] nums = new int[n];

            int sum = 0;
            for(int i=0; i<n; i++)
            {
                    nums[i] = (int)Math.pow(2, i);
                    sum += nums[i];
            }
            combo = addSumToCombo(combo, max, sum);

            for(int i=n; i>0; i--)
            {     		
                while(sum <= max)
                {
                        sum = 0;
                        nums[i-1] *= 2;
                        for(int j=0; j<n; j++)
                                sum += nums[j];

                        if(sum > max) break;

                        combo = addSumToCombo(combo, max, sum);
                }
            }
    	}
    	  	
    	return combo;
    }
    public LinkedList<String> addSumToCombo(LinkedList<String> combo, int max, int sum)
    {
    	if(max>12)
                combo.add(String.format("%02d", 0));
            else
                combo.add(String.format("%d", 0));
    	
    	return combo;
    }
	
    
// === 09/27/2016 ===
    
    
    // E 191. Number of 1 Bits
    // OJ: PASS
    // unsigned n, ignore sign
    public int hammingWeight(int n) {
        int count = 0;
        while(n!=0){
            if((n&1) == 1) count++;
            n=n>>>1;
        }
        return count;
    }
    
    
    // E 83. Remove Duplicates from Sorted List
    // OJ: PASS
    // two pointer
    // need pay attention to reaching end of the list
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return head;
        
        ListNode curr = head;
        ListNode next = head.next;
        while(next != null){
            if(next.val == curr.val){
                next = next.next;
                if(next == null)
                    curr.next = null;
            }
            else{
                curr.next = next;
                curr = next;
                next = next.next;
            }
        }
        
        return head;
    }   
    
    
    // E 202. Happy Number
    // OJ: PASS
    // if have sum before, end loop
    public boolean isHappy(int n) {
        if(n <= 0) return false;
        if(n == 1) return true;
        HashSet<Integer> hs = new HashSet<>();
        
        while(n != 1){
            int sum = 0;
            while(n != 0){
                sum += (int)Math.pow(n%10, 2);
                n /= 10;
            }
            n = sum;
            if(hs.contains(n))
                return false;
            else
                hs.add(n);
        }
        
        return true;
    }
    
    
    // E 231. Power of Two
    // OJ: PASS
    // Negative values, n cannot be negative
    // if n is pow of 2, then there should be only one 1 in binary
    // if n is not zero after reaching that 1, return false
    public boolean isPowerOfTwo(int n) {
        if(n<0) return false;
        boolean isPowerOfTwo = false;

        while(n != 0){
            if((n&1) == 1){
                n = n>>>1;
                if(n != 0)
                    isPowerOfTwo = false;
                else
                    isPowerOfTwo = true;
                break;
            } 
            n = n>>>1;
        }
        return isPowerOfTwo;
    }
    
    
    // E 206. Reverse Linked List
    // OJ: PASS
    // 3 pointer, pre/head/next
    // return pre at the end. NOT head, it will be null when exits loop
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return head;
        
        ListNode pre = head;
        head = head.next;
        pre.next = null;
        while(head != null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        
        return pre;
    }
    
    
    // M 15. 3Sum
    // o(N^2)
    // OJ:
    public List<List<Integer>> threeSum(int[] nums) 
    {
        return null;
    }	
	
	
    // E 13. Roman to Integer
    // OJ:
    public int romanToInt(String s) 
    {
        return 0;
    }	
	
	
    // M 12. Integer to Roman
    // OJ:
    public String intToRoman(int num) 
    {
        return "";
    }
        
	
    // M 200. Number of Islands
    // OJ: PASS
    // Removed queue to pass time limit
    private char[][] grid;
    private int rowNum;
    private int colNum;
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        
        this.grid = grid;
        this.rowNum = grid.length;
        this.colNum = grid[0].length;
        int count = 0;
        
        for(int y=0; y<rowNum; y++)
            for(int x=0; x<colNum; x++){
                if(grid[y][x] == '1'){
                    setNeighborsToZero(y, x);
                    count++;
                }
            }
        
        return count;
    }
    public void setNeighborsToZero(int y, int x){
            grid[y][x] = '0';
            if(isValidPos(y-1, x)) setNeighborsToZero(y-1, x);
            if(isValidPos(y+1, x)) setNeighborsToZero(y+1, x);
            if(isValidPos(y, x-1)) setNeighborsToZero(y, x-1);
            if(isValidPos(y, x+1)) setNeighborsToZero(y, x+1);
    }
    public boolean isValidPos(int y, int x){
        return (y>=0 && y<rowNum)
                && (x>= 0 && x<colNum)
                && (grid[y][x] == '1');
    }
	

// === 09/26/2016 ===

        
    // E 405. Convert a Number to Hexadecimal
    // OJ: PASS
    // think in binary
    // -1 -> 1111 1111 1111 1111 1111 1111 1111 1111
    // to get last digit: number & 0b1111 or 15 or 0xf)
    // StringBuilder has a reverse() function to output string in reverse order
    public String toHex(int num) {
        if(num ==0) return "0";
        
        StringBuilder sb = new StringBuilder();
        
        while(num != 0){
            int digit = num & 0xf;
            sb.append(digit < 10 ? (char)(digit+'0') : (char)('a'+digit-10));
            num = num>>>4;
        }
        
        return sb.reverse().toString();
    }
    
    
    // E 404. Sum of Left Leaves
    // OJ: PASS
    // It only includes LEAVES!!!
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        return (root.left != null && isALeaf(root.left) ? root.left.val : 0) + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
    public boolean isALeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    
    
// 09/21/2016
	
	
    // 237. Delete Node in a Linked List
    // OJ:PASS
    // shift node by 1
    public void deleteNode(ListNode node) 
    {
        while(node.next != null)
        {
        	node.val = node.next.val;
        	if(node.next.next == null) 
        		node.next = null;
        	else
        		node = node.next;
        }
    }
	
	
    //226. Invert Binary Tree
    // Recursively invert each node
    public TreeNode invertTree(TreeNode root) 
    {
    	if(root == null) return root;
    	
    	TreeNode tempNode = root.left;
    	root.left = root.right;
    	root.right = tempNode;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
	
	
// === 09/15/2016 ===
	
	
    //146. LRU Cache
    public class LRUCache
    {
        private HashMap<Integer, CacheNode> cache = new HashMap<Integer, CacheNode>();
        private int capacity;
        private CacheNode head;
        private CacheNode tail;

        public LRUCache(int capacity)
        {
                this.capacity = capacity;
        }
        public int get(int key)
        {
                if(cache.containsKey(key)) 
                {
                        CacheNode node = cache.get(key);
                        moveToHead(node);				
                        return node.val;
                }		
                else return -1;   // assume all values are >=0
        }
        public void set(int key, int val)
        {	
                if(cache.containsKey(key))
                {
                        CacheNode node = cache.get(key);	
                        moveToHead(node);							
                        node.val = val;
                }
                else
                {
                        CacheNode newNode = new CacheNode(key, val);
                        if(cache.size() == capacity) 
                        {
                                cache.remove(tail.key);
                                removeLast();					
                        }

                        addToHead(newNode);				
                        cache.put(key, newNode);
                }
        }
        public void removeLast()
        {
                if(tail.pre == null) head = tail = null; // capacity == 1
                else
                {
                        tail = tail.pre;
                        tail.next = null;	
                }				
        }
        public void moveToHead(CacheNode node)
        {
                if(node.pre == null) return;
                if(node.next == null) // tail
                {
                        tail = node.pre;
                        tail.next = null;
                        node.next = head;
                        head.pre = node;
                        head = node;
                        head.pre = null;
                }
                else
                {
                        node.pre.next = node.next;
                        node.next.pre = node.pre;
                        head.pre = node;
                        node.next = head;
                        head = node;
                        head.pre = null;
                }
        }
        public void addToHead(CacheNode newNode)
        {
                if(head == null) head = tail = newNode;
                else
                {
                        head.pre = newNode;
                        newNode.next = head;
                        head = newNode;
                }
        }
        public class CacheNode  // inner class
        {
                public int key;
                public int val;
                public CacheNode pre;
                public CacheNode next;

                public CacheNode(int key, int val)
                {
                        this.key = key;
                        this.val = val;
                }
        }
    }
	
	
// === 09/14/2016 ===
	
	
    //41. First Missing Positive
    // 1st num not 1
    // duplicate
    public int firstMissingPositive(int[] nums) 
    {
    	if(nums == null) return -1;
    	if(nums.length == 0) return 1;
    	
    	Arrays.sort(nums);
    	
    	int next = 1;
    	int pre = 2;
    	boolean isFirstFound = false;
    	for(int i=0; i<nums.length; i++)
    	{
    		if(nums[i] > 0)
    		{
    			if(!isFirstFound) 
        		{
        			if(nums[i] > 1) return 1; 
        			else 
    				{
        				isFirstFound = true;
        				pre = nums[i];
        				next += 1;
    				}
        		}
        		else
        		{
        			if(nums[i] == pre) continue;
        			if(nums[i] != next) return next;
        			else 
    				{
        				pre = nums[i];
        				next += 1;
    				}
        		}        			
    		}   		
    	}
    	return nums[nums.length-1]+1;
    }
	
	
// === 09/13/2016 ===
		
	
	//38. Count and Say
	// start from 2
	// when append, counter+s.charAt(j) will return int. So use String.format()
    public String countAndSay(int n) 
    {
    	if(n<=0) return null;
    	
    	String s = "1";
    	StringBuilder sb = new StringBuilder();
    	for(int i=2; i<=n; i++)
    	{
    		int counter = 1;
    		for(int j=0; j<s.length(); j++)
    		{
    			if(j+1<s.length())
    			{
    				if(s.charAt(j+1) == s.charAt(j)){counter++;}
    				else {sb.append(String.format("%d%c", counter, s.charAt(j)));counter=1;}
    			}
    			else
    				sb.append(String.format("%d%c", counter, s.charAt(j)));
    		}
    		s = sb.toString();
    		sb = new StringBuilder();
    	}
    	
    	return s;
    }
	
	
	//33. Search in Rotated Sorted Array
	// find pivot
	// binary search and divide array by pivot
	// pay attention to boundary
	// if sorted don't use pivot
    public int search(int[] nums, int target) 
    {
        if(nums == null) return -1;
        if(nums.length == 1) return nums[0] == target ? 0 : -1;
        
        if(nums[0] < nums[nums.length-1])
        	return this.searchTarget(nums, 0, nums.length-1, target);
        else
        {
        	int pivot = this.findPivot(nums, 0, nums.length-1);
        	if(target == nums[0]) return 0;
            if(target < nums[0]) return this.searchTarget(nums, pivot, nums.length-1, target);
            else return this.searchTarget(nums, 0, pivot, target);  
        }
              
    }
    public int searchTarget(int[] nums, int start, int end, int target)
    {
    	int mid = (start + end) / 2;
    	if(mid==start) 
    	{
    		if(nums[start]==target)
    			return start;
    		else if (nums[end]== target)
    			return end;
    		else
    			return -1;
    	}  
    	else if(target == nums[mid]) return mid;
    	else if(target > nums[mid]) return searchTarget(nums, mid, end, target);
    	else return searchTarget(nums, start, mid, target);
    }
    public int findPivot(int[] nums, int start, int end)
    {
    	int mid = (start + end) / 2;
    	if(mid==start) 
    	{
    		if(nums[mid]>nums[end])
    			return mid;
    		else
    			return -1;
    	}
    	if(nums[mid] > nums[mid+1]) return mid;
    	else if(nums[start] > nums[mid]) return findPivot(nums, start, mid);
    	else return findPivot(nums, mid, end);
    }
	
	
	//28. Implement strStr()
	// check empty string cases
    public int strStr(String haystack, String needle)
    {
        if(haystack == null || needle == null) return -1;
        if(haystack.length() == 0 && needle.length() == 0) return 0;
        if(haystack.length() != 0 && needle.length() == 0) return 0;
        if(haystack.length() < needle.length()) return -1;
        
        for(int i=0; i<=haystack.length()-needle.length(); i++)
        {
        	if(haystack.charAt(i) == needle.charAt(0))
        	{
        		for(int j=0; j<needle.length(); j++)
				{
        			if(haystack.charAt(i+j) != needle.charAt(j))
        				break;
        			else
        				if(j == needle.length()-1)
        					return i;
				}
        	}
        }
        return -1;
    }
	
	
	//20. Valid Parentheses
    public boolean isValid(String s) 
    {
        if (s == null) return false;
        if (s.length() == 0 || s.length() % 2 ==1) return false;
        
        Stack<Character> sk = new Stack<Character>();
        sk.push(s.charAt(0));
        for(int i=1; i<s.length(); i++)
        {
        	char current = s.charAt(i);
        	if(current == '(' || current == '[' || current == '{')
        		sk.push(current);
        	else
        		if(sk.empty()) 
        			return false;
        		else
        		{
        			char top = sk.pop();
        			if(top == '(' && current == ')' || top == '[' && current == ']' || top == '{' && current == '}')
        				continue;
        			else
        				return false;
        		}
        			
        	
        }
        
        return sk.empty() ? true : false;
    }
	
	
	//2. Add Two Numbers
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	// carry of the last digit
	// check null before get next
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) 
    {
    	if(l1 == null && l2 == null) return null;
    	if(l1 == null) return l2;
    	if(l2 == null) return l1;
    	
    	boolean hasCarry = false;
    	
    	ListNode ln = new ListNode(-1);
    	ListNode current = ln;
    	
    	while(l1 != null || l2 != null || hasCarry == true)
    	{
    		int v1 = (l1 == null ? 0 : l1.val);
    		int v2 = (l2 == null ? 0 : l2.val);
    		int val = v1 + v2 + (hasCarry ? 1 : 0);
    		
    		hasCarry = (val >= 10 ? true : false);
    		val = (val >=10 ? val-10 : val);
    		
    		ListNode newN = new ListNode(val);
    		current.next = newN;
    		current = newN;
    			
			l1 = l1 != null ? l1.next : null; 
			l2 = l2 != null ? l2.next : null;
    	}
    	
    	return ln.next;
    }		
	
	
	//238. Product of Array Except Self
	// from left to right, then from right to left to ripple the times result
    public int[] productExceptSelf(int[] nums) 
    {
        if(nums == null) return null;
        
        int[] sol = new int[nums.length];
        for(int i=0; i<sol.length; i++)
        	sol[i] = 1;
        
        for(int i=1; i<nums.length; i++)
        	sol[i] = sol[i-1] * nums[i-1];
        
        int right = 1;
        for(int i=nums.length-1; i>=0; i--)
        {
        	sol[i] = sol[i] * right;
        	right = right * nums[i];
        }
        
        return sol;
    }
	
	
// === 09/12/1016 ===
	
	
	//1. Two Sum
    public int[] twoSum(int[] nums, int target) 
    {
        if(nums == null) throw new IllegalArgumentException();
        
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        
        for(int i=0; i<nums.length; i++)
        {
        	if(hm.containsKey(target-nums[i]))
        		return new int[]{hm.get(target-nums[i]), i};
        	else
        		hm.put(nums[i], i);
        }
        
        return null;
    }
	
	
	//135. Candy
    // left -> right, right -> left makeup
	public int candy(int[] ratings) 
	{
		if(ratings == null) throw new IllegalArgumentException("Invalid Input");
		
		if(ratings.length == 1)	return 1;
		
		int[] candy = new int[ratings.length];
		candy[0] = 1;
		for(int i=1; i<ratings.length;i++)
			if(ratings[i]>ratings[i-1]) candy[i] = candy[i-1] + 1;
			else candy[i] = 1;
		
		for(int i=ratings.length-1; i>0; i--)
			if(ratings[i] < ratings[i-1] && candy[i] >= candy[i-1])
				candy[i-1] = candy[i] + 1;
		
		int sum = 0;
		for(int i=0; i<candy.length; i++)
			sum += candy[i];
		
		return sum;
    }
	
	
	//88. Merge Sorted Array
	// from right -> left; 
	// copy the rest if short array is done first
	public void merge(int[] nums1, int m, int[] nums2, int n) 
	{
        if(nums1 == null || nums2 == null) throw new IllegalArgumentException("Invalid Input");
        if(nums1.length < nums2.length) throw new IllegalArgumentException("Invalid Input");
        if(m == 0 && n == 0) return;
        
        if(m == 0) System.arraycopy(nums2, 0, nums1, 0, nums2.length);
        if(n == 0) return;
        
        int im=m-1, in=n-1;
        for(int j=n+m-1; im>=0 && in>=0; )
        {
        	if(nums1[im] > nums2[in])
        		nums1[j--] = nums1[im--];        		
        	else if(nums1[im] < nums2[in])
        		nums1[j--] = nums2[in--];
        	else
        	{
        		nums1[j--] = nums2[in--];
        		nums1[j--] = nums1[im--];
        	}
        }
        
		if(im < 0 && in>=0) 
			System.arraycopy(nums2, 0, nums1, 0, in+1);
    }
	
	
// === 09/09/2016 ===
	
	
	//167. Two Sum II - Input array is sorted
	// Note: there is only *ONE* solution in the array
    public int[] twoSumII(int[] numbers, int target) 
    {
        if(numbers == null) throw new IllegalArgumentException("Invalid Input");
        
        int head = 0;
        int minReq = target - numbers[numbers.length-1];
        while(numbers[head] < minReq)
        	head++;
        
        int tail = numbers.length-1;
        int maxReq = target - numbers[0];
        while(numbers[tail] > maxReq)
        	tail--;
        
        while(head < tail)
        {
        	if(numbers[head] + numbers[tail] == target) return new int[]{head+1, tail+1};
        	else if(numbers[head]+numbers[tail] < target) head++;
        	else tail--;
        }
        return new int[0];
    }	

    
// === 09/08/2016 ===
    
	
	//189. Rotate Array
    public void rotate(int[] nums, int k) 
    {
        if(nums == null) throw new IllegalArgumentException("Invalid Input");
        
        if(k != 0)
        {
        	if(k<0)
        	{
        		k = 0-k > nums.length ? nums.length-(0-k)%nums.length : nums.length + k;
        	}
        	else
        	{
        		k = ((k>nums.length) ? k%nums.length : k);
        	}
        	       	    
        	int[] array = new int[nums.length-k];
        	for(int i=0; i<array.length; i++)
        		array[i] = nums[i];
        	
        	int j = 0;
        	for(int i=nums.length-k; i<nums.length; i++)
        		nums[j++] = nums[i];
        	
        	for(int i=0; i<array.length; i++)
        		nums[k+i] = array[i];
        }
    }	
	

	//165. Compare Version Numbers
	/* 
	 * split takes a regular expression, need to escape special characters: \ ^ $ . | ? * + ( ) [ {
	 * need to take care of multiple . like: 1.0.1
	 * 1.09 is not allowed here
	*/
    public int compareVersion(String version1, String version2) 
    {
        if(version1 == null || version2 == null || version1.length() == 0 || version2.length() == 0) throw new IllegalArgumentException("Invalid Input");
        
        if(!version1.contains(".")) version1 += ".0";
        if(!version2.contains(".")) version2 += ".0";

    	String[] v1 = version1.split("[.]");
        String[] v2 = version2.split("[.]"); 
        
        int i=0;
        for(; i<Math.min(v1.length, v2.length); i++)
        {
        	if (Integer.parseInt(v1[i]) > Integer.parseInt(v2[i]))
            	return 1;
            else if (Integer.parseInt(v1[i]) < Integer.parseInt(v2[i]))
            	return -1;
        }
        
        if(v1.length > v2.length)
        {
        	for(;i<v1.length;i++)
        		if(Integer.parseInt(v1[i])>0) return 1;
        	
        	return 0;
        }
        else
        {
        	for(;i<v2.length;i++)
        		if(Integer.parseInt(v2[i])>0) return -1;
        	
        	return 0;
        }
    }	
	

 // === 09/07/2016 ===
    
    
    //8. String to Integer (atoi)
    public int myAtoi(String str) 
    {
        if(str == null || str.length() == 0) return 0;
        
        boolean isBegin = false;
        boolean isNegative = false;
        LinkedList<Character> ll = new LinkedList<Character>();
        for(int i=0; i<str.length(); i++)
        {
        	if(!isBegin && str.charAt(i) == ' ') continue;
        	
        	if(!isBegin && (str.charAt(i) == '-' || str.charAt(i) == '+' || ('0' <= str.charAt(i) && str.charAt(i) <= '9')))
        	{
        		isBegin = true;
        		if(str.charAt(i) == '-') isNegative = true;
        		else if('0' <= str.charAt(i) && str.charAt(i) <= '9') ll.add(str.charAt(i));
        		continue;
        	}
        	
        	if(isBegin && '0' <= str.charAt(i) && str.charAt(i) <= '9') ll.add(str.charAt(i));
        	else break;
        }
        
        double base = Math.pow(10, ll.size()-1);
        double sum = 0;
        Iterator<Character> it = ll.iterator();
        while(it.hasNext())
        {
        	sum = sum + (it.next()-'0') * base;
        	base = base / 10;
        }
        
        sum = isNegative ? sum * -1 : sum;
        if(isNegative)
        	sum = sum < Integer.MIN_VALUE ? Integer.MIN_VALUE : sum;
        else
        	sum = sum > Integer.MAX_VALUE ? Integer.MAX_VALUE : sum;
             
        return (int)sum ;
    } 
	
	
	//169. Majority Element
	public int majorityElement(int[] nums) 
	{
        if(nums == null || nums.length == 0) throw new IllegalArgumentException("Invalid Input");
        
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for(int i=0; i<nums.length; i++)
        {
        	int key = nums[i];
        	if(hm.containsKey(key))
        		hm.put(key, hm.get(key)+1);
        	else
        		hm.put(key, 1);
        }
        
        int result = 0;
        Iterator<Integer> it = hm.keySet().iterator();
        while(it.hasNext())
        {
        	Integer key = it.next();
        	if(hm.get(key) > nums.length/2) 
        		{
        			result = key;
        			break;
        		}
        }
        
        return result;
    }
		
		
	//217. Contains Duplicate
	public boolean containsDuplicate(int[] nums) 
	{
        if(nums == null) throw new IllegalArgumentException("Invalid Input");
        
        if(nums.length <= 1) return false;
        
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int i=0; i<nums.length; i++)
        	if(!hs.add(nums[i])) return true;
        
        return false;
    }


	//350. Intersection of Two Arrays II
	public int[] intersect(int[] nums1, int[] nums2) 
	{
		if(nums1 == null || nums2 == null) throw new IllegalArgumentException("Invalid Input");
		
		if(nums1.length == 0 || nums2.length == 0) return new int[0];
		
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		
		LinkedList<Integer> ll = new LinkedList<Integer>();
		for(int i=0, j=0; i<nums1.length && j<nums2.length;)
		{
			if(nums1[i]<nums2[j]) 
				i++;
			else if(nums1[i] > nums2[j])
				j++;
			else
			{
				ll.add(nums1[i]);
				i++;
				j++;
			}
		}
		
		if(ll.size() == 0)
			return new int[0];
		else
		{
			int[] result = new int[ll.size()];
			int i=0;
			Iterator<Integer> it = ll.iterator();
			while(it.hasNext())
			{
				result[i++] = it.next();
			}
			
			return result;
		}		
    }
		
		
	//338. Counting Bits
    public int[] countBits(int num) 
    {
        if(num < 0) throw new IllegalArgumentException("Invalid Input");
        if(num == 0) return new int[]{0};
        if(num == 1) return new int[]{0,1};
        
        int[] result = new int[num+1];
        result[0] = 0;
        result[1] = 1;
        int sum = 0;
    	int current = 0;
        for(int i=2; i<num+1; i++)
        {
        	sum = 0;
        	current = i;
        	while(current > 1)
        	{
        		sum += current % 2;
        		if(current/2 == 1) sum++;
        		current = current / 2;
        	}
        	result[i] = sum;
        }
        
        return result;
    }

	    
// === 09/06/2016 ===
    
    
	//100. Same Tree
	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 
	public boolean isSameTree(TreeNode p, TreeNode q) 
	{
		if(p == null && q == null) return true;
		if(p == null || q == null) return false;
		
		if(p.val != q.val) 
			return false;
		else
		{
			if(isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) return true;
			else return false;
		}
    }
	*/
	
	//383. Ransom Note
	public boolean canConstruct(String ransomNote, String magazine) 
	{
        if(ransomNote == null && magazine == null) return true;
        if(ransomNote == null || magazine == null) return false;
        
        HashMap<Character, Integer> magazineAsHashMap = new HashMap<Character, Integer>();
        for(int i=0; i<magazine.length(); i++)
        {
        	Character key = magazine.charAt(i);
        	if(magazineAsHashMap.containsKey(key))
        		magazineAsHashMap.put(key, magazineAsHashMap.get(key)+1);
        	else
        		magazineAsHashMap.put(key, 1);
        }
        
        for(int i=0; i<ransomNote.length(); i++)
        {
        	Character key = ransomNote.charAt(i);
        	if(!magazineAsHashMap.containsKey(key) || (magazineAsHashMap.get(key)-1 <0))
        		return false;
        	else
        		magazineAsHashMap.put(key, magazineAsHashMap.get(key)-1);
        }
        
        return true;
    }
	
	
	//171. Excel Sheet Column Number
	public int titleToNumber(String s) 
	{
        if(s == null || s.length() == 0) throw new IllegalArgumentException("Invalid Input");
        
        s = s.toUpperCase();
        
        int result = 0;
        for(int i=0; i<s.length(); i++)
        {
        	result += (s.charAt(i)-'A'+1)*Math.pow(26, s.length()-i-1);
        }
        
        return result;
    }

	
	//242. Valid Anagram
	public boolean isAnagram(String s, String t) 
	{
		if(s == null && t == null) return true;
		if(s == null || t == null) return false;
			
		if(s.length() == 0 && t.length() == 0) return true;
		if(s.length() != t.length()) return false;
		
		HashMap<Character, Integer> sHM = new HashMap<Character, Integer>();
		for(int i=0; i<s.length(); i++)
		{
			Character key = s.charAt(i);
			if(sHM.containsKey(key))
				sHM.put(key, sHM.get(key)+1);
			else
				sHM.put(key, 1);
		}
		
		for(int i=0; i<t.length(); i++)
		{
			Character key = t.charAt(i);
			if(!sHM.containsKey(key) || (sHM.get(key) - 1 < 0))
				return false;				
			else
				if(sHM.get(key) - 1 == 0)
					sHM.remove(key);
				else
					sHM.put(key, sHM.get(key) - 1);
		}
		
		if(sHM.keySet().size()==0)
			return true;
		else
			return false;	
    }
	
	
	//387. First Unique Character in a String
	public int firstUniqChar(String s) 
	{
        if(s == null) throw new IllegalArgumentException("Invalid Input");
        
        if(s.length() == 0) return -1;
        if(s.length() == 1) return 0;
        
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for(int i=0; i<s.length(); i++)
        {
        	Character key = s.charAt(i);
        	if(hm.containsKey(key))
        		hm.put(key, hm.get(key)+1);
        	else
        		hm.put(key, 1);
        }
        
        for(int i=0; i<s.length(); i++)
        {
        	if(hm.get(s.charAt(i)) == 1)
        		return i;
        }
        
        return -1;
    }
	 

// === 09/05/2016 ===
	
	
	//349. Intersection of Two Arrays
	public int[] intersection(int[] nums1, int[] nums2) 
	{
        if(nums1 == null || nums2 == null) throw new IllegalArgumentException("Invalid Input");
        
        if(nums1.length == 0 || nums2.length == 0)	
        	return new int[0];
        else
        {
        	HashSet<Integer> nums1HS = new HashSet<Integer>();
        	for(int i=0; i<nums1.length; i++)
        		nums1HS.add(nums1[i]);
        	
        	HashSet<Integer> resultHS = new HashSet<Integer>();
        	for(int i=0; i<nums2.length; i++)
        	{
        		if(nums1HS.contains(nums2[i]))
        			resultHS.add(nums2[i]);
        	}
        	int[] resultArray = new int[resultHS.size()];
        	Iterator<Integer> iterator = resultHS.iterator();
        	int counter = 0;
        	while(iterator.hasNext())
        	{
        		resultArray[counter++] = iterator.next();
        	}
        	
        	return resultArray;
        }
    }
	
	
// === 09/02/2016 ===

	
	//258. Add Digits
	public int addDigits(int num) 
	{
        if(num < 0) 
        	throw new IllegalArgumentException("Invalid Input");
        
        if(num < 10 )
        	return num;
        else
        {
        	int sum = 0;
        	while(num >= 10)
        	{
        		sum = sum + (num%10);
        		num = num / 10;
        		if(num < 10)
        		{
        			num = sum + num;
        			sum = 0;
        		}
        	}
        	return num;
        }
    }
	
	
	//104. Maximum Depth of Binary Tree
        /**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	public int maxDepth(TreeNode root) 
	{
			
        if(root == null) return 0;
        if(root.left == null && root.right == null) 
        	return 1;
        else
        {
        	int maxLeft = 0;
        	int maxRight = 0;
        	if(root.left != null)
        		maxLeft = 1 + maxDepth(root.left);
        	if(root.right != null)
        		maxRight = 1 + maxDepth(root.right);
        	return maxLeft > maxRight ? maxLeft : maxRight;
        }    
    }
     
	
	//389. Find the Difference
       /**
        * @param s
        * @param t
        * @return 
	*/
	public char findTheDifference(String s, String t) 
	{
        if(s == null || t == null) throw new IllegalArgumentException("Invalid Input");
        if(s.length() > t.length() || t.length() - s.length() != 1) throw new IllegalArgumentException("t should be greater than s by 1");
        if(s.length() == 0 && t.length() == 1) return t.charAt(0);
        
        char result = 0;
        HashMap<Character, Integer> hs = new HashMap<>();
        for(int i=0; i<s.length(); i++)
        {
        	if((int)s.charAt(i) < 128)
        	{
        		Character skey = s.charAt(i);
        		if(hs.containsKey(skey))
        			hs.put(skey, hs.get(skey)+1);
        		else
        			hs.put(skey, 1);
        		
        		Character tkey = t.charAt(i);
        		if(hs.containsKey(tkey))
        			hs.put(tkey, hs.get(tkey)+1);
        		else
        			hs.put(tkey, 1);
        		
        		if(i==s.length()-1)
        		{
        			Character key = t.charAt(i+1);
            		if(hs.containsKey(key))
            			hs.put(key, hs.get(key)+1);
            		else
            			hs.put(key, 1);
        		}      			
        	}
        }
        
        for(Character key : hs.keySet())
        {
        	if(hs.get(key)%2 == 1)
        		result = key;
        }
        
        return result;
    }


    //283. Move Zeroes
    public void moveZeroes(int[] nums) 
    {
        if(nums == null) throw new IllegalArgumentException("Invalid Input");
        
        if(nums.length >= 2){
            for(int i=nums.length-1; i>=0; i--){
                if(nums[i] == 0){
                    for(int j=i; j<nums.length-1; j++){
                        int temp = nums[j];
                        nums[j] = nums[j+1];
                        nums[j+1] = temp;
                    }
                }
            }
        }
    }
				
	
// === 09/01/2016 ===
	

    //344. Reverse String
    public String reverseString(String s) {
        if(s == null) throw new IllegalArgumentException("Invalid Input");

        else if(s.length() == 1) return s;

        else{
            StringBuilder sb = new StringBuilder();
            for(int i=s.length() - 1; i>=0; i--)
                    sb.append(s.charAt(i));
            
            return sb.toString();
        }	
    }
	
	
    //292. Nim Game
    public boolean canWinNim(int n){
        if(n <= 0) throw new IllegalArgumentException("Input not valid");
        if(n <= 3) return true;
        return n%4 != 0;
    }
	
	
    //371. Sum of Two Integers
    public int getSum(int a, int b){
        while((a&b) != 0){
                int a1 = (a&b) << 1;
                b = a^b;
                a = a1;
        }
        return a|b;
    }
	
	
    //136. Single Number
    public int singleNumber(int[] nums){
        int result = -1;

        if(nums.length == 0) throw new IllegalArgumentException("Invalid Input");

        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i =0; i<nums.length; i++){
                if(hm.containsKey(nums[i])) hm.put(nums[i], 1);
                else hm.put(nums[i], 0);
        }

        if(hm.containsValue(0)) {
            for(Map.Entry<Integer, Integer> e : hm.entrySet()){
                if(e.getValue() == 0) result = e.getKey();
            }
        }

        return result;
    }
	
}





