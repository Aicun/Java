package com.lac.spider.db;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.lac.spider.model.Book;

public class DbManager {

	static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/spider");
    static QueryRunner qr = new QueryRunner(ds);
    
    public static void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void executeInsert(List<Book> bookList) throws SQLException {
        Object[][] params = new Object[bookList.size()][3];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = bookList.get(i).getId();
            params[i][1] = bookList.get(i).getName();
            params[i][2] = bookList.get(i).getPrice();
        }
        qr.batch("insert into book (id, name, price)"
                + "values (?,?,?)", params);
        System.out.println("Done"+"number of books inserted: "+bookList.size());

    }
}
