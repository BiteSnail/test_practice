interface QueueInterface<T> {
    fun enqueue(element: T): Boolean
    fun dequeue(): T?
    val count: Int
        get
    val isEmpty: Boolean
        get() = count == 0
    fun peek(): T?
}
fun <T> QueueInterface<T>.reverse(){
    val aux = Stack<T>()
    var next = this.dequeue()
    while (next != null){
        aux.push(next)
        next = this.dequeue()
    }
    var next2 = aux.pop()
    while(next2 != null){
        this.enqueue(next2)
        next2 = aux.pop()
    }
}


class ArrayListQueue<T>:QueueInterface<T> {
    private val list = arrayListOf<T>()

    override val count: Int
        get() = list.size

    override fun peek(): T? {
        return list.getOrNull(0)
    }

    override fun enqueue(element: T): Boolean {
        list.add(element)
        return true
    }

    override fun dequeue(): T? {
        return if(isEmpty) null else list.removeAt(0)
    }

    override fun toString(): String {
        return list.toString()
    }

}

class LinkedListQueue<T>:QueueInterface<T> {
    private val list = LinkedList<T>()
    private var size = 0
    override val count: Int
        get() = size

    override fun peek(): T? {
        return list.nodeAt(0)?.value
    }

    override fun enqueue(element: T): Boolean {
        list.append(element)
        size++
        return true
    }

    override fun dequeue(): T? {
        val firstNode = list.nodeAt(0)?: return null
        size--
        return list.removeHead()
    }
}

class RingBufferQueue<T>(size: Int):QueueInterface<T> {
    private val ringBuffer: RingBuffer<T> = RingBuffer(size);
    override val count:Int
        get() = ringBuffer.size
    override fun peek(): T? = ringBuffer.first()

    override fun enqueue(element: T): Boolean {
        return ringBuffer.write(element);
    }

    override fun dequeue(): T? {
        return if (isEmpty) null else ringBuffer.read();
    }

    override fun toString(): String {
        return ringBuffer.toString()
    }

}

