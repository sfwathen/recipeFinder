create table users(
					username varchar(25) primary key,
                    password varchar(25) not null
					);
                    
create table dish(
					dishID int auto_increment primary key,
                    dishName varchar(50) not null,
                    description varchar(500)
					);
                    
create table recipe(
					amount int not null,
                    ingredient varchar(50) not null,
                    dID int,
                    foreign key(dID) references dish(dishID) on delete cascade,
                    primary key (dId, ingredient)
					);

create table saved(
					savedUser varchar(25),
                    savedID int,
                    foreign key(savedUser) references users(username),
                    foreign key(savedID) references dish(dishID) on delete cascade,
                    primary key (savedUser, savedID)
					);