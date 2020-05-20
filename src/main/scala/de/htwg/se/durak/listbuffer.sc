import scala.collection.mutable.ListBuffer

val l = new ListBuffer[Int]()

l += 4
l += 8
l += 78
l += 48
l += 745
l += 474
l += 845
l.remove(1)
l(1)
l

l.remove(1,2)
l
