<template>
  <div class="home-view">
    <img alt="Vue logo" src="../assets/logo.png" class="home-vue-logo"> <!-- Added Vue Logo -->
    <ThePersonalInfo />
    <div class="welcome-message">
      <h1>{{ msg }}</h1>
      <p>Backend Message: {{ backendMessage }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import ThePersonalInfo from '../components/ThePersonalInfo.vue';

export default {
  name: 'HomeView',
  components: {
    ThePersonalInfo
  },
  props: {
    msg: {
      type: String,
      default: 'Welcome to Your Personal Site'
    }
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
        console.error("Error fetching data for HomeView: ", error);
      });
  }
}
</script>

<style scoped>
.home-view {
  padding: 15px;
  text-align: left;
}

.home-vue-logo { /* Style for the logo specifically on the homepage */
  display: block;
  margin: 0 auto 20px auto; /* Center logo, space below */
  width: 100px; /* Example size, adjust as needed */
  height: auto;
}

.welcome-message {
  background-color: #fff;
  padding: 20px;
  border-radius: 6px;
  border: 1px solid #e1e4e8;
  margin-top: 20px;
}

.welcome-message h1 {
  color: #333;
  text-align: center;
}

.welcome-message p {
  color: #555;
  text-align: center;
}
</style>
