import java.util.*;

public class Solution{
	
	// Problem 1: Swap Adjacent Node
	// Given a linked list, swap every two adjacent nodes and return its head
	// Sample: Input: 1->2->3->4->5
	//         Output: 2->1->4->3->5
	public static ListNode swapAdjacentNode(ListNode head){
		if (head == null)
			return null;
		
		ListNode dummy = new ListNode(0);
		ListNode temp = dummy;
		while (head != null && head.next != null){
			ListNode next = head.next;
			temp.next = next;
			head.next = next.next;
			next.next = head;
			temp = head;
			head = head.next;
		}
		return dummy.next;
	}
	
	
	// Problem 2: Start of Circle 
	public static ListNode startCircle(ListNode head){
	    if (head == null) return null;
	    
	    ListNode quick = head;
	    ListNode slow = head;
	    ListNode backup = head;
	    
	    while (quick != null && quick.next != null){
	        quick = quick.next.next;
	        slow = slow.next;
	        if (slow == quick)
	            break;
	    }
	    
	    if (quick == null || quick.next == null){
	    	System.out.println("The linked list isn't a circle.");
	        return null;
	    }
	    
	    // I have a question about this part. 
	    // I think slow and fast always meet each at the head...
	    // So why not just return slow here...?
	    /*slow = backup;
	    
	    // How can they meet each other in the same chasing speed...?
	    while (slow != quick){
	        slow = slow.next;
	        quick = quick.next;
	    }*/
	    return slow;
	}
	

	// Problem 3: Sort List
	// Sample: Input: 1->3->2->null
	//         Output: 1->2->3->null	
	public static ListNode sortList(ListNode head){
		if (head == null || head.next == null)
			return head;
		
		ListNode mid = getMiddle(head);
		//Attention: the head of the right list is mid.next, not mid!!! 
		ListNode right = sortList(mid.next);
		mid.next = null;
		ListNode left = sortList(head);
		return mergeList(left, right);
	}
	

	// Problem 4: Rotate List
	// Rotate the list to the right by k(non-negative) places.
	// Sample: Input: 1->2->3->4->5, k = 2
	//         Output: 4->5->1->2->3
	public static ListNode rotateList(ListNode head, int k){
		if (head == null)
			return null;		
		
		int length = 1;
		ListNode temp = head;
		while (temp.next != null){
			length++;
			temp = temp.next;
		}
		
		k = k % length;
		if (k == 0) 
		    return head;
		
		temp.next = head;
		for(int i = 0; i < length-k; i++){
			temp = temp.next;
		}
		
		head = temp.next;
		temp.next = null;
		
		return head;
		   		
	}
	
	
	// Problem 5: Reversely List Traverse
	// Traverse the linked list reversely.
	// Sample: Input: 1->2->3-> null
	//         Output: 3->2->1-> null
	public static ListNode reverseListTraverse(ListNode head){
		if (head == null || head.next == null)
			return head;
		ListNode rehead = reverseListTraverse(head.next);
		head.next.next = head;
		head.next = null;
		
		return rehead;		
	}
	
	
	// Problem 6: Reverse Node in k Groups
	// Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
	// Sample: Input: 1->2->3->4->5
	//         Output: if k=2, 2->1->4->3->5 
	//                 if k=3, 3->2->1->4->5
	public static ListNode reversekGroups(ListNode head, int k){
		if (k <= 1)
			return head;
		
		int t = ListNode.getLength(head)/k;
		ListNode dummy = new ListNode(0);
		ListNode cur = head;
		ListNode ins = dummy;
		dummy.next = head;
		for (int i = 0; i < t; i++){
			for (int j = 0; j < k - 1; j++){
				ListNode temp = cur.next;
				cur.next = temp.next;
				temp.next = ins.next;
				ins.next = temp;
			}
			ins = cur;
			cur = cur.next;
		}
		return dummy.next;
	}
	
	// Problem 7: Reverse List
	// Reverse the linked list and return the new head.
	// Sample: Input: 1->2->3-> null
	//         Output: 3->2->1-> null
	public static ListNode reverseList(ListNode head){
		if (head == null){
			System.out.println("No LinkedList Exists!");
			return null;
		}
		
		ListNode prev = null;
		ListNode next = null;
		
		while (head.next != null){
			next = head.next;
			head.next = prev;
			
			prev = head;
			head = next;
		}
		head.next = prev;  //Attention! Don't forget the last step to build the pointer of head.next!!
		return head;
	}
	
	
	// Problem 8: Reverse List Range
	// Sample: Input: 1->2->3->4->5->null， m = 2 and n = 4
	//         Output: 1->4->3->2->5->null
	public static ListNode reverseListRange(ListNode head, int m, int n){
		if (m >= n || head == null || head.next == null)
			return head;
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		for (int i = 1; i < m - 1; i++){
			if (head == null)
				return null;
			head = head.next;
		}
		
		ListNode prev = head;
		ListNode mNode = head.next;
		ListNode nNode = mNode;
		ListNode post = nNode.next;
		
		for (int i = m; i < n; i++){
			if (post == null)
				return null;
			ListNode temp = post.next;
			post.next = nNode;
			nNode = post;
			post = temp;
		}
		prev.next = nNode;
		mNode.next = post;
		
		return dummy.next;
	}
	
	
	// Problem 9: Reorder List
	// Sample: Input: 1->2->3->4->null
	//         Output: 1->4->2->3->null
	public static ListNode reorderList(ListNode head){
		if (head == null || head.next == null)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		ListNode mid = getMiddle(head);
		ListNode right = mid.next;
		mid.next = null;		
		right = reverseList(right);
		
		while(head != null && right != null){
			ListNode temp = head.next;
			head.next = right;
			right = right.next;
			head.next.next = temp;
			head = temp;
		}
		return dummy.next;
	}	
	
	
	// Problem 10: Remove Linked List Elements
	// Sample: Input: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
	//         Output: 1 --> 2 --> 3 --> 4 --> 5
	public static ListNode removeElements(ListNode head, int val){
		if (head == null)
			return null;
		
		ListNode dummy = new ListNode(0);
		ListNode temp = dummy;
		dummy.next = head;
			
		while (head.next != null){
			if (head.value != val){
				temp.next = head;
				temp = temp.next;
				head = head.next;
			}else
				head = head.next;
		}
		
		if (head.value != val){
			temp.next = head;			
			//temp = temp.next;
			//head = head.next;
		}
		else{
			temp.next = null;
			//head = head.next;
		}		
		return dummy.next;
	}
	
	
	//Problem 11: Remove Duplicates from Unsorted List
	public static ListNode removeDupsFromUnsortedList(ListNode head){
		if (head == null)
			return null;
		
		ListNode cur = head;
		while (cur != null){
			ListNode temp = cur;
			while (temp.next != null){
				if (temp.next.value == cur.value)
					temp.next = temp.next.next;
				else
					temp = temp.next;
			}
			cur = cur.next;
		}
		return head;
	}
	
	
	//Problem 12: Remove Duplicates from Sorted List 1
	//Sample: Input: 1->1->2->3->3
	//        Output: 1->2->3
	public static ListNode removeDupsFromSortedList_1(ListNode head){
		if (head == null || head.next == null)
			return head;
		
		ListNode pre = head;
		ListNode cur = head.next;
		while (cur != null){
			if (pre.value == cur.value)
				pre.next = cur.next;
			else
				pre = pre.next;
			cur = cur.next;
		}
		return head;
	}
	
	
	//Problem 13: Remove Duplicates From Sorted List 2
	//Sample: Input: 1->2->3->3->4->4->5
	//        Output: 1->2->5
	public static ListNode removeDupsFromSortedList_2(ListNode head){
		if (head == null)
			return null;
		
		if (head.next == null || head.value!=head.next.value){
			head.next = removeDupsFromSortedList_2(head.next);
			return head;
		}
		
		while (head.next != null && head.value == head.next.value)
			head = head.next;
		return removeDupsFromSortedList_2(head.next);
	}
	
	
	// Problem 14: Partition Linked List
	// Write code to partition a linked list around a value x, 
	// such that all nodes less than x come before all nodes greater than or equal to x. 
	// If x is contained within the list, the values of x only need to be after the elements less than x.
	// Sample: Input: 3->5->8->5->10->2->1 [partition = 5]
	//         Output: 3->2->1->5->8->5->10	
	public static ListNode partitionLinkedList(ListNode head, int x){
		if (head == null)
			return null;
		
		ListNode minStart = null;
		ListNode minEnd = null;
		ListNode maxStart = null;
		ListNode maxEnd = null;
		
		while (head != null){
			ListNode next = head.next;
			head.next = null;
			
			if (head.value < x){
				if (minStart == null){
					minStart = head;
					minEnd = minStart;
				}
				else{
					minEnd.next = head;
					minEnd = head;
				}
			}
			else{
				if (maxStart == null){
					maxStart = head;
					maxEnd = maxStart;
				}
				else{
					maxEnd.next = head;
					maxEnd = head;
				}
			}
			head = next;
		}
		if (minStart == null)
			return maxStart;
		minEnd.next = maxStart;
		return minStart;		
	}
	
	
	// Problem 15: Partition List Sorted
	// Given a linked list and a value x, write a function to reorder this list 
	// such that all nodes less than x come before the nodes greater than or equal to x.
	// Sample: Input: 1->4->3->2->5->2->null, x = 3
	//         Output: 1->2->2->4->3->5->null
	public static ListNode partitionListSorted(ListNode head, int x){
		if (head == null)
			return null;
		
		ListNode min = new ListNode(-1);
		ListNode dummy_min = min;
		ListNode max = new ListNode(-1);
		ListNode dummy_max = max;
		
		while (head != null){
			ListNode next = head.next;
			head.next = null;
			if(head.value < x){
				min.next = head;
				min = min.next;
			}
			else{
				max.next = head;
				max = max.next;
			}
			head = next;
		}
		min.next = dummy_max.next;
		return dummy_min.next;
	}
	
	
	// Problem 16: Middle of List
	// Given a linked list, write a function to return the middle point of that list.
	public static ListNode getMiddle(ListNode head){
		if (head == null)
			return null;
		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != null && fast.next != null){
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	
	// Problem 17: Merge Two Lists
	// Given two sorted linked lists, write a function to merge these two lists, and return a new list which is sorted.
	public static ListNode mergeList(ListNode head1, ListNode head2){		
		// Attention: Do not forget these three judgment if we cannot guarantee 
		// there exists head1 and head2 or not!!
		if (head1 == null && head2 == null)
			return null;
		if (head1 == null)
			return head2;
		if (head2 == null)
			return head1;
		
		ListNode dummy = new ListNode(0);
		ListNode temp = dummy;
		
		while (head1 != null && head2 != null){
			if (head1.value < head2.value){
				temp.next = head1;
				head1 = head1.next;
			}else{
				temp.next = head2;
				head2 = head2.next;
			}
			temp = temp.next;
		}
		if (head1 != null)
			temp.next = head1;
		else
			temp.next = head2;

		return dummy.next;
	}
	
	
	// Problem 18: Merge K Linked List
	// Merge k sorted linked lists to be one sorted list.
	public static ListNode mergeKLists(ArrayList<ListNode> lists){
		if (lists.size() == 0)
			return null;
		
		PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(lists.size(),
				new Comparator<ListNode>(){
			public int compare(ListNode a, ListNode b){
				if (a.value > b.value)
					return 1;
				else if (a.value == b.value)
					return 0;
				else
					return -1;
			}
		});
		
		// Add first node of each list to the queue
		for (ListNode list : lists){
			if (list != null)
				q.add(list);
		}
		
		ListNode head = new ListNode(0);
		ListNode cur = head;
		
		while (q.size() > 0){
			// poll() retrieves and removes the head of the queue q.
			ListNode temp = q.poll();
			cur.next = temp;
			
			if (temp.next != null)
				q.add(temp.next);
			
			cur = cur.next;
		}
		return head.next;
		
	}
	
	// Problem 19: Kth to Last
	// Find the kth to last element of a singly linked list.
	// Sample: Input: 1->2->3->4->5, k = 2
	//         Output: 4->5
	public static ListNode kthToLast(ListNode head, int k){
		if (head == null)
			return null;
		if (k < 0)
			return null;
		
		ListNode fast = head;
		ListNode slow = head;
		
		for (int i = 0; i < k; i++){
			if (fast.next != null)
				fast = fast.next;
		}
		
		while (fast != null){
			slow = slow.next;
			fast = fast.next;
		}		
		return slow;
	}
	
	
	// Problem 20: Insertion Sort List
	public static ListNode insertSortList(ListNode head){
		if (head == null || head.next == null)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = head.next;
		head.next = null;
		while (cur != null){
			ListNode temp = dummy;
			while (temp.next != null && temp.next.value < cur.value)
				temp = temp.next;
			ListNode next = cur.next;
			cur.next = temp.next;
			temp.next = cur;
			cur = next;
		}
		return dummy.next;
	}
	
	
	// Problem 21: Copy List with Random Pointer
	// A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
	// Return a deep copy of the list.
	public static ListNode copyRandomList2(ListNode head){
		if (head == null)
			return null;
		
		// Construct new node after every old node
		ListNode cur = head;
		while (cur != null){
			ListNode newnode = new ListNode(cur.value);
			newnode.next = cur.next;
			cur.next = newnode;
			cur = cur.next.next;
		}
		
		// Set the random of the new list
		cur = head;
		while (cur != null){
			if (cur.random != null)
				// Every cur.next is the corresponding new node in new list
				cur.next.random = cur.random.next;
			cur = cur.next.next;
		}
		
		ListNode dummy = new ListNode(0);
		ListNode curNew = dummy;
		cur = head;
		while (cur != null){
			curNew.next = cur.next;
			curNew = curNew.next;
			cur.next = cur.next.next;
			cur = cur.next;
		}
		return dummy.next;
		
	}
	
	
	// Problem 22: Palindrome Linked List
	// Given a singly linked list, determine if it is a palindrome.
	public static boolean isPalindrome(ListNode head){
		if (head == null || head.next == null)
			return true;
		
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next != null && fast.next.next != null){
			fast = fast.next.next;
			slow = slow.next;
		}
		
		ListNode cur = slow.next;
		slow.next = null;
		
		// Reverse the second list
		ListNode p1 = cur;
		ListNode p2 = p1.next;
		while (p1 != null && p2 != null){
			ListNode temp = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = temp;
		}
		cur.next = null;
		
		ListNode head2 = p1;
		while (head2 != null){
			if (head.value != head2.value)
				return false;
			head = head.next;
			head2 = head2.next;
		}
		return true;
		
	}
	
	
	// Problem 23: Check Intersection
	// Given two singly linked lists, determine if the two lists intersect. Return the intersecting node.
	public static ListNode intersection(ListNode head1, ListNode head2){
		if (head1 == null || head2 == null)
			return null;
		
		int length1 = 1;
		int length2 = 1;
		
		ListNode tail1 = head1;
		ListNode tail2 = head2;
		
		while (tail1.next != null){
			length1++;
			tail1 = tail1.next;
		}
		while (tail2.next != null){
			length2++;
			tail2 = tail2.next;
		}
		
		if (tail1 != tail2)
			return null;

		ListNode longer = length1 < length2 ? head2 : head1;
		ListNode shorter = length1 < length2 ? head1 : head2;
		
		for (int i = 0; i < Math.abs(length1 - length2); i++)
			longer = longer.next;
		
		while (shorter != longer){
			shorter = shorter.next;
			longer = longer.next;
		}
		
		return longer;
	}
	
	
	
	
	// Problem 24: Check Circle
	// Sample: Input: 1->2->3->4->5, tail connects to node index 1
	public static boolean hasCircle(ListNode head){
		if (head == null)
			return false;
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next!= null){
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast){
				System.out.println("This list is a circle.");
				return true;
			}
		}
		System.out.println("This list is not a circle.");
		return false;
	}
	
	
	// Problem 25: 
	// Given two linked lists, each element of the lists is a integer. 
	// Write a function to return a new list, which is the “sum” of the given two lists.
	// Part a. Given input (7->1->6) + (5->9->2), output 2->1->9. 
	// Part b. Given input (6->1->7) + (2->9->5), output 9->1->2.
	public static ListNode addList1(ListNode head1, ListNode head2){
		int num1 = 0, count1 = 0;
		int num2 = 0, count2 = 0;
		int result = 0;
		
		while (head1 != null){
			num1 += head1.value * Math.pow(10, count1);
			count1++;
			head1 = head1.next;
		}
		
		while (head2 != null){
			num2 += head2.value * Math.pow(10, count2);
			count2++;
			head2 = head2.next;
		}		
		result = num1 + num2;
		
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		while (result > 0){
			cur.next = new ListNode(result % 10);
			cur = cur.next;
			result /= 10;
		}	
		return dummy.next;
	}
	
	public static ListNode addList2(ListNode head1, ListNode head2){
		int num1 = 0, num2 = 0;
		int result = 0;
		
		while (head1 != null){
			num1 = num1 * 10 + head1.value;
			head1 = head1.next;
		}
		while (head1 != null){
			num2 = num2 * 10 + head2.value;
			head2 = head2.next;
		}
		result = num1 + num2;
		
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		while (result > 0){
			ListNode temp = new ListNode(result % 10);
			temp.next = cur.next;
			cur.next = temp;
			result /= 10;
		}
		return dummy.next;
	}









}
