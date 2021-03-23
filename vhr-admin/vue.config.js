// module.exports = {
//   devServer: {
//     proxy: {
//       '/': {
//         // 配置请求的根路径
//         target: 'http://127.0.0.1:8081',
//         changeOrigin: true,
//         pathRewrite: {
//           '^/': ''
//         }
//       }
//     }
//   }
// }
module.exports = {
  devServer: {
      proxy: 'http://localhost:8081'
  }
}
