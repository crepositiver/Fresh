package java.lang;

/**
 * 类 Object 是类层次结构的根类。每个类都使用 Object 作为超类。
 * 所有对象（包括数组）都实现这个类的方法。
 *
 * @author  unascribed
 * @see     java.lang.Class
 * @since   JDK1.0
 */
public class Object {
    //native修饰的均是jni方法，调用c或c++库
    private static native void registerNatives();
    static {
        registerNatives();
    }

    /**
     * 返回此 Object 的运行时类。返回的 Class 对象是由所表示类的 static synchronized 方法锁定的对象。
     *
     * 实际返回的类型是 Class<? extends |X|>，其中 |X| 表示清除调用 getClass表达式中的静态类型。 
     * 例如，以下代码片段中不需要强制转换：
     *
     * Number n = 0;
     * Class<? extends Number> c = n.getClass();
     *
     * @return 此对象运行时类的 Class 对象
     * @jls 15.8.2 Class Literals
     */
    public final native Class<?> getClass();

    /**
     * 返回该对象的哈希码值。支持此方法是为了提高哈希表（例如 java.util.Hashtable 提供的哈希表）的性能。
     * 
     * hashCode 的通用规定是：
     * * 在 Java 应用程序执行期间，在对同一对象多次调用 hashCode 方法时，必须一致地返回相同的整数，前提是对象进行 equals 比较时所用的信息没有被修改。
     *   从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。
     *     
     * * 如果根据 equals 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。
     *
     * * 如果根据 equals 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。
     *   但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。
     * 
     * 合理的实践是, 由 Object 类定义的 hashCode 方法确实会针对不同的对象返回不同的整数。
     * (这一般是通过将该对象的内部地址转换成一个整数来实现的，但是 JavaTM 编程语言不需要这种实现技巧)
     *
     * @return  对象的哈希值
     * @see     java.lang.Object#equals(java.lang.Object)
     * @see     java.lang.System#identityHashCode
     */
    public native int hashCode();

    /**
     * 表示一个对象是否与另一个对象"相等"。
     *
     * equals 方法在非空对象引用上实现相等关系：
     * 
     * 自反性：对于任何非空引用值 x，x.equals(x) 都应返回 true。
     * 对称性：对于任何非空引用值 x 和 y，当且仅当 y.equals(x) 返回 true 时，x.equals(y) 返回 true。
     * 传递性：对于任何非空引用值 x、y 和 z，如果 x.equals(y) 返回 true，并且 y.equals(z) 返回 true，那么 x.equals(z) 应返回 true。
     * 一致性：对于任何非空引用值 x 和 y，多次调用 x.equals(y) 始终返回 true 或始终返回 false，前提是对象上 equals 比较中所用的信息没有被修改。
     * 对于任何非空引用值 x，x.equals(null) 都应返回 false。
     *
     * object类的equals方法实现了最大程度的鉴别对象的相等关系。
     * 即，对于任何非空引用值 x 和 y，当且仅当 x 和 y 引用同一个对象时，此方法才返回 true（x == y 为 true）。
     *
     * 注意：当此方法被重写时，通常有必要重写 hashCode 方法，
     * 以维护 hashCode 方法的常规协定，该协定声明相等对象必须具有相等的哈希码。
     *
     * @param   obj   用来比较的对象引用
     * @return  如果此对象与obj所引用的对象相同则返回true，否则返回false
     * @see     #hashCode()
     * @see     java.util.HashMap
     */
    public boolean equals(Object obj) {
        return (this == obj);
    }

    /**
     * 创建并返回此对象的一个副本。“副本”的准确含义可能依赖于对象的类。
     * 这样做的目的是，对于任何对象 x，表达式： x.clone() != x 为 true
     * 表达式：x.clone().getClass() == x.getClass() 也为 true 但这些并非必须要满足的要求。
     * 一般情况下：x.clone().equals(x) 为 true，这并非必须要满足的要求。
     *
     * 按照惯例，返回的对象应该通过调用 super.clone 获得。
     * 如果一个类及其所有的超类（Object 除外）都遵守此约定，x.clone().getClass() == x.getClass() 将是一个示例。
     *
     * 按照惯例，此方法返回的对象应该独立于该对象（正被复制的对象）。在 super.clone 返回对象之前，有必要对该对象的一个或多个字段进行修改。
     * 这意味着复制任何可变对象的内部“深层次结构”，并且用对副本的引用来替代对对象的引用。
     * 如果一个类只包含基本字段或对不变对象的引用，则不需要修改 super.clone 返回的对象中的字段。
     *
     * Object 类的 clone 方法执行特定的复制操作。首先，如果此对象的类不能实现接口 Cloneable，则会抛出 CloneNotSupportedException。
     * 注意，所有的数组都被视为实现接口 Cloneable，并且对基本类型的数组来说，clone方法返回相同的类型。
     * 否则，此方法会创建此对象的类的一个新实例，并精确地用此对象的字段内容来初始化新实例。
     * 这些字段的内容没有被自我复制。所以，此方法执行的是该对象的“浅复制”，而不“深复制”操作。
     *
     *Object 类本身不实现接口 Cloneable，所以在类为 Object 的对象上调用 clone 方法将会导致在运行时抛出异常。
     * @return     此实例的一个副本
     * @throws  CloneNotSupportedException  如果对象的类不支持 Cloneable 接口，则重写 clone 方法的子类也会抛出此异常，表示实例不能被复制。
     *
     * @see java.lang.Cloneable
     */ 
    protected native Object clone() throws CloneNotSupportedException;

    /**
     * 返回该对象的字符串表示。通常， toString 方法会返回一个“以文本方式表示”此对象的字符串。结果应是一个简明但易于读懂的信息表达式。
     * 建议所有子类都重写此方法。
     * <p>
     * Object 类的 toString 方法返回一个字符串，该字符串由此实例对象的类名、at 标记符“@”和此对象哈希码的无符号十六进制数表示组成。
     * 换句话说，该方法返回一个字符串，它的值等于：
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     *
     * @return  该对象的字符串表示
     */
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    /**
     * 唤醒在此对象监视器上等待的单个线程。如果所有线程都在此对象上等待，则会选择唤醒其中一个线程。选择是任意性的，并由实现自行决定而发生。
     * 线程通过调用其中一个 wait 方法，实现在对象的监视器上等待。
     *
     * 直到当前线程放弃此对象锁，被唤醒的线程才能执行。被唤醒的线程将以常规方式与在该对象上主动同步的其他所有线程进行竞争；
     * 例如，唤醒的线程在作为锁定此对象的下一个线程方面没有可靠的特权或劣势。
     *
     * 此方法只应由作为此对象监视器的所有者的线程来调用。
     * 通过以下三种方法之一，线程可以成为此对象监视器的所有者：
     *
     * 通过执行此对象的同步实例方法。
     * 通过执行在此对象上进行同步的 synchronized 代码块。
     * 对于 Class 类型的对象，可以通过执行该类的同步静态方法。
     * 
     * 一次只能有一个线程拥有对象的监视器。
     *
     * @throws  IllegalMonitorStateException  如果当前线程不是此对象监视器的所有者
     * @see        java.lang.Object#notifyAll()
     * @see        java.lang.Object#wait()
     */
    public final native void notify();

    /**
     * 唤醒在此对象监视器上等待的所有线程。线程通过调用 wait 方法，在对象的监视器上等待。
     *
     * 直到当前线程放弃此对象上的锁定，才能继续执行被唤醒的线程。被唤醒的线程将以常规方式与在该对象上主动同步的其他所有线程进行竞争；
     * 例如，唤醒的线程在作为锁定此对象的下一个线程方面没有可靠的特权或劣势。
     *
     * 此方法只应由作为此对象监视器的所有者的线程来调用。有关线程能够成为监视器所有者的方法的描述，请参阅 notify 方法。
     *
     * @throws  IllegalMonitorStateException  如果当前线程不是此对象监视器的所有者
     * @see        java.lang.Object#notify()
     * @see        java.lang.Object#wait()
     */
    public final native void notifyAll();

    /**
     * 直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，
     * 或者超过指定的时间量前，导致当前线程等待。
     * 
     * 当前线程必须拥有此对象监视器。
     * 
     * 此方法导致当前线程（称之为 T）将其自身放置在对象的等待集中，然后放弃此对象上的所有同步要求。
     * 出于线程调度目的，在发生以下四种情况之一前，线程 T 被禁用，且处于休眠状态：
     * 
     * 其他某个线程调用此对象的 notify 方法，并且线程 T 碰巧被任选为被唤醒的线程。
     * 其他某个线程调用此对象的 notifyAll 方法。
     * 其他某个线程中断线程 T。
     * 指定的实际时间大约过去了。但是，如果 timeout 为零，则不考虑实际时间，在获得通知前该线程将一直等待。
     * 
     * 然后，从对象的等待集中删除线程 T，并重新进行线程调度。然后，该线程以常规方式与其他线程竞争，以获得在该对象上同步的权利。
     * 一旦获得对该对象的控制权，该对象上的所有其同步声明都将被恢复到以前的状态，这就是调用 wait 方法时的情况。
     * 然后，线程 T 从 wait 方法的调用中返回。该对象和线程 T 的同步状态与调用 wait 方法时的情况完全相同。
     * 
     * A thread can also wake up without being notified, interrupted, or
     * timing out, a so-called <i>spurious wakeup</i>.  While this will rarely
     * occur in practice, applications must guard against it by testing for
     * the condition that should have caused the thread to be awakened, and
     * continuing to wait if the condition is not satisfied.  In other words,
     * waits should always occur in loops, like this one:
     * 在没有被通知、中断或超时的情况下，线程还可以唤醒一个所谓的虚假唤醒 (spurious wakeup)。
     * 虽然这种情况在实践中很少发生，但是应用程序必须通过对应该导致该线程被提醒的条件进行测试，以防止其发生。
     * 即如果不满足该条件，则继续等待。换句话说，等待应总是发生在循环中，如下面的示例：
     * 
     *     synchronized (obj) {
     *         while (condition does not hold)
     *             obj.wait(timeout);
     *         ... // Perform action appropriate to condition
     *     }
     * 
     *（有关这一主题的更多信息，请参阅 Doug Lea 撰写的 Concurrent Programming in Java (Second Edition) (Addison-Wesley, 2000) 
     * 中的第 3.2.3 节或 Joshua Bloch 撰写的 Effective Java Programming Language Guide (Addison-Wesley, 2001) 中的第 50 项。
     *
     * 如果当前线程在等待之前或在等待时被任何线程中断，则会抛出 InterruptedException。
     * 在按上述形式恢复此对象的锁定状态时才会抛出此异常。
     *
     * <p>
     * Note that the {@code wait} method, as it places the current thread
     * into the wait set for this object, unlocks only this object; any
     * other objects on which the current thread may be synchronized remain
     * locked while the thread waits.
     * <p>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     *
     * @param      timeout   the maximum time to wait in milliseconds.
     * @throws  IllegalArgumentException      if the value of timeout is
     *               negative.
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of the object's monitor.
     * @throws  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     * @see        java.lang.Object#notify()
     * @see        java.lang.Object#notifyAll()
     */
    public final native void wait(long timeout) throws InterruptedException;

    /**
     * Causes the current thread to wait until another thread invokes the
     * {@link java.lang.Object#notify()} method or the
     * {@link java.lang.Object#notifyAll()} method for this object, or
     * some other thread interrupts the current thread, or a certain
     * amount of real time has elapsed.
     * <p>
     * This method is similar to the {@code wait} method of one
     * argument, but it allows finer control over the amount of time to
     * wait for a notification before giving up. The amount of real time,
     * measured in nanoseconds, is given by:
     * <blockquote>
     * <pre>
     * 1000000*timeout+nanos</pre></blockquote>
     * <p>
     * In all other respects, this method does the same thing as the
     * method {@link #wait(long)} of one argument. In particular,
     * {@code wait(0, 0)} means the same thing as {@code wait(0)}.
     * <p>
     * The current thread must own this object's monitor. The thread
     * releases ownership of this monitor and waits until either of the
     * following two conditions has occurred:
     * <ul>
     * <li>Another thread notifies threads waiting on this object's monitor
     *     to wake up either through a call to the {@code notify} method
     *     or the {@code notifyAll} method.
     * <li>The timeout period, specified by {@code timeout}
     *     milliseconds plus {@code nanos} nanoseconds arguments, has
     *     elapsed.
     * </ul>
     * <p>
     * The thread then waits until it can re-obtain ownership of the
     * monitor and resumes execution.
     * <p>
     * As in the one argument version, interrupts and spurious wakeups are
     * possible, and this method should always be used in a loop:
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait(timeout, nanos);
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     *
     * @param      timeout   the maximum time to wait in milliseconds.
     * @param      nanos      additional time, in nanoseconds range
     *                       0-999999.
     * @throws  IllegalArgumentException      if the value of timeout is
     *                      negative or the value of nanos is
     *                      not in the range 0-999999.
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of this object's monitor.
     * @throws  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     */
    public final void wait(long timeout, int nanos) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                                "nanosecond timeout value out of range");
        }

        if (nanos > 0) {
            timeout++;
        }

        wait(timeout);
    }

    /**
     * Causes the current thread to wait until another thread invokes the
     * {@link java.lang.Object#notify()} method or the
     * {@link java.lang.Object#notifyAll()} method for this object.
     * In other words, this method behaves exactly as if it simply
     * performs the call {@code wait(0)}.
     * <p>
     * The current thread must own this object's monitor. The thread
     * releases ownership of this monitor and waits until another thread
     * notifies threads waiting on this object's monitor to wake up
     * either through a call to the {@code notify} method or the
     * {@code notifyAll} method. The thread then waits until it can
     * re-obtain ownership of the monitor and resumes execution.
     * <p>
     * As in the one argument version, interrupts and spurious wakeups are
     * possible, and this method should always be used in a loop:
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait();
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     *
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of the object's monitor.
     * @throws  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     * @see        java.lang.Object#notify()
     * @see        java.lang.Object#notifyAll()
     */
    public final void wait() throws InterruptedException {
        wait(0);
    }

    /**
     * Called by the garbage collector on an object when garbage collection
     * determines that there are no more references to the object.
     * A subclass overrides the {@code finalize} method to dispose of
     * system resources or to perform other cleanup.
     * <p>
     * The general contract of {@code finalize} is that it is invoked
     * if and when the Java&trade; virtual
     * machine has determined that there is no longer any
     * means by which this object can be accessed by any thread that has
     * not yet died, except as a result of an action taken by the
     * finalization of some other object or class which is ready to be
     * finalized. The {@code finalize} method may take any action, including
     * making this object available again to other threads; the usual purpose
     * of {@code finalize}, however, is to perform cleanup actions before
     * the object is irrevocably discarded. For example, the finalize method
     * for an object that represents an input/output connection might perform
     * explicit I/O transactions to break the connection before the object is
     * permanently discarded.
     * <p>
     * The {@code finalize} method of class {@code Object} performs no
     * special action; it simply returns normally. Subclasses of
     * {@code Object} may override this definition.
     * <p>
     * The Java programming language does not guarantee which thread will
     * invoke the {@code finalize} method for any given object. It is
     * guaranteed, however, that the thread that invokes finalize will not
     * be holding any user-visible synchronization locks when finalize is
     * invoked. If an uncaught exception is thrown by the finalize method,
     * the exception is ignored and finalization of that object terminates.
     * <p>
     * After the {@code finalize} method has been invoked for an object, no
     * further action is taken until the Java virtual machine has again
     * determined that there is no longer any means by which this object can
     * be accessed by any thread that has not yet died, including possible
     * actions by other objects or classes which are ready to be finalized,
     * at which point the object may be discarded.
     * <p>
     * The {@code finalize} method is never invoked more than once by a Java
     * virtual machine for any given object.
     * <p>
     * Any exception thrown by the {@code finalize} method causes
     * the finalization of this object to be halted, but is otherwise
     * ignored.
     *
     * @throws Throwable the {@code Exception} raised by this method
     * @see java.lang.ref.WeakReference
     * @see java.lang.ref.PhantomReference
     * @jls 12.6 Finalization of Class Instances
     */
    protected void finalize() throws Throwable { }
}
