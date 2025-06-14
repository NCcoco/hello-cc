<template>
  <div> <!-- New root element since template can only have one -->
    <TheNavbar /> <!-- Navbar is now outside #app-wrapper -->
    <div id="app-wrapper">
      <img alt="Vue logo" src="./assets/logo.png">
      <router-view/>
    </div>
  </div>
</template>

<script>
import TheNavbar from './components/TheNavbar.vue';

export default {
  name: 'App',
  components: {
    TheNavbar
  }
}
</script>

<style>
/* Global styles */
body {
  margin: 0;
  background-color: #f6f8fa; /* GitHub-like light gray background for areas outside content */
}

#app-wrapper {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;

  max-width: 1012px;
  margin: 0 auto; /* Center the content area */
  background-color: #ffffff; /* White background for the content area */
  border-left: 1px solid #d0d7de; /* Borders only on sides for content area */
  border-right: 1px solid #d0d7de;
  /* border-top and border-bottom might be removed if navbar handles top and a footer handles bottom */
  /* For now, let's keep a bottom border and space for content */
  border-bottom: 1px solid #d0d7de;
  padding: 20px; /* Add padding inside the content area */

  /* Adjust margin-top if navbar has fixed height, or handle spacing globally */
  /* margin-top: 30px; -- This was for when wrapper was the topmost */
  /* margin-bottom: 30px; */
  min-height: calc(100vh - 60px); /* Example: if navbar is 60px, make wrapper fill rest of height */
}

/* Styling for the logo, can be adjusted */
#app-wrapper img[alt="Vue logo"] {
  display: block;
  margin: 0 auto 20px auto; /* Center logo if it's kept, with space below */
}
</style>
