package com.JPA;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public class BaseDAO<T> {

    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/testForJPA?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    private Class<T> beanClass;

    public BaseDAO(){
        beanClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void add (T bean){

        Field[] fields = beanClass.getDeclaredFields();

        String sql = "INSERT INTO "
                + beanClass.getAnnotation(Table.class).value()
                + " VALUES(";

        for(int i=0; i<fields.length; i++){
            sql += "?";
            if(i<fields.length - 1){
                sql += ",";
            }
        }
        sql += ")";

        // get Bean Value
        ArrayList<Object> paramList = new ArrayList<>();
        for(int  i=0; i<fields.length; i++){
            try{
                fields[i].setAccessible(true);
                Object obj = fields[i].get(bean);
                paramList.add(obj);
            } catch (IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }

        int size = paramList.size();

        Object[] params = paramList.toArray(new Object[size]);
        System.out.println(sql);
        int num = jdbcTemplate.update(sql, params);
        System.out.println(num);

    }


}
