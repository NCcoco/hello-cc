import { createRouter, createWebHistory } from 'vue-router';
import HelloWorld from '../components/HelloWorld.vue'; // Assuming HelloWorld is general enough for now

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HelloWorld,
    props: { msg: 'Welcome to Your Personal Site' }
  },
  {
    path: '/java',
    name: 'Java',
    component: HelloWorld, // Placeholder
    props: { msg: 'Java Content Page' }
  },
  {
    path: '/ai',
    name: 'AI',
    component: HelloWorld, // Placeholder
    props: { msg: 'Artificial Intelligence Content Page' }
  },
  {
    path: '/math',
    name: 'Math',
    component: HelloWorld, // Placeholder
    props: { msg: 'Mathematics Content Page' }
  },
  {
    path: '/dev',
    name: 'DevInProgress',
    component: HelloWorld, // Placeholder
    props: { msg: 'Content Under Development' }
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
