package edu.hut.bookshop.controller;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import edu.hut.bookshop.pojo.Book;
import edu.hut.bookshop.service.BookService;
import edu.hut.bookshop.util.ResultCode;
import edu.hut.bookshop.util.ResultVO;

import javax.validation.Valid;

/**
 * @Description: 书籍管理模块控制器
 */
@RestController   //注解表示这是一个控制器类，用于处理请求
@RequestMapping("/book")  //注解指定了该控制器下的所有请求路径都以 "/book" 开头
public class BookController {

    @Autowired  //自动装配 BookService，将 BookService 注入到该控制器中，以便调用 BookService 中的方法
    private BookService bookService;


    @GetMapping("/searchid")//注解表示处理以 "/book/searchid" 路径的 GET 请求
    public ResultVO bookSearchByBookId(Integer bookId) {
        //当请求到达时，调用 bookSearchById() 方法查询书籍信息，并根据查询结果返回相应的 ResultVO 对象
        Book books = bookService.bookSearchById(bookId);
        if (books != null)
            return new ResultVO(ResultCode.SUCCESS,books);
        else
            return new ResultVO(ResultCode.RECORD_NOT_FOUND, null);
    }


    @PostMapping("/delete") //注解表示处理以 "/book/delete" 路径的 POST 请求
    public ResultVO bookDelete(Integer bookId) {
        //当请求到达时，调用 bookDeleteSearchById() 方法根据书籍ID删除书籍，并返回相应的 ResultVO 对象
        int books = bookService.bookDeleteSearchById(bookId);
        return new ResultVO(ResultCode.SUCCESS, null);
    }

    //添加验证注解
    @PostMapping("/insert")//注解表示处理以 "/book/insert" 路径的 POST 请求
    public ResultVO bookInsert(@Valid Book record) {
        //当请求到达时，调用 bookInsert() 方法添加书籍，并返回相应的 ResultVO 对象
        //@Valid 注解表示对 Book 对象进行数据验证
        int books = bookService.bookInsert(record);
        return new ResultVO(ResultCode.SUCCESS, null);
    }



    @GetMapping("/searchcode")//注解表示处理以 "/book/searchcode" 路径的 GET 请求
  public ResultVO bookSerchByCategoryCode(String catrgoryCode,Integer page,Integer limit) {
      List<Book> books = bookService.bookSearchByCode(catrgoryCode,page,limit);
        //当请求到达时，调用 bookSearchByCode() 方法根据分类代码查询书籍，并返回相应的 ResultVO 对象
        PageInfo pageInfo = new PageInfo(books);
     if(books.size()!=0)
     {
      return new ResultVO(ResultCode.SUCCESS,(int)pageInfo.getTotal(), books);
     }
     else
    	 return new ResultVO(ResultCode.RECORD_NOT_FOUND, null);

  }


    //添加验证注解
    @PostMapping("/update")//注解表示处理以 "/book/update" 路径的 POST 请求
    public ResultVO bookUpdate(@Valid Book record) {
        //当请求到达时，调用 bookUpdate() 方法更新书籍信息，并返回相应的 ResultVO 对象
        // @Valid 注解表示对 Book 对象进行数据验证
        int books = bookService.bookUpdate(record);
        return new ResultVO(ResultCode.SUCCESS, null);
    }

    /**
     * 多条件搜索
     * @param book
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/search") //注解表示处理以 "/book/search" 路径的 GET 请求
    public ResultVO searchBooks(Book book,Integer page, Integer limit) {
        if(book.getBookName().isEmpty()){
            book.setBookName(null);
        }
        if(book.getIsbn().isEmpty()){
            book.setIsbn(null);
        }
        //当请求到达时，调用 searchBooks() 方法根据多个条件搜索书籍，并返回相应的 ResultVO 对象
        List<Book> books = bookService.searchBooks(book, page, limit);
        PageInfo pageInfo = new PageInfo(books);
        return new ResultVO(ResultCode.SUCCESS, (int) pageInfo.getTotal(), books);
    }


}
