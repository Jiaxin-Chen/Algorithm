import java.util.*;

public class ListNode {
	
	int value;
	ListNode next;
	ListNode random;

	
	ListNode(int x){
		this.value = x;
		this.next = null;
		this.random = null;
	}
	
	public static int getLength(ListNode head){
		
		int length = 0;
		while(head != null){
			length++;
			head = head.next;
		}
		return length;
	}
	
	public static ListNode inputLinkedList(){
		System.out.println("Please Input the LinkedList and End up with a non-intvalue:");
		
		Scanner in = new Scanner(System.in);
		ListNode head = null;
		
		if(in.hasNextInt()){
			
			head = new ListNode(in.nextInt());
		}

		ListNode temp = head;
		while(in.hasNextInt()){
			temp.next = new ListNode(in.nextInt());			
			temp = temp.next;
		}
		//in.close();
		
		//Uncomment the following line if we need create a circle list.
		//temp.next = head;
		
		return head;
	}
	
	public static void outputLinkedList(ListNode head){
		if (head == null){
			System.out.println("No LinkedList Exists!");
		}
		else{
			System.out.println("\nThe Output of LinkedList After Operation:");
			while(head != null){
				System.out.print(head.value + " ");
				head = head.next;
			}
		}
	}
	
	public static void insert(ListNode node, int val){
		ListNode temp = new ListNode(val);
		if (node.next == null){
			node.next = temp;
			temp.next = null;
		}
		else{
			temp.next = node.next;
			node.next = temp;
		}
		
	}
	
	
	public static void main(String[] args){
		
		ListNode head = inputLinkedList();
		ListNode head2 = inputLinkedList();
		
		int k = 3;
		int m = 3, n = 6;
		int val = 6;
		int x = 3;
		
		//head = Solution.swapAdjacentNode(head);
		//head = Solution.startCircle(head);
		//head = Solution.sortList(head);
		//head = Solution.rotateList(head,k);
		//head = Solution.reverseListTraverse(head);
		//head = Solution.reversekGroups(head, k);
		//head = Solution.reverseList(head);
		//head = Solution.reverseListRange(head, m, n);
		//head = Solution.reorderList(head);
		//head = Solution.removeElements(head, val);
		//head = Solution.removeDupsFromUnsortedList(head);
		//head = Solution.removeDupsFromSortedList_1(head);
		//head = Solution.removeDupsFromSortedList_2(head);
		//head = Solution.partitionLinkedList(head, x);
		//head = Solution.partitionListSorted(head, x);
		//head = Solution.getMiddle(head);
		//head = Solution.mergeList(head, head2);
		//head = Solution.kthToLast(head, k);
		//head = Solution.insertSortList(head);
		//System.out.println(Solution.isPalindrome(head));
		//head = Solution.intersection(head, head2);
		//Solution.hasCircle(head);
		head = Solution.addList1(head, head2);
		
		outputLinkedList(head);
		
	}

}
