<template>
  <div class="english-view">
    <h2>English Learning Modules</h2>
    <p>Please select a module to continue:</p>
    <div class="module-selection">
      <button @click="selectModule('dictionary')" class="module-button">词典 (Dictionary)</button>
      <button @click="selectModule('vocabulary')" class="module-button">生词本 (Vocabulary Book)</button>
      <button @click="selectModule('sentences')" class="module-button">收藏例句 (Favorite Sentences)</button>
    </div>
    <div v-if="selectedModule" class="selected-module-area">
      <h3>{{ currentModuleDisplayName }}</h3>
      <p>This is the area for the selected module: {{ currentModuleDisplayName }}. Content to be implemented.</p>
      <!-- Future content for Dictionary, Vocabulary, or Sentences will go here or be routed -->
    </div>
  </div>
</template>

<script>
export default {
  name: 'EnglishView',
  data() {
    return {
      selectedModule: null, // e.g., 'dictionary', 'vocabulary', 'sentences'
      moduleDisplayNames: {
        dictionary: "词典 (Dictionary)",
        vocabulary: "生词本 (Vocabulary Book)",
        sentences: "收藏例句 (Favorite Sentences)"
      }
    };
  },
  computed: {
    currentModuleDisplayName() {
      if (this.selectedModule) {
        return this.moduleDisplayNames[this.selectedModule];
      }
      return "";
    }
  },
  methods: {
    selectModule(moduleName) {
      this.selectedModule = moduleName;
      console.log("Selected module:", moduleName);
      // In the future, this could navigate to a sub-route or load specific components
      // For now, it just updates the selectedModule data property to show a message.
    }
  }
}
</script>

<style scoped>
.english-view {
  padding: 20px;
  text-align: center; /* Center align the content of this view */
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
  gap: 15px; /* Spacing between buttons */
  margin-bottom: 30px;
}

.module-button {
  padding: 12px 25px;
  font-size: 1rem;
  color: #ffffff;
  background-color: #0366d6; /* GitHub blue */
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.2s ease-in-out;
}

.module-button:hover {
  background-color: #005cc5; /* Darker blue on hover */
}

.selected-module-area {
  margin-top: 20px;
  padding: 20px;
  border: 1px dashed #d1d5da; /* Dashed border for placeholder content */
  border-radius: 6px;
  background-color: #f9f9f9;
}
</style>
