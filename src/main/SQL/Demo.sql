create database apjd_book_store;

use apjd_book_store;

create table book(
   bid varchar(20),
   isbm varchar(20),
   name varchar(200),
   qty int,
   price decimal(10,2),
   primary key(bid)
);


create table MyTest(
Username varchar(20),
Email varchar(50),
Password varchar(20),
Password_Again varchar(20),
Phone_Number int 
);

////////////////////////////////////////////////
create database My_Test;

use My_Test;

create table Add_Book(
bid varchar(20),
name varchar(50),
price double,
qty int,
isbm varchar(20),
primary key (bid)
);

///////////////////////////////////////////
Create database javaFxTest;

use javaFxTest;

create table add_Book(
NAME varchar(70),
BOOK_ID varchar(10),
ISBM varchar(10),
QTY int,
PRICE double,
primary key(BOOK_ID)
);

create table member(
	Member_id varchar(20),
	Name varchar(20),
	Email varchar(20),
	Address varchar(200),
	age int,
	primary key(Member_id)
);

select 
	o.id, cus.name,i.description, od.qty
	from customer cus
	join orders o
	on o.customerId = cus.id
	join order_detail od
	on od.orderId = o.id
	join item i
	on i.code = od.itemCode;

-- ///////////////////////IN CLASS TEST//////////////////////////

create database test2;
use test2;

create table customer(
	cus_id varchar(10) PRIMARY KEY,
	name varchar(255) NOT NULL,
	address varchar(255) NOT NULL,
	tel_No int NOT NULL
);

insert into customer(cus_id, name, address, tel_No) values
('C001', 'Lahiru', 'Panadura', 0761298256),
('C002', 'Rishitha', 'Galle', 0701212567),
('C003', 'Mewan', 'Kaluthara', 0783050333);

create table payment(
	pay_id varchar(10) PRIMARY KEY,
	price double NOT NULL,
	date date NOT NULL
);
insert into payment(pay_id,price, date) values
('P001', 500.00, '2022.06.03'),
('P002', 800.00, '2022.09.13'),
('P003', 1000.00, '2022.04.23');

create table delivery(
	deli_id varchar(10) PRIMARY KEY,
	deli_mod varchar(10) NOT NULL
);

insert into delivery(deli_id, deli_mod) values 
('D001', 'Fast'),
('D002', 'Fast'),
('D003', 'Fast');

create table location(
	l_id varchar(10) PRIMARY KEY,
	name varchar(100) NOT NULL,
	fix_pricde double NOT NULL
);
ALTER TABLE location RENAME COLUMN fix_pricde TO fix_price;

insert into location(l_id, name, fix_pricde) values
('L001', 'Kaluthara', 250.00),
('L002', 'Colombo', 450.00),
('L003', 'Galle', 750.00);

create table deli_location(
	location_id varchar(10) NOT NULL,
	delivery_id varchar(10) NOT NULL,
	FOREIGN KEY (location_id) references location(l_id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (delivery_id) references delivery(deli_id) ON UPDATE CASCADE ON DELETE CASCADE
);

insert into deli_location(location_id, delivery_id) values
('L001', 'D001'),
('L002', 'D002'),
('L003', 'D002');

create table orders(
	o_id varchar(10) PRIMARY KEY,
	type varchar(50) NOT NULL,
	place_date date NOT NULL,
	cus_id varchar(10) NOT NULL,
	pay_id varchar(10) NOT NULL,
	deli_id varchar(10) NOT NULL,
	FOREIGN KEY (cus_id) references customer(cus_id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (pay_id) references payment(pay_id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (deli_id) references delivery(deli_id) ON UPDATE CASCADE ON DELETE CASCADE
);

insert into orders (o_id, type, place_date, cus_id, pay_id, deli_id) values
('O001', 'electronic', '2022.06.03', 'C001', 'P001', 'D001'),
('O002', 'clothing', '2022.09.13', 'C002', 'P002', 'D002'),
('O003', 'fruts', '2022.06.03', 'C002', 'P003', 'D003');

create table parcel(
	p_id varchar(10) PRIMARY KEY ,
	weight_cm int NOT NULL,
	height_cm int NOT NULL,
	description varchar(255) NOT NULL,
	order_id varchar(10) NOT NULL,
	FOREIGN KEY (order_id) references orders(o_id) ON UPDATE CASCADE ON DELETE CASCADE
);

insert into parcel(p_id, weight_cm, height_cm, description, order_id) values
('P001', 115, 100, 'this is my children toy for thier brithday...plese wish', 'O001'),
('P002', 15, 50, 'No-description', 'O002'),
('P003', 115, 100, 'No-description', 'O002');
////////////////////////////////////////////////////////////////////////
create database test3;
use test3;

create table user(
	userID varchar(10) PRIMARY KEY,
	password varchar(10)
);

create table student(
	name varchar(50) PRIMARY KEY,
	address varchar(100) NOT NULL,
	age int
);

alter table student
modify name varchar(100);

	alter table student 
	modify name varchar(100) DEFAULT 'No Name';

create table userCopy(
	userId varchar(10) PRIMARY KEY,
	FOREIGN KEY(userId) REFERENCES user(userID)
);

////////////////////////////////////////////////////////////////////////
create database resturent;
use resturent;
create table item(
	iId varchar(10) PRIMARY KEY,
	description varchar(200) NOT NULL,
	qty int NOT NULL,
	price float(2) NOT NULL 
);

insert into item values ("I001", "Rice Of Bols", 10, 350.00);

////////////////////////////////////////////////////////////////////////
 
create database buddikaFitnessCenter;
use buddikaFitnessCenter;

create table admin(
admin_id varchar(10) PRIMARY KEY,
name varchar(100) NOT NULL,
address varchar(500) NOT NULL,
password varchar(10) UNIQUE
);

insert into admin values ("A001", "Lahiru", "Kaluthara", "user");

create table login(
admin_id varchar(10) NOT NULL,
password varchar(10) NOT NULL,
FOREIGN KEY (password) references admin(password) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (admin_id) references admin(admin_id) ON UPDATE CASCADE ON DELETE CASCADE
);

insert into login values ("A001", "user");

create table reportGenerator(
report_id varchar(10) PRIMARY KEY,
admin_id varchar(10) NOT NULL,
type varchar(20) NOT NULL,
FOREIGN KEY (admin_id) references admin(admin_id) ON UPDATE CASCADE ON DELETE CASCADE
);

insert into reportGenerator values 
("R001", "A001", "Payment"),
("R002", "A001", "Schedule"),
("R003", "A001", "Monthly Revenue"),
("R004", "A001", "Diet Plan");

create table schedule(
schedule_id varchar(10) PRIMARY KEY,
name varchar(100) NOT NULL,
admin_id varchar(10) NOT NULL,
FOREIGN KEY (admin_id) references admin(admin_id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO schedule VALUES 
('SCH001', 'Basic Schedule', 'A001'),
('SCH002', 'Advanced Schedule', 'A001'),
('SCH003', 'Strength Training', 'A001');

create table exercise(
exercise_id varchar(10) PRIMARY KEY,
exercise_name varchar(100) NOT NULL,
description varchar(200) NOT NULL
);

INSERT INTO exercise VALUES 
('EXE001', 'Push Ups', 'Upper body exercise using body weight'),
('EXE002', 'Squats', 'Lower body exercise focusing on thighs and hips'),
('EXE003', 'Bench Press', 'Chest exercise using a bench and barbell'),
('EXE004', 'Deadlifts', 'Full body exercise focusing on lower back and hamstrings'),
('EXE005', 'Pull Ups', 'Upper body exercise focusing on the back and biceps'),
('EXE006', 'Lunges', 'Lower body exercise for legs and glutes'),
('EXE007', 'Shoulder Press', 'Upper body exercise for shoulders and triceps'),
('EXE008', 'Planks', 'Core stability exercise'),
('EXE009', 'Bicep Curls', 'Upper body exercise focusing on the biceps'),
('EXE010', 'Tricep Dips', 'Upper body exercise focusing on the triceps');

create table exerciseSchedule(
    schedule_id varchar(10) NOT NULL,
    exercise_id varchar(10) NOT NULL,
    exercise_name varchar(100) NOT NULL,
    schedule_name varchar(100) NOT NULL,
    exercise_count int NOT NULL,
    exercise_set int NOT NULL,
    admin_id varchar(10) NOT NULL,
    FOREIGN KEY (schedule_id) references schedule(schedule_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (exercise_id) references exercise(exercise_id) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY (exercise_name) references exercise(exercise_name) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (schedule_name) references schedule(name) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (admin_id) references schedule(admin_id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO exerciseSchedule VALUES
('SCH001', 'EXE001', 'Push Ups', 'Basic Schedule', 15, 3,'A001'),
('SCH001', 'EXE002', 'Squats', 'Basic Schedule', 20, 4,'A001'),
('SCH001', 'EXE003', 'Bench Press','Basic Schedule', 12, 3,'A001'),
('SCH001', 'EXE004', 'Deadlifts','Basic Schedule', 10, 3,'A001'),
('SCH001', 'EXE005', 'Pull Ups','Basic Schedule', 10, 3,'A001'),
('SCH002', 'EXE005', 'Pull Ups','Advanced Schedule', 10, 3, 'A001'),
('SCH002', 'EXE006', 'Lunges','Advanced Schedule', 12, 3,'A001'),
('SCH002', 'EXE007', 'Shoulder Press','Advanced Schedule', 10, 3,'A001'),
('SCH002', 'EXE008', 'Planks','Advanced Schedule', 60, 3,'A001'),
('SCH002', 'EXE003', 'Bench Press','Advanced Schedule', 12, 3,'A001'),
('SCH002', 'EXE009', 'Bicep Curls','Advanced Schedule', 15, 3,'A001'),
('SCH002', 'EXE010', 'Tricep Dips','Advanced Schedule', 12, 3,'A001'),
('SCH003', 'EXE001', 'Push Ups','Strength Training', 20, 4, 'A001'),
('SCH003', 'EXE005', 'Pull Ups','Strength Training', 10, 3,'A001'),
('SCH003', 'EXE004', 'Deadlifts','Strength Training', 15, 4,'A001'),
('SCH003', 'EXE002', 'Squats', 'Strength Training',25, 4, 'A001'),
('SCH003', 'EXE003', 'Bench Press','Strength Training', 15, 4,'A001'),
('SCH003', 'EXE006', 'Lunges','Strength Training', 15, 3,'A001'),
('SCH003', 'EXE010', 'Tricep Dips','Strength Training', 12, 3,'A001'),
('SCH003', 'EXE009', 'Bicep Curls', 'Strength Training',15, 3,'A001'),
('SCH003', 'EXE008', 'Planks', 'Strength Training',60, 3,'A001');

create table diet_plan(
diet_plan_id varchar(10) PRIMARY KEY,
admin_id varchar(10) NOT NULL,
name varchar(100) NOT NULL,
duration varchar(20) NOT NULL,
description varchar(200) NOT NULL
);

INSERT INTO diet_plan VALUES 
('DP000', 'A001', 'No Diet Plan', '0 days', 'No Diet Plan'),
('DP001', 'A001', 'Weight Loss Plan', '30 days', 'A low-calorie diet plan focusing on weight loss.'),
('DP002', 'A001', 'Muscle Gain Plan', '45 days', 'A high-protein diet plan for muscle gain.'),
('DP003', 'A001', 'Keto Diet Plan', '21 days', 'A ketogenic diet plan with high fats and low carbs.'),
('DP004', 'A001', 'Vegan Detox Plan', '14 days', 'A plant-based detox plan for cleansing the body.'),
('DP005', 'A001', 'Balanced Diet Plan', '60 days', 'A well-balanced diet plan for overall health.'),
('DP006', 'A001', 'Intermittent Fasting Plan', '30 days', 'A diet plan incorporating intermittent fasting methods.');

-- Updating 'No Diet Plan'
UPDATE diet_plan
SET description = 'No specific dietary recommendations. Users follow their regular eating habits without restrictions.'
WHERE diet_plan_id = 'DP000';

-- Updating 'Weight Loss Plan'
UPDATE diet_plan
SET description = 'A low-calorie diet plan focusing on weight loss. Breakfast: 1 cup of vegetable poha (150 calories) and 1 cup (200 ml) of low-fat milk (60 calories). Mid-Morning Snack: 1 small bowl of mixed fruit chaat (80 calories). Lunch: 2 small chapattis (160 calories) with 1 small bowl of palak dal (150 calories). Evening Snack: 1 cup of masala chai (40 calories) and 2 pieces of dhokla (160 calories). Dinner: 1 small bowl of vegetable khichdi (150 calories) with cucumber and tomato salad (100 calories).'
WHERE diet_plan_id = 'DP001';

-- Updating 'Muscle Gain Plan'
UPDATE diet_plan
SET description = 'A high-protein diet plan designed to build muscle. Breakfast: 3 egg whites and 2 slices of whole-grain toast (250 calories), and 1 banana (90 calories). Mid-Morning Snack: 1 cup of Greek yogurt with nuts and honey (150 calories). Lunch: Grilled chicken breast (200 calories) with quinoa (150 calories) and steamed broccoli (50 calories). Evening Snack: 1 protein shake with almond milk (200 calories). Dinner: Baked salmon (200 calories) with sweet potato (150 calories) and a side salad (80 calories).'
WHERE diet_plan_id = 'DP002';

-- Updating 'Keto Diet Plan'
UPDATE diet_plan
SET description = 'A ketogenic diet plan with high fats and low carbohydrates. Breakfast: 2 scrambled eggs cooked in butter (200 calories) with avocado slices (120 calories). Mid-Morning Snack: Handful of mixed nuts (150 calories). Lunch: Grilled chicken thigh (250 calories) with cauliflower rice (50 calories) and cheese (100 calories). Evening Snack: 1 slice of keto bread with almond butter (120 calories). Dinner: Zucchini noodles (100 calories) with creamy Alfredo sauce and shrimp (200 calories).'
WHERE diet_plan_id = 'DP003';

-- Updating 'Vegan Detox Plan'
UPDATE diet_plan
SET description = 'A plant-based detox plan for cleansing the body. Breakfast: Smoothie with spinach, banana, almond milk, and chia seeds (200 calories). Mid-Morning Snack: 1 cup of fresh vegetable juice (80 calories). Lunch: Lentil soup (150 calories) with a mixed green salad (80 calories). Evening Snack: Handful of roasted chickpeas (100 calories). Dinner: Stir-fried tofu (150 calories) with quinoa (150 calories) and steamed vegetables (50 calories).'
WHERE diet_plan_id = 'DP004';

-- Updating 'Balanced Diet Plan'
UPDATE diet_plan
SET description = 'A well-balanced diet plan for overall health. Breakfast: Oatmeal with berries and a drizzle of honey (200 calories). Mid-Morning Snack: 1 small apple with almond butter (120 calories). Lunch: Grilled chicken (200 calories) with brown rice (150 calories) and a side of steamed spinach (50 calories). Evening Snack: 1 small bowl of yogurt with granola (150 calories). Dinner: Baked fish (200 calories) with roasted vegetables (100 calories) and a small serving of mashed potatoes (120 calories).'
WHERE diet_plan_id = 'DP005';

-- Updating 'Intermittent Fasting Plan'
UPDATE diet_plan
SET description = 'A diet plan incorporating intermittent fasting methods (e.g., 16:8 fasting window). Eating Window (Lunch): Grilled turkey wrap (250 calories) with a side of sweet potato fries (150 calories). Mid-Afternoon Snack: 1 boiled egg (70 calories) and a handful of nuts (100 calories). Dinner: Grilled chicken salad with olive oil dressing (300 calories).'
WHERE diet_plan_id = 'DP006';

create table fitness_center(
center_id varchar(10) PRIMARY KEY,
admin_id varchar(10) NOT NULL,
center_name varchar(100) NOT NULL,
location varchar(100) NOT NULL,
FOREIGN KEY (admin_id) references admin(admin_id) ON UPDATE CASCADE ON DELETE CASCADE
);

insert into fitness_center values ("FC001", "A001", "Moronthuduwa Center", "Moronthuduwa");

create table employee(
employee_id varchar(10) PRIMARY KEY,
center_id varchar(10) NOT NULL,
name varchar(100) NOT NULL,
phone_number int NOT NULL,
date_of_hire date NOT NULL,
position varchar(100) NOT NULL,
age int NOT NULL,
address varchar(200) NOT NULL,
FOREIGN KEY (center_id) references fitness_center(center_id) ON UPDATE CASCADE ON DELETE CASCADE
); 

INSERT INTO employee VALUES 
('EMP001', 'FC001', 'Saman Kumara', 0761298276, '2022-01-10', 'Administrator', 35, 'Panadura'),
('EMP002', 'FC001', 'Janith Sampath', 0719299945, '2021-05-15', 'Cleaner', 28, 'Kaluthara');

create table employee_salary(
salary_id varchar(10) PRIMARY KEY,
date date NOT NULL,
amount double NOT NULL,
admin_id varchar(10) NOT NULL,
employee_id varchar(10) NOT NULL,
FOREIGN KEY (admin_id) references admin(admin_id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (employee_id) references employee_salary() ON UPDATE CASCADE ON DELETE CASCADE 
);

INSERT INTO employee_salary VALUES 
('SAL001', '2022-03-31', 1500.00, 'A001', 'EMP001'),
('SAL002', '2021-07-31', 1000.00, 'A001', 'EMP002'),
('SAL003', '2022-04-31', 1500.00, 'A001', 'EMP001'),
('SAL004', '2021-08-31', 1000.00, 'A001', 'EMP002'),
('SAL005', '2022-05-31', 1500.00, 'A001', 'EMP001'),
('SAL006', '2021-09-31', 1000.00, 'A001', 'EMP002');


create table payment_plan(  
plan_id varchar(10) PRIMARY KEY,
plan_name varchar(100) NOT NULL,
price double NOT NULL
);

insert into payment_plan values 
("PL001", "Monthly Plan", 3000),
("PL002", "Half Year Plan", 15000),
("PL003", "Year Plan", 30000);

create table member(
member_id varchar(10) PRIMARY KEY,
name varchar(100) UNIQUE,
address varchar(200) NOT NULL,
phone_number varchar(10) NOT NULL,
email varchar(100) NOT NULL,
register_date date NOT NULL,
year_of_birth date NOT NULL,
weight double NOT NULL,
height double NOT NULL,
schedule_id varchar(10) NOT NULL,
plan_id varchar(10) NOT NULL,
diet_plan_id varchar(10),
FOREIGN KEY (schedule_id) references schedule(schedule_id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (plan_id) references payment_plan(plan_id) ON UPDATE CASCADE ON DELETE CASCADE, 
FOREIGN KEY (diet_plan_id) references diet_plan(diet_plan_id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- ALTER TABLE member
-- RENAME COLUMN date_of_birth to year_of_birth;

-- ALTER TABLE member
-- ALTER COLUMN year_of_birth YEAR;

insert into member values (
"M002",
"Lahiru",
"Moronthuduwa", 
"0761298256", 
"lahiru@gmail.com", 
"2024-01-1", 
"2006-05-24", 
48, 
5.3, 
"SCH001", 
"PL001",
"DP000");

create table payment(
payment_id varchar(10) PRIMARY KEY,
payment_date date UNIQUE,
admin_id varchar(10) NOT NULL
);

insert into payment values 
("P001", "2024-01-20", "A001"),
("P002", "2024-02-20", "A001");

create table paymentDetail(
payment_id varchar(10) NOT NULL,
member_id varchar(10) NOT NULL,
member_name varchar(100) NOT NULL,
payment_date date NOT NULL,
price double NOT NULL,
payment_method varchar(10) NOT NULL,
FOREIGN KEY (payment_id) references payment(payment_id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (member_id) references member(member_id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (member_name) references member(name) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (payment_date) references payment(payment_date) ON UPDATE CASCADE ON DELETE CASCADE
); 

insert into paymentDetail values
("P001", "M001", "Lahiru Mudith", "2024-01-20", 3000, "Cash"),
("P002", "M001", "Lahiru Mudith", "2024-02-20", 3000, "Cash");

create table positionItem(
positionName varchar(100) PRIMARY KEY
);
insert into positionItem values
("Trainer");




























