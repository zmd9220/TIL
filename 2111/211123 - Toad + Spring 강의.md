# 211123 - Toad + Spring 강의



## 오늘의 피드백

- Mysql과 Oracle db의 join 방법이 다르다.
- Null을 포함한 직속 상사 및 상사의 상사 정보를 보고 싶은 경우 (연습문제 4-10)

```sql
-- mysql
select e.empno, e.ename, m.ename, p.ename
from emp e left join emp m
on e.mgr = m.empno
left join emp p
on m.mgr = p.empno
-- https://siyoon210.tistory.com/28 참고

-- oracle
select e.empno, e.ename, m.ename, p.ename
from emp e, emp m, emp p
where e.mgr = m.empno(+) and m.mgr = p.empno(+)
```

- join에 대해서는 기회를 봐서 정리해보는 시간이 필요할 것 같다.



