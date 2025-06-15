<template>
  <div class="dictionary-view">
    <div class="search-container">
      <input
        type="text"
        class="search-input"
        v-model="searchQuery"
        placeholder="Search dictionary..."
        @keyup.enter="performSearch"
        :disabled="isLoading"
      />
      <button @click="performSearch" class="search-button" :disabled="isLoading">
        <svg v-if="!isLoading" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" width="24px" height="24px">
          <path d="M15.5 14h-.79l-.28-.27A6.471 6.471 0 0 0 16 9.5 6.5 6.5 0 1 0 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"/>
          <path d="M0 0h24v24H0z" fill="none"/>
        </svg>
        <div v-if="isLoading" class="loader"></div> <!-- Simple loader -->
      </button>
    </div>

    <div v-if="isLoading" class="loading-message">
        <p>Loading results for "{{ submittedQuery }}"...</p>
    </div>

    <div class="results-container" v-if="!isLoading && showResults && searchResult">
      <h2 class="word-title">{{ searchResult.wordText }}</h2> <!-- Changed from searchResult.word -->

      <div v-for="(entry, index) in searchResult.definitions" :key="index" class="result-entry"> <!-- Changed from searchResult.entries -->
        <h3 class="part-of-speech">{{ entry.partOfSpeech }}</h3>

        <div class="meanings-section">
          <h4>Meanings:</h4>
          <ul>
            <li v-for="(meaning, mIndex) in entry.meanings" :key="mIndex" class="meaning-item">
              {{ meaning.definitionText }} <!-- Changed from meaning.definition -->
              <span v-if="meaning.exampleSentenceForMeaning" class="example-sentence-inline"> (e.g., "{{ meaning.exampleSentenceForMeaning }}")</span> <!-- Changed from meaning.example -->
            </li>
          </ul>
        </div>

        <div class="inflections-section" v-if="entry.inflections && hasInflections(entry.inflections)"> <!-- Added null check for entry.inflections -->
          <h4>Inflections:</h4>
          <ul class="inflections-list">
            <li v-if="entry.inflections.plural"><strong>Plural:</strong> {{ entry.inflections.plural }}</li>
            <li v-if="entry.inflections.thirdPersonSingular"><strong>3rd Person Singular:</strong> {{ entry.inflections.thirdPersonSingular }}</li>
            <li v-if="entry.inflections.pastTense"><strong>Past Tense:</strong> {{ entry.inflections.pastTense }}</li>
            <li v-if="entry.inflections.pastParticiple"><strong>Past Participle:</strong> {{ entry.inflections.pastParticiple }}</li>
            <li v-if="entry.inflections.presentParticiple"><strong>Present Participle:</strong> {{ entry.inflections.presentParticiple }}</li>
          </ul>
        </div>

        <div class="general-example-sentences-section" v-if="entry.generalExampleSentences && entry.generalExampleSentences.length">
          <h4>General Examples (for {{ entry.partOfSpeech }}):</h4>
          <ul>
            <li v-for="(sentence, sIndex) in entry.generalExampleSentences" :key="sIndex" class="example-sentence-item">
              {{ sentence }}
            </li>
          </ul>
        </div>
      </div>

      <!-- Removed old top-level exampleSentences section, as examples are now part of WordDefinition or Meaning -->

    </div>
    <div class="no-results" v-if="!isLoading && showResults && !searchResult">
      <p>No results found for "{{ submittedQuery }}". Please check the spelling or try another word.</p>
    </div>
    <div class="error-message" v-if="!isLoading && errorMessage">
        <p>Error: {{ errorMessage }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios'; // Import axios

export default {
  name: 'DictionaryView',
  data() {
    return {
      searchQuery: '',
      submittedQuery: '',
      showResults: false,
      searchResult: null,
      isLoading: false,
      errorMessage: null,
    };
  },
  methods: {
    async performSearch() { // Changed to async
      if (!this.searchQuery.trim()) return;

      this.submittedQuery = this.searchQuery.trim();
      this.isLoading = true;
      this.showResults = false; // Hide previous results while loading
      this.searchResult = null;
      this.errorMessage = null;

      try {
        // The backend API is at /api/dictionary/{word}
        // Vue dev server proxy should be configured if backend is on different port (e.g. 8080)
        // and frontend on 8081. Assuming proxy is set up in vue.config.js for /api calls.
        const response = await axios.get('/api/dictionary/' + encodeURIComponent(this.submittedQuery));
        if (response.data) {
          this.searchResult = response.data;
        } else {
          // This case might not be hit if 404s are caught by .catch
          this.searchResult = null;
        }
      } catch (error) {
        console.error("Error fetching dictionary data:", error);
        this.searchResult = null;
        if (error.response && error.response.status === 404) {
          this.errorMessage = null; // "No results found" will be shown by v-if="!searchResult"
        } else if (error.request) {
          this.errorMessage = "Could not connect to the dictionary service. Please try again later.";
        } else {
          this.errorMessage = "An unexpected error occurred.";
        }
      } finally {
        this.isLoading = false;
        this.showResults = true; // Always show results area (either with data, no results, or error)
      }
    },
    hasInflections(inflections) {
      if (!inflections) return false;
      return Object.values(inflections).some(value => value !== null && value !== '' && value !== 0); // id is a number
    }
    // Removed getMockResult method
  }
}
</script>

<style scoped>
/* Styles from previous step, plus loader and messages */
.dictionary-view {
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.search-container {
  display: flex;
  align-items: center;
  width: 100%;
  max-width: 600px;
  border: 1px solid #dfe1e5;
  border-radius: 24px;
  padding: 5px 15px;
  box-shadow: 0 2px 5px 1px rgba(60,64,67,.15);
  margin-bottom: 30px;
}
.search-container:hover, .search-container:focus-within {
  box-shadow: 0 2px 8px 1px rgba(60,64,67,.25);
}
.search-input {
  flex-grow: 1;
  border: none;
  outline: none;
  padding: 10px;
  font-size: 16px;
  background-color: transparent;
}
.search-input:disabled {
  background-color: #f0f0f0; /* Indicate disabled state */
}
.search-button {
  background: none;
  border: none;
  padding: 8px;
  margin-left: 8px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #4285f4;
  min-width: 40px; /* Ensure button has some width for loader */
  min-height: 40px;
}
.search-button:disabled {
  cursor: not-allowed;
  opacity: 0.5;
}
.search-button:hover svg { opacity: 0.8; }

.loader {
  border: 4px solid #f3f3f3; /* Light grey */
  border-top: 4px solid #3498db; /* Blue */
  border-radius: 50%;
  width: 20px;
  height: 20px;
  animation: spin 1s linear infinite;
}
@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.loading-message, .no-results, .error-message {
  margin-top: 20px;
  color: #555;
  font-size: 1.1em;
}
.error-message {
    color: red;
    font-weight: bold;
}

.results-container {
  width: 100%;
  max-width: 800px;
  text-align: left;
  border: 1px solid #e0e0e0;
  padding: 20px;
  border-radius: 8px;
  background-color: #f9f9f9;
}
.word-title {
  font-size: 2.5em;
  color: #2c3e50;
  margin-bottom: 20px;
  border-bottom: 2px solid #7f8c8d;
  padding-bottom: 10px;
}
.result-entry { margin-bottom: 25px; }
.part-of-speech {
  font-style: italic;
  color: #3498db;
  margin-bottom: 10px;
  font-size: 1.4em;
}
.meanings-section h4,
.inflections-section h4,
.general-example-sentences-section h4 { /* Changed from .example-sentences-section */
  font-size: 1.1em;
  color: #333;
  margin-bottom: 8px;
  margin-top: 0;
}
.meanings-section ul,
.inflections-section ul,
.general-example-sentences-section ul { /* Changed */
  list-style-type: none;
  padding-left: 0;
}
.meanings-section .meaning-item,
.inflections-section .inflections-list li,
.general-example-sentences-section .example-sentence-item { /* Changed */
  margin-bottom: 8px;
  line-height: 1.6;
  font-size: 0.95em;
}
.inflections-list li strong { color: #555; }
.example-sentence-inline { font-style: italic; color: #777; }
.example-sentence-item { padding-left: 10px; border-left: 3px solid #bdc3c7; }
</style>
