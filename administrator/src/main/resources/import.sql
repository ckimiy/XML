insert into tuser(id,dtype,email,name,password,surname,username,pmb,location_id) values(300,"User","s@s","s","s","s","s","s", null)

insert into comment(id,approved,content,author_id, accomodation_id) values(300, false, "nestooooo", 300, 300)
insert into comment(id,approved,content,author_id, accomodation_id) values(301, false, "svastaaaa", 300, 300)

insert into accomodation_comment(comment_id, accomodation_id) values(300, 300)

insert into accomodation(id, description, max_person, name, category_id, location_id, owner_id, price_shedule_id, type_id) values(300, null, 3, null, null, null, null, null, null)

insert into object_category(id, category) values(300, "category")
insert into object_type(id, type) values(300, "type")
insert into services(id, service) values(300, "service")