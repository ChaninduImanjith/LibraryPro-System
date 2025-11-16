📚 LibraryPro System: CLI Library Management
Overview
The LibraryPro System is a robust, comprehensive Command Line Interface (CLI) application designed to modernize and automate the routine operations of a library. It aims to eliminate the inefficiencies associated with manual record-keeping by providing a highly efficient and reliable computerized platform.

Built adhering to strict project constraints, the system uses core Java features,including arrays for efficient data storage and manipulation.

Key Features
LibraryPro offers a complete suite of functionalities to manage all critical library operations securely and efficiently.

🔒 Authentication and Security
Secure Login: Restricts access to authorized personnel via a username and password validation system.

📖 Book Management
Manage the library's catalog:

Add Book: Register a new book with a unique Book ID, Title, Author, Genre, and Quantity.

Update Book: Modify the details of an existing book.

Delete Book: Permanently remove a book record.

Search Book: Find and display details of a specific book using its Book ID.

View All Books: Display the entire catalog in a clear tabular format.

👤 Member Management
Maintain detailed records of library members:

Add Member: Register a new member with a unique Member ID, Name, Contact Number, and Email.

Update Member: Modify the details of an existing member.

Delete Member: Remove a member's record permanently.

Search Member: Find and display details of a specific member using their Member ID.

View All Members: Display all registered members in a clear tabular format.

🔄 Transaction Management
Streamline the core lending process:

Issue Book: Lend an available book to a member, recording the transaction and due date, and decrementing the book's quantity.

Return Book: Process a book return, check for overdues, calculate fines, increment the book's quantity, and remove the transaction record.

📊 Reporting & Tracking
Gain insights into library activity:

Overdue Books Report: List all books past their due date, calculate the number of days overdue, and the corresponding fine amount.

Books Issued Per Member: Display the total number of books currently issued to each member.

Technical Constraints & Implementation Details
The system was developed under the following strict constraints:

Language: Pure Java (Core features only).

Architecture: Single-class implementation.

Data Storage: All data is stored in arrays (e.g., booksArray, membersArray, issuedBooksArray).

Input Handling: Primarily uses the Scanner class for CLI input.

Algorithms: All required logic (e.g., searching, shifting elements for deletion) is manually implemented without relying on external libraries or complex built-in structures.

Validation: Robust validation mechanisms are implemented to ensure data integrity (e.g., unique IDs, positive quantity checks, valid format checks).