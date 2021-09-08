package main.groovy.com.github.zjjfly.gia.ch04

//java的数组无法动态的修改长度,但可以在语法上方便的访问和修改元素,而Groovy的list既避免了前者又实现了后者

//第一种声明list的方法,使用方括号并以","作为元素的分隔符
List myList = [1, 2, 3]
assert myList.size() == 3
//直接使用下标操作符访问元素
assert myList[0] == 1
//默认的类型是ArrayList
assert myList instanceof ArrayList

//空list
List emptyList = []
assert emptyList.size() == 0

//第二种声明list的方法是对Range调用toList方法
List longList = (0..1000).toList()
assert longList[555] == 555

//其他类型的list也可以使用下标操作符访问元素
List explicitList = new LinkedList(myList)
assert explicitList.size() == 3
explicitList[0] = 10
assert explicitList[0] == 10

//下标操作符实际是调用了getAt和putAt方法
//GDK中,getAt不止可以传入一个整数,还可以传入range或集合
myList = ['a', 'b', 'c', 'd', 'e', 'f']
assert myList[0..2] == ['a', 'b', 'c']
assert myList[0, 2, 4] == ['a', 'c', 'e']

//putAt可以传入一个集合或区间,为子list进行赋值
myList[0..2] = ['x', 'y', 'z']
assert myList == ['x', 'y', 'z', 'd', 'e', 'f']
//使用putAt来实现删除多个连续元素
myList[3..5] = []
assert myList == ['x', 'y', 'z']
//使用putAt来实现插入多个元素
myList[1..1] = [0, 1, 2]
assert myList == ['x', 0, 1, 2, 'z']

//下标可以是负数,这和Python的语法很像
assert myList[-1] == 'z'
//如果使用反向range,取到的元素也是反的
assert myList[3..1] == [2, 1, 0]
//正和负的index可以混用,这和Python也是一样的
myList[1..-2] == [0, 1, 2]

//半包区间也可以使用
assert myList[0..<-2] == ['x', 0, 1]

//虽然可以通过上面的方式增删元素,但是使用下面的方法更简单
myList = []
//plus(Object)方法
myList += 'a'
assert myList == ['a']
//plus(Collection)方法
myList += ['b', 'c']
assert myList == ['a', 'b', 'c']
//leftShift(Object)方法
myList = []
myList << 'a' << 'b'
assert myList == ['a', 'b']
//minus(Object)方法
assert myList - 'b' == ['a']
//multiple(Number)方法
assert myList * 2 == ['a', 'b', 'a', 'b']

//list还可以在控制结构中使用,如if,switch和for
myList = ['a', 'b', 'c']
//list实现了isCase,所以可以用在switch中
assert myList.isCase('a')
assert 'b' in myList
def candidate = 'c'
switch (candidate) {
    case myList: assert true; break
    default: assert false
}
//list可以用作过滤器
assert ['x', 'a', 'z'].grep(myList) == ['a']
//空list可以认为是false
myList = []
if (myList) {
    assert false
}
//list可以在for..in
def expr = ''
for (i in [1, '*', 5]) {
    expr += i
}
assert expr == '1*5'

//list相关的方法
//操作list内容的方法
//把嵌套的list压平,无论嵌套的层级有多少
assert [1, [2, [3]]].flatten() == [1, 2, 3]
//得到两个集合的交集
assert [1, 2, 3].intersect([4, 3, 1]) == [3, 1]
//判断两个集合的交集是否为空
assert [1, 2, 3].disjoint([4, 5, 6])

list = [1, 2, 3]
//移除list的第一个元素,并把这个元素返回
poped = list.pop()
assert poped == 1
assert list == [2, 3]
//颠倒list
assert [1, 2].reverse() == [2, 1]
//排序list
assert [4, 2, 1, 3].sort() == [1, 2, 3, 4]
assert ['abc', 'de', 'f', 'gh'].sort { it.size() } == ['f', 'de', 'gh', 'abc']
assert [[3, 5], [0, 3], [2, 1]].sort({ a, b -> a[0] <=> b[0] }) == [[0, 3], [2, 1], [3, 5]]
//通过index删除元素
list = ['a', 'b', 'c']
list.remove(2)
assert list == ['a', 'b']
//删除某个元素
list.remove('b')
assert list == ['a']
list = ['a', 'b', 'b', 'c']
//删除多个元素
list.removeAll(['b', 'c'])
assert list == ['a']
//转成成另一个list,类似map
def doubled = [1, 2, 3].collect { it * 2 }
assert doubled == [2, 4, 6]
//查找出所有符合closure的元素,类似filter
def odd = [1, 2, 3].findAll { it % 2 == 1 }
assert odd == [1, 3]
//去除重复元素
def x = [1, 1, 1]
assert [1] == new HashSet(x).toList()
assert [1] == x.unique()
//去除null元素
x = [1, null, 1]
assert [1, 1] == x.findAll { it != null }
assert [1, 1] == x.grep()//grep的原理和findAll是一样的,只是使用的closure是Closure.IDENTITY,会去过滤掉在Groovy中非true的元素

//访问list内容的方法
list = [1, 2, 3]
//获取首个元素
assert list.first() == 1
assert list.head() == 1
//获取尾list
assert list.tail() == [2, 3]
//获取最后的元素
assert list.last() == 3
//获取特定元素的数量
assert list.count(2) == 1
//获取最大元素
assert list.max() == 3
//获取最小元素
assert list.min() == 1
//获取符合closure的第一个元素
def even = list.find { item ->
    item % 2 == 1
}
assert even == 1
//验证是否每一个元素都符合closure
assert list.every { it < 5 }
//验证是否有任意一个元素符合closure
assert list.any() { it < 2 }
//遍历list,并应用传入的closure,类似java的forEach
def store = ''
list.each { store += it }
assert store == '123'
//反向遍历list
store = ''
list.reverseEach { store += it }
assert store == '321'
//遍历list,传入的closure有两个参数,一个是其中的元素,一个是index
store = ''
list.eachWithIndex { entry, index ->
    store += "$index:$entry "
}
assert store == '0:1 1:2 2:3 '
//使用指定的分隔符拼接所有元素
assert list.join('-') == '1-2-3'
//累加各个元素,有一个初始值,类似java的reduce
result = list.inject(0) {
    sum, i ->
        sum + i
}
assert result == 0 + 1 + 2 + 3
//计算list的和
assert list.sum() == 6

//把list转成不可变集合或线程安全的集合
list.asSynchronized()
list.asImmutable()
//使用这些方法来实现quick sort,优势是不需要关系元素的类型
def quickSort(list) {
    if (list.size() < 2) return list
    def pivot = list[list.size().intdiv(2)]
    def right = list.findAll { it > pivot }
    def left = list.findAll { it < pivot }
    def middle = list.findAll { it == pivot }
    quickSort(left) + middle + quickSort(right)
}

assert quickSort([1, 3, 2, 6, 4, 5]) == [1, 2, 3, 4, 5, 6]
assert quickSort([1.0f, 'a', 10, null]) == [null, 1.0f, 10, 'a']
assert quickSort('bca') == 'abc'.toList()
