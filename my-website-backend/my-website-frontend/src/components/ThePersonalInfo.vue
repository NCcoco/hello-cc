<template>
  <div class="personal-info-card">
    <div class="avatar-section">
      <img src="https://via.placeholder.com/150" alt="User Avatar" class="avatar-image">
    </div>
    <div class="info-section">
      <h2 class="user-name">CC</h2>
      <p class="user-bio">A brief bio about yourself. This can be a couple of sentences describing your interests, profession, or anything you'd like to share.</p>
      <ul class="contact-details">
        <li><strong>Email:</strong> your.email@example.com</li>
        <li><strong>LinkedIn:</strong> linkedin.com/in/yourprofile</li>
        <li><strong>GitHub:</strong> github.com/yourusername</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ThePersonalInfo'
}
</script>

<style scoped>
.personal-info-card {
  display: flex;
  align-items: flex-start; /* Align items to the top */
  padding: 20px;
  margin-bottom: 20px; /* Space below the card */
  border: 1px solid #e1e4e8; /* Lighter border */
  border-radius: 6px;
  background-color: #f9f9f9; /* Slightly different background for the card */
}

.avatar-section {
  margin-right: 20px;
  flex-shrink: 0; /* Prevent avatar section from shrinking */
}

.avatar-image {
  width: 150px;
  height: 150px;
  border-radius: 50%; /* Circular avatar */
  border: 3px solid #ffffff;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.info-section {
  text-align: left; /* Align text to the left within this section */
  flex-grow: 1; /* Allow info section to take available space */
}

.user-name {
  margin-top: 0; /* Remove default margin for h2 */
  margin-bottom: 10px;
  font-size: 1.8rem;
  color: #333;
}

.user-bio {
  font-size: 0.95rem;
  color: #586069; /* GitHub text color */
  line-height: 1.6;
  margin-bottom: 15px;
}

.contact-details {
  list-style: none;
  padding: 0;
  margin: 0;
}

.contact-details li {
  font-size: 0.9rem;
  color: #586069;
  margin-bottom: 5px;
}

.contact-details li strong {
  color: #333;
}
</style>
