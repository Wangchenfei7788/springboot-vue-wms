const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    historyApiFallback: true,
    allowedHosts: "all",
    client: {
      webSocketURL: 'wss://localhost:8080/',
    },
  }

})

