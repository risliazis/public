CREATE TYPE gender AS ENUM ('M', 'F');

CREATE TABLE public.employees (
	emp_no serial NOT NULL,
	birth_date date NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NULL,
	gender gender NULL,
	hire_date date NOT NULL,
	CONSTRAINT employees_pkey PRIMARY KEY (emp_no)
);

CREATE TABLE public.salaries (
	emp_no int4 NULL,
	salary int4 NULL,
	from_date date NOT NULL,
	to_date date NULL
);

ALTER TABLE public.salaries ADD CONSTRAINT salaries_emp_no_fkey FOREIGN KEY (emp_no) REFERENCES employees(emp_no) ON DELETE CASCADE;

CREATE TABLE public.departments (
	dept_no bpchar(4) NOT NULL,
	dept_name varchar(40) NOT NULL,
	CONSTRAINT departments_pkey PRIMARY KEY (dept_no)
);

CREATE TABLE public.dept_emp (
	emp_no int4 NULL,
	dept_no bpchar(4) NULL,
	from_date date NOT NULL,
	to_date date NULL
);

ALTER TABLE public.dept_emp ADD CONSTRAINT dept_emp_dept_no_fkey FOREIGN KEY (dept_no) REFERENCES departments(dept_no);
ALTER TABLE public.dept_emp ADD CONSTRAINT dept_emp_emp_no_fkey FOREIGN KEY (emp_no) REFERENCES employees(emp_no) ON DELETE CASCADE;

CREATE TABLE public.dept_manager (
	dept_no bpchar(4) NULL,
	emp_no int4 NULL,
	from_date date NOT NULL,
	to_date date NULL
);

ALTER TABLE public.dept_manager ADD CONSTRAINT dept_manager_dept_no_fkey FOREIGN KEY (dept_no) REFERENCES departments(dept_no);
ALTER TABLE public.dept_manager ADD CONSTRAINT dept_manager_emp_no_fkey FOREIGN KEY (emp_no) REFERENCES employees(emp_no) ON DELETE CASCADE;


CREATE TABLE public.titles (
	emp_no int4 NULL,
	title varchar(50) NOT NULL,
	from_date date NOT NULL,
	to_date date NULL
);

ALTER TABLE public.titles ADD CONSTRAINT titles_emp_no_fkey FOREIGN KEY (emp_no) REFERENCES employees(emp_no) ON DELETE CASCADE;


INSERT INTO public.departments (dept_no,dept_name) VALUES 
('D001','IT')
,('D002','Accounting')
,('D003','Sales')
,('D004','HRD')
,('D005','Purchasing')
,('D006','Production')
;







