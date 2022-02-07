/**
 *  首页
 */
define(['app','commonService','ngInfiniteScroll'], function(app){
	return app.controller('faXianCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
        $scope.currentNav = "faxian";
        $scope.articleClsList = [];
        $scope.articleClsId = "";
        $scope.pageInfoObj = {};

		//初始化数据方法
        $scope.searchArticleListPage = function(){
            var url = "/article/selectActicleListPage.jsn";
            var param = "queryObj.clsId="+$scope.articleClsId;
            commonService.generatePageObj($scope.pageInfoObj,url,5);
            $scope.pageInfoObj.param = param;
            $scope.nextArticlePage();
        }

        //下一页
        $scope.nextArticlePage = function(){
            var promis = commonService.getNextPageObjectByCondition($scope.pageInfoObj);
            if(promis){
                promis.then(function(list){
                    //设置点击量和图片
                    for(i=0;i<list.length;i++){
                        var article = list[i];
                        var clickNum = article.clickNum;
                        var mainPhoto = article.mainPhoto;
                        if(!clickNum || clickNum==''){
                            article.clickNum = 0;
                        }
                        if(!mainPhoto || mainPhoto==''){
                            article.mainPhoto = "images/news.jpg";
                        }else{
                            if(article.mainPhoto.indexOf("http")!=0){
                                article.mainPhoto = appConfig.baseImgUrl + article.mainPhoto ;
                            }
                        }
                    }
                });
            }
        }

        //查询新闻分类
        $scope.selectCmsCls = function(){
            var url = "/article/selectArticleCls.jsn";
            $rootScope.request(url,{},function(data){
                if(data.success){
                    $scope.articleClsList = data.data;
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //去到新闻详情页
        $scope.gotoDetailPage = function(id){
        	$rootScope.changePage("/faxianDetail/"+id);
		}

		//根据分类查询新闻列表
		$scope.freshNewsList = function(clsId){
            $scope.articleClsId = clsId;

            $scope.searchArticleListPage();
        }

        //去到公共服务平台
        $scope.gotoCommonServe = function(){
            window.location.href = "https://www.qhdrsj.gov.cn/newplatform/#/";
        }

        //查询初始化数据
        $scope.searchArticleListPage();
        //查询新闻分类
        $scope.selectCmsCls();

	}]);
});