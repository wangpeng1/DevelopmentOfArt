// IBookManager.aidl
package qingfengmy.developmentofart._2activity.aidl;

import qingfengmy.developmentofart._2activity.aidl.Book;
import qingfengmy.developmentofart._2activity.aidl.IOnNewBookArrivedListener;
interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
     void registerListener(IOnNewBookArrivedListener listener);
     void unRegisterListener(IOnNewBookArrivedListener listener);
}
