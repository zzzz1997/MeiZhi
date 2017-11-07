package com.zzapp.meizhi.utils

/**
 * Project MeiZhi
 * Date 2017-11-05
 *
 * @author zzzz
 */

class DayBean {
    /**
     * category : ["休息视频","瞎推荐","Android","前端","iOS","福利"]
     * error : false
     * results : {"Android":[{"_id":"59f8553f421aa90fef2034e9","createdAt":"2017-10-31T18:49:35.980Z","desc":"深度学习js与安卓的交互以及WebView的那些坑","images":["http://img.gank.io/d1d4f7b4-9291-499a-8b20-c3c485c46119"],"publishedAt":"2017-11-01T14:20:59.209Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/b9164500d3fb","used":true,"who":"阿韦"},{"_id":"59f92869421aa90fe50c01c1","createdAt":"2017-11-01T09:50:33.515Z","desc":"Android启动页黑屏及最优解决方案","publishedAt":"2017-11-01T14:20:59.209Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247487886&idx=1&sn=6fbe4d971e873ee351aef213eedba0ae","used":true,"who":"陈宇明"},{"_id":"59f92b44421aa90fe50c01c3","createdAt":"2017-11-01T10:02:44.598Z","desc":"可设定阴影颜色的shadow-layout","images":["http://img.gank.io/d3acd780-a1a6-4529-a026-b8bd7967626a"],"publishedAt":"2017-11-01T14:20:59.209Z","source":"chrome","type":"Android","url":"https://github.com/dmytrodanylyk/shadow-layout","used":true,"who":"galois"},{"_id":"59f937f1421aa90fe50c01c4","createdAt":"2017-11-01T10:56:49.711Z","desc":"LeetCode的Java题解(updating)","publishedAt":"2017-11-01T14:20:59.209Z","source":"web","type":"Android","url":"https://github.com/Blankj/awesome-java-leetcode","used":true,"who":"Mengjie Cai"},{"_id":"59f95971421aa90fef2034ec","createdAt":"2017-11-01T13:19:45.187Z","desc":"Facebook面经记","images":["http://img.gank.io/7aa86243-57c4-43a4-80ba-de2809dd106e"],"publishedAt":"2017-11-01T14:20:59.209Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/fd8d3478f6ee","used":true,"who":"Mengjie Cai"}],"iOS":[{"_id":"59f9668f421aa90fe2f02c0f","createdAt":"2017-11-01T14:15:43.827Z","desc":"交互便捷的 iOS 选择器组件。","images":["http://img.gank.io/a0e25a0b-b5aa-4e21-a273-c7e08ef7e0de"],"publishedAt":"2017-11-01T14:20:59.209Z","source":"chrome","type":"iOS","url":"https://github.com/blueapron/Mandoline","used":true,"who":"代码家"}],"休息视频":[{"_id":"59f74442421aa90fe50c01b4","createdAt":"2017-10-30T23:24:50.283Z","desc":"新海诚的动画短片来自谁的凝望","publishedAt":"2017-11-01T14:20:59.209Z","source":"chrome","type":"休息视频","url":"http://www.miaopai.com/show/JcgCcPco3LYy3z7eQKDei2izkff3R9DO-OPWNw__.htm","used":true,"who":"lxxself"}],"前端":[{"_id":"59f93495421aa90fe2f02c0e","createdAt":"2017-11-01T10:42:29.763Z","desc":"开箱即用的中台前端/设计解决方案","publishedAt":"2017-11-01T14:20:59.209Z","source":"web","type":"前端","url":"http://pro.ant.design/","used":true,"who":"niko"}],"瞎推荐":[{"_id":"59f80a8a421aa90fe50c01ba","createdAt":"2017-10-31T13:30:50.789Z","desc":"Android、iOS历史版本对比","publishedAt":"2017-11-01T14:20:59.209Z","source":"chrome","type":"瞎推荐","url":"http://www.jianshu.com/p/042f0660510a","used":true,"who":"galois"},{"_id":"59f9216f421aa90fe72535e3","createdAt":"2017-11-01T09:20:47.721Z","desc":"基于Golang的跨平台解决方案","publishedAt":"2017-11-01T14:20:59.209Z","source":"web","type":"瞎推荐","url":"https://github.com/gomatcha/matcha","used":true,"who":"Modificator"}],"福利":[{"_id":"59f9674c421aa90fe50c01c6","createdAt":"2017-11-01T14:18:52.937Z","desc":"11-1","publishedAt":"2017-11-01T14:20:59.209Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171101141835_yQYTXc_enakorin_1_11_2017_14_16_45_351.jpeg","used":true,"who":"daimajia"}]}
     */

    var isError: Boolean = false
    var results: ResultsBean? = null
    var category: List<String>? = null

    class ResultsBean {
        var Android: List<AndroidBean>? = null
        var iOS: List<IOSBean>? = null
        var 休息视频: List<休息视频Bean>? = null
        var 前端: List<前端Bean>? = null
        var 瞎推荐: List<瞎推荐Bean>? = null
        var 福利: List<福利Bean>? = null

        class AndroidBean {
            /**
             * _id : 59f8553f421aa90fef2034e9
             * createdAt : 2017-10-31T18:49:35.980Z
             * desc : 深度学习js与安卓的交互以及WebView的那些坑
             * images : ["http://img.gank.io/d1d4f7b4-9291-499a-8b20-c3c485c46119"]
             * publishedAt : 2017-11-01T14:20:59.209Z
             * source : web
             * type : Android
             * url : http://www.jianshu.com/p/b9164500d3fb
             * used : true
             * who : 阿韦
             */

            var _id: String? = null
            var createdAt: String? = null
            var desc: String? = null
            var publishedAt: String? = null
            var source: String? = null
            var type: String? = null
            var url: String? = null
            var isUsed: Boolean = false
            var who: String? = null
            var images: List<String>? = null
        }

        class IOSBean {
            /**
             * _id : 59f9668f421aa90fe2f02c0f
             * createdAt : 2017-11-01T14:15:43.827Z
             * desc : 交互便捷的 iOS 选择器组件。
             * images : ["http://img.gank.io/a0e25a0b-b5aa-4e21-a273-c7e08ef7e0de"]
             * publishedAt : 2017-11-01T14:20:59.209Z
             * source : chrome
             * type : iOS
             * url : https://github.com/blueapron/Mandoline
             * used : true
             * who : 代码家
             */

            var _id: String? = null
            var createdAt: String? = null
            var desc: String? = null
            var publishedAt: String? = null
            var source: String? = null
            var type: String? = null
            var url: String? = null
            var isUsed: Boolean = false
            var who: String? = null
            var images: List<String>? = null
        }

        class 休息视频Bean {
            /**
             * _id : 59f74442421aa90fe50c01b4
             * createdAt : 2017-10-30T23:24:50.283Z
             * desc : 新海诚的动画短片来自谁的凝望
             * publishedAt : 2017-11-01T14:20:59.209Z
             * source : chrome
             * type : 休息视频
             * url : http://www.miaopai.com/show/JcgCcPco3LYy3z7eQKDei2izkff3R9DO-OPWNw__.htm
             * used : true
             * who : lxxself
             */

            var _id: String? = null
            var createdAt: String? = null
            var desc: String? = null
            var publishedAt: String? = null
            var source: String? = null
            var type: String? = null
            var url: String? = null
            var isUsed: Boolean = false
            var who: String? = null
        }

        class 前端Bean {
            /**
             * _id : 59f93495421aa90fe2f02c0e
             * createdAt : 2017-11-01T10:42:29.763Z
             * desc : 开箱即用的中台前端/设计解决方案
             * publishedAt : 2017-11-01T14:20:59.209Z
             * source : web
             * type : 前端
             * url : http://pro.ant.design/
             * used : true
             * who : niko
             */

            var _id: String? = null
            var createdAt: String? = null
            var desc: String? = null
            var publishedAt: String? = null
            var source: String? = null
            var type: String? = null
            var url: String? = null
            var isUsed: Boolean = false
            var who: String? = null
        }

        class 瞎推荐Bean {
            /**
             * _id : 59f80a8a421aa90fe50c01ba
             * createdAt : 2017-10-31T13:30:50.789Z
             * desc : Android、iOS历史版本对比
             * publishedAt : 2017-11-01T14:20:59.209Z
             * source : chrome
             * type : 瞎推荐
             * url : http://www.jianshu.com/p/042f0660510a
             * used : true
             * who : galois
             */

            var _id: String? = null
            var createdAt: String? = null
            var desc: String? = null
            var publishedAt: String? = null
            var source: String? = null
            var type: String? = null
            var url: String? = null
            var isUsed: Boolean = false
            var who: String? = null
        }

        class 福利Bean {
            /**
             * _id : 59f9674c421aa90fe50c01c6
             * createdAt : 2017-11-01T14:18:52.937Z
             * desc : 11-1
             * publishedAt : 2017-11-01T14:20:59.209Z
             * source : chrome
             * type : 福利
             * url : http://7xi8d6.com1.z0.glb.clouddn.com/20171101141835_yQYTXc_enakorin_1_11_2017_14_16_45_351.jpeg
             * used : true
             * who : daimajia
             */

            var _id: String? = null
            var createdAt: String? = null
            var desc: String? = null
            var publishedAt: String? = null
            var source: String? = null
            var type: String? = null
            var url: String? = null
            var isUsed: Boolean = false
            var who: String? = null
        }
    }
}
