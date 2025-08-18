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