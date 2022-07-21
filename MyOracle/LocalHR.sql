SELECT employee_id, first_name, last_name,
           first_name ||'-'|| last_name "�̸�-��"
FROM employees;

SELECT employee_id, 
        salary �⺻��, 
        COMMISSION_PCT �����,
        salary+salary*commission_pct �Ǳ޿�,
        salary+salary*commission_pct *NVL(commission_pct, 0)�Ǳ޿�2
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

--�޿��� ���� ������� ���
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
ORDER BY 4; -- 4��° �÷� ��ġ�� salary+(salary*NVL(commission_pct,0))�� ��Ÿ��

SELECT employee_id, salary, commission_pct, 
			 salary+(salary*NVL(commission_pct,0)) �Ǳ޿�
FROM employees 
ORDER BY �Ǳ޿�; -- ��Ī���ε� ���� ����

SELECT employee_id, salary , commission_pct, salary*NVL(commission_pct,0),
			 salary+(salary*NVL(commission_pct,0)) �Ǳ޿�
FROM employees 
ORDER BY �Ǳ޿� DESC; -- ��������

SELECT job_id, job_title,min_salary,max_salary,
        (min_salary + max_salary)/2
FROM jobs
ORDER BY 5 DESC;

SELECT employee_id, hire_date, SYSDATE-hire_date 
FROM employees
ORDER BY 3 DESC;

SELECT employee_id, hire_date, TRUNC(SYSDATE-hire_date,1)||'��' �ٹ��ϼ�
FROM employees
ORDER BY 3 DESC;

SELECT employee_id, hire_date, salary, TRUNC(SYSDATE-hire_date,1)||'��' �ٹ��ϼ�
FROM employees
ORDER BY 4 DESC;

SELECT employee_id, hire_date, salary, TRUNC(SYSDATE-hire_date,1)||'��' �ٹ��ϼ�
FROM employees
ORDER BY 4 DESC,salary ASC;

SELECT employee_id, first_name
FROM employees
WHERE first_name LIKE '%e%';

SELECT department_id
FROM employees; -- �μ��� ����(null) ����� ����

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

-- �Ҽ��� ���� 2�ڸ����� ǥ�� 
SELECT TRUNC(12345.12345,2) From dual; 

-- �Ҽ��� ���� 0�ڸ����� ǥ��
SELECT TRUNC(12345.12345,0) FROM dual;
SELECT TRUNC(12345) FROM dual;

-- �Ҽ��� ���� -1�ڸ����� ǥ��(�Ҽ��� 1�� �ڸ����� �ڸ�)
SELECT TRUNC(12345.12345,-1) FROM dual; --12340

-- �Ҽ��� ���� -2�ڸ����� ǥ��(�Ҽ��� 0�� �ڸ����� �ڸ�)
SELECT TRUNC(12345.12345,-2) FROM dual; --123400

SELECT ROUND(12345.12345,-1) FROM dual; --12350
SELECT ROUND(12345.12345,-2) FROM dual; --12300

SELECT INSTR('�����ٰ�����','��') FROM dual; --2
SELECT INSTR('�����ٰ�����','��') FROM dual; --0
SELECT SUBSTR('�����ٰ�����',2,3) FROM dual; --���ٰ�
SELECT LENGTH('�����ٰ�����'), LENGTHB('�����ٰ�����')FROM dual;
SELECT LPAD('ABC',10,'*') FROM dual;
SELECT 'TEST'|| LTRIM('  ABC') FROM dual;

SELECT 'BEGIN' ||LTRIM('  ABC  ')||'END' FROM dual;
SELECT 'BEGIN' ||TRIM('  ABC  ')||'END' FROM dual;

SELECT SYSDATE , SYSDATE+1 ���ϳ�¥ , SYSDATE+3-SYSDATE , SYSDATE-1 ������¥ 
--SYSDATE+3�� ���� SYSDATE-1 ������¥
FROM dual;

SELECT SYSDATE , SYSDATE+1 ���ϳ�¥ , SYSDATE+3-SYSDATE, SYSDATE -1 ������¥,
        SYSDATE -TO_DATE('22/04/18')�����ϼ�
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

SELECT ADD_MONTHS(SYSDATE,1)"�Ѵ� �� ��¥", -- ���� ��¥�κ��� �Ѵ��� ������Ų ���� ����
       ADD_MONTHS(SYSDATE,-1)"�Ѵ� �� ��¥", -- ���� ��¥�κ��� �Ѵ��� ���ҽ�Ų ���� ����
       ADD_MONTHS(SYSDATE,-6)"6���� �� ��¥" -- ���� ��¥�κ��� 6���� ���ҽ�Ų ���� ����
FROM dual;

SELECT LAST_DAY(TO_DATE('22/02/01'))
FROM dual;

SELECT NEXT_DAY(TO_DATE('22/12/25'),'�Ͽ���')
FROM dual;

SELECT NEXT_DAY(TO_DATE('22/12/25'),'�Ͽ���'), LAST_DAY('22/02/01'), 
-- �� ��쿣 LAST_DAY �ڵ� ����ȯ ������. 
-- �׳� �����ϰ� TO_DATE�� ���� ���·� ���� ����ȯ �� ���ϴ°� ����
       NEXT_DAY('22/12/25','�Ͽ���')
FROM dual;

SELECT TO_CHAR(SYSDATE, 'yy-mm-dd am HH:MI:DD day')
FROM dual;

SELECT TO_CHAR(TO_DATE('22-12-25'), 'yy-mm-dd am HH:MI:DD day')
FROM dual;


SELECT TO_NUMBER('1,234.5', '9,999.9')            FROM dual; --���� 1234.5
SELECT TO_NUMBER('1,234.5', '9,999.999')         FROM dual; --���� 1234.5
SELECT TO_NUMBER('1,234.5', '9,999')               FROM dual;--X �ڸ��� ���ڶ�
SELECT TO_NUMBER('1,234,567.8', '9,999.9')       FROM dual;--X �ڸ��� ���ڶ�
SELECT TO_NUMBER('1,234,567.8', '9,999,999,999.9')       FROM dual;

SELECT TO_CHAR(1234.5, '99,999.9')                  FROM dual; --���� '1,234.5'
SELECT TO_CHAR(123.4, '9,999.990')                   FROM dual; --���� '123.400'
SELECT TO_CHAR(123.45, '0,000.990')                  FROM dual; --���� '0,123.450'
SELECT TO_CHAR(12345.666 , '0,000.99999')            FROM dual;
SELECT TO_CHAR(1234.5,'L99,999.9') FROM dual;

SELECT NVL(commission_pct,0)    FROM employees;
SELECT NVL(commission_pct,'�������')     FROM employees; -- X
SELECT commission_pct, NVL2(commission_pct, '��������', '�������') FROM employees;

SELECT employee_id, commission_pct
FROM employees
--WHERE commission_pct <> null;
WHERE commission_pct IS NOT NULL;

SELECT NULLIF(10,10) FROM dual;
SELECT NULLIF(1,10) FROM dual;

-- SELECT DECODE(commission_pct, null,'||commission_pct','�������') FROM employees; 
SELECT DECODE(commission_pct,null,TO_CHAR(commission_pct),'�������') FROM employees;

SELECT DECODE(commission_pct,null,'�������', TO_CHAR(commission_pct)) FROM employees;

SELECT DECODE(commission_pct, null, '�������', 0.1,'A���',TO_CHAR(commission_pct))
FROM employees;

--������ 0.1~0.19 ��쿡�� 'A���', 0.2~0.29 ��쿡�� 'B���', 0.2 ~0.39 ��쿡�� 'C���',
--0.4 ~0.49 ��쿡�� 'D���', 0.5~0.59 ��쿡�� 'E���', 0.6 �̻��� 'F���'
SELECT DECODE(TRUNC(commission_pct,1),null, '�������', 0.1, 'A���', 0.2 ,'B���',0.3,'C���',
              0.4,'D���',0.5,'E���','F���')
FROM employees;

SELECT commission_pct, CASE WHEN commission_pct IS NULL THEN '�������'
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
SELECT employee_id, first_name , DECODE(manager_id, NUll, '������ �ƴ�' , '��������')
FROM employees;

--5
SELECT employee_id, department_id, first_name, salary, salary*NVL(commission_pct,0)���� 
FROM employees
WHERE department_id IN(30,90) AND salary >=10000;

--6
SELECT employee_id, department_id, first_name, salary, salary*NVL(commission_pct,0) ����
FROM employees
WHERE department_id NOT IN(30,60,90) AND salary>=10000;

--7
SELECT employee_id, TRUNC((SYSDATE-hire_Date)/365,0)
FROM employees
ORDER BY 2 DESC;