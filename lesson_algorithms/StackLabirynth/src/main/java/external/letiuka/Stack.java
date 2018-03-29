package external.letiuka;

public class Stack {
    class Node{
        Integer data;
        Node next;
        public Node(Integer data) {
            Node.this.data=data;
        }
    }
    Node head;
    public void push(Integer data)
    {
        Node node=new Node(data);
        node.next=head;
        head=node;
    }
    public Integer peek()
    {
        if(head!=null) return head.data;
        else return null;
    }
    public Integer pop()
    {
        Integer res = peek();
        if(head!=null) head=head.next;
        return res;
    }

}
