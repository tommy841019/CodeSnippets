package net.txie.java.cc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class CodeSnippets
{
    public static void main(String[] args)
    {
        CodeSnippets cs = new CodeSnippets();
        long start = System.currentTimeMillis();
        System.out.println(6&1);
        System.out.println(String.format("Time: %dms", System.currentTimeMillis() - start));

    }
    
    
// === 11/26/2016 ===
    
    
    // 190. Reverse Bits
    // OJ: 
    //
    public int reverseBits(int n) {
        int res = 0;
        for(int i=0; i<32; i++){
            res += (n&1) * Math.pow(2, 31-i);
            n = n>>>1;
        }
        return res;
    }
    
    
// === 11/25/2016 ===
    
    
    // 67. Add Binary
    // OJ: PASS
    // from right to left, calculate carry
    // check carry at last
    public String addBinary(String a, String b) {
        if(a.length()==0) return b;
        if(b.length()==0) return a;
        if(a.length()>b.length()){
            String tmp = a;
            a = b;
            b = tmp;
        }
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i=a.length()-1,j=b.length()-1; i>=0; i--,j--){
            int res = a.charAt(i)-'0'+b.charAt(j)-'0'+carry;
            if(res==3) {carry = 1; res = 1;}
            else if(res==2) {carry = 1; res = 0;}
            else carry = 0;
            sb.append(res);
        }
        for(int i=b.length()-a.length()-1; i>=0; i--){
            int res = b.charAt(i)-'0'+carry;
            if(res==2) {carry = 1; res = 0;}
            else carry = 0;
            sb.append(res);
        }
        if(carry==1) sb.append('1');
        return sb.reverse().toString();
    }
    
    
    // 160. Intersection of Two Linked Lists
    // OJ: PASS
    // use HashSet to track node history
    // return node if found duplicate
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null) return null;
        HashSet<ListNode> hs = new HashSet<>();
        while(headA!=null || headB!=null){
            if(hs.contains(headA))
                return headA;
            if(hs.contains(headB))
                return headB;
            if(headA==headB)
                return headA;
            if(headA!=null) {hs.add(headA);headA = headA.next;}
            if(headB!=null) {hs.add(headB);headB = headB.next;}
        }
        return null;
    }
    
    
// === 11/24/2016 ===
    
    
    // E 463. Island Perimeter
    // OJ: PASS
    // loop through array, check left right up and down
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for(int y=0; y<grid.length; y++){
            for(int x=0; x<grid[y].length; x++){
                if(grid[y][x]==1){
                    if(x-1<0 || grid[y][x-1]==0) perimeter++;
                    if(x+1>=grid[y].length || grid[y][x+1]==0) perimeter++;
                    if(y-1<0 || grid[y-1][x]==0) perimeter++;
                    if(y+1>=grid.length || grid[y+1][x]==0) perimeter++;
                }
            }
        }
        return perimeter;
    }
    
    
    // E 400. Nth Digit
    // OJ: PASS with hint
    // find first number of each section, find number of digits
    // find the number, find the nth digit
    // use chartAt to get the digit
    public int findNthDigit(int n) {
        n -= 1; // make index start with 0, so can use % to get the digit
        int first = 1, d = 1;
        while(n/first/d/9>=1){ // use divide to avoid overflow
            n -= first*d*9;
            d += 1;
            first *= 10;
        }
        return (first+n/d+"").charAt(n%d)-'0';
    }
    
    
// === 11/20/2016 ===
    
    
    // E 14. Longest Common Prefix
    // OJ: PASS with hint
    // Since to find longest, start with longest
    // if found in every str, return lcp
    // if not found in any str, reduce lcp length by 1
    public String longestCommonPrefix(String[] strs) {
        String lcp = strs.length == 0 ? "" : strs[0];
        for(String str : strs){
            while(!str.startsWith(lcp))
                lcp = lcp.substring(0, lcp.length()-1);
        }
        return lcp;
    }
    
    
// === 11/18/2016 ===
    
    
    // E 203. Remove Linked List Elements
    // OJ: PASS
    // move one by one, check if head, track curr and pre
    public ListNode removeElements(ListNode head, int val) {
        ListNode curr = head, pre = null;
        while(curr!=null){
            if(curr.val == val){
                if(pre == null){
                    head = curr.next;
                }
                else{
                    pre.next = curr.next;
                }
            } 
            else {
                pre = curr;
            }
            curr = curr.next;
        }
        return head;
    }
    
    
// === 11/17/2016 ===
    
    
    // E 58. Length of Last Word
    // OJ: PASS
    // run from end, check ' ' or reach head
    public int lengthOfLastWord(String s) {
        boolean isLastWordStarted = false;
        int start = 0;
        for(int i=s.length()-1; i>=0; i--){
            if(s.charAt(i)!=' '&&!isLastWordStarted) {isLastWordStarted=true;start=i;}  
            if(s.charAt(i)==' '&&isLastWordStarted) return start-i;
            if(i==0&&isLastWordStarted) return start+1;
        }
        return 0;
    }
    
    
    // E 234. Palindrome Linked List
    // OJ: 
    // Slow Fast Pointer
    public boolean isPalindrome(ListNode head) {

    }
    // OJ: PASS
    // put list into array, two pointer
    // O(n) time O(n) space
    public boolean isPalindromeArrayList(ListNode head) {
        if(head == null) return true;
        ArrayList<Integer> list = new ArrayList<>();
        while(head!=null){
            list.add(head.val);
            head=head.next;
        }
        for(int i=0,j=list.size()-1; i<=j; i++,j--)
            if(!list.get(i).equals(list.get(j))) return false;
        return true;
    }
    
    
    // E 459. Repeated Substring Pattern
    // OJ: PASS
    // KMP
    public boolean repeatedSubstringPattern(String str) {
        
    }
    // Brute force O(n2): test every possible from sub lenght = 1 to n/2
    public boolean repeatedSubstringPatternBruteForce(String str) {
        for(int i=0; i<str.length()/2; i++){
            String sub = str.substring(0, i+1);
            if(isFoundSubstringBruteForce(str, sub)) return true;
        }
        return false;
    }
    public boolean isFoundSubstringBruteForce(String str, String sub){
        for(int j=sub.length(); j<str.length(); j++)
                if(sub.charAt(j%sub.length())!=str.charAt(j)) return false;
        return true;
    }
    
    
// === 11/16/2016 ===
    
    
    // E 455. Assign Cookies
    // OJ: PASS
    // two pointer, s>=g -> count++
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        for(int i=0, j=0; i<g.length && j<s.length; ){
            if(g[i]<=s[j]) {count++;i++;j++;}
            else j++;
        }
        return count;
    }
    
    
    // E 223. Rectangle Area
    // OJ: PASS
    // More compact with hint
    // find max area, substract overlap if exists
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int res = (G-E)*(H-F) + (C-A)*(D-B);
        if(E>=C || G<=A || F>=D || H<=B) return res;
        else return res - (Math.min(C, G)-Math.max(A, E))*(Math.min(H, D)-Math.max(F, B));
    }
    
    
// === 11/15/2016 ===
    
    
    // E 19. Remove Nth Node From End of List
    // OJ: PASS
    // Reverse list, remove, reverse
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null || head.next==null) return null;
        ListNode reverseListNodeHead = reverseList19(head);
        ListNode curr = reverseListNodeHead, pre=null;
        while(--n>=0){
            if(n==0){
                if(pre==null) reverseListNodeHead = curr.next;
                else pre.next = curr.next;
            }
            else{
                pre = curr;
                curr = curr.next;
            }
        }
        return reverseList19(reverseListNodeHead);
    }
    public ListNode reverseList19(ListNode head){
        ListNode curr = head, next = head.next, pre = null;
        while(next != null){
            ListNode tmp = next.next;
            curr.next = pre;
            next.next = curr;
            pre = curr;
            curr = next;
            next = tmp;
        }
        return curr;
    }
    
    
// === 11/14/2016 ===
    
    
    // E 374. Guess Number Higher or Lower
    // OJ: PASS
    // binary search
    // be careful about int overflow, need to use double when calculating index
    public int guessNumber(int n) {
        return numberRange(1, n);
    }
    public int numberRange(int start, int end){
        int mid = (int)((start*1.0 + end*1.0)/2);
        int res = guess(mid);
        if(res==0) return mid;
        else if(res==1) return numberRange(mid+1, end);
        else return numberRange(start, mid-1);
    }
    // pre-defined api
    public int guess(int n){
        return 0;
    }
    
    
    // E 438. Find All Anagrams in a String
    // OJ:  PASS
    // sliding window: window size = p.length()-1
    // each iteration, move in one, check diff, move out one
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> l = new LinkedList<>();
        if(s.length()>=p.length()){
            int[] pArray = new int[26];
            int[] window = new int[26];
            int diffCounter = 0;
            for(int i=0; i<p.length(); i++)
                pArray[p.charAt(i)-'a']++;
            for(int i=0; i<p.length()-1; i++)
                window[s.charAt(i)-'a']++;
            for(int i=0; i<26; i++)
                diffCounter += Math.abs(pArray[i]-window[i]);
            for(int i=p.length()-1; i<s.length(); i++){
                diffCounter += (++window[s.charAt(i)-'a']) > pArray[s.charAt(i)-'a'] ? 1 : -1;
                if(diffCounter == 0) l.add(i-p.length()+1);
                diffCounter += (--window[s.charAt(i-p.length()+1)-'a']) < pArray[s.charAt(i-p.length()+1)-'a'] ? 1 : -1;
            }
        }
        return l;
    }
    // brute force
    // OJ: timeout
    // double space, O(n)
    public List<Integer> findAnagramsBruteForce(String s, String p) {
        List<Integer> l = new LinkedList<>();
        HashSet<String> set = getAnagramsByDoubleSpace(p);
        for(int i=0; i<s.length()-p.length()+1; i++)
            if(set.contains(s.substring(i, i+p.length()))) l.add(i);
        return l;
    }
    public HashSet<String> getAnagramsByDoubleSpace(String p){
        HashSet<String> hs = new HashSet<>();
        String s = p+p;
        for(int i=0; i<p.length(); i++){
            hs.add(s.substring(i, i+p.length()));
            StringBuilder sb = new StringBuilder(s.substring(i, i+p.length()));
            hs.add(sb.reverse().toString());
        }
        return hs;
    }
    
    
// === 11/13/2016 ===
    
    
    // E 9. Palindrome Number
    // OJ: PASS
    // calculate a new number, return if equal
    public boolean isPalindrome(int x) {
        int n = 0, x1 = x;
        while(x1>0){
            n *= 10;
            n += x1%10;
            x1 = x1/10;
        }
        return n==x;
    }
    
    
// === 11/12/2016 ===
    
    
    // E 172. Factorial Trailing Zeroes
    // OJ: PASS with Hint
    // divided by 5 to count number of 5
    public int trailingZeroes(int n) {
        int counter = 0;
        while(n>0){
            n /= 5;
            counter += n;
        }
        return counter;
    }   
    
    
    // E 441. Arranging Coins
    // OJ: PASS
    // check and reduce
    public int arrangeCoins(int n) {
        int counter = 0;
        for(int i=1; n>0; counter++, i++){
            if(n<i) break;
            else n -= i;
        }
        return counter;
    }
    
    
    // E 447. Number of Boomerangs
    // OJ: PASS
    // Calculate distance for each group, put into hashmap, count =+ n*(n-1)
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        HashMap<Double, Integer> hm = new HashMap<>();
        for(int i=0; i<points.length; i++){
            for(int j=0; j<points.length; j++){
                if(j==i) continue;
                Double key = getDistance(Math.abs(points[j][0]-points[i][0]), Math.abs(points[j][1]-points[i][1]));
                if(hm.containsKey(key))
                    hm.put(key, hm.get(key)+1);
                else
                    hm.put(key, 1);
            }
            for(Double key : hm.keySet()){
                if(hm.get(key)>0) res += hm.get(key)*(hm.get(key)-1);
            }
            hm = new HashMap<>();
        }
        return res;
    }
    public Double getDistance(int x, int y){
        return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
    }
    
       
// === 11/07/2016 ===    
    
    
    // E 453. Minimum Moves to Equal Array Elements
    // OJ: Pass
    // Subtract min, and sum
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int min = nums[0];
        for(int i=0; i<nums.length; i++)
            nums[i] = nums[i] - min;
        
        for(int i=1; i<nums.length; i++)
            nums[0] += nums[i];
        
        return nums[0];
    }
    
    
// === 10/31/2016 ===
    
    
    // E 437. Path Sum III
    // OJ: PASS
    // Pre-Order + DFS
    int total = 0;
    public int pathSum(TreeNode root, int sum) {
        path(root, sum);
        return total;
    }   
    public void path(TreeNode node, int sum){
        if(node == null) return;
        total += countPathByNode(node, sum, 0, 0);
        path(node.left, sum);
        path(node.right, sum);
    }
    public int countPathByNode(TreeNode node, int sum, int currSum, int count){
        if(node==null) return count;
        currSum += node.val;
        if(sum==currSum) count++;
        count = countPathByNode(node.left, sum, currSum, count);
        count = countPathByNode(node.right, sum, currSum, count);
        return count;
    }
    // BFS + DFS
    public int pathSumBFS(TreeNode root, int sum) {
        if(root==null) return 0;
        int number = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode node = q.remove();
            if(node==null) continue;
            q.offer(node.left);
            q.offer(node.right);
            number += countPath(node, sum, 0, 0);
        }
        return number;
    }   
    public int countPath(TreeNode node, int sum, int currSum, int count){
        if(node==null) return count;
        currSum += node.val;
        if(sum==currSum) count++;
        count = countPath(node.left, sum, currSum, count);
        count = countPath(node.right, sum, currSum, count);
        return count;
    }

    
// === 10/26/2016 ===
    
    
    // E 111. Minimum Depth of Binary Tree
    // OJ: PASS Could be more concise
    // BFS
    public int minDepth(TreeNode root) {
        int minDepth = 0;
        if(root==null) return minDepth;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);
        while(!q.isEmpty()){
            TreeNode node = q.remove();
            if(node!=null){
                if(node.left==null&&node.right==null) return ++minDepth;
                if(node.left!=null) q.offer(node.left);
                if(node.right!=null) q.offer(node.right);
            }
            else{
                if(q.size()==1&&q.peek()==null) {++minDepth;break;}
                else {++minDepth;q.offer(null);}
            }
        }
        return minDepth;
    }

    
// === 10/25/2016 ===
    
    
    // E 412. Fizz Buzz
    // OJ: PASS
    public List<String> fizzBuzz(int n) {
        List<String> list = new LinkedList<>();
        String out = "";
        for(int i=1; i<=n; i++){
            if(i%3==0) out+="Fizz";
            if(i%5==0) out+="Buzz";
            if(out.length()==0) list.add(i+"");
            else list.add(out);
            out="";
        }
        return list;
    }
    
    
    // E 112. Path Sum
    // OJ: PASS
    // return left==null&&right==null&&sum==currentSum+root.val || left || right
    public boolean hasPathSum(TreeNode root, int sum) {
        return hasPath(root, sum, 0);
    }
    public boolean hasPath(TreeNode root, int sum, int currentSum){
        if(root==null) return false;
        return (root.left==null&&root.right==null&&sum==currentSum+root.val)
                ||hasPath(root.left, sum, currentSum+root.val)
                ||hasPath(root.right, sum, currentSum+root.val);
    }
    
    
    // E 257. Binary Tree Paths
    // OJ: PASS
    // dfs, keep list of path and current path
    // if leaf, add current path to path list
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList<>();
        dfs(root, paths, new LinkedList<Integer>());
        return paths; 
    }
    public void dfs(TreeNode root, List<String> paths, List<Integer> currPath){
        if(root==null) return;
        currPath.add(root.val);
        if(root.left==null && root.right==null) {paths.add(pathToString(currPath));return;}
        if(root.left!=null){
            dfs(root.left, paths, currPath);
            currPath.remove(currPath.size()-1);
        }
        if(root.right!=null){
            dfs(root.right, paths, currPath);
            currPath.remove(currPath.size()-1);
        }
    }
    public String pathToString(List<Integer> path){
        StringBuilder pathString = new StringBuilder();
        pathString.append(path.get(0));
        for(int i=1; i<path.size(); i++)
            pathString.append("->").append(path.get(i));
        return pathString.toString();
    }
    
    
// === 10/16/2016 ===
    
    
    // E 299. Bulls and Cows
    // OJ: PASS
    // use charAt or toCharArray counter 
    // HashMap is much slower
    public String getHint(String secret, String guess) {
        if(secret==null || guess==null) return "0A0B";
        int bulls = 0, cows = 0;
        int[] counter = new int[10];
        char[] sec = secret.toCharArray();
        char[] gue = guess.toCharArray();
        for(int i=0; i<sec.length; i++){
            if(sec[i]==gue[i]) bulls++;
            else counter[sec[i]-'0']++;
        }
        for(int i=0; i<gue.length; i++){
            if(counter[gue[i]-'0']>0 && sec[i]!=gue[i]){
                cows++;
                counter[gue[i]-'0']--;
            }
        }
        return String.format("%dA%dB", bulls, cows);
    }
    public String getHintString(String secret, String guess) {
        if(secret==null || guess==null) return "0A0B";
        int bulls = 0, cows = 0;
        int[] counter = new int[10];
        for(int i=0; i<secret.length(); i++){
            if(secret.charAt(i)==guess.charAt(i)) bulls++;
            else counter[secret.charAt(i)-'0']++;
        }
        for(int i=0; i<guess.length(); i++){
            if(counter[guess.charAt(i)-'0']>0 && secret.charAt(i)!=guess.charAt(i)){
                cows++;
                counter[guess.charAt(i)-'0']--;
            }
        }
        return String.format("%dA%dB", bulls, cows);
    }
    public String getHintTwoHashMap(String secret, String guess) {
        if(secret==null || guess==null) return "0A0B";
        int bulls = 0;
        int cows = 0;
        HashMap<Character, Integer> shm = new HashMap<>();
        HashMap<Character, Integer> ghm = new HashMap<>();
        for(int i=0; i<secret.length(); i++){
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if(s==g) bulls++;
            else{
                if(shm.containsKey(s)) shm.put(s,shm.get(s)+1);
                else shm.put(s,1);
                if(ghm.containsKey(g)) ghm.put(g,ghm.get(g)+1);
                else ghm.put(g, 1);
            }
        }
        for(char c : shm.keySet()){
            if(ghm.containsKey(c))
                cows += Math.min(shm.get(c), ghm.get(c));
        }
        return String.format("%dA%dB",bulls, cows);
    }
    
    
    // E 205. Isomorphic Strings
    // OJ: PASS
    // add to hashmap, compare last position which is returned from put method
    public boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length()) return false;
        HashMap hm = new HashMap();
        for(Integer i=0; i<s.length(); i++)
            if(hm.put(s.charAt(i), i)!=hm.put(t.charAt(i)+"", i)) return false;
        return true;
    }
    
    
    // E 290. Word Pattern
    // OJ: PASS optimized with hint
    // add pattern and str to hashset same time
    // since one is char, one is string, 'a' and "a" won't cause wrong value
    // **NOTE**:    The idea is to compare is values are some object 
    //              so in for loop, need to use Integer i NOT int i 
    //              because int i will create two different Integer when put into HashMap
    //              != will not work when comparing two Integer Objects
    public boolean wordPattern(String pattern, String str) {
        if(pattern == null && str == null) return true;
        if(pattern == null || str == null) return false;
        String[] strs = str.split(" ");
        if(pattern.length()!=strs.length) return false;
        HashMap hs = new HashMap();
        for(Integer i=0; i<strs.length; i++)
            if(hs.put(pattern.charAt(i),i)!=hs.put(strs[i],i)) 
                return false;
        return true;
    }
    
    
// === 10/12/2016 ===
    
    
    // E 36. Valid Sudoku
    // OJ: PASS
    // check row, check column, check cube
    public boolean isValidSudoku(char[][] board) {
        for(int y=0; y<9; y++){
            HashSet<Character> hs = new HashSet<>();
            for(int x=0; x<9; x++)
                if(board[y][x]!='.' && !hs.add(board[y][x])) return false;
        }
        for(int x=0; x<9; x++){
            HashSet<Character> hs = new HashSet<>();
            for(int y=0; y<9; y++)
                if(board[y][x]!='.' && !hs.add(board[y][x])) return false;
        }
        for(int y=1; y<9; y+=3){
            for(int x=1; x<9; x+=3){
                HashSet<Character> hs = new HashSet<>();
                if((board[y-1][x-1]!='.' && !hs.add(board[y-1][x-1]))
                ||(board[y-1][x]!='.' && !hs.add(board[y-1][x]))
                ||(board[y-1][x+1]!='.' && !hs.add(board[y-1][x+1]))
                ||(board[y][x-1]!='.' && !hs.add(board[y][x-1]))
                ||(board[y][x]!='.' && !hs.add(board[y][x]))
                ||(board[y][x+1]!='.' && !hs.add(board[y][x+1]))
                ||(board[y+1][x-1]!='.' && !hs.add(board[y+1][x-1]))
                ||(board[y+1][x]!='.' && !hs.add(board[y+1][x]))
                ||(board[y+1][x+1]!='.' && !hs.add(board[y+1][x+1]))) return false;
            }
        }
        return true;
    }
    
    
    // E 219. Contains Duplicate II
    // OJ: PASS
    // HashMap remember last position
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(k<=0) return false;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(hm.containsKey(nums[i])){
                if((i-hm.get(nums[i]))<=k) 
                    return true;
                else
                    hm.put(nums[i], i);
            }
            else{
                hm.put(nums[i], i);
            }
        }
        return false;
    }
    
    
    // E 119. Pascal's Triangle II
    // OJ: PASS
    public List<Integer> getRow(int rowIndex) {
        if(rowIndex<0) return new ArrayList<>();
        List<Integer> pre = new ArrayList<>();
        pre.add(1);
        for(int i=1; i<=rowIndex; i++){
            List<Integer> curr = new ArrayList<>();
            curr.add(1);
            for(int j=1; j<=rowIndex; j++){
                if(j==pre.size()){
                    curr.add(1);
                    pre = curr;
                    break;
                }
                else{
                    curr.add(pre.get(j)+pre.get(j-1));
                }
            }
        }
        return pre;
    }
    
    
// === 10/11/2016 ===
    
    
    // E 396. Rotate Function
    // OJ: PASS
    // calculate new index: int ind = i+k>=A.length ? i+k-A.length : i+k;
    public int maxRotateFunction(int[] A) {
        if(A.length==0) return 0;
        Integer max = null;
        for(int k=0; k<A.length; k++){
            int sum = 0;
            for(int i=0; i<A.length; i++){
                int ind = i+k>=A.length ? i+k-A.length : i+k;
                sum += ind*A[i];
            }
            max = max==null?sum : Math.max(max, sum);
        }
        return max;
    }    
    
    
    // E 415. Add Strings
    // OJ: PASS
    // Make num1 the longer string
    // loop through num1, if exceed num2 length, set digit from num2=0, add digit1,digit2,carry
    // check final carry
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        if(num1.length()==0 && num2.length()==0) return sb.toString();
        if(num1.length()==0) return num2;
        if(num2.length()==0) return num1;
        if(num1.length()<num2.length()){
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
        }
        boolean hasCarry = false;
        for(int i_1=num1.length()-1, i_2=num2.length()-1;i_1>=0; i_1--, i_2--){
            int digSum = (num1.charAt(i_1)-'0')
                    +((i_2<0 ? 0 : num2.charAt(i_2)-'0')
                    +(hasCarry?1:0));
            sb.insert(0, digSum%10);
            hasCarry = (digSum>=10);
        }
        if(hasCarry) sb.insert(0, 1);
        return sb.toString();
    }
    
    
    // E 303. Range Sum Query - Immutable
    // OJ: PASS with hint
    // store sum in nums array by using nums[i]+=nums[i-1]
    // return nums[j]-nums[i-1]
    public class NumArray {
        int[] nums;
        public NumArray(int[] nums) {
            for(int i=1; i<nums.length; i++)
                nums[i] += nums[i-1];
            this.nums = nums;
        }
        
        public int sumRange(int i, int j) {
            return i==0? nums[j] : nums[j] - nums[i-1];
        }
    }
    
    
// === 10/10/2016 ===
    
    
    // E 155. Min Stack
    // OJ: PASS
    // stack + min 
    // calculate min when push
    // calculate min when pop if:stack is empty or pop min val
    public class MinStack {
        Stack<Integer> stack;
        Integer minVal;
        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
            minVal = null;
        }

        public void push(int x) {
            stack.push(x);
            minVal = (minVal==null?x:(Math.min(x, minVal)));
        }

        public void pop() {
            int x = stack.peek();
            stack.pop();
            if(x==minVal){
                if(!stack.isEmpty()){
                    minVal = stack.peek();
                    for(Integer i : stack){
                        minVal = Math.min(minVal, i);
                    }
                }
                else{
                    minVal = null;
                }
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minVal;
        }
    }
    
    
    // E 225. Implement Stack using Queues
    // OJ: PASS
    // Rotate queue when push
    class MyStack {
        Queue<Integer> queue = new LinkedList<>();
        // Push element x onto stack.
        public void push(int x) {
            queue.offer(x);
            shift();
        }

        // Removes the element on top of the stack.
        public void pop() {
            queue.remove();
        }

        // Get the top element.
        public int top() {
            return queue.peek();
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return queue.isEmpty();
        }
        
        public void shift(){
            for(int i=1; i<=queue.size()-1; i++)
                queue.offer(queue.remove());
        }
    }
    
    
    // E 232. Implement Queue using Stacks
    // OJ: PASS
    // Two stacks: in, out
    class MyQueue {
        Stack<Integer> in = new Stack<>();
        Stack<Integer> out = new Stack<>();
        // Push element x to the back of queue.
        public void push(int x) {
            in.push(x);
        }

        // Removes the element from in front of queue.
        public void pop() {
            if(out.isEmpty())
                while(!in.isEmpty())
                    out.push(in.pop());
            out.pop();
        }

        // Get the front element.
        public int peek() {
            if(out.isEmpty())
                while(!in.isEmpty())
                    out.push(in.pop());
            return out.peek();
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return out.isEmpty()&&in.isEmpty();
        }
    }
    

// === 10/09/2016 ===    
    
    
    // E 26. Remove Duplicates from Sorted Array
    // OJ: PASS
    // Two pointer, mark dup with max value and sord array with max mark, return length-numOfDup
    public int removeDuplicates(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return 1;
        int dupCount = 0;
        int max = nums[nums.length-1]+1;
        for(int i=0,j=1; j<nums.length;){
            if(nums[i]==nums[j]){
                nums[j]=max;
                dupCount++;
                j++;
            }
            else{
                i=j;
                j++;
            }
        }
        Arrays.sort(nums);
        return nums.length-dupCount;
    }    
    
    
    // E 102. Binary Tree Level Order Traversal
    // OJ: PASS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ll = new LinkedList<>();
        if(root==null) return ll;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        LinkedList<Integer> nodes = new LinkedList<>();
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node==null){
                ll.add(nodes);
                if(queue.isEmpty()){
                    break;
                }
                queue.offer(null);
                nodes = new LinkedList<>();
            }
            else{
                nodes.add(node.val);
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
        }
        return ll;
    }    
    
    
    // E 110. Balanced Binary Tree
    // OJ: Pass with hint
    // Current tree needs to be balanced
    // Every sub tree needs to be balanced
    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
        return Math.abs(getHeightOfTree(root.left)-getHeightOfTree(root.right))<2 
                && isBalanced(root.left) && isBalanced(root.right);
    }
    public int getHeightOfTree(TreeNode node){
        if(node==null) {return 0;}
        return 1 + Math.max(getHeightOfTree(node.left), getHeightOfTree(node.right));
    }
    
    
    // E 118. Pascal's Triangle
    // OJ: PASS
    // using ArrayList with index
    // if out-of-boundary add 1
    // if index == previous list size add 1 then break
    // else index val = previous-1 + previous
    public List<List<Integer>> generate(int numRows) {
        if(numRows<=0) return new ArrayList<>();
        List<List<Integer>> triangle = new ArrayList<>();
        for(int i=0;i<numRows;i++){
            triangle.add(new ArrayList<Integer>());
            for(int j=0;j<=i;j++){
                if(i-1<0 || j-1<0) triangle.get(i).add(1);
                else if(triangle.get(i-1).size()==j) {triangle.get(i).add(1);break;}
                else triangle.get(i).add(triangle.get(i-1).get(j-1)+triangle.get(i-1).get(j));
            }
        }
        return triangle;
    }
    
    
// === 10/08/2016 ===
    
    
    // E 204. Count Primes
    // OJ: PASS with hint
    // Sieve of Eratosthenes algorithm
    // assume all numbers are prime
    // if current is prime, set all current*times to false
    public int countPrimes(int n) {
        int counter = 0;
        boolean[] primes = new boolean[n];
        for(int i=0; i<n; i++) primes[i] = true;
        for(int i=2; i<n; i++){
            if(primes[i])
                for(int j=2; i*j<n; j++)
                    if(primes[i*j]) primes[i*j]=false;
        }
        for(int i=2;i<n;i++)
            if(primes[i]) counter++;
        return counter;
    }
    
    
// === 10/07/2016 ===
    
    
    // E 66. Plus One
    // OJ: PASS
    public int[] plusOne(int[] digits) {
        boolean hasCarry = false;
        for(int i=digits.length-1; i>=0; i--){
            if(i==digits.length-1){
                hasCarry = (digits[i]+1>=10);
                digits[i] = (digits[i]+1)%10;
            }
            else{
                int tmp = digits[i]+(hasCarry?1:0);
                hasCarry = tmp>=10;
                digits[i] = tmp%10;
            }
        }
        if(hasCarry){
            int[] tmp = new int[digits.length+1];
            tmp[0]=1;
            System.arraycopy(digits, 0, tmp, 1, digits.length);
            digits = tmp;
        }
        return digits;
    }    
    
    
    // E 101. Symmetric Tree
    // OJ: PASS with hint
    // Recursive using DFS, Iterative using BFS
    // check if current nodes equal && left vs right && right vs left
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSymmetricNodes(root.left, root.right);
    }
    public boolean isSymmetricNodes(TreeNode left, TreeNode right){
        if(left==null && right==null) return true;
        if(left==null || right==null) return false;
        return left.val==right.val && isSymmetricNodes(left.left, right.right)&&isSymmetricNodes(left.right, right.left);
    }
    
    
// === 10/06/2016 ===
    
    
    // E 27. Remove Element
    // OJ: PASS with hint
    // if element == val => shift both pointer, otherwise shift i in order to overwrite j pointer
    public int removeElement(int[] nums, int val) {
        if(nums.length==0) return 0;
        int j = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i]!=val) nums[j++]=nums[i];
        }
        return j;
    }    
	
	
    // E 13. Roman to Integer
    // OJ: PASS
    public int romanToInt(String s) 
    {
        int result = 0;
        if(s.length()==0) return result;
        HashMap<Character, Integer> hm = new HashMap<>();
        hm.put('I', 1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);
        if(s.length()==1) return hm.get(s.charAt(0));
        for(int i=0; i<s.length(); i++){
            if(i+1==s.length()) result+=hm.get(s.charAt(i));
            else{
                if(hm.get(s.charAt(i+1))>hm.get(s.charAt(i))) result -= hm.get(s.charAt(i));
                else result += hm.get(s.charAt(i));
            }
        }
        return result;
    }	
    
    
    // E 107. Binary Tree Level Order Traversal II
    // OJ: PASS
    // LinkedList.push() add element to head
    // use offer instead to put element to tail
    // Level traversal and return result in reverse order
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null) return new LinkedList<>();
        List<List<Integer>> ll = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while(!queue.isEmpty()){
            TreeNode node = queue.pop();
            if(node==null){
                if(!queue.isEmpty()){
                    ll.add(list);
                    queue.offer(null);
                    list=new LinkedList<>();
                }
                else ll.add(list);
            }       
            else{
                list.add(node.val);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
        }
        Collections.reverse(ll); 
        return ll; 
    }


    // E 198. House Robber
    // OJ: PASS with Hint
    // since don't know about follwoing numbers, need to look backward for max
    // n = max(sumof(n-2), sumof(n-3)) + n
    // return max of last two
    public int rob(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        if(nums.length==2) return Math.max(nums[0], nums[1]);
        for(int i=2; i<nums.length; i++){
            nums[i] += i==2? nums[0] : Math.max(nums[i-2], nums[i-3]);
        }
        return Math.max(nums[nums.length-1], nums[nums.length-2]);
    }
    
    
// === 10/04/2016 ===
    
    
    // E 141. Linked List Cycle
    // OJ: PASS
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> hs = new HashSet<>();
        while(head!=null){
            if(hs.contains(head)) return true;
            hs.add(head);
            head = head.next;
        }
        return false;
    }
    
    
    // E 345. Reverse Vowels of a String
    // OJ: PASS
    // Two pointer
    // return string from char array: new String(array)
    // char to lower case: Character.toLowerCase(c)
    public String reverseVowels(String s) {
        char[] sarray = s.toCharArray();
        int left = 0;
        int right = s.length()-1;
        while(left<right){
            if(isVowel(sarray[left])&&isVowel(sarray[right])){
                char tmp = sarray[left];
                sarray[left] = sarray[right];
                sarray[right] = tmp;
                left++;
                right--;
            }
            else if(isVowel(sarray[left])&&!isVowel(sarray[right]))
                right--;
            else left++;
        }
        return new String(sarray);
    }
    public boolean isVowel(char c){
        c = Character.toLowerCase(c);
        return (c=='a' || c=='e' || c=='i' || c=='o' || c=='u');
    }
    
    
    // E 24. Swap Nodes in Pairs
    // OJ: PASS
    // Inplace
    // Need to remember head and pre position
    public ListNode swapPairs_inplace(ListNode head) {
        if(head==null) return null;
        if(head.next==null) return head;
        ListNode node = head;
        ListNode pre = null;
        head = head.next;
        while(node!=null){
            if(node.next==null) break;
            ListNode tmp = node.next;
            node.next = tmp.next;
            tmp.next = node;
            if(pre!=null) pre.next = tmp;
            pre = node;
            node = node.next;
        }
        return head;
    }
    // Recursion
    // swap first two and return head recursively
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode second = head.next;
        head.next = second.next;
        second.next = head;
        head.next = swapPairs(head.next);
        return second;
    }
    
    
    // E 342. Power of Four
    // OJ: PASS
    public boolean isPowerOfFour(int num) {
        if(num<=0) return false;
        if(num==1) return true;
        while(num>1){
            if(num%4!=0) return false;
            num/=4;
        }
        return true;
    }
    
    
    // E 21. Merge Two Sorted Lists
    // OJ: PASS
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null && l2==null) return null;
        if(l1==null) return l2;
        if(l2==null) return l1;
        
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                curr.next = new ListNode(l1.val);
                curr = curr.next;
                l1 = l1.next;
            }
            else if(l1.val > l2.val){
                curr.next = new ListNode(l2.val);
                curr = curr.next;
                l2 = l2.next;
            }
            else{
                curr.next = new ListNode(l1.val);
                curr = curr.next;
                l1 = l1.next;
                curr.next = new ListNode(l2.val);
                curr = curr.next;
                l2 = l2.next;
            }
        }
        if(l1!=null) curr.next = l1;
        if(l2!=null) curr.next = l2;
        return head.next;
    }    
    
    
// === 10/03/2016 ===
    
    
    // E 235. Lowest Common Ancestor of a Binary Search Tree
    // OJ: PASS
    // number is a decendent of itself
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(p.val > q.val){
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }
        
        if(p.val <= root.val && q.val >=root.val ) return root;
        else if(p.val < q.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        else return lowestCommonAncestor(root.right, p, q);
    }
    
    
    // E 121. Best Time to Buy and Sell Stock
    // OJ: PASS
    // Scan find min for each section
    // Scan reverse to find max profit
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int max = 0;
        int[] mins = new int[prices.length];
        mins[0]=prices[0];
        for(int i=1; i<prices.length; i++)
            mins[i]=Math.min(mins[i-1],prices[i]);
        for(int j=prices.length-1; j>=0; j--){
            max = Math.max(max, prices[j]-mins[j]);
        }
        return max;
    } 
    
    
    // E 70. Climbing Stairs
    // OJ: PASS
    // DP memorization with array
    public int climbStairs(int n) {
        int[] steps = new int[n+1];
        return climb(steps, n);
    }
    public int climb(int[] steps, int n) {
        if(n==0) return 1;
        if(n>0){
            if(steps[n] != 0) return steps[n];
            steps[n] = climb(steps,n-1)+climb(steps,n-2);
            return steps[n];
        }
        return 0;
    }
    
    
    // E 70. Climbing Stairs
    // OJ: PASS
    // DP memorization with HashMap
    HashMap<Integer, Integer> hm_climb = new HashMap<>();
    public int climbStairs_hm(int n) {
        if(n<0) return 0;
        if(n==0) return 1;
        int i;
        if(hm_climb.containsKey(n-1)) i=hm_climb.get(n-1);
        else{
            i = climbStairs_hm(n-1);
            hm_climb.put(n-1,i);
        }
        int j;
        if(hm_climb.containsKey(n-2)) j=hm_climb.get(n-2);
        else{
            j = climbStairs_hm(n-2);
            hm_climb.put(n-2,j);
        }
        return i + j;
    }
    
    
    // E 401. Binary Watch
    // OJ: PASS
    // Use Integer.bitCount method
    public List<String> readBinaryWatch(int num) {
        LinkedList<String> times = new LinkedList<>();
        if(num<0 || num>8) return times;
        for(int h=0; h<=11; h++){
            for(int j=0; j<=59; j++){
                if(Integer.bitCount(j)+Integer.bitCount(h)==num)
                    times.add(String.format("%d:%02d",h,j));
            }  
        }
        return times;
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
        private final HashMap<Integer, CacheNode> cache = new HashMap<>();
        private final int capacity;
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
        
        Stack<Character> sk = new Stack<>();
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
        
        HashMap<Integer, Integer> hm = new HashMap<>();
        
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
        LinkedList<Character> ll = new LinkedList<>();
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
        
        HashMap<Integer, Integer> hm = new HashMap<>();
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
        
        HashSet<Integer> hs = new HashSet<>();
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
		
		LinkedList<Integer> ll = new LinkedList<>();
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
		
		if(ll.isEmpty())
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
        
        HashMap<Character, Integer> magazineAsHashMap = new HashMap<>();
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
		
		HashMap<Character, Integer> sHM = new HashMap<>();
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
		
		if(sHM.keySet().isEmpty())
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
        
        HashMap<Character, Integer> hm = new HashMap<>();
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
        	HashSet<Integer> nums1HS = new HashSet<>();
        	for(int i=0; i<nums1.length; i++)
        		nums1HS.add(nums1[i]);
        	
        	HashSet<Integer> resultHS = new HashSet<>();
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
