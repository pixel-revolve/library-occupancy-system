const webpack = require('webpack')

module.exports = {
    chainWebpack: config => {
        config.plugin('provide').use(webpack.ProvidePlugin, [{
            $: 'jquery',
            jquery: 'jquery',
            jQuery: 'jquery',
            'window.jQuery': 'jquery'
        }])
    },
    publicPath: './',
    devServer: {
        port: 8850,
        proxy: {
            // 代理标识
            '/api': {
                // 实际请求地址
                target: 'http://127.0.0.1:8887/',
                // 关闭跨域请求
                changOrigin: true
            },
            '/avatar':{
                target: 'http://127.0.0.1:8887/',
                // 关闭跨域请求
                changOrigin: true
            }
        }
    }

}
