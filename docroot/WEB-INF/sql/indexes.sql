create index IX_C5414928 on cal_CalEventAttendee (eventId);
create index IX_C637D0C5 on cal_CalEventAttendee (eventId, assist);
create index IX_1FB0B1F7 on cal_CalEventAttendee (userId);
create index IX_F214FA94 on cal_CalEventAttendee (userId, assist);
create index IX_94A720CB on cal_CalEventAttendee (userId, eventId, assist);