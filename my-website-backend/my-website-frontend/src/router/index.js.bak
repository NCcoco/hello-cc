import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue'; // Import HomeView
import HelloWorld from '../components/HelloWorld.vue'; // Still needed for other placeholders
import EnglishView from '../views/EnglishView.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView // Use HomeView for the homepage
    // Props for HomeView can be defined here if needed, or handled within HomeView itself
  },
  {
    path: '/english',
    name: 'English',
    component: EnglishView
  },
  {
    path: '/java',
    name: 'Java',
    component: HelloWorld, // HelloWorld is now a generic placeholder
    props: { pageTitle: 'Java Section', msg: 'Content for the Java section is under development.' }
  },
  {
    path: '/ai',
    name: 'AI',
    component: HelloWorld, // HelloWorld is now a generic placeholder
    props: { pageTitle: 'Artificial Intelligence Section', msg: 'Content for AI is under development.' }
  },
  {
    path: '/math',
    name: 'Math',
    component: HelloWorld, // HelloWorld is now a generic placeholder
    props: { pageTitle: 'Mathematics Section', msg: 'Content for Mathematics is under development.' }
  },
  {
    path: '/dev',
    name: 'DevInProgress',
    component: HelloWorld, // HelloWorld is now a generic placeholder
    props: { pageTitle: 'Under Development', msg: 'This section is currently under development.' }
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
