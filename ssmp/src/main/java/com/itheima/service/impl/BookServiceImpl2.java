package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.dao.BookDao;
import com.itheima.domain.Book;
import com.itheima.service.IBookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl2 extends ServiceImpl<BookDao, Book> implements IBookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public IPage<Book> getPage(Integer currentPage, Integer pageSize) {
        IPage<Book> page = new Page<>(currentPage,pageSize);
        bookDao.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<Book> getPage(Integer currentPage, Integer pageSize, Book book) {
        IPage<Book> page = new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(book.getName()),Book::getName,book.getName());
        queryWrapper.like(Strings.isNotEmpty(book.getType()),Book::getType,book.getType());
        queryWrapper.like(Strings.isNotEmpty(book.getDescription()),Book::getDescription,book.getDescription());
        return bookDao.selectPage(page,queryWrapper);
    }
}
