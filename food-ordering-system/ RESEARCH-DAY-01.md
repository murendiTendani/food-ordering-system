# RESEARCH-DAY-01.md

## Concepts

**Q1. What does CRUD stand for?**
CRUD stands for Create, Read, Update, and Delete. These are the four basic
operations you perform on data in a database or through an API.

**Q2. Difference between POST, PUT, PATCH, DELETE?**
- POST: Creates a new resource. The server assigns the ID.
- PUT: Fully replaces an existing resource. You send all fields.
- PATCH: Partially updates a resource. You only send the fields to change.
- DELETE: Removes a resource permanently.

**Q3. HTTP status codes:**
a. New category created → 201 Created
b. Category deleted successfully → 204 No Content
c. ID does not exist → 404 Not Found
d. Request body missing required field → 400 Bad Request
e. User is logged in but not allowed → 403 Forbidden

**Q4. @RequestBody, @RequestParam, @PathVariable**
- @PathVariable: Pulls a value from the URL path itself.
  Example: GET /api/categories/5 → @GetMapping("/{id}") + @PathVariable Long id
- @RequestParam: Pulls a value from the query string after ?.
  Example: GET /api/categories?name=pizza → @RequestParam String name
- @RequestBody: Reads the JSON in the request body and maps it to a Java object.
  Example: POST /api/categories body {"name":"Drinks"} → @RequestBody CategoryDto dto

**Q5. Jakarta Bean Validation**
Jakarta Bean Validation lets you validate Java objects using annotations directly
on the fields. Spring checks these automatically when you add @Valid.
- @Valid: Tells Spring to run the validation rules on this parameter before
  the method executes.
- @NotBlank: Rejects null values and strings that are empty or only whitespace.
- @Size(min, max): Enforces a minimum and maximum length on a string field.

**Q6. Why return a DTO and not the entity?**
1. Security: The entity may contain sensitive internal fields like audit columns
   or foreign keys that clients should never see.
2. Decoupling: If the database schema changes, the DTO stays the same so the
   API contract does not break for consumers.

**Q7. What is Optional<T>?**
Optional<T> is a Java wrapper that may or may not contain a value. findById
returns Optional<Category> because the record might not exist in the database.
This forces you to explicitly handle the missing case, preventing
NullPointerExceptions and making the code safer and more readable.



## Self-Quiz

**Q1. Why ResponseEntity instead of returning the object directly?**
ResponseEntity gives you full control over the HTTP response including the
status code, headers, and body. Returning a plain object always sends 200 OK
even when the correct status should be 201 or 204.

**Q2. What status should a successful DELETE return? Why?**
204 No Content. The operation succeeded but there is no data to send back,
so the body is empty and 204 correctly communicates that.

**Q3. Update only one field — PUT or PATCH? Defend your answer.**
PATCH is the correct choice because it is designed for partial updates.
PUT is meant to replace the entire resource, so semantically using PUT to
change only one field is incorrect even if it works technically.

**Q4. What happens if you forget @Valid on the controller?**
Spring completely ignores all validation annotations on the DTO. Any input
including empty strings, nulls, and values that violate @Size rules will
pass through to the service without any checking.

**Q5. Why must update/delete have {id} in the URL but create does not?**
When creating a resource the server generates the ID so the client does not
know it yet. When updating or deleting you must identify which specific record
to act on, so the ID goes in the URL to target that exact resource.