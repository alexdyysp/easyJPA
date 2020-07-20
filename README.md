# easyJPA
-- 模拟JPA的简单实现

## 核心实现

表注解
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
	String value();
}
```

## 使用方法
```java
// @Table注解指明在哪一张表中
@Table("t_user")
public class User {}
```

## 结果
```sql
| name | age |
| hst  | 20  |
| dyy  | 22  |
```