package Leetcode75.linkedList

class LinkedList {

    /***
     * We can use Fast Slow Pointers to traverse the linked list.
     *
     * The fast pointer traverses two elements each time, while the slow pointer traverses one element each time.
     * In this way, when the fast pointer has finished traversing the linked list, the slow pointer is exactly in
     * the middle of the linked list.
     *
     * We need to delete the middle node of the linked list to solve this problem. Therefore, we can use a pointer
     * pre pointer which is the previous node of slow pointer. In this way, we can delete slow through pre after traversal.
     */
    fun deleteMiddle(head: ListNode?): ListNode? {
        if(head==null || head.next==null)
            return null
        else {
            var pre: ListNode? = null
            var slow = head
            var fast = head

            while (fast != null && fast.next != null) {
                pre = slow
                slow = slow!!.next
                fast=fast!!.next!!.next
            }
            pre!!.next=slow!!.next
        }
        return head
    }
}

class ListNode(var value:Int) {
    var next: ListNode? = null
}

fun main(){

}