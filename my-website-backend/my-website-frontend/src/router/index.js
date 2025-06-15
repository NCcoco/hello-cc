import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import HelloWorld from '../components/HelloWorld.vue'; // Generic placeholder
import EnglishView from '../views/EnglishView.vue';
import DictionaryView from '../views/DictionaryView.vue'; // Import DictionaryView

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView
  },
  {
    path: '/english', // This page shows options: Dictionary, Vocab, Sentences
    name: 'English',
    component: EnglishView
  },
  {
    path: '/dictionary', // The actual dictionary page
    name: 'Dictionary',
    component: DictionaryView
  },
  // Placeholder for Vocabulary
  {
    path: '/vocabulary',
    name: 'Vocabulary',
    component: HelloWorld, // Using generic placeholder for now
    props: { pageTitle: 'Vocabulary Book', msg: 'Vocabulary Book module is under construction.' }
  },
  // Placeholder for Favorite Sentences
  {
    path: '/sentences',
    name: 'Sentences',
    component: HelloWorld, // Using generic placeholder for now
    props: { pageTitle: 'Favorite Sentences', msg: 'Favorite Sentences module is under construction.' }
  },
  {
    path: '/java',
    name: 'Java',
    component: HelloWorld,
    props: { pageTitle: 'Java Section', msg: 'Content for the Java section is under development.' }
  },
  {
    path: '/ai',
    name: 'AI',
    component: HelloWorld,
    props: { pageTitle: 'Artificial Intelligence Section', msg: 'Content for AI is under development.' }
  },
  {
    path: '/math',
    name: 'Math',
    component: HelloWorld,
    props: { pageTitle: 'Mathematics Section', msg: 'Content for Mathematics is under development.' }
  },
  {
    path: '/dev',
    name: 'DevInProgress',
    component: HelloWorld,
    props: { pageTitle: 'Under Development', msg: 'This section is currently under development.' }
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
