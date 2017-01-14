package common;

public class Stack {
	//Stack is LIFO data structure
	private int arr[];
	private int top=-1;
	public Stack(int size)
	{
		arr=new int[size];
	}
	//Push on top of stack
	public void push(int val)
	{
		arr[++top]=val;
	}
	//Delete the element on stack top and return the deleted element
	public int pop()
	{
		return arr[top--];
	}
	//Check if the stack is empty
	public boolean isEmpty()
	{
		return top==-1;
	}
}
