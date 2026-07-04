# RESEARCH-DAY-02.md

## Q1. What is a Java generic type? Why is <T> useful?
A generic type is a class or method that can work with any data type,
specified when you use it. The `<T>` is just a placeholder name for
that type. It is useful because one `Response<T>` class can hold a
`CategoryDto`, a `List<CategoryDto>`, or anything else without
duplicating code and without losing type safety.

## Q2. What does Lombok @Builder generate behind the scenes?
@Builder creates a static inner class called `Builder` that has one
method per field. Each method sets the field and returns the builder
itself so you can chain calls. It also adds a `builder()` method to
start the chain and a `build()` method at the end to create the
actual object.

## Q3. What is the Builder design pattern? When to use it?
The Builder pattern is a way to construct a complex object step by
step instead of passing all values in one big constructor. Use it
when an object has many fields and not all of them are always needed.
For example, Response sometimes has data and sometimes does not.

## Q4. What is LocalDateTime? How is it different from Date?
LocalDateTime stores a date and a time together (e.g. 2026-06-18T08:42:11)
but with no timezone. The old `Date` class is mutable, harder to use,
and mixes timezone concerns in. LocalDateTime is from the java.time
package introduced in Java 8, is immutable, and is much easier to
format and compare.

## Q5. Why does a consistent response format matter to frontend developers?
If every endpoint returns the same shape, the frontend can write one
function to handle all responses. The developer always knows where the
data is, where the error message is, and what the status code means —
instead of guessing per endpoint.

## Q6. What does @JsonInclude(JsonInclude.Include.NON_NULL) do?
It tells Jackson to skip any field that is null when converting the
object to JSON. So if `data` is null in an error response, it will
not appear in the JSON output at all, keeping the response clean.

## Q7. What is a static factory method? Why use Response.success(...)?
A static factory method is a static method on a class that builds and
returns an instance. `Response.success("message", data)` is more
readable than `new Response<>(200, "message", data, LocalDateTime.now())`.
It also hides the construction details,so they can be changed later
without touching every call site.



## Self-Quiz

### Q1. Why use <T> instead of Object for the data field?
With `Object`, the caller has to cast the result and mistakes only
show up at runtime. With `<T>`, the compiler checks types at compile
time, so errors are caught early.

### Q2. Difference between Response<T> and ResponseEntity<T>?
`ResponseEntity<T>` is a Spring class that controls the HTTP layer —
the status code, headers, and body. `Response<T>` is our custom class
that shapes the JSON body. We combine both:
`ResponseEntity<Response<CategoryDto>>` gives HTTP control and a
consistent JSON format at the same time.

### Q3. If a request fails, what statusCode does Response hold?
The matching HTTP error code. For example, 404 if the category is not
found, 400 if validation fails, 403 if the user is not allowed.

### Q4. Why add a timestamp?
Timestamp helps with debugging because you can see exactly when the response
was produced. It is also useful for audit trails and for the frontend
to show "last updated" times.