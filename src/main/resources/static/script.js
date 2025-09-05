// script.js
async function onLogin(userId, examTitle) {
  try {
    const res = await fetch(`/examAttempt/resume?userId=${userId}&examTitle=${examTitle}`);
    const attempt = await res.json();
    if (attempt) {
      // Load exam state
      loadQuestions(attempt.answersJson); // implement this
      showQuestion(attempt.currentQuestionIndex); // implement this
    }
  } catch (err) {
    console.error('Error resuming exam:', err);
  }
}

async function autoSave(attempt) {
  try {
    await fetch('/examAttempt/save', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(attempt)
    });
  } catch (err) {
    console.error('Error during autosave:', err);
  }
}
