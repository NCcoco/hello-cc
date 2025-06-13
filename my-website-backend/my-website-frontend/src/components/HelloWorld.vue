<template>
  <div class="page-content">
    <ThePersonalInfo /> <!-- Add PersonalInfo component -->
    <div class="hello-world-message">
      <h1>{{ msg }}</h1>
      <p>Backend Message: {{ backendMessage }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import ThePersonalInfo from './ThePersonalInfo.vue'; // Import PersonalInfo

export default {
  name: 'HelloWorld',
  components: {
    ThePersonalInfo // Register PersonalInfo
  },
  props: {
    msg: String
  },
  data() {
    return {
      backendMessage: 'Loading message from backend...'
    };
  },
  mounted() {
    axios.get('http://localhost:8080/api/hello')
      .then(response => {
        this.backendMessage = response.data;
      })
      .catch(error => {
        this.backendMessage = 'Failed to load message from backend: ' + error.message;
        console.error("Error fetching data: ", error);
      });
  }
}
</script>

<style scoped>
.page-content {
  padding: 15px; /* Add some padding to the overall page content area */
  text-align: left; /* Override App.vue's text-align: center for page content */
}

.hello-world-message {
  background-color: #fff;
  padding: 20px;
  border-radius: 6px;
  border: 1px solid #e1e4e8;
  margin-top: 20px; /* Space it from personal info card */
}

h1 { /* Target h1 within this component */
  color: #333;
  text-align: center; /* Center the h1 if desired */
}

p { /* Target p within this component */
  color: #555;
  text-align: center; /* Center the p if desired */
}
</style>
