 select * from employees a, departments b where a.employee_id = 100 and a.department_id = b.department_id;
 
 select sql_id, sql_text
 from v$sql
 where sql_text like '%select * from employees a%';
 
 select * from table(dbms_xplan.display_cursor('7m9mj5qb5pjr9'));