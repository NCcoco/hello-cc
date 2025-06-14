<template>
  <div>
    <TheNavbar />
    <div id="app-wrapper">
      <!-- Vue Logo was here, now removed -->
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
  background-color: #f6f8fa;
}

#app-wrapper {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;

  max-width: 1012px;
  margin: 0 auto;
  background-color: #ffffff;
  border-left: 1px solid #d0d7de;
  border-right: 1px solid #d0d7de;
  border-bottom: 1px solid #d0d7de;
  padding: 20px;
  min-height: calc(100vh - 60px); /* Assuming navbar height of approx 60px */
}

/* Styles for the logo that were in App.vue are no longer needed here globally */
/*
#app-wrapper img[alt="Vue logo"] {
  display: block;
  margin: 0 auto 20px auto;
}
*/
</style>
