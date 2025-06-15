<template>
  <div class="dictionary-view">
    <div class="search-container">
      <input
        type="text"
        class="search-input"
        v-model="searchQuery"
        placeholder="搜索词典..."
        @keyup.enter="performSearch"
        :disabled="isLoading"
      /> <!-- Placeholder translated comment moved or removed -->
      <button @click="performSearch" class="search-button" :disabled="isLoading">
        <svg v-if="!isLoading" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" width="24px" height="24px">
          <path d="M15.5 14h-.79l-.28-.27A6.471 6.471 0 0 0 16 9.5 6.5 6.5 0 1 0 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"/>
          <path d="M0 0h24v24H0z" fill="none"/>
        </svg>
        <div v-if="isLoading" class="loader"></div>
      </button>
    </div>

    <div v-if="isLoading" class="loading-message">
        <p>正在为 "{{ submittedQuery }}" 加载结果...</p> <!-- Translated -->
    </div>

    <div class="results-container" v-if="!isLoading && showResults && searchResult">
      <h2 class="word-title">{{ searchResult.wordText }}</h2>

      <div v-for="(entry, index) in searchResult.definitions" :key="index" class="result-entry">
        <div class="pos-and-meanings">
          <span class="part-of-speech">{{ getPartOfSpeechAbbreviation(entry.partOfSpeech) }}</span>
          <ul class="meanings-list">
            <li v-for="(meaning, mIndex) in entry.meanings" :key="mIndex" class="meaning-item">
              <!-- Assuming meaning.definitionText is now Chinese from backend based on user's earlier broader request -->
              {{ meaning.definitionText }}
              <span v-if="meaning.exampleSentenceForMeaning" class="example-sentence-inline"> (例: "{{ meaning.exampleSentenceForMeaning }}")</span> <!-- Translated "e.g." -->
            </li>
          </ul>
        </div>

        <div class="inflections-section" v-if="entry.inflections && hasInflections(entry.inflections)">
          <h4>词形变化:</h4> <!-- Translated -->
          <ul class="inflections-list">
            <li v-if="entry.inflections.plural"><strong>复数:</strong> {{ entry.inflections.plural }}</li> <!-- Translated -->
            <li v-if="entry.inflections.thirdPersonSingular"><strong>第三人称单数:</strong> {{ entry.inflections.thirdPersonSingular }}</li> <!-- Translated -->
            <li v-if="entry.inflections.pastTense"><strong>过去式:</strong> {{ entry.inflections.pastTense }}</li> <!-- Translated -->
            <li v-if="entry.inflections.pastParticiple"><strong>过去分词:</strong> {{ entry.inflections.pastParticiple }}</li> <!-- Translated -->
            <li v-if="entry.inflections.presentParticiple"><strong>现在分词:</strong> {{ entry.inflections.presentParticiple }}</li> <!-- Translated -->
          </ul>
        </div>

        <div class="general-example-sentences-section" v-if="entry.generalExampleSentences && entry.generalExampleSentences.length">
          <h4>通用例句 (词性 "{{ getPartOfSpeechAbbreviation(entry.partOfSpeech) }}"):</h4> <!-- Translated -->
          <ul>
            <li v-for="(sentence, sIndex) in entry.generalExampleSentences" :key="sIndex" class="example-sentence-item">
              <!-- Assuming sentence is now Chinese from backend -->
              {{ sentence }}
            </li>
          </ul>
        </div>
      </div>

    </div>
    <div class="no-results" v-if="!isLoading && showResults && !searchResult">
      <p>未找到 "{{ submittedQuery }}" 的结果。请检查拼写或尝试其他单词。</p> <!-- Translated -->
    </div>
    <div class="error-message" v-if="!isLoading && errorMessage">
        <p>错误: {{ errorMessage }}</p> <!-- "错误:" is Chinese for Error: -->
    </div>
  </div>
</template>

<script>
import axios from 'axios';

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
      posAbbreviations: {
        'noun': '名.', 'verb': '动.', 'verb_transitive': 'vt.', 'verb_intransitive': 'vi.',
        'adjective': '形.', 'adverb': '副.', 'pronoun': '代.', 'preposition': '介.',
        'conjunction': '连.', 'interjection': '感.'
      }
    };
  },
  methods: {
    async performSearch() {
      if (!this.searchQuery.trim()) return;
      this.submittedQuery = this.searchQuery.trim();
      this.isLoading = true;
      this.showResults = false; this.searchResult = null; this.errorMessage = null;
      try {
        const response = await axios.get('/api/dictionary/' + encodeURIComponent(this.submittedQuery));
        this.searchResult = response.data ? response.data : null;
      } catch (error) {
        console.error("Error fetching dictionary data:", error);
        this.searchResult = null;
        if (error.response && error.response.status === 404) { this.errorMessage = null; } // "No results" message will be shown
        else if (error.request) { this.errorMessage = "无法连接到词典服务，请稍后再试。"; } // Translated
        else { this.errorMessage = "发生意外错误。"; } // Translated
      } finally {
        this.isLoading = false; this.showResults = true;
      }
    },
    hasInflections(inflections) {
      if (!inflections) return false;
      return Object.values(inflections).some(value => value !== null && value !== '' && value !== 0);
    },
    getPartOfSpeechAbbreviation(partOfSpeech) {
      if (!partOfSpeech) return '';
      return this.posAbbreviations[partOfSpeech.toLowerCase()] || partOfSpeech;
    }
  }
}
</script>

<style scoped>
/* ... existing styles from previous step ... */
.dictionary-view { padding: 20px; display: flex; flex-direction: column; align-items: center; }
.search-container { display: flex; align-items: center; width: 100%; max-width: 600px; border: 1px solid #dfe1e5; border-radius: 24px; padding: 5px 15px; box-shadow: 0 2px 5px 1px rgba(60,64,67,.15); margin-bottom: 30px; }
.search-container:hover, .search-container:focus-within { box-shadow: 0 2px 8px 1px rgba(60,64,67,.25); }
.search-input { flex-grow: 1; border: none; outline: none; padding: 10px; font-size: 16px; background-color: transparent; }
.search-input:disabled { background-color: #f0f0f0; }
.search-button { background: none; border: none; padding: 8px; margin-left: 8px; cursor: pointer; display: flex; align-items: center; justify-content: center; color: #4285f4; min-width: 40px; min-height: 40px; }
.search-button:disabled { cursor: not-allowed; opacity: 0.5; }
.search-button:hover svg { opacity: 0.8; }
.loader { border: 4px solid #f3f3f3; border-top: 4px solid #3498db; border-radius: 50%; width: 20px; height: 20px; animation: spin 1s linear infinite; }
@keyframes spin { 0% { transform: rotate(0deg); } 100% { transform: rotate(360deg); } }
.loading-message, .no-results, .error-message { margin-top: 20px; color: #555; font-size: 1.1em; text-align: center; } /* Added text-align center */
.error-message { color: red; font-weight: bold; }
.results-container { width: 100%; max-width: 800px; text-align: left; border: 1px solid #e0e0e0; padding: 20px; border-radius: 8px; background-color: #f9f9f9; }
.word-title { font-size: 2.5em; color: #2c3e50; margin-bottom: 20px; border-bottom: 2px solid #7f8c8d; padding-bottom: 10px; }
.result-entry { margin-bottom: 25px; }
.pos-and-meanings { display: flex; align-items: flex-start; margin-bottom: 10px; }
.part-of-speech { font-weight: bold; color: #2c3e50; font-size: 1.2em; margin-right: 10px; white-space: nowrap; }
.meanings-list { list-style-type: none; padding-left: 0; margin-top: 0; flex-grow: 1; }
.meaning-item { margin-bottom: 8px; line-height: 1.6; font-size: 0.95em; }
.inflections-section h4,
.general-example-sentences-section h4 { font-size: 1.1em; color: #333; margin-bottom: 8px; margin-top: 15px; }
.inflections-section ul,
.general-example-sentences-section ul { list-style-type: none; padding-left: 0; }
.inflections-section .inflections-list li,
.general-example-sentences-section .example-sentence-item { margin-bottom: 8px; line-height: 1.6; font-size: 0.95em; }
.inflections-list li strong { color: #555; } /* These strong tags are now Chinese */
.example-sentence-inline { font-style: italic; color: #777; }
.example-sentence-item { padding-left: 10px; border-left: 3px solid #bdc3c7; }
</style>
