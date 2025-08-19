select *
from emp;

select *
from dual;

select *
from departments;

select * from table(dbms_xplan.display_cursor(sql_id=>'3sa2p3pn98p8n', format=>'ALLSTATS LAST'));


EXPLAIN PLAN FOR select * from employees where employee_id = 100;

select * from table(DBMS_XPLAN.DISPLAY);

select * from table(DBMS_XPLAN.DISPLAY(null,null,'advanced'));

select * from employees where employee_id = 100;

 select * from table(dbms_xplan.display_cursor('7m9mj5qb5pjr9'));
 
 alter session set STATISTICS_LEVEL = ALL;
 
 select * from employees where employee_id = 100;
 
  select * from table(dbms_xplan.display_cursor(null,null, 'ALLSTATS LAST'));
  
  
  select /*+ index(emp2 emp2_x01) */ * 
from emp2 
where deptno=30 and sal >=2000; 

create index emp2_new on emp2 (deptno, job, sal) nologging;

  select /*+ index(emp2 emp2_new) */ * 
from emp2 
where deptno=30 and sal >=2000; 

drop index emp2_new;

SELECT * 
FROM emp 
--where sal is not null
ORDER BY sal ASC ; 

SELECT /*+ index(emp emp_sal_ix) */* 
FROM emp 
where sal is not null
ORDER BY sal ASC ; 


  select * from table(dbms_xplan.display_cursor(null,null, 'ALLSTATS LAST'));

create index emp2_new on emp2 (deptno, job, sal) nologging;

create index custs_new on custs (cust_id, cust_city, cust_credit_limit) nologging;


create index sales_new on sales (cust_id, time_id) nologging;


SELECT /* index(c custs_new s sales_new) */
    c.cust_id,
    c.cust_last_name,
    c.cust_city,
    c.cust_credit_limit,
    s.prod_id,
    s.time_id,
    s.quantity_sold
FROM
    custs c
    JOIN sales s ON c.cust_id = s.cust_id
WHERE
    c.cust_city = 'Los Angeles'
    AND c.cust_credit_limit > 3000
    AND s.time_id BETWEEN TO_DATE('1999/01/01', 'YYYY/MM/DD')
                      AND TO_DATE('1999/12/31', 'YYYY/MM/DD');
                      
 SELECT /* use_nl(s c) index(c custs_new2 s sales_new) */
    c.cust_id,
    c.cust_last_name,
    c.cust_city,
    c.cust_credit_limit,
    s.prod_id,
    s.time_id,
    s.quantity_sold
FROM
    custs c
  ,  sales s
WHERE
    c.cust_id = s.cust_id
    and c.cust_city = 'Los Angeles'
    AND c.cust_credit_limit > 3000
    AND s.time_id BETWEEN TO_DATE('1999/01/01', 'YYYY/MM/DD')
                      AND TO_DATE('1999/12/31', 'YYYY/MM/DD');
                      
create index custs_new2 on custs (cust_city) nologging;

select a.prod_id, a.prod_name, sum(b.quantity_sold)
from prods a
, sales b
where a.prod_id = b.prod_id(+)
group by a.prod_id, a.prod_name;

select a.prod_id, a.prod_name, b.sum_sold
from prods a
, (select b.prod_id, sum(quantity_sold) as sum_sold 
    from sales b
    group by b.prod_id) b
where a.prod_id = b.prod_id(+);
--group by a.prod_id, a.prod_name;