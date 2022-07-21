SELECT employee_id, first_name, last_name,
           first_name ||'-'|| last_name "이름-성"
FROM employees;

SELECT employee_id, 
        salary 기본급, 
        COMMISSION_PCT 수당률,
        salary+salary*commission_pct 실급여,
        salary+salary*commission_pct *NVL(commission_pct, 0)실급여2
From employees;

SELECT employee_id, salary
FROM employees
WHERE salary >= 3000;

SELECT employee_id, salary
FROM employees;

SELECT employee_id, salary
FROM employees
WHERE salary*12>= 100000;

SELECT employee_id, first_name,last_name
FROM employees
WHERE first_name = 'William';

SELECT employee_id, first_name,last_name
FROM employees
WHERE first_name <> 'William';

SELECT employee_id, first_name,department_id,salary
FROM employees
WHERE salary >=10000 AND salary<=15000;

SELECT employee_id, first_name, department_id, salary
FROM employees
WHERE department_id =30 OR department_id=70 OR department_id= 80;


SELECT employee_id, first_name, department_id, salary
FROM employees
WHERE (department_id =30 OR department_id=70 OR department_id= 80 )AND salary >=10000 AND salary<=15000;

SELECT employee_id, first_name, department_id, salary
FROM employees
WHERE department_id IN(30, 70, 80);

SELECT employee_id, first_name, department_id, salary
FROM employees
WHERE department_id NOT IN (30,70,80) AND salary NOT BETWEEN 10000 AND 15000;

SELECT MOD(3,2) FROM dual;   

SELECT 3+2,3-2,3*2,3/2, MOD(3,2) FROM dual;

SELECT 3/2, ROUND(3/2) FROM dual; -- 1.5 2
SELECT ROUND(123.4556,1) FROM dual;
SELECT 3/2, TRUNC(3/2), TRUNC(123.4567,1) FROM dual;

--급여가 적은 사원부터 출력
SELECT employee_id, salary
FROM employees
ORDER BY salary ASC ;

SELECT employee_id, salary ,  commission_pct, salary*NVL(commission_pct,0),
       salary+(salary*NVL(commission_pct,0))
FROM employees 
ORDER BY salary+(salary*NVL(commission_pct,0)) ASC;

SELECT employee_id, salary, commission_pct, 
			 salary+(salary*NVL(commission_pct,0)) 
FROM employees 
ORDER BY 4; -- 4번째 컬럼 위치의 salary+(salary*NVL(commission_pct,0))를 나타냄

SELECT employee_id, salary, commission_pct, 
			 salary+(salary*NVL(commission_pct,0)) 실급여
FROM employees 
ORDER BY 실급여; -- 별칭으로도 정렬 가능

SELECT employee_id, salary , commission_pct, salary*NVL(commission_pct,0),
			 salary+(salary*NVL(commission_pct,0)) 실급여
FROM employees 
ORDER BY 실급여 DESC; -- 내림차순

SELECT job_id, job_title,min_salary,max_salary,
        (min_salary + max_salary)/2
FROM jobs
ORDER BY 5 DESC;

SELECT employee_id, hire_date, SYSDATE-hire_date 
FROM employees
ORDER BY 3 DESC;

SELECT employee_id, hire_date, TRUNC(SYSDATE-hire_date,1)||'일' 근무일수
FROM employees
ORDER BY 3 DESC;

SELECT employee_id, hire_date, salary, TRUNC(SYSDATE-hire_date,1)||'일' 근무일수
FROM employees
ORDER BY 4 DESC;

SELECT employee_id, hire_date, salary, TRUNC(SYSDATE-hire_date,1)||'일' 근무일수
FROM employees
ORDER BY 4 DESC,salary ASC;

SELECT employee_id, first_name
FROM employees
WHERE first_name LIKE '%e%';

SELECT department_id
FROM employees; -- 부서가 없는(null) 사원도 있음

SELECT DISTINCT department_id
FROM employees;

SELECT DISTINCT employee_id, department_id
FROM employees;

SELECT DISTINCT department_id, employee_id
FROM employees;

SELECT DISTINCT department_id, job_id
FROM employees
ORDER BY department_id;

SELECT rownum, employee_id
FROM employees;

SELECT rownum,employee_id
FROM employees
ORDER BY salary;

SELECT rownum, employee_id, salary
FROM employees
ORDER BY salary DESC;

SELECT rownum, employee_id, salary
FROM employees
WHERE rownum <= 5
ORDER BY salary DESC;

SELECT rownum, employee_id, salary
FROM employees
WHERE rownum BEtwEEN 11 AND 20
ORDER BY salary DESC;

SELECT rownum, employee_id, salary
FROM employees
WHERE rownum BEtwEEN 1 AND 10
ORDER BY salary DESC;

SELECT rownum, employee_id, salary
FROM employees
WHERE 1=1
ORDER BY salary DESC;

-- 소숫점 이하 2자리까지 표현 
SELECT TRUNC(12345.12345,2) From dual; 

-- 소숫점 이하 0자리까지 표현
SELECT TRUNC(12345.12345,0) FROM dual;
SELECT TRUNC(12345) FROM dual;

-- 소숫점 이하 -1자리까지 표현(소숫점 1의 자리에서 자름)
SELECT TRUNC(12345.12345,-1) FROM dual; --12340

-- 소숫점 이하 -2자리까지 표현(소숫점 0의 자리에서 자름)
SELECT TRUNC(12345.12345,-2) FROM dual; --123400

SELECT ROUND(12345.12345,-1) FROM dual; --12350
SELECT ROUND(12345.12345,-2) FROM dual; --12300

SELECT INSTR('가나다가나다','나') FROM dual; --2
SELECT INSTR('가나다가나다','마') FROM dual; --0
SELECT SUBSTR('가나다가나다',2,3) FROM dual; --나다가
SELECT LENGTH('가나다가나다'), LENGTHB('가나다가나다')FROM dual;
SELECT LPAD('ABC',10,'*') FROM dual;
SELECT 'TEST'|| LTRIM('  ABC') FROM dual;

SELECT 'BEGIN' ||LTRIM('  ABC  ')||'END' FROM dual;
SELECT 'BEGIN' ||TRIM('  ABC  ')||'END' FROM dual;

SELECT SYSDATE , SYSDATE+1 내일날짜 , SYSDATE+3-SYSDATE , SYSDATE-1 어제날짜 
--SYSDATE+3은 글피 SYSDATE-1 어제날짜
FROM dual;

SELECT SYSDATE , SYSDATE+1 내일날짜 , SYSDATE+3-SYSDATE, SYSDATE -1 어제날짜,
        SYSDATE -TO_DATE('22/04/18')수업일수
FROM dual;

SELECT TO_DATE('22/02/28'),
        TO_DATE('2022/02/28'),
        TO_DATE('22-02-28'),
        TO_DATE('02/28/22','mm/dd/yy'),
        SYSDATE,
        TO_CHAR(SYSDATE,'yyyy-mm-dd HH:MI:SS'),
        TO_CHAR(SYSDATE,'yyyy-mm-dd am HH:MI:SS'),
        TO_CHAR(SYSDATE,'yyyy-mm-dd HH24:MI:SS')
FROM dual;

SELECT MONTHS_BETWEEN(TO_DATE('22/04/18'), SYSDATE),
       MONTHS_BETWEEN(SYSDATE, TO_DATE('22/04/18'))
FROM dual;

SELECT ADD_MONTHS(SYSDATE,1)"한달 후 날짜", -- 오늘 날짜로부터 한달을 증가시킨 값이 나옴
       ADD_MONTHS(SYSDATE,-1)"한달 전 날짜", -- 오늘 날짜로부터 한달을 감소시킨 값이 나옴
       ADD_MONTHS(SYSDATE,-6)"6개월 전 날짜" -- 오늘 날짜로부터 6개월 감소시킨 값이 나옴
FROM dual;

SELECT LAST_DAY(TO_DATE('22/02/01'))
FROM dual;

SELECT NEXT_DAY(TO_DATE('22/12/25'),'일요일')
FROM dual;

SELECT NEXT_DAY(TO_DATE('22/12/25'),'일요일'), LAST_DAY('22/02/01'), 
-- 이 경우엔 LAST_DAY 자동 형변환 시켜줌. 
-- 그냥 안전하게 TO_DATE로 문자 형태로 강제 형변환 후 사하는게 좋음
       NEXT_DAY('22/12/25','일요일')
FROM dual;

SELECT TO_CHAR(SYSDATE, 'yy-mm-dd am HH:MI:DD day')
FROM dual;

SELECT TO_CHAR(TO_DATE('22-12-25'), 'yy-mm-dd am HH:MI:DD day')
FROM dual;


SELECT TO_NUMBER('1,234.5', '9,999.9')            FROM dual; --숫자 1234.5
SELECT TO_NUMBER('1,234.5', '9,999.999')         FROM dual; --숫자 1234.5
SELECT TO_NUMBER('1,234.5', '9,999')               FROM dual;--X 자리수 모자람
SELECT TO_NUMBER('1,234,567.8', '9,999.9')       FROM dual;--X 자리수 모자람
SELECT TO_NUMBER('1,234,567.8', '9,999,999,999.9')       FROM dual;

SELECT TO_CHAR(1234.5, '99,999.9')                  FROM dual; --문자 '1,234.5'
SELECT TO_CHAR(123.4, '9,999.990')                   FROM dual; --문자 '123.400'
SELECT TO_CHAR(123.45, '0,000.990')                  FROM dual; --문자 '0,123.450'
SELECT TO_CHAR(12345.666 , '0,000.99999')            FROM dual;
SELECT TO_CHAR(1234.5,'L99,999.9') FROM dual;

SELECT NVL(commission_pct,0)    FROM employees;
SELECT NVL(commission_pct,'수당없음')     FROM employees; -- X
SELECT commission_pct, NVL2(commission_pct, '수당있음', '수당없음') FROM employees;

SELECT employee_id, commission_pct
FROM employees
--WHERE commission_pct <> null;
WHERE commission_pct IS NOT NULL;

SELECT NULLIF(10,10) FROM dual;
SELECT NULLIF(1,10) FROM dual;

-- SELECT DECODE(commission_pct, null,'||commission_pct','수당없음') FROM employees; 
SELECT DECODE(commission_pct,null,TO_CHAR(commission_pct),'수당없음') FROM employees;

SELECT DECODE(commission_pct,null,'수당없음', TO_CHAR(commission_pct)) FROM employees;

SELECT DECODE(commission_pct, null, '수당없음', 0.1,'A등급',TO_CHAR(commission_pct))
FROM employees;

--수당이 0.1~0.19 경우에는 'A등급', 0.2~0.29 경우에는 'B등급', 0.2 ~0.39 경우에는 'C등급',
--0.4 ~0.49 경우에는 'D등급', 0.5~0.59 경우에는 'E등급', 0.6 이상은 'F등급'
SELECT DECODE(TRUNC(commission_pct,1),null, '수당없음', 0.1, 'A등급', 0.2 ,'B등급',0.3,'C등급',
              0.4,'D등급',0.5,'E등급','F등급')
FROM employees;

SELECT commission_pct, CASE WHEN commission_pct IS NULL THEN '수당없음'
                            WHEN commission_pct >=-0.6 THEN 'F'
							WHEN commission_pct >=-0.5 THEN 'E'
                            WHEN commission_pct >=-0.4 THEN 'D'
							WHEN commission_pct >=-0.3 THEN 'C'
							WHEN commission_pct >=-0.2 THEN 'B'	
							WHEN commission_pct >=-0.1 THEN 'A'
                       END
FROM employees;

--1
SELECT employee_id, first_name, salary
FROM employees
WHERE salary BETWEEN 10000 AND 14000; 

--2
SELECT employee_id, first_name, last_name
FROM employees
WHERE Lower(first_name) LIKE '%e%';

--3
SELECT employee_id, first_name
FROM employees
WHERE First_name Like 'J%'AND First_name Like '%n%';

--4
SELECT employee_id, first_name , DECODE(manager_id, NUll, '관리자 아님' , '관리자임')
FROM employees;

--5
SELECT employee_id, department_id, first_name, salary, salary*NVL(commission_pct,0)수당 
FROM employees
WHERE department_id IN(30,90) AND salary >=10000;

--6
SELECT employee_id, department_id, first_name, salary, salary*NVL(commission_pct,0) 수당
FROM employees
WHERE department_id NOT IN(30,60,90) AND salary>=10000;

--7
SELECT employee_id, TRUNC((SYSDATE-hire_Date)/365,0)
FROM employees
ORDER BY 2 DESC;