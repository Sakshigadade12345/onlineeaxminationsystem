
    document.getElementById('javaQuizForm').addEventListener('submit', async function(e) {
        e.preventDefault();

        const answers = {
            q1: "D",
            q2: "B",
            q3: "B"
        };

        let score = 0;
        for (let key in answers) {
            const selected = document.querySelector(`input[name="${key}"]:checked`);
            if (selected && selected.value === answers[key]) {
                score++;
            }
        }

        // Get userId from session
        const userResponse = await fetch('/session-user');
        const user = await userResponse.json();

        const exam = {
            userId: user.userId,
            examTitle: "Java Basics Quiz",
            totalMarks: score
        };

        // POST to /Exam
        const response = await fetch('/Exam', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(exam)
        });

        if (response.ok) {
            const totalQuestions = Object.keys(answers).length;
            window.location.href = `result.html?score=${score}&total=${totalQuestions}`;
        } else {
            alert("Failed to submit exam.");
        }
    });

