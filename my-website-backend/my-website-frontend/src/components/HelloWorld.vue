<template>
  <div class="placeholder-content">
    <h2>{{ pageTitle }}</h2>
    <p v-if="msg">{{ msg }}</p>
    <p v-else>This section is currently under development.</p>
    <!-- Removed ThePersonalInfo and backend message fetching -->
  </div>
</template>

<script>
export default {
  name: 'HelloWorld', // Keep the name as HelloWorld if other routes still refer to it by this name for dynamic import
  props: {
    msg: String, // This prop will be passed from the router for pages like Java, AI, etc.
    pageTitle: String // Optional title for the placeholder page
  }
}
</script>

<style scoped>
.placeholder-content {
  padding: 20px;
  text-align: center;
  border: 1px solid #e1e4e8;
  border-radius: 6px;
  background-color: #f9f9f9;
  min-height: 200px; /* Give it some default height */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.placeholder-content h2 {
  color: #333;
  margin-bottom: 10px;
}

.placeholder-content p {
  color: #586069;
  font-size: 1.1rem;
}
</style>
