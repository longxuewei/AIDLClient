// BookManager.aidl
package cn.lxw.aidlclient;
// Declare any non-default types here with import statements
import cn.lxw.aidlclient.Book;
interface BookManager {

    List<Book> getBooks();

    void addBook(in Book b);
}
