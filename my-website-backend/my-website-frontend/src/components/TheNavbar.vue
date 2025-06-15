<template>
  <nav class="navbar-full-width">
    <div class="navbar-content-wrapper">
      <div class="navbar-brand">
        <router-link to="/" class="navbar-item brand-text">CC</router-link>
      </div>
      <div class="navbar-menu">
        <router-link to="/" class="navbar-item">首页</router-link>
        <router-link to="/english" class="navbar-item">英语</router-link> <!-- New Button -->
        <router-link to="/java" class="navbar-item">JAVA</router-link>
        <router-link to="/ai" class="navbar-item">人工智能</router-link>
        <router-link to="/math" class="navbar-item">数学</router-link>
        <router-link to="/dev" class="navbar-item">开发中...</router-link>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'TheNavbar'
}
</script>

<style scoped>
.navbar-full-width {
  background-color: #24292e;
  color: #ffffff;
  padding: 0.5rem 1rem;
  border-bottom: 1px solid #1b1f23;
  width: 100%;
  box-sizing: border-box;
}

.navbar-content-wrapper {
  max-width: 1012px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
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
.navbar-item.router-link-exact-active {
  background-color: #40474f;
  border-radius: 6px;
}
</style>
