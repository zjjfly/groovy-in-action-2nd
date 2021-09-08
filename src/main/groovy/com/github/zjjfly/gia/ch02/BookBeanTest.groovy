package main.groovy.com.github.zjjfly.gia.ch02

/**
 * Created by zjjfly on 2017/2/4.
 */
class BookBean {
    String title
}

BookBean groovyBook = new BookBean()
groovyBook.setTitle('Groovy in Action')
assert groovyBook.getTitle() == 'Groovy in Action'
groovyBook.title = 'Groovy conquers the world'//这一行和下一行的title是一个accessor方法的简写而不是直接的成员变量的访问。
assert groovyBook.title == 'Groovy conquers the world'
