public class QueueWithTwoStacks<Item> {
    private Stack<Item> stack1;
    private Stack<Item> stack2;
    private int N;
    
    public QueueWithTwoStacks() {
        stack1 = new Stack<Item>();
        stack2 = new Stack<Item>();
        N = 0;
    }
    
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    
    public void enqueue(Item item) {
        StdOut.println("In: " + item);
        stack1.push(item);
        N++;
    }
    
    public Item dequeue() {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) throw new RuntimeException("Queue underflow!");
            StdOut.println("Info: Copy elements from stack1 to stack2...");
            while(!stack1.isEmpty()) {
                Item item = stack1.pop();
                StdOut.println("      copy " + item);
                stack2.push(item);
            }
        }
        N--;
        return stack2.pop();
    }
    
    public static void main(String[] args) {
        QueueWithTwoStacks<String> queue = new QueueWithTwoStacks<String>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        StdOut.println("Out: " + queue.dequeue());
        StdOut.println("Out: " + queue.dequeue());
        queue.enqueue("11");
        queue.enqueue("22");
        queue.enqueue("33");
        StdOut.println("Out: " + queue.dequeue());
        StdOut.println("Out: " + queue.dequeue());
        StdOut.println("Out: " + queue.dequeue());
        StdOut.println("Out: " + queue.dequeue());
        queue.enqueue("111");
        queue.enqueue("222");
        queue.enqueue("333");
        StdOut.println("Out: " + queue.dequeue());
    }
}