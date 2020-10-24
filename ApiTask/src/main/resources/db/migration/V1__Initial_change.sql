create table Tasks (
  id bigint not null GENERATED ALWAYS AS IDENTITY,
  primary key(id),

  name varchar(250) null,
  description varchar(250) null,
  create_date date,
  urgent boolean,
  status integer
);