select first_name, birthday
from users
where birthday > '1999-10-23 00:00:00'
order by first_name
limit 3;