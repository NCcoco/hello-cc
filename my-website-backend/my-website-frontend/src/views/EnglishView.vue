<template>
  <div class="english-view">
    <h2>English Learning Modules</h2>
    <p>Please select a module to continue:</p>
    <div class="module-selection">
      <router-link to="/dictionary" class="module-button">词典 (Dictionary)</router-link>
      <router-link to="/vocabulary" class="module-button">生词本 (Vocabulary Book)</router-link>
      <router-link to="/sentences" class="module-button">收藏例句 (Favorite Sentences)</router-link>
    </div>
    <!-- Removed the local selectedModule display area, as navigation will handle view changes -->
  </div>
</template>

<script>
export default {
  name: 'EnglishView'
  // Removed data, computed, and methods related to local module display
}
</script>

<style scoped>
.english-view {
  padding: 20px;
  text-align: center;
}

.english-view h2 {
  margin-bottom: 10px;
}

.english-view p {
  margin-bottom: 20px;
  color: #586069;
}

.module-selection {
  display: flex;
  justify-content: center;
  gap: 15px;
  margin-bottom: 30px;
}

.module-button {
  padding: 12px 25px;
  font-size: 1rem;
  color: #ffffff;
  background-color: #0366d6;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  text-decoration: none; /* For router-link styled as button */
  display: inline-block; /* For router-link styled as button */
  transition: background-color 0.2s ease-in-out;
}

.module-button:hover {
  background-color: #005cc5;
}
</style>
