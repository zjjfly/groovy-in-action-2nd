package main.groovy.com.github.zjjfly.gia.ch02

/**
 * Created by zjjfly on 2017/2/4.
 */
class Book {
    private String title

    //方法不用加访问标识符，默认是public
    Book(String theTitle) {
        title = theTitle
    }

    String getTitle() {
        return title
    }
}
