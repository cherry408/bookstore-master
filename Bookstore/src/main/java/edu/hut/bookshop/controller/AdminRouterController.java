package edu.hut.bookshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 后台页面的路由跳转
 */

//当请求到达相应的路径时，控制器会返回对应的字符串，Spring Boot 会根据字符串找到对应的页面进行跳转

@Controller           //声明控制器类，用于处理请求
@RequestMapping("/admin")    //注解指定了该控制器下的所有请求路径都以 "/admin" 开头
public class AdminRouterController {

    //跳转用户管理页面
    @GetMapping("/user_manage")       // 注解表示处理以 "/admin/user_manage" 路径的 GET 请求
    public String toUserManage(){
        return "admin/user";
    } //请求到达时，将返回 "admin/user" 字符串，该字符串表示需要跳转到 "admin/user.html" 页面

    //跳转书籍管理页面
    @GetMapping({"/","/book_manage"}) // 注解表示处理以 "/admin/book_manage" 路径的 GET 请求
    public String toBookManage(){
        return "admin/books";
    }//当请求到达时，将返回 "admin/books" 字符串，该字符串表示需要跳转到 "admin/books.html" 页面

    //跳转分类管理页面
    @GetMapping("/category_manage")  //注解表示处理以 "/admin/category_manage" 路径的 GET 请求
    public String toCategoryManage(){
        return "admin/category";
    } //当请求到达时，将返回 "admin/category" 字符串，该字符串表示需要跳转到 "admin/category.html" 页面

    //跳转订单管理页面
    @GetMapping("/order_manage") //注解表示处理以 "/admin/order_manage" 路径的 GET 请求
    public String toOrderManage(){
        return "admin/order";
    }//当请求到达时，将返回 "admin/order" 字符串，该字符串表示需要跳转到 "admin/order.html" 页面

    //跳转添加书籍页面
    @GetMapping("/add_book")//注解表示处理以 "/admin/add_book" 路径的 GET 请求
    public String AddBook(){
        return "admin/add_book";
    }//当请求到达时，将返回 "admin/add_book" 字符串，该字符串表示需要跳转到 "admin/add_book.html" 页面

    //跳转后台登录页面
    @GetMapping("/login")//注解表示处理以 "/admin/login" 路径的 GET 请求
    public String toAdminLogin(){
        return "admin/login";
    }//当请求到达时，将返回 "admin/login" 字符串，该字符串表示需要跳转到 "admin/login.html" 页面

}
