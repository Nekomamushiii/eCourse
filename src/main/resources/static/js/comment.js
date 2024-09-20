document.addEventListener("DOMContentLoaded", function() {
    const courseId = document.getElementById('courseId').value;
    const commentForm = document.getElementById('commentForm');
    const commentContainer = document.getElementById('commentCtnr');

    const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content;
    const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;

    // Load existing comments when the page loads
    loadComments(courseId);

    // Add event listener for the form submission
    commentForm.addEventListener("submit", handleFormSubmission);

    // Function to handle form submission
    async function handleFormSubmission(event) {
        event.preventDefault();

        const messageValue = document.getElementById('message').value;

        // Basic client-side validation
        if (messageValue.length < 10) {
            showError("Message should be at least 10 characters long.");
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/api/${courseId}/comments`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    [csrfHeaderName]: csrfHeaderValue
                },
                body: JSON.stringify({ message: messageValue })
            });

            // Check if the response is valid and JSON
            if (response.ok) {
                const comment = await response.json();
                document.getElementById('message').value = ""; // Clear the textarea
                commentContainer.innerHTML += commentAsHtml(comment); // Append new comment
            } else {
                const errorText = await response.text();
                throw new Error(errorText || 'Failed to post the comment.');
            }
        } catch (error) {
            showError(error.message || 'An error occurred while posting your comment.');
        }
    }

    // Function to load comments from the server
    async function loadComments(courseId) {
        try {
            const response = await fetch(`http://localhost:8080/api/${courseId}/comments`, {
                headers: {
                    'Accept': 'application/json'
                }
            });

            if (response.ok) {
                const comments = await response.json();
                comments.forEach(comment => {
                    commentContainer.innerHTML += commentAsHtml(comment);
                });
            } else {
                showError("Failed to load comments.");
            }
        } catch (error) {
            showError("Error loading comments. Please try again later.");
        }
    }

    // Function to render a comment as HTML
    function commentAsHtml(comment) {
        return `
            <div class="comment">
                <h4>${comment.authorName}</h4>
                <p>${comment.message}</p>
            </div>`;
    }

    // Function to show error messages
    function showError(message) {
        const messageError = document.getElementById('messageError');
        messageError.textContent = message;
        messageError.style.display = 'block';
        setTimeout(() => {
            messageError.style.display = 'none';
        }, 5000); // Hide the error message after 5 seconds
    }
});
