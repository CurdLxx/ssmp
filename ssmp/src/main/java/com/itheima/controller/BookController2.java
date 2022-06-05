package com.itheima.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.controller.utils.Result;
import com.itheima.domain.Book;
import com.itheima.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController2 {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public Result getAll(){
        return new Result(true,bookService.list());
    }

    @PostMapping
    public Result save(@RequestBody Book book) throws IOException {
        if (book.getName().equals("123") ) throw new IOException();
        boolean flag = bookService.save(book);
        return new Result(flag, flag ? "添加成功^_^" : "添加失败-_-!");
    }

    @PutMapping
    public Result update(@RequestBody Book book){
        boolean flag = bookService.updateById(book);
        return new Result(flag, flag ? "修改成功^_^" : "修改失败-_-!");
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return new Result(bookService.removeById(id));
    }

    @GetMapping("{id}")
    public Result getById(@PathVariable Integer id){
        System.out.println("根据id查询成功！");
        return new Result(true,bookService.getById(id));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public Result getPage(@PathVariable Integer currentPage,@PathVariable Integer pageSize,Book book){
        IPage<Book> page = bookService.getPage(currentPage, pageSize,book);
        if (currentPage > page.getPages()){
            page = bookService.getPage((int) page.getPages(),pageSize,book);
        }
        return new Result(true,page);
    }


}
