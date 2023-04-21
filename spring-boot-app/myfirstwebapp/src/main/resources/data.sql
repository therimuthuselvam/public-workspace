-- insert into todo
-- values(10001,'Get AWS Certified', false, current_date(), 'in28minutes');
-- the query above can be used if the columns in the table are in the above defined order or else we can go for the below


-- use single quotes
insert into todo(id, username, description, target_date, done)
values('10001', 'in28minutes', 'Get AWS Certified', current_date(), false);

insert into todo(id, username, description, target_date, done)
values('10002', 'in28minutes', 'Get Azure Certified', current_date(), true);

insert into todo(id, username, description, target_date, done)
values('10003', 'in28minutes', 'Get GCP Certified', current_date(), false);

insert into todo(id, username, description, target_date, done)
values('10004', 'in28minutes', 'Learn FullStack', current_date(), false);