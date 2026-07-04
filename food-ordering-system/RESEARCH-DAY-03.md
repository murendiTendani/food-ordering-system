# RESEARCH-DAY-03.md

## Q1. What is JPA? What is Hibernate? How are they related?
JPA (Jakarta Persistence API) is a specification that defines rules for
mapping Java objects to database tables. Hibernate is an implementation
of JPA. JPA defines the interface; Hibernate does the actual work.

## Q2. What is the difference between @Entity and @Table?
@Entity tells JPA this Java class maps to a database table. @Table lets
you customise the table name. Without @Table, JPA uses the class name.
With @Table(name = "menus") we force the table to be called "menus".

## Q3. What is a foreign key? What is @ManyToOne? Give 2 real examples.
A foreign key is a column that links to the primary key of another table.
@ManyToOne means many records relate to one record in another table.
For Example:
1. Many menus belong to one category.
2. Many orders belong to one customer.

## Q4. What does @JoinColumn(name = "category_id") do?
It creates a column called "category_id" in the menus table that stores
the id of the related category. Without it JPA generates its own name.

## Q5. Why store price as BigDecimal and not double?
double uses binary floating point and cannot represent some decimals
exactly. BigDecimal stores exact values. For money this matters because
rounding errors cause financial mistakes.

## Q6. What does FetchType LAZY vs EAGER mean?
LAZY loads the related object only when you access it. EAGER loads it
immediately with the parent. The default for @ManyToOne is EAGER but
LAZY is usually preferred to avoid loading unnecessary data.

## Q7. What is the N+1 query problem?
Loading N menus triggers N extra queries to load each category — one
per menu plus the original list query. This is slow and fixed using
JOIN FETCH or @EntityGraph.

## Q8. What is dependency injection? Constructor vs field injection?
Dependency injection means a class receives its dependencies from
outside. Constructor injection passes them through the constructor.
Field injection uses @Autowired on fields. Constructor injection is
preferred because it is testable and makes dependencies visible.

## Q9. What does @RequiredArgsConstructor do?
It generates a constructor for all private final fields. Spring uses
that constructor to inject dependencies automatically.

## Q10. What is the role of the SERVICE layer?
The service layer contains business logic. The controller only handles
HTTP. Keeping them separate means the same logic can be reused and
tested without HTTP.

## Q11. Why validate categoryId exists before saving a menu?
Without the check, a bad categoryId causes a database foreign key error
which gives the client an unhelpful 500. Checking first lets us return
a clean 404 with a useful message.

## Q12. Difference between save() and saveAndFlush()?
save() may wait until the transaction commits before sending SQL.
saveAndFlush() sends the SQL immediately. Use saveAndFlush() when you
need the database to reflect changes right away.

## Q13. Why write private mapper methods?
Mapper methods keep conversion logic in one place. If the entity or DTO
changes you only update the mapper. It also keeps service methods short
and readable.

---

## Self-Quiz

### Q1. Why no @OneToMany on Category?
We used a unidirectional relationship. Category does not need to know
about menus for our use cases. Bidirectional adds complexity and can
cause infinite loops in JSON serialisation.

### Q2. What would ddl-auto = create-drop do?
Creates the schema on startup and drops it on shutdown. Useful in
development but never in production — all data is lost every restart.

### Q3. If you delete a Category that has menus, what happens?
The database throws a foreign key constraint error because menus still
reference that category. You must delete the menus first or configure
cascade delete.

### Q4. Why is BigDecimal better than double for money?
BigDecimal is exact. double produces tiny rounding errors in binary
representation. In financial calculations those errors add up and cause
real problems.