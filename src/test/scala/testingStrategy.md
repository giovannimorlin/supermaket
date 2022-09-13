

* Verify correct HTTP status code. 
  * For example, creating a resource should return 201 CREATED and unpermitted requests should return 403 FORBIDDEN, etc.

* Verify response payload. 
  * Check valid JSON body and correct field names, types, and values — including in error responses.

* Verify response headers. 
  * HTTP server headers have implications on both security and performance.

* Verify correct application state. 
  * This is optional and applies mainly to manual testing, or when a UI or another interface can be easily inspected.

* Verify basic performance sanity. 
  * If an operation was completed successfully but took an unreasonable amount of time, the test fails.