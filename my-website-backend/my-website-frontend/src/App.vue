<template>
  <div id="app-wrapper">
    <TheNavbar /> <!-- Add Navbar component -->
    <img alt="Vue logo" src="./assets/logo.png"> <!-- This might be removed later or integrated into a profile section -->
    <router-view/>
  </div>
</template>

<script>
import TheNavbar from './components/TheNavbar.vue'; // Import Navbar

export default {
  name: 'App',
  components: {
    TheNavbar // Register Navbar
  }
}
</script>

<style>
/* Global styles */
body {
  margin: 0;
  background-color: #f6f8fa;
}

#app-wrapper {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; -- Removing this as navbar and other content might not always be centered text-wise */
  color: #2c3e50;

  max-width: 1012px;
  margin: 0 auto;
  /* Padding might be adjusted if navbar is inside and has its own padding */
  /* Let's remove direct padding from app-wrapper for now and let navbar and router-view manage their spacing */
  /* padding: 20px;  */
  background-color: #ffffff;
  border: 1px solid #d0d7de;
  border-radius: 6px;
  margin-top: 30px;
  margin-bottom: 30px;
  /* Overflow hidden can be useful if child elements with negative margins are used for full-width effects inside */
  /* overflow: hidden; */
}

/* Styling for the logo, can be adjusted */
#app-wrapper img[alt="Vue logo"] {
  display: block;
  margin: 20px auto; /* Center logo if it's kept */
}
</style>
