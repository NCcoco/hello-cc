<template>
  <nav class="navbar">
    <div class="navbar-brand">
      <router-link to="/" class="navbar-item brand-text">MySite</router-link>
    </div>
    <div class="navbar-menu">
      <router-link to="/" class="navbar-item">首页</router-link>
      <router-link to="/java" class="navbar-item">JAVA</router-link>
      <router-link to="/ai" class="navbar-item">人工智能</router-link>
      <router-link to="/math" class="navbar-item">数学</router-link>
      <router-link to="/dev" class="navbar-item">开发中...</router-link>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'TheNavbar'
}
</script>

<style scoped>
.navbar {
  background-color: #24292e; /* GitHub's header background color */
  color: #ffffff;
  padding: 0.5rem 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #d0d7de;
}

.navbar-brand {
  display: flex;
  align-items: center;
}

.brand-text {
  font-weight: bold;
  font-size: 1.25rem;
}

.navbar-menu {
  display: flex;
}

.navbar-item {
  color: #ffffff;
  padding: 0.5rem 0.75rem;
  text-decoration: none;
  transition: background-color 0.3s ease;
}

.navbar-item:hover,
.navbar-item.router-link-exact-active { /* Style for active link */
  background-color: #40474f;
  border-radius: 6px;
}
</style>
