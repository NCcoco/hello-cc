const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,

  // Set the output directory for the build.
  // This path is relative to the my-website-frontend directory.
  // It places the built assets into the Spring Boot static resources directory.
  outputDir: '../src/main/resources/static',

  // publicPath is the base URL your application is served from.
  // For serving from the root of the Spring Boot app, '/' is usually correct.
  // If you were deploying to a subpath like /myapp/, you'd set it here.
  publicPath: '/',

  // devServer proxy configuration (already discussed, useful for separate dev mode)
  // This doesn't affect the build for Spring Boot serving, but good to keep if used.
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // Your Spring Boot backend URL
        changeOrigin: true,
      }
    }
  }
})
