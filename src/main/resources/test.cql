CREATE KEYSPACE myKeySpace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 2 };

CREATE TYPE validationRecord (
fieldName text,
errorLevel int,
errorCode int);

CREATE TABLE medicalClaim (
id text PRIMARY KEY,
packageId text,
name text,
additionalProperties map<text, text>,
validationRecords list< frozen<validationRecord>>);

INSERT INTO medicalClaim json '{
"id":"12345",
"packageId":"12345",
"name":"test",
"additionalProperties":{
"a":"b"
},
"validationRecords":[
{"fieldName":"id",
"errorCode":2,
"errorLevel":1}
]
}';