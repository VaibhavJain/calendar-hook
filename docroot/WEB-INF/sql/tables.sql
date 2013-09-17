create table cal_CalEventAttendee (
	calEventAttendeeId LONG not null primary key,
	eventId LONG,
	userId LONG,
	assist BOOLEAN
);